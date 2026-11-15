/**
 * ============================================================================
 * PicoPhrase Modern Engine (engine.js)
 * Port direct de l'architecture C++ décompilée (cpp_decompiled/) :
 * - BookSettings, GlobalParams, ImageDatabase, PositionManager, PageLogicManager
 * - ColourLabelParser (phonèmes après '|' en rouge + red-line.svg)
 * - SpineIntro -> HomePageScene -> BookCoverScene -> OptionsScene -> PageScene -> BookEndPageScene
 * - PinLayer / FullScreenPinLock (contrôle parental 3 chiffres en ordre croissant)
 * - SettingScene (5 onglets : PictoPhrase, Information, Langue, Boutique, Notation)
 * - BuyTriScene (écran 3 offres pour les livres verrouillés)
 * ============================================================================
 */

(function () {
    "use strict";

    // ========================================================================
    // 1. Pont Natif Android (PicoNative) + Fallback Web
    // ========================================================================
    const NativeBridge = {
        readJson(assetPath) {
            if (window.PicoNative && typeof window.PicoNative.readAssetText === "function") {
                const raw = window.PicoNative.readAssetText(assetPath);
                if (raw && raw.length > 0) {
                    return JSON.parse(raw);
                }
            }
            throw new Error("Impossible de lire l'asset JSON : " + assetPath);
        },

        assetExists(assetPath) {
            if (window.PicoNative && typeof window.PicoNative.assetExists === "function") {
                return !!window.PicoNative.assetExists(assetPath);
            }
            return true;
        },

        getLanguage() {
            if (window.PicoNative && typeof window.PicoNative.getLanguage === "function") {
                return Number(window.PicoNative.getLanguage());
            }
            return 2; // QUEBEC par défaut
        },

        setLanguage(lang) {
            if (window.PicoNative && typeof window.PicoNative.setLanguage === "function") {
                window.PicoNative.setLanguage(Number(lang));
            }
        },

        isProductPurchased(bookFolderOrSku) {
            if (window.PicoNative && typeof window.PicoNative.isProductPurchased === "function") {
                return !!window.PicoNative.isProductPurchased(String(bookFolderOrSku));
            }
            return bookFolderOrSku === "book-panda";
        },

        playAudio(relativePath) {
            if (!relativePath) return;
            const cleanPath = relativePath.replace(/\\/g, "/");
            if (window.PicoNative && typeof window.PicoNative.playAudio === "function") {
                window.PicoNative.playAudio(cleanPath);
            } else {
                const audio = new Audio("../" + cleanPath);
                audio.play().catch(function () { });
            }
        },

        stopAllAudio() {
            if (window.PicoNative && typeof window.PicoNative.stopAllAudio === "function") {
                window.PicoNative.stopAllAudio();
            }
        },

        getAudioDuration(relativePath, bookFolder) {
            if (!relativePath) return 0;
            const cleanPath = relativePath.replace(/\\/g, "/");
            if (window.PicoNative && typeof window.PicoNative.getAudioDuration === "function") {
                try {
                    return window.PicoNative.getAudioDuration(cleanPath, bookFolder || "");
                } catch (e) {
                    return 0;
                }
            }
            return 0;
        },

        openExternalUrl(url) {
            if (window.PicoNative && typeof window.PicoNative.openExternalUrl === "function") {
                window.PicoNative.openExternalUrl(url);
            } else {
                window.open(url, "_blank");
            }
        }
    };

    // ========================================================================
    // 2. BookSettings & Énumérations (BookSettings.h)
    // ========================================================================
    const eLanguage = {
        FRENCH: 0,
        ENGLISH: 1,
        QUEBEC: 2
    };

    const PageDifficulty = {
        LEVEL_1_EASY: 0,
        LEVEL_2_MEDIUM: 1,
        LEVEL_3_HARD: 2
    };

    const ePageTurnMode = {
        AUTOMATIC: 0,
        MANUALLY: 1,
        CODE_REQUIRED: 2
    };

    const BookSettings = {
        language: eLanguage.QUEBEC,
        difficulty: PageDifficulty.LEVEL_1_EASY,
        writtenMode: false, // false = Phrases (pictogrammes + mots), true = Langage écrit
        pageTurnMode: ePageTurnMode.AUTOMATIC,
        activeBookFolder: "book-panda",

        isEnglish() {
            return this.language === eLanguage.ENGLISH;
        },

        getBookLangKey() {
            if (this.language === eLanguage.ENGLISH) return "en";
            if (this.language === eLanguage.QUEBEC) return "qu";
            return "fr";
        }
    };

    // ========================================================================
    // 3. GlobalParams, ImageDatabase & Convertisseur PNG -> SVG
    // ========================================================================
    const EngineState = {
        globalParams: {
            shinyDingDelay: 0.2,
            autoChangePageDelay: 1.0,
            pinFailedHideTime: 1.0
        },
        imageDb: {},
        buyInfo: { items: [] },
        commonSvgMap: {}, // dest (.png) -> { src, width, height, folder: 'common' }
        bookSvgMap: {},   // dest (.png) -> { src, width, height, folder: '<book>' }
        carouselIndex: 0,
        activeTimers: [],
        currentBookData: null,
        currentPageIndex: 0
    };

    function clearAllTimers() {
        while (EngineState.activeTimers.length > 0) {
            clearTimeout(EngineState.activeTimers.pop());
        }
    }

    function scheduleTimer(fn, delayMs) {
        const id = setTimeout(fn, delayMs);
        EngineState.activeTimers.push(id);
        return id;
    }

    function loadCoreDatabases() {
        try {
            EngineState.globalParams = NativeBridge.readJson("global.json");
        } catch (e) {
            console.warn(e);
        }

        EngineState.imageDb = NativeBridge.readJson("imagedatabase.json");
        EngineState.buyInfo = NativeBridge.readJson("BuyInfo.json");

        // Charge common/imageconversion.json et imageconversion.json
        const commonConv = NativeBridge.readJson("common/imageconversion.json");
        commonConv.forEach(function (entry) {
            EngineState.commonSvgMap[entry.dest] = {
                src: "../common/" + entry.src,
                width: entry.width,
                height: entry.height
            };
        });

        try {
            const rootConv = NativeBridge.readJson("imageconversion.json");
            rootConv.forEach(function (entry) {
                if (!EngineState.commonSvgMap[entry.dest]) {
                    EngineState.commonSvgMap[entry.dest] = {
                        src: "../common/" + entry.src,
                        width: entry.width,
                        height: entry.height
                    };
                }
            });
        } catch (e) { }
    }

    function loadBookConversion(bookFolder) {
        EngineState.bookSvgMap = {};
        try {
            const bookConv = NativeBridge.readJson(bookFolder + "/imageconversion.json");
            bookConv.forEach(function (entry) {
                EngineState.bookSvgMap[entry.dest] = {
                    src: "../" + bookFolder + "/" + entry.src,
                    width: entry.width,
                    height: entry.height
                };
            });
        } catch (e) {
            console.warn("Pas de imageconversion.json pour " + bookFolder, e);
        }
    }

    /**
     * Résout n'importe quel nom d'image (.png, .svg, .jpg) en son chemin réel dans assets/
     * et ses dimensions en pourcentage d'écran issues de imageconversion.json.
     */
    function resolveAssetImage(pngOrFileName, bookFolder) {
        if (!pngOrFileName) return { src: "", width: 10, height: 10 };

        if (EngineState.bookSvgMap[pngOrFileName]) {
            return EngineState.bookSvgMap[pngOrFileName];
        }
        if (EngineState.commonSvgMap[pngOrFileName]) {
            return EngineState.commonSvgMap[pngOrFileName];
        }

        // Fallback direct vers .svg dans le dossier du livre ou dans common/
        const svgName = pngOrFileName.replace(/\.png$/i, ".svg");
        if (bookFolder && NativeBridge.assetExists(bookFolder + "/" + svgName)) {
            return { src: "../" + bookFolder + "/" + svgName, width: 20, height: 20 };
        }
        if (NativeBridge.assetExists("common/" + svgName)) {
            return { src: "../common/" + svgName, width: 20, height: 20 };
        }
        if (bookFolder && NativeBridge.assetExists(bookFolder + "/" + pngOrFileName)) {
            return { src: "../" + bookFolder + "/" + pngOrFileName, width: 25, height: 20 };
        }
        if (NativeBridge.assetExists("res/" + pngOrFileName)) {
            return { src: "../res/" + pngOrFileName, width: 25, height: 20 };
        }
        return { src: "../common/" + svgName, width: 20, height: 20 };
    }

    /**
     * Équivalent de ImageDatabase::makeMenuItemImageForID(id, settings)
     * Crée un bouton positionné aux coordonnées x%, y% définies dans imagedatabase.json.
     */
    function createDbMenuButton(id, onClick, options) {
        options = options || {};
        const entry = EngineState.imageDb[id];
        if (!entry) {
            console.warn("Clé ImageDatabase introuvable : " + id);
            return document.createElement("div");
        }

        const isEn = BookSettings.isEnglish();
        const isSelectedState = !!options.selected;

        let normalPng = isEn ? entry.english : entry.french;
        let selectedPng = isEn ? entry.english_selected : entry.french_selected;
        if (isSelectedState) {
            normalPng = selectedPng;
        }

        const resolvedNormal = resolveAssetImage(normalPng, BookSettings.activeBookFolder);
        const resolvedSelected = resolveAssetImage(selectedPng, BookSettings.activeBookFolder);

        const img = document.createElement("img");
        img.className = "cc-btn" + (options.flipX ? " cc-btn-flipped-x" : "");
        img.src = resolvedNormal.src;
        img.alt = id;

        const x = options.x !== undefined ? options.x : entry.x;
        const y = options.y !== undefined ? options.y : entry.y;
        const width = options.width !== undefined ? options.width : resolvedNormal.width;
        const height = options.height !== undefined ? options.height : resolvedNormal.height;

        img.style.left = x + "%";
        img.style.top = y + "%";
        if (width) img.style.width = width + "%";
        if (height) img.style.height = height + "%";

        if (onClick) {
            img.addEventListener("pointerdown", function () {
                if (!options.selected && resolvedSelected.src) {
                    img.src = resolvedSelected.src;
                }
            });
            img.addEventListener("pointerup", function () {
                if (!options.selected) {
                    img.src = resolvedNormal.src;
                }
            });
            img.addEventListener("pointerleave", function () {
                if (!options.selected) {
                    img.src = resolvedNormal.src;
                }
            });
            img.addEventListener("click", function (e) {
                e.stopPropagation();
                onClick(img);
            });
        } else {
            img.style.pointerEvents = "none";
        }

        return img;
    }

    // ========================================================================
    // 4. ColourLabelParser & PageLogicManager (PinLocksAndUIHelpers.cpp)
    // ========================================================================
    /**
     * Reproduit ColourLabelParser::parseText (0x0037bd3c) :
     * Colorie en rouge et souligne avec red-line.svg le phonème suivant chaque caractère '|'.
     * Exemples :
     * - "Le |panda" -> "Le <span class='phoneme-red'>p</span>anda"
     * - "un |poisson." -> "un <span class='phoneme-red'>p</span>oisson."
     * - "un |chapeau" -> "un <span class='phoneme-red'>ch</span>apeau"
     * - "un |oiseau" -> "un <span class='phoneme-red'>oi</span>seau"
     */
    function formatColourLabel(rawText) {
        if (!rawText) return "";
        if (rawText.indexOf("|") === -1) return escapeHtml(rawText);

        let result = "";
        let i = 0;
        while (i < rawText.length) {
            if (rawText[i] === "|") {
                i++;
                if (i < rawText.length) {
                    // Détecte les digrammes phonétiques fréquents (ch, oi, ou, on, an, in, gn, ph)
                    let phonemeLen = 1;
                    const pair = rawText.substr(i, 2).toLowerCase();
                    if (pair === "ch" || pair === "oi" || pair === "ou" || pair === "gn" || pair === "ph") {
                        phonemeLen = 2;
                    }
                    const highlighted = escapeHtml(rawText.substr(i, phonemeLen));
                    result += '<span class="phoneme-red">' + highlighted + "</span>";
                    i += phonemeLen;
                }
            } else {
                result += escapeHtml(rawText[i]);
                i++;
            }
        }
        return result;
    }

    function escapeHtml(str) {
        return String(str)
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;");
    }

    /**
     * Reproduit exactement PageLogicManager::getOrderForPage (0x00388b98) :
     * Retourne le masque binaire (A=1, B=2, C=4) des cartes que l'enfant doit glisser.
     */
    function getOrderForPage(pageNum, difficulty) {
        if (difficulty === PageDifficulty.LEVEL_1_EASY) {
            const group = Math.floor((pageNum - 1) / 2) % 3;
            if (group === 0) return 4; // Seul C est à glisser (A et B sont déjà en place)
            if (group === 1) return 2; // Seul B est à glisser (A et C sont déjà en place)
            return 1;                  // Seul A est à glisser (B et C sont déjà en place)
        }
        if (difficulty === PageDifficulty.LEVEL_2_MEDIUM) {
            return pageNum < 7 ? 6 : 3; // Pages 1..6 : B+C (6), Pages 7+ : A+B (3)
        }
        return 7; // Niveau 3 : A + B + C (7)
    }

    // ========================================================================
    // 5. PinLayer / Contrôle Parental (Désactivé : accès direct sans popup)
    // ========================================================================
    function showParentalPinGate(onSuccessCallback) {
        if (typeof onSuccessCallback === "function") {
            onSuccessCallback();
        }
    }

    // ========================================================================
    // 6. Écran Splash d'introduction (Désactivé : démarrage instantané sur l'accueil)
    // ========================================================================
    function renderSplashIntroScene() {
        renderHomePageScene();
    }

    // ========================================================================
    // 7. HomePageScene & HomePageLayer (Carrousel des livres 3D)
    // ========================================================================
    function getHomeBooks() {
        return (EngineState.buyInfo.items || []).filter(function (item) {
            return Number(item.AppearOnHome) === 1;
        });
    }

    /**
     * Recherche le fichier audio du titre ou du sujet pour l'annonce vocale
     * lors de l'arrêt du carrousel sur un livre.
     */
    function getBookAnnouncementAudio(bookFolder) {
        if (!bookFolder) return null;
        const isEn = BookSettings.isEnglish();
        const animal = bookFolder.replace("book-", "");
        let candidates = [];
        if (isEn) {
            candidates = [
                animal + "_title_en.mp3",
                "bear_title_en.mp3",
                animal + "_a_en.mp3",
                animal + "_p1_a_en.mp3",
                "bear_p1_a_en.mp3"
            ];
        } else {
            candidates = [
                animal + "_title_fr.mp3",
                animal + "_title-b_fr.mp3",
                animal + "_title_s.mp3",
                "bear_title_fr.mp3",
                "cat_le_chat_s.mp3",
                "ant_la_fourmi_s.mp3",
                animal + "_a_fr.mp3",
                animal + "_p1_a_fr.mp3",
                animal + "_p1_a_fr_eu.mp3",
                "giraf_p1_a_fr_eu.mp3",
                "bear_p1_a_fr.mp3",
                "Bear_p1_a_fr_eu.mp3",
                "Spider_p1_a_fr_eu.mp3",
                "Turtle_p1_a_fr_eu.mp3"
            ];
        }

        for (let i = 0; i < candidates.length; i++) {
            const candidatePath = bookFolder + "/" + candidates[i];
            if (NativeBridge.assetExists(candidatePath)) {
                return candidatePath;
            }
        }
        return null;
    }

    let homeAnnouncementTimer = null;

    function cancelBookAnnouncement() {
        if (homeAnnouncementTimer) {
            clearTimeout(homeAnnouncementTimer);
            homeAnnouncementTimer = null;
        }
    }

    function scheduleBookAnnouncement(bookFolder) {
        cancelBookAnnouncement();
        NativeBridge.stopAllAudio();

        const audioPath = getBookAnnouncementAudio(bookFolder);
        if (!audioPath) return;

        homeAnnouncementTimer = scheduleTimer(function () {
            NativeBridge.playAudio(audioPath);
            homeAnnouncementTimer = null;
        }, 420);
    }

    function renderHomePageScene() {
        clearAllTimers();
        cancelBookAnnouncement();
        NativeBridge.stopAllAudio();

        const container = document.getElementById("scene-container");
        container.innerHTML = "";

        // 1. Fond de la page d'accueil (homepage-background-ui.svg / _en.svg)
        const bgSvg = BookSettings.isEnglish()
            ? "../common/homepage-background-ui_en.svg"
            : "../common/homepage-background-ui.svg";
        const bg = document.createElement("img");
        bg.className = "cc-bg";
        bg.src = bgSvg;
        container.appendChild(bg);

        // 1.1 Titre PictoPhrase et Choisis ton livre en haut
        const orthoLogo = createDbMenuButton("home_page_ortho_logo", null);
        container.appendChild(orthoLogo);

        const choisisText = createDbMenuButton("home_page_choisis_text", null);
        container.appendChild(choisisText);

        // 2. Bouton Réglages en haut à droite (accès direct sans popup)
        const settingsBtn = createDbMenuButton("home_page_settings", function () {
            cancelBookAnnouncement();
            NativeBridge.stopAllAudio();
            renderSettingScene("orthopicto");
        });
        container.appendChild(settingsBtn);

        // 4. Carrousel des 3 livres visibles en perspective 3D
        const books = getHomeBooks();
        if (books.length === 0) return;

        const carouselContainer = document.createElement("div");
        carouselContainer.className = "book-carousel-container";
        container.appendChild(carouselContainer);

        // Ombres portées au sol sous les livres
        const centerShadow = document.createElement("div");
        centerShadow.className = "book-ground-shadow center-shadow";
        carouselContainer.appendChild(centerShadow);

        if (books.length > 1) {
            const leftShadow = document.createElement("div");
            leftShadow.className = "book-ground-shadow side-shadow left-shadow";
            carouselContainer.appendChild(leftShadow);

            const rightShadow = document.createElement("div");
            rightShadow.className = "book-ground-shadow side-shadow right-shadow";
            carouselContainer.appendChild(rightShadow);
        }

        // Calque pour les cartes de livres (au-dessus des ombres au sol)
        const booksLayer = document.createElement("div");
        booksLayer.style.position = "absolute";
        booksLayer.style.inset = "0";
        booksLayer.style.pointerEvents = "none";
        carouselContainer.appendChild(booksLayer);

        // Gros bouton d'action sous le livre central (JOUER / DÉBLOQUER)
        const playBtn = document.createElement("button");
        playBtn.className = "play-book-btn";
        playBtn.setAttribute("type", "button");
        container.appendChild(playBtn);

        // Indicateur de pagination (dots + badge) sous le bouton
        const dotsContainer = document.createElement("div");
        dotsContainer.className = "carousel-dots-container";
        container.appendChild(dotsContainer);

        function updateDots() {
            if (books.length <= 1) {
                dotsContainer.style.display = "none";
                return;
            }
            dotsContainer.innerHTML = "";
            const total = books.length;
            const current = EngineState.carouselIndex;

            const windowSize = Math.min(total, 7);
            const half = Math.floor(windowSize / 2);
            for (let i = 0; i < windowSize; i++) {
                const dot = document.createElement("div");
                dot.className = "carousel-dot";
                if (i === half) {
                    dot.classList.add("active");
                }
                dotsContainer.appendChild(dot);
            }

            const badge = document.createElement("span");
            badge.className = "carousel-counter-badge";
            badge.textContent = (current + 1) + " / " + total;
            dotsContainer.appendChild(badge);
        }

        function createNavBubble(direction, onClick) {
            const btn = document.createElement("div");
            btn.className = "nav-bubble-btn " + direction;
            btn.setAttribute("role", "button");
            btn.setAttribute("aria-label", direction === "left" ? "Livre précédent" : "Livre suivant");

            const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
            svg.setAttribute("viewBox", "0 0 24 24");
            const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
            if (direction === "left") {
                path.setAttribute("d", "M15 19l-7-7 7-7");
            } else {
                path.setAttribute("d", "M9 5l7 7-7 7");
            }
            svg.appendChild(path);
            btn.appendChild(svg);

            btn.addEventListener("click", function (e) {
                e.stopPropagation();
                onClick();
            });
            return btn;
        }

        function updateCarouselItems() {
            booksLayer.innerHTML = "";
            const total = books.length;
            updateDots();

            const midIdx = (EngineState.carouselIndex + total) % total;
            const centerItem = books[midIdx];
            const isCenterUnlocked = NativeBridge.isProductPurchased(centerItem.book_folder) ||
                NativeBridge.isProductPurchased(centerItem.iap_name);

            // Mise à jour de l'état du gros bouton JOUER / DÉBLOQUER
            if (isCenterUnlocked) {
                playBtn.className = "play-book-btn unlocked";
                playBtn.innerHTML = '<span class="play-btn-icon">▶</span><span class="play-btn-label">' +
                    (BookSettings.isEnglish() ? "PLAY" : "JOUER") + '</span>';
                playBtn.setAttribute("aria-label", BookSettings.isEnglish() ? "Play book" : "Lire le livre");
                playBtn.onclick = function (e) {
                    e.stopPropagation();
                    cancelBookAnnouncement();
                    NativeBridge.stopAllAudio();
                    startBook(centerItem.book_folder);
                };
            } else {
                playBtn.className = "play-book-btn locked";
                playBtn.innerHTML = '<span class="play-btn-icon">🔒</span><span class="play-btn-label">' +
                    (BookSettings.isEnglish() ? "UNLOCK" : "DÉBLOQUER") + '</span>';
                playBtn.setAttribute("aria-label", BookSettings.isEnglish() ? "Unlock book" : "Débloquer le livre");
                playBtn.onclick = function (e) {
                    e.stopPropagation();
                    cancelBookAnnouncement();
                    NativeBridge.stopAllAudio();
                    renderBuyTriScene(centerItem);
                };
            }

            // Programmation de l'annonce vocale après stabilisation (~420ms)
            scheduleBookAnnouncement(centerItem.book_folder);

            if (total === 1) {
                const titlePng = BookSettings.isEnglish() ? centerItem.title_icon_english : centerItem.title_icon_french;
                const resolvedIcon = resolveAssetImage(titlePng, centerItem.book_folder);

                const wrapper = document.createElement("div");
                wrapper.className = "book-carousel-item center-book";
                wrapper.style.left = "50%";
                wrapper.style.top = "52%";
                wrapper.style.width = resolvedIcon.width + "%";
                wrapper.style.height = resolvedIcon.height + "%";
                wrapper.style.pointerEvents = "auto";

                const coverImg = document.createElement("img");
                coverImg.className = "book-cover-img";
                coverImg.src = resolvedIcon.src;
                wrapper.appendChild(coverImg);

                const gloss = document.createElement("div");
                gloss.className = "book-gloss-overlay";
                wrapper.appendChild(gloss);

                if (!isCenterUnlocked) {
                    const lockResolved = resolveAssetImage(EngineState.buyInfo.TitleLockIcon || "lock-ui.png");
                    const lockImg = document.createElement("img");
                    lockImg.className = "book-lock-badge";
                    lockImg.src = lockResolved.src;
                    wrapper.appendChild(lockImg);
                }

                wrapper.addEventListener("click", function (e) {
                    e.stopPropagation();
                    cancelBookAnnouncement();
                    NativeBridge.stopAllAudio();
                    if (isCenterUnlocked) {
                        startBook(centerItem.book_folder);
                    } else {
                        renderBuyTriScene(centerItem);
                    }
                });

                booksLayer.appendChild(wrapper);
                return;
            }

            const leftIdx = (EngineState.carouselIndex - 1 + total) % total;
            const rightIdx = (EngineState.carouselIndex + 1) % total;

            const slots = [
                { book: books[leftIdx], x: 23, y: 53.5, className: "side-book left-book", isCenter: false },
                { book: books[midIdx], x: 50, y: 52, className: "center-book", isCenter: true },
                { book: books[rightIdx], x: 77, y: 53.5, className: "side-book right-book", isCenter: false }
            ];

            slots.forEach(function (slot, slotPos) {
                const item = slot.book;
                const titlePng = BookSettings.isEnglish() ? item.title_icon_english : item.title_icon_french;
                const resolvedIcon = resolveAssetImage(titlePng, item.book_folder);
                const unlocked = NativeBridge.isProductPurchased(item.book_folder) ||
                    NativeBridge.isProductPurchased(item.iap_name);

                const wrapper = document.createElement("div");
                wrapper.className = "book-carousel-item " + slot.className;
                wrapper.style.left = slot.x + "%";
                wrapper.style.top = slot.y + "%";
                wrapper.style.width = resolvedIcon.width + "%";
                wrapper.style.height = resolvedIcon.height + "%";
                wrapper.style.pointerEvents = "auto";

                const coverImg = document.createElement("img");
                coverImg.className = "book-cover-img";
                coverImg.src = resolvedIcon.src;
                wrapper.appendChild(coverImg);

                if (slot.isCenter) {
                    const gloss = document.createElement("div");
                    gloss.className = "book-gloss-overlay";
                    wrapper.appendChild(gloss);
                }

                if (!unlocked) {
                    const lockResolved = resolveAssetImage(EngineState.buyInfo.TitleLockIcon || "lock-ui.png");
                    const lockImg = document.createElement("img");
                    lockImg.className = "book-lock-badge";
                    lockImg.src = lockResolved.src;
                    wrapper.appendChild(lockImg);
                }

                wrapper.addEventListener("click", function (e) {
                    e.stopPropagation();
                    if (slotPos === 0) {
                        EngineState.carouselIndex = (EngineState.carouselIndex - 1 + total) % total;
                        updateCarouselItems();
                        return;
                    }
                    if (slotPos === 2) {
                        EngineState.carouselIndex = (EngineState.carouselIndex + 1) % total;
                        updateCarouselItems();
                        return;
                    }

                    // Clic sur le livre central
                    cancelBookAnnouncement();
                    NativeBridge.stopAllAudio();
                    if (unlocked) {
                        startBook(item.book_folder);
                    } else {
                        renderBuyTriScene(item);
                    }
                });

                booksLayer.appendChild(wrapper);
            });
        }

        updateCarouselItems();

        // 5. Flèches bulles gauche et droite du carrousel (uniquement s'il y a plus d'un livre)
        if (books.length > 1) {
            const leftBubble = createNavBubble("left", function () {
                EngineState.carouselIndex = (EngineState.carouselIndex - 1 + books.length) % books.length;
                updateCarouselItems();
            });
            container.appendChild(leftBubble);

            const rightBubble = createNavBubble("right", function () {
                EngineState.carouselIndex = (EngineState.carouselIndex + 1) % books.length;
                updateCarouselItems();
            });
            container.appendChild(rightBubble);
        }

        // 6. Navigation naturelle au doigt (Glissement / Swipe tactile)
        let touchStartX = 0;
        let touchStartY = 0;
        let touchStartTime = 0;
        let isTouching = false;

        container.addEventListener("touchstart", function (e) {
            if (e.touches.length !== 1) return;
            touchStartX = e.touches[0].clientX;
            touchStartY = e.touches[0].clientY;
            touchStartTime = Date.now();
            isTouching = true;
        }, { passive: true });

        container.addEventListener("touchend", function (e) {
            if (!isTouching || e.changedTouches.length !== 1) return;
            isTouching = false;
            const deltaX = e.changedTouches[0].clientX - touchStartX;
            const deltaY = e.changedTouches[0].clientY - touchStartY;
            const elapsed = Date.now() - touchStartTime;

            // Déplacement horizontal prédominant d'au moins 40px en moins de 750ms
            if (Math.abs(deltaX) > 40 && Math.abs(deltaX) > Math.abs(deltaY) * 1.1 && elapsed < 750) {
                if (deltaX < 0) {
                    // Swipe vers la gauche -> livre suivant
                    EngineState.carouselIndex = (EngineState.carouselIndex + 1) % books.length;
                    updateCarouselItems();
                } else {
                    // Swipe vers la droite -> livre précédent
                    EngineState.carouselIndex = (EngineState.carouselIndex - 1 + books.length) % books.length;
                    updateCarouselItems();
                }
            }
        }, { passive: true });

        // Support du glissement à la souris pour le dev / test
        let mouseStartX = 0;
        let isMouseDown = false;
        container.addEventListener("mousedown", function (e) {
            if (e.button !== 0) return;
            mouseStartX = e.clientX;
            isMouseDown = true;
        });
        window.addEventListener("mouseup", function (e) {
            if (!isMouseDown) return;
            isMouseDown = false;
            const deltaX = e.clientX - mouseStartX;
            if (Math.abs(deltaX) > 55) {
                if (deltaX < 0) {
                    EngineState.carouselIndex = (EngineState.carouselIndex + 1) % books.length;
                    updateCarouselItems();
                } else {
                    EngineState.carouselIndex = (EngineState.carouselIndex - 1 + books.length) % books.length;
                    updateCarouselItems();
                }
            }
        });
    }

    // ========================================================================
    // 8. SettingScene (2 onglets : PictoPhrase, Boutique)
    // ========================================================================
    function renderSettingScene(activeTab) {
        activeTab = activeTab || "orthopicto";
        clearAllTimers();

        const container = document.getElementById("scene-container");
        container.innerHTML = "";

        const bg = document.createElement("img");
        bg.className = "cc-bg";
        bg.src = "../common/background-option-menu-ui.svg";
        container.appendChild(bg);

        // Bouton retour vers l'accueil
        const backBtn = createDbMenuButton("back_arrow", function () {
            renderHomePageScene();
        });
        container.appendChild(backBtn);

        // Titre de l'onglet actif
        const titleKeyMap = {
            orthopicto: "settings_orthopicto_text",
            shop: "settings_achat_text"
        };
        const titleSprite = createDbMenuButton(titleKeyMap[activeTab] || "settings_orthopicto_text", null);
        container.appendChild(titleSprite);

        // Barre des 2 onglets (y = 22%, équidistants à 35% et 65%)
        const tabs = [
            { id: "orthopicto", dbKey: "settings_orthopicto_icon", x: 35 },
            { id: "shop", dbKey: "settings_shop_icon", x: 65 }
        ];

        tabs.forEach(function (tab) {
            const btn = createDbMenuButton(tab.dbKey, function () {
                renderSettingScene(tab.id);
            }, { selected: activeTab === tab.id, x: tab.x });
            container.appendChild(btn);
        });

        // Contenu spécifique à l'onglet sélectionné
        if (activeTab === "orthopicto") {
            renderOrthopictoSettingsTab(container);
        } else if (activeTab === "shop") {
            renderShopSettingsTab(container);
        }
    }

    function renderOrthopictoSettingsTab(container) {
        const panel = document.createElement("div");
        panel.className = "settings-content-panel";
        panel.innerHTML =
            "<h2>À propos de PictoPhrase</h2>" +
            "<p>Développée selon les principes de la pratique orthophonique, <strong>PictoPhrase</strong> s'impose comme une référence pour accompagner tous les enfants dans l'entrée dans le langage, y compris ceux présentant un retard de parole, une dysphasie ou un trouble du spectre de l'autisme (TSA).</p>" +
            "<p>En associant pictogrammes explicites, renforcement audio et manipulation tactile, chaque livre permet d'exercer la combinatoire de mots et la précision articulatoire de manière progressive et valorisante.</p>" +
            "<p>Une ressource clé pour les orthophonistes, les enseignants et les parents soucieux de donner à l'enfant le plaisir de communiquer.</p>";
        container.appendChild(panel);
    }

    function renderShopSettingsTab(container) {
        const panel = document.createElement("div");
        panel.className = "settings-content-panel";
        const grid = document.createElement("div");
        grid.className = "shop-grid";

        const items = (EngineState.buyInfo.items || []).filter(function (it) {
            return Number(it.skipSaleList) !== 1;
        });

        items.forEach(function (item) {
            const card = document.createElement("div");
            card.className = "shop-grid-card";

            const iconPng = BookSettings.isEnglish() ? item.icon_english : item.icon_french;
            const resolvedIcon = resolveAssetImage(iconPng, item.book_folder);
            const unlocked = NativeBridge.isProductPurchased(item.book_folder) ||
                NativeBridge.isProductPurchased(item.iap_name);

            const img = document.createElement("img");
            img.className = "shop-item-icon";
            img.src = resolvedIcon.src;
            card.appendChild(img);

            const badgePng = unlocked
                ? (EngineState.buyInfo.IconBought || "green-check-ui.png")
                : (EngineState.buyInfo.IconLocked || "lock-red-ui.png");
            const badgeResolved = resolveAssetImage(badgePng);
            const badge = document.createElement("img");
            badge.className = "shop-status-badge";
            badge.src = badgeResolved.src;
            card.appendChild(badge);

            const title = document.createElement("div");
            title.className = "shop-item-title";
            title.textContent = BookSettings.isEnglish() ? item.text_english : item.text_french;
            card.appendChild(title);

            card.addEventListener("click", function () {
                if (unlocked && item.book_folder && item.book_folder.indexOf("book-") === 0) {
                    startBook(item.book_folder);
                } else {
                    showOptionsBuyInfoModal(item);
                }
            });

            grid.appendChild(card);
        });

        panel.appendChild(grid);
        container.appendChild(panel);
    }

    function showOptionsBuyInfoModal(item) {
        const modal = document.getElementById("modal-container");
        modal.innerHTML = "";
        modal.classList.remove("hidden");

        const bg = createDbMenuButton("settings_buy_background", null);
        modal.appendChild(bg);

        const iconPng = BookSettings.isEnglish() ? item.icon_english : item.icon_french;
        const resolvedIcon = resolveAssetImage(iconPng, item.book_folder);
        const iconImg = document.createElement("img");
        iconImg.className = "cc-sprite";
        iconImg.src = resolvedIcon.src;
        iconImg.style.left = "50%";
        iconImg.style.top = "38%";
        iconImg.style.width = "18%";
        modal.appendChild(iconImg);

        const desc = document.createElement("div");
        desc.style.position = "absolute";
        desc.style.left = "50%";
        desc.style.top = "56%";
        desc.style.width = "50%";
        desc.style.transform = "translate(-50%, -50%)";
        desc.style.textAlign = "center";
        desc.style.fontWeight = "bold";
        desc.style.fontSize = "clamp(15px, 2vw, 22px)";
        desc.textContent = BookSettings.isEnglish() ? item.text_english : item.text_french;
        modal.appendChild(desc);

        const closeBtn = createDbMenuButton("red-x-ui", function () {
            modal.classList.add("hidden");
            modal.innerHTML = "";
        }, { x: 76, y: 27 });
        modal.appendChild(closeBtn);
    }

    // ========================================================================
    // 9. BuyTriScene (Écran 3 offres pour un livre verrouillé)
    // ========================================================================
    function renderBuyTriScene(item) {
        clearAllTimers();
        const container = document.getElementById("scene-container");
        container.innerHTML = "";

        const bg = document.createElement("img");
        bg.className = "cc-bg";
        bg.src = BookSettings.isEnglish()
            ? "../common/background-option-menu-ui_en.svg"
            : "../common/background-option-menu-ui.svg";
        container.appendChild(bg);

        container.appendChild(createDbMenuButton("back_arrow", function () {
            renderHomePageScene();
        }));

        container.appendChild(createDbMenuButton("tri_buy_top_text", null));
        container.appendChild(createDbMenuButton("tri_buy_panels", null));

        // Offre 1 : Le livre individuel
        const bundle2nd = (EngineState.buyInfo.items || []).find(function (b) {
            return b.iap_name === item["2ndTriItem"];
        }) || item;
        const bundleAll = (EngineState.buyInfo.items || []).find(function (b) {
            return b.iap_name === "com.symbolicone.ouille.all22";
        }) || item;

        const offers = [
            {
                icon: BookSettings.isEnglish() ? item.icon_english : item.icon_french,
                folder: item.book_folder,
                title: BookSettings.isEnglish() ? item.text_english : item.text_french,
                yIcon: EngineState.buyInfo.TriBuyIcon1Y || 28
            },
            {
                icon: BookSettings.isEnglish() ? bundle2nd.icon_english : bundle2nd.icon_french,
                folder: bundle2nd.book_folder,
                title: BookSettings.isEnglish() ? (item.text_english_2nd || bundle2nd.text_english) : (item.text_french_2nd || bundle2nd.text_french),
                yIcon: EngineState.buyInfo.TriBuyIcon2Y || 55
            },
            {
                icon: BookSettings.isEnglish() ? bundleAll.icon_english : bundleAll.icon_french,
                folder: bundleAll.book_folder,
                title: BookSettings.isEnglish() ? bundleAll.text_english : bundleAll.text_french,
                yIcon: EngineState.buyInfo.TriBuyIcon3Y || 82
            }
        ];

        offers.forEach(function (offer) {
            const resolved = resolveAssetImage(offer.icon, offer.folder);
            const icon = document.createElement("img");
            icon.className = "cc-sprite";
            icon.src = resolved.src;
            icon.style.left = (EngineState.buyInfo.TriBuyIconX || 25) + "%";
            icon.style.top = offer.yIcon + "%";
            icon.style.width = "16%";
            icon.style.height = "18%";
            container.appendChild(icon);

            const txt = document.createElement("div");
            txt.style.position = "absolute";
            txt.style.left = (EngineState.buyInfo.TriBuyDetailsLeftEdgeX || 40) + "%";
            txt.style.top = (offer.yIcon - 6) + "%";
            txt.style.width = "48%";
            txt.style.color = "#ffffff";
            txt.style.fontWeight = "bold";
            txt.style.fontSize = "clamp(14px, 2vw, 22px)";
            txt.style.whiteSpace = "pre-line";
            txt.textContent = offer.title;
            container.appendChild(txt);
        });
    }

    // ========================================================================
    // 10. Contrôleur Book : BookCoverScene, OptionsScene, PageScene, BookEndPageScene
    // ========================================================================
    function startBook(bookFolder) {
        BookSettings.activeBookFolder = bookFolder;
        loadBookConversion(bookFolder);
        EngineState.currentBookData = NativeBridge.readJson(bookFolder + "/book.json");
        EngineState.currentPageIndex = 0;
        renderCurrentBookPage();
    }

    function renderCurrentBookPage() {
        clearAllTimers();
        NativeBridge.stopAllAudio();

        const bookArr = EngineState.currentBookData;
        if (!bookArr || !bookArr.length) return;

        const totalInteractivePages = bookArr.length - 1;
        if (EngineState.currentPageIndex <= 0) {
            EngineState.currentPageIndex = 0;
            renderBookCoverScene(bookArr[0]);
        } else if (EngineState.currentPageIndex <= totalInteractivePages) {
            renderInteractivePageScene(bookArr[EngineState.currentPageIndex], EngineState.currentPageIndex, totalInteractivePages);
        } else {
            renderBookEndPageScene(bookArr[0]);
        }
    }

    function gotoNextBookPage() {
        EngineState.currentPageIndex++;
        renderCurrentBookPage();
    }

    function gotoPrevBookPage() {
        if (EngineState.currentPageIndex > 0) {
            EngineState.currentPageIndex--;
            renderCurrentBookPage();
        }
    }

    /**
     * BookCoverScene (Page 0 d'un livre) :
     * Affiche l'illustration de couverture ("background" / "background_en")
     * et les boutons : Accueil ("home_icon"), Infos Parents ("parent_info"),
     * Options ("cover_options") et Jouer ("cover_play").
     */
    function renderBookCoverScene(coverMeta) {
        const container = document.getElementById("scene-container");
        container.innerHTML = "";
        container.className = "page-turn-enter";

        const bgPng = (BookSettings.isEnglish() && coverMeta.background_en)
            ? coverMeta.background_en
            : coverMeta.background;
        const resolvedBg = resolveAssetImage(bgPng, BookSettings.activeBookFolder);

        const bgImg = document.createElement("img");
        bgImg.className = "cc-bg";
        bgImg.src = resolvedBg.src;
        container.appendChild(bgImg);

        // Bouton Accueil
        container.appendChild(createDbMenuButton("home_icon", function () {
            renderHomePageScene();
        }));

        // Bouton Infos Parents / Fiche pédagogique (Désactivé pour le moment, réactivable plus tard)
        const ENABLE_PEDAGOGICAL_SHEET = false;
        if (ENABLE_PEDAGOGICAL_SHEET) {
            container.appendChild(createDbMenuButton("parent_info", function () {
                showParentPedagogicalSheet(coverMeta);
            }));
        }

        // Bouton Options du livre
        container.appendChild(createDbMenuButton("cover_options", function () {
            renderOptionsScene();
        }));

        // Bouton Jouer (lance la page 1)
        container.appendChild(createDbMenuButton("cover_play", function () {
            EngineState.currentPageIndex = 1;
            renderCurrentBookPage();
        }));
    }

    function showParentPedagogicalSheet(coverMeta) {
        const modal = document.getElementById("modal-container");
        modal.innerHTML = "";
        modal.classList.remove("hidden");

        const card = document.createElement("div");
        card.className = "settings-content-panel";
        card.style.top = "50%";
        card.style.height = "78%";
        card.style.width = "84%";

        const bookItem = (EngineState.buyInfo.items || []).find(function (it) {
            return it.book_folder === BookSettings.activeBookFolder;
        });
        const title = bookItem
            ? (BookSettings.isEnglish() ? bookItem.text_english : bookItem.text_french)
            : BookSettings.activeBookFolder;

        if (BookSettings.isEnglish()) {
            card.innerHTML =
                "<h2>Parent & Speech Therapy Guide — " + escapeHtml(title) + "</h2>" +
                "<p><strong>Educational Goal:</strong> Help the child build 3-part sentences (Subject + Verb + Object) while practicing clear articulation of the target sound.</p>" +
                "<p><strong>How to use the 3 levels:</strong></p>" +
                "<p>• <strong>Level 1:</strong> Only 1 pictogram to place per page (ideal for discovering the vocabulary and sentence structure).</p>" +
                "<p>• <strong>Level 2:</strong> 2 pictograms to place per page.</p>" +
                "<p>• <strong>Level 3:</strong> All 3 pictograms (Subject, Verb, Object) must be placed in order.</p>" +
                "<p><em>" + escapeHtml(coverMeta.ISBN || "") + "</em></p>";
        } else {
            card.innerHTML =
                "<h2>Fiche pédagogique parents — " + escapeHtml(title) + "</h2>" +
                "<p><strong>Objectif orthophonique :</strong> Accompagner l'enfant dans la construction de phrases à 3 éléments (Sujet + Verbe + Complément) tout en stimulant la prononciation du son cible mis en évidence en rouge.</p>" +
                "<p><strong>Progression des 3 niveaux :</strong></p>" +
                "<p>• <strong>Niveau 1 :</strong> 1 seul pictogramme à placer par page (idéal pour découvrir le vocabulaire).</p>" +
                "<p>• <strong>Niveau 2 :</strong> 2 pictogrammes à placer par page.</p>" +
                "<p>• <strong>Niveau 3 :</strong> Les 3 pictogrammes de la phrase complète sont à assembler dans l'ordre.</p>" +
                "<p><em>" + escapeHtml(coverMeta.ISBN || "") + "</em></p>";
        }

        modal.appendChild(card);

        const closeBtn = createDbMenuButton("red-x-ui", function () {
            modal.classList.add("hidden");
            modal.innerHTML = "";
        }, { x: 90, y: 14 });
        modal.appendChild(closeBtn);
    }

    /**
     * OptionsScene (0x00394078 - 0x00395cb4) :
     * Permet de choisir :
     * - Mode Phrases (writtenMode = false) vs Langage écrit (writtenMode = true)
     * - Niveau 1, Niveau 2, Niveau 3
     * - Tourne-page Automatique, Manuel ou Code requis
     */
    function renderOptionsScene() {
        const container = document.getElementById("scene-container");
        container.innerHTML = "";

        const bg = document.createElement("img");
        bg.className = "cc-bg";
        bg.src = BookSettings.isEnglish()
            ? "../common/options_background_en.svg"
            : "../common/options_background.svg";
        container.appendChild(bg);

        // Retour à la couverture
        container.appendChild(createDbMenuButton("back_arrow", function () {
            renderCurrentBookPage();
        }));

        // Ligne 1 (y = 34%) : Phrases vs Langage écrit
        container.appendChild(createDbMenuButton("options_phrases", function () {
            BookSettings.writtenMode = false;
            renderOptionsScene();
        }, { selected: !BookSettings.writtenMode }));

        container.appendChild(createDbMenuButton("options_written", function () {
            BookSettings.writtenMode = true;
            renderOptionsScene();
        }, { selected: BookSettings.writtenMode }));

        // Ligne 2 (y = 51%) : Niveau 1 / Niveau 2 / Niveau 3
        container.appendChild(createDbMenuButton("options_level1", function () {
            BookSettings.difficulty = PageDifficulty.LEVEL_1_EASY;
            renderOptionsScene();
        }, { selected: BookSettings.difficulty === PageDifficulty.LEVEL_1_EASY }));

        container.appendChild(createDbMenuButton("options_level2", function () {
            BookSettings.difficulty = PageDifficulty.LEVEL_2_MEDIUM;
            renderOptionsScene();
        }, { selected: BookSettings.difficulty === PageDifficulty.LEVEL_2_MEDIUM }));

        container.appendChild(createDbMenuButton("options_level3", function () {
            BookSettings.difficulty = PageDifficulty.LEVEL_3_HARD;
            renderOptionsScene();
        }, { selected: BookSettings.difficulty === PageDifficulty.LEVEL_3_HARD }));

        // Ligne 3 (y = 68%) : Automatique / Manuel / Code requis
        container.appendChild(createDbMenuButton("options_automatic", function () {
            BookSettings.pageTurnMode = ePageTurnMode.AUTOMATIC;
            renderOptionsScene();
        }, { selected: BookSettings.pageTurnMode === ePageTurnMode.AUTOMATIC }));

        container.appendChild(createDbMenuButton("options_manually", function () {
            BookSettings.pageTurnMode = ePageTurnMode.MANUALLY;
            renderOptionsScene();
        }, { selected: BookSettings.pageTurnMode === ePageTurnMode.MANUALLY }));

        container.appendChild(createDbMenuButton("options_code", function () {
            BookSettings.pageTurnMode = ePageTurnMode.CODE_REQUIRED;
            renderOptionsScene();
        }, { selected: BookSettings.pageTurnMode === ePageTurnMode.CODE_REQUIRED }));

        // Bouton Play vert en bas (y = 84%)
        container.appendChild(createDbMenuButton("options_play", function () {
            EngineState.currentPageIndex = 1;
            renderCurrentBookPage();
        }));
    }

    // ========================================================================
    // 11. PageScene & PageLayer (Gameplay Éducatif Glisser-Déposer)
    // ========================================================================
    function resolvePageAudioPath(audioKey, bookFolder) {
        if (!audioKey) return "";
        let cleaned = audioKey.replace(/\\/g, "/");
        if (!/\.mp3$/i.test(cleaned)) {
            cleaned += ".mp3";
        }
        if (cleaned.indexOf("/") !== -1) {
            return cleaned;
        }
        return bookFolder + "/" + cleaned;
    }

    function renderInteractivePageScene(pageData, pageNum, totalPages) {
        const container = document.getElementById("scene-container");
        container.innerHTML = "";
        container.className = "page-turn-enter";

        const bookFolder = BookSettings.activeBookFolder;
        const langKey = BookSettings.getBookLangKey();
        const langBlock = pageData[langKey] || pageData.fr || pageData.en;

        // 1. Décor de fond de la page ("background")
        if (pageData.background) {
            const resolvedBg = resolveAssetImage(pageData.background, bookFolder);
            const bgImg = document.createElement("img");
            bgImg.className = "cc-bg";
            bgImg.src = resolvedBg.src;
            container.appendChild(bgImg);
        }

        // 2. Illustration centrale du personnage ("main", "mainX", "mainY", "mainScale")
        let mainCharacterSprite = null;
        if (pageData.main) {
            const resolvedMain = resolveAssetImage(pageData.main, bookFolder);
            const scale = (pageData.mainScale || 100) / 100;
            mainCharacterSprite = document.createElement("img");
            mainCharacterSprite.className = "cc-sprite";
            mainCharacterSprite.src = resolvedMain.src;
            mainCharacterSprite.style.left = (pageData.mainX !== undefined ? pageData.mainX : 50) + "%";
            mainCharacterSprite.style.top = (pageData.mainY !== undefined ? pageData.mainY : 52) + "%";
            mainCharacterSprite.style.width = (resolvedMain.width * scale) + "%";
            mainCharacterSprite.style.height = (resolvedMain.height * scale) + "%";
            container.appendChild(mainCharacterSprite);
        }

        // 3. Bouton retour (page précédente / couverture) en haut à gauche
        // Positionné au-dessus des pictogrammes (z-index élevé) avec coordonnées distinctes
        const homeBtn = createDbMenuButton("back_arrow", function () {
            gotoPrevBookPage();
        }, { x: 5.5, y: 5.5, width: 8.5, height: 7.5 });
        homeBtn.classList.add("page-back-button");
        container.appendChild(homeBtn);

        // 4. Calcul des colonnes A, B, C
        // AX commence à 21% pour laisser un dégagement clair au bouton retour en haut à gauche
        const colX = [
            pageData.aX !== undefined ? Math.max(pageData.aX, 21.0) : 21.0,
            pageData.bX !== undefined ? pageData.bX : 51.0,
            pageData.cX !== undefined ? pageData.cX : 81.0
        ];
        const topSlotY = [
            pageData.aY !== undefined ? Math.max(pageData.aY, 16.0) : 16.0,
            pageData.bY !== undefined ? Math.max(pageData.bY, 16.0) : 16.0,
            pageData.cY !== undefined ? Math.max(pageData.cY, 16.0) : 16.0
        ];
        const topTextY = 28.5;
        const bottomRowY = 82.0;

        const pictoFiles = [pageData.a, pageData.b, pageData.c];
        const topTexts = [langBlock.textA, langBlock.textB, langBlock.textC];
        const dragTexts = [
            langBlock.textDragA || langBlock.textA,
            langBlock.textDragB || langBlock.textB,
            langBlock.textDragC || langBlock.textC
        ];
        const audioFiles = [
            resolvePageAudioPath(langBlock.audioA, bookFolder),
            resolvePageAudioPath(langBlock.audioB, bookFolder),
            resolvePageAudioPath(langBlock.audioC, bookFolder)
        ];
        const delays = [
            Number(langBlock.delayA || 1.8),
            Number(langBlock.delayB || 1.8),
            Number(langBlock.delayC || 1.8)
        ];

        // Masque de difficulté (A=1, B=2, C=4)
        const orderMask = getOrderForPage(pageNum, BookSettings.difficulty);
        const placed = [
            (orderMask & 1) === 0,
            (orderMask & 2) === 0,
            (orderMask & 4) === 0
        ];

        const slotElements = [];
        const topLabelElements = [];
        const frameResolved = resolveAssetImage("frame.png", bookFolder);
        const cardWidthPct = frameResolved.width || 22;
        const cardHeightPct = frameResolved.height || 19;

        // Création des 3 zones cibles (HotZones A, B, C) et des textes de phrase en haut
        for (let i = 0; i < 3; i++) {
            const slot = document.createElement("div");
            slot.className = "hotzone-slot" + (placed[i] ? " placed" : "");
            slot.style.left = colX[i] + "%";
            slot.style.top = topSlotY[i] + "%";
            slot.style.width = cardWidthPct + "%";
            slot.style.height = cardHeightPct + "%";
            container.appendChild(slot);
            slotElements.push(slot);

            const topLabel = document.createElement("div");
            topLabel.className = "top-sentence-label";
            topLabel.style.left = colX[i] + "%";
            topLabel.style.top = topTextY + "%";
            topLabel.innerHTML = formatColourLabel(topTexts[i]);

            // Visibilité initiale du texte du haut selon le mode et si la carte est déjà placée
            if (!placed[i] && BookSettings.writtenMode) {
                topLabel.style.opacity = "0.25";
            }

            (function (idx) {
                topLabel.addEventListener("click", function () {
                    NativeBridge.playAudio(audioFiles[idx]);
                    zoomTextElement(topLabelElements[idx], 900);
                });
            })(i);

            container.appendChild(topLabel);
            topLabelElements.push(topLabel);
        }

        // Création des 3 cartes pictogrammes (pré-placées en haut ou déplaçables depuis le bas)
        let sequenceCompleted = false;

        for (let i = 0; i < 3; i++) {
            const isPrePlaced = placed[i];
            const startX = colX[i];
            const startY = isPrePlaced ? topSlotY[i] : bottomRowY;

            const card = document.createElement("div");
            card.className = "drag-card" + (isPrePlaced ? " snapped" : "");
            card.style.left = startX + "%";
            card.style.top = startY + "%";
            card.style.width = cardWidthPct + "%";
            card.style.height = cardHeightPct + "%";

            const frameImg = document.createElement("img");
            frameImg.className = "frame-bg";
            frameImg.src = frameResolved.src;
            card.appendChild(frameImg);

            const resolvedPicto = resolveAssetImage(pictoFiles[i], bookFolder);
            const pictoImg = document.createElement("img");
            pictoImg.className = "picto-icon";
            pictoImg.src = resolvedPicto.src;
            if (BookSettings.writtenMode && !isPrePlaced) {
                // En mode "Langage écrit", l'enfant associe le mot écrit
                pictoImg.style.opacity = "0.18";
            }
            card.appendChild(pictoImg);

            if (!isPrePlaced) {
                const caption = document.createElement("div");
                caption.className = "drag-card-caption";
                caption.innerHTML = formatColourLabel(dragTexts[i]);
                card.appendChild(caption);
            }

            container.appendChild(card);

            if (isPrePlaced) {
                (function (idx) {
                    card.addEventListener("click", function () {
                        NativeBridge.playAudio(audioFiles[idx]);
                        zoomTextElement(topLabelElements[idx], 900);
                    });
                })(i);
            } else {
                bindDraggableCard(card, i, startX, startY);
            }
        }

        function bindDraggableCard(card, objIndex, homeXPct, homeYPct) {
            let isDragging = false;

            card.addEventListener("pointerdown", function (e) {
                if (placed[objIndex] || sequenceCompleted) return;
                e.preventDefault();
                isDragging = true;
                card.setPointerCapture(e.pointerId);
                card.classList.remove("returning");
                card.classList.add("dragging");
            });

            card.addEventListener("pointermove", function (e) {
                if (!isDragging) return;
                const rect = container.getBoundingClientRect();
                const xPct = ((e.clientX - rect.left) / rect.width) * 100;
                const yPct = ((e.clientY - rect.top) / rect.height) * 100;

                card.style.left = xPct + "%";
                card.style.top = yPct + "%";

                const hoveredZone = detectHotZone(xPct, yPct);
                for (let z = 0; z < 3; z++) {
                    slotElements[z].classList.toggle("highlight", z === hoveredZone && !placed[z]);
                }
            });

            card.addEventListener("pointerup", function (e) {
                if (!isDragging) return;
                isDragging = false;
                card.classList.remove("dragging");
                for (let z = 0; z < 3; z++) {
                    slotElements[z].classList.remove("highlight");
                }

                const rect = container.getBoundingClientRect();
                const dropXPct = ((e.clientX - rect.left) / rect.width) * 100;
                const dropYPct = ((e.clientY - rect.top) / rect.height) * 100;
                const targetZone = detectHotZone(dropXPct, dropYPct);

                handleObjectInHotZone(card, objIndex, targetZone, homeXPct, homeYPct);
            });
        }

        function detectHotZone(xPct, yPct) {
            for (let z = 0; z < 3; z++) {
                const dx = Math.abs(xPct - colX[z]);
                const dy = Math.abs(yPct - topSlotY[z]);
                if (dx <= cardWidthPct * 0.65 && dy <= cardHeightPct * 0.85) {
                    return z;
                }
            }
            return -1;
        }

        /**
         * Reproduit PageLayer::handleObjectInHotZone (0x0038c67c) :
         * - Si objIndex == hotZoneIndex : valide le dépôt, joue l'audio du mot et zoome le texte.
         * - Sinon : renvoie la carte à sa position de départ (doFailInHotZone).
         */
        function getWordDurationMs(wordIndex) {
            const file = audioFiles[wordIndex];
            const nativeDur = NativeBridge.getAudioDuration(file, bookFolder);
            if (nativeDur && nativeDur > 300) {
                // Durée réelle de l'audio mesurée + 300ms de respiration naturelle
                return nativeDur + 300;
            }
            // Fallback sécurisé : au moins 2600ms pour éviter tout chevauchement
            const jsonDelay = Number(delays[wordIndex] || 1.8) * 1000;
            return Math.max(jsonDelay, 2600);
        }

        function handleObjectInHotZone(card, objIndex, hotZoneIndex, homeXPct, homeYPct) {
            if (objIndex !== hotZoneIndex) {
                card.classList.add("returning");
                card.style.left = homeXPct + "%";
                card.style.top = homeYPct + "%";
                return;
            }

            // Dépôt réussi !
            placed[objIndex] = true;
            slotElements[objIndex].classList.add("placed");
            card.classList.add("snapped");
            card.style.left = colX[objIndex] + "%";
            card.style.top = topSlotY[objIndex] + "%";

            const caption = card.querySelector(".drag-card-caption");
            if (caption) caption.remove();

            const pictoImg = card.querySelector(".picto-icon");
            if (pictoImg) pictoImg.style.opacity = "1";

            topLabelElements[objIndex].style.opacity = "1";

            // Joue l'audio du mot déposé et zoome l'étiquette
            NativeBridge.playAudio(audioFiles[objIndex]);
            const wordDur = getWordDurationMs(objIndex);
            zoomTextElement(topLabelElements[objIndex], wordDur);

            card.addEventListener("click", function () {
                NativeBridge.playAudio(audioFiles[objIndex]);
                zoomTextElement(topLabelElements[objIndex], 900);
            });

            // Si les 3 éléments A, B, C sont placés, lance doCompleteSentence()
            if (placed[0] && placed[1] && placed[2] && !sequenceCompleted) {
                sequenceCompleted = true;
                // Attend impérativement la FIN de la prononciation du mot qui vient d'être déposé (+ 400ms de pause)
                const initialWordWait = wordDur + 400;
                scheduleTimer(function () {
                    doCompleteSentence();
                }, initialWordWait);
            }
        }

        /**
         * Reproduit PageLayer::doCompleteSentence (0x0038d770) :
         * Relit toute la phrase dans l'ordre (Audio A -> Audio B -> Audio C -> shiny-ding -> Animation).
         */
        function doCompleteSentence() {
            // Coupe tout son antérieur pour éviter tout résidu sonore
            NativeBridge.stopAllAudio();

            const durA = getWordDurationMs(0);
            const durB = getWordDurationMs(1);
            const durC = getWordDurationMs(2);

            let t = 0;

            // Mot A (Sujet)
            scheduleTimer(function () {
                NativeBridge.playAudio(audioFiles[0]);
                zoomTextElement(topLabelElements[0], durA - 100);
            }, t);
            t += durA;

            // Mot B (Verbe)
            scheduleTimer(function () {
                NativeBridge.playAudio(audioFiles[1]);
                zoomTextElement(topLabelElements[1], durB - 100);
            }, t);
            t += durB;

            // Mot C (Complément)
            scheduleTimer(function () {
                NativeBridge.playAudio(audioFiles[2]);
                zoomTextElement(topLabelElements[2], durC - 100);
            }, t);
            t += durC;

            // Son "shiny-ding.mp3"
            const dingDelay = (EngineState.globalParams.shinyDingDelay || 0.2) * 1000;
            scheduleTimer(function () {
                NativeBridge.playAudio("book-hurt/shiny-ding.mp3");
            }, t + dingDelay);

            const animDelay = Number(langBlock.animationDelay || 1.0) * 1000;
            t += dingDelay + animDelay;

            // Animation de récompense + bruitage animationAudio
            scheduleTimer(function () {
                playPageRewardAnimation();
            }, t);
        }

        /**
         * Reproduit PageLayer::playAnimation (0x0038e07c) :
         * Joue l'animation du personnage et le bruitage `animationAudio`, puis tourne la page
         * selon `BookSettings.pageTurnMode` (AUTOMATIC, MANUALLY ou CODE_REQUIRED).
         */
        function playPageRewardAnimation() {
            if (langBlock.animationAudio) {
                const animAudioPath = resolvePageAudioPath(langBlock.animationAudio, bookFolder);
                NativeBridge.playAudio(animAudioPath);
            }

            if (mainCharacterSprite) {
                mainCharacterSprite.classList.add("reward-animating");
            }

            const autoDelay = (EngineState.globalParams.autoChangePageDelay || 1.0) * 1000;
            const totalAnimDuration = 1800 + autoDelay;

            if (BookSettings.pageTurnMode === ePageTurnMode.AUTOMATIC) {
                scheduleTimer(function () {
                    gotoNextBookPage();
                }, totalAnimDuration);
            } else if (BookSettings.pageTurnMode === ePageTurnMode.MANUALLY) {
                showNextPageButton(false);
            } else {
                showNextPageButton(true);
            }
        }

        function showNextPageButton() {
            const nextArrow = createDbMenuButton("home_page_right_arrow", function () {
                gotoNextBookPage();
            }, { x: 93, y: 52, width: 9, height: 9 });
            container.appendChild(nextArrow);
        }
    }

    function zoomTextElement(el, durationMs) {
        if (!el) return;
        el.classList.add("zoom-active");
        scheduleTimer(function () {
            el.classList.remove("zoom-active");
        }, durationMs || 900);
    }

    // ========================================================================
    // 12. BookEndPageScene (Dernière page d'un livre)
    // ========================================================================
    function renderBookEndPageScene(coverMeta) {
        const container = document.getElementById("scene-container");
        container.innerHTML = "";
        container.className = "page-turn-enter";

        const lastPagePng = (BookSettings.isEnglish() && coverMeta.lastPage_en)
            ? coverMeta.lastPage_en
            : coverMeta.lastPage;
        const resolvedBg = resolveAssetImage(lastPagePng, BookSettings.activeBookFolder);

        const bgImg = document.createElement("img");
        bgImg.className = "cc-bg";
        bgImg.src = resolvedBg.src;
        container.appendChild(bgImg);

        // Bouton Accueil
        container.appendChild(createDbMenuButton("home_icon", function () {
            renderHomePageScene();
        }));

        // Bouton Recommencer le livre ("book_restart" -> page 1)
        container.appendChild(createDbMenuButton("book_restart", function () {
            EngineState.currentPageIndex = 1;
            renderCurrentBookPage();
        }));

        // Note: La photo publicitaire du coffret papier imprimé (bookset) et son lien
        // vers la boutique externe ont été retirés pour cette version de l'application.
    }

    // ========================================================================
    // 13. Démarrage du Moteur (AppDelegate::applicationDidFinishLaunching)
    // ========================================================================
    window.PicoEngine = {
        onBackPressed: function () {
            const pinOverlay = document.getElementById("pin-overlay-container");
            if (pinOverlay && !pinOverlay.classList.contains("hidden")) {
                pinOverlay.classList.add("hidden");
                pinOverlay.innerHTML = "";
                return true;
            }
            const modalOverlay = document.getElementById("modal-container");
            if (modalOverlay && !modalOverlay.classList.contains("hidden")) {
                modalOverlay.classList.add("hidden");
                modalOverlay.innerHTML = "";
                return true;
            }
            if (EngineState.currentBookData && EngineState.currentPageIndex > 0) {
                gotoPrevBookPage();
                return true;
            }
            return false;
        }
    };

    function bootPicoPhraseEngine() {
        BookSettings.language = NativeBridge.getLanguage();
        loadCoreDatabases();
        renderHomePageScene();
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", bootPicoPhraseEngine);
    } else {
        bootPicoPhraseEngine();
    }
})();

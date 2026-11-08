#include "BookSettings.h"
#include "cocos2d.h"
#include "ui/CocosGUI.h"
#include "ui/UIWebView.h"
#include "audio/include/AudioEngine.h"
#include <spine/spine-cocos2dx.h>

/**
 * ============================================================================
 * BookAndPages.cpp
 * Reconstruction de toutes les classes gérant un livre interactif et ses pages :
 * - Book (0x0036db24 - 0x0036e7f8)
 * - BookCoverScene (0x00378f20 - 0x00379f24)
 * - TitlePageScene (0x00396614 - 0x00397204)
 * - PageScene (0x0038fc90 - 0x003902fc)
 * - PageLayer (0x003891f0 - 0x0038e608)
 * - BookEndPageScene (0x0037aaec - 0x0037b9f8)
 * ============================================================================
 */

// Déclarations anticipées
class HomePageScene {
public:
    static cocos2d::Scene* createScene(BookSettings* settings);
};
class OptionsScene {
public:
    static cocos2d::Scene* createScene(BookSettings* settings);
};
class ImageDatabase {
public:
    static ImageDatabase* getImageDatabase();
    cocos2d::MenuItemImage* makeMenuItemImageForID(const std::string& id, const BookSettings* settings);
};
class SingleImageLayer : public cocos2d::Layer {
public:
    static SingleImageLayer* createLayer(const std::string& filename);
};
class PinLayer : public cocos2d::Layer {
public:
    static PinLayer* createLayer(const BookSettings* settings);
    void reset();
};

/**
 * Classe BookCoverScene (0x00378f20 - 0x00379f24)
 * Page 0 d'un livre : affiche l'illustration de couverture ("background" / "background_en")
 * et les 4 boutons : Accueil ("home_icon"), Infos Parents ("parent_info"),
 * Options du livre ("cover_options") et Jouer ("cover_play").
 */
class BookCoverScene : public cocos2d::Scene {
private:
    BookSettings* m_settings = nullptr;
    nlohmann::json m_coverJson;

public:
    // Adresse : 0x00378f20
    static cocos2d::Scene* createScene(nlohmann::json& coverJson, BookSettings* settings) {
        auto scene = new (std::nothrow) BookCoverScene();
        if (scene && scene->init(coverJson, settings)) {
            scene->autorelease();
            return scene;
        }
        delete scene;
        return nullptr;
    }

    // Adresse : 0x00378fe8 (taille : 1744 octets)
    bool init(nlohmann::json& coverJson, BookSettings* settings) {
        if (!cocos2d::Scene::init()) return false;
        m_coverJson = coverJson;
        m_settings = settings;

        // Image de couverture selon la langue ("background" ou "background_en")
        std::string bgKey = m_settings->isEnglish() ? "background_en" : "background";
        if (m_coverJson.find(bgKey) != m_coverJson.end()) {
            this->addChild(SingleImageLayer::createLayer(m_coverJson[bgKey].get<std::string>()));
        }

        auto imgDb = ImageDatabase::getImageDatabase();

        // Boutons de menu sur la couverture
        auto homeBtn    = imgDb->makeMenuItemImageForID("home_icon", m_settings);
        auto parentBtn  = imgDb->makeMenuItemImageForID("parent_info", m_settings);
        auto optionsBtn = imgDb->makeMenuItemImageForID("cover_options", m_settings);
        auto playBtn    = imgDb->makeMenuItemImageForID("cover_play", m_settings);

        homeBtn->setCallback(CC_CALLBACK_1(BookCoverScene::doHome, this));
        parentBtn->setCallback(CC_CALLBACK_1(BookCoverScene::doParent, this));
        optionsBtn->setCallback(CC_CALLBACK_1(BookCoverScene::doOptions, this));
        playBtn->setCallback(CC_CALLBACK_1(BookCoverScene::doPlay, this));

        auto menu = cocos2d::Menu::create(homeBtn, parentBtn, optionsBtn, playBtn, nullptr);
        this->addChild(menu);
        return true;
    }

    // Adresse : 0x003799f4 — Retour à l'accueil
    void doHome(cocos2d::Ref* sender) {
        auto scene = HomePageScene::createScene(m_settings);
        cocos2d::Director::getInstance()->replaceScene(scene);
    }

    // Adresse : 0x00379a20 — Commence la lecture (envoie l'événement "next_page" à Book)
    void doPlay(cocos2d::Ref* sender) {
        cocos2d::EventCustom event("next_page");
        cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&event);
    }

    // Adresse : 0x00379b34 — Ouvre l'écran des options de lecture (OptionsScene)
    void doOptions(cocos2d::Ref* sender) {
        auto scene = OptionsScene::createScene(m_settings);
        cocos2d::Director::getInstance()->pushScene(scene);
    }

    // Adresse : 0x00379b60 — Ouvre une WebView avec la fiche pédagogique parents ("html")
    void doParent(cocos2d::Ref* sender) {
        auto webView = cocos2d::experimental::ui::WebView::create();
        auto visibleSize = cocos2d::Director::getInstance()->getVisibleSize();
        webView->setContentSize(visibleSize);
        webView->loadFile(m_coverJson["html"].get<std::string>());
        this->addChild(webView);
    }
};

/**
 * Classe TitlePageScene (0x00396614 - 0x00397204)
 * Scène de sélection rapide (utilisée en mode test/débogage) permettant de choisir
 * directement la difficulté (Easy / Medium / Hard), la langue (English / French)
 * et le mode d'affichage (Icon / Text).
 */
class TitlePageScene : public cocos2d::Scene {
private:
    BookSettings* m_settings = nullptr;

public:
    // Adresse : 0x00396614
    static cocos2d::Scene* createScene(BookSettings* settings) {
        auto scene = new (std::nothrow) TitlePageScene();
        if (scene && scene->init(settings)) {
            scene->autorelease();
            return scene;
        }
        delete scene;
        return nullptr;
    }

    // Adresse : 0x003966c0 (taille : 1888 octets)
    bool init(BookSettings* settings) {
        if (!cocos2d::Scene::init()) return false;
        m_settings = settings;
        // Crée des MenuItemFont pour tester rapidement les niveaux et langues
        return true;
    }

    // Adresses : 0x00396e20, 0x00396f3c, 0x0039705c
    void choseEasy(cocos2d::Ref*)   { m_settings->difficulty = PageDifficuly::LEVEL_1_EASY;   cocos2d::EventCustom e("next_page"); cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&e); }
    void choseMedium(cocos2d::Ref*) { m_settings->difficulty = PageDifficuly::LEVEL_2_MEDIUM; cocos2d::EventCustom e("next_page"); cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&e); }
    void choseHard(cocos2d::Ref*)   { m_settings->difficulty = PageDifficuly::LEVEL_3_HARD;   cocos2d::EventCustom e("next_page"); cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&e); }

    // Adresses : 0x0039717c, 0x00397188, 0x00397198, 0x003971a4
    void choseEnglish(cocos2d::Ref*) { m_settings->language = eLanguage::ENGLISH; }
    void chooseFrench(cocos2d::Ref*) { m_settings->language = eLanguage::FRENCH; }
    void choseIcon(cocos2d::Ref*)    { m_settings->writtenMode = false; }
    void choseText(cocos2d::Ref*)    { m_settings->writtenMode = true; }
};

/**
 * Classe PageLayer (0x003891f0 - 0x0038e608)
 * Cœur du gameplay éducatif d'une page d'un livre :
 * - Affiche le décor, le personnage ou l'animation squelettique Spine (.sbin/.json + .atlas).
 * - Crée les 3 zones cibles ("HotZones" A, B, C) en haut et les 3 pictogrammes déplaçables en bas.
 * - Gère le glisser-déposer (drag & drop) :
 *   - Si l'enfant dépose le bon pictogramme dans la bonne zone : joue l'audio du mot (`playSFXAudioA/B/C`)
 *     et zoome sur le mot (`makeTextZoomFunction`).
 *   - Si l'enfant se trompe de zone : ramène le pictogramme à sa place (`doFailInHotZone`).
 *   - Lorsque les 3 éléments de la phrase sont complétés (`doComplete` -> `doCompleteSentence`) :
 *     relit toute la phrase dans l'ordre (A -> B -> C + exclamation), joue le son `"shiny-ding1.mp3"`,
 *     puis déclenche l'animation Spine finale (`playAnimation`) et passe à la page suivante.
 */
class PageLayer : public cocos2d::Layer {
private:
    const BookSettings* m_settings = nullptr;
    int m_pageIndex = 0;

    // Éléments visuels et zones de dépôt (A = Sujet, B = Verbe, C = Complément)
    cocos2d::Rect   m_hotZones[3];
    cocos2d::Node*  m_dragObjects[3] = { nullptr, nullptr, nullptr };
    cocos2d::Node*  m_topTexts[3]    = { nullptr, nullptr, nullptr };
    bool            m_placed[3]      = { false, false, false };

    // Fichiers audio et délais lus depuis book.json (selon la langue "fr", "en" ou "qu")
    std::string m_audioA, m_audioB, m_audioC;
    std::string m_afterAnimAudioExclamation;
    std::string m_animationAudio;
    float m_delayA = 0.0f, m_delayB = 0.0f, m_delayC = 0.0f;
    float m_delayAfterAudioExclamation = 0.0f;
    float m_animationDelay = 0.0f;

    // Animation Spine de la page
    spine::SkeletonAnimation* m_spineNode = nullptr;
    std::string m_spineAnimName;
    PinLayer* m_pinLayer = nullptr;

public:
    // Adresses : 0x003891f0 / 0x003891f4
    static PageLayer* createLayer(nlohmann::json& pageJson, int pageNum, const BookSettings* settings) {
        return create(pageJson, pageNum, settings);
    }

    static PageLayer* create(nlohmann::json& pageJson, int pageNum, const BookSettings* settings) {
        auto layer = new (std::nothrow) PageLayer();
        if (layer && layer->init(pageJson, pageNum, settings)) {
            layer->autorelease();
            return layer;
        }
        delete layer;
        return nullptr;
    }

    /**
     * Adresse : 0x00389540 (taille : 5136 octets)
     * Initialise les graphismes, l'animation Spine et les pictogrammes de la page.
     */
    bool init(nlohmann::json& pageJson, int pageNum, const BookSettings* settings) {
        if (!cocos2d::Layer::init()) return false;
        m_pageIndex = pageNum;
        m_settings = settings;

        // 1. Détermine quels pictogrammes (A, B, C) doivent être joués selon la difficulté (Niveau 1, 2 ou 3)
        int orderMask = PageLogicManager::getOrderForPage(pageNum, m_settings->difficulty);

        // 2. Décor d'arrière-plan ("background")
        if (pageJson.find("background") != pageJson.end()) {
            this->addChild(cocos2d::Sprite::create(pageJson["background"].get<std::string>()));
        }

        // 3. Charge l'animation Spine (".sbin" binaire ou ".json" + ".atlas")
        if (pageJson.find("spine") != pageJson.end()) {
            std::string spineBase = pageJson["spine"].get<std::string>();
            float spineScale = pageJson.value("spine_scale", 1.0f);
            m_spineAnimName = pageJson.value("spine_anim", "");

            std::string atlasPath = spineBase + ".atlas";
            std::string sbinPath  = spineBase + ".sbin";
            if (cocos2d::FileUtils::getInstance()->isFileExist(sbinPath)) {
                m_spineNode = spine::SkeletonAnimation::createWithBinaryFile(sbinPath, atlasPath, spineScale);
            } else {
                m_spineNode = spine::SkeletonAnimation::createWithJsonFile(spineBase + ".json", atlasPath, spineScale);
            }
            m_spineNode->setSkin("default");
            this->addChild(m_spineNode);
        }

        // 4. Charge les textes et sons propres à la langue active ("fr", "en", ou "qu")
        std::string langKey = "fr";
        if (m_settings->language == eLanguage::ENGLISH) langKey = "en";
        else if (m_settings->language == eLanguage::QUEBEC) langKey = "qu";

        initWithLang(pageJson[langKey], m_settings);

        // 5. Positionne les éléments via PositionManager
        doLayout();
        return true;
    }

    /**
     * Adresse : 0x0038a950 (taille : 3636 octets)
     * Lit les textes ("textA", "textB", "textC"), les icônes ("frame.png") et les pistes audio
     * ("audioA", "audioB", "audioC", "afterAnimAudioExclamation", "animationAudio").
     */
    void initWithLang(nlohmann::json& langJson, const BookSettings* settings) {
        m_audioA = langJson.value("audioA", "") + ".mp3";
        m_audioB = langJson.value("audioB", "") + ".mp3";
        m_audioC = langJson.value("audioC", "") + ".mp3";
        m_afterAnimAudioExclamation = langJson.value("afterAnimAudioExclamation", "") + ".mp3";
        m_animationAudio = langJson.value("animationAudio", "") + ".mp3";

        m_delayA = langJson.value("delayA", 1.0f);
        m_delayB = langJson.value("delayB", 1.0f);
        m_delayC = langJson.value("delayC", 1.0f);
        m_delayAfterAudioExclamation = langJson.value("delayAfterAudioExclamation", 1.0f);
        m_animationDelay = langJson.value("animationDelay", 0.5f);
    }

    // Adresse : 0x0038b920 — Calcule la disposition des colonnes A, B, C via PositionManager
    void doLayout() {
        auto posMgr = PositionManager::getInstance();
        float ax = posMgr->getAX();
        float bx = posMgr->getBX();
        float cx = posMgr->getCX();
        float bottomY = posMgr->getBottomRowY();
        float bottomTextY = posMgr->getBottomTextRowY();
        // Positionne les cadres et étiquettes aux abscisses ax, bx, cx
    }

    // Adresse : 0x0038c310 — Vérifie dans quelle zone cible (0=A, 1=B, 2=C, -1=aucune) se trouve l'objet glissé
    int getHotZoneObjectIsIn(const cocos2d::Node* node) {
        cocos2d::Vec2 pos = node->getPosition();
        for (int i = 0; i < 3; ++i) {
            if (m_hotZones[i].containsPoint(pos)) return i;
        }
        return -1;
    }

    // Adresse : 0x0038c67c — Gère le dépôt d'un pictogramme `objIndex` dans la zone `hotZoneIndex`
    void handleObjectInHotZone(int objIndex, int hotZoneIndex) {
        if (objIndex != hotZoneIndex) {
            // Mauvaise zone : renvoie le pictogramme à sa position de départ
            doFailInHotZone(objIndex, hotZoneIndex);
            return;
        }

        // Bonne zone : valide l'emplacement, joue le son du mot et zoome le texte
        m_placed[objIndex] = true;
        if (objIndex == 0) playSFXAudioA();
        else if (objIndex == 1) playSFXAudioB();
        else if (objIndex == 2) playSFXAudioC();

        // Si les 3 éléments (A, B, C) sont en place, lance la lecture de la phrase complète
        if (m_placed[0] && m_placed[1] && m_placed[2]) {
            doComplete();
        }
    }

    // Adresse : 0x0038cc88 — Animation de retour (`MoveTo`) quand l'enfant dépose au mauvais endroit
    void doFailInHotZone(int objIndex, int hotZoneIndex) {
        // Ramène m_dragObjects[objIndex] vers sa position initiale sur la ligne du bas
    }

    // Adresses : 0x0038d180, 0x0038d2a0, 0x0038d3c0, 0x0038d5a8, 0x0038d650
    void playSFXAudioA()    { cocos2d::experimental::AudioEngine::play2d(m_audioA, false, 1.0f, nullptr); }
    void playSFXAudioB()    { cocos2d::experimental::AudioEngine::play2d(m_audioB, false, 1.0f, nullptr); }
    void playSFXAudioC()    { cocos2d::experimental::AudioEngine::play2d(m_audioC, false, 1.0f, nullptr); }
    void playSFXDing()      { cocos2d::experimental::AudioEngine::play2d("shiny-ding1.mp3", false, 1.0f, nullptr); }
    void playAfterAudio()   { cocos2d::experimental::AudioEngine::play2d(m_afterAnimAudioExclamation, false, 1.0f, nullptr); }

    // Adresse : 0x0038d4e0
    void doComplete() {
        doCompleteSentence();
    }

    /**
     * Adresse : 0x0038d770 (taille : 2204 octets)
     * Relit toute la phrase dans l'ordre (Audio A -> Audio B -> Audio C -> Ding -> Animation Spine).
     */
    void doCompleteSentence() {
        auto seq = cocos2d::Sequence::create(
            cocos2d::CallFunc::create([this]() { playSFXAudioA(); }),
            cocos2d::DelayTime::create(m_delayA),
            cocos2d::CallFunc::create([this]() { playSFXAudioB(); }),
            cocos2d::DelayTime::create(m_delayB),
            cocos2d::CallFunc::create([this]() { playSFXAudioC(); }),
            cocos2d::DelayTime::create(m_delayC),
            cocos2d::CallFunc::create([this]() { playSFXDing(); }),
            cocos2d::DelayTime::create(m_animationDelay),
            cocos2d::CallFunc::create([this]() { playAnimationSequence(); }),
            nullptr
        );
        this->runAction(seq);
    }

    // Adresse : 0x0038e00c
    void playAnimationSequence() {
        playAnimation();
    }

    /**
     * Adresse : 0x0038e07c (taille : 920 octets)
     * Joue l'animation Spine de récompense et le bruitage associé, puis selon `pageTurnMode`
     * passe automatiquement à la page suivante ou attend l'action de l'utilisateur.
     */
    void playAnimation() {
        if (m_spineNode && !m_spineAnimName.empty()) {
            spTrackEntry* entry = m_spineNode->setAnimation(0, m_spineAnimName, false);
            m_spineNode->setTrackEntryLoop(entry, false);
            float animDuration = m_spineNode->getAnimationEnd(entry);
            float autoDelay = GlobalParams::getFloatForKey("autoChangePageDelay");

            cocos2d::experimental::AudioEngine::play2d(m_animationAudio, false, 1.0f, nullptr);

            if (m_settings->pageTurnMode == ePageTurnMode::AUTOMATIC) {
                this->runAction(cocos2d::Sequence::create(
                    cocos2d::DelayTime::create(animDuration + autoDelay),
                    cocos2d::CallFunc::create([this]() { stopAnimationSequence(); }),
                    nullptr
                ));
            }
        }
    }

    // Adresse : 0x0038e414 — Envoie l'événement "next_page" pour tourner la page
    void stopAnimationSequence() {
        cocos2d::EventCustom event("next_page");
        cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&event);
    }
};

/**
 * Classe PageScene (0x0038fc90 - 0x003902fc)
 * Scène conteneur encapsulant `PageLayer` et `PinLayer`.
 */
class PageScene : public cocos2d::Scene {
public:
    // Adresse : 0x0038fc90
    static cocos2d::Scene* createScene(nlohmann::json& pageJson, int pageNum, BookSettings* settings) {
        auto scene = new (std::nothrow) PageScene();
        if (scene && scene->init(pageJson, pageNum, settings)) {
            scene->autorelease();
            return scene;
        }
        delete scene;
        return nullptr;
    }

    // Adresse : 0x0038fd54
    bool init(nlohmann::json& pageJson, int pageNum, BookSettings* settings) {
        if (!cocos2d::Scene::init()) return false;
        this->addChild(PageLayer::createLayer(pageJson, pageNum, settings));
        this->addChild(PinLayer::createLayer(settings));
        return true;
    }
};

/**
 * Classe BookEndPageScene (0x0037aaec - 0x0037b9f8)
 * Dernière page d'un livre : propose de recommencer le livre ("book_restart"),
 * retourner à l'accueil ("home_icon"), ou découvrir la version papier en boutique (protégé par PinLayer).
 */
class BookEndPageScene : public cocos2d::Scene {
private:
    BookSettings* m_settings = nullptr;
    PinLayer* m_pinLayer = nullptr;

public:
    // Adresse : 0x0037aaec
    static cocos2d::Scene* createScene(nlohmann::json& endPageJson, BookSettings* settings) {
        auto scene = new (std::nothrow) BookEndPageScene();
        if (scene && scene->init(endPageJson, settings)) {
            scene->autorelease();
            return scene;
        }
        delete scene;
        return nullptr;
    }

    // Adresse : 0x0037abb0 (taille : 2556 octets)
    bool init(nlohmann::json& endPageJson, BookSettings* settings) {
        if (!cocos2d::Scene::init()) return false;
        m_settings = settings;
        m_pinLayer = PinLayer::createLayer(settings);

        std::string bgKey = m_settings->isEnglish() ? "lastPage_en" : "lastPage";
        this->addChild(SingleImageLayer::createLayer(endPageJson[bgKey].get<std::string>()));

        auto imgDb = ImageDatabase::getImageDatabase();
        auto restartBtn = imgDb->makeMenuItemImageForID("book_restart", m_settings);
        auto homeBtn    = imgDb->makeMenuItemImageForID("home_icon", m_settings);

        restartBtn->setCallback(CC_CALLBACK_1(BookEndPageScene::doReplay, this));
        homeBtn->setCallback(CC_CALLBACK_1(BookEndPageScene::doHome, this));

        this->addChild(cocos2d::Menu::create(restartBtn, homeBtn, nullptr));
        this->addChild(m_pinLayer);
        return true;
    }

    // Adresse : 0x0037b5ec — Recommence le livre depuis le début
    void doReplay(cocos2d::Ref* sender) {
        cocos2d::EventCustom event("restart_book");
        cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&event);
    }

    // Adresse : 0x0037b7f4 — Retourne à l'écran d'accueil
    void doHome(cocos2d::Ref* sender) {
        cocos2d::Director::getInstance()->replaceScene(HomePageScene::createScene(m_settings));
    }

    // Adresse : 0x0037b848 — Appelé après validation du code PIN parental sur l'image du coffret physique
    void pinLayerPassed(cocos2d::Layer* layer) {
        if (m_settings->language == eLanguage::FRENCH) {
            cocos2d::Application::getInstance()->openURL("https://www.symbolicone.fr/boutique");
        } else if (m_settings->language == eLanguage::ENGLISH) {
            cocos2d::Application::getInstance()->openURL(
                "https://www.amazon.ca/-/fr/Caroline-Martin/dp/2924410193/ref=sr_1_15"
            );
        } else {
            cocos2d::Application::getInstance()->openURL("https://www.symbolicone.com/boutique-canada");
        }
    }
};

/**
 * Classe Book (0x0036db24 - 0x0036e7f8)
 * Contrôleur principal d'un livre : parse `<book_folder>/book.json`, écoute les événements
 * `"next_page"`, `"prev_page"`, `"reload_book"`, `"restart_book"` et instancie la scène correspondant
 * au numéro de page courant (`0` = `BookCoverScene`, `1..N` = `PageScene`, `N+1` = `BookEndPageScene`).
 */
class Book {
private:
    std::string m_filename = "book.json";
    nlohmann::json m_bookJson;
    int m_currentPage = 0;
    BookSettings* m_settings = nullptr;

public:
    // Constructeur — Adresse : 0x0036db24 (taille : 844 octets)
    Book() {
        auto dispatcher = cocos2d::Director::getInstance()->getEventDispatcher();

        dispatcher->addEventListenerWithFixedPriority(
            cocos2d::EventListenerCustom::create("next_page", [this](cocos2d::EventCustom*) { gotoNextPage(); }), 1
        );
        dispatcher->addEventListenerWithFixedPriority(
            cocos2d::EventListenerCustom::create("prev_page", [this](cocos2d::EventCustom*) { gotoPrevPage(); }), 1
        );
        dispatcher->addEventListenerWithFixedPriority(
            cocos2d::EventListenerCustom::create("reload_book", [this](cocos2d::EventCustom*) { reloadBook(); }), 1
        );
        dispatcher->addEventListenerWithFixedPriority(
            cocos2d::EventListenerCustom::create("restart_book", [this](cocos2d::EventCustom*) { restartBook(); }), 1
        );
    }

    // Adresse : 0x0036df14
    void setBookFilename(std::string filename) {
        m_filename = filename;
    }

    // Adresse : 0x0036df4c — Lit et parse le fichier book.json du livre actif
    void parseBook() {
        std::string content = cocos2d::FileUtils::getInstance()->getStringFromFile(m_filename);
        m_bookJson = nlohmann::json::parse(content);
    }

    // Adresse : 0x0036e4dc — Démarre le livre à la page 0 (couverture)
    void startBook() {
        m_currentPage = 0;
        cocos2d::Director::getInstance()->getTextureCache()->removeUnusedTextures();
        cocos2d::Scene* scene = getPageForNum(0);
        cocos2d::Director::getInstance()->replaceScene(scene);
    }

    /**
     * Adresse : 0x0036e520 (taille : 396 octets)
     * Retourne la scène correspondant à `pageNum` :
     * - pageNum == 0 : BookCoverScene (couverture)
     * - 1 <= pageNum <= pages.size() : PageScene (page interactive)
     * - pageNum > pages.size() : BookEndPageScene (page de fin)
     */
    cocos2d::Scene* getPageForNum(int pageNum) {
        auto& pages = m_bookJson["pages"];
        if (pageNum == 0) {
            return BookCoverScene::createScene(m_bookJson["cover"], m_settings);
        } else if (pageNum <= static_cast<int>(pages.size())) {
            return PageScene::createScene(pages[pageNum - 1], pageNum, m_settings);
        } else {
            return BookEndPageScene::createScene(m_bookJson["endPage"], m_settings);
        }
    }

    // Adresse : 0x0036e6ac — Page suivante avec effet de page tournée (TransitionPageTurn)
    void gotoNextPage() {
        m_currentPage++;
        auto nextScene = getPageForNum(m_currentPage);
        auto transition = cocos2d::TransitionPageTurn::create(0.5f, nextScene, false);
        cocos2d::Director::getInstance()->replaceScene(transition);
    }

    // Adresse : 0x0036e704 — Page précédente avec effet de page tournée inversé
    void gotoPrevPage() {
        if (m_currentPage > 0) m_currentPage--;
        auto prevScene = getPageForNum(m_currentPage);
        auto transition = cocos2d::TransitionPageTurn::create(0.5f, prevScene, true);
        cocos2d::Director::getInstance()->replaceScene(transition);
    }

    // Adresse : 0x0036e758 — Recharge le livre sur la page en cours
    void reloadBook() {
        parseBook();
        auto scene = getPageForNum(m_currentPage);
        cocos2d::Director::getInstance()->replaceScene(scene);
    }

    // Adresse : 0x0036e7b0 — Recommence le livre à la première page (page 1)
    void restartBook() {
        m_currentPage = 1;
        auto firstPage = getPageForNum(1);
        auto transition = cocos2d::TransitionPageTurn::create(0.5f, firstPage, true);
        cocos2d::Director::getInstance()->replaceScene(transition);
    }
};


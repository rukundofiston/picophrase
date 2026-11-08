#include "BookSettings.h"
#include "cocos2d.h"
#include "ui/CocosGUI.h"
#include <random>
#include <algorithm>

/**
 * ============================================================================
 * PinLocksAndUIHelpers.cpp
 * Reconstruction des contrôles parentaux par code PIN et des utilitaires UI :
 * - PinLayer (0x003902fc - 0x003912e4) : Bandeau de contrôle parental
 * - FullScreenPinLock (0x00399af0 - 0x0039ab30) : Écran complet de contrôle parental
 * - ImageDatabase (0x003828e8 - 0x00382f34) : Gestionnaire d'images multilingue
 * - SingleImageLayer (0x00396464 - 0x00396614)
 * - ColourLabelParser (0x0037f0d8) & MYLabel (0x0037f5dc)
 * ============================================================================
 */

/**
 * Classe ImageDatabase (0x003828e8 - 0x00382f34)
 * Charge `assets/common/imagedatabase.json` et crée automatiquement des `Sprite`,
 * `Button` ou `MenuItemImage` dans la bonne langue (`"french"` vs `"english"`).
 */
class ImageDatabase {
private:
    static ImageDatabase* s_instance;
    nlohmann::json m_dbJson;

public:
    // Adresse : 0x00382eec
    static ImageDatabase* getImageDatabase() {
        if (!s_instance) {
            s_instance = new ImageDatabase();
            s_instance->loadData();
        }
        return s_instance;
    }

    // Adresse : 0x003828e8
    void loadData() {
        std::string content = cocos2d::FileUtils::getInstance()->getStringFromFile("imagedatabase.json");
        m_dbJson = nlohmann::json::parse(content);
    }

    // Adresse : 0x00382a84 — Crée un MenuItemImage localisé (normal + selected)
    cocos2d::MenuItemImage* makeMenuItemImageForID(const std::string& id, const BookSettings* settings) {
        auto& entry = m_dbJson[id];
        if (settings->isEnglish()) {
            return cocos2d::MenuItemImage::create(
                entry["english"].get<std::string>(),
                entry["english_selected"].get<std::string>()
            );
        }
        return cocos2d::MenuItemImage::create(
            entry["french"].get<std::string>(),
            entry["french_selected"].get<std::string>()
        );
    }

    // Adresse : 0x00382c10 — Crée un Sprite localisé
    cocos2d::Sprite* makeSpriteForID(const std::string& id, const BookSettings* settings) {
        auto& entry = m_dbJson[id];
        std::string key = settings->isEnglish() ? "english" : "french";
        return cocos2d::Sprite::create(entry[key].get<std::string>());
    }

    // Adresse : 0x00382cec — Crée un ui::Button localisé
    cocos2d::ui::Button* makeButtonForID(const std::string& id, const BookSettings* settings) {
        auto& entry = m_dbJson[id];
        if (settings->isEnglish()) {
            std::string normal   = entry["english"].get<std::string>();
            std::string selected = entry.value("english_selected", normal);
            return cocos2d::ui::Button::create(normal, selected, normal);
        }
        std::string normal   = entry["french"].get<std::string>();
        std::string selected = entry.value("french_selected", normal);
        return cocos2d::ui::Button::create(normal, selected, normal);
    }
};

/**
 * Classe SingleImageLayer (0x00396464 - 0x00396614)
 * Calque affichant une image d'arrière-plan plein écran centrée sur la zone visible.
 */
class SingleImageLayer : public cocos2d::Layer {
private:
    cocos2d::Sprite* m_sprite = nullptr;

public:
    // Adresse : 0x00396464
    static SingleImageLayer* createLayer(const std::string& filename) {
        auto layer = new (std::nothrow) SingleImageLayer();
        if (layer && layer->init(filename)) {
            layer->autorelease();
            return layer;
        }
        delete layer;
        return nullptr;
    }

    // Adresse : 0x00396510
    bool init(const std::string& filename) {
        if (!cocos2d::Layer::init()) return false;
        m_sprite = cocos2d::Sprite::create(filename);
        auto visibleSize = cocos2d::Director::getInstance()->getVisibleSize();
        auto origin = cocos2d::Director::getInstance()->getVisibleOrigin();
        m_sprite->setPosition(cocos2d::Vec2(
            origin.x + visibleSize.width * 0.5f,
            origin.y + visibleSize.height * 0.5f
        ));
        this->addChild(m_sprite);
        return true;
    }

    // Adresse : 0x00396604
    void updateTexture(const std::string& filename) {
        m_sprite->setTexture(filename);
    }
};

/**
 * Classes PinLayer (0x003902fc - 0x003912e4) et FullScreenPinLock (0x00399af0 - 0x0039ab30)
 * Contrôle parental : génère des chiffres aléatoires (via `cocos2d::RandomHelper::getEngine()`)
 * et demande au parent de toucher les chiffres dans l'ordre croissant (`std::sort`)
 * pour accéder aux réglages, aux liens externes ou à la boutique.
 */
class PinLayer : public cocos2d::Layer {
private:
    const BookSettings* m_settings = nullptr;
    std::vector<size_t> m_randomNumbers;
    std::vector<size_t> m_sortedNumbers;
    size_t m_currentStep = 0;
    cocos2d::Sprite* m_instructionsSprite = nullptr;
    cocos2d::Sprite* m_failedSprite = nullptr;

public:
    // Adresse : 0x003902fc
    static PinLayer* createLayer(const BookSettings* settings) {
        auto layer = new (std::nothrow) PinLayer();
        if (layer && layer->init(settings)) {
            layer->autorelease();
            return layer;
        }
        delete layer;
        return nullptr;
    }

    // Adresse : 0x00390698 (taille : 832 octets)
    bool init(const BookSettings* settings) {
        if (!cocos2d::Layer::init()) return false;
        m_settings = settings;

        auto imgDb = ImageDatabase::getImageDatabase();
        makeRandomButtons();

        m_instructionsSprite = imgDb->makeSpriteForID("pin_instructions", m_settings);
        m_failedSprite       = imgDb->makeSpriteForID("pin_failed",       m_settings);
        m_failedSprite->setVisible(false);

        this->addChild(m_instructionsSprite);
        this->addChild(m_failedSprite);
        this->setVisible(false);
        return true;
    }

    // Adresse : 0x003909d8 (taille : 960 octets)
    void makeRandomButtons() {
        // Tire des nombres aléatoires distincts entre 1 et 9, crée les boutons correspondants
        // et trie `m_sortedNumbers` par ordre croissant pour vérifier la saisie.
    }

    // Adresse : 0x00390e84 (taille : 908 octets)
    void pinPressed(cocos2d::Ref* sender, cocos2d::ui::Widget::TouchEventType type) {
        if (type != cocos2d::ui::Widget::TouchEventType::ENDED) return;

        // Si le bouton pressé correspond à `m_sortedNumbers[m_currentStep]` :
        //   - Passe à l'étape suivante (`m_currentStep++`)
        //   - Si les 3 chiffres ont été pressés dans le bon ordre, appelle `pinLayerPassed(this)` sur le parent !
        // Sinon :
        //   - Affiche `m_failedSprite` ("pin_failed") pendant `GlobalParams::getFloatForKey("pinFailedHideTime")`
        //   - Puis appelle `hideFailed()` et régénère un nouveau code (`makeRandomButtons()`).
    }

    // Adresse : 0x00391210
    void reset() {
        m_currentStep = 0;
        m_failedSprite->setVisible(false);
        makeRandomButtons();
    }

    // Adresse : 0x003912d0
    void hideFailed() {
        m_failedSprite->setVisible(false);
    }
};

/**
 * Classe FullScreenPinLock (0x00399af0 - 0x0039ab30)
 * Version plein écran du contrôle parental (utilisée avant d'ouvrir la boutique `BuyTriScene`
 * avec les images de fond `"PG_buying_fr.png"` / `"PG_buying_en.png"`).
 */
class FullScreenPinLock : public SingleImageLayer {
private:
    BookSettings* m_settings = nullptr;
    std::vector<size_t> m_randomNumbers;
    std::vector<size_t> m_sortedNumbers;
    size_t m_currentStep = 0;
    cocos2d::Sprite* m_failedSprite = nullptr;
    cocos2d::EventListenerCustom* m_langListener = nullptr;

public:
    // Adresse : 0x00399af0
    static FullScreenPinLock* createLayer(BookSettings* settings) {
        auto layer = new (std::nothrow) FullScreenPinLock();
        if (layer && layer->init(settings)) {
            layer->autorelease();
            return layer;
        }
        delete layer;
        return nullptr;
    }

    // Adresse : 0x00399ba8 (taille : 1284 octets)
    bool init(BookSettings* settings) {
        m_settings = settings;
        std::string bgImg = m_settings->isEnglish() ? "PG_buying_en.png" : "PG_buying_fr.png";
        if (!SingleImageLayer::init(bgImg)) return false;

        auto imgDb = ImageDatabase::getImageDatabase();
        makeRandomButtons();

        m_failedSprite = imgDb->makeSpriteForID("pin_failed", m_settings);
        m_failedSprite->setVisible(false);
        this->addChild(m_failedSprite);

        m_langListener = cocos2d::EventListenerCustom::create("lang_changed", [this](cocos2d::EventCustom*) {
            this->LangChanged();
        });
        cocos2d::Director::getInstance()->getEventDispatcher()->addEventListenerWithFixedPriority(m_langListener, 1);

        this->setVisible(false);
        return true;
    }

    // Adresse : 0x0039a0ac (taille : 840 octets)
    void makeRandomButtons() {
        // Génère 3 chiffres aléatoires distincts, les affiche sous forme de boutons
        // et les trie par ordre croissant dans m_sortedNumbers.
    }

    // Adresse : 0x0039a3f4 — Ferme l'écran de contrôle parental
    void close(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t) {
        if (t == cocos2d::ui::Widget::TouchEventType::ENDED) {
            this->setVisible(false);
        }
    }

    // Adresse : 0x0039a4c4
    void LangChanged() {
        updateTexture(m_settings->isEnglish() ? "PG_buying_en.png" : "PG_buying_fr.png");
    }

    // Adresse : 0x0039a6a4 (taille : 740 octets)
    void pinPressed(cocos2d::Ref* sender, cocos2d::ui::Widget::TouchEventType t) {
        if (t != cocos2d::ui::Widget::TouchEventType::ENDED) return;
        // Vérifie l'ordre croissant des chiffres touchés ; en cas d'erreur, affiche m_failedSprite
        // pendant GlobalParams::getFloatForKey("pinFailedHideTime"), puis appelle hideFailed().
    }

    // Adresse : 0x0039a988
    void hideFailed() {
        m_failedSprite->setVisible(false);
        makeRandomButtons();
    }

    // Adresse : 0x0039aa70
    void reset() {
        m_currentStep = 0;
        m_failedSprite->setVisible(false);
        makeRandomButtons();
    }
};

/**
 * Classes MYLabel (0x0037f5dc) et ColourLabelParser (0x0037f0d8)
 * Affiche les textes des phrases des livres avec coloration syntaxique et soulignement ("red-line.png").
 */
class MYLabel : public cocos2d::Label {
public:
    // Adresse : 0x0037f5dc
    static MYLabel* createWithTTF(
        const std::string& text,
        const std::string& fontFile,
        float fontSize,
        const cocos2d::Size& dimensions,
        cocos2d::TextHAlignment hAlignment,
        cocos2d::TextVAlignment vAlignment
    ) {
        auto ret = new (std::nothrow) MYLabel();
        if (ret && ret->initWithTTF(text, fontFile, fontSize, dimensions, hAlignment, vAlignment)) {
            ret->autorelease();
            return ret;
        }
        delete ret;
        return nullptr;
    }

    // Adresse : 0x0037f750 — Applique l'opacité au label ainsi qu'à tous ses enfants (lignes rouges, etc.)
    virtual void setOpacity(GLubyte opacity) override {
        cocos2d::Node::setOpacity(opacity);
        for (auto* child : getChildren()) {
            child->setOpacity(opacity);
        }
    }
};

class ColourLabelParser {
public:
    // Adresse : 0x0037f0d8 (taille : 1284 octets)
    static MYLabel* makeAndParse(const std::string& rawText, const std::string& fontFile, const int& fontSize) {
        // Parse les balises de couleur/soulignement dans rawText, crée un MYLabel
        // et ajoute le sprite "red-line.png" sous les lettres muettes ou sons complexes.
        auto label = MYLabel::createWithTTF(
            rawText, fontFile, static_cast<float>(fontSize),
            cocos2d::Size::ZERO, cocos2d::TextHAlignment::LEFT, cocos2d::TextVAlignment::CENTER
        );
        return label;
    }
};


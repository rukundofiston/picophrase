#include "BookSettings.h"
#include "cocos2d.h"
#include "ui/CocosGUI.h"

/**
 * ============================================================================
 * SettingsAndMenus.cpp
 * Reconstruction des écrans de paramètres et d'options :
 * - OptionsScene (0x0038724c - 0x003884c4) : Options de lecture d'un livre
 * - SettingScene (0x00393bb0 - 0x00394c3c) : Menu général à 5 onglets
 * - OrthopictoSettingsLayer (0x00388714 - 0x00389058)
 * - InformationSettingsLayer (0x00384b04 - 0x0038573c)
 * - LanguageSettingsLayer (0x00385898 - 0x0038656c)
 * - RateSettingsLayer (0x003928cc - 0x00392f48)
 * ============================================================================
 */

class AppDelegate {
public:
    static void DoSymboliconeURL();
    static void DoSymboliconeFacebook();
    static void DoSymboliconeEmail();
    static void DoRate();
    static void DoPrivacy();
};
class ImageDatabase {
public:
    static ImageDatabase* getImageDatabase();
    cocos2d::MenuItemImage* makeMenuItemImageForID(const std::string& id, const BookSettings* settings);
    cocos2d::ui::Button* makeButtonForID(const std::string& id, const BookSettings* settings);
    cocos2d::Sprite* makeSpriteForID(const std::string& id, const BookSettings* settings);
};
class SingleImageLayer : public cocos2d::Layer {
public:
    static SingleImageLayer* createLayer(const std::string& filename);
};
class ShopSettingsLayer : public cocos2d::Layer {
public:
    bool init(BookSettings* settings);
};

/**
 * Classe OptionsScene (0x0038724c - 0x003884c4)
 * Écran d'options accessible depuis la couverture d'un livre (`BookCoverScene::doOptions`).
 * Permet de configurer les 3 paramètres de `BookSettings` :
 * 1. Type d'indices : Pictogrammes ("options_phrases") vs Texte seul ("options_written")
 * 2. Difficulté : Niveau 1 ("options_level1"), Niveau 2 ("options_level2"), Niveau 3 ("options_level3")
 * 3. Changement de page : Automatique ("options_automatic"), Manuel ("options_manually"), Code PIN ("options_code")
 */
class OptionsScene : public cocos2d::Scene {
private:
    BookSettings* m_settings = nullptr; // Offset 0x368 dans l'objet OptionsScene
    cocos2d::MenuItemImage* m_btnPhrases   = nullptr;
    cocos2d::MenuItemImage* m_btnWritten   = nullptr;
    cocos2d::MenuItemImage* m_btnLevel1    = nullptr;
    cocos2d::MenuItemImage* m_btnLevel2    = nullptr;
    cocos2d::MenuItemImage* m_btnLevel3    = nullptr;
    cocos2d::MenuItemImage* m_btnAutomatic = nullptr;
    cocos2d::MenuItemImage* m_btnManually  = nullptr;
    cocos2d::MenuItemImage* m_btnCode      = nullptr;

public:
    // Adresse : 0x0038724c
    static cocos2d::Scene* createScene(BookSettings* settings) {
        auto scene = new (std::nothrow) OptionsScene();
        if (scene && scene->init(settings)) {
            scene->autorelease();
            return scene;
        }
        delete scene;
        return nullptr;
    }

    // Adresse : 0x00387320 (taille : 3552 octets)
    bool init(BookSettings* settings) {
        if (!cocos2d::Scene::init()) return false;
        m_settings = settings;

        // Fond selon la langue ("options_background.png" ou "options_background_en.png")
        this->addChild(SingleImageLayer::createLayer(
            m_settings->isEnglish() ? "options_background_en.png" : "options_background.png"
        ));

        auto imgDb = ImageDatabase::getImageDatabase();

        m_btnPhrases   = imgDb->makeMenuItemImageForID("options_phrases",   m_settings);
        m_btnWritten   = imgDb->makeMenuItemImageForID("options_written",   m_settings);
        m_btnLevel1    = imgDb->makeMenuItemImageForID("options_level1",    m_settings);
        m_btnLevel2    = imgDb->makeMenuItemImageForID("options_level2",    m_settings);
        m_btnLevel3    = imgDb->makeMenuItemImageForID("options_level3",    m_settings);
        m_btnAutomatic = imgDb->makeMenuItemImageForID("options_automatic", m_settings);
        m_btnManually  = imgDb->makeMenuItemImageForID("options_manually",  m_settings);
        m_btnCode      = imgDb->makeMenuItemImageForID("options_code",      m_settings);

        auto btnPlay = imgDb->makeMenuItemImageForID("options_play", m_settings);
        auto btnBack = imgDb->makeMenuItemImageForID("back_arrow",   m_settings);

        m_btnPhrases->setCallback(CC_CALLBACK_1(OptionsScene::doPhrases, this));
        m_btnWritten->setCallback(CC_CALLBACK_1(OptionsScene::doWritten, this));
        m_btnLevel1->setCallback(CC_CALLBACK_1(OptionsScene::doLevel1, this));
        m_btnLevel2->setCallback(CC_CALLBACK_1(OptionsScene::doLevel2, this));
        m_btnLevel3->setCallback(CC_CALLBACK_1(OptionsScene::doLevel3, this));
        m_btnAutomatic->setCallback(CC_CALLBACK_1(OptionsScene::doAutomatic, this));
        m_btnManually->setCallback(CC_CALLBACK_1(OptionsScene::doManually, this));
        m_btnCode->setCallback(CC_CALLBACK_1(OptionsScene::doCode, this));
        btnPlay->setCallback(CC_CALLBACK_1(OptionsScene::doPlay, this));
        btnBack->setCallback(CC_CALLBACK_1(OptionsScene::doBackArrow, this));

        auto menu = cocos2d::Menu::create(
            m_btnPhrases, m_btnWritten,
            m_btnLevel1, m_btnLevel2, m_btnLevel3,
            m_btnAutomatic, m_btnManually, m_btnCode,
            btnPlay, btnBack, nullptr
        );
        this->addChild(menu);
        return true;
    }

    // Adresse : 0x00388100 — Mode Pictogrammes + Phrases (writtenMode = false)
    void doPhrases(cocos2d::Ref*) {
        m_btnWritten->unselected();
        m_btnPhrases->selected();
        m_settings->writtenMode = false;
    }

    // Adresse : 0x00388144 — Mode Texte Écrit uniquement (writtenMode = true)
    void doWritten(cocos2d::Ref*) {
        m_btnWritten->selected();
        m_btnPhrases->unselected();
        m_settings->writtenMode = true;
    }

    // Adresse : 0x0038818c — Difficulté Niveau 1 (1 pictogramme par page)
    void doLevel1(cocos2d::Ref*) {
        m_btnLevel1->selected();
        m_btnLevel2->unselected();
        m_btnLevel3->unselected();
        m_settings->difficulty = PageDifficuly::LEVEL_1_EASY;
    }

    // Adresse : 0x003881e0 — Difficulté Niveau 2 (2 pictogrammes par page)
    void doLevel2(cocos2d::Ref*) {
        m_btnLevel1->unselected();
        m_btnLevel2->selected();
        m_btnLevel3->unselected();
        m_settings->difficulty = PageDifficuly::LEVEL_2_MEDIUM;
    }

    // Adresse : 0x00388238 — Difficulté Niveau 3 (3 pictogrammes par page)
    void doLevel3(cocos2d::Ref*) {
        m_btnLevel1->unselected();
        m_btnLevel2->unselected();
        m_btnLevel3->selected();
        m_settings->difficulty = PageDifficuly::LEVEL_3_HARD;
    }

    // Adresse : 0x00388290 — Changement de page Automatique
    void doAutomatic(cocos2d::Ref*) {
        m_btnAutomatic->selected();
        m_btnManually->unselected();
        m_btnCode->unselected();
        m_settings->pageTurnMode = ePageTurnMode::AUTOMATIC;
    }

    // Adresse : 0x003882e4 — Changement de page Manuel
    void doManually(cocos2d::Ref*) {
        m_btnAutomatic->unselected();
        m_btnManually->selected();
        m_btnCode->unselected();
        m_settings->pageTurnMode = ePageTurnMode::MANUAL;
    }

    // Adresse : 0x0038833c — Changement de page protégé par Code PIN
    void doCode(cocos2d::Ref*) {
        m_btnAutomatic->unselected();
        m_btnManually->unselected();
        m_btnCode->selected();
        m_settings->pageTurnMode = ePageTurnMode::CODE_PIN;
    }

    // Adresse : 0x00388394 — Ferme l'écran d'options et lance immédiatement la première page du livre
    void doPlay(cocos2d::Ref*) {
        cocos2d::Director::getInstance()->popScene();
        cocos2d::EventCustom event("next_page");
        cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&event);
    }

    // Adresse : 0x003884b0 — Retour à la couverture sans lancer le livre
    void doBackArrow(cocos2d::Ref*) {
        cocos2d::Director::getInstance()->popScene();
    }
};

/**
 * Classe LanguageSettingsLayer (0x00385898 - 0x0038656c)
 * Onglet de sélection de la langue dans SettingScene :
 * - Drapeau Québécois ("settings_quebec_flag") -> lang = 2
 * - Drapeau Français ("settings_french_flag")  -> lang = 0
 * - Drapeau Américain ("settings_usa_flag")    -> lang = 1
 * Sauvegarde la clé "lang" dans `UserDefault` et émet l'événement `"lang_changed"`.
 */
class LanguageSettingsLayer : public cocos2d::Layer {
private:
    BookSettings* m_settings = nullptr;

public:
    // Adresse : 0x00385978
    bool init(BookSettings* settings) {
        if (!cocos2d::Layer::init()) return false;
        setItems(settings);
        return true;
    }

    // Adresse : 0x003859bc (taille : 1312 octets)
    void setItems(BookSettings* settings) {
        m_settings = settings;
        auto imgDb = ImageDatabase::getImageDatabase();

        auto btnQuebec   = imgDb->makeMenuItemImageForID("settings_quebec_flag", m_settings);
        auto btnFrench   = imgDb->makeMenuItemImageForID("settings_french_flag", m_settings);
        auto btnAmerican = imgDb->makeMenuItemImageForID("settings_usa_flag",    m_settings);

        btnQuebec->setCallback(CC_CALLBACK_1(LanguageSettingsLayer::doQuebec, this));
        btnFrench->setCallback(CC_CALLBACK_1(LanguageSettingsLayer::doFrench, this));
        btnAmerican->setCallback(CC_CALLBACK_1(LanguageSettingsLayer::doAmerican, this));

        this->addChild(cocos2d::Menu::create(btnQuebec, btnFrench, btnAmerican, nullptr));
        this->addChild(imgDb->makeSpriteForID("settings_language_text", m_settings));
    }

    // Adresse : 0x00385edc — Passe en Français Québécois
    void doQuebec(cocos2d::Ref*) {
        m_settings->language = eLanguage::QUEBEC;
        cocos2d::UserDefault::getInstance()->setIntegerForKey("lang", static_cast<int>(eLanguage::QUEBEC));
        reload();
        cocos2d::EventCustom event("lang_changed");
        cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&event);
    }

    // Adresse : 0x00386058 — Passe en Français
    void doFrench(cocos2d::Ref*) {
        m_settings->language = eLanguage::FRENCH;
        cocos2d::UserDefault::getInstance()->setIntegerForKey("lang", static_cast<int>(eLanguage::FRENCH));
        reload();
        cocos2d::EventCustom event("lang_changed");
        cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&event);
    }

    // Adresse : 0x003861d4 — Passe en Anglais
    void doAmerican(cocos2d::Ref*) {
        m_settings->language = eLanguage::ENGLISH;
        cocos2d::UserDefault::getInstance()->setIntegerForKey("lang", static_cast<int>(eLanguage::ENGLISH));
        reload();
        cocos2d::EventCustom event("lang_changed");
        cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&event);
    }

    // Adresse : 0x00386438
    void reload() {
        this->removeAllChildren();
        setItems(m_settings);
    }
};

/**
 * Fonctions globales d'aide au texte HTML/XML des onglets Informations et Orthopicto
 * Adresses : 0x00382f34 (getISBody_English), 0x00383ca4 (getISBody_French),
 *            0x00384a14 (ISLcallbackFromRichText), 0x0038866c (getOSBody_English), 0x003886c0 (getOSBody_French)
 */
std::string getISBody_English(float width); // Génère le XML RichText anglais avec les icônes fb-inc.png, site-inc.png, mail-inc.png
std::string getISBody_French(float width);  // Génère le XML RichText français avec les icônes fb-inc.png, site-inc.png, mail-inc.png
std::string getOSBody_English();            // Retourne le XML RichText anglais de présentation d'Orthopicto
std::string getOSBody_French();             // Retourne le XML RichText français de présentation d'Orthopicto

// Adresse : 0x00384a14 (taille : 240 octets)
void ISLcallbackFromRichText(const std::string& url) {
    if (url == "cocos2dx:site") {
        AppDelegate::DoSymboliconeURL();
    } else if (url == "cocos2dx:mail") {
        AppDelegate::DoSymboliconeEmail();
    } else if (url == "cocos2dx:fb") {
        AppDelegate::DoSymboliconeFacebook();
    } else if (url == "cocos2dx:privacy") {
        AppDelegate::DoPrivacy();
    }
}

/**
 * Classe OrthopictoSettingsLayer (0x00388714 - 0x00389058)
 * Premier onglet de SettingScene : présente la démarche orthophonique Orthopicto dans un ScrollView.
 */
class OrthopictoSettingsLayer : public cocos2d::Layer {
private:
    BookSettings* m_settings = nullptr;
    cocos2d::ui::ScrollView* m_scrollView = nullptr;
    cocos2d::EventListenerCustom* m_langListener = nullptr;

public:
    // Adresse : 0x00388714
    static OrthopictoSettingsLayer* createLayer(BookSettings* settings) {
        auto layer = new (std::nothrow) OrthopictoSettingsLayer();
        if (layer && layer->init(settings)) {
            layer->autorelease();
            return layer;
        }
        delete layer;
        return nullptr;
    }

    // Adresse : 0x003887c0
    virtual ~OrthopictoSettingsLayer() {
        if (m_langListener) {
            cocos2d::Director::getInstance()->getEventDispatcher()->removeEventListener(m_langListener);
        }
    }

    // Adresse : 0x00388870 (taille : 636 octets)
    bool init(BookSettings* settings) {
        if (!cocos2d::Layer::init()) return false;
        m_settings = settings;

        m_langListener = cocos2d::EventListenerCustom::create("lang_changed", [this](cocos2d::EventCustom*) {
            this->LangChanged();
        });
        cocos2d::Director::getInstance()->getEventDispatcher()->addEventListenerWithFixedPriority(m_langListener, 1);

        auto visibleSize = cocos2d::Director::getInstance()->getVisibleSize();
        m_scrollView = cocos2d::ui::ScrollView::create();
        m_scrollView->setContentSize(cocos2d::Size(visibleSize.width * 0.8f, visibleSize.height * 0.65f));
        m_scrollView->setBackGroundColor(cocos2d::Color3B(255, 255, 255));
        this->addChild(m_scrollView);

        reload();
        return true;
    }

    // Adresse : 0x00388aec (taille : 468 octets)
    void reload() {
        auto imgDb = ImageDatabase::getImageDatabase();
        auto header = imgDb->makeSpriteForID("settings_orthopicto_text", m_settings);
        this->addChild(header);
        addTextToScrollView();
    }

    // Adresse : 0x00388cc0 (taille : 860 octets)
    void addTextToScrollView() {
        std::string xml = m_settings->isEnglish() ? getOSBody_English() : getOSBody_French();
        cocos2d::ValueMap defaults;
        auto richText = cocos2d::ui::RichText::createWithXML(xml, defaults, ISLcallbackFromRichText);
        richText->formatText();
        m_scrollView->addChild(richText);
    }

    // Adresse : 0x0038901c
    void LangChanged() {
        reload();
    }
};

/**
 * Classe InformationSettingsLayer (0x00384b04 - 0x0038573c)
 * Deuxième onglet de SettingScene : affiche les crédits de l'application et les liens
 * vers le site Symbolicone, Facebook, l'adresse e-mail et la politique de confidentialité.
 */
class InformationSettingsLayer : public cocos2d::Layer {
private:
    BookSettings* m_settings = nullptr;
    cocos2d::ui::ScrollView* m_scrollView = nullptr;
    cocos2d::EventListenerCustom* m_langListener = nullptr;

public:
    // Adresse : 0x00384b04
    static InformationSettingsLayer* createLayer(BookSettings* settings) {
        auto layer = new (std::nothrow) InformationSettingsLayer();
        if (layer && layer->init(settings)) {
            layer->autorelease();
            return layer;
        }
        delete layer;
        return nullptr;
    }

    // Adresse : 0x00384bb0
    virtual ~InformationSettingsLayer() {
        if (m_langListener) {
            cocos2d::Director::getInstance()->getEventDispatcher()->removeEventListener(m_langListener);
        }
    }

    // Adresse : 0x00384c60 (taille : 872 octets)
    bool init(BookSettings* settings) {
        if (!cocos2d::Layer::init()) return false;
        m_settings = settings;

        m_langListener = cocos2d::EventListenerCustom::create("lang_changed", [this](cocos2d::EventCustom*) {
            this->LangChanged();
        });
        cocos2d::Director::getInstance()->getEventDispatcher()->addEventListenerWithFixedPriority(m_langListener, 1);

        auto imgDb = ImageDatabase::getImageDatabase();
        this->addChild(imgDb->makeSpriteForID("settings_information_text", m_settings));

        auto visibleSize = cocos2d::Director::getInstance()->getVisibleSize();
        m_scrollView = cocos2d::ui::ScrollView::create();
        m_scrollView->setContentSize(cocos2d::Size(visibleSize.width * 0.8f, visibleSize.height * 0.65f));
        m_scrollView->setBackGroundColor(cocos2d::Color3B(255, 255, 255));
        m_scrollView->setSwallowTouches(false);
        this->addChild(m_scrollView);

        addTextToScrollView();
        return true;
    }

    // Adresse : 0x003850b4 (taille : 1024 octets)
    void addTextToScrollView() {
        auto visibleSize = cocos2d::Director::getInstance()->getVisibleSize();
        std::string xml = m_settings->isEnglish()
            ? getISBody_English(visibleSize.width)
            : getISBody_French(visibleSize.width);

        cocos2d::ValueMap defaults;
        auto richText = cocos2d::ui::RichText::createWithXML(xml, defaults, ISLcallbackFromRichText);
        richText->formatText();
        m_scrollView->addChild(richText);
    }

    // Adresse : 0x003854b4
    void LangChanged() {
        reload();
    }

    // Adresse : 0x003854f0
    void reload() {
        m_scrollView->removeAllChildren();
        addTextToScrollView();
    }
};

/**
 * Classe RateSettingsLayer (0x003928cc - 0x00392f48)
 * Cinquième onglet de SettingScene : invite l'utilisateur à évaluer l'application sur le Store.
 */
class RateSettingsLayer : public cocos2d::Layer {
private:
    BookSettings* m_settings = nullptr;
    cocos2d::EventListenerCustom* m_langListener = nullptr;

public:
    // Adresse : 0x003928cc
    static RateSettingsLayer* createLayer(BookSettings* settings) {
        auto layer = new (std::nothrow) RateSettingsLayer();
        if (layer && layer->init(settings)) {
            layer->autorelease();
            return layer;
        }
        delete layer;
        return nullptr;
    }

    // Adresse : 0x00392978
    virtual ~RateSettingsLayer() {
        if (m_langListener) {
            cocos2d::Director::getInstance()->getEventDispatcher()->removeEventListener(m_langListener);
        }
    }

    // Adresse : 0x00392a28 (taille : 368 octets)
    bool init(BookSettings* settings) {
        if (!cocos2d::Layer::init()) return false;
        m_settings = settings;

        m_langListener = cocos2d::EventListenerCustom::create("lang_changed", [this](cocos2d::EventCustom*) {
            this->LangChanged();
        });
        cocos2d::Director::getInstance()->getEventDispatcher()->addEventListenerWithFixedPriority(m_langListener, 1);

        reload();
        return true;
    }

    // Adresse : 0x00392b98 (taille : 648 octets)
    void reload() {
        this->removeAllChildren();
        auto imgDb = ImageDatabase::getImageDatabase();

        auto textSprite = imgDb->makeSpriteForID("settings_notation_text", m_settings);
        auto rateBtn    = imgDb->makeButtonForID("rate_app_button", m_settings);
        rateBtn->addTouchEventListener(CC_CALLBACK_2(RateSettingsLayer::rate, this));

        this->addChild(textSprite);
        this->addChild(rateBtn);
    }

    // Adresse : 0x00392e20
    void LangChanged() {
        reload();
    }

    // Adresse : 0x00392f44
    void rate(cocos2d::Ref* sender, cocos2d::ui::Widget::TouchEventType type) {
        if (type == cocos2d::ui::Widget::TouchEventType::ENDED) {
            AppDelegate::DoRate();
        }
    }
};

/**
 * Classe SettingScene (0x00393bb0 - 0x00394c3c)
 * Menu général accessible depuis l'icône d'engrenage de l'accueil (après le code PIN).
 * Contient 5 onglets commutables via `swapLayer()` :
 * 1. Orthopicto (`OrthopictoSettingsLayer`)
 * 2. Informations (`InformationSettingsLayer`)
 * 3. Langue (`LanguageSettingsLayer`)
 * 4. Boutique (`ShopSettingsLayer`)
 * 5. Évaluation (`RateSettingsLayer`)
 */
class SettingScene : public cocos2d::Scene {
private:
    cocos2d::Layer* m_activeLayer = nullptr;
    OrthopictoSettingsLayer*  m_orthoLayer = nullptr;
    InformationSettingsLayer* m_infoLayer  = nullptr;
    LanguageSettingsLayer*    m_langLayer  = nullptr;
    ShopSettingsLayer*        m_shopLayer  = nullptr;
    RateSettingsLayer*        m_rateLayer  = nullptr;

public:
    // Adresse : 0x00393cec (taille : 3136 octets)
    bool init(BookSettings* settings) {
        if (!cocos2d::Scene::init()) return false;

        this->addChild(SingleImageLayer::createLayer(
            settings->isEnglish() ? "background-option-menu-ui_en.png" : "background-option-menu-ui.png"
        ));

        auto imgDb = ImageDatabase::getImageDatabase();
        auto btnOrtho = imgDb->makeButtonForID("settings_orthopicto_icon",  settings);
        auto btnInfo  = imgDb->makeButtonForID("settings_information_icon", settings);
        auto btnLang  = imgDb->makeButtonForID("settings_language_icon",    settings);
        auto btnShop  = imgDb->makeButtonForID("settings_shop_icon",        settings);
        auto btnRate  = imgDb->makeButtonForID("settings_rate_icon",        settings);
        auto btnBack  = imgDb->makeButtonForID("back_arrow",                settings);

        btnOrtho->addTouchEventListener(CC_CALLBACK_2(SettingScene::doOrtho, this));
        btnInfo->addTouchEventListener(CC_CALLBACK_2(SettingScene::doInfo, this));
        btnLang->addTouchEventListener(CC_CALLBACK_2(SettingScene::doLanguage, this));
        btnShop->addTouchEventListener(CC_CALLBACK_2(SettingScene::doShop, this));
        btnRate->addTouchEventListener(CC_CALLBACK_2(SettingScene::doNotation, this));
        btnBack->addTouchEventListener(CC_CALLBACK_2(SettingScene::doBack, this));

        // Instancie et retient les 5 calques d'onglets
        m_orthoLayer = new (std::nothrow) OrthopictoSettingsLayer(); m_orthoLayer->init(settings); m_orthoLayer->retain();
        m_infoLayer  = new (std::nothrow) InformationSettingsLayer(); m_infoLayer->init(settings); m_infoLayer->retain();
        m_langLayer  = new (std::nothrow) LanguageSettingsLayer();    m_langLayer->init(settings); m_langLayer->retain();
        m_shopLayer  = new (std::nothrow) ShopSettingsLayer();        m_shopLayer->init(settings); m_shopLayer->retain();
        m_rateLayer  = new (std::nothrow) RateSettingsLayer();        m_rateLayer->init(settings); m_rateLayer->retain();

        swapLayer(m_orthoLayer);
        return true;
    }

    // Adresses : 0x0039492c .. 0x00394a7c — Changement d'onglet actif
    void doOrtho(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t)    { if (t == cocos2d::ui::Widget::TouchEventType::ENDED) swapLayer(m_orthoLayer); }
    void doInfo(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t)     { if (t == cocos2d::ui::Widget::TouchEventType::ENDED) swapLayer(m_infoLayer); }
    void doLanguage(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t) { if (t == cocos2d::ui::Widget::TouchEventType::ENDED) swapLayer(m_langLayer); }
    void doShop(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t)     { if (t == cocos2d::ui::Widget::TouchEventType::ENDED) swapLayer(m_shopLayer); }
    void doNotation(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t) { if (t == cocos2d::ui::Widget::TouchEventType::ENDED) swapLayer(m_rateLayer); }

    // Adresse : 0x00394ad0 — Retour à HomePageScene
    void doBack(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t) {
        if (t == cocos2d::ui::Widget::TouchEventType::ENDED) {
            cocos2d::Director::getInstance()->popScene();
        }
    }

    // Adresse : 0x00394bf0
    void swapLayer(cocos2d::Layer* newLayer) {
        if (m_activeLayer) {
            this->removeChild(m_activeLayer, false);
        }
        m_activeLayer = newLayer;
        this->addChild(m_activeLayer);
    }
};


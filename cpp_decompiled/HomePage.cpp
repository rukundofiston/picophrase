#include "BookSettings.h"
#include "cocos2d.h"
#include "ui/CocosGUI.h"

/**
 * ============================================================================
 * HomePage.cpp
 * Reconstruction de l'écran d'accueil et du carrousel de sélection des livres :
 * - HomePageScene (0x00382560 - 0x003828e8)
 * - HomePageLayer (0x00380168 - 0x003820dc)
 * ============================================================================
 */

// Déclarations anticipées
class AppDelegate {
public:
    static void startBook(std::string bookFolder);
    static void DoSymboliconeURL();
};
class IAPListen {
public:
    static bool productPurchased(std::string iapName);
};
namespace mingos {
    class IAP {
    public:
        static void restore();
    };
}
class ImageDatabase {
public:
    static ImageDatabase* getImageDatabase();
    cocos2d::ui::Button* makeButtonForID(const std::string& id, const BookSettings* settings);
    cocos2d::Sprite* makeSpriteForID(const std::string& id, const BookSettings* settings);
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
class FullScreenPinLock : public cocos2d::Layer {
public:
    static FullScreenPinLock* createLayer(BookSettings* settings);
    void reset();
};
class SettingScene : public cocos2d::Scene {
public:
    bool init(BookSettings* settings);
};
class BuyTriScene : public cocos2d::Scene {
public:
    static cocos2d::Scene* createScene(const BookSettings* settings, nlohmann::json& buyInfo, int itemIndex);
};

/**
 * Classe HomePageLayer (0x00380168 - 0x003820dc)
 * Gère le carrousel à 3 icônes (gauche, milieu, droite) permettant de faire défiler
 * les livres listés dans assets/BuyInfo.json (ceux ayant "AppearOnHome": 1).
 */
class HomePageLayer : public cocos2d::Layer {
private:
    enum class ePendingPinAction {
        NONE     = 0,
        SETTINGS = 1, // Ouvrir SettingScene
        BUY_BOOK = 2, // Ouvrir BuyTriScene pour le livre verrouillé sélectionné
        LOGO_URL = 3  // Ouvrir le site web Symbolicone
    };

    BookSettings* m_settings = nullptr;
    nlohmann::json m_buyInfo;
    std::vector<int> m_homeItemIndices; // Index des éléments de BuyInfo["items"] ayant "AppearOnHome" == 1
    int m_currentCenterSlot = 0;
    int m_selectedBookIndex = 0;
    ePendingPinAction m_pendingAction = ePendingPinAction::NONE;

    cocos2d::ui::Button* m_iconButtons[3] = { nullptr, nullptr, nullptr };
    cocos2d::Sprite*     m_lockSprites[3] = { nullptr, nullptr, nullptr };
    PinLayer*            m_pinLayer = nullptr;
    FullScreenPinLock*   m_fullScreenPinLock = nullptr;
    cocos2d::EventListenerCustom* m_langListener = nullptr;

public:
    // Adresse : 0x00380168
    static HomePageLayer* createLayer(BookSettings* settings) {
        auto layer = new (std::nothrow) HomePageLayer();
        if (layer && layer->init(settings)) {
            layer->autorelease();
            return layer;
        }
        delete layer;
        return nullptr;
    }

    // Adresse : 0x00381a68
    virtual ~HomePageLayer() {
        if (m_langListener) {
            cocos2d::Director::getInstance()->getEventDispatcher()->removeEventListener(m_langListener);
        }
    }

    /**
     * Adresse : 0x00380224 (taille : 3848 octets)
     * Initialise les boutons d'accueil, le carrousel à 3 livres et les cadenas.
     */
    bool init(BookSettings* settings) {
        if (!cocos2d::Layer::init()) return false;
        m_settings = settings;

        auto visibleSize = cocos2d::Director::getInstance()->getVisibleSize();

        // 1. Charge BuyInfo.json et filtre les éléments qui ont "AppearOnHome" == 1
        std::string content = cocos2d::FileUtils::getInstance()->getStringFromFile("BuyInfo.json");
        m_buyInfo = nlohmann::json::parse(content);
        auto& items = m_buyInfo["items"];
        for (size_t i = 0; i < items.size(); ++i) {
            if (items[i].value("AppearOnHome", 0) == 1) {
                m_homeItemIndices.push_back(static_cast<int>(i));
            }
        }

        auto imgDb = ImageDatabase::getImageDatabase();

        // 2. Bouton Réglages ("home_page_settings") en haut
        auto settingsBtn = imgDb->makeButtonForID("home_page_settings", m_settings);
        settingsBtn->addTouchEventListener(CC_CALLBACK_2(HomePageLayer::options, this));
        this->addChild(settingsBtn);

        // 3. Logo Symbolicone ("home_page_symbolicone_logo")
        auto logoBtn = imgDb->makeButtonForID("home_page_symbolicone_logo", m_settings);
        logoBtn->addTouchEventListener(CC_CALLBACK_2(HomePageLayer::pressLogo, this));
        this->addChild(logoBtn);

        // 4. Texte d'entête ("home_page_text")
        auto headerSprite = imgDb->makeSpriteForID("home_page_text", m_settings);
        this->addChild(headerSprite);

        // 5. Crée les 3 emplacements du carrousel (Gauche, Milieu, Droite)
        // Les coordonnées proviennent des clés de BuyInfo.json :
        // "TitleIconXLeft" (0.2), "TitleIconXMiddle" (0.5), "TitleIconXRight" (0.8),
        // "TitleIconYPercent" (0.53), "TitleLockYPercent" (0.35), "TitleLockIcon" ("common/ cadena.png")
        std::string lockIconPath = m_buyInfo["TitleLockIcon"].get<std::string>();
        for (int slot = 0; slot < 3; ++slot) {
            m_iconButtons[slot] = cocos2d::ui::Button::create();
            m_iconButtons[slot]->addTouchEventListener(CC_CALLBACK_2(HomePageLayer::select, this));
            this->addChild(m_iconButtons[slot]);

            m_lockSprites[slot] = cocos2d::Sprite::create(lockIconPath);
            this->addChild(m_lockSprites[slot]);
        }

        SetIcons();

        // 6. Flèches de défilement gauche ("home_page_left_arrow") et droite ("home_page_right_arrow")
        auto leftArrow = imgDb->makeButtonForID("home_page_left_arrow", m_settings);
        leftArrow->addTouchEventListener(CC_CALLBACK_2(HomePageLayer::prev, this));
        this->addChild(leftArrow);

        auto rightArrow = imgDb->makeButtonForID("home_page_right_arrow", m_settings);
        rightArrow->addTouchEventListener(CC_CALLBACK_2(HomePageLayer::next, this));
        this->addChild(rightArrow);

        // 7. Bouton de restauration des achats ("restore_purchase_button")
        auto restoreBtn = imgDb->makeButtonForID("restore_purchase_button", m_settings);
        restoreBtn->addTouchEventListener(CC_CALLBACK_2(HomePageLayer::RestoreItems, this));
        this->addChild(restoreBtn);

        // 8. Écoute l'événement "lang_changed" pour mettre à jour les textes si la langue change
        m_langListener = cocos2d::EventListenerCustom::create("lang_changed", [this](cocos2d::EventCustom*) {
            this->LangChanged();
        });
        cocos2d::Director::getInstance()->getEventDispatcher()->addEventListenerWithFixedPriority(m_langListener, 1);

        return true;
    }

    // Adresse : 0x00381218 — Clic sur le bouton Réglages (demande d'abord le code PIN parental)
    void options(cocos2d::Ref* sender, cocos2d::ui::Widget::TouchEventType type) {
        if (type == cocos2d::ui::Widget::TouchEventType::ENDED) {
            m_pendingAction = ePendingPinAction::SETTINGS;
            m_pinLayer->reset();
            m_pinLayer->setVisible(true);
        }
    }

    // Adresse : 0x0038125c — Clic sur le logo Symbolicone (demande d'abord le code PIN parental)
    void pressLogo(cocos2d::Ref* sender, cocos2d::ui::Widget::TouchEventType type) {
        if (type == cocos2d::ui::Widget::TouchEventType::ENDED) {
            m_pendingAction = ePendingPinAction::LOGO_URL;
            m_pinLayer->reset();
            m_pinLayer->setVisible(true);
        }
    }

    /**
     * Adresse : 0x003812a4 (taille : 904 octets)
     * Appelé lorsque l'utilisateur touche l'une des 3 icônes de livres du carrousel.
     */
    void select(cocos2d::Ref* sender, cocos2d::ui::Widget::TouchEventType type) {
        if (type != cocos2d::ui::Widget::TouchEventType::ENDED) return;

        auto& item = m_buyInfo["items"][m_selectedBookIndex];
        std::string iapName = item["iap_name"].get<std::string>();

        // Vérifie si le livre est acheté (ou débloqué)
        if (IAPListen::productPurchased(iapName)) {
            if (item.find("book_folder") != item.end()) {
                std::string bookFolder = item["book_folder"].get<std::string>();
                AppDelegate::startBook(bookFolder);
            }
        } else {
            // Livre verrouillé : ouvre le contrôle parental plein écran avant d'afficher BuyTriScene
            m_pendingAction = ePendingPinAction::BUY_BOOK;
            m_fullScreenPinLock->reset();
            m_fullScreenPinLock->setVisible(true);
        }
    }

    /**
     * Adresse : 0x0038162c (taille : 736 octets)
     * Configure l'image du livre ("title_icon_french" / "title_icon_english") et la visibilité
     * du cadenas selon que le produit "iap_name" est acheté ou non.
     */
    void setIconTo(int buttonSlot, int itemIndex, int positionSlot) {
        auto& item = m_buyInfo["items"][itemIndex];
        std::string iconPath = m_settings->isEnglish()
            ? item["title_icon_english"].get<std::string>()
            : item["title_icon_french"].get<std::string>();

        m_iconButtons[buttonSlot]->loadTextures(iconPath, iconPath, iconPath);

        std::string iapName = item["iap_name"].get<std::string>();
        bool purchased = IAPListen::productPurchased(iapName);
        m_lockSprites[buttonSlot]->setVisible(!purchased);

        setIconToPosition(buttonSlot, positionSlot);
    }

    // Adresses : 0x0038190c (next) et 0x003819b0 (prev) — Défilement du carrousel
    void next(cocos2d::Ref* sender, cocos2d::ui::Widget::TouchEventType type) {
        if (type == cocos2d::ui::Widget::TouchEventType::ENDED) {
            m_currentCenterSlot = getNextItemNum(m_currentCenterSlot);
            SetIcons();
        }
    }

    void prev(cocos2d::Ref* sender, cocos2d::ui::Widget::TouchEventType type) {
        if (type == cocos2d::ui::Widget::TouchEventType::ENDED) {
            m_currentCenterSlot = getPrevItemNum(m_currentCenterSlot);
            SetIcons();
        }
    }

    // Adresse : 0x00381a58 — Restauration des achats Google Play
    void RestoreItems(cocos2d::Ref* sender, cocos2d::ui::Widget::TouchEventType type) {
        if (type == cocos2d::ui::Widget::TouchEventType::ENDED) {
            mingos::IAP::restore();
        }
    }

    // Adresse : 0x00381b00
    void LangChanged() {
        SetIcons();
    }

    // Adresse : 0x00381dfc
    virtual void onEnter() override {
        SetIcons();
        cocos2d::Node::onEnter();
    }

    // Adresse : 0x00381e74
    void SetIcons() {
        int leftIdx  = getPrevItemNum(m_currentCenterSlot);
        int midIdx   = m_currentCenterSlot;
        int rightIdx = getNextItemNum(m_currentCenterSlot);

        setIconTo(0, m_homeItemIndices[leftIdx],  0);
        setIconTo(1, m_homeItemIndices[midIdx],   1);
        setIconTo(2, m_homeItemIndices[rightIdx], 2);
    }

    // Adresse : 0x00381ee4
    void setIconToPosition(int buttonSlot, int positionSlot) {
        // Positionne le bouton à gauche (TitleIconXLeft), au centre (TitleIconXMiddle) ou à droite (TitleIconXRight)
    }

    // Adresses : 0x00381f94 et 0x00381fa8
    int getNextItemNum(int current) {
        int count = static_cast<int>(m_homeItemIndices.size());
        return (current + 1) % count;
    }

    int getPrevItemNum(int current) {
        int count = static_cast<int>(m_homeItemIndices.size());
        return (current - 1 + count) % count;
    }

    /**
     * Adresse : 0x00381fc4 (taille : 272 octets)
     * Appelé lorsque le code PIN parental a été validé avec succès.
     */
    void pinLayerPassed(cocos2d::Layer* pinLayer) {
        if (m_pendingAction == ePendingPinAction::SETTINGS) {
            auto scene = new (std::nothrow) SettingScene();
            if (scene && scene->init(m_settings)) {
                scene->autorelease();
                cocos2d::Director::getInstance()->pushScene(scene);
            }
        } else if (m_pendingAction == ePendingPinAction::BUY_BOOK) {
            auto scene = BuyTriScene::createScene(m_settings, m_buyInfo, m_selectedBookIndex);
            cocos2d::Director::getInstance()->pushScene(scene);
        } else if (m_pendingAction == ePendingPinAction::LOGO_URL) {
            AppDelegate::DoSymboliconeURL();
        }
        m_pendingAction = ePendingPinAction::NONE;
    }
};

/**
 * Classe HomePageScene (0x00382560 - 0x003828e8)
 * Scène conteneur de l'écran d'accueil : assemble l'image de fond, HomePageLayer et les 2 calques PIN.
 */
class HomePageScene : public cocos2d::Scene {
public:
    // Adresse : 0x00382560
    static cocos2d::Scene* createScene(BookSettings* settings) {
        auto scene = new (std::nothrow) HomePageScene();
        if (scene && scene->init(settings)) {
            scene->autorelease();
            return scene;
        }
        delete scene;
        return nullptr;
    }

    // Adresse : 0x0038260c (taille : 692 octets)
    bool init(BookSettings* settings) {
        if (!cocos2d::Scene::init()) return false;

        // Fond d'écran selon la langue ("homepage-background-ui.png" ou "homepage-background-ui_en.png")
        auto bgFr = SingleImageLayer::createLayer("homepage-background-ui.png");
        auto bgEn = SingleImageLayer::createLayer("homepage-background-ui_en.png");
        this->addChild(bgFr);
        this->addChild(bgEn);

        // Calque interactif du carrousel
        auto homeLayer = HomePageLayer::createLayer(settings);
        this->addChild(homeLayer);

        // Calques de contrôle parental (invisibles par défaut jusqu'à ce qu'on clique sur un élément protégé)
        auto fullScreenPin = FullScreenPinLock::createLayer(settings);
        auto topBarPin = PinLayer::createLayer(settings);
        this->addChild(fullScreenPin);
        this->addChild(topBarPin);

        return true;
    }
};


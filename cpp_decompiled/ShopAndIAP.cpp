#include "BookSettings.h"
#include "cocos2d.h"
#include "platform/android/jni/JniHelper.h"
#include "ui/CocosGUI.h"
#include <jni.h>

/**
 * ============================================================================
 * ShopAndIAP.cpp
 * Reconstruction complète du système d'achats intégrés (IAP), du pont JNI vers
 * PurchaseManager.java et des écrans de boutique :
 * - mingos::IAP (0x003aa0ec - 0x003aa980) : Appels C++ -> Java (PurchaseManager)
 * - Java_iap_PurchaseEventListener_* (0x003a557c - 0x003a79d8) : Callbacks Java -> C++
 * - BuyInfoManager (0x0037bcb0 - 0x0037c2fc)
 * - IAPListen (0x0039afc0 - 0x0039c4e4)
 * - BuyTriScene (0x0037eabc) & BuyTriLayer (0x0037c7f0)
 * - ShopSettingsLayer (0x00394dbc), ScrollShopItemNode (0x003930c4), OptionsBuyInfoLayer (0x00386628)
 * ============================================================================
 */

namespace mingos {
    struct Product {
        std::string id;
        std::string name;
        std::string title;
        std::string description;
        float       priceValue = 0.0f;
        std::string price;
        std::string currencyCode;
        std::string receiptCipheredPayload;
        std::string receipt;
        std::string transactionID;
    };

    enum class BillingResponseCode : int {
        OK = 0,
        USER_CANCELED = 1,
        ERROR = 6
    };

    class IAPEventListener {
    public:
        virtual ~IAPEventListener() = default;
        virtual void onInitialized(bool success) = 0;
        virtual void onPurchaseSuccess(const Product& product) = 0;
        virtual void onPurchaseFailure(const Product& product, const BillingResponseCode& code, const std::string& msg) = 0;
        virtual void onPurchaseCanceled(const Product& product) = 0;
        virtual void onRestoreSuccess(const std::vector<Product>& products) = 0;
        virtual void onProductRequestSuccess(const std::vector<Product>& products) = 0;
        virtual void onProductRequestFailure(const std::string& msg) = 0;
        virtual void onPurchaseHistory(const std::string& historyJson) {} // 0x0039c4d0
        virtual void onConsumeSuccess(const Product& product) = 0;
        virtual void onConsumeFailure(const Product& product, const BillingResponseCode& code, const std::string& msg) = 0;
        virtual void onRestoreFailure(const BillingResponseCode& code, const std::string& msg) = 0;
        virtual void onAcknowledgeSuccess(const Product& product) = 0;
        virtual void onAcknowledgeFailure(const Product& product, const BillingResponseCode& code, const std::string& msg) = 0;
    };

    /**
     * Classe mingos::IAP (0x003aa0ec - 0x003aa980)
     * Pont JNI qui appelle les méthodes de `iap/PurchaseManager.java` dans le code Java Android.
     */
    class IAP {
    private:
        // Récupère l'instance singleton Java `iap.PurchaseManager.getInstance()`
        static jobject getJavaPurchaseManager() {
            cocos2d::JniMethodInfo t;
            if (cocos2d::JniHelper::getStaticMethodInfo(t, "iap/PurchaseManager", "getInstance", "()Liap/PurchaseManager;")) {
                return t.env->CallStaticObjectMethod(t.classID, t.methodID);
            }
            return nullptr;
        }

    public:
        // Adresse : 0x003aa0ec — Lit assets/sdkbox_config.json et appelle PurchaseManager.init(jsonString)
        static void init() {
            jobject pm = getJavaPurchaseManager();
            cocos2d::JniMethodInfo t;
            if (cocos2d::JniHelper::getMethodInfo(t, "iap/PurchaseManager", "init", "(Ljava/lang/String;)V")) {
                std::string configJson = cocos2d::FileUtils::getInstance()->getStringFromFile("sdkbox_config.json");
                jstring jConfig = t.env->NewStringUTF(configJson.c_str());
                t.env->CallVoidMethod(pm, t.methodID, jConfig);
            }
        }

        // Adresse : 0x003aa3ec — Instancie `new iap.PurchaseEventListener(long ptr)` et l'attache à PurchaseManager
        static void setListener(IAPEventListener* listener) {
            jobject pm = getJavaPurchaseManager();
            if (listener) {
                cocos2d::JniMethodInfo ctor;
                cocos2d::JniHelper::getMethodInfo(ctor, "iap/PurchaseEventListener", "<init>", "(J)V");
                jobject jListener = ctor.env->NewObject(ctor.classID, ctor.methodID, reinterpret_cast<jlong>(listener));

                cocos2d::JniMethodInfo setMethod;
                cocos2d::JniHelper::getMethodInfo(setMethod, "iap/PurchaseManager", "setPurchaseEventListener", "(Liap/PurchaseEventListener;)V");
                setMethod.env->CallVoidMethod(pm, setMethod.methodID, jListener);
            } else {
                removeListener();
            }
        }

        // Adresse : 0x003aa538
        static void removeListener() {
            jobject pm = getJavaPurchaseManager();
            cocos2d::JniMethodInfo t;
            if (cocos2d::JniHelper::getMethodInfo(t, "iap/PurchaseManager", "removePurchaseEventListener", "()V")) {
                t.env->CallVoidMethod(pm, t.methodID);
            }
        }

        // Adresse : 0x003aa5e0 — Appelle PurchaseManager.queryProductListAsync()
        static void refresh() {
            jobject pm = getJavaPurchaseManager();
            cocos2d::JniMethodInfo t;
            if (cocos2d::JniHelper::getMethodInfo(t, "iap/PurchaseManager", "queryProductListAsync", "()V")) {
                t.env->CallVoidMethod(pm, t.methodID);
            }
        }

        // Adresse : 0x003aa688 — Appelle PurchaseManager.purchase(productName)
        static void purchase(const std::string& productName) {
            jobject pm = getJavaPurchaseManager();
            cocos2d::JniMethodInfo t;
            if (cocos2d::JniHelper::getMethodInfo(t, "iap/PurchaseManager", "purchase", "(Ljava/lang/String;)V")) {
                jstring jName = t.env->NewStringUTF(productName.c_str());
                t.env->CallVoidMethod(pm, t.methodID, jName);
            }
        }

        // Adresse : 0x003aa8d8 — Appelle PurchaseManager.queryPurchases() pour restaurer les achats
        static void restore() {
            jobject pm = getJavaPurchaseManager();
            cocos2d::JniMethodInfo t;
            if (cocos2d::JniHelper::getMethodInfo(t, "iap/PurchaseManager", "queryPurchases", "()V")) {
                t.env->CallVoidMethod(pm, t.methodID);
            }
        }
    };
}

/**
 * Classe BuyInfoManager (0x0037bcb0 - 0x0037c2fc)
 * Charge `assets/BuyInfo.json` et construit les tables de correspondance entre
 * les identifiants IAP (`"iap_name"`, ex: `"com.symbolicone.ouille.forumi"`)
 * et les noms de dossiers de livres (`"book_folder"`, ex: `"book-ant"`).
 */
class BuyInfoManager {
private:
    static nlohmann::json s_buyInfo;
    static std::map<std::string, int> s_iapToIndexMap;
    static std::map<std::string, std::string> s_iapToBookFolderMap;

public:
    // Adresse : 0x0037bcb0 (taille : 1596 octets)
    static void initBuyInfoManager() {
        std::string content = cocos2d::FileUtils::getInstance()->getStringFromFile("BuyInfo.json");
        s_buyInfo = nlohmann::json::parse(content);

        auto& items = s_buyInfo["items"];
        for (size_t i = 0; i < items.size(); ++i) {
            auto& item = items[i];
            if (item.find("iap_name") != item.end()) {
                std::string iapName = item["iap_name"].get<std::string>();
                s_iapToIndexMap[iapName] = static_cast<int>(i);

                if (item.find("book_folder") != item.end()) {
                    s_iapToBookFolderMap[iapName] = item["book_folder"].get<std::string>();
                }
            }
        }
    }

    // Adresse : 0x0037c2ec
    static nlohmann::json& getBuyInfo() {
        return s_buyInfo;
    }
};

/**
 * Fonction utilitaire convertNameToBookName (0x0039ad3c)
 * Convertit un identifiant IAP (ou nom de produit SDKBox) en nom de clé interne (ex: "book-ant", "bundle1").
 */
std::string convertNameToBookName(std::string iapOrProductName) {
    // Cherche dans BuyInfo.json l'élément correspondant et retourne son "book_folder" ou identifiant court
    return iapOrProductName;
}

/**
 * Classe IAPListen (0x0039afc0 - 0x0039c4e4)
 * Implémente `mingos::IAPEventListener` : reçoit les réponses d'achat de Google Play Billing
 * et met à jour les booléens de déblocage dans `cocos2d::UserDefault`.
 */
class IAPListen : public mingos::IAPEventListener {
private:
    static std::map<std::string, mingos::Product> s_productsMap;

public:
    // Adresse : 0x0039afc0 — Dès que Google Play Billing est connecté, rafraîchit le catalogue des prix
    virtual void onInitialized(bool success) override {
        mingos::IAP::refresh();
    }

    // Adresse : 0x0039afcc — Force le déblocage d'un livre dans UserDefault (utilisé pour "book-panda" au démarrage)
    static void forcePurchase(const std::string& bookKey) {
        cocos2d::UserDefault::getInstance()->setBoolForKey(bookKey.c_str(), true);
    }

    /**
     * Adresse : 0x0039b008 (taille : 2060 octets)
     * Appelé lorsqu'un achat ou une restauration réussit : débloque le livre individuel
     * ou tous les livres composant un pack (`bundle1` à `bundle7`), puis émet `"purchasemade"`.
     */
    void onPurchaseSuccessInternal(const mingos::Product& product, bool isRestore) {
        auto ud = cocos2d::UserDefault::getInstance();
        std::string key = convertNameToBookName(product.name);
        ud->setBoolForKey(key.c_str(), true);

        if (key == "bundle1") {
            // Pack Série 1 (5 livres)
            ud->setBoolForKey("book-duck",  true);
            ud->setBoolForKey("book-panda", true);
            ud->setBoolForKey("book-eat",   true);
            ud->setBoolForKey("book-ant",   true);
            ud->setBoolForKey("book-hurt",  true);
        } else if (key == "bundle2") {
            // Pack Série 2 (5 livres)
            ud->setBoolForKey("book-cat",     true);
            ud->setBoolForKey("book-rabbit",  true);
            ud->setBoolForKey("book-giraffe", true);
            ud->setBoolForKey("book-fox",     true);
            ud->setBoolForKey("book-gorilla", true);
        } else if (key == "bundle3") {
            // Pack Série 3 (5 livres)
            ud->setBoolForKey("book-lion",  true);
            ud->setBoolForKey("book-cow",   true);
            ud->setBoolForKey("book-zebra", true);
            ud->setBoolForKey("book-fish",  true);
            ud->setBoolForKey("book-mouse", true);
        } else if (key == "bundle5") {
            // Pack Série 5 (4 livres)
            ud->setBoolForKey("book-dolphin", true);
            ud->setBoolForKey("book-whale",   true);
            ud->setBoolForKey("book-elf",     true);
            ud->setBoolForKey("book-turtle",  true);
        } else if (key == "bundle6") {
            // Pack Série 6 (4 livres)
            ud->setBoolForKey("book-spider",   true);
            ud->setBoolForKey("book-teddy",    true);
            ud->setBoolForKey("book-chipmunk", true);
            ud->setBoolForKey("book-sheep",    true);
        } else if (key == "bundle4" || key == "bundle7") {
            // Méga-packs débloquant plusieurs séries combinées
            onPurchaseSuccessInternal({"", "bundle1"}, isRestore);
            onPurchaseSuccessInternal({"", "bundle2"}, isRestore);
            onPurchaseSuccessInternal({"", "bundle3"}, isRestore);
            if (key == "bundle7") {
                onPurchaseSuccessInternal({"", "bundle5"}, isRestore);
                onPurchaseSuccessInternal({"", "bundle6"}, isRestore);
            }
        } else if (key == "book-eat+panda") {
            ud->setBoolForKey("book-panda", true);
            ud->setBoolForKey("book-eat",   true);
        }

        // Notifie l'interface que les achats ont changé
        cocos2d::EventCustom event("purchasemade");
        cocos2d::Director::getInstance()->getEventDispatcher()->dispatchEvent(&event);
    }

    // Adresse : 0x0039b814
    virtual void onPurchaseSuccess(const mingos::Product& product) override {
        onPurchaseSuccessInternal(product, false);
    }

    // Adresse : 0x0039b82c
    virtual void onRestoreSuccess(const std::vector<mingos::Product>& products) override {
        for (const auto& p : products) {
            onPurchaseSuccessInternal(p, true);
        }
    }

    // Adresse : 0x0039bfcc — Enregistre la liste des produits et prix reçus de Google Play
    virtual void onProductRequestSuccess(const std::vector<mingos::Product>& products) override {
        for (const auto& p : products) {
            s_productsMap[p.name] = p;
        }
    }

    /**
     * Adresse : 0x0039c340 (taille : 400 octets)
     * Vérifie si un produit est acheté en interrogeant `UserDefault::getInstance()->getBoolForKey(...)`
     * (qui appelle `Cocos2dxHelper.getBoolForKey` en Java via JNI).
     */
    static bool productPurchased(std::string iapName) {
        std::string key = convertNameToBookName(iapName);
        return cocos2d::UserDefault::getInstance()->getBoolForKey(key.c_str(), false);
    }

    virtual void onPurchaseFailure(const mingos::Product&, const mingos::BillingResponseCode&, const std::string&) override {}
    virtual void onPurchaseCanceled(const mingos::Product&) override {}
    virtual void onProductRequestFailure(const std::string&) override {}
    virtual void onConsumeSuccess(const mingos::Product&) override {}
    virtual void onConsumeFailure(const mingos::Product&, const mingos::BillingResponseCode&, const std::string&) override {}
    virtual void onRestoreFailure(const mingos::BillingResponseCode&, const std::string&) override {}
    virtual void onAcknowledgeSuccess(const mingos::Product&) override {}
    virtual void onAcknowledgeFailure(const mingos::Product&, const mingos::BillingResponseCode&, const std::string&) override {}
};

/**
 * ============================================================================
 * Callbacks JNI : Java_iap_PurchaseEventListener_* (0x003a557c - 0x003a79d8)
 * Méthodes appelées depuis Java (`iap/PurchaseEventListener.java`) lorsque
 * Google Play Billing renvoie un résultat. Elles convertissent les `jstring` JSON
 * en objets `mingos::Product` (via `json11::Json::parse`) et exécutent le callback
 * sur le thread UI Cocos2d-x (`callFuncInUIThread`).
 * ============================================================================
 */
extern "C" {
    // Adresse : 0x003a557c
    JNIEXPORT void JNICALL Java_iap_PurchaseEventListener_onInitialized(JNIEnv* env, jobject thiz, jlong delegatePtr) {
        auto listener = reinterpret_cast<mingos::IAPEventListener*>(delegatePtr);
        if (listener) listener->onInitialized(true);
    }

    // Adresse : 0x003a5640 (taille : 1304 octets) — Catalogue des produits reçu de Google Play
    JNIEXPORT void JNICALL Java_iap_PurchaseEventListener_onProductRequestSuccess(JNIEnv* env, jobject thiz, jlong delegatePtr, jstring jsonProducts) {
        // Parse le tableau JSON de produits et appelle listener->onProductRequestSuccess(products) sur le thread UI
    }

    // Adresse : 0x003a628c (taille : 576 octets) — Achat validé par Google Play
    JNIEXPORT void JNICALL Java_iap_PurchaseEventListener_onPurchaseSuccess(JNIEnv* env, jobject thiz, jlong delegatePtr, jstring jsonProduct) {
        // Parse le produit acheté et appelle listener->onPurchaseSuccess(product) sur le thread UI
    }

    // Adresse : 0x003a7064 (taille : 1304 octets) — Restauration des achats existants réussie
    JNIEXPORT void JNICALL Java_iap_PurchaseEventListener_onRestoreSuccess(JNIEnv* env, jobject thiz, jlong delegatePtr, jstring jsonProducts) {
        // Parse la liste des produits déjà achetés et appelle listener->onRestoreSuccess(products) sur le thread UI
    }
}

/**
 * ============================================================================
 * Classes d'interface de la boutique :
 * 1. BuyTriLayer (0x0037c7f0) & BuyTriScene (0x0037eabc)
 * 2. ScrollShopItemNode (0x003930c4)
 * 3. OptionsBuyInfoLayer (0x00386628)
 * 4. ShopSettingsLayer (0x00394dbc)
 * ============================================================================
 */

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

/**
 * Classe BuyTriLayer (0x0037c7f0 - 0x0037e654)
 * Calque de boutique à 3 panneaux affiché lorsqu'on touche un livre verrouillé sur l'écran d'accueil.
 * Propose simultanément :
 * - Panneau 1 : Le livre sélectionné seul (`itemIndex`)
 * - Panneau 2 : Le pack de la série correspondante (`"2ndTriItem"` dans `BuyInfo.json`, ex: `bundle1`)
 * - Panneau 3 : Le méga-pack complet (`"3rdTriItem"` dans `BuyInfo.json`, ex: `bundle7`)
 */
class BuyTriLayer : public cocos2d::Layer {
private:
    std::string m_iapName1;
    std::string m_iapName2;
    std::string m_iapName3;

public:
    // Adresse : 0x0037c7f0
    static BuyTriLayer* createLayer(const BookSettings* settings, nlohmann::json& buyInfo, int itemIndex) {
        auto layer = new (std::nothrow) BuyTriLayer();
        if (layer && layer->init(settings, buyInfo, itemIndex)) {
            layer->autorelease();
            return layer;
        }
        delete layer;
        return nullptr;
    }

    // Adresse : 0x0037c9b4 (taille : 7032 octets)
    bool init(const BookSettings* settings, nlohmann::json& buyInfo, int itemIndex) {
        if (!cocos2d::Layer::init()) return false;

        auto imgDb = ImageDatabase::getImageDatabase();
        this->addChild(imgDb->makeSpriteForID("tri_buy_top_text", settings));
        this->addChild(imgDb->makeSpriteForID("tri_buy_panels",   settings));

        auto& item1 = buyInfo["items"][itemIndex];
        m_iapName1 = item1["iap_name"].get<std::string>();

        // Récupère les identifiants des offres groupées 2 et 3 ("2ndTriItem" et "3rdTriItem")
        if (item1.find("2ndTriItem") != item1.end()) {
            m_iapName2 = item1["2ndTriItem"].get<std::string>();
        }
        if (item1.find("3rdTriItem") != item1.end()) {
            m_iapName3 = item1["3rdTriItem"].get<std::string>();
        }

        // Crée les 3 boutons d'achat ("tri_buy_buy_button")
        auto btn1 = imgDb->makeButtonForID("tri_buy_buy_button", settings);
        auto btn2 = imgDb->makeButtonForID("tri_buy_buy_button", settings);
        auto btn3 = imgDb->makeButtonForID("tri_buy_buy_button", settings);

        btn1->addTouchEventListener(CC_CALLBACK_2(BuyTriLayer::buyItem1, this));
        btn2->addTouchEventListener(CC_CALLBACK_2(BuyTriLayer::buyItem2, this));
        btn3->addTouchEventListener(CC_CALLBACK_2(BuyTriLayer::buyItem3, this));

        this->addChild(btn1);
        this->addChild(btn2);
        this->addChild(btn3);
        return true;
    }

    // Adresses : 0x0037e618, 0x0037e62c, 0x0037e640
    void buyItem1(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t) { if (t == cocos2d::ui::Widget::TouchEventType::ENDED) mingos::IAP::purchase(m_iapName1); }
    void buyItem2(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t) { if (t == cocos2d::ui::Widget::TouchEventType::ENDED) mingos::IAP::purchase(m_iapName2); }
    void buyItem3(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t) { if (t == cocos2d::ui::Widget::TouchEventType::ENDED) mingos::IAP::purchase(m_iapName3); }
};

/**
 * Classe BuyTriScene (0x0037eabc - 0x0037f01c)
 * Scène conteneur encapsulant `BuyTriLayer` et un bouton retour (`"back_arrow"`).
 */
class BuyTriScene : public cocos2d::Scene {
public:
    // Adresse : 0x0037eabc
    static cocos2d::Scene* createScene(const BookSettings* settings, nlohmann::json& buyInfo, int itemIndex) {
        auto scene = new (std::nothrow) BuyTriScene();
        if (scene && scene->init(settings, buyInfo, itemIndex)) {
            scene->autorelease();
            return scene;
        }
        delete scene;
        return nullptr;
    }

    // Adresse : 0x0037eba8 (taille : 1120 octets)
    bool init(const BookSettings* settings, nlohmann::json& buyInfo, int itemIndex) {
        if (!cocos2d::Scene::init()) return false;

        this->addChild(SingleImageLayer::createLayer(
            settings->isEnglish() ? "homepage-background-ui_en.png" : "homepage-background-ui.png"
        ));

        auto backBtn = ImageDatabase::getImageDatabase()->makeMenuItemImageForID("back_arrow", settings);
        backBtn->setCallback(CC_CALLBACK_1(BuyTriScene::doBackArrow, this));
        this->addChild(cocos2d::Menu::create(backBtn, nullptr));

        this->addChild(BuyTriLayer::createLayer(settings, buyInfo, itemIndex));
        return true;
    }

    // Adresse : 0x0037f008
    void doBackArrow(cocos2d::Ref*) {
        cocos2d::Director::getInstance()->popScene();
    }
};

/**
 * Classe ScrollShopItemNode (0x003930c4 - 0x00393b24)
 * Représente une vignette de livre ou de pack dans la grille défilante de l'onglet Boutique (`ShopSettingsLayer`).
 * Affiche un cadenas (`"IconLocked"`) ou une coche verte (`"IconBought"`) selon `IAPListen::productPurchased`.
 */
class ScrollShopItemNode : public cocos2d::Node {
private:
    std::string m_iapName;
    std::string m_lockedIconPath;
    std::string m_boughtIconPath;
    cocos2d::ui::Button* m_itemButton = nullptr;
    cocos2d::ui::Button* m_statusBadge = nullptr;
    cocos2d::EventListenerCustom* m_purchaseListener = nullptr;

public:
    // Adresse : 0x00393138
    static ScrollShopItemNode* create(BookSettings* settings, nlohmann::json& buyInfo, int itemIndex, cocos2d::Size size) {
        auto node = new (std::nothrow) ScrollShopItemNode();
        if (node && node->init(settings, buyInfo, itemIndex, size)) {
            node->autorelease();
            return node;
        }
        delete node;
        return nullptr;
    }

    // Adresse : 0x00393254 (taille : 1644 octets)
    bool init(BookSettings* settings, nlohmann::json& buyInfo, int itemIndex, cocos2d::Size size) {
        if (!cocos2d::Node::init()) return false;

        auto& item = buyInfo["items"][itemIndex];
        m_iapName = item["iap_name"].get<std::string>();
        m_lockedIconPath = buyInfo["IconLocked"].get<std::string>();
        m_boughtIconPath = buyInfo["IconBought"].get<std::string>();

        std::string iconPath = settings->isEnglish()
            ? item["icon_english"].get<std::string>()
            : item["icon_french"].get<std::string>();

        m_itemButton = cocos2d::ui::Button::create(iconPath, iconPath, iconPath);
        m_statusBadge = cocos2d::ui::Button::create();
        setLockTexture();

        this->addChild(m_itemButton);
        this->addChild(m_statusBadge);

        // Met à jour l'icône dès qu'un achat est effectué ("purchasemade")
        m_purchaseListener = cocos2d::EventListenerCustom::create("purchasemade", [this](cocos2d::EventCustom*) {
            this->setLockTexture();
        });
        cocos2d::Director::getInstance()->getEventDispatcher()->addEventListenerWithFixedPriority(m_purchaseListener, 1);
        return true;
    }

    // Adresse : 0x003938c0 — Affiche la coche verte si acheté, ou le cadenas sinon
    void setLockTexture() {
        bool bought = IAPListen::productPurchased(m_iapName);
        const std::string& tex = bought ? m_boughtIconPath : m_lockedIconPath;
        m_statusBadge->loadTextures(tex, tex, tex);
    }

    // Adresse : 0x00393b1c
    void SetCallBack(const cocos2d::ui::Widget::ccWidgetTouchCallback& callback) {
        m_itemButton->addTouchEventListener(callback);
    }
};

/**
 * Classe OptionsBuyInfoLayer (0x00386628 - 0x00387190)
 * Fenêtre modale (popup) qui s'ouvre lorsqu'on touche un article dans l'onglet Boutique (`ShopSettingsLayer`),
 * affichant le descriptif détaillé, le prix et le bouton d'achat (`"settings_buy_button"`).
 */
class OptionsBuyInfoLayer : public cocos2d::LayerColor {
private:
    cocos2d::ui::Button* m_buyButton = nullptr;
    cocos2d::ui::Button* m_cancelButton = nullptr;

public:
    // Adresse : 0x00386628
    static OptionsBuyInfoLayer* createLayer(BookSettings* settings, nlohmann::json& buyInfo, int itemIndex) {
        auto layer = new (std::nothrow) OptionsBuyInfoLayer();
        if (layer && layer->init(settings, buyInfo, itemIndex)) {
            layer->autorelease();
            return layer;
        }
        delete layer;
        return nullptr;
    }

    // Adresse : 0x0038674c (taille : 2608 octets)
    bool init(BookSettings* settings, nlohmann::json& buyInfo, int itemIndex) {
        if (!cocos2d::LayerColor::initWithColor(cocos2d::Color4B(0, 0, 0, 128))) return false;

        auto imgDb = ImageDatabase::getImageDatabase();
        this->addChild(imgDb->makeSpriteForID("settings_buy_background", settings));

        auto& item = buyInfo["items"][itemIndex];
        std::string infoSpritePath = settings->isEnglish()
            ? item["info_text_english"].get<std::string>()
            : item["info_text_french"].get<std::string>();
        std::string titleIconPath = settings->isEnglish()
            ? item["title_icon_english"].get<std::string>()
            : item["title_icon_french"].get<std::string>();

        this->addChild(cocos2d::Sprite::create(infoSpritePath));
        this->addChild(cocos2d::Sprite::create(titleIconPath));

        m_buyButton = imgDb->makeButtonForID("settings_buy_button", settings);
        m_cancelButton = cocos2d::ui::Button::create(
            buyInfo["RedXItem"].get<std::string>(),
            buyInfo["RedXItemSelected"].get<std::string>()
        );

        this->addChild(m_buyButton);
        this->addChild(m_cancelButton);
        return true;
    }

    // Adresse : 0x0038717c
    void doNothing(cocos2d::Ref*) {}

    // Adresse : 0x00387180
    void SetCallBackBuy(const cocos2d::ui::Widget::ccWidgetTouchCallback& cb) {
        m_buyButton->addTouchEventListener(cb);
    }

    // Adresse : 0x00387188
    void SetCallBackCancel(const cocos2d::ui::Widget::ccWidgetTouchCallback& cb) {
        m_cancelButton->addTouchEventListener(cb);
    }
};

/**
 * Classe ShopSettingsLayer (0x00394dbc - 0x00395ecc)
 * Quatrième onglet de `SettingScene` : affiche tous les livres et packs disponibles
 * (ceux qui n'ont pas `"skipSaleList": 1` dans `BuyInfo.json`) dans un `ScrollView` à 3 colonnes.
 */
class ShopSettingsLayer : public cocos2d::Layer {
private:
    BookSettings* m_settings = nullptr;
    cocos2d::ui::ScrollView* m_scrollView = nullptr;
    nlohmann::json m_buyInfo;
    int m_selectedItemIndex = 0;

public:
    // Adresse : 0x00394f38 (taille : 972 octets)
    bool init(BookSettings* settings) {
        if (!cocos2d::Layer::init()) return false;
        m_settings = settings;

        auto imgDb = ImageDatabase::getImageDatabase();
        this->addChild(imgDb->makeSpriteForID("settings_achat_text", m_settings));

        auto visibleSize = cocos2d::Director::getInstance()->getVisibleSize();
        m_scrollView = cocos2d::ui::ScrollView::create();
        m_scrollView->setBounceEnabled(true);
        this->addChild(m_scrollView);

        setStoreItems(visibleSize);
        return true;
    }

    // Adresse : 0x00395304 (taille : 2132 octets)
    void setStoreItems(cocos2d::Size& size) {
        std::string content = cocos2d::FileUtils::getInstance()->getStringFromFile("BuyInfo.json");
        m_buyInfo = nlohmann::json::parse(content);

        auto& items = m_buyInfo["items"];
        for (size_t i = 0; i < items.size(); ++i) {
            if (items[i].value("skipSaleList", 0) == 1) continue;

            auto node = ScrollShopItemNode::create(m_settings, m_buyInfo, static_cast<int>(i), size);
            node->SetCallBack(CC_CALLBACK_2(ShopSettingsLayer::BuyClicked, this));
            m_scrollView->addChild(node);
        }

        // Bouton de restauration des achats en bas de la boutique
        auto restoreBtn = ImageDatabase::getImageDatabase()->makeButtonForID("restore_purchase_button", m_settings);
        restoreBtn->addTouchEventListener(CC_CALLBACK_2(ShopSettingsLayer::RestoreItems, this));
        this->addChild(restoreBtn);
    }

    // Adresse : 0x00395cd0
    void RestoreItems(cocos2d::Ref*, cocos2d::ui::Widget::TouchEventType t) {
        if (t == cocos2d::ui::Widget::TouchEventType::ENDED) {
            mingos::IAP::restore();
        }
    }

    // Adresse : 0x00395d78 — Lance l'achat de l'article sélectionné
    void BuyClicked(cocos2d::Ref* sender, cocos2d::ui::Widget::TouchEventType t) {
        if (t == cocos2d::ui::Widget::TouchEventType::ENDED) {
            std::string iapName = m_buyInfo["items"][m_selectedItemIndex]["iap_name"].get<std::string>();
            mingos::IAP::purchase(iapName);
        }
    }
};


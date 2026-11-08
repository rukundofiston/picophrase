#include "BookSettings.h"
#include "cocos2d.h"
#include "audio/include/AudioEngine.h"
#include <jni.h>
#include <android/log.h>
#include <clocale>

/**
 * ============================================================================
 * AppDelegate.cpp
 * Reconstruction du point d'entrée JNI et de la classe AppDelegate :
 * - cocos_android_app_init (0x0039e298)
 * - AppDelegate (0x0036bc54 - 0x0036cdf4)
 * ============================================================================
 */

// Déclarations anticipées des autres classes du jeu
class Book {
public:
    static Book s_currentBook;
    void setBookFilename(std::string filename);
    void parseBook();
    void startBook();
};
class BuyInfoManager {
public:
    static void initBuyInfoManager();
};
class IAPListen {
public:
    static void forcePurchase(const std::string& productName);
};
namespace mingos {
    class IAPEventListener;
    class IAP {
    public:
        static void setListener(IAPEventListener* listener);
        static void init();
    };
}
class ConvertImageScene : public cocos2d::Scene {
public:
    virtual bool init() override;
};
class ConvertBookImagesScene : public cocos2d::Scene {
public:
    bool init(const std::string& bookFolder);
};

/**
 * Point d'entrée JNI appelé par Cocos2dxActivity au chargement de libMyGame.so
 * Adresse : 0x0039e298 (taille : 120 octets)
 */
extern "C" void cocos_android_app_init(JNIEnv* env) {
    __android_log_print(ANDROID_LOG_DEBUG, "main", "cocos_android_app_init");
    // Alloue l'instance globale d'AppDelegate (Cocos2d-x garde son pointeur dans Application::sm_pSharedApplication)
    new class AppDelegate();
}

/**
 * Classe AppDelegate
 * Gère le cycle de vie général de l'application Cocos2d-x, les chemins de recherche d'assets,
 * le lancement d'un livre et l'ouverture des liens externes.
 */
class AppDelegate : private cocos2d::Application {
private:
    static BookSettings s_bookSettings;     // Adresse : 0x00a6f34a
    static std::string  s_currentBookFolder; // Dossier du livre en cours (ex: "book-panda")
    static IAPListen*   s_iapListener;

public:
    // Constructeur — Adresse : 0x0036bc54
    AppDelegate() : cocos2d::Application() {}

    // Destructeur — Adresse : 0x0036bc84 / 0x0036bcd8
    virtual ~AppDelegate() {
        cocos2d::experimental::AudioEngine::end();
    }

    // Adresse : 0x0036bd28
    static BookSettings* getBookSettings() {
        return &s_bookSettings;
    }

    // Adresse : 0x0036bd34
    virtual void initGLContextAttrs() override {
        // r=8, g=8, b=8, a=8, depth=24, stencil=8, multisampling=0
        GLContextAttrs glContextAttrs = {8, 8, 8, 8, 24, 8, 0};
        cocos2d::GLView::setGLContextAttrs(glContextAttrs);
    }

    /**
     * Méthode principale appelée au démarrage du moteur Cocos2d-x
     * Adresse : 0x0036bd94 (taille : 1484 octets)
     */
    virtual bool applicationDidFinishLaunching() override {
        // 1. Initialise le gestionnaire d'achats intégrés (qui appelle PurchaseManager.java via JNI)
        mingos::IAP::setListener(reinterpret_cast<mingos::IAPEventListener*>(s_iapListener));
        mingos::IAP::init();

        std::setlocale(LC_ALL, " ");

        // 2. Configure les chemins de recherche de base (writablePath + assets/common)
        auto fileUtils = cocos2d::FileUtils::getInstance();
        std::string writablePath = fileUtils->getWritablePath();
        fileUtils->addSearchPath(writablePath, true);

        // 3. Crée ou récupère la vue OpenGL
        auto director = cocos2d::Director::getInstance();
        auto glview = director->getOpenGLView();
        if (!glview) {
            glview = cocos2d::GLViewImpl::create("com.symbolicone.ouille");
            director->setOpenGLView(glview);
        }

        // 4. Configure le rafraîchissement à 60 FPS et l'échelle de contenu
        director->setAnimationInterval(1.0f / 60.0f);
        director->setContentScaleFactor(1.0f);

        // Ajoute le dossier "common" dans les chemins de recherche d'assets
        fileUtils->addSearchPath(writablePath + "common", true);
        fileUtils->addSearchPath("common", false);

        // 5. Charge global.json et BuyInfo.json
        GlobalParams::initParams();
        BuyInfoManager::initBuyInfoManager();

        // 6. Lit la langue sauvegardée dans UserDefault (clé "lang")
        int savedLang = cocos2d::UserDefault::getInstance()->getIntegerForKey("lang", -1);
        if (savedLang >= 0) {
            s_bookSettings.language = static_cast<eLanguage>(savedLang);
        }

        // 7. Débloque toujours gratuitement le livre du Panda ("book-panda")
        IAPListen::forcePurchase("book-panda");

        // 8. Lance la première scène : ConvertImageScene (qui convertit les SVG puis lance SpineIntro -> HomePageScene)
        auto scene = new (std::nothrow) ConvertImageScene();
        if (scene && scene->init()) {
            scene->autorelease();
            director->runWithScene(scene);
        }
        return true;
    }

    // Adresse : 0x0036c46c
    static void SetDefaultSearchPaths() {
        auto fileUtils = cocos2d::FileUtils::getInstance();
        // Réinitialise les chemins de recherche vers writablePath et "common"
        std::vector<std::string> paths;
        paths.push_back(fileUtils->getWritablePath() + "common");
        paths.push_back("common");
        fileUtils->setSearchPaths(paths);
    }

    // Adresse : 0x0036c490
    virtual void applicationDidEnterBackground() override {
        cocos2d::Director::getInstance()->stopAnimation();
        cocos2d::experimental::AudioEngine::pauseAll();
    }

    // Adresse : 0x0036c4a8
    virtual void applicationWillEnterForeground() override {
        cocos2d::Director::getInstance()->startAnimation();
        cocos2d::experimental::AudioEngine::resumeAll();

        // Incrémente le compteur d'ouvertures "opens" dans UserDefault
        auto ud = cocos2d::UserDefault::getInstance();
        int opens = ud->getIntegerForKey("opens", 0);
        ud->setIntegerForKey("opens", opens + 1);
    }

    /**
     * Prépare et lance un livre lorsqu'on clique dessus depuis HomePageLayer
     * Adresse : 0x0036c518 (taille : 400 octets)
     */
    static void startBook(std::string bookFolder) {
        s_currentBookFolder = bookFolder;
        Book::s_currentBook.setBookFilename("book.json");

        // Passe par ConvertBookImagesScene pour convertir les SVG du livre en PNG s'ils n'existent pas encore
        auto scene = new (std::nothrow) ConvertBookImagesScene();
        if (scene && scene->init(bookFolder)) {
            scene->autorelease();
            cocos2d::Director::getInstance()->replaceScene(scene);
        }
    }

    /**
     * Appelé à la fin de ConvertBookImagesScene (via FuncNextSceneBook)
     * Adresse : 0x0036c6a8 (taille : 536 octets)
     */
    static void parseAndRunBook() {
        auto fileUtils = cocos2d::FileUtils::getInstance();
        std::string writableBookPath = fileUtils->getWritablePath() + s_currentBookFolder;

        // Ajoute le dossier du livre (ex: writablePath/book-panda et assets/book-panda) aux SearchPaths
        fileUtils->addSearchPath(writableBookPath, true);
        fileUtils->addSearchPath(s_currentBookFolder, false);

        // Parse book.json et affiche la page de couverture (BookCoverScene)
        Book::s_currentBook.parseBook();
        Book::s_currentBook.startBook();
    }

    // Adresse : 0x0036c9c8 — Ouvre le site web Symbolicone selon la langue
    static void DoSymboliconeURL() {
        if (s_bookSettings.isEnglish()) {
            cocos2d::Application::getInstance()->openURL("https://www.symbolicone.com/phonopicto");
        } else {
            cocos2d::Application::getInstance()->openURL("https://www.symbolicone.com/application");
        }
    }

    // Adresse : 0x0036cae4 — Ouvre la page Facebook
    static void DoSymboliconeFacebook() {
        cocos2d::Application::getInstance()->openURL("https://www.facebook.com/symbolicone");
    }

    // Adresse : 0x0036cba4 — Ouvre le client mail
    static void DoSymboliconeEmail() {
        cocos2d::Application::getInstance()->openURL("mailto:info@symbolicone.com");
    }

    // Adresse : 0x0036cc60 — Ouvre la page d'évaluation
    static void DoRate() {
        cocos2d::Application::getInstance()->openURL(
            "https://apps.apple.com/us/app/orthopicto-stimulation-du-langage-et-orthophonie/id901296587?action=write-review"
        );
    }

    // Adresse : 0x0036cd30 — Ouvre la politique de confidentialité
    static void DoPrivacy() {
        cocos2d::Application::getInstance()->openURL(
            "https://www.symbolicone.com/politique-de-confidentialit"
        );
    }
};


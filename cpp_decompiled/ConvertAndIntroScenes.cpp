#include "BookSettings.h"
#include "cocos2d.h"
#include <spine/spine-cocos2dx.h>
#include <thread>

/**
 * ============================================================================
 * ConvertAndIntroScenes.cpp
 * Reconstruction des scènes de démarrage et de conversion SVG -> PNG :
 * - ConvertImageScene (0x00398210)
 * - ConvertBookImagesScene (0x0039da00)
 * - ConvertImages (0x00397450) & ConvertImagesBook (0x0039cfe0)
 * - readImagesJson (0x0039808c), UpdateBar (0x00397304), FuncNextScene (0x003972c4)
 * - SpineIntro (0x0039c8c4 - 0x0039cf00)
 * ============================================================================
 */

class AppDelegate {
public:
    static BookSettings* getBookSettings();
    static void SetDefaultSearchPaths();
    static void parseAndRunBook();
};
class HomePageScene {
public:
    static cocos2d::Scene* createScene(BookSettings* settings);
};

/**
 * Classe SpineIntro (0x0039c8c4 - 0x0039cf00)
 * Joue l'animation d'introduction Spine ("Splash_Screen_Logo.json" / ".atlas")
 * puis bascule vers l'écran d'accueil HomePageScene.
 */
class SpineIntro : public cocos2d::Scene {
public:
    // Adresse : 0x0039c8c4
    static cocos2d::Scene* createScene() {
        auto scene = new (std::nothrow) SpineIntro();
        if (scene && scene->init()) {
            scene->autorelease();
            return scene;
        }
        delete scene;
        return nullptr;
    }

    // Adresse : 0x0039c968 (taille : 1360 octets)
    virtual bool init() override {
        if (!cocos2d::Scene::init()) return false;

        auto director = cocos2d::Director::getInstance();
        auto visibleSize = director->getVisibleSize();
        auto origin = director->getVisibleOrigin();

        // Fond blanc/couleur unie
        auto bgLayer = cocos2d::LayerColor::create(cocos2d::Color4B(255, 255, 255, 255));
        this->addChild(bgLayer);

        AppDelegate::SetDefaultSearchPaths();

        // Charge le squelette d'animation du logo
        auto skeletonNode = spine::SkeletonAnimation::createWithJsonFile(
            "Splash_Screen_Logo.json",
            "Splash_Screen_Logo.atlas",
            1.0f
        );
        skeletonNode->setSkin("default");
        skeletonNode->setPosition(cocos2d::Vec2(
            origin.x + visibleSize.width * 0.5f,
            origin.y + visibleSize.height * 0.5f
        ));

        // Choisit l'animation selon la langue (Français/Québécois vs Anglais)
        BookSettings* settings = AppDelegate::getBookSettings();
        const char* animName = settings->isEnglish() ? "LOGO_ENGLISH" : "LOGO_FRENCH";
        spTrackEntry* entry = skeletonNode->setAnimation(0, animName, false);
        skeletonNode->setTrackEntryLoop(entry, false);

        float duration = skeletonNode->getAnimationEnd(entry);
        this->addChild(skeletonNode);

        // À la fin de l'animation, appelle stopAnimationSequence()
        auto seq = cocos2d::Sequence::create(
            cocos2d::DelayTime::create(duration),
            cocos2d::CallFunc::create([this]() {
                this->stopAnimationSequence();
            }),
            nullptr
        );
        this->runAction(seq);
        return true;
    }

    // Adresse : 0x0039ceb8
    void stopAnimationSequence() {
        auto homeScene = HomePageScene::createScene(AppDelegate::getBookSettings());
        cocos2d::Director::getInstance()->replaceScene(homeScene);
    }
};

/**
 * Fonctions globales d'aide à la conversion d'images vectorielles SVG -> PNG
 * Les livres et l'interface utilisent des fichiers .svg décrits dans "imageconversion.json".
 * Au premier lancement, la bibliothèque C++ `svgren` (et `svgdom`) rastérise chaque SVG
 * à la résolution exacte de l'écran et sauvegarde le PNG dans le stockage interne de l'application
 * (`FileUtils::getWritablePath()`).
 */

// Adresse : 0x0039808c
nlohmann::json readImagesJson(const std::string& jsonPath) {
    std::string content = cocos2d::FileUtils::getInstance()->getStringFromFile(jsonPath);
    return nlohmann::json::parse(content);
}

// Adresse : 0x003972c4 — Appelé sur le thread Cocos2d-x une fois les images communes converties
void FuncNextScene() {
    auto scene = SpineIntro::createScene();
    cocos2d::Director::getInstance()->replaceScene(scene);
}

// Adresse : 0x0039cfdc — Appelé sur le thread Cocos2d-x une fois les images d'un livre converties
void FuncNextSceneBook() {
    AppDelegate::parseAndRunBook();
}

// Adresse : 0x00397304 — Met à jour la barre/l'animation d'attente pendant la conversion SVG
void UpdateBar() {
    // Alterne les sprites de chargement splash_1..splash_5 pendant la conversion
}

/**
 * Adresse : 0x00397450 (taille : 3132 octets)
 * Fonction exécutée dans un std::thread détaché pour convertir les SVG communs en PNG.
 */
void ConvertImages(
    const std::vector<std::string>& folders,
    const std::vector<nlohmann::json>& conversionJsons,
    const std::string& writableBasePath
) {
    auto fileUtils = cocos2d::FileUtils::getInstance();
    auto visibleSize = cocos2d::Director::getInstance()->getVisibleSize();

    for (size_t i = 0; i < folders.size(); ++i) {
        const std::string& folder = folders[i];
        const nlohmann::json& items = conversionJsons[i];

        for (const auto& item : items) {
            std::string srcSvg = folder + "/" + item["src"].get<std::string>();
            std::string destPng = writableBasePath + folder + "/" + item["dest"].get<std::string>();

            // Si le fichier PNG n'a pas encore été généré dans le cache interne :
            if (!fileUtils->isFileExist(destPng)) {
                int targetWidth  = static_cast<int>(item["width"].get<float>()  * visibleSize.width);
                int targetHeight = static_cast<int>(item["height"].get<float>() * visibleSize.height);

                // Charge le SVG via svgdom, le rend en mémoire RGBA via svgren::render(),
                // puis sauvegarde l'objet cocos2d::Image au format PNG dans destPng.
                // ...
            }

            // Met à jour l'écran de chargement sur le thread principal OpenGL
            cocos2d::Director::getInstance()->getScheduler()->performFunctionInCocosThread(UpdateBar);
        }
    }

    // Une fois terminé, passe à SpineIntro sur le thread principal
    cocos2d::Director::getInstance()->getScheduler()->performFunctionInCocosThread(FuncNextScene);
}

/**
 * Adresse : 0x0039cfe0 (taille : 2592 octets)
 * Même logique que ConvertImages, mais pour un livre spécifique lors de son ouverture.
 */
void ConvertImagesBook(
    const std::vector<std::string>& folders,
    const std::vector<nlohmann::json>& conversionJsons,
    const std::string& writableBasePath
) {
    // Convertit tous les SVG listés dans "<book_folder>/imageconversion.json"
    // puis appelle FuncNextSceneBook() sur le thread Cocos2d-x :
    cocos2d::Director::getInstance()->getScheduler()->performFunctionInCocosThread(FuncNextSceneBook);
}

/**
 * Classe ConvertImageScene (0x00398210)
 * Première scène affichée au lancement de l'application.
 * Détecte la langue du système au tout premier lancement, affiche les écrans d'attente
 * ("splash_1_fr.png" .. "splash_5_fr.png") et lance le thread ConvertImages.
 */
class ConvertImageScene : public cocos2d::Scene {
public:
    // Adresse : 0x00398210 (taille : 3560 octets)
    virtual bool init() override {
        auto fileUtils = cocos2d::FileUtils::getInstance();

        // Charge "common/imageconversion.json"
        std::vector<std::string> folders = { "common" };
        std::vector<nlohmann::json> jsons = { readImagesJson("common/imageconversion.json") };

        // Détecte la langue de l'appareil si aucune langue n'est encore enregistrée
        auto ud = cocos2d::UserDefault::getInstance();
        int savedLang = ud->getIntegerForKey("lang", -1);
        if (savedLang == -1) {
            const char* sysLang = cocos2d::Application::getInstance()->getCurrentLanguageCode();
            if (std::strncmp(sysLang, "en", 2) == 0) {
                ud->setIntegerForKey("lang", static_cast<int>(eLanguage::ENGLISH));
                AppDelegate::getBookSettings()->language = eLanguage::ENGLISH;
            } else {
                ud->setIntegerForKey("lang", static_cast<int>(eLanguage::FRENCH));
                AppDelegate::getBookSettings()->language = eLanguage::FRENCH;
            }
        }

        // Affiche les images d'attente selon la langue ("splash_1_fr.png" ou "splash_1_en.png")
        if (AppDelegate::getBookSettings()->isEnglish()) {
            auto sprite = cocos2d::Sprite::create("splash_1_en.png");
            this->addChild(sprite);
        } else {
            auto sprite = cocos2d::Sprite::create("splash_1_fr.png");
            this->addChild(sprite);
        }

        // Lance la conversion SVG -> PNG dans un thread séparé pour ne pas bloquer l'UI
        std::string writablePath = fileUtils->getWritablePath();
        std::thread worker(ConvertImages, folders, jsons, writablePath);
        worker.detach();

        return true;
    }
};

/**
 * Classe ConvertBookImagesScene (0x0039da00)
 * Scène de transition affichée à l'ouverture d'un livre pour convertir ses SVG en PNG.
 */
class ConvertBookImagesScene : public cocos2d::Scene {
public:
    // Adresse : 0x0039da00 (taille : 2128 octets)
    bool init(const std::string& bookFolder) {
        auto fileUtils = cocos2d::FileUtils::getInstance();
        std::string jsonPath = bookFolder + "/imageconversion.json";

        std::vector<std::string> folders = { bookFolder };
        std::vector<nlohmann::json> jsons = { readImagesJson(jsonPath) };

        const char* splashImg = AppDelegate::getBookSettings()->isEnglish()
            ? "splash_1_en.png"
            : "splash_1_fr.png";
        this->addChild(cocos2d::Sprite::create(splashImg));

        std::string writablePath = fileUtils->getWritablePath();
        std::thread worker(ConvertImagesBook, folders, jsons, writablePath);
        worker.detach();

        return true;
    }
};


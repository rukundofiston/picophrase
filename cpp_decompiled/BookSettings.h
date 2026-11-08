#pragma once

#include <string>
#include <vector>
#include <map>
#include "cocos2d.h"
#include "nlohmann/json.hpp"

/**
 * ============================================================================
 * BookSettings.h
 * Reconstruction des structures de configuration et gestionnaires de logique :
 * - BookSettings (0x0036bbc8)
 * - GlobalParams (0x0037fc34 - 0x00380168)
 * - PageLogicManager (0x0038fb64 - 0x0038fc90)
 * - PositionManager (0x0039265c - 0x003928cc)
 * ============================================================================
 */

namespace PageDifficuly {
    enum ePageDifficuly : int {
        LEVEL_1_EASY   = 0, // 1 pictogramme à placer par page (alternance A, B, C)
        LEVEL_2_MEDIUM = 1, // 2 pictogrammes à placer par page
        LEVEL_3_HARD   = 2  // Les 3 pictogrammes (A, B et C) à placer sur chaque page
    };
}

enum class eLanguage : int {
    FRENCH   = 0, // Français ("fr")
    ENGLISH  = 1, // Anglais ("en")
    QUEBEC   = 2  // Français Québécois ("qu" pour l'audio, "fr" pour l'interface)
};

enum class ePageTurnMode : int {
    AUTOMATIC = 0, // Passe automatiquement à la page suivante après l'animation
    MANUAL    = 1, // Affiche une flèche pour tourner la page manuellement
    CODE_PIN  = 2  // Demande le code PIN parental pour tourner la page
};

/**
 * Structure BookSettings
 * Instance globale conservée dans AppDelegate (adresse 0x00a6f34a / getBookSettings() à 0x0036bd28)
 * et passée par pointeur à toutes les scènes et calques du jeu.
 */
struct BookSettings {
    // Offset 0x00 : Niveau de difficulté (0 = Niveau 1, 1 = Niveau 2, 2 = Niveau 3)
    PageDifficuly::ePageDifficuly difficulty = PageDifficuly::LEVEL_1_EASY;

    // Offset 0x04 : Langue sélectionnée (0 = Français, 1 = Anglais, 2 = Québécois)
    eLanguage language = eLanguage::FRENCH;

    // Offset 0x08 : Mode d'affichage des éléments à glisser
    // false (0) = Pictogrammes + Texte ("options_phrases")
    // true  (1) = Texte écrit uniquement sans icône ("options_written")
    bool writtenMode = false;

    // Offset 0x10 : Mode de changement de page (0 = Automatique, 1 = Manuel, 2 = Code PIN)
    ePageTurnMode pageTurnMode = ePageTurnMode::AUTOMATIC;

    // Destructeur (0x0036bbc8)
    ~BookSettings() = default;

    bool isEnglish() const {
        return language == eLanguage::ENGLISH;
    }
};

/**
 * Classe GlobalParams (0x0037fc34 - 0x00380168)
 * Charge le fichier assets/global.json et fournit les constantes de mise en page et de timing.
 */
class GlobalParams {
private:
    static nlohmann::json s_paramsJson;
    static bool s_initialized;

public:
    GlobalParams() = default;  // 0x0037fc34
    ~GlobalParams() = default; // 0x0037fc40

    // Adresse : 0x0037fc78
    static void initParams() {
        std::string content = cocos2d::FileUtils::getInstance()->getStringFromFile("global.json");
        s_paramsJson = nlohmann::json::parse(content);
        s_initialized = true;
    }

    // Adresse : 0x0037fc48
    static nlohmann::json& getParams() {
        if (!s_initialized) {
            initParams();
        }
        return s_paramsJson;
    }

    // Adresse : 0x0037fe54
    static float getFloatForKey(const std::string& key) {
        return getParams()[key].get<float>();
    }

    // Adresse : 0x0038011c
    static std::string getStringForKey(const std::string& key) {
        return getParams()[key].get<std::string>();
    }
};

/**
 * Classe PageLogicManager (0x0038fb64 - 0x0038fc90)
 * Détermine quels pictogrammes (A = Sujet, B = Verbe, C = Complément) doivent être
 * glissés par l'enfant selon le numéro de page et le niveau de difficulté (1, 2 ou 3).
 */
class PageLogicManager {
public:
    PageLogicManager() = default;  // 0x0038fb64
    ~PageLogicManager() = default; // 0x0038fb68

    /**
     * Adresse : 0x0038fb6c
     * Retourne un masque de bits (1..7) indiquant quels emplacements (A=bit0, B=bit1, C=bit2)
     * sont actifs sur la page `pageNum` pour la difficulté `diff`.
     */
    static int getOrderForPage(int pageNum, PageDifficuly::ePageDifficuly diff) {
        if (diff == PageDifficuly::LEVEL_1_EASY) {
            // Au niveau 1 : un seul pictogramme actif par page
            if (pageNum <= 0) {
                int r = std::rand() % 3;
                return (r == 0) ? 1 : ((r == 1) ? 2 : 4);
            }
            // Alterne entre A (4), B (2) et C (1) selon la page
            int step = ((pageNum - 1) / 2) % 3;
            if (step == 0) return 4; // C uniquement
            if (step == 1) return 2; // B uniquement
            return 1;                // A uniquement
        }
        if (diff == PageDifficuly::LEVEL_2_MEDIUM) {
            // Au niveau 2 : deux pictogrammes actifs (ex: B + C = 6, ou A + B = 3)
            return (pageNum < 7) ? 6 : 3;
        }
        // Au niveau 3 : les 3 pictogrammes (A + B + C = 7) sont toujours actifs
        return 7;
    }

    // Adresse : 0x0038fbf4
    static bool shouldShowPictogramA(int orderMask, PageDifficuly::ePageDifficuly diff) {
        if (diff == PageDifficuly::LEVEL_3_HARD) return true;
        if (diff == PageDifficuly::LEVEL_2_MEDIUM) return false;
        return (orderMask != 6);
    }

    // Adresse : 0x0038fc20
    static bool shouldShowPictogramB(int orderMask, PageDifficuly::ePageDifficuly diff) {
        if (diff == PageDifficuly::LEVEL_3_HARD) return true;
        if (diff == PageDifficuly::LEVEL_2_MEDIUM) return false;
        return (orderMask != 4);
    }

    // Adresse : 0x0038fc4c
    static bool shouldShowPictogramC(int orderMask, PageDifficuly::ePageDifficuly diff) {
        if (diff == PageDifficuly::LEVEL_3_HARD) return true;
        if (diff == PageDifficuly::LEVEL_2_MEDIUM) return false;
        return (orderMask != 2);
    }

    // Adresses : 0x0038fc78, 0x0038fc80, 0x0038fc88
    static bool shouldShowPictogramTextA(int, PageDifficuly::ePageDifficuly) { return true; }
    static bool shouldShowPictogramTextB(int, PageDifficuly::ePageDifficuly) { return true; }
    static bool shouldShowPictogramTextC(int, PageDifficuly::ePageDifficuly) { return true; }
};

/**
 * Classe PositionManager (0x0039265c - 0x003928cc)
 * Singleton calculant les coordonnées X et Y des 3 colonnes (A, B, C) et des lignes
 * proportionnellement à la taille visible de l'écran (visibleSize).
 */
class PositionManager {
private:
    static PositionManager* s_instance;

public:
    PositionManager() = default;  // 0x0039268c
    ~PositionManager() = default; // 0x00392690

    // Adresse : 0x0039265c
    static PositionManager* getInstance() {
        if (!s_instance) {
            s_instance = new PositionManager();
        }
        return s_instance;
    }

    // Adresse : 0x00392694 — Colonne A (1/6 de la largeur d'écran)
    float getAX() {
        return cocos2d::Director::getInstance()->getVisibleSize().width / 6.0f;
    }

    // Adresse : 0x003926ec — Colonne B (3/6 = milieu de la largeur d'écran)
    float getBX() {
        return (cocos2d::Director::getInstance()->getVisibleSize().width / 6.0f) * 3.0f;
    }

    // Adresse : 0x0039274c — Colonne C (5/6 de la largeur d'écran)
    float getCX() {
        return (cocos2d::Director::getInstance()->getVisibleSize().width / 6.0f) * 5.0f;
    }

    // Adresse : 0x003927ac — Ligne supérieure (9.5 / 10 de la hauteur)
    float getTopRowY() {
        return (cocos2d::Director::getInstance()->getVisibleSize().height / 10.0f) * 9.5f;
    }

    // Adresse : 0x0039280c — Ligne inférieure des pictogrammes (1.75 / 10 de la hauteur)
    float getBottomRowY() {
        return (cocos2d::Director::getInstance()->getVisibleSize().height / 10.0f) * 1.75f;
    }

    // Adresse : 0x0039286c — Ligne inférieure du texte sous les pictogrammes (0.625 / 10 de la hauteur)
    float getBottomTextRowY() {
        return (cocos2d::Director::getInstance()->getVisibleSize().height / 10.0f) * 0.625f;
    }
};


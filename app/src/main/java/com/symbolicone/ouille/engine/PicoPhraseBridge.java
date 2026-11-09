package com.symbolicone.ouille.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Pont Java natif exposant les services Android (AssetManager, MediaPlayer Audio,
 * SharedPreferences "Cocos2dxPrefsFile", ouverture d'URL) au moteur moderne PicoPhrase.
 */
public class PicoPhraseBridge {
    private static final String TAG = "PicoPhraseBridge";
    private static final String PREFS_NAME = "Cocos2dxPrefsFile";

    private final Activity activity;
    private final WebView webView;
    private final List<MediaPlayer> activePlayers = new ArrayList<>();

    public PicoPhraseBridge(Activity activity, WebView webView) {
        this.activity = activity;
        this.webView = webView;
        initDefaultPurchasesAndLang();
    }

    /**
     * Reproduit le comportement exact d'AppDelegate::applicationDidFinishLaunching() :
     * - Détecte la langue du système au premier lancement si "lang" n'existe pas encore
     * - Débloque le livre gratuit par défaut ("book-panda") via forcePurchase("book-panda")
     */
    private void initDefaultPurchasesAndLang() {
        SharedPreferences prefs = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (!prefs.contains("lang")) {
            String sysLang = Locale.getDefault().getLanguage();
            int defaultLang = (sysLang != null && sysLang.startsWith("en")) ? 1 : 0;
            editor.putInt("lang", defaultLang);
        }

        // AppDelegate::applicationDidFinishLaunching -> IAPListen::forcePurchase("book-panda")
        editor.putBoolean("book-panda", true);
        editor.putInt("opens", prefs.getInt("opens", 0) + 1);
        editor.apply();
    }

    @JavascriptInterface
    public String readAssetText(String assetPath) {
        String cleanPath = normalizeAssetPath(assetPath);
        AssetManager am = activity.getAssets();
        try (InputStream is = am.open(cleanPath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            return sb.toString();
        } catch (IOException e) {
            Log.w(TAG, "Asset introuvable: " + cleanPath);
            return "";
        }
    }

    @JavascriptInterface
    public boolean assetExists(String assetPath) {
        String cleanPath = normalizeAssetPath(assetPath);
        try (InputStream is = activity.getAssets().open(cleanPath)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @JavascriptInterface
    public int getLanguage() {
        SharedPreferences prefs = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (prefs.contains("lang")) {
            return prefs.getInt("lang", 2);
        }
        // Premier démarrage : auto-détection selon la locale de l'appareil
        java.util.Locale locale = java.util.Locale.getDefault();
        String lang = locale.getLanguage();
        String country = locale.getCountry();
        int detected;
        if ("fr".equalsIgnoreCase(lang)) {
            if ("CA".equalsIgnoreCase(country)) {
                detected = 2; // eLanguage.QUEBEC
            } else {
                detected = 0; // eLanguage.FRENCH (France)
            }
        } else {
            detected = 1; // eLanguage.ENGLISH
        }
        prefs.edit().putInt("lang", detected).apply();
        return detected;
    }

    @JavascriptInterface
    public void setLanguage(int langCode) {
        SharedPreferences prefs = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt("lang", langCode).apply();
    }

    @JavascriptInterface
    public boolean isProductPurchased(String bookKey) {
        /*if ("book-panda".equals(bookKey)) {
            return true;
        }
        SharedPreferences prefs = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        try {
            return prefs.getBoolean(bookKey, false);
        } catch (ClassCastException e) {
            Object val = prefs.getAll().get(bookKey);
            if (val instanceof String) {
                return Boolean.parseBoolean((String) val);
            }
            if (val instanceof Number) {
                return ((Number) val).intValue() != 0;
            }
            return false;
        }*/
        return true;
    }

    @JavascriptInterface
    public void playAudio(String rawPath) {
        playAudioInBook(rawPath, "");
    }

    @JavascriptInterface
    public void playAudioInBook(String rawPath, String bookFolder) {
        String resolved = resolveAudioAssetPath(rawPath, bookFolder);
        if (resolved == null) {
            Log.w(TAG, "Fichier audio introuvable: " + rawPath + " (dossier: " + bookFolder + ")");
            return;
        }
        try {
            AssetFileDescriptor afd = activity.getAssets().openFd(resolved);
            final MediaPlayer mp = new MediaPlayer();
            synchronized (activePlayers) {
                activePlayers.add(mp);
            }
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mp.setOnCompletionListener(player -> {
                synchronized (activePlayers) {
                    activePlayers.remove(player);
                }
                player.release();
            });
            mp.setOnErrorListener((player, what, extra) -> {
                synchronized (activePlayers) {
                    activePlayers.remove(player);
                }
                player.release();
                return true;
            });
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            Log.e(TAG, "Erreur lecture audio: " + resolved, e);
        }
    }

    @JavascriptInterface
    public void stopAllAudio() {
        synchronized (activePlayers) {
            for (MediaPlayer mp : activePlayers) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                    }
                    mp.release();
                } catch (Exception ignored) {
                }
            }
            activePlayers.clear();
        }
    }

    /**
     * Retourne la durée exacte en millisecondes d'une piste audio des assets.
     */
    @JavascriptInterface
    public int getAudioDuration(String rawPath, String bookFolder) {
        String resolved = resolveAudioAssetPath(rawPath, bookFolder);
        if (resolved == null) return 0;
        try (AssetFileDescriptor afd = activity.getAssets().openFd(resolved)) {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            String durStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            mmr.release();
            if (durStr != null) {
                return Integer.parseInt(durStr);
            }
        } catch (Exception e) {
            Log.w(TAG, "getAudioDuration failed for " + resolved, e);
        }
        return 0;
    }

    @JavascriptInterface
    public void openExternalUrl(String url) {
        if (url == null || url.isEmpty()) return;
        activity.runOnUiThread(() -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                activity.startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, "Impossible d'ouvrir l'URL: " + url, e);
            }
        });
    }

    private String normalizeAssetPath(String path) {
        if (path == null) return "";
        String clean = path.replace('\\', '/').trim();
        while (clean.startsWith("/")) {
            clean = clean.substring(1);
        }
        return clean;
    }

    private String resolveAudioAssetPath(String rawPath, String bookFolder) {
        if (rawPath == null || rawPath.isEmpty()) return null;
        String clean = normalizeAssetPath(rawPath);

        List<String> variants = new ArrayList<>();
        if (clean.endsWith(".mp3") || clean.endsWith(".aac")) {
            variants.add(clean);
        } else {
            variants.add(clean + ".mp3");
            variants.add(clean + ".aac");
        }

        List<String> candidates = new ArrayList<>();
        for (String v : variants) {
            candidates.add(v);
            if (bookFolder != null && !bookFolder.isEmpty() && !v.startsWith(bookFolder + "/")) {
                candidates.add(bookFolder + "/" + v);
            }
            if ("shiny-ding1.mp3".equals(v) || "shiny-ding.mp3".equals(v)) {
                candidates.add("book-hurt/shiny-ding.mp3");
            }
            if ("hurt.mp3".equals(v)) {
                candidates.add("book-hurt/hurt_p1_b_fr_eu.mp3");
            }
            candidates.add("common/" + v);
        }

        for (String candidate : candidates) {
            if (assetExists(candidate)) {
                return candidate;
            }
        }
        return null;
    }
}

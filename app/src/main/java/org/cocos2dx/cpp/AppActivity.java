package org.cocos2dx.cpp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.symbolicone.ouille.engine.PicoPhraseBridge;

/**
 * Activité principale modernisée de PicoPhrase.
 * Remplace l'ancien moteur natif Cocos2d-x (libMyGame.so) par un moteur Java + WebGL/SVG
 * accéléré matériellement qui lit directement 100 % des assets (.json, .svg, .mp3, .atlas) du projet.
 */
public class AppActivity extends Activity {
    private WebView webView;
    private PicoPhraseBridge bridge;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        if (isTaskRoot() && Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(attributes);
        }

        hideSystemUI();

        webView = new WebView(this);
        webView.setBackgroundColor(Color.WHITE);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        bridge = new PicoPhraseBridge(this, webView);
        webView.addJavascriptInterface(bridge, "PicoNative");
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(android.webkit.ConsoleMessage consoleMessage) {
                android.util.Log.d("PicoJS", consoleMessage.message() + " -- [Ligne "
                        + consoleMessage.lineNumber() + " de "
                        + consoleMessage.sourceId() + "]");
                return true;
            }
        });

        setContentView(webView);
        webView.loadUrl("file:///android_asset/picophrase_engine/index.html");
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (bridge != null) {
            bridge.stopAllAudio();
        }
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) {
            webView.onResume();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null) {
            webView.evaluateJavascript(
                    "(window.PicoEngine && window.PicoEngine.onBackPressed) ? window.PicoEngine.onBackPressed() : false",
                    result -> {
                        if (!"true".equals(result)) {
                            AppActivity.super.onBackPressed();
                        }
                    }
            );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (bridge != null) {
            bridge.stopAllAudio();
        }
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}

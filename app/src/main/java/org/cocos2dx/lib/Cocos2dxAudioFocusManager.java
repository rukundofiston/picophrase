package org.cocos2dx.lib;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class Cocos2dxAudioFocusManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static AudioManager.OnAudioFocusChangeListener f519a = new a();

    static class a implements AudioManager.OnAudioFocusChangeListener {

        /* JADX INFO: renamed from: org.cocos2dx.lib.Cocos2dxAudioFocusManager$a$a, reason: collision with other inner class name */
        class RunnableC0011a implements Runnable {
            RunnableC0011a(a aVar) {
            }

            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(1);
            }
        }

        class b implements Runnable {
            b(a aVar) {
            }

            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(2);
            }
        }

        class c implements Runnable {
            c(a aVar) {
            }

            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(3);
            }
        }

        class d implements Runnable {
            d(a aVar) {
            }

            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(0);
            }
        }

        a() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            Runnable dVar;
            Log.d("AudioFocusManager", "onAudioFocusChange: " + i + ", thread: " + Thread.currentThread().getName());
            if (i == -1) {
                Log.d("AudioFocusManager", "Pause music by AUDIOFOCUS_LOSS");
                dVar = new RunnableC0011a(this);
            } else if (i == -2) {
                Log.d("AudioFocusManager", "Pause music by AUDIOFOCUS_LOSS_TRANSILENT");
                dVar = new b(this);
            } else if (i == -3) {
                Log.d("AudioFocusManager", "Lower the volume, keep playing by AUDIOFOCUS_LOSS_TRANSILENT_CAN_DUCK");
                dVar = new c(this);
            } else {
                if (i != 1) {
                    return;
                }
                Log.d("AudioFocusManager", "Resume music by AUDIOFOCUS_GAIN");
                dVar = new d(this);
            }
            Cocos2dxHelper.runOnGLThread(dVar);
        }
    }

    static class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(0);
        }
    }

    static boolean a(Context context) {
        if (((AudioManager) context.getSystemService("audio")).requestAudioFocus(f519a, 3, 1) == 1) {
            Log.d("AudioFocusManager", "requestAudioFocus succeed");
            return true;
        }
        Log.e("AudioFocusManager", "requestAudioFocus failed!");
        return false;
    }

    static void b(Context context) {
        if (((AudioManager) context.getSystemService("audio")).abandonAudioFocus(f519a) == 1) {
            Log.d("AudioFocusManager", "abandonAudioFocus succeed!");
        } else {
            Log.e("AudioFocusManager", "abandonAudioFocus failed!");
        }
        Cocos2dxHelper.runOnGLThread(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnAudioFocusChange(int i);
}

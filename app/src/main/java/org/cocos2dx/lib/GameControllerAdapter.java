package org.cocos2dx.lib;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GameControllerAdapter {
    private static ArrayList<Runnable> sRunnableFrameStartList;

    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f669a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f670b;

        a(String str, int i) {
            this.f669a = str;
            this.f670b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            GameControllerAdapter.nativeControllerConnected(this.f669a, this.f670b);
        }
    }

    static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f671a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f672b;

        b(String str, int i) {
            this.f671a = str;
            this.f672b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            GameControllerAdapter.nativeControllerDisconnected(this.f671a, this.f672b);
        }
    }

    static class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f673a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f674b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f675c;
        final /* synthetic */ boolean d;
        final /* synthetic */ float e;
        final /* synthetic */ boolean f;

        c(String str, int i, int i2, boolean z, float f, boolean z2) {
            this.f673a = str;
            this.f674b = i;
            this.f675c = i2;
            this.d = z;
            this.e = f;
            this.f = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            GameControllerAdapter.nativeControllerButtonEvent(this.f673a, this.f674b, this.f675c, this.d, this.e, this.f);
        }
    }

    static class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f676a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f677b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f678c;
        final /* synthetic */ float d;
        final /* synthetic */ boolean e;

        d(String str, int i, int i2, float f, boolean z) {
            this.f676a = str;
            this.f677b = i;
            this.f678c = i2;
            this.d = f;
            this.e = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            GameControllerAdapter.nativeControllerAxisEvent(this.f676a, this.f677b, this.f678c, this.d, this.e);
        }
    }

    public static void addRunnableToFrameStartList(Runnable runnable) {
        if (sRunnableFrameStartList == null) {
            sRunnableFrameStartList = new ArrayList<>();
        }
        sRunnableFrameStartList.add(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeControllerAxisEvent(String str, int i, int i2, float f, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeControllerButtonEvent(String str, int i, int i2, boolean z, float f, boolean z2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeControllerConnected(String str, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeControllerDisconnected(String str, int i);

    public static void onAxisEvent(String str, int i, int i2, float f, boolean z) {
        Cocos2dxHelper.runOnGLThread(new d(str, i, i2, f, z));
    }

    public static void onButtonEvent(String str, int i, int i2, boolean z, float f, boolean z2) {
        Cocos2dxHelper.runOnGLThread(new c(str, i, i2, z, f, z2));
    }

    public static void onConnected(String str, int i) {
        Cocos2dxHelper.runOnGLThread(new a(str, i));
    }

    public static void onDisconnected(String str, int i) {
        Cocos2dxHelper.runOnGLThread(new b(str, i));
    }

    public static void onDrawFrameStart() {
        ArrayList<Runnable> arrayList = sRunnableFrameStartList;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                sRunnableFrameStartList.get(i).run();
            }
        }
    }

    public static void removeRunnableFromFrameStartList(Runnable runnable) {
        ArrayList<Runnable> arrayList = sRunnableFrameStartList;
        if (arrayList != null) {
            arrayList.remove(runnable);
        }
    }
}

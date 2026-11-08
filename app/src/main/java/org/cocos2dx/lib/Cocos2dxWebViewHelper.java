package org.cocos2dx.lib;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.widget.FrameLayout;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public class Cocos2dxWebViewHelper {
    private static final String TAG = "Cocos2dxWebViewHelper";
    private static Cocos2dxActivity sCocos2dxActivity;
    private static Handler sHandler;
    private static FrameLayout sLayout;
    private static int viewTag;
    private static SparseArray<Cocos2dxWebView> webViews;

    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f635a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f636b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f637c;

        a(int i, String str, String str2) {
            this.f635a = i;
            this.f636b = str;
            this.f637c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f635a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.loadDataWithBaseURL(this.f636b, this.f637c, null, null, null);
            }
        }
    }

    static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f638a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f639b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f640c;

        b(int i, boolean z, String str) {
            this.f638a = i;
            this.f639b = z;
            this.f640c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f638a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.getSettings().setCacheMode(this.f639b ? 2 : -1);
                cocos2dxWebView.loadUrl(this.f640c);
            }
        }
    }

    static class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f641a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f642b;

        c(int i, String str) {
            this.f641a = i;
            this.f642b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f641a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.loadUrl(this.f642b);
            }
        }
    }

    static class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f643a;

        d(int i) {
            this.f643a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f643a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.stopLoading();
            }
        }
    }

    static class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f644a;

        e(int i) {
            this.f644a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f644a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.reload();
            }
        }
    }

    static class f implements Callable<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f645a;

        f(int i) {
            this.f645a = i;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f645a);
            return Boolean.valueOf(cocos2dxWebView != null && cocos2dxWebView.canGoBack());
        }
    }

    static class g implements Callable<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f646a;

        g(int i) {
            this.f646a = i;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f646a);
            return Boolean.valueOf(cocos2dxWebView != null && cocos2dxWebView.canGoForward());
        }
    }

    static class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f647a;

        h(int i) {
            this.f647a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f647a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.goBack();
            }
        }
    }

    static class i implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f648a;

        i(int i) {
            this.f648a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f648a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.goForward();
            }
        }
    }

    static class j implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f649a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f650b;

        j(int i, String str) {
            this.f649a = i;
            this.f650b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f649a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.loadUrl("javascript:" + this.f650b);
            }
        }
    }

    static class k implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f651a;

        k(int i) {
            this.f651a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = new Cocos2dxWebView(Cocos2dxWebViewHelper.sCocos2dxActivity, this.f651a);
            Cocos2dxWebViewHelper.sLayout.addView(cocos2dxWebView, new FrameLayout.LayoutParams(-2, -2));
            Cocos2dxWebViewHelper.webViews.put(this.f651a, cocos2dxWebView);
        }
    }

    static class l implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f652a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f653b;

        l(int i, boolean z) {
            this.f652a = i;
            this.f653b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f652a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.setScalesPageToFit(this.f653b);
            }
        }
    }

    static class m implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f654a;

        m(int i) {
            this.f654a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f654a);
            if (cocos2dxWebView != null) {
                Cocos2dxWebViewHelper.webViews.remove(this.f654a);
                Cocos2dxWebViewHelper.sLayout.removeView(cocos2dxWebView);
            }
        }
    }

    static class n implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f655a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f656b;

        n(int i, boolean z) {
            this.f655a = i;
            this.f656b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f655a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.setVisibility(this.f656b ? 0 : 8);
            }
        }
    }

    static class o implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f657a;

        o(int i) {
            this.f657a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f657a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.setBackgroundColor(0);
            }
        }
    }

    static class p implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f658a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ float f659b;

        p(int i, float f) {
            this.f658a = i;
            this.f659b = f;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f658a);
            if (cocos2dxWebView != null) {
                try {
                    cocos2dxWebView.getClass().getMethod("setAlpha", Float.TYPE).invoke(cocos2dxWebView, Float.valueOf(this.f659b));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class q implements Callable<Float> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f660a;

        q(int i) {
            this.f660a = i;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Float call() {
            Object objInvoke;
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f660a);
            if (cocos2dxWebView != null) {
                try {
                    objInvoke = cocos2dxWebView.getClass().getMethod("getAlpha", new Class[0]).invoke(cocos2dxWebView, new Object[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                    objInvoke = null;
                }
            } else {
                objInvoke = null;
            }
            return (Float) objInvoke;
        }
    }

    static class r implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f661a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f662b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f663c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;

        r(int i, int i2, int i3, int i4, int i5) {
            this.f661a = i;
            this.f662b = i2;
            this.f663c = i3;
            this.d = i4;
            this.e = i5;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f661a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.setWebViewRect(this.f662b, this.f663c, this.d, this.e);
            }
        }
    }

    static class s implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f664a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f665b;

        s(int i, String str) {
            this.f664a = i;
            this.f665b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f664a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.setJavascriptInterfaceScheme(this.f665b);
            }
        }
    }

    static class t implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f666a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f667b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f668c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;

        t(int i, String str, String str2, String str3, String str4) {
            this.f666a = i;
            this.f667b = str;
            this.f668c = str2;
            this.d = str3;
            this.e = str4;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f666a);
            if (cocos2dxWebView != null) {
                cocos2dxWebView.loadDataWithBaseURL(this.f667b, this.f668c, this.d, this.e, null);
            }
        }
    }

    public Cocos2dxWebViewHelper(FrameLayout frameLayout) {
        sLayout = frameLayout;
        sHandler = new Handler(Looper.myLooper());
        sCocos2dxActivity = (Cocos2dxActivity) Cocos2dxActivity.getContext();
        webViews = new SparseArray<>();
    }

    public static void _didFailLoading(int i2, String str) {
        didFailLoading(i2, str);
    }

    public static void _didFinishLoading(int i2, String str) {
        didFinishLoading(i2, str);
    }

    public static void _onJsCallback(int i2, String str) {
        onJsCallback(i2, str);
    }

    public static boolean _shouldStartLoading(int i2, String str) {
        return !shouldStartLoading(i2, str);
    }

    public static <T> T callInMainThread(Callable<T> callable) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(callable);
        sHandler.post(futureTask);
        return (T) futureTask.get();
    }

    public static boolean canGoBack(int i2) {
        try {
            return ((Boolean) callInMainThread(new f(i2))).booleanValue();
        } catch (InterruptedException | ExecutionException unused) {
            return false;
        }
    }

    public static boolean canGoForward(int i2) {
        try {
            return ((Boolean) callInMainThread(new g(i2))).booleanValue();
        } catch (InterruptedException | ExecutionException unused) {
            return false;
        }
    }

    public static int createWebView() {
        sCocos2dxActivity.runOnUiThread(new k(viewTag));
        int i2 = viewTag;
        viewTag = i2 + 1;
        return i2;
    }

    private static native void didFailLoading(int i2, String str);

    private static native void didFinishLoading(int i2, String str);

    public static void evaluateJS(int i2, String str) {
        sCocos2dxActivity.runOnUiThread(new j(i2, str));
    }

    public static float getOpacityWebView(int i2) {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1.0f;
        }
        FutureTask futureTask = new FutureTask(new q(i2));
        sCocos2dxActivity.runOnUiThread(futureTask);
        try {
            return ((Float) futureTask.get()).floatValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 1.0f;
        }
    }

    public static void goBack(int i2) {
        sCocos2dxActivity.runOnUiThread(new h(i2));
    }

    public static void goForward(int i2) {
        sCocos2dxActivity.runOnUiThread(new i(i2));
    }

    public static void loadData(int i2, String str, String str2, String str3, String str4) {
        sCocos2dxActivity.runOnUiThread(new t(i2, str4, str, str2, str3));
    }

    public static void loadFile(int i2, String str) {
        sCocos2dxActivity.runOnUiThread(new c(i2, str));
    }

    public static void loadHTMLString(int i2, String str, String str2) {
        sCocos2dxActivity.runOnUiThread(new a(i2, str2, str));
    }

    public static void loadUrl(int i2, String str, boolean z) {
        sCocos2dxActivity.runOnUiThread(new b(i2, z, str));
    }

    private static native void onJsCallback(int i2, String str);

    public static void reload(int i2) {
        sCocos2dxActivity.runOnUiThread(new e(i2));
    }

    public static void removeWebView(int i2) {
        sCocos2dxActivity.runOnUiThread(new m(i2));
    }

    public static void setBackgroundTransparent(int i2) {
        sCocos2dxActivity.runOnUiThread(new o(i2));
    }

    public static void setJavascriptInterfaceScheme(int i2, String str) {
        sCocos2dxActivity.runOnUiThread(new s(i2, str));
    }

    public static void setOpacityWebView(int i2, float f2) {
        if (Build.VERSION.SDK_INT > 10) {
            sCocos2dxActivity.runOnUiThread(new p(i2, f2));
        }
    }

    public static void setScalesPageToFit(int i2, boolean z) {
        sCocos2dxActivity.runOnUiThread(new l(i2, z));
    }

    public static void setVisible(int i2, boolean z) {
        sCocos2dxActivity.runOnUiThread(new n(i2, z));
    }

    public static void setWebViewRect(int i2, int i3, int i4, int i5, int i6) {
        sCocos2dxActivity.runOnUiThread(new r(i2, i3, i4, i5, i6));
    }

    private static native boolean shouldStartLoading(int i2, String str);

    public static void stopLoading(int i2) {
        sCocos2dxActivity.runOnUiThread(new d(i2));
    }
}

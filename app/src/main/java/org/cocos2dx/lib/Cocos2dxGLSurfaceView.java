package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: classes.dex */
public class Cocos2dxGLSurfaceView extends GLSurfaceView {
    private static final int HANDLER_CLOSE_IME_KEYBOARD = 3;
    private static final int HANDLER_OPEN_IME_KEYBOARD = 2;
    private static final String TAG = Cocos2dxGLSurfaceView.class.getSimpleName();
    private static Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
    private static Cocos2dxTextInputWrapper sCocos2dxTextInputWraper;
    private static Handler sHandler;
    private Cocos2dxEditBox mCocos2dxEditText;
    private Cocos2dxRenderer mCocos2dxRenderer;
    private boolean mMultipleTouchEnabled;
    private boolean mSoftKeyboardShown;

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f579a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ float f580b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f581c;

        a(int i, float f, float f2) {
            this.f579a = i;
            this.f580b = f;
            this.f581c = f2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionUp(this.f579a, this.f580b, this.f581c);
        }
    }

    class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int[] f582a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ float[] f583b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ float[] f584c;

        b(int[] iArr, float[] fArr, float[] fArr2) {
            this.f582a = iArr;
            this.f583b = fArr;
            this.f584c = fArr2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionCancel(this.f582a, this.f583b, this.f584c);
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int[] f585a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ float[] f586b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ float[] f587c;

        c(int[] iArr, float[] fArr, float[] fArr2) {
            this.f585a = iArr;
            this.f586b = fArr;
            this.f587c = fArr2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionCancel(this.f585a, this.f586b, this.f587c);
        }
    }

    class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f588a;

        d(int i) {
            this.f588a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleKeyDown(this.f588a);
        }
    }

    class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f590a;

        e(int i) {
            this.f590a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleKeyUp(this.f590a);
        }
    }

    class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f592a;

        f(String str) {
            this.f592a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleInsertText(this.f592a);
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleDeleteBackward();
        }
    }

    class h extends Handler {
        h() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str;
            int i = message.what;
            if (i != 2) {
                if (i != 3 || Cocos2dxGLSurfaceView.this.mCocos2dxEditText == null) {
                    return;
                }
                Cocos2dxGLSurfaceView.this.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                ((InputMethodManager) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(Cocos2dxGLSurfaceView.this.mCocos2dxEditText.getWindowToken(), 0);
                Cocos2dxGLSurfaceView.this.requestFocus();
                ((Cocos2dxActivity) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext()).hideVirtualButton();
                str = "HideSoftInput";
            } else {
                if (Cocos2dxGLSurfaceView.this.mCocos2dxEditText == null || !Cocos2dxGLSurfaceView.this.mCocos2dxEditText.requestFocus()) {
                    return;
                }
                Cocos2dxGLSurfaceView.this.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                Cocos2dxGLSurfaceView.this.mCocos2dxEditText.setText(BuildConfig.FLAVOR);
                String str2 = (String) message.obj;
                Cocos2dxGLSurfaceView.this.mCocos2dxEditText.append(str2);
                Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper.setOriginText(str2);
                Cocos2dxGLSurfaceView.this.mCocos2dxEditText.addTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                ((InputMethodManager) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).showSoftInput(Cocos2dxGLSurfaceView.this.mCocos2dxEditText, 0);
                str = "showSoftInput";
            }
            Log.d("GLSurfaceView", str);
        }
    }

    static class i implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f596a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ float f597b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f598c;
        final /* synthetic */ long d;

        i(float f, float f2, float f3, long j) {
            this.f596a = f;
            this.f597b = f2;
            this.f598c = f3;
            this.d = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxAccelerometer.onSensorChanged(this.f596a, this.f597b, this.f598c, this.d);
        }
    }

    class j implements Runnable {
        j() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleOnResume();
        }
    }

    class k implements Runnable {
        k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleOnPause();
        }
    }

    class l implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f601a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ float f602b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f603c;

        l(int i, float f, float f2) {
            this.f601a = i;
            this.f602b = f;
            this.f603c = f2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionDown(this.f601a, this.f602b, this.f603c);
        }
    }

    class m implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f604a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ float f605b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f606c;

        m(int i, float f, float f2) {
            this.f604a = i;
            this.f605b = f;
            this.f606c = f2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionDown(this.f604a, this.f605b, this.f606c);
        }
    }

    class n implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int[] f607a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ float[] f608b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ float[] f609c;

        n(int[] iArr, float[] fArr, float[] fArr2) {
            this.f607a = iArr;
            this.f608b = fArr;
            this.f609c = fArr2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionMove(this.f607a, this.f608b, this.f609c);
        }
    }

    class o implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int[] f610a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ float[] f611b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ float[] f612c;

        o(int[] iArr, float[] fArr, float[] fArr2) {
            this.f610a = iArr;
            this.f611b = fArr;
            this.f612c = fArr2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionMove(this.f610a, this.f611b, this.f612c);
        }
    }

    class p implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f613a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ float f614b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f615c;

        p(int i, float f, float f2) {
            this.f613a = i;
            this.f614b = f;
            this.f615c = f2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionUp(this.f613a, this.f614b, this.f615c);
        }
    }

    public Cocos2dxGLSurfaceView(Context context) {
        super(context);
        this.mSoftKeyboardShown = false;
        this.mMultipleTouchEnabled = true;
        initView();
    }

    public Cocos2dxGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSoftKeyboardShown = false;
        this.mMultipleTouchEnabled = true;
        initView();
    }

    public static void closeIMEKeyboard() {
        Message message = new Message();
        message.what = 3;
        sHandler.sendMessage(message);
    }

    private static void dumpMotionEvent(MotionEvent motionEvent) {
        StringBuilder sb = new StringBuilder();
        int action = motionEvent.getAction();
        int i2 = action & 255;
        sb.append("event ACTION_");
        sb.append(new String[]{"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"}[i2]);
        if (i2 == 5 || i2 == 6) {
            sb.append("(pid ");
            sb.append(action >> 8);
            sb.append(")");
        }
        sb.append("[");
        int i3 = 0;
        while (i3 < motionEvent.getPointerCount()) {
            sb.append("#");
            sb.append(i3);
            sb.append("(pid ");
            sb.append(motionEvent.getPointerId(i3));
            sb.append(")=");
            sb.append((int) motionEvent.getX(i3));
            sb.append(",");
            sb.append((int) motionEvent.getY(i3));
            i3++;
            if (i3 < motionEvent.getPointerCount()) {
                sb.append(";");
            }
        }
        sb.append("]");
        Log.d(TAG, sb.toString());
    }

    private String getContentText() {
        return this.mCocos2dxRenderer.getContentText();
    }

    public static Cocos2dxGLSurfaceView getInstance() {
        return mCocos2dxGLSurfaceView;
    }

    public static void openIMEKeyboard() {
        Message message = new Message();
        message.what = 2;
        message.obj = mCocos2dxGLSurfaceView.getContentText();
        sHandler.sendMessage(message);
    }

    public static void queueAccelerometer(float f2, float f3, float f4, long j2) {
        mCocos2dxGLSurfaceView.queueEvent(new i(f2, f3, f4, j2));
    }

    public void deleteBackward() {
        queueEvent(new g());
    }

    public Cocos2dxEditBox getCocos2dxEditText() {
        return this.mCocos2dxEditText;
    }

    protected void initView() {
        setEGLContextClientVersion(2);
        setFocusableInTouchMode(true);
        mCocos2dxGLSurfaceView = this;
        sCocos2dxTextInputWraper = new Cocos2dxTextInputWrapper(this);
        sHandler = new h();
    }

    public void insertText(String str) {
        queueEvent(new f(str));
    }

    public boolean isMultipleTouchEnabled() {
        return this.mMultipleTouchEnabled;
    }

    public boolean isSoftKeyboardShown() {
        return this.mSoftKeyboardShown;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            Cocos2dxVideoHelper.mVideoHandler.sendEmptyMessage(1000);
        } else if (i2 != 66 && i2 != 82 && i2 != 85) {
            switch (i2) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    break;
                default:
                    return super.onKeyDown(i2, keyEvent);
            }
        }
        queueEvent(new d(i2));
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4 && i2 != 66 && i2 != 82 && i2 != 85) {
            switch (i2) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    break;
                default:
                    return super.onKeyUp(i2, keyEvent);
            }
        }
        queueEvent(new e(i2));
        return true;
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        queueEvent(new k());
        setRenderMode(0);
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        setRenderMode(1);
        queueEvent(new j());
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        if (isInEditMode()) {
            return;
        }
        this.mCocos2dxRenderer.setScreenWidthAndHeight(i2, i3);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Runnable mVar;
        Runnable oVar;
        Runnable lVar;
        int pointerCount = motionEvent.getPointerCount();
        int[] iArr = new int[pointerCount];
        float[] fArr = new float[pointerCount];
        float[] fArr2 = new float[pointerCount];
        if (this.mSoftKeyboardShown) {
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(((Activity) getContext()).getCurrentFocus().getWindowToken(), 0);
            requestFocus();
            this.mSoftKeyboardShown = false;
        }
        for (int i2 = 0; i2 < pointerCount; i2++) {
            iArr[i2] = motionEvent.getPointerId(i2);
            fArr[i2] = motionEvent.getX(i2);
            fArr2[i2] = motionEvent.getY(i2);
        }
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action == 1) {
                mVar = new a(motionEvent.getPointerId(0), fArr[0], fArr2[0]);
            } else {
                if (action == 2) {
                    if (this.mMultipleTouchEnabled) {
                        queueEvent(new o(iArr, fArr, fArr2));
                    } else {
                        for (int i3 = 0; i3 < pointerCount; i3++) {
                            if (iArr[i3] == 0) {
                                queueEvent(new n(new int[]{0}, new float[]{fArr[i3]}, new float[]{fArr2[i3]}));
                                break;
                            }
                        }
                    }
                } else if (action == 3) {
                    if (this.mMultipleTouchEnabled) {
                        queueEvent(new c(iArr, fArr, fArr2));
                    } else {
                        for (int i4 = 0; i4 < pointerCount; i4++) {
                            if (iArr[i4] == 0) {
                                queueEvent(new b(new int[]{0}, new float[]{fArr[i4]}, new float[]{fArr2[i4]}));
                                break;
                            }
                        }
                    }
                } else if (action == 5) {
                    int action2 = motionEvent.getAction() >> 8;
                    if (this.mMultipleTouchEnabled || action2 == 0) {
                        lVar = new l(motionEvent.getPointerId(action2), motionEvent.getX(action2), motionEvent.getY(action2));
                        queueEvent(lVar);
                    }
                } else if (action == 6) {
                    int action3 = motionEvent.getAction() >> 8;
                    if (this.mMultipleTouchEnabled || action3 == 0) {
                        lVar = new p(motionEvent.getPointerId(action3), motionEvent.getX(action3), motionEvent.getY(action3));
                        queueEvent(lVar);
                    }
                }
            }
            return true;
        }
        mVar = new m(motionEvent.getPointerId(0), fArr[0], fArr2[0]);
        queueEvent(mVar);
        return true;
    }

    public void setCocos2dxEditText(Cocos2dxEditBox cocos2dxEditBox) {
        Cocos2dxTextInputWrapper cocos2dxTextInputWrapper;
        this.mCocos2dxEditText = cocos2dxEditBox;
        if (cocos2dxEditBox == null || (cocos2dxTextInputWrapper = sCocos2dxTextInputWraper) == null) {
            return;
        }
        cocos2dxEditBox.setOnEditorActionListener(cocos2dxTextInputWrapper);
        requestFocus();
    }

    public void setCocos2dxRenderer(Cocos2dxRenderer cocos2dxRenderer) {
        this.mCocos2dxRenderer = cocos2dxRenderer;
        setRenderer(cocos2dxRenderer);
    }

    public void setMultipleTouchEnabled(boolean z) {
        this.mMultipleTouchEnabled = z;
    }

    public void setSoftKeyboardShown(boolean z) {
        this.mSoftKeyboardShown = z;
    }
}

package org.cocos2dx.lib;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class Cocos2dxEditBoxHelper {
    private static final String TAG = "Cocos2dxEditBoxHelper";
    private static Cocos2dxActivity mCocos2dxActivity = null;
    private static SparseArray<Cocos2dxEditBox> mEditBoxArray = null;
    private static ResizeLayout mFrameLayout = null;
    private static float mPadding = 5.0f;
    private static int mViewTag;

    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f532a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f533b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f534c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;
        final /* synthetic */ int f;

        /* JADX INFO: renamed from: org.cocos2dx.lib.Cocos2dxEditBoxHelper$a$a, reason: collision with other inner class name */
        class C0012a implements TextWatcher {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Cocos2dxEditBox f535a;

            /* JADX INFO: renamed from: org.cocos2dx.lib.Cocos2dxEditBoxHelper$a$a$a, reason: collision with other inner class name */
            class RunnableC0013a implements Runnable {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                final /* synthetic */ Editable f537a;

                RunnableC0013a(Editable editable) {
                    this.f537a = editable;
                }

                @Override // java.lang.Runnable
                public void run() {
                    Cocos2dxEditBoxHelper.__editBoxEditingChanged(a.this.f, this.f537a.toString());
                }
            }

            C0012a(Cocos2dxEditBox cocos2dxEditBox) {
                this.f535a = cocos2dxEditBox;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (!this.f535a.getChangedTextProgrammatically().booleanValue() && ((Boolean) this.f535a.getTag()).booleanValue()) {
                    Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new RunnableC0013a(editable));
                }
                this.f535a.setChangedTextProgrammatically(false);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        }

        class b implements View.OnFocusChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Cocos2dxEditBox f539a;

            /* JADX INFO: renamed from: org.cocos2dx.lib.Cocos2dxEditBoxHelper$a$b$a, reason: collision with other inner class name */
            class RunnableC0014a implements Runnable {
                RunnableC0014a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    b bVar = b.this;
                    bVar.f539a.endAction = 0;
                    Cocos2dxEditBoxHelper.__editBoxEditingDidBegin(a.this.f);
                }
            }

            /* JADX INFO: renamed from: org.cocos2dx.lib.Cocos2dxEditBoxHelper$a$b$b, reason: collision with other inner class name */
            class RunnableC0015b implements Runnable {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                final /* synthetic */ String f542a;

                RunnableC0015b(String str) {
                    this.f542a = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    b bVar = b.this;
                    Cocos2dxEditBoxHelper.__editBoxEditingDidEnd(a.this.f, this.f542a, bVar.f539a.endAction);
                }
            }

            b(Cocos2dxEditBox cocos2dxEditBox) {
                this.f539a = cocos2dxEditBox;
            }

            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                String str;
                String str2;
                this.f539a.setTag(true);
                this.f539a.setChangedTextProgrammatically(false);
                if (z) {
                    Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new RunnableC0014a());
                    Cocos2dxEditBox cocos2dxEditBox = this.f539a;
                    cocos2dxEditBox.setSelection(cocos2dxEditBox.getText().length());
                    Cocos2dxEditBoxHelper.mFrameLayout.setEnableForceDoLayout(true);
                    Cocos2dxEditBoxHelper.mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(true);
                    str = Cocos2dxEditBoxHelper.TAG;
                    str2 = "edit box get focus";
                } else {
                    this.f539a.setVisibility(8);
                    Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new RunnableC0015b(new String(this.f539a.getText().toString())));
                    Cocos2dxEditBoxHelper.mCocos2dxActivity.hideVirtualButton();
                    Cocos2dxEditBoxHelper.mFrameLayout.setEnableForceDoLayout(false);
                    str = Cocos2dxEditBoxHelper.TAG;
                    str2 = "edit box lose focus";
                }
                Log.d(str, str2);
            }
        }

        class c implements View.OnKeyListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Cocos2dxEditBox f544a;

            c(Cocos2dxEditBox cocos2dxEditBox) {
                this.f544a = cocos2dxEditBox;
            }

            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0 || i != 66 || (this.f544a.getInputType() & 131072) == 131072) {
                    return false;
                }
                Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(a.this.f);
                return true;
            }
        }

        class d implements TextView.OnEditorActionListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Cocos2dxEditBox f546a;

            d(Cocos2dxEditBox cocos2dxEditBox) {
                this.f546a = cocos2dxEditBox;
            }

            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 5) {
                    this.f546a.endAction = 1;
                    Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(a.this.f);
                    return true;
                }
                if (i != 6 && i != 4 && i != 3 && i != 2) {
                    return false;
                }
                this.f546a.endAction = 3;
                Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(a.this.f);
                return false;
            }
        }

        a(float f, int i, int i2, int i3, int i4, int i5) {
            this.f532a = f;
            this.f533b = i;
            this.f534c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = new Cocos2dxEditBox(Cocos2dxEditBoxHelper.mCocos2dxActivity);
            cocos2dxEditBox.setFocusable(true);
            cocos2dxEditBox.setFocusableInTouchMode(true);
            cocos2dxEditBox.setInputFlag(5);
            cocos2dxEditBox.setInputMode(6);
            cocos2dxEditBox.setReturnType(0);
            cocos2dxEditBox.setHintTextColor(-7829368);
            cocos2dxEditBox.setVisibility(8);
            cocos2dxEditBox.setBackgroundColor(0);
            cocos2dxEditBox.setTextColor(-1);
            cocos2dxEditBox.setSingleLine();
            cocos2dxEditBox.setOpenGLViewScaleX(this.f532a);
            cocos2dxEditBox.setPadding(Cocos2dxEditBoxHelper.getPadding(this.f532a), 0, 0, 0);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.leftMargin = this.f533b;
            layoutParams.topMargin = this.f534c;
            layoutParams.width = this.d;
            layoutParams.height = this.e;
            layoutParams.gravity = 51;
            Cocos2dxEditBoxHelper.mFrameLayout.addView(cocos2dxEditBox, layoutParams);
            cocos2dxEditBox.setTag(false);
            cocos2dxEditBox.addTextChangedListener(new C0012a(cocos2dxEditBox));
            cocos2dxEditBox.setOnFocusChangeListener(new b(cocos2dxEditBox));
            cocos2dxEditBox.setOnKeyListener(new c(cocos2dxEditBox));
            cocos2dxEditBox.setOnEditorActionListener(new d(cocos2dxEditBox));
            Cocos2dxEditBoxHelper.mEditBoxArray.put(this.f, cocos2dxEditBox);
        }
    }

    static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f548a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f549b;

        b(int i, int i2) {
            this.f548a = i;
            this.f549b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f548a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setReturnType(this.f549b);
            }
        }
    }

    static class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f550a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f551b;

        c(int i, int i2) {
            this.f550a = i;
            this.f551b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f550a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setTextHorizontalAlignment(this.f551b);
            }
        }
    }

    static class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f552a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f553b;

        d(int i, int i2) {
            this.f552a = i;
            this.f553b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f552a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setInputMode(this.f553b);
            }
        }
    }

    static class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f554a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f555b;

        e(int i, int i2) {
            this.f554a = i;
            this.f555b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f554a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setInputFlag(this.f555b);
            }
        }
    }

    static class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f556a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f557b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f558c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;

        f(int i, int i2, int i3, int i4, int i5) {
            this.f556a = i;
            this.f557b = i2;
            this.f558c = i3;
            this.d = i4;
            this.e = i5;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f556a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setEditBoxViewRect(this.f557b, this.f558c, this.d, this.e);
            }
        }
    }

    static class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f559a;

        g(int i) {
            this.f559a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBoxHelper.openKeyboardOnUiThread(this.f559a);
        }
    }

    static class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f560a;

        h(int i) {
            this.f560a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(this.f560a);
        }
    }

    static class i implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f561a;

        i(int i) {
            this.f561a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f561a);
            if (cocos2dxEditBox != null) {
                Cocos2dxEditBoxHelper.mEditBoxArray.remove(this.f561a);
                Cocos2dxEditBoxHelper.mFrameLayout.removeView(cocos2dxEditBox);
                Log.e(Cocos2dxEditBoxHelper.TAG, "remove EditBox");
            }
        }
    }

    static class j implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f562a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f563b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f564c;

        j(int i, String str, float f) {
            this.f562a = i;
            this.f563b = str;
            this.f564c = f;
        }

        @Override // java.lang.Runnable
        public void run() {
            Typeface typefaceCreate;
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f562a);
            if (cocos2dxEditBox != null) {
                if (this.f563b.isEmpty()) {
                    typefaceCreate = Typeface.DEFAULT;
                } else if (this.f563b.endsWith(".ttf")) {
                    try {
                        Cocos2dxActivity unused = Cocos2dxEditBoxHelper.mCocos2dxActivity;
                        typefaceCreate = Cocos2dxTypefaces.get(Cocos2dxActivity.getContext(), this.f563b);
                    } catch (Exception unused2) {
                        Log.e("Cocos2dxEditBoxHelper", "error to create ttf type face: " + this.f563b);
                        typefaceCreate = Typeface.create(this.f563b, 0);
                    }
                } else {
                    typefaceCreate = Typeface.create(this.f563b, 0);
                }
                float f = this.f564c;
                if (f >= 0.0f) {
                    cocos2dxEditBox.setTextSize(0, f);
                }
                cocos2dxEditBox.setTypeface(typefaceCreate);
            }
        }
    }

    static class k implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f565a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f566b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f567c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;

        k(int i, int i2, int i3, int i4, int i5) {
            this.f565a = i;
            this.f566b = i2;
            this.f567c = i3;
            this.d = i4;
            this.e = i5;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f565a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setTextColor(Color.argb(this.f566b, this.f567c, this.d, this.e));
            }
        }
    }

    static class l implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f568a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f569b;

        l(int i, String str) {
            this.f568a = i;
            this.f569b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f568a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setHint(this.f569b);
            }
        }
    }

    static class m implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f570a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f571b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f572c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;

        m(int i, int i2, int i3, int i4, int i5) {
            this.f570a = i;
            this.f571b = i2;
            this.f572c = i3;
            this.d = i4;
            this.e = i5;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f570a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setHintTextColor(Color.argb(this.f571b, this.f572c, this.d, this.e));
            }
        }
    }

    static class n implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f573a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f574b;

        n(int i, int i2) {
            this.f573a = i;
            this.f574b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f573a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setMaxLength(this.f574b);
            }
        }
    }

    static class o implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f575a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f576b;

        o(int i, boolean z) {
            this.f575a = i;
            this.f576b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f575a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setVisibility(this.f576b ? 0 : 8);
            }
        }
    }

    static class p implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f577a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f578b;

        p(int i, String str) {
            this.f577a = i;
            this.f578b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f577a);
            if (cocos2dxEditBox != null) {
                cocos2dxEditBox.setChangedTextProgrammatically(true);
                cocos2dxEditBox.setText(this.f578b);
                cocos2dxEditBox.setSelection(cocos2dxEditBox.getText().length());
            }
        }
    }

    public Cocos2dxEditBoxHelper(ResizeLayout resizeLayout) {
        mFrameLayout = resizeLayout;
        mCocos2dxActivity = (Cocos2dxActivity) Cocos2dxActivity.getContext();
        mEditBoxArray = new SparseArray<>();
    }

    public static void __editBoxEditingChanged(int i2, String str) {
        editBoxEditingChanged(i2, str);
    }

    public static void __editBoxEditingDidBegin(int i2) {
        editBoxEditingDidBegin(i2);
    }

    public static void __editBoxEditingDidEnd(int i2, String str, int i3) {
        editBoxEditingDidEnd(i2, str, i3);
    }

    public static void closeKeyboard(int i2) {
        mCocos2dxActivity.runOnUiThread(new h(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void closeKeyboardOnUiThread(int i2) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.e(TAG, "closeKeyboardOnUiThread doesn't run on UI thread!");
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) Cocos2dxActivity.getContext().getSystemService("input_method");
        Cocos2dxEditBox cocos2dxEditBox = mEditBoxArray.get(i2);
        if (cocos2dxEditBox != null) {
            inputMethodManager.hideSoftInputFromWindow(cocos2dxEditBox.getWindowToken(), 0);
            mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(false);
            mCocos2dxActivity.getGLSurfaceView().requestFocus();
            mCocos2dxActivity.hideVirtualButton();
        }
    }

    public static int createEditBox(int i2, int i3, int i4, int i5, float f2) {
        mCocos2dxActivity.runOnUiThread(new a(f2, i2, i3, i4, i5, mViewTag));
        int i6 = mViewTag;
        mViewTag = i6 + 1;
        return i6;
    }

    private static native void editBoxEditingChanged(int i2, String str);

    private static native void editBoxEditingDidBegin(int i2);

    private static native void editBoxEditingDidEnd(int i2, String str, int i3);

    public static int getPadding(float f2) {
        return (int) (mPadding * f2);
    }

    public static void openKeyboard(int i2) {
        mCocos2dxActivity.runOnUiThread(new g(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void openKeyboardOnUiThread(int i2) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.e(TAG, "openKeyboardOnUiThread doesn't run on UI thread!");
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) Cocos2dxActivity.getContext().getSystemService("input_method");
        Cocos2dxEditBox cocos2dxEditBox = mEditBoxArray.get(i2);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.requestFocus();
            mCocos2dxActivity.getGLSurfaceView().requestLayout();
            inputMethodManager.showSoftInput(cocos2dxEditBox, 0);
            mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(true);
        }
    }

    public static void removeEditBox(int i2) {
        mCocos2dxActivity.runOnUiThread(new i(i2));
    }

    public static void setEditBoxViewRect(int i2, int i3, int i4, int i5, int i6) {
        mCocos2dxActivity.runOnUiThread(new f(i2, i3, i4, i5, i6));
    }

    public static void setFont(int i2, String str, float f2) {
        mCocos2dxActivity.runOnUiThread(new j(i2, str, f2));
    }

    public static void setFontColor(int i2, int i3, int i4, int i5, int i6) {
        mCocos2dxActivity.runOnUiThread(new k(i2, i6, i3, i4, i5));
    }

    public static void setInputFlag(int i2, int i3) {
        mCocos2dxActivity.runOnUiThread(new e(i2, i3));
    }

    public static void setInputMode(int i2, int i3) {
        mCocos2dxActivity.runOnUiThread(new d(i2, i3));
    }

    public static void setMaxLength(int i2, int i3) {
        mCocos2dxActivity.runOnUiThread(new n(i2, i3));
    }

    public static void setPlaceHolderText(int i2, String str) {
        mCocos2dxActivity.runOnUiThread(new l(i2, str));
    }

    public static void setPlaceHolderTextColor(int i2, int i3, int i4, int i5, int i6) {
        mCocos2dxActivity.runOnUiThread(new m(i2, i6, i3, i4, i5));
    }

    public static void setReturnType(int i2, int i3) {
        mCocos2dxActivity.runOnUiThread(new b(i2, i3));
    }

    public static void setText(int i2, String str) {
        mCocos2dxActivity.runOnUiThread(new p(i2, str));
    }

    public static void setTextHorizontalAlignment(int i2, int i3) {
        mCocos2dxActivity.runOnUiThread(new c(i2, i3));
    }

    public static void setVisible(int i2, boolean z) {
        mCocos2dxActivity.runOnUiThread(new o(i2, z));
    }
}

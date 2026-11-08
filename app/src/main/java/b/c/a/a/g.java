package b.c.a.a;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class g implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f45a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f46b = 2;

    @TargetApi(8)
    private void c(String str, String str2, Throwable th) {
        Log.wtf(str, str2, th);
    }

    public void a(int i, String str, String str2) {
        a(i, str, str2, null);
    }

    public void a(int i, String str, String str2, Throwable th) {
        if (a() && a(i)) {
            if (i == 2) {
                Log.v(str, str2, th);
                return;
            }
            if (i == 3) {
                Log.d(str, str2, th);
                return;
            }
            if (i == 4) {
                Log.i(str, str2, th);
                return;
            }
            if (i == 5) {
                Log.w(str, str2, th);
                return;
            }
            if (i != 6) {
                if (i != 8) {
                    return;
                }
                if (Integer.valueOf(Build.VERSION.SDK).intValue() > 8) {
                    c(str, str2, th);
                    return;
                }
            }
            Log.e(str, str2, th);
        }
    }

    @Override // b.c.a.a.h
    public void a(String str, String str2) {
        a(6, str, str2);
    }

    @Override // b.c.a.a.h
    public void a(String str, String str2, Throwable th) {
        a(5, str, str2, th);
    }

    public boolean a() {
        return this.f45a;
    }

    public boolean a(int i) {
        return i >= this.f46b;
    }

    @Override // b.c.a.a.h
    public void b(String str, String str2) {
        a(2, str, str2);
    }

    @Override // b.c.a.a.h
    public void b(String str, String str2, Throwable th) {
        a(6, str, str2, th);
    }

    @Override // b.c.a.a.h
    public void c(String str, String str2) {
        a(5, str, str2);
    }

    @Override // b.c.a.a.h
    public void d(String str, String str2) {
        a(2, str, str2);
    }
}

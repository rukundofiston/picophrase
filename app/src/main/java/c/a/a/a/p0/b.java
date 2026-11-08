package c.a.a.a.p0;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f186b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f187c = false;
    private boolean d = false;
    private boolean e = false;

    public b(Object obj) {
        this.f185a = obj.toString();
    }

    public void a(Object obj) {
        if (a()) {
            Log.d(this.f185a, obj.toString());
        }
    }

    public void a(Object obj, Throwable th) {
        if (a()) {
            Log.d(this.f185a, obj.toString(), th);
        }
    }

    public boolean a() {
        return this.f186b;
    }

    public void b(Object obj) {
        if (b()) {
            Log.e(this.f185a, obj.toString());
        }
    }

    public void b(Object obj, Throwable th) {
        if (d()) {
            Log.w(this.f185a, obj.toString(), th);
        }
    }

    public boolean b() {
        return this.f187c;
    }

    public void c(Object obj) {
        if (c()) {
            Log.i(this.f185a, obj.toString());
        }
    }

    public boolean c() {
        return this.e;
    }

    public void d(Object obj) {
        if (d()) {
            Log.w(this.f185a, obj.toString());
        }
    }

    public boolean d() {
        return this.d;
    }
}

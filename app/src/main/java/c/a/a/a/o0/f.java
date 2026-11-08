package c.a.a.a.o0;

import c.a.a.a.k;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class f implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected k f181a;

    public f(k kVar) {
        c.a.a.a.x0.a.a(kVar, "Wrapped entity");
        this.f181a = kVar;
    }

    @Override // c.a.a.a.k
    public void a(OutputStream outputStream) {
        this.f181a.a(outputStream);
    }

    @Override // c.a.a.a.k
    public boolean a() {
        return this.f181a.a();
    }

    @Override // c.a.a.a.k
    public c.a.a.a.e c() {
        return this.f181a.c();
    }

    @Override // c.a.a.a.k
    public c.a.a.a.e d() {
        return this.f181a.d();
    }

    @Override // c.a.a.a.k
    public boolean e() {
        return this.f181a.e();
    }

    @Override // c.a.a.a.k
    public boolean g() {
        return this.f181a.g();
    }

    @Override // c.a.a.a.k
    @Deprecated
    public void h() {
        this.f181a.h();
    }

    @Override // c.a.a.a.k
    public InputStream k() {
        return this.f181a.k();
    }

    @Override // c.a.a.a.k
    public long m() {
        return this.f181a.m();
    }
}

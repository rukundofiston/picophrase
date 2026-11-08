package c.a.a.a.q0.g;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class c implements c.a.a.a.i0.d, c.a.a.a.i0.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Charset f198a;

    public c() {
        this(null);
    }

    public c(Charset charset) {
        this.f198a = charset;
    }

    @Override // c.a.a.a.i0.d
    public c.a.a.a.i0.c a(c.a.a.a.t0.e eVar) {
        return new b();
    }

    @Override // c.a.a.a.i0.e
    public c.a.a.a.i0.c a(c.a.a.a.v0.e eVar) {
        return new b(this.f198a);
    }
}

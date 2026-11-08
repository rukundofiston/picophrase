package c.a.a.a.q0.g;

import c.a.a.a.i0.o;
import c.a.a.a.q;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class b extends l {
    private boolean d;

    public b() {
        this(c.a.a.a.c.f55b);
    }

    public b(Charset charset) {
        super(charset);
        this.d = false;
    }

    @Override // c.a.a.a.i0.c
    @Deprecated
    public c.a.a.a.e a(c.a.a.a.i0.m mVar, q qVar) {
        return a(mVar, qVar, new c.a.a.a.v0.a());
    }

    @Override // c.a.a.a.q0.g.a, c.a.a.a.i0.l
    public c.a.a.a.e a(c.a.a.a.i0.m mVar, q qVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(mVar, "Credentials");
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        StringBuilder sb = new StringBuilder();
        sb.append(mVar.a().getName());
        sb.append(":");
        sb.append(mVar.b() == null ? "null" : mVar.b());
        byte[] bArrA = c.a.a.a.p0.a.a(c.a.a.a.x0.f.a(sb.toString(), a(qVar)), 2);
        c.a.a.a.x0.d dVar = new c.a.a.a.x0.d(32);
        dVar.a(e() ? "Proxy-Authorization" : "Authorization");
        dVar.a(": Basic ");
        dVar.a(bArrA, 0, bArrA.length);
        return new c.a.a.a.s0.q(dVar);
    }

    @Override // c.a.a.a.q0.g.a, c.a.a.a.i0.c
    public void a(c.a.a.a.e eVar) throws o {
        super.a(eVar);
        this.d = true;
    }

    @Override // c.a.a.a.i0.c
    public boolean a() {
        return this.d;
    }

    @Override // c.a.a.a.i0.c
    public String b() {
        return "basic";
    }

    @Override // c.a.a.a.i0.c
    public boolean d() {
        return false;
    }

    @Override // c.a.a.a.q0.g.a
    public String toString() {
        return "BASIC [complete=" + this.d + "]";
    }
}

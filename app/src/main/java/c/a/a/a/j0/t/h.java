package c.a.a.a.j0.t;

import c.a.a.a.c0;
import c.a.a.a.e0;
import c.a.a.a.s0.n;
import java.net.URI;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends b implements i, d {
    private c0 e;
    private URI f;
    private c.a.a.a.j0.r.a g;

    @Override // c.a.a.a.p
    public c0 a() {
        c0 c0Var = this.e;
        return c0Var != null ? c0Var : c.a.a.a.t0.f.b(k());
    }

    public void a(c0 c0Var) {
        this.e = c0Var;
    }

    public void a(c.a.a.a.j0.r.a aVar) {
        this.g = aVar;
    }

    public void a(URI uri) {
        this.f = uri;
    }

    public abstract String b();

    @Override // c.a.a.a.q
    public e0 g() {
        String strB = b();
        c0 c0VarA = a();
        URI uriH = h();
        String aSCIIString = uriH != null ? uriH.toASCIIString() : null;
        if (aSCIIString == null || aSCIIString.isEmpty()) {
            aSCIIString = "/";
        }
        return new n(strB, aSCIIString, c0VarA);
    }

    @Override // c.a.a.a.j0.t.i
    public URI h() {
        return this.f;
    }

    @Override // c.a.a.a.j0.t.d
    public c.a.a.a.j0.r.a i() {
        return this.g;
    }

    public String toString() {
        return b() + " " + h() + " " + a();
    }
}

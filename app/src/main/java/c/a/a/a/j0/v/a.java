package c.a.a.a.j0.v;

import c.a.a.a.n0.k;

/* JADX INFO: loaded from: classes.dex */
public class a extends c.a.a.a.v0.f {
    public a() {
    }

    public a(c.a.a.a.v0.e eVar) {
        super(eVar);
    }

    public static a a(c.a.a.a.v0.e eVar) {
        return eVar instanceof a ? (a) eVar : new a(eVar);
    }

    private <T> c.a.a.a.l0.a<T> b(String str, Class<T> cls) {
        return (c.a.a.a.l0.a) a(str, (Class) c.a.a.a.l0.a.class);
    }

    public void a(c.a.a.a.j0.a aVar) {
        a("http.auth.auth-cache", aVar);
    }

    public c.a.a.a.j0.a e() {
        return (c.a.a.a.j0.a) a("http.auth.auth-cache", c.a.a.a.j0.a.class);
    }

    public c.a.a.a.l0.a<c.a.a.a.i0.e> f() {
        return b("http.authscheme-registry", c.a.a.a.i0.e.class);
    }

    public c.a.a.a.n0.f g() {
        return (c.a.a.a.n0.f) a("http.cookie-origin", c.a.a.a.n0.f.class);
    }

    public c.a.a.a.n0.i h() {
        return (c.a.a.a.n0.i) a("http.cookie-spec", c.a.a.a.n0.i.class);
    }

    public c.a.a.a.l0.a<k> i() {
        return b("http.cookiespec-registry", k.class);
    }

    public c.a.a.a.j0.h j() {
        return (c.a.a.a.j0.h) a("http.cookie-store", c.a.a.a.j0.h.class);
    }

    public c.a.a.a.j0.i k() {
        return (c.a.a.a.j0.i) a("http.auth.credentials-provider", c.a.a.a.j0.i.class);
    }

    public c.a.a.a.m0.u.e l() {
        return (c.a.a.a.m0.u.e) a("http.route", c.a.a.a.m0.u.b.class);
    }

    public c.a.a.a.i0.h m() {
        return (c.a.a.a.i0.h) a("http.auth.proxy-scope", c.a.a.a.i0.h.class);
    }

    public c.a.a.a.j0.r.a n() {
        c.a.a.a.j0.r.a aVar = (c.a.a.a.j0.r.a) a("http.request-config", c.a.a.a.j0.r.a.class);
        return aVar != null ? aVar : c.a.a.a.j0.r.a.p;
    }

    public c.a.a.a.i0.h o() {
        return (c.a.a.a.i0.h) a("http.auth.target-scope", c.a.a.a.i0.h.class);
    }
}

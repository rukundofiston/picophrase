package c.a.a.a.q0.h;

import java.net.URI;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class o implements c.a.a.a.j0.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.j0.n f241a;

    public o(c.a.a.a.j0.n nVar) {
        this.f241a = nVar;
    }

    public c.a.a.a.j0.n a() {
        return this.f241a;
    }

    @Override // c.a.a.a.j0.o
    public c.a.a.a.j0.t.i a(c.a.a.a.q qVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) {
        URI uriB = this.f241a.b(sVar, eVar);
        return qVar.g().b().equalsIgnoreCase("HEAD") ? new c.a.a.a.j0.t.g(uriB) : new c.a.a.a.j0.t.f(uriB);
    }

    @Override // c.a.a.a.j0.o
    public boolean b(c.a.a.a.q qVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) {
        return this.f241a.a(sVar, eVar);
    }
}

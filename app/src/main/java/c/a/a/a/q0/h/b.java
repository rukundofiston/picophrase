package c.a.a.a.q0.h;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class b implements c.a.a.a.j0.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f221a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.j0.b f222b;

    private boolean a(c.a.a.a.i0.c cVar) {
        if (cVar == null || !cVar.a()) {
            return false;
        }
        String strB = cVar.b();
        return strB.equalsIgnoreCase("Basic") || strB.equalsIgnoreCase("Digest");
    }

    public c.a.a.a.j0.b a() {
        return this.f222b;
    }

    @Override // c.a.a.a.j0.c
    public Queue<c.a.a.a.i0.a> a(Map<String, c.a.a.a.e> map, c.a.a.a.n nVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(map, "Map of auth challenges");
        c.a.a.a.x0.a.a(nVar, "Host");
        c.a.a.a.x0.a.a(sVar, "HTTP response");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        LinkedList linkedList = new LinkedList();
        c.a.a.a.j0.i iVar = (c.a.a.a.j0.i) eVar.a("http.auth.credentials-provider");
        if (iVar == null) {
            this.f221a.a("Credentials provider not set in the context");
            return linkedList;
        }
        try {
            c.a.a.a.i0.c cVarA = this.f222b.a(map, sVar, eVar);
            cVarA.a(map.get(cVarA.b().toLowerCase(Locale.ROOT)));
            c.a.a.a.i0.m mVarA = iVar.a(new c.a.a.a.i0.g(nVar.b(), nVar.c(), cVarA.c(), cVarA.b()));
            if (mVarA != null) {
                linkedList.add(new c.a.a.a.i0.a(cVarA, mVarA));
            }
            return linkedList;
        } catch (c.a.a.a.i0.i e) {
            if (this.f221a.d()) {
                this.f221a.b(e.getMessage(), e);
            }
            return linkedList;
        }
    }

    @Override // c.a.a.a.j0.c
    public void a(c.a.a.a.n nVar, c.a.a.a.i0.c cVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.j0.a dVar = (c.a.a.a.j0.a) eVar.a("http.auth.auth-cache");
        if (a(cVar)) {
            if (dVar == null) {
                dVar = new d();
                eVar.a("http.auth.auth-cache", dVar);
            }
            if (this.f221a.a()) {
                this.f221a.a("Caching '" + cVar.b() + "' auth scheme for " + nVar);
            }
            dVar.a(nVar, cVar);
        }
    }

    @Override // c.a.a.a.j0.c
    public boolean a(c.a.a.a.n nVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) {
        return this.f222b.b(sVar, eVar);
    }

    @Override // c.a.a.a.j0.c
    public Map<String, c.a.a.a.e> b(c.a.a.a.n nVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) {
        return this.f222b.a(sVar, eVar);
    }

    @Override // c.a.a.a.j0.c
    public void b(c.a.a.a.n nVar, c.a.a.a.i0.c cVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.j0.a aVar = (c.a.a.a.j0.a) eVar.a("http.auth.auth-cache");
        if (aVar == null) {
            return;
        }
        if (this.f221a.a()) {
            this.f221a.a("Removing from cache '" + cVar.b() + "' auth scheme for " + nVar);
        }
        aVar.b(nVar);
    }
}

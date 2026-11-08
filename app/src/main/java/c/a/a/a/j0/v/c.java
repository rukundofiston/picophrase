package c.a.a.a.j0.v;

import c.a.a.a.i0.m;
import c.a.a.a.n;
import c.a.a.a.q;
import c.a.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class c implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f96a = new c.a.a.a.p0.b(c.class);

    private void a(n nVar, c.a.a.a.i0.c cVar, c.a.a.a.i0.h hVar, c.a.a.a.j0.i iVar) {
        String strB = cVar.b();
        if (this.f96a.a()) {
            this.f96a.a("Re-using cached '" + strB + "' auth scheme for " + nVar);
        }
        m mVarA = iVar.a(new c.a.a.a.i0.g(nVar, c.a.a.a.i0.g.f, strB));
        if (mVarA == null) {
            this.f96a.a("No credentials for preemptive authentication");
        } else {
            hVar.a("BASIC".equalsIgnoreCase(cVar.b()) ? c.a.a.a.i0.b.CHALLENGED : c.a.a.a.i0.b.SUCCESS);
            hVar.a(cVar, mVarA);
        }
    }

    @Override // c.a.a.a.r
    public void a(q qVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.i0.c cVarA;
        c.a.a.a.i0.c cVarA2;
        c.a.a.a.p0.b bVar;
        String str;
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        a aVarA = a.a(eVar);
        c.a.a.a.j0.a aVarE = aVarA.e();
        if (aVarE == null) {
            bVar = this.f96a;
            str = "Auth cache not set in the context";
        } else {
            c.a.a.a.j0.i iVarK = aVarA.k();
            if (iVarK == null) {
                bVar = this.f96a;
                str = "Credentials provider not set in the context";
            } else {
                c.a.a.a.m0.u.e eVarL = aVarA.l();
                if (eVarL == null) {
                    bVar = this.f96a;
                    str = "Route info not set in the context";
                } else {
                    n nVarC = aVarA.c();
                    if (nVarC != null) {
                        if (nVarC.c() < 0) {
                            nVarC = new n(nVarC.b(), eVarL.b().c(), nVarC.d());
                        }
                        c.a.a.a.i0.h hVarO = aVarA.o();
                        if (hVarO != null && hVarO.d() == c.a.a.a.i0.b.UNCHALLENGED && (cVarA2 = aVarE.a(nVarC)) != null) {
                            a(nVarC, cVarA2, hVarO, iVarK);
                        }
                        n nVarG = eVarL.g();
                        c.a.a.a.i0.h hVarM = aVarA.m();
                        if (nVarG == null || hVarM == null || hVarM.d() != c.a.a.a.i0.b.UNCHALLENGED || (cVarA = aVarE.a(nVarG)) == null) {
                            return;
                        }
                        a(nVarG, cVarA, hVarM, iVarK);
                        return;
                    }
                    bVar = this.f96a;
                    str = "Target host not set in the context";
                }
            }
        }
        bVar.a(str);
    }
}

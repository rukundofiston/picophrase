package c.a.a.a.j0.v;

import c.a.a.a.m0.n;
import c.a.a.a.q;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class g extends d {
    @Override // c.a.a.a.r
    public void a(q qVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.p0.b bVar;
        String str;
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        if (qVar.c("Proxy-Authorization")) {
            return;
        }
        n nVar = (n) eVar.a("http.connection");
        if (nVar == null) {
            bVar = this.f97a;
            str = "HTTP connection not set in the context";
        } else {
            if (nVar.b().f()) {
                return;
            }
            c.a.a.a.i0.h hVar = (c.a.a.a.i0.h) eVar.a("http.auth.proxy-scope");
            if (hVar != null) {
                if (this.f97a.a()) {
                    this.f97a.a("Proxy auth state: " + hVar.d());
                }
                a(hVar, qVar, eVar);
                return;
            }
            bVar = this.f97a;
            str = "Proxy auth state not set in the context";
        }
        bVar.a(str);
    }
}

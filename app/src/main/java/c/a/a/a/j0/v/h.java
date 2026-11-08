package c.a.a.a.j0.v;

import c.a.a.a.q;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class h extends d {
    @Override // c.a.a.a.r
    public void a(q qVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        if (qVar.g().b().equalsIgnoreCase("CONNECT") || qVar.c("Authorization")) {
            return;
        }
        c.a.a.a.i0.h hVar = (c.a.a.a.i0.h) eVar.a("http.auth.target-scope");
        if (hVar == null) {
            this.f97a.a("Target auth state not set in the context");
            return;
        }
        if (this.f97a.a()) {
            this.f97a.a("Target auth state: " + hVar.d());
        }
        a(hVar, qVar, eVar);
    }
}

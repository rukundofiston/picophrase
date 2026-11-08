package c.a.a.a.j0.v;

import c.a.a.a.q;
import c.a.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class e implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f99a = new c.a.a.a.p0.b(e.class);

    @Override // c.a.a.a.r
    public void a(q qVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        if (qVar.g().b().equalsIgnoreCase("CONNECT")) {
            qVar.a("Proxy-Connection", "Keep-Alive");
            return;
        }
        c.a.a.a.m0.u.e eVarL = a.a(eVar).l();
        if (eVarL == null) {
            this.f99a.a("Connection route not set in the context");
            return;
        }
        if ((eVarL.c() == 1 || eVarL.f()) && !qVar.c("Connection")) {
            qVar.b("Connection", "Keep-Alive");
        }
        if (eVarL.c() != 2 || eVarL.f() || qVar.c("Proxy-Connection")) {
            return;
        }
        qVar.b("Proxy-Connection", "Keep-Alive");
    }
}

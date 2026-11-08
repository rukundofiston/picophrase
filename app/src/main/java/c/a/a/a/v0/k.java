package c.a.a.a.v0;

import c.a.a.a.c0;
import c.a.a.a.q;
import c.a.a.a.r;
import c.a.a.a.v;

/* JADX INFO: loaded from: classes.dex */
public class k implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f419a;

    @Deprecated
    public k() {
        this(false);
    }

    public k(boolean z) {
        this.f419a = z;
    }

    @Override // c.a.a.a.r
    public void a(q qVar, e eVar) {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        if (qVar.c("Expect") || !(qVar instanceof c.a.a.a.l)) {
            return;
        }
        c0 c0VarA = qVar.g().a();
        c.a.a.a.k kVarC = ((c.a.a.a.l) qVar).c();
        if (kVarC == null || kVarC.m() == 0 || c0VarA.c(v.e) || !qVar.k().b("http.protocol.expect-continue", this.f419a)) {
            return;
        }
        qVar.b("Expect", "100-continue");
    }
}

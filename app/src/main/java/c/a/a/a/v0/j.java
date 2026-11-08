package c.a.a.a.v0;

import c.a.a.a.b0;
import c.a.a.a.c0;
import c.a.a.a.q;
import c.a.a.a.r;
import c.a.a.a.v;

/* JADX INFO: loaded from: classes.dex */
public class j implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f418a;

    public j() {
        this(false);
    }

    public j(boolean z) {
        this.f418a = z;
    }

    @Override // c.a.a.a.r
    public void a(q qVar, e eVar) throws b0 {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        if (qVar instanceof c.a.a.a.l) {
            if (this.f418a) {
                qVar.a("Transfer-Encoding");
                qVar.a("Content-Length");
            } else {
                if (qVar.c("Transfer-Encoding")) {
                    throw new b0("Transfer-encoding header already present");
                }
                if (qVar.c("Content-Length")) {
                    throw new b0("Content-Length header already present");
                }
            }
            c0 c0VarA = qVar.g().a();
            c.a.a.a.k kVarC = ((c.a.a.a.l) qVar).c();
            if (kVarC == null) {
                qVar.b("Content-Length", "0");
                return;
            }
            if (!kVarC.g() && kVarC.m() >= 0) {
                qVar.b("Content-Length", Long.toString(kVarC.m()));
            } else {
                if (c0VarA.c(v.e)) {
                    throw new b0("Chunked transfer encoding not allowed for " + c0VarA);
                }
                qVar.b("Transfer-Encoding", "chunked");
            }
            if (kVarC.d() != null && !qVar.c("Content-Type")) {
                qVar.a(kVarC.d());
            }
            if (kVarC.c() == null || qVar.c("Content-Encoding")) {
                return;
            }
            qVar.a(kVarC.c());
        }
    }
}

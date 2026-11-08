package c.a.a.a.v0;

import c.a.a.a.b0;
import c.a.a.a.c0;
import c.a.a.a.q;
import c.a.a.a.s;
import c.a.a.a.v;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f415a;

    public h() {
        this(3000);
    }

    public h(int i) {
        c.a.a.a.x0.a.b(i, "Wait for continue time");
        this.f415a = i;
    }

    private static void a(c.a.a.a.i iVar) {
        try {
            iVar.close();
        } catch (IOException unused) {
        }
    }

    protected s a(q qVar, c.a.a.a.i iVar, e eVar) {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.x0.a.a(iVar, "Client connection");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        s sVarG = null;
        int iC = 0;
        while (true) {
            if (sVarG != null && iC >= 200) {
                return sVarG;
            }
            sVarG = iVar.g();
            if (a(qVar, sVarG)) {
                iVar.a(sVarG);
            }
            iC = sVarG.j().c();
        }
    }

    public void a(q qVar, g gVar, e eVar) {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.x0.a.a(gVar, "HTTP processor");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        eVar.a("http.request", qVar);
        gVar.a(qVar, eVar);
    }

    public void a(s sVar, g gVar, e eVar) {
        c.a.a.a.x0.a.a(sVar, "HTTP response");
        c.a.a.a.x0.a.a(gVar, "HTTP processor");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        eVar.a("http.response", sVar);
        gVar.a(sVar, eVar);
    }

    protected boolean a(q qVar, s sVar) {
        int iC;
        return ("HEAD".equalsIgnoreCase(qVar.g().b()) || (iC = sVar.j().c()) < 200 || iC == 204 || iC == 304 || iC == 205) ? false : true;
    }

    protected s b(q qVar, c.a.a.a.i iVar, e eVar) throws b0 {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.x0.a.a(iVar, "Client connection");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        eVar.a("http.connection", iVar);
        eVar.a("http.request_sent", Boolean.FALSE);
        iVar.a(qVar);
        s sVar = null;
        if (qVar instanceof c.a.a.a.l) {
            boolean z = true;
            c0 c0VarA = qVar.g().a();
            c.a.a.a.l lVar = (c.a.a.a.l) qVar;
            if (lVar.f() && !c0VarA.c(v.e)) {
                iVar.flush();
                if (iVar.b(this.f415a)) {
                    s sVarG = iVar.g();
                    if (a(qVar, sVarG)) {
                        iVar.a(sVarG);
                    }
                    int iC = sVarG.j().c();
                    if (iC >= 200) {
                        z = false;
                        sVar = sVarG;
                    } else if (iC != 100) {
                        throw new b0("Unexpected response: " + sVarG.j());
                    }
                }
            }
            if (z) {
                iVar.a(lVar);
            }
        }
        iVar.flush();
        eVar.a("http.request_sent", Boolean.TRUE);
        return sVar;
    }

    public s c(q qVar, c.a.a.a.i iVar, e eVar) throws IOException, c.a.a.a.m {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.x0.a.a(iVar, "Client connection");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        try {
            s sVarB = b(qVar, iVar, eVar);
            return sVarB == null ? a(qVar, iVar, eVar) : sVarB;
        } catch (c.a.a.a.m e) {
            a(iVar);
            throw e;
        } catch (IOException e2) {
            a(iVar);
            throw e2;
        } catch (RuntimeException e3) {
            a(iVar);
            throw e3;
        }
    }
}

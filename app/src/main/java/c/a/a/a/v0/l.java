package c.a.a.a.v0;

import c.a.a.a.b0;
import c.a.a.a.c0;
import c.a.a.a.o;
import c.a.a.a.q;
import c.a.a.a.r;
import c.a.a.a.v;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public class l implements r {
    @Override // c.a.a.a.r
    public void a(q qVar, e eVar) throws b0 {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        f fVarA = f.a(eVar);
        c0 c0VarA = qVar.g().a();
        if ((qVar.g().b().equalsIgnoreCase("CONNECT") && c0VarA.c(v.e)) || qVar.c("Host")) {
            return;
        }
        c.a.a.a.n nVarC = fVarA.c();
        if (nVarC == null) {
            c.a.a.a.j jVarA = fVarA.a();
            if (jVarA instanceof o) {
                o oVar = (o) jVarA;
                InetAddress remoteAddress = oVar.getRemoteAddress();
                int iH = oVar.h();
                if (remoteAddress != null) {
                    nVarC = new c.a.a.a.n(remoteAddress.getHostName(), iH);
                }
            }
            if (nVarC == null) {
                if (!c0VarA.c(v.e)) {
                    throw new b0("Target host missing");
                }
                return;
            }
        }
        qVar.b("Host", nVarC.e());
    }
}

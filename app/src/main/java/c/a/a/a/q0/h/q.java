package c.a.a.a.q0.h;

import java.security.Principal;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: classes.dex */
public class q implements c.a.a.a.j0.q {
    static {
        new q();
    }

    private static Principal a(c.a.a.a.i0.h hVar) {
        c.a.a.a.i0.m mVarC;
        c.a.a.a.i0.c cVarB = hVar.b();
        if (cVarB == null || !cVarB.a() || !cVarB.d() || (mVarC = hVar.c()) == null) {
            return null;
        }
        return mVarC.a();
    }

    @Override // c.a.a.a.j0.q
    public Object a(c.a.a.a.v0.e eVar) {
        Principal principalA;
        SSLSession sSLSessionD;
        c.a.a.a.j0.v.a aVarA = c.a.a.a.j0.v.a.a(eVar);
        c.a.a.a.i0.h hVarO = aVarA.o();
        if (hVarO != null) {
            principalA = a(hVarO);
            if (principalA == null) {
                principalA = a(aVarA.m());
            }
        } else {
            principalA = null;
        }
        if (principalA != null) {
            return principalA;
        }
        c.a.a.a.j jVarA = aVarA.a();
        return (jVarA.isOpen() && (jVarA instanceof c.a.a.a.m0.p) && (sSLSessionD = ((c.a.a.a.m0.p) jVarA).d()) != null) ? sSLSessionD.getLocalPrincipal() : principalA;
    }
}

package c.a.a.a.q0;

import c.a.a.a.s;

/* JADX INFO: loaded from: classes.dex */
public class b implements c.a.a.a.b {
    static {
        new b();
    }

    private boolean a(s sVar) {
        int iC = sVar.j().c();
        return (iC < 200 || iC == 204 || iC == 304 || iC == 205) ? false : true;
    }

    @Override // c.a.a.a.b
    public boolean a(c.a.a.a.s sVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(sVar, "HTTP response");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        c.a.a.a.c0 c0VarA = sVar.j().a();
        c.a.a.a.e eVarD = sVar.d("Transfer-Encoding");
        if (eVarD != null) {
            if (!"chunked".equalsIgnoreCase(eVarD.getValue())) {
                return false;
            }
        } else if (a(sVar)) {
            c.a.a.a.e[] eVarArrB = sVar.b("Content-Length");
            if (eVarArrB.length != 1) {
                return false;
            }
            try {
                if (Integer.parseInt(eVarArrB[0].getValue()) < 0) {
                    return false;
                }
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        c.a.a.a.e[] eVarArrB2 = sVar.b("Connection");
        if (eVarArrB2.length == 0) {
            eVarArrB2 = sVar.b("Proxy-Connection");
        }
        if (eVarArrB2.length != 0) {
            try {
                c.a.a.a.s0.p pVar = new c.a.a.a.s0.p(new c.a.a.a.s0.e(eVarArrB2, null));
                boolean z = false;
                while (pVar.hasNext()) {
                    String strNextToken = pVar.nextToken();
                    if ("Close".equalsIgnoreCase(strNextToken)) {
                        return false;
                    }
                    if ("Keep-Alive".equalsIgnoreCase(strNextToken)) {
                        z = true;
                    }
                }
                if (z) {
                    return true;
                }
            } catch (c.a.a.a.a0 unused2) {
                return false;
            }
        }
        return !c0VarA.c(c.a.a.a.v.e);
    }
}

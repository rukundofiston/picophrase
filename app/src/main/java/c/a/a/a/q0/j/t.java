package c.a.a.a.q0.j;

import java.util.Locale;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes.dex */
public class t extends f {
    private static boolean a(String str) {
        String upperCase = str.toUpperCase(Locale.ROOT);
        return upperCase.endsWith(".COM") || upperCase.endsWith(".EDU") || upperCase.endsWith(".NET") || upperCase.endsWith(".GOV") || upperCase.endsWith(".MIL") || upperCase.endsWith(".ORG") || upperCase.endsWith(".INT");
    }

    @Override // c.a.a.a.q0.j.f, c.a.a.a.n0.b
    public String a() {
        return "domain";
    }

    @Override // c.a.a.a.q0.j.f, c.a.a.a.n0.d
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.h {
        String strA = fVar.a();
        String strC = cVar.c();
        if (!strA.equals(strC) && !f.a(strC, strA)) {
            throw new c.a.a.a.n0.h("Illegal domain attribute \"" + strC + "\". Domain of origin: \"" + strA + "\"");
        }
        if (strA.contains(".")) {
            int iCountTokens = new StringTokenizer(strC, ".").countTokens();
            if (!a(strC)) {
                if (iCountTokens >= 3) {
                    return;
                }
                throw new c.a.a.a.n0.h("Domain attribute \"" + strC + "\" violates the Netscape cookie specification");
            }
            if (iCountTokens >= 2) {
                return;
            }
            throw new c.a.a.a.n0.h("Domain attribute \"" + strC + "\" violates the Netscape cookie specification for special domains");
        }
    }

    @Override // c.a.a.a.q0.j.f, c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) throws c.a.a.a.n0.m {
        c.a.a.a.x0.a.a(oVar, "Cookie");
        if (c.a.a.a.x0.i.b(str)) {
            throw new c.a.a.a.n0.m("Blank or null value for domain attribute");
        }
        oVar.d(str);
    }

    @Override // c.a.a.a.q0.j.f, c.a.a.a.n0.d
    public boolean b(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        String strA = fVar.a();
        String strC = cVar.c();
        if (strC == null) {
            return false;
        }
        return strA.endsWith(strC);
    }
}

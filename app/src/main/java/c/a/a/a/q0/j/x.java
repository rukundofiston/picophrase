package c.a.a.a.q0.j;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class x implements c.a.a.a.n0.b {
    @Override // c.a.a.a.n0.b
    public String a() {
        return "domain";
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.h {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        String strA = fVar.a();
        String strC = cVar.c();
        if (strC == null) {
            throw new c.a.a.a.n0.h("Cookie domain may not be null");
        }
        if (strC.equals(strA)) {
            return;
        }
        if (strC.indexOf(46) == -1) {
            throw new c.a.a.a.n0.h("Domain attribute \"" + strC + "\" does not match the host \"" + strA + "\"");
        }
        if (!strC.startsWith(".")) {
            throw new c.a.a.a.n0.h("Domain attribute \"" + strC + "\" violates RFC 2109: domain must start with a dot");
        }
        int iIndexOf = strC.indexOf(46, 1);
        if (iIndexOf < 0 || iIndexOf == strC.length() - 1) {
            throw new c.a.a.a.n0.h("Domain attribute \"" + strC + "\" violates RFC 2109: domain must contain an embedded dot");
        }
        String lowerCase = strA.toLowerCase(Locale.ROOT);
        if (lowerCase.endsWith(strC)) {
            if (lowerCase.substring(0, lowerCase.length() - strC.length()).indexOf(46) == -1) {
                return;
            }
            throw new c.a.a.a.n0.h("Domain attribute \"" + strC + "\" violates RFC 2109: host minus domain may not contain any dots");
        }
        throw new c.a.a.a.n0.h("Illegal domain attribute \"" + strC + "\". Domain of origin: \"" + lowerCase + "\"");
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) throws c.a.a.a.n0.m {
        c.a.a.a.x0.a.a(oVar, "Cookie");
        if (str == null) {
            throw new c.a.a.a.n0.m("Missing value for domain attribute");
        }
        if (str.trim().isEmpty()) {
            throw new c.a.a.a.n0.m("Blank value for domain attribute");
        }
        oVar.d(str);
    }

    @Override // c.a.a.a.n0.d
    public boolean b(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        String strA = fVar.a();
        String strC = cVar.c();
        if (strC == null) {
            return false;
        }
        return strA.equals(strC) || (strC.startsWith(".") && strA.endsWith(strC));
    }
}

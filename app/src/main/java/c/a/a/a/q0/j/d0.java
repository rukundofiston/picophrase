package c.a.a.a.q0.j;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class d0 implements c.a.a.a.n0.b {
    @Override // c.a.a.a.n0.b
    public String a() {
        return "domain";
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.h {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        String lowerCase = fVar.a().toLowerCase(Locale.ROOT);
        if (cVar.c() == null) {
            throw new c.a.a.a.n0.h("Invalid cookie state: domain not specified");
        }
        String lowerCase2 = cVar.c().toLowerCase(Locale.ROOT);
        if (!(cVar instanceof c.a.a.a.n0.a) || !((c.a.a.a.n0.a) cVar).c("domain")) {
            if (cVar.c().equals(lowerCase)) {
                return;
            }
            throw new c.a.a.a.n0.h("Illegal domain attribute: \"" + cVar.c() + "\".Domain of origin: \"" + lowerCase + "\"");
        }
        if (!lowerCase2.startsWith(".")) {
            throw new c.a.a.a.n0.h("Domain attribute \"" + cVar.c() + "\" violates RFC 2109: domain must start with a dot");
        }
        int iIndexOf = lowerCase2.indexOf(46, 1);
        if ((iIndexOf < 0 || iIndexOf == lowerCase2.length() - 1) && !lowerCase2.equals(".local")) {
            throw new c.a.a.a.n0.h("Domain attribute \"" + cVar.c() + "\" violates RFC 2965: the value contains no embedded dots and the value is not .local");
        }
        if (!a(lowerCase, lowerCase2)) {
            throw new c.a.a.a.n0.h("Domain attribute \"" + cVar.c() + "\" violates RFC 2965: effective host name does not domain-match domain attribute.");
        }
        if (lowerCase.substring(0, lowerCase.length() - lowerCase2.length()).indexOf(46) == -1) {
            return;
        }
        throw new c.a.a.a.n0.h("Domain attribute \"" + cVar.c() + "\" violates RFC 2965: effective host minus domain may not contain any dots");
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
        String lowerCase = str.toLowerCase(Locale.ROOT);
        if (!str.startsWith(".")) {
            lowerCase = '.' + lowerCase;
        }
        oVar.d(lowerCase);
    }

    public boolean a(String str, String str2) {
        return str.equals(str2) || (str2.startsWith(".") && str.endsWith(str2));
    }

    @Override // c.a.a.a.n0.d
    public boolean b(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        String lowerCase = fVar.a().toLowerCase(Locale.ROOT);
        String strC = cVar.c();
        return a(lowerCase, strC) && lowerCase.substring(0, lowerCase.length() - strC.length()).indexOf(46) == -1;
    }
}

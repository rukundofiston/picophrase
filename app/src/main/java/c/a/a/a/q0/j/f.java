package c.a.a.a.q0.j;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class f implements c.a.a.a.n0.b {
    static boolean a(String str, String str2) {
        if (!c.a.a.a.m0.x.a.a(str2) && !c.a.a.a.m0.x.a.b(str2)) {
            if (str.startsWith(".")) {
                str = str.substring(1);
            }
            if (str2.endsWith(str)) {
                int length = str2.length() - str.length();
                if (length == 0) {
                    return true;
                }
                if (length > 1 && str2.charAt(length - 1) == '.') {
                    return true;
                }
            }
        }
        return false;
    }

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
            throw new c.a.a.a.n0.h("Cookie 'domain' may not be null");
        }
        if (strA.equals(strC) || a(strC, strA)) {
            return;
        }
        throw new c.a.a.a.n0.h("Illegal 'domain' attribute \"" + strC + "\". Domain of origin: \"" + strA + "\"");
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) throws c.a.a.a.n0.m {
        c.a.a.a.x0.a.a(oVar, "Cookie");
        if (c.a.a.a.x0.i.b(str)) {
            throw new c.a.a.a.n0.m("Blank or null value for domain attribute");
        }
        if (str.endsWith(".")) {
            return;
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        oVar.d(str.toLowerCase(Locale.ROOT));
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
        if (strC.startsWith(".")) {
            strC = strC.substring(1);
        }
        String lowerCase = strC.toLowerCase(Locale.ROOT);
        if (strA.equals(lowerCase)) {
            return true;
        }
        if ((cVar instanceof c.a.a.a.n0.a) && ((c.a.a.a.n0.a) cVar).c("domain")) {
            return a(lowerCase, strA);
        }
        return false;
    }
}

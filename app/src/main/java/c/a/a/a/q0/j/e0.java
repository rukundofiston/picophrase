package c.a.a.a.q0.j;

import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes.dex */
public class e0 implements c.a.a.a.n0.b {
    private static boolean a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private static int[] a(String str) throws c.a.a.a.n0.m {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            try {
                iArr[i] = Integer.parseInt(stringTokenizer.nextToken().trim());
                if (iArr[i] < 0) {
                    throw new c.a.a.a.n0.m("Invalid Port attribute.");
                }
                i++;
            } catch (NumberFormatException e) {
                throw new c.a.a.a.n0.m("Invalid Port attribute: " + e.getMessage());
            }
        }
        return iArr;
    }

    @Override // c.a.a.a.n0.b
    public String a() {
        return "port";
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.h {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        int iC = fVar.c();
        if ((cVar instanceof c.a.a.a.n0.a) && ((c.a.a.a.n0.a) cVar).c("port") && !a(iC, cVar.f())) {
            throw new c.a.a.a.n0.h("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
        }
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) {
        c.a.a.a.x0.a.a(oVar, "Cookie");
        if (oVar instanceof c.a.a.a.n0.n) {
            c.a.a.a.n0.n nVar = (c.a.a.a.n0.n) oVar;
            if (str == null || str.trim().isEmpty()) {
                return;
            }
            nVar.a(a(str));
        }
    }

    @Override // c.a.a.a.n0.d
    public boolean b(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        int iC = fVar.c();
        if ((cVar instanceof c.a.a.a.n0.a) && ((c.a.a.a.n0.a) cVar).c("port")) {
            return cVar.f() != null && a(iC, cVar.f());
        }
        return true;
    }
}

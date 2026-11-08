package c.a.a.a.q0.j;

/* JADX INFO: loaded from: classes.dex */
public class i implements c.a.a.a.n0.b {
    static boolean a(String str, String str2) {
        if (str2 == null) {
            str2 = "/";
        }
        if (str2.length() > 1 && str2.endsWith("/")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        return str.startsWith(str2) && (str2.equals("/") || str.length() == str2.length() || str.charAt(str2.length()) == '/');
    }

    @Override // c.a.a.a.n0.b
    public String a() {
        return "path";
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.h {
        if (b(cVar, fVar)) {
            return;
        }
        throw new c.a.a.a.n0.h("Illegal 'path' attribute \"" + cVar.e() + "\". Path of origin: \"" + fVar.b() + "\"");
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) {
        c.a.a.a.x0.a.a(oVar, "Cookie");
        if (c.a.a.a.x0.i.b(str)) {
            str = "/";
        }
        oVar.b(str);
    }

    @Override // c.a.a.a.n0.d
    public boolean b(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        return a(fVar.b(), cVar.e());
    }
}

package c.a.a.a.q0.j;

/* JADX INFO: loaded from: classes.dex */
public class h0 implements c.a.a.a.n0.b {
    @Override // c.a.a.a.n0.b
    public String a() {
        return "version";
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.h {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        if ((cVar instanceof c.a.a.a.n0.n) && (cVar instanceof c.a.a.a.n0.a) && !((c.a.a.a.n0.a) cVar).c("version")) {
            throw new c.a.a.a.n0.h("Violates RFC 2965. Version attribute is required.");
        }
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) throws c.a.a.a.n0.m {
        int i;
        c.a.a.a.x0.a.a(oVar, "Cookie");
        if (str == null) {
            throw new c.a.a.a.n0.m("Missing value for version attribute");
        }
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            i = -1;
        }
        if (i < 0) {
            throw new c.a.a.a.n0.m("Invalid cookie version.");
        }
        oVar.a(i);
    }

    @Override // c.a.a.a.n0.d
    public boolean b(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        return true;
    }
}

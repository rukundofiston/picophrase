package c.a.a.a.q0.j;

/* JADX INFO: loaded from: classes.dex */
public class a0 extends a implements c.a.a.a.n0.b {
    @Override // c.a.a.a.n0.b
    public String a() {
        return "version";
    }

    @Override // c.a.a.a.q0.j.a, c.a.a.a.n0.d
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.h {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        if (cVar.b() < 0) {
            throw new c.a.a.a.n0.h("Cookie version may not be negative");
        }
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) throws c.a.a.a.n0.m {
        c.a.a.a.x0.a.a(oVar, "Cookie");
        if (str == null) {
            throw new c.a.a.a.n0.m("Missing value for version attribute");
        }
        if (str.trim().isEmpty()) {
            throw new c.a.a.a.n0.m("Blank value for version attribute");
        }
        try {
            oVar.a(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            throw new c.a.a.a.n0.m("Invalid version: " + e.getMessage());
        }
    }
}

package c.a.a.a.q0.j;

/* JADX INFO: loaded from: classes.dex */
public class o extends a implements c.a.a.a.n0.b {
    @Override // c.a.a.a.n0.b
    public String a() {
        return "version";
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) throws c.a.a.a.n0.m {
        c.a.a.a.x0.a.a(oVar, "Cookie");
        if (str == null) {
            throw new c.a.a.a.n0.m("Missing value for version attribute");
        }
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
        oVar.a(i);
    }
}

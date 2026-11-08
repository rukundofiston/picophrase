package c.a.a.a.q0.j;

/* JADX INFO: loaded from: classes.dex */
public class j extends a implements c.a.a.a.n0.b {
    @Override // c.a.a.a.n0.b
    public String a() {
        return "secure";
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) {
        c.a.a.a.x0.a.a(oVar, "Cookie");
        oVar.a(true);
    }

    @Override // c.a.a.a.q0.j.a, c.a.a.a.n0.d
    public boolean b(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        return !cVar.a() || fVar.d();
    }
}

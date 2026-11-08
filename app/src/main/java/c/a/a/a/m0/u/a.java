package c.a.a.a.m0.u;

/* JADX INFO: loaded from: classes.dex */
public class a implements c {
    protected int a(e eVar) {
        return eVar.c() > 1 ? 2 : 1;
    }

    @Override // c.a.a.a.m0.u.c
    public int a(e eVar, e eVar2) {
        c.a.a.a.x0.a.a(eVar, "Planned route");
        if (eVar2 == null || eVar2.c() < 1) {
            return a(eVar);
        }
        return eVar.c() > 1 ? c(eVar, eVar2) : b(eVar, eVar2);
    }

    protected int b(e eVar, e eVar2) {
        if (eVar2.c() <= 1 && eVar.b().equals(eVar2.b()) && eVar.a() == eVar2.a()) {
            return (eVar.d() == null || eVar.d().equals(eVar2.d())) ? 0 : -1;
        }
        return -1;
    }

    protected int c(e eVar, e eVar2) {
        int iC;
        int iC2;
        if (eVar2.c() <= 1 || !eVar.b().equals(eVar2.b()) || (iC = eVar.c()) < (iC2 = eVar2.c())) {
            return -1;
        }
        for (int i = 0; i < iC2 - 1; i++) {
            if (!eVar.a(i).equals(eVar2.a(i))) {
                return -1;
            }
        }
        if (iC > iC2) {
            return 4;
        }
        if ((eVar2.f() && !eVar.f()) || (eVar2.e() && !eVar.e())) {
            return -1;
        }
        if (eVar.f() && !eVar2.f()) {
            return 3;
        }
        if (!eVar.e() || eVar2.e()) {
            return eVar.a() != eVar2.a() ? -1 : 0;
        }
        return 5;
    }
}

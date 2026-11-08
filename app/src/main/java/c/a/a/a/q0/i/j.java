package c.a.a.a.q0.i;

import c.a.a.a.m0.s;

/* JADX INFO: loaded from: classes.dex */
public class j implements c.a.a.a.m0.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f268a = new j();

    @Override // c.a.a.a.m0.r
    public int a(c.a.a.a.n nVar) throws s {
        c.a.a.a.x0.a.a(nVar, "HTTP host");
        int iC = nVar.c();
        if (iC > 0) {
            return iC;
        }
        String strD = nVar.d();
        if (strD.equalsIgnoreCase("http")) {
            return 80;
        }
        if (strD.equalsIgnoreCase("https")) {
            return 443;
        }
        throw new s(strD + " protocol is not supported");
    }
}

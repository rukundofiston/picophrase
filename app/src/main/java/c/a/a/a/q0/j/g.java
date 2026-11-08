package c.a.a.a.q0.j;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class g extends a implements c.a.a.a.n0.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String[] f304a;

    public g(String[] strArr) {
        c.a.a.a.x0.a.a(strArr, "Array of date patterns");
        this.f304a = strArr;
    }

    @Override // c.a.a.a.n0.b
    public String a() {
        return "expires";
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) throws c.a.a.a.n0.m {
        c.a.a.a.x0.a.a(oVar, "Cookie");
        if (str == null) {
            throw new c.a.a.a.n0.m("Missing value for 'expires' attribute");
        }
        Date dateA = c.a.a.a.j0.w.b.a(str, this.f304a);
        if (dateA != null) {
            oVar.b(dateA);
            return;
        }
        throw new c.a.a.a.n0.m("Invalid 'expires' attribute: " + str);
    }
}

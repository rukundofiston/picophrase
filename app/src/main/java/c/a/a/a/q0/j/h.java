package c.a.a.a.q0.j;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class h extends a implements c.a.a.a.n0.b {
    @Override // c.a.a.a.n0.b
    public String a() {
        return "max-age";
    }

    @Override // c.a.a.a.n0.d
    public void a(c.a.a.a.n0.o oVar, String str) throws c.a.a.a.n0.m {
        c.a.a.a.x0.a.a(oVar, "Cookie");
        if (str == null) {
            throw new c.a.a.a.n0.m("Missing value for 'max-age' attribute");
        }
        try {
            int i = Integer.parseInt(str);
            if (i >= 0) {
                oVar.b(new Date(System.currentTimeMillis() + (((long) i) * 1000)));
                return;
            }
            throw new c.a.a.a.n0.m("Negative 'max-age' attribute: " + str);
        } catch (NumberFormatException unused) {
            throw new c.a.a.a.n0.m("Invalid 'max-age' attribute: " + str);
        }
    }
}

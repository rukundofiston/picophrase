package c.a.a.a.q0.h;

/* JADX INFO: loaded from: classes.dex */
public class j implements c.a.a.a.m0.g {
    static {
        new j();
    }

    @Override // c.a.a.a.m0.g
    public long a(c.a.a.a.s sVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(sVar, "HTTP response");
        c.a.a.a.s0.d dVar = new c.a.a.a.s0.d(sVar.e("Keep-Alive"));
        while (dVar.hasNext()) {
            c.a.a.a.f fVarNextElement = dVar.nextElement();
            String name = fVarNextElement.getName();
            String value = fVarNextElement.getValue();
            if (value != null && name.equalsIgnoreCase("timeout")) {
                try {
                    return Long.parseLong(value) * 1000;
                } catch (NumberFormatException unused) {
                }
            }
        }
        return -1L;
    }
}

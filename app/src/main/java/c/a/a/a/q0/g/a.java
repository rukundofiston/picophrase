package c.a.a.a.q0.g;

import c.a.a.a.i0.o;
import c.a.a.a.q;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements c.a.a.a.i0.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c.a.a.a.i0.k f197a;

    @Override // c.a.a.a.i0.l
    public c.a.a.a.e a(c.a.a.a.i0.m mVar, q qVar, c.a.a.a.v0.e eVar) {
        return a(mVar, qVar);
    }

    @Override // c.a.a.a.i0.c
    public void a(c.a.a.a.e eVar) throws o {
        c.a.a.a.i0.k kVar;
        c.a.a.a.x0.d dVar;
        int iB;
        c.a.a.a.x0.a.a(eVar, "Header");
        String name = eVar.getName();
        if (name.equalsIgnoreCase("WWW-Authenticate")) {
            kVar = c.a.a.a.i0.k.TARGET;
        } else {
            if (!name.equalsIgnoreCase("Proxy-Authenticate")) {
                throw new o("Unexpected header name: " + name);
            }
            kVar = c.a.a.a.i0.k.PROXY;
        }
        this.f197a = kVar;
        if (eVar instanceof c.a.a.a.d) {
            c.a.a.a.d dVar2 = (c.a.a.a.d) eVar;
            dVar = dVar2.c();
            iB = dVar2.b();
        } else {
            String value = eVar.getValue();
            if (value == null) {
                throw new o("Header value is null");
            }
            dVar = new c.a.a.a.x0.d(value.length());
            dVar.a(value);
            iB = 0;
        }
        while (iB < dVar.length() && c.a.a.a.v0.d.a(dVar.charAt(iB))) {
            iB++;
        }
        int i = iB;
        while (i < dVar.length() && !c.a.a.a.v0.d.a(dVar.charAt(i))) {
            i++;
        }
        String strA = dVar.a(iB, i);
        if (strA.equalsIgnoreCase(b())) {
            a(dVar, i, dVar.length());
            return;
        }
        throw new o("Invalid scheme identifier: " + strA);
    }

    protected abstract void a(c.a.a.a.x0.d dVar, int i, int i2);

    public boolean e() {
        c.a.a.a.i0.k kVar = this.f197a;
        return kVar != null && kVar == c.a.a.a.i0.k.PROXY;
    }

    public String toString() {
        String strB = b();
        return strB != null ? strB.toUpperCase(Locale.ROOT) : super.toString();
    }
}

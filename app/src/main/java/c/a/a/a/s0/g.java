package c.a.a.a.s0;

import c.a.a.a.y;
import java.util.ArrayList;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public class g implements s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g f371b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final BitSet f372c;
    private static final BitSet d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final w f373a = w.f401a;

    static {
        new g();
        f371b = new g();
        f372c = w.a(61, 59, 44);
        d = w.a(59, 44);
    }

    public static c.a.a.a.f[] a(String str, s sVar) {
        c.a.a.a.x0.a.a(str, "Value");
        c.a.a.a.x0.d dVar = new c.a.a.a.x0.d(str.length());
        dVar.a(str);
        v vVar = new v(0, str.length());
        if (sVar == null) {
            sVar = f371b;
        }
        return sVar.a(dVar, vVar);
    }

    protected c.a.a.a.f a(String str, String str2, y[] yVarArr) {
        return new c(str, str2, yVarArr);
    }

    protected y a(String str, String str2) {
        return new m(str, str2);
    }

    @Override // c.a.a.a.s0.s
    public c.a.a.a.f[] a(c.a.a.a.x0.d dVar, v vVar) {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        c.a.a.a.x0.a.a(vVar, "Parser cursor");
        ArrayList arrayList = new ArrayList();
        while (!vVar.a()) {
            c.a.a.a.f fVarB = b(dVar, vVar);
            if (fVarB.getName().length() != 0 || fVarB.getValue() != null) {
                arrayList.add(fVarB);
            }
        }
        return (c.a.a.a.f[]) arrayList.toArray(new c.a.a.a.f[arrayList.size()]);
    }

    @Override // c.a.a.a.s0.s
    public c.a.a.a.f b(c.a.a.a.x0.d dVar, v vVar) {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        c.a.a.a.x0.a.a(vVar, "Parser cursor");
        y yVarC = c(dVar, vVar);
        return a(yVarC.getName(), yVarC.getValue(), (vVar.a() || dVar.charAt(vVar.b() + (-1)) == ',') ? null : d(dVar, vVar));
    }

    public y c(c.a.a.a.x0.d dVar, v vVar) {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        c.a.a.a.x0.a.a(vVar, "Parser cursor");
        String strA = this.f373a.a(dVar, vVar, f372c);
        if (vVar.a()) {
            return new m(strA, null);
        }
        char cCharAt = dVar.charAt(vVar.b());
        vVar.a(vVar.b() + 1);
        if (cCharAt != '=') {
            return a(strA, (String) null);
        }
        String strB = this.f373a.b(dVar, vVar, d);
        if (!vVar.a()) {
            vVar.a(vVar.b() + 1);
        }
        return a(strA, strB);
    }

    public y[] d(c.a.a.a.x0.d dVar, v vVar) {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        c.a.a.a.x0.a.a(vVar, "Parser cursor");
        this.f373a.a(dVar, vVar);
        ArrayList arrayList = new ArrayList();
        while (!vVar.a()) {
            arrayList.add(c(dVar, vVar));
            if (dVar.charAt(vVar.b() - 1) == ',') {
                break;
            }
        }
        return (y[]) arrayList.toArray(new y[arrayList.size()]);
    }
}

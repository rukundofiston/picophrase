package c.a.a.a.q0.j;

import java.util.ArrayList;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public class u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final u f316b = new u();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final BitSet f317c = c.a.a.a.s0.w.a(61, 59);
    private static final BitSet d = c.a.a.a.s0.w.a(59);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.s0.w f318a = c.a.a.a.s0.w.f401a;

    private c.a.a.a.y b(c.a.a.a.x0.d dVar, c.a.a.a.s0.v vVar) {
        String strA = this.f318a.a(dVar, vVar, f317c);
        if (vVar.a()) {
            return new c.a.a.a.s0.m(strA, null);
        }
        char cCharAt = dVar.charAt(vVar.b());
        vVar.a(vVar.b() + 1);
        if (cCharAt != '=') {
            return new c.a.a.a.s0.m(strA, null);
        }
        String strA2 = this.f318a.a(dVar, vVar, d);
        if (!vVar.a()) {
            vVar.a(vVar.b() + 1);
        }
        return new c.a.a.a.s0.m(strA, strA2);
    }

    public c.a.a.a.f a(c.a.a.a.x0.d dVar, c.a.a.a.s0.v vVar) {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        c.a.a.a.x0.a.a(vVar, "Parser cursor");
        c.a.a.a.y yVarB = b(dVar, vVar);
        ArrayList arrayList = new ArrayList();
        while (!vVar.a()) {
            arrayList.add(b(dVar, vVar));
        }
        return new c.a.a.a.s0.c(yVarB.getName(), yVarB.getValue(), (c.a.a.a.y[]) arrayList.toArray(new c.a.a.a.y[arrayList.size()]));
    }
}

package c.a.a.a.q0.j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class f0 extends y {
    public f0() {
        this((String[]) null, false);
    }

    f0(boolean z, c.a.a.a.n0.b... bVarArr) {
        super(z, bVarArr);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public f0(String[] strArr, boolean z) {
        c.a.a.a.n0.b[] bVarArr = new c.a.a.a.n0.b[10];
        bVarArr[0] = new h0();
        bVarArr[1] = new i();
        bVarArr[2] = new d0();
        bVarArr[3] = new e0();
        bVarArr[4] = new h();
        bVarArr[5] = new j();
        bVarArr[6] = new e();
        bVarArr[7] = new g(strArr != null ? (String[]) strArr.clone() : y.f320c);
        bVarArr[8] = new b0();
        bVarArr[9] = new c0();
        super(z, bVarArr);
    }

    private List<c.a.a.a.n0.c> b(c.a.a.a.f[] fVarArr, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.m {
        ArrayList arrayList = new ArrayList(fVarArr.length);
        for (c.a.a.a.f fVar2 : fVarArr) {
            String name = fVar2.getName();
            String value = fVar2.getValue();
            if (name == null || name.isEmpty()) {
                throw new c.a.a.a.n0.m("Cookie name may not be empty");
            }
            c cVar = new c(name, value);
            cVar.b(p.b(fVar));
            cVar.d(p.a(fVar));
            cVar.a(new int[]{fVar.c()});
            c.a.a.a.y[] yVarArrA = fVar2.a();
            HashMap map = new HashMap(yVarArrA.length);
            for (int length = yVarArrA.length - 1; length >= 0; length--) {
                c.a.a.a.y yVar = yVarArrA[length];
                map.put(yVar.getName().toLowerCase(Locale.ROOT), yVar);
            }
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                c.a.a.a.y yVar2 = (c.a.a.a.y) ((Map.Entry) it.next()).getValue();
                String lowerCase = yVar2.getName().toLowerCase(Locale.ROOT);
                cVar.a(lowerCase, yVar2.getValue());
                c.a.a.a.n0.d dVarA = a(lowerCase);
                if (dVarA != null) {
                    dVarA.a(cVar, yVar2.getValue());
                }
            }
            arrayList.add(cVar);
        }
        return arrayList;
    }

    private static c.a.a.a.n0.f c(c.a.a.a.n0.f fVar) {
        String strA = fVar.a();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= strA.length()) {
                z = true;
                break;
            }
            char cCharAt = strA.charAt(i);
            if (cCharAt == '.' || cCharAt == ':') {
                break;
            }
            i++;
        }
        if (!z) {
            return fVar;
        }
        return new c.a.a.a.n0.f(strA + ".local", fVar.c(), fVar.b(), fVar.d());
    }

    @Override // c.a.a.a.q0.j.y, c.a.a.a.n0.i
    public c.a.a.a.e a() {
        c.a.a.a.x0.d dVar = new c.a.a.a.x0.d(40);
        dVar.a("Cookie2");
        dVar.a(": ");
        dVar.a("$Version=");
        dVar.a(Integer.toString(b()));
        return new c.a.a.a.s0.q(dVar);
    }

    @Override // c.a.a.a.q0.j.y, c.a.a.a.n0.i
    public List<c.a.a.a.n0.c> a(c.a.a.a.e eVar, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.m {
        c.a.a.a.x0.a.a(eVar, "Header");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        if (eVar.getName().equalsIgnoreCase("Set-Cookie2")) {
            return b(eVar.a(), c(fVar));
        }
        throw new c.a.a.a.n0.m("Unrecognized cookie header '" + eVar.toString() + "'");
    }

    @Override // c.a.a.a.q0.j.p
    protected List<c.a.a.a.n0.c> a(c.a.a.a.f[] fVarArr, c.a.a.a.n0.f fVar) {
        return b(fVarArr, c(fVar));
    }

    @Override // c.a.a.a.q0.j.y, c.a.a.a.q0.j.p, c.a.a.a.n0.i
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        super.a(cVar, c(fVar));
    }

    @Override // c.a.a.a.q0.j.y
    protected void a(c.a.a.a.x0.d dVar, c.a.a.a.n0.c cVar, int i) {
        String strA;
        int[] iArrF;
        super.a(dVar, cVar, i);
        if (!(cVar instanceof c.a.a.a.n0.a) || (strA = ((c.a.a.a.n0.a) cVar).a("port")) == null) {
            return;
        }
        dVar.a("; $Port");
        dVar.a("=\"");
        if (!strA.trim().isEmpty() && (iArrF = cVar.f()) != null) {
            int length = iArrF.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (i2 > 0) {
                    dVar.a(",");
                }
                dVar.a(Integer.toString(iArrF[i2]));
            }
        }
        dVar.a("\"");
    }

    @Override // c.a.a.a.q0.j.y, c.a.a.a.n0.i
    public int b() {
        return 1;
    }

    @Override // c.a.a.a.q0.j.p, c.a.a.a.n0.i
    public boolean b(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        return super.b(cVar, c(fVar));
    }

    @Override // c.a.a.a.q0.j.y
    public String toString() {
        return "rfc2965";
    }
}

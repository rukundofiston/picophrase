package c.a.a.a.q0.j;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class v extends p {
    public v() {
        this((String[]) null);
    }

    v(c.a.a.a.n0.b... bVarArr) {
        super(bVarArr);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public v(String[] strArr) {
        c.a.a.a.n0.b[] bVarArr = new c.a.a.a.n0.b[5];
        bVarArr[0] = new i();
        bVarArr[1] = new t();
        bVarArr[2] = new j();
        bVarArr[3] = new e();
        bVarArr[4] = new g(strArr != null ? (String[]) strArr.clone() : new String[]{"EEE, dd-MMM-yy HH:mm:ss z"});
        super(bVarArr);
    }

    @Override // c.a.a.a.n0.i
    public c.a.a.a.e a() {
        return null;
    }

    @Override // c.a.a.a.n0.i
    public List<c.a.a.a.n0.c> a(c.a.a.a.e eVar, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.m {
        c.a.a.a.x0.d dVar;
        c.a.a.a.s0.v vVar;
        c.a.a.a.x0.a.a(eVar, "Header");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        if (!eVar.getName().equalsIgnoreCase("Set-Cookie")) {
            throw new c.a.a.a.n0.m("Unrecognized cookie header '" + eVar.toString() + "'");
        }
        u uVar = u.f316b;
        if (eVar instanceof c.a.a.a.d) {
            c.a.a.a.d dVar2 = (c.a.a.a.d) eVar;
            dVar = dVar2.c();
            vVar = new c.a.a.a.s0.v(dVar2.b(), dVar.length());
        } else {
            String value = eVar.getValue();
            if (value == null) {
                throw new c.a.a.a.n0.m("Header value is null");
            }
            dVar = new c.a.a.a.x0.d(value.length());
            dVar.a(value);
            vVar = new c.a.a.a.s0.v(0, dVar.length());
        }
        return a(new c.a.a.a.f[]{uVar.a(dVar, vVar)}, fVar);
    }

    @Override // c.a.a.a.n0.i
    public List<c.a.a.a.e> a(List<c.a.a.a.n0.c> list) {
        c.a.a.a.x0.a.a(list, "List of cookies");
        c.a.a.a.x0.d dVar = new c.a.a.a.x0.d(list.size() * 20);
        dVar.a("Cookie");
        dVar.a(": ");
        for (int i = 0; i < list.size(); i++) {
            c.a.a.a.n0.c cVar = list.get(i);
            if (i > 0) {
                dVar.a("; ");
            }
            dVar.a(cVar.getName());
            String value = cVar.getValue();
            if (value != null) {
                dVar.a("=");
                dVar.a(value);
            }
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new c.a.a.a.s0.q(dVar));
        return arrayList;
    }

    @Override // c.a.a.a.n0.i
    public int b() {
        return 0;
    }

    public String toString() {
        return "netscape";
    }
}

package c.a.a.a.q0.j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class m extends p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f307b = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z"};

    class a extends i {
        a() {
        }

        @Override // c.a.a.a.q0.j.i, c.a.a.a.n0.d
        public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public m(String[] strArr, n.a aVar) {
        c.a.a.a.n0.b[] bVarArr = new c.a.a.a.n0.b[7];
        bVarArr[0] = new o();
        bVarArr[1] = new f();
        bVarArr[2] = aVar == n.a.SECURITYLEVEL_IE_MEDIUM ? new a() : new i();
        bVarArr[3] = new h();
        bVarArr[4] = new j();
        bVarArr[5] = new e();
        bVarArr[6] = new g(strArr != null ? (String[]) strArr.clone() : f307b);
        super(bVarArr);
    }

    private static boolean b(String str) {
        return str != null && str.startsWith("\"") && str.endsWith("\"");
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
        c.a.a.a.f[] fVarArrA = eVar.a();
        boolean z = false;
        boolean z2 = false;
        for (c.a.a.a.f fVar2 : fVarArrA) {
            if (fVar2.a("version") != null) {
                z2 = true;
            }
            if (fVar2.a("expires") != null) {
                z = true;
            }
        }
        if (!z && z2) {
            return a(fVarArrA, fVar);
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
        c.a.a.a.f fVarA = uVar.a(dVar, vVar);
        String name = fVarA.getName();
        String value2 = fVarA.getValue();
        if (name == null || name.isEmpty()) {
            throw new c.a.a.a.n0.m("Cookie name may not be empty");
        }
        d dVar3 = new d(name, value2);
        dVar3.b(p.b(fVar));
        dVar3.d(p.a(fVar));
        c.a.a.a.y[] yVarArrA = fVarA.a();
        for (int length = yVarArrA.length - 1; length >= 0; length--) {
            c.a.a.a.y yVar = yVarArrA[length];
            String lowerCase = yVar.getName().toLowerCase(Locale.ROOT);
            dVar3.a(lowerCase, yVar.getValue());
            c.a.a.a.n0.d dVarA = a(lowerCase);
            if (dVarA != null) {
                dVarA.a(dVar3, yVar.getValue());
            }
        }
        if (z) {
            dVar3.a(0);
        }
        return Collections.singletonList(dVar3);
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
            String name = cVar.getName();
            String value = cVar.getValue();
            if (cVar.b() <= 0 || b(value)) {
                dVar.a(name);
                dVar.a("=");
                if (value != null) {
                    dVar.a(value);
                }
            } else {
                c.a.a.a.s0.f.f370a.a(dVar, (c.a.a.a.f) new c.a.a.a.s0.c(name, value), false);
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
        return "compatibility";
    }
}

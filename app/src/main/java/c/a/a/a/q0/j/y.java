package c.a.a.a.q0.j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class y extends p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final String[] f320c = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f321b;

    public y() {
        this((String[]) null, false);
    }

    protected y(boolean z, c.a.a.a.n0.b... bVarArr) {
        super(bVarArr);
        this.f321b = z;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public y(String[] strArr, boolean z) {
        c.a.a.a.n0.b[] bVarArr = new c.a.a.a.n0.b[7];
        bVarArr[0] = new a0();
        bVarArr[1] = new i();
        bVarArr[2] = new x();
        bVarArr[3] = new h();
        bVarArr[4] = new j();
        bVarArr[5] = new e();
        bVarArr[6] = new g(strArr != null ? (String[]) strArr.clone() : f320c);
        super(bVarArr);
        this.f321b = z;
    }

    private List<c.a.a.a.e> b(List<c.a.a.a.n0.c> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (c.a.a.a.n0.c cVar : list) {
            int iB = cVar.b();
            c.a.a.a.x0.d dVar = new c.a.a.a.x0.d(40);
            dVar.a("Cookie: ");
            dVar.a("$Version=");
            dVar.a(Integer.toString(iB));
            dVar.a("; ");
            a(dVar, cVar, iB);
            arrayList.add(new c.a.a.a.s0.q(dVar));
        }
        return arrayList;
    }

    private List<c.a.a.a.e> c(List<c.a.a.a.n0.c> list) {
        int iB = Integer.MAX_VALUE;
        for (c.a.a.a.n0.c cVar : list) {
            if (cVar.b() < iB) {
                iB = cVar.b();
            }
        }
        c.a.a.a.x0.d dVar = new c.a.a.a.x0.d(list.size() * 40);
        dVar.a("Cookie");
        dVar.a(": ");
        dVar.a("$Version=");
        dVar.a(Integer.toString(iB));
        for (c.a.a.a.n0.c cVar2 : list) {
            dVar.a("; ");
            a(dVar, cVar2, iB);
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new c.a.a.a.s0.q(dVar));
        return arrayList;
    }

    @Override // c.a.a.a.n0.i
    public c.a.a.a.e a() {
        return null;
    }

    @Override // c.a.a.a.n0.i
    public List<c.a.a.a.n0.c> a(c.a.a.a.e eVar, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.m {
        c.a.a.a.x0.a.a(eVar, "Header");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        if (eVar.getName().equalsIgnoreCase("Set-Cookie")) {
            return a(eVar.a(), fVar);
        }
        throw new c.a.a.a.n0.m("Unrecognized cookie header '" + eVar.toString() + "'");
    }

    @Override // c.a.a.a.n0.i
    public List<c.a.a.a.e> a(List<c.a.a.a.n0.c> list) {
        c.a.a.a.x0.a.a(list, "List of cookies");
        if (list.size() > 1) {
            ArrayList arrayList = new ArrayList(list);
            Collections.sort(arrayList, c.a.a.a.n0.g.f170a);
            list = arrayList;
        }
        return this.f321b ? c(list) : b(list);
    }

    @Override // c.a.a.a.q0.j.p, c.a.a.a.n0.i
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        String name = cVar.getName();
        if (name.indexOf(32) != -1) {
            throw new c.a.a.a.n0.h("Cookie name may not contain blanks");
        }
        if (name.startsWith("$")) {
            throw new c.a.a.a.n0.h("Cookie name may not start with $");
        }
        super.a(cVar, fVar);
    }

    protected void a(c.a.a.a.x0.d dVar, c.a.a.a.n0.c cVar, int i) {
        a(dVar, cVar.getName(), cVar.getValue(), i);
        if (cVar.e() != null && (cVar instanceof c.a.a.a.n0.a) && ((c.a.a.a.n0.a) cVar).c("path")) {
            dVar.a("; ");
            a(dVar, "$Path", cVar.e(), i);
        }
        if (cVar.c() != null && (cVar instanceof c.a.a.a.n0.a) && ((c.a.a.a.n0.a) cVar).c("domain")) {
            dVar.a("; ");
            a(dVar, "$Domain", cVar.c(), i);
        }
    }

    protected void a(c.a.a.a.x0.d dVar, String str, String str2, int i) {
        dVar.a(str);
        dVar.a("=");
        if (str2 != null) {
            if (i <= 0) {
                dVar.a(str2);
                return;
            }
            dVar.a('\"');
            dVar.a(str2);
            dVar.a('\"');
        }
    }

    @Override // c.a.a.a.n0.i
    public int b() {
        return 1;
    }

    public String toString() {
        return "rfc2109";
    }
}

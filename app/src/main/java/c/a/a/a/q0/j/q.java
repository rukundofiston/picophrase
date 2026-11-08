package c.a.a.a.q0.j;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class q implements c.a.a.a.n0.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f0 f313a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final y f314b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final v f315c;

    public q(String[] strArr, boolean z) {
        this.f313a = new f0(z, new h0(), new i(), new d0(), new e0(), new h(), new j(), new e(), new b0(), new c0());
        this.f314b = new y(z, new a0(), new i(), new x(), new h(), new j(), new e());
        c.a.a.a.n0.b[] bVarArr = new c.a.a.a.n0.b[5];
        bVarArr[0] = new f();
        bVarArr[1] = new i();
        bVarArr[2] = new j();
        bVarArr[3] = new e();
        bVarArr[4] = new g(strArr != null ? (String[]) strArr.clone() : new String[]{"EEE, dd-MMM-yy HH:mm:ss z"});
        this.f315c = new v(bVarArr);
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
            return "Set-Cookie2".equals(eVar.getName()) ? this.f313a.a(fVarArrA, fVar) : this.f314b.a(fVarArrA, fVar);
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
        return this.f315c.a(new c.a.a.a.f[]{uVar.a(dVar, vVar)}, fVar);
    }

    @Override // c.a.a.a.n0.i
    public List<c.a.a.a.e> a(List<c.a.a.a.n0.c> list) {
        c.a.a.a.x0.a.a(list, "List of cookies");
        int iB = Integer.MAX_VALUE;
        boolean z = true;
        for (c.a.a.a.n0.c cVar : list) {
            if (!(cVar instanceof c.a.a.a.n0.n)) {
                z = false;
            }
            if (cVar.b() < iB) {
                iB = cVar.b();
            }
        }
        if (iB > 0) {
            return z ? this.f313a.a(list) : this.f314b.a(list);
        }
        return this.f315c.a(list);
    }

    @Override // c.a.a.a.n0.i
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        if (cVar.b() <= 0) {
            this.f315c.a(cVar, fVar);
        } else if (cVar instanceof c.a.a.a.n0.n) {
            this.f313a.a(cVar, fVar);
        } else {
            this.f314b.a(cVar, fVar);
        }
    }

    @Override // c.a.a.a.n0.i
    public int b() {
        return this.f313a.b();
    }

    @Override // c.a.a.a.n0.i
    public boolean b(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        if (cVar.b() > 0) {
            return cVar instanceof c.a.a.a.n0.n ? this.f313a.b(cVar, fVar) : this.f314b.b(cVar, fVar);
        }
        return this.f315c.b(cVar, fVar);
    }
}

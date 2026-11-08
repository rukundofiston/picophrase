package c.a.a.a.v0;

import c.a.a.a.q;
import c.a.a.a.r;
import c.a.a.a.s;
import c.a.a.a.u;

/* JADX INFO: loaded from: classes.dex */
public final class i implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r[] f416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final u[] f417b;

    public i(r[] rVarArr, u[] uVarArr) {
        if (rVarArr != null) {
            int length = rVarArr.length;
            r[] rVarArr2 = new r[length];
            this.f416a = rVarArr2;
            System.arraycopy(rVarArr, 0, rVarArr2, 0, length);
        } else {
            this.f416a = new r[0];
        }
        if (uVarArr == null) {
            this.f417b = new u[0];
            return;
        }
        int length2 = uVarArr.length;
        u[] uVarArr2 = new u[length2];
        this.f417b = uVarArr2;
        System.arraycopy(uVarArr, 0, uVarArr2, 0, length2);
    }

    @Override // c.a.a.a.r
    public void a(q qVar, e eVar) {
        for (r rVar : this.f416a) {
            rVar.a(qVar, eVar);
        }
    }

    @Override // c.a.a.a.u
    public void a(s sVar, e eVar) {
        for (u uVar : this.f417b) {
            uVar.a(sVar, eVar);
        }
    }
}

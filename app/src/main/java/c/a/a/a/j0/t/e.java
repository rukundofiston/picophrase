package c.a.a.a.j0.t;

import c.a.a.a.k;
import c.a.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public abstract class e extends h implements l {
    private k h;

    public void a(k kVar) {
        this.h = kVar;
    }

    @Override // c.a.a.a.l
    public k c() {
        return this.h;
    }

    @Override // c.a.a.a.j0.t.b
    public Object clone() {
        e eVar = (e) super.clone();
        k kVar = this.h;
        if (kVar != null) {
            eVar.h = (k) c.a.a.a.j0.w.a.a(kVar);
        }
        return eVar;
    }

    @Override // c.a.a.a.l
    public boolean f() {
        c.a.a.a.e eVarD = d("Expect");
        return eVarD != null && "100-continue".equalsIgnoreCase(eVarD.getValue());
    }
}

package c.a.a.a.q0.j;

import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class g0 implements c.a.a.a.n0.j, c.a.a.a.n0.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.n0.i f305a;

    public g0() {
        this(null, false);
    }

    public g0(String[] strArr, boolean z) {
        this.f305a = new f0(strArr, z);
    }

    @Override // c.a.a.a.n0.j
    public c.a.a.a.n0.i a(c.a.a.a.t0.e eVar) {
        if (eVar == null) {
            return new f0();
        }
        Collection collection = (Collection) eVar.b("http.protocol.cookie-datepatterns");
        return new f0(collection != null ? (String[]) collection.toArray(new String[collection.size()]) : null, eVar.b("http.protocol.single-cookie-header", false));
    }

    @Override // c.a.a.a.n0.k
    public c.a.a.a.n0.i a(c.a.a.a.v0.e eVar) {
        return this.f305a;
    }
}

package c.a.a.a.q0.j;

import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class w implements c.a.a.a.n0.j, c.a.a.a.n0.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.n0.i f319a;

    public w() {
        this(null);
    }

    public w(String[] strArr) {
        this.f319a = new v(strArr);
    }

    @Override // c.a.a.a.n0.j
    public c.a.a.a.n0.i a(c.a.a.a.t0.e eVar) {
        if (eVar == null) {
            return new v();
        }
        Collection collection = (Collection) eVar.b("http.protocol.cookie-datepatterns");
        return new v(collection != null ? (String[]) collection.toArray(new String[collection.size()]) : null);
    }

    @Override // c.a.a.a.n0.k
    public c.a.a.a.n0.i a(c.a.a.a.v0.e eVar) {
        return this.f319a;
    }
}

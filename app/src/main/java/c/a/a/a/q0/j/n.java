package c.a.a.a.q0.j;

import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class n implements c.a.a.a.n0.j, c.a.a.a.n0.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f308a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.n0.i f309b;

    public enum a {
        SECURITYLEVEL_DEFAULT,
        SECURITYLEVEL_IE_MEDIUM
    }

    public n() {
        this(null, a.SECURITYLEVEL_DEFAULT);
    }

    public n(String[] strArr, a aVar) {
        this.f308a = aVar;
        this.f309b = new m(strArr, aVar);
    }

    @Override // c.a.a.a.n0.j
    public c.a.a.a.n0.i a(c.a.a.a.t0.e eVar) {
        if (eVar == null) {
            return new m(null, this.f308a);
        }
        Collection collection = (Collection) eVar.b("http.protocol.cookie-datepatterns");
        return new m(collection != null ? (String[]) collection.toArray(new String[collection.size()]) : null, this.f308a);
    }

    @Override // c.a.a.a.n0.k
    public c.a.a.a.n0.i a(c.a.a.a.v0.e eVar) {
        return this.f309b;
    }
}

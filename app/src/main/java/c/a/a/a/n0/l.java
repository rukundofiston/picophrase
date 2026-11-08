package c.a.a.a.n0;

import c.a.a.a.q;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class l implements c.a.a.a.l0.a<k> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<String, j> f171a = new ConcurrentHashMap<>();

    class a implements k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f172a;

        a(String str) {
            this.f172a = str;
        }

        @Override // c.a.a.a.n0.k
        public i a(c.a.a.a.v0.e eVar) {
            return l.this.a(this.f172a, ((q) eVar.a("http.request")).k());
        }
    }

    public i a(String str, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(str, "Name");
        j jVar = this.f171a.get(str.toLowerCase(Locale.ENGLISH));
        if (jVar != null) {
            return jVar.a(eVar);
        }
        throw new IllegalStateException("Unsupported cookie spec: " + str);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // c.a.a.a.l0.a
    public k a(String str) {
        return new a(str);
    }

    public void a(String str, j jVar) {
        c.a.a.a.x0.a.a(str, "Name");
        c.a.a.a.x0.a.a(jVar, "Cookie spec factory");
        this.f171a.put(str.toLowerCase(Locale.ENGLISH), jVar);
    }
}

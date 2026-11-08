package c.a.a.a.i0;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class f implements c.a.a.a.l0.a<e> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<String, d> f65a = new ConcurrentHashMap<>();

    class a implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f66a;

        a(String str) {
            this.f66a = str;
        }

        @Override // c.a.a.a.i0.e
        public c a(c.a.a.a.v0.e eVar) {
            return f.this.a(this.f66a, ((c.a.a.a.q) eVar.a("http.request")).k());
        }
    }

    public c a(String str, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(str, "Name");
        d dVar = this.f65a.get(str.toLowerCase(Locale.ENGLISH));
        if (dVar != null) {
            return dVar.a(eVar);
        }
        throw new IllegalStateException("Unsupported authentication scheme: " + str);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // c.a.a.a.l0.a
    public e a(String str) {
        return new a(str);
    }

    public void a(String str, d dVar) {
        c.a.a.a.x0.a.a(str, "Name");
        c.a.a.a.x0.a.a(dVar, "Authentication scheme factory");
        this.f65a.put(str.toLowerCase(Locale.ENGLISH), dVar);
    }
}

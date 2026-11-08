package c.a.a.a.m0.t;

import c.a.a.a.t0.e;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final b f123a = new C0008a();

    /* JADX INFO: renamed from: c.a.a.a.m0.t.a$a, reason: collision with other inner class name */
    static class C0008a implements b {
        C0008a() {
        }

        @Override // c.a.a.a.m0.t.b
        public int a(c.a.a.a.m0.u.b bVar) {
            return 2;
        }
    }

    public static b a(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        b bVar = (b) eVar.b("http.conn-manager.max-per-route");
        return bVar == null ? f123a : bVar;
    }

    public static void a(e eVar, int i) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        eVar.a("http.conn-manager.max-total", i);
    }

    @Deprecated
    public static void a(e eVar, long j) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        eVar.b("http.conn-manager.timeout", j);
    }

    public static void a(e eVar, b bVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        eVar.a("http.conn-manager.max-per-route", bVar);
    }

    public static int b(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        return eVar.b("http.conn-manager.max-total", 20);
    }
}

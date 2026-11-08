package c.a.a.a.t0;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class c {
    public static int a(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        return eVar.b("http.connection.timeout", 0);
    }

    public static void a(e eVar, int i) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        eVar.a("http.connection.timeout", i);
    }

    public static void a(e eVar, boolean z) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        eVar.a("http.tcp.nodelay", z);
    }

    public static int b(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        return eVar.b("http.socket.linger", -1);
    }

    public static void b(e eVar, int i) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        eVar.a("http.socket.timeout", i);
    }

    public static void c(e eVar, int i) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        eVar.a("http.socket.buffer-size", i);
    }

    public static boolean c(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        return eVar.b("http.socket.reuseaddr", false);
    }

    public static int d(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        return eVar.b("http.socket.timeout", 0);
    }

    public static boolean e(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        return eVar.b("http.tcp.nodelay", true);
    }

    public static boolean f(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        return eVar.b("http.connection.stalecheck", true);
    }
}

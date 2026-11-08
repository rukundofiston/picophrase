package c.a.a.a.j0.u;

import c.a.a.a.t0.c;
import c.a.a.a.t0.e;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b {
    public static long a(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        Long l = (Long) eVar.b("http.conn-manager.timeout");
        return l != null ? l.longValue() : c.a(eVar);
    }

    public static boolean b(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        return eVar.b("http.protocol.handle-authentication", true);
    }

    public static boolean c(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        return eVar.b("http.protocol.handle-redirects", true);
    }
}

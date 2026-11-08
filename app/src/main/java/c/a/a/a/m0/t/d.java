package c.a.a.a.m0.t;

import c.a.a.a.n;
import c.a.a.a.t0.e;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f126a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c.a.a.a.m0.u.b f127b;

    static {
        n nVar = new n("127.0.0.255", 0, "no-host");
        f126a = nVar;
        f127b = new c.a.a.a.m0.u.b(nVar);
    }

    public static n a(e eVar) {
        c.a.a.a.x0.a.a(eVar, "Parameters");
        n nVar = (n) eVar.b("http.route.default-proxy");
        if (nVar == null || !f126a.equals(nVar)) {
            return nVar;
        }
        return null;
    }

    public static c.a.a.a.m0.u.b b(e eVar) {
        c.a.a.a.x0.a.a(eVar, "Parameters");
        c.a.a.a.m0.u.b bVar = (c.a.a.a.m0.u.b) eVar.b("http.route.forced-route");
        if (bVar == null || !f127b.equals(bVar)) {
            return bVar;
        }
        return null;
    }

    public static InetAddress c(e eVar) {
        c.a.a.a.x0.a.a(eVar, "Parameters");
        return (InetAddress) eVar.b("http.route.local-address");
    }
}

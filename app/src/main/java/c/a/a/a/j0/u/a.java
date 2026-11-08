package c.a.a.a.j0.u;

import c.a.a.a.n;
import c.a.a.a.t0.e;
import java.net.InetAddress;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class a {
    public static c.a.a.a.j0.r.a a(e eVar) {
        c.a.a.a.j0.r.a.C0005a c0005aF = c.a.a.a.j0.r.a.f();
        c0005aF.d(eVar.b("http.socket.timeout", 0));
        c0005aF.f(eVar.b("http.connection.stalecheck", true));
        c0005aF.a(eVar.b("http.connection.timeout", 0));
        c0005aF.c(eVar.b("http.protocol.expect-continue", false));
        c0005aF.a((n) eVar.b("http.route.default-proxy"));
        c0005aF.a((InetAddress) eVar.b("http.route.local-address"));
        c0005aF.a((Collection<String>) eVar.b("http.auth.proxy-scheme-pref"));
        c0005aF.b((Collection<String>) eVar.b("http.auth.target-scheme-pref"));
        c0005aF.a(eVar.b("http.protocol.handle-authentication", true));
        c0005aF.b(eVar.b("http.protocol.allow-circular-redirects", false));
        c0005aF.b((int) eVar.a("http.conn-manager.timeout", 0L));
        c0005aF.a((String) eVar.b("http.protocol.cookie-policy"));
        c0005aF.c(eVar.b("http.protocol.max-redirects", 50));
        c0005aF.d(eVar.b("http.protocol.handle-redirects", true));
        c0005aF.e(!eVar.b("http.protocol.reject-relative-redirect", false));
        return c0005aF.a();
    }
}

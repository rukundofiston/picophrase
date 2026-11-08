package c.a.a.a.t0;

import c.a.a.a.c0;
import c.a.a.a.v;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class f {
    public static String a(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        String str = (String) eVar.b("http.protocol.element-charset");
        return str == null ? c.a.a.a.v0.d.f413b.name() : str;
    }

    public static void a(e eVar, c0 c0Var) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        eVar.a("http.protocol.version", c0Var);
    }

    public static void a(e eVar, String str) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        eVar.a("http.protocol.content-charset", str);
    }

    public static c0 b(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        Object objB = eVar.b("http.protocol.version");
        return objB == null ? v.f : (c0) objB;
    }

    public static void b(e eVar, String str) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        eVar.a("http.useragent", str);
    }
}

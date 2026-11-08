package c.a.a.a.v0;

import c.a.a.a.q;

/* JADX INFO: loaded from: classes.dex */
public class f implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e f414a;

    public f() {
        this.f414a = new a();
    }

    public f(e eVar) {
        this.f414a = eVar;
    }

    public static f a(e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        return eVar instanceof f ? (f) eVar : new f(eVar);
    }

    public c.a.a.a.j a() {
        return (c.a.a.a.j) a("http.connection", c.a.a.a.j.class);
    }

    @Override // c.a.a.a.v0.e
    public Object a(String str) {
        return this.f414a.a(str);
    }

    public <T> T a(String str, Class<T> cls) {
        c.a.a.a.x0.a.a(cls, "Attribute class");
        Object objA = a(str);
        if (objA == null) {
            return null;
        }
        return cls.cast(objA);
    }

    @Override // c.a.a.a.v0.e
    public void a(String str, Object obj) {
        this.f414a.a(str, obj);
    }

    public q b() {
        return (q) a("http.request", q.class);
    }

    public c.a.a.a.n c() {
        return (c.a.a.a.n) a("http.target_host", c.a.a.a.n.class);
    }

    public boolean d() {
        Boolean bool = (Boolean) a("http.request_sent", Boolean.class);
        return bool != null && bool.booleanValue();
    }
}

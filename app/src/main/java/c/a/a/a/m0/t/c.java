package c.a.a.a.m0.t;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<c.a.a.a.m0.u.b, Integer> f124a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile int f125b;

    public c() {
        this(2);
    }

    public c(int i) {
        this.f124a = new ConcurrentHashMap<>();
        a(i);
    }

    @Override // c.a.a.a.m0.t.b
    public int a(c.a.a.a.m0.u.b bVar) {
        c.a.a.a.x0.a.a(bVar, "HTTP route");
        Integer num = this.f124a.get(bVar);
        return num != null ? num.intValue() : this.f125b;
    }

    public void a(int i) {
        c.a.a.a.x0.a.b(i, "Default max per route");
        this.f125b = i;
    }

    public String toString() {
        return this.f124a.toString();
    }
}

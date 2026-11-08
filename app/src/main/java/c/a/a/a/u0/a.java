package c.a.a.a.u0;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<T, C> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f403a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f404b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final C f405c;
    private final long d;
    private final long e;
    private long f;
    private long g;
    private volatile Object h;

    public a(String str, T t, C c2, long j, TimeUnit timeUnit) {
        c.a.a.a.x0.a.a(t, "Route");
        c.a.a.a.x0.a.a(c2, "Connection");
        c.a.a.a.x0.a.a(timeUnit, "Time unit");
        this.f403a = str;
        this.f404b = t;
        this.f405c = c2;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.d = jCurrentTimeMillis;
        this.e = j > 0 ? jCurrentTimeMillis + timeUnit.toMillis(j) : Long.MAX_VALUE;
        this.g = this.e;
    }

    public C a() {
        return this.f405c;
    }

    public synchronized void a(long j, TimeUnit timeUnit) {
        c.a.a.a.x0.a.a(timeUnit, "Time unit");
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.f = jCurrentTimeMillis;
        this.g = Math.min(j > 0 ? jCurrentTimeMillis + timeUnit.toMillis(j) : Long.MAX_VALUE, this.e);
    }

    public void a(Object obj) {
        this.h = obj;
    }

    public synchronized boolean a(long j) {
        return j >= this.g;
    }

    public synchronized long b() {
        return this.g;
    }

    public T c() {
        return this.f404b;
    }

    public String toString() {
        return "[id:" + this.f403a + "][route:" + this.f404b + "][state:" + this.h + "]";
    }
}

package c.a.a.a.q0.i.s;

import c.a.a.a.m0.q;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends c.a.a.a.q0.i.b {
    private final long f;
    private long g;
    private final long h;
    private long i;

    public b(c.a.a.a.m0.d dVar, c.a.a.a.m0.u.b bVar, long j, TimeUnit timeUnit) {
        super(dVar, bVar);
        c.a.a.a.x0.a.a(bVar, "HTTP route");
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.f = jCurrentTimeMillis;
        this.h = j > 0 ? jCurrentTimeMillis + timeUnit.toMillis(j) : Long.MAX_VALUE;
        this.i = this.h;
    }

    public void a(long j, TimeUnit timeUnit) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.g = jCurrentTimeMillis;
        this.i = Math.min(this.h, j > 0 ? jCurrentTimeMillis + timeUnit.toMillis(j) : Long.MAX_VALUE);
    }

    public boolean a(long j) {
        return j >= this.i;
    }

    @Override // c.a.a.a.q0.i.b
    protected void b() {
        super.b();
    }

    protected final q c() {
        return this.f256b;
    }

    protected final c.a.a.a.m0.u.b d() {
        return this.f257c;
    }
}

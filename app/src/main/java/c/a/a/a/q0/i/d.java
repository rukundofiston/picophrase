package c.a.a.a.q0.i;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class d implements c.a.a.a.m0.b {
    private static final AtomicLong g = new AtomicLong();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f258a = new c.a.a.a.p0.b(d.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.m0.v.i f259b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.a.a.a.m0.d f260c;
    private k d;
    private o e;
    private volatile boolean f;

    class a implements c.a.a.a.m0.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.m0.u.b f261a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f262b;

        a(c.a.a.a.m0.u.b bVar, Object obj) {
            this.f261a = bVar;
            this.f262b = obj;
        }

        @Override // c.a.a.a.m0.e
        public c.a.a.a.m0.o a(long j, TimeUnit timeUnit) {
            return d.this.b(this.f261a, this.f262b);
        }

        @Override // c.a.a.a.m0.e
        public void a() {
        }
    }

    public d(c.a.a.a.m0.v.i iVar) {
        c.a.a.a.x0.a.a(iVar, "Scheme registry");
        this.f259b = iVar;
        this.f260c = a(iVar);
    }

    private void a(c.a.a.a.i iVar) {
        try {
            iVar.c();
        } catch (IOException e) {
            if (this.f258a.a()) {
                this.f258a.a("I/O exception shutting down connection", e);
            }
        }
    }

    private void b() {
        c.a.a.a.x0.b.a(!this.f, "Connection manager has been shut down");
    }

    protected c.a.a.a.m0.d a(c.a.a.a.m0.v.i iVar) {
        return new g(iVar);
    }

    @Override // c.a.a.a.m0.b
    public final c.a.a.a.m0.e a(c.a.a.a.m0.u.b bVar, Object obj) {
        return new a(bVar, obj);
    }

    @Override // c.a.a.a.m0.b
    public c.a.a.a.m0.v.i a() {
        return this.f259b;
    }

    @Override // c.a.a.a.m0.b
    public void a(c.a.a.a.m0.o oVar, long j, TimeUnit timeUnit) {
        String str;
        c.a.a.a.x0.a.a(oVar instanceof o, "Connection class mismatch, connection not obtained from this manager");
        o oVar2 = (o) oVar;
        synchronized (oVar2) {
            if (this.f258a.a()) {
                this.f258a.a("Releasing connection " + oVar);
            }
            if (oVar2.o() == null) {
                return;
            }
            c.a.a.a.x0.b.a(oVar2.n() == this, "Connection not obtained from this manager");
            synchronized (this) {
                if (this.f) {
                    a(oVar2);
                    return;
                }
                try {
                    if (oVar2.isOpen() && !oVar2.p()) {
                        a(oVar2);
                    }
                    if (oVar2.p()) {
                        this.d.a(j, timeUnit != null ? timeUnit : TimeUnit.MILLISECONDS);
                        if (this.f258a.a()) {
                            if (j > 0) {
                                str = "for " + j + " " + timeUnit;
                            } else {
                                str = "indefinitely";
                            }
                            this.f258a.a("Connection can be kept alive " + str);
                        }
                    }
                    oVar2.m();
                    this.e = null;
                    if (this.d.h()) {
                        this.d = null;
                    }
                } catch (Throwable th) {
                    oVar2.m();
                    this.e = null;
                    if (this.d.h()) {
                        this.d = null;
                    }
                    throw th;
                }
            }
        }
    }

    c.a.a.a.m0.o b(c.a.a.a.m0.u.b bVar, Object obj) {
        o oVar;
        c.a.a.a.x0.a.a(bVar, "Route");
        synchronized (this) {
            b();
            if (this.f258a.a()) {
                this.f258a.a("Get connection for route " + bVar);
            }
            c.a.a.a.x0.b.a(this.e == null, "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
            if (this.d != null && !this.d.f().equals(bVar)) {
                this.d.d();
                this.d = null;
            }
            if (this.d == null) {
                this.d = new k(this.f258a, Long.toString(g.getAndIncrement()), bVar, this.f260c.a(), 0L, TimeUnit.MILLISECONDS);
            }
            if (this.d.a(System.currentTimeMillis())) {
                this.d.d();
                this.d.g().i();
            }
            oVar = new o(this, this.f260c, this.d);
            this.e = oVar;
        }
        return oVar;
    }

    @Override // c.a.a.a.m0.b
    public void c() {
        synchronized (this) {
            this.f = true;
            try {
                if (this.d != null) {
                    this.d.d();
                }
                this.d = null;
                this.e = null;
            } catch (Throwable th) {
                this.d = null;
                this.e = null;
                throw th;
            }
        }
    }

    protected void finalize() throws Throwable {
        try {
            c();
        } finally {
            super.finalize();
        }
    }
}

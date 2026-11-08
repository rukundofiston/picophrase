package c.a.a.a.q0.i.s;

import c.a.a.a.m0.q;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class d extends c.a.a.a.q0.i.s.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.a.a.p0.b f282c;
    private final Lock d;
    protected final c.a.a.a.m0.d e;
    protected final c.a.a.a.m0.t.b f;
    protected final Set<b> g;
    protected final Queue<b> h;
    protected final Queue<h> i;
    protected final Map<c.a.a.a.m0.u.b, f> j;
    private final long k;
    private final TimeUnit l;
    protected volatile boolean m;
    protected volatile int n;
    protected volatile int o;

    class a implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f283a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.m0.u.b f284b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f285c;

        a(i iVar, c.a.a.a.m0.u.b bVar, Object obj) {
            this.f283a = iVar;
            this.f284b = bVar;
            this.f285c = obj;
        }

        @Override // c.a.a.a.q0.i.s.e
        public b a(long j, TimeUnit timeUnit) {
            return d.this.a(this.f284b, this.f285c, j, timeUnit, this.f283a);
        }

        @Override // c.a.a.a.q0.i.s.e
        public void a() {
            d.this.d.lock();
            try {
                this.f283a.a();
            } finally {
                d.this.d.unlock();
            }
        }
    }

    public d(c.a.a.a.m0.d dVar, c.a.a.a.m0.t.b bVar, int i) {
        this(dVar, bVar, i, -1L, TimeUnit.MILLISECONDS);
    }

    public d(c.a.a.a.m0.d dVar, c.a.a.a.m0.t.b bVar, int i, long j, TimeUnit timeUnit) {
        this.f282c = new c.a.a.a.p0.b(d.class);
        c.a.a.a.x0.a.a(dVar, "Connection operator");
        c.a.a.a.x0.a.a(bVar, "Connections per route");
        this.d = this.f280a;
        this.g = this.f281b;
        this.e = dVar;
        this.f = bVar;
        this.n = i;
        this.h = a();
        this.i = c();
        this.j = b();
        this.k = j;
        this.l = timeUnit;
    }

    @Deprecated
    public d(c.a.a.a.m0.d dVar, c.a.a.a.t0.e eVar) {
        this(dVar, c.a.a.a.m0.t.a.a(eVar), c.a.a.a.m0.t.a.b(eVar));
    }

    private void b(b bVar) {
        q qVarC = bVar.c();
        if (qVarC != null) {
            try {
                qVarC.close();
            } catch (IOException e) {
                this.f282c.a("I/O error closing connection", e);
            }
        }
    }

    protected b a(c.a.a.a.m0.u.b bVar, Object obj, long j, TimeUnit timeUnit, i iVar) {
        c.a.a.a.m0.d dVar;
        b bVarA = null;
        Date date = j > 0 ? new Date(System.currentTimeMillis() + timeUnit.toMillis(j)) : null;
        this.d.lock();
        try {
            f fVarA = a(bVar, true);
            h hVarA = null;
            while (bVarA == null) {
                c.a.a.a.x0.b.a(!this.m, "Connection pool shut down");
                if (this.f282c.a()) {
                    this.f282c.a("[" + bVar + "] total kept alive: " + this.h.size() + ", total issued: " + this.g.size() + ", total allocated: " + this.o + " out of " + this.n);
                }
                bVarA = a(fVarA, obj);
                if (bVarA != null) {
                    break;
                }
                boolean z = fVarA.b() > 0;
                if (this.f282c.a()) {
                    this.f282c.a("Available capacity: " + fVarA.b() + " out of " + fVarA.c() + " [" + bVar + "][" + obj + "]");
                }
                if (z && this.o < this.n) {
                    dVar = this.e;
                } else if (!z || this.h.isEmpty()) {
                    if (this.f282c.a()) {
                        this.f282c.a("Need to wait for connection [" + bVar + "][" + obj + "]");
                    }
                    if (hVarA == null) {
                        hVarA = a(this.d.newCondition(), fVarA);
                        iVar.a(hVarA);
                    }
                    try {
                        fVarA.a(hVarA);
                        this.i.add(hVarA);
                        boolean zA = hVarA.a(date);
                        fVarA.b(hVarA);
                        this.i.remove(hVarA);
                        if (!zA && date != null && date.getTime() <= System.currentTimeMillis()) {
                            throw new c.a.a.a.m0.h("Timeout waiting for connection from pool");
                        }
                    } catch (Throwable th) {
                        fVarA.b(hVarA);
                        this.i.remove(hVarA);
                        throw th;
                    }
                } else {
                    d();
                    fVarA = a(bVar, true);
                    dVar = this.e;
                }
                bVarA = a(fVarA, dVar);
            }
            this.d.unlock();
            return bVarA;
        } catch (Throwable th2) {
            this.d.unlock();
            throw th2;
        }
    }

    protected b a(f fVar, c.a.a.a.m0.d dVar) {
        if (this.f282c.a()) {
            this.f282c.a("Creating new connection [" + fVar.d() + "]");
        }
        b bVar = new b(dVar, fVar.d(), this.k, this.l);
        this.d.lock();
        try {
            fVar.a(bVar);
            this.o++;
            this.g.add(bVar);
            return bVar;
        } finally {
            this.d.unlock();
        }
    }

    protected b a(f fVar, Object obj) {
        this.d.lock();
        boolean z = false;
        b bVarA = null;
        while (!z) {
            try {
                bVarA = fVar.a(obj);
                if (bVarA != null) {
                    if (this.f282c.a()) {
                        this.f282c.a("Getting free connection [" + fVar.d() + "][" + obj + "]");
                    }
                    this.h.remove(bVarA);
                    if (bVarA.a(System.currentTimeMillis())) {
                        if (this.f282c.a()) {
                            this.f282c.a("Closing expired free connection [" + fVar.d() + "][" + obj + "]");
                        }
                        b(bVarA);
                        fVar.a();
                        this.o--;
                    } else {
                        this.g.add(bVarA);
                    }
                } else if (this.f282c.a()) {
                    this.f282c.a("No free connections [" + fVar.d() + "][" + obj + "]");
                }
                z = true;
            } catch (Throwable th) {
                this.d.unlock();
                throw th;
            }
        }
        this.d.unlock();
        return bVarA;
    }

    public e a(c.a.a.a.m0.u.b bVar, Object obj) {
        return new a(new i(), bVar, obj);
    }

    protected f a(c.a.a.a.m0.u.b bVar) {
        return new f(bVar, this.f);
    }

    protected f a(c.a.a.a.m0.u.b bVar, boolean z) {
        this.d.lock();
        try {
            f fVarA = this.j.get(bVar);
            if (fVarA == null && z) {
                fVarA = a(bVar);
                this.j.put(bVar, fVarA);
            }
            return fVarA;
        } finally {
            this.d.unlock();
        }
    }

    protected h a(Condition condition, f fVar) {
        return new h(condition, fVar);
    }

    protected Queue<b> a() {
        return new LinkedList();
    }

    protected void a(b bVar) {
        c.a.a.a.m0.u.b bVarD = bVar.d();
        if (this.f282c.a()) {
            this.f282c.a("Deleting connection [" + bVarD + "][" + bVar.a() + "]");
        }
        this.d.lock();
        try {
            b(bVar);
            f fVarA = a(bVarD, true);
            fVarA.b(bVar);
            this.o--;
            if (fVarA.f()) {
                this.j.remove(bVarD);
            }
        } finally {
            this.d.unlock();
        }
    }

    public void a(b bVar, boolean z, long j, TimeUnit timeUnit) {
        String str;
        c.a.a.a.m0.u.b bVarD = bVar.d();
        if (this.f282c.a()) {
            this.f282c.a("Releasing connection [" + bVarD + "][" + bVar.a() + "]");
        }
        this.d.lock();
        try {
            if (this.m) {
                b(bVar);
                return;
            }
            this.g.remove(bVar);
            f fVarA = a(bVarD, true);
            if (!z || fVarA.b() < 0) {
                b(bVar);
                fVarA.a();
                this.o--;
            } else {
                if (this.f282c.a()) {
                    if (j > 0) {
                        str = "for " + j + " " + timeUnit;
                    } else {
                        str = "indefinitely";
                    }
                    this.f282c.a("Pooling connection [" + bVarD + "][" + bVar.a() + "]; keep alive " + str);
                }
                fVarA.c(bVar);
                bVar.a(j, timeUnit);
                this.h.add(bVar);
            }
            a(fVarA);
        } finally {
            this.d.unlock();
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0039 A[Catch: all -> 0x0074, TryCatch #0 {all -> 0x0074, blocks: (B:4:0x0007, B:6:0x000d, B:8:0x0015, B:9:0x0034, B:21:0x006b, B:10:0x0039, B:12:0x0041, B:14:0x0049, B:15:0x0050, B:16:0x0059, B:18:0x0061), top: B:27:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:12:0x0041 A[Catch: all -> 0x0074, TryCatch #0 {all -> 0x0074, blocks: (B:4:0x0007, B:6:0x000d, B:8:0x0015, B:9:0x0034, B:21:0x006b, B:10:0x0039, B:12:0x0041, B:14:0x0049, B:15:0x0050, B:16:0x0059, B:18:0x0061), top: B:27:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:14:0x0049 A[Catch: all -> 0x0074, TryCatch #0 {all -> 0x0074, blocks: (B:4:0x0007, B:6:0x000d, B:8:0x0015, B:9:0x0034, B:21:0x006b, B:10:0x0039, B:12:0x0041, B:14:0x0049, B:15:0x0050, B:16:0x0059, B:18:0x0061), top: B:27:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:16:0x0059 A[Catch: all -> 0x0074, TryCatch #0 {all -> 0x0074, blocks: (B:4:0x0007, B:6:0x000d, B:8:0x0015, B:9:0x0034, B:21:0x006b, B:10:0x0039, B:12:0x0041, B:14:0x0049, B:15:0x0050, B:16:0x0059, B:18:0x0061), top: B:27:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x0061 A[Catch: all -> 0x0074, TryCatch #0 {all -> 0x0074, blocks: (B:4:0x0007, B:6:0x000d, B:8:0x0015, B:9:0x0034, B:21:0x006b, B:10:0x0039, B:12:0x0041, B:14:0x0049, B:15:0x0050, B:16:0x0059, B:18:0x0061), top: B:27:0x0007 }] */
    protected void a(f fVar) {
        h hVarG;
        this.d.lock();
        if (fVar != null) {
            try {
                if (fVar.e()) {
                    if (this.f282c.a()) {
                        this.f282c.a("Notifying thread waiting on pool [" + fVar.d() + "]");
                    }
                    hVarG = fVar.g();
                } else if (this.i.isEmpty()) {
                    if (this.f282c.a()) {
                        this.f282c.a("Notifying no-one, there are no waiting threads");
                    }
                    hVarG = null;
                } else {
                    if (this.f282c.a()) {
                        this.f282c.a("Notifying thread waiting on any pool");
                    }
                    hVarG = this.i.remove();
                }
            } finally {
                this.d.unlock();
            }
        } else if (this.i.isEmpty()) {
            if (this.f282c.a()) {
                this.f282c.a("Notifying thread waiting on any pool");
            }
            hVarG = this.i.remove();
        } else {
            if (this.f282c.a()) {
                this.f282c.a("Notifying no-one, there are no waiting threads");
            }
            hVarG = null;
        }
        if (hVarG != null) {
            hVarG.b();
        }
    }

    protected Map<c.a.a.a.m0.u.b, f> b() {
        return new HashMap();
    }

    protected Queue<h> c() {
        return new LinkedList();
    }

    protected void d() {
        this.d.lock();
        try {
            b bVarRemove = this.h.remove();
            if (bVarRemove != null) {
                a(bVarRemove);
            } else if (this.f282c.a()) {
                this.f282c.a("No free connection to delete");
            }
        } finally {
            this.d.unlock();
        }
    }

    public void e() {
        this.d.lock();
        try {
            if (this.m) {
                return;
            }
            this.m = true;
            Iterator<b> it = this.g.iterator();
            while (it.hasNext()) {
                b next = it.next();
                it.remove();
                b(next);
            }
            Iterator<b> it2 = this.h.iterator();
            while (it2.hasNext()) {
                b next2 = it2.next();
                it2.remove();
                if (this.f282c.a()) {
                    this.f282c.a("Closing connection [" + next2.d() + "][" + next2.a() + "]");
                }
                b(next2);
            }
            Iterator<h> it3 = this.i.iterator();
            while (it3.hasNext()) {
                h next3 = it3.next();
                it3.remove();
                next3.b();
            }
            this.j.clear();
        } finally {
            this.d.unlock();
        }
    }
}

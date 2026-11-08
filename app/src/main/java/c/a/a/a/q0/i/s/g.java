package c.a.a.a.q0.i.s;

import c.a.a.a.m0.o;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class g implements c.a.a.a.m0.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f289a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final c.a.a.a.m0.v.i f290b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final d f291c;
    protected final c.a.a.a.m0.d d;

    class a implements c.a.a.a.m0.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ e f292a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.m0.u.b f293b;

        a(e eVar, c.a.a.a.m0.u.b bVar) {
            this.f292a = eVar;
            this.f293b = bVar;
        }

        @Override // c.a.a.a.m0.e
        public o a(long j, TimeUnit timeUnit) {
            c.a.a.a.x0.a.a(this.f293b, "Route");
            if (g.this.f289a.a()) {
                g.this.f289a.a("Get connection: " + this.f293b + ", timeout = " + j);
            }
            return new c(g.this, this.f292a.a(j, timeUnit));
        }

        @Override // c.a.a.a.m0.e
        public void a() {
            this.f292a.a();
        }
    }

    @Deprecated
    public g(c.a.a.a.t0.e eVar, c.a.a.a.m0.v.i iVar) {
        c.a.a.a.x0.a.a(iVar, "Scheme registry");
        this.f289a = new c.a.a.a.p0.b(g.class);
        this.f290b = iVar;
        new c.a.a.a.m0.t.c();
        this.d = a(iVar);
        this.f291c = (d) a(eVar);
    }

    protected c.a.a.a.m0.d a(c.a.a.a.m0.v.i iVar) {
        return new c.a.a.a.q0.i.g(iVar);
    }

    @Override // c.a.a.a.m0.b
    public c.a.a.a.m0.e a(c.a.a.a.m0.u.b bVar, Object obj) {
        return new a(this.f291c.a(bVar, obj), bVar);
    }

    @Override // c.a.a.a.m0.b
    public c.a.a.a.m0.v.i a() {
        return this.f290b;
    }

    @Deprecated
    protected c.a.a.a.q0.i.s.a a(c.a.a.a.t0.e eVar) {
        return new d(this.d, eVar);
    }

    @Override // c.a.a.a.m0.b
    public void a(o oVar, long j, TimeUnit timeUnit) {
        c.a.a.a.p0.b bVar;
        String str;
        boolean zP;
        d dVar;
        c.a.a.a.p0.b bVar2;
        String str2;
        c.a.a.a.p0.b bVar3;
        String str3;
        c.a.a.a.x0.a.a(oVar instanceof c, "Connection class mismatch, connection not obtained from this manager");
        c cVar = (c) oVar;
        if (cVar.r() != null) {
            c.a.a.a.x0.b.a(cVar.n() == this, "Connection not obtained from this manager");
        }
        synchronized (cVar) {
            b bVar4 = (b) cVar.r();
            if (bVar4 == null) {
                return;
            }
            try {
                try {
                    if (cVar.isOpen() && !cVar.p()) {
                        cVar.c();
                    }
                    zP = cVar.p();
                    if (this.f289a.a()) {
                        if (zP) {
                            bVar3 = this.f289a;
                            str3 = "Released connection is reusable.";
                        } else {
                            bVar3 = this.f289a;
                            str3 = "Released connection is not reusable.";
                        }
                        bVar3.a(str3);
                    }
                    cVar.m();
                    dVar = this.f291c;
                } catch (IOException e) {
                    if (this.f289a.a()) {
                        this.f289a.a("Exception shutting down released connection.", e);
                    }
                    zP = cVar.p();
                    if (this.f289a.a()) {
                        if (zP) {
                            bVar2 = this.f289a;
                            str2 = "Released connection is reusable.";
                        } else {
                            bVar2 = this.f289a;
                            str2 = "Released connection is not reusable.";
                        }
                        bVar2.a(str2);
                    }
                    cVar.m();
                    dVar = this.f291c;
                }
                dVar.a(bVar4, zP, j, timeUnit);
            } catch (Throwable th) {
                boolean zP2 = cVar.p();
                if (this.f289a.a()) {
                    if (zP2) {
                        bVar = this.f289a;
                        str = "Released connection is reusable.";
                    } else {
                        bVar = this.f289a;
                        str = "Released connection is not reusable.";
                    }
                    bVar.a(str);
                }
                cVar.m();
                this.f291c.a(bVar4, zP2, j, timeUnit);
                throw th;
            }
        }
    }

    @Override // c.a.a.a.m0.b
    public void c() {
        this.f289a.a("Shutting down");
        this.f291c.e();
    }

    protected void finalize() throws Throwable {
        try {
            c();
        } finally {
            super.finalize();
        }
    }
}

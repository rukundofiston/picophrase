package c.a.a.a.q0.i;

import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final c.a.a.a.m0.d f255a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final c.a.a.a.m0.q f256b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected volatile c.a.a.a.m0.u.b f257c;
    protected volatile Object d;
    protected volatile c.a.a.a.m0.u.f e;

    protected b(c.a.a.a.m0.d dVar, c.a.a.a.m0.u.b bVar) {
        c.a.a.a.x0.a.a(dVar, "Connection operator");
        this.f255a = dVar;
        this.f256b = dVar.a();
        this.f257c = bVar;
        this.e = null;
    }

    public Object a() {
        return this.d;
    }

    public void a(c.a.a.a.m0.u.b bVar, c.a.a.a.v0.e eVar, c.a.a.a.t0.e eVar2) throws InterruptedIOException {
        c.a.a.a.x0.a.a(bVar, "Route");
        c.a.a.a.x0.a.a(eVar2, "HTTP parameters");
        if (this.e != null) {
            c.a.a.a.x0.b.a(!this.e.h(), "Connection already open");
        }
        this.e = new c.a.a.a.m0.u.f(bVar);
        c.a.a.a.n nVarG = bVar.g();
        this.f255a.a(this.f256b, nVarG != null ? nVarG : bVar.b(), bVar.d(), eVar, eVar2);
        c.a.a.a.m0.u.f fVar = this.e;
        if (fVar == null) {
            throw new InterruptedIOException("Request aborted");
        }
        boolean zA = this.f256b.a();
        if (nVarG == null) {
            fVar.a(zA);
        } else {
            fVar.a(nVarG, zA);
        }
    }

    public void a(c.a.a.a.v0.e eVar, c.a.a.a.t0.e eVar2) {
        c.a.a.a.x0.a.a(eVar2, "HTTP parameters");
        c.a.a.a.x0.b.a(this.e, "Route tracker");
        c.a.a.a.x0.b.a(this.e.h(), "Connection not open");
        c.a.a.a.x0.b.a(this.e.f(), "Protocol layering without a tunnel not supported");
        c.a.a.a.x0.b.a(!this.e.e(), "Multiple protocol layering not supported");
        this.f255a.a(this.f256b, this.e.b(), eVar, eVar2);
        this.e.b(this.f256b.a());
    }

    public void a(Object obj) {
        this.d = obj;
    }

    public void a(boolean z, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        c.a.a.a.x0.b.a(this.e, "Route tracker");
        c.a.a.a.x0.b.a(this.e.h(), "Connection not open");
        c.a.a.a.x0.b.a(!this.e.f(), "Connection is already tunnelled");
        this.f256b.a(null, this.e.b(), z, eVar);
        this.e.c(z);
    }

    protected void b() {
        this.e = null;
        this.d = null;
    }
}

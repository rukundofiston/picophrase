package c.a.a.a.q0;

import c.a.a.a.i;
import c.a.a.a.l;
import c.a.a.a.q;
import c.a.a.a.q0.l.j;
import c.a.a.a.r0.g;
import c.a.a.a.s;
import c.a.a.a.t;
import java.io.IOException;
import java.net.SocketTimeoutException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class a implements i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c.a.a.a.r0.f f190c = null;
    private g d = null;
    private c.a.a.a.r0.b e = null;
    private c.a.a.a.r0.c<s> f = null;
    private c.a.a.a.r0.d<q> g = null;
    private e h = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.q0.k.b f188a = m();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.q0.k.a f189b = l();

    protected e a(c.a.a.a.r0.e eVar, c.a.a.a.r0.e eVar2) {
        return new e(eVar, eVar2);
    }

    protected abstract c.a.a.a.r0.c<s> a(c.a.a.a.r0.f fVar, t tVar, c.a.a.a.t0.e eVar);

    protected c.a.a.a.r0.d<q> a(g gVar, c.a.a.a.t0.e eVar) {
        return new j(gVar, null, eVar);
    }

    @Override // c.a.a.a.i
    public void a(l lVar) throws IOException {
        c.a.a.a.x0.a.a(lVar, "HTTP request");
        f();
        if (lVar.c() == null) {
            return;
        }
        this.f188a.a(this.d, lVar, lVar.c());
    }

    @Override // c.a.a.a.i
    public void a(q qVar) {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        f();
        this.g.a(qVar);
        this.h.a();
    }

    protected void a(c.a.a.a.r0.f fVar, g gVar, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(fVar, "Input session buffer");
        this.f190c = fVar;
        c.a.a.a.x0.a.a(gVar, "Output session buffer");
        this.d = gVar;
        if (fVar instanceof c.a.a.a.r0.b) {
            this.e = (c.a.a.a.r0.b) fVar;
        }
        this.f = a(fVar, n(), eVar);
        this.g = a(gVar, eVar);
        this.h = a(fVar.a(), gVar.a());
    }

    @Override // c.a.a.a.i
    public void a(s sVar) {
        c.a.a.a.x0.a.a(sVar, "HTTP response");
        f();
        sVar.a(this.f189b.a(this.f190c, sVar));
    }

    @Override // c.a.a.a.i
    public boolean b(int i) {
        f();
        try {
            return this.f190c.a(i);
        } catch (SocketTimeoutException unused) {
            return false;
        }
    }

    protected abstract void f();

    @Override // c.a.a.a.i
    public void flush() {
        f();
        o();
    }

    @Override // c.a.a.a.i
    public s g() {
        f();
        s sVar = (s) this.f.a();
        if (sVar.j().c() >= 200) {
            this.h.b();
        }
        return sVar;
    }

    @Override // c.a.a.a.j
    public boolean k() {
        if (!isOpen() || p()) {
            return true;
        }
        try {
            this.f190c.a(1);
            return p();
        } catch (SocketTimeoutException unused) {
            return false;
        } catch (IOException unused2) {
            return true;
        }
    }

    protected c.a.a.a.q0.k.a l() {
        return new c.a.a.a.q0.k.a(new c.a.a.a.q0.k.c());
    }

    protected c.a.a.a.q0.k.b m() {
        return new c.a.a.a.q0.k.b(new c.a.a.a.q0.k.d());
    }

    protected t n() {
        return c.f191b;
    }

    protected void o() {
        this.d.flush();
    }

    protected boolean p() {
        c.a.a.a.r0.b bVar = this.e;
        return bVar != null && bVar.b();
    }
}

package c.a.a.a.q0.l;

import c.a.a.a.p;
import c.a.a.a.s0.t;

/* JADX INFO: loaded from: classes.dex */
public abstract class b<T extends p> implements c.a.a.a.r0.d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final c.a.a.a.r0.g f330a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final c.a.a.a.x0.d f331b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final t f332c;

    @Deprecated
    public b(c.a.a.a.r0.g gVar, t tVar, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(gVar, "Session input buffer");
        this.f330a = gVar;
        this.f331b = new c.a.a.a.x0.d(128);
        this.f332c = tVar == null ? c.a.a.a.s0.j.f376a : tVar;
    }

    @Override // c.a.a.a.r0.d
    public void a(T t) {
        c.a.a.a.x0.a.a(t, "HTTP message");
        b(t);
        c.a.a.a.h hVarD = t.d();
        while (hVarD.hasNext()) {
            this.f330a.a(this.f332c.a(this.f331b, hVarD.nextHeader()));
        }
        this.f331b.b();
        this.f330a.a(this.f331b);
    }

    protected abstract void b(T t);
}

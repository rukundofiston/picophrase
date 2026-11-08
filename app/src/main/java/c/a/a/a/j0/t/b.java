package c.a.a.a.j0.t;

import c.a.a.a.q;
import c.a.a.a.s0.r;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends c.a.a.a.s0.a implements c.a.a.a.j0.t.a, Cloneable, q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AtomicBoolean f89c = new AtomicBoolean(false);
    private final AtomicReference<c.a.a.a.k0.a> d = new AtomicReference<>(null);

    class a implements c.a.a.a.k0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.m0.e f90a;

        a(b bVar, c.a.a.a.m0.e eVar) {
            this.f90a = eVar;
        }

        @Override // c.a.a.a.k0.a
        public boolean a() {
            this.f90a.a();
            return true;
        }
    }

    /* JADX INFO: renamed from: c.a.a.a.j0.t.b$b, reason: collision with other inner class name */
    class C0006b implements c.a.a.a.k0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.m0.i f91a;

        C0006b(b bVar, c.a.a.a.m0.i iVar) {
            this.f91a = iVar;
        }

        @Override // c.a.a.a.k0.a
        public boolean a() {
            try {
                this.f91a.f();
                return true;
            } catch (IOException unused) {
                return false;
            }
        }
    }

    protected b() {
    }

    public void a(c.a.a.a.k0.a aVar) {
        if (this.f89c.get()) {
            return;
        }
        this.d.set(aVar);
    }

    @Override // c.a.a.a.j0.t.a
    @Deprecated
    public void a(c.a.a.a.m0.e eVar) {
        a(new a(this, eVar));
    }

    @Override // c.a.a.a.j0.t.a
    @Deprecated
    public void a(c.a.a.a.m0.i iVar) {
        a(new C0006b(this, iVar));
    }

    public Object clone() {
        b bVar = (b) super.clone();
        bVar.f357a = (r) c.a.a.a.j0.w.a.a(this.f357a);
        bVar.f358b = (c.a.a.a.t0.e) c.a.a.a.j0.w.a.a(this.f358b);
        return bVar;
    }

    public void e() {
        c.a.a.a.k0.a andSet;
        if (!this.f89c.compareAndSet(false, true) || (andSet = this.d.getAndSet(null)) == null) {
            return;
        }
        andSet.a();
    }

    public boolean m() {
        return this.f89c.get();
    }
}

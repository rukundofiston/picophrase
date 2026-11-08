package c.a.a.a.q0.i;

import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class c extends a {
    protected volatile b f;

    protected c(c.a.a.a.m0.b bVar, b bVar2) {
        super(bVar, bVar2.f256b);
        this.f = bVar2;
    }

    @Override // c.a.a.a.m0.o
    public void a(c.a.a.a.m0.u.b bVar, c.a.a.a.v0.e eVar, c.a.a.a.t0.e eVar2) throws InterruptedIOException {
        b bVarR = r();
        a(bVarR);
        bVarR.a(bVar, eVar, eVar2);
    }

    protected void a(b bVar) {
        if (q() || bVar == null) {
            throw new e();
        }
    }

    @Override // c.a.a.a.m0.o
    public void a(c.a.a.a.v0.e eVar, c.a.a.a.t0.e eVar2) {
        b bVarR = r();
        a(bVarR);
        bVarR.a(eVar, eVar2);
    }

    @Override // c.a.a.a.m0.o
    public void a(Object obj) {
        b bVarR = r();
        a(bVarR);
        bVarR.a(obj);
    }

    @Override // c.a.a.a.m0.o
    public void a(boolean z, c.a.a.a.t0.e eVar) {
        b bVarR = r();
        a(bVarR);
        bVarR.a(z, eVar);
    }

    @Override // c.a.a.a.m0.o, c.a.a.a.m0.n
    public c.a.a.a.m0.u.b b() {
        b bVarR = r();
        a(bVarR);
        if (bVarR.e == null) {
            return null;
        }
        return bVarR.e.j();
    }

    @Override // c.a.a.a.j
    public void c() {
        b bVarR = r();
        if (bVarR != null) {
            bVarR.b();
        }
        c.a.a.a.m0.q qVarO = o();
        if (qVarO != null) {
            qVarO.c();
        }
    }

    @Override // c.a.a.a.j, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        b bVarR = r();
        if (bVarR != null) {
            bVarR.b();
        }
        c.a.a.a.m0.q qVarO = o();
        if (qVarO != null) {
            qVarO.close();
        }
    }

    @Override // c.a.a.a.q0.i.a
    protected synchronized void m() {
        this.f = null;
        super.m();
    }

    @Deprecated
    protected b r() {
        return this.f;
    }
}

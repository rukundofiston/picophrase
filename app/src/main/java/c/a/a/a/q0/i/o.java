package c.a.a.a.q0.i;

import c.a.a.a.s;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class o implements c.a.a.a.m0.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.m0.b f275a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.m0.d f276b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile k f277c;
    private volatile boolean d;
    private volatile long e;

    o(c.a.a.a.m0.b bVar, c.a.a.a.m0.d dVar, k kVar) {
        c.a.a.a.x0.a.a(bVar, "Connection manager");
        c.a.a.a.x0.a.a(dVar, "Connection operator");
        c.a.a.a.x0.a.a(kVar, "HTTP pool entry");
        this.f275a = bVar;
        this.f276b = dVar;
        this.f277c = kVar;
        this.d = false;
        this.e = Long.MAX_VALUE;
    }

    private c.a.a.a.m0.q q() {
        k kVar = this.f277c;
        if (kVar != null) {
            return kVar.a();
        }
        throw new e();
    }

    private k r() {
        k kVar = this.f277c;
        if (kVar != null) {
            return kVar;
        }
        throw new e();
    }

    private c.a.a.a.m0.q s() {
        k kVar = this.f277c;
        if (kVar == null) {
            return null;
        }
        return kVar.a();
    }

    @Override // c.a.a.a.j
    public void a(int i) {
        q().a(i);
    }

    @Override // c.a.a.a.m0.o
    public void a(long j, TimeUnit timeUnit) {
        this.e = j > 0 ? timeUnit.toMillis(j) : -1L;
    }

    @Override // c.a.a.a.i
    public void a(c.a.a.a.l lVar) {
        q().a(lVar);
    }

    @Override // c.a.a.a.m0.o
    public void a(c.a.a.a.m0.u.b bVar, c.a.a.a.v0.e eVar, c.a.a.a.t0.e eVar2) {
        c.a.a.a.m0.q qVarA;
        c.a.a.a.x0.a.a(bVar, "Route");
        c.a.a.a.x0.a.a(eVar2, "HTTP parameters");
        synchronized (this) {
            if (this.f277c == null) {
                throw new e();
            }
            c.a.a.a.m0.u.f fVarG = this.f277c.g();
            c.a.a.a.x0.b.a(fVarG, "Route tracker");
            c.a.a.a.x0.b.a(!fVarG.h(), "Connection already open");
            qVarA = this.f277c.a();
        }
        c.a.a.a.n nVarG = bVar.g();
        this.f276b.a(qVarA, nVarG != null ? nVarG : bVar.b(), bVar.d(), eVar, eVar2);
        synchronized (this) {
            if (this.f277c == null) {
                throw new InterruptedIOException();
            }
            c.a.a.a.m0.u.f fVarG2 = this.f277c.g();
            if (nVarG == null) {
                fVarG2.a(qVarA.a());
            } else {
                fVarG2.a(nVarG, qVarA.a());
            }
        }
    }

    @Override // c.a.a.a.i
    public void a(c.a.a.a.q qVar) {
        q().a(qVar);
    }

    @Override // c.a.a.a.i
    public void a(s sVar) {
        q().a(sVar);
    }

    @Override // c.a.a.a.m0.o
    public void a(c.a.a.a.v0.e eVar, c.a.a.a.t0.e eVar2) {
        c.a.a.a.n nVarB;
        c.a.a.a.m0.q qVarA;
        c.a.a.a.x0.a.a(eVar2, "HTTP parameters");
        synchronized (this) {
            if (this.f277c == null) {
                throw new e();
            }
            c.a.a.a.m0.u.f fVarG = this.f277c.g();
            c.a.a.a.x0.b.a(fVarG, "Route tracker");
            c.a.a.a.x0.b.a(fVarG.h(), "Connection not open");
            c.a.a.a.x0.b.a(fVarG.f(), "Protocol layering without a tunnel not supported");
            c.a.a.a.x0.b.a(!fVarG.e(), "Multiple protocol layering not supported");
            nVarB = fVarG.b();
            qVarA = this.f277c.a();
        }
        this.f276b.a(qVarA, nVarB, eVar, eVar2);
        synchronized (this) {
            if (this.f277c == null) {
                throw new InterruptedIOException();
            }
            this.f277c.g().b(qVarA.a());
        }
    }

    @Override // c.a.a.a.m0.o
    public void a(Object obj) {
        r().a(obj);
    }

    @Override // c.a.a.a.m0.o
    public void a(boolean z, c.a.a.a.t0.e eVar) {
        c.a.a.a.n nVarB;
        c.a.a.a.m0.q qVarA;
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        synchronized (this) {
            if (this.f277c == null) {
                throw new e();
            }
            c.a.a.a.m0.u.f fVarG = this.f277c.g();
            c.a.a.a.x0.b.a(fVarG, "Route tracker");
            c.a.a.a.x0.b.a(fVarG.h(), "Connection not open");
            c.a.a.a.x0.b.a(!fVarG.f(), "Connection is already tunnelled");
            nVarB = fVarG.b();
            qVarA = this.f277c.a();
        }
        qVarA.a(null, nVarB, z, eVar);
        synchronized (this) {
            if (this.f277c == null) {
                throw new InterruptedIOException();
            }
            this.f277c.g().c(z);
        }
    }

    @Override // c.a.a.a.m0.o, c.a.a.a.m0.n
    public c.a.a.a.m0.u.b b() {
        return r().e();
    }

    @Override // c.a.a.a.i
    public boolean b(int i) {
        return q().b(i);
    }

    @Override // c.a.a.a.j
    public void c() {
        k kVar = this.f277c;
        if (kVar != null) {
            c.a.a.a.m0.q qVarA = kVar.a();
            kVar.g().i();
            qVarA.c();
        }
    }

    @Override // c.a.a.a.j, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        k kVar = this.f277c;
        if (kVar != null) {
            c.a.a.a.m0.q qVarA = kVar.a();
            kVar.g().i();
            qVarA.close();
        }
    }

    @Override // c.a.a.a.m0.p
    public SSLSession d() {
        Socket socketE = q().e();
        if (socketE instanceof SSLSocket) {
            return ((SSLSocket) socketE).getSession();
        }
        return null;
    }

    @Override // c.a.a.a.m0.i
    public void f() {
        synchronized (this) {
            if (this.f277c == null) {
                return;
            }
            this.d = false;
            try {
                this.f277c.a().c();
            } catch (IOException unused) {
            }
            this.f275a.a(this, this.e, TimeUnit.MILLISECONDS);
            this.f277c = null;
        }
    }

    @Override // c.a.a.a.i
    public void flush() {
        q().flush();
    }

    @Override // c.a.a.a.i
    public s g() {
        return q().g();
    }

    @Override // c.a.a.a.o
    public InetAddress getRemoteAddress() {
        return q().getRemoteAddress();
    }

    @Override // c.a.a.a.o
    public int h() {
        return q().h();
    }

    @Override // c.a.a.a.m0.o
    public void i() {
        this.d = true;
    }

    @Override // c.a.a.a.j
    public boolean isOpen() {
        c.a.a.a.m0.q qVarS = s();
        if (qVarS != null) {
            return qVarS.isOpen();
        }
        return false;
    }

    @Override // c.a.a.a.m0.o
    public void j() {
        this.d = false;
    }

    @Override // c.a.a.a.j
    public boolean k() {
        c.a.a.a.m0.q qVarS = s();
        if (qVarS != null) {
            return qVarS.k();
        }
        return true;
    }

    @Override // c.a.a.a.m0.i
    public void l() {
        synchronized (this) {
            if (this.f277c == null) {
                return;
            }
            this.f275a.a(this, this.e, TimeUnit.MILLISECONDS);
            this.f277c = null;
        }
    }

    k m() {
        k kVar = this.f277c;
        this.f277c = null;
        return kVar;
    }

    public c.a.a.a.m0.b n() {
        return this.f275a;
    }

    k o() {
        return this.f277c;
    }

    public boolean p() {
        return this.d;
    }
}

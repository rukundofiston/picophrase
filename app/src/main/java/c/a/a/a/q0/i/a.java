package c.a.a.a.q0.i;

import c.a.a.a.s;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class a implements c.a.a.a.m0.o, c.a.a.a.v0.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.m0.b f252a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile c.a.a.a.m0.q f253b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile boolean f254c = false;
    private volatile boolean d = false;
    private volatile long e = Long.MAX_VALUE;

    protected a(c.a.a.a.m0.b bVar, c.a.a.a.m0.q qVar) {
        this.f252a = bVar;
        this.f253b = qVar;
    }

    @Override // c.a.a.a.v0.e
    public Object a(String str) {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        if (qVarO instanceof c.a.a.a.v0.e) {
            return ((c.a.a.a.v0.e) qVarO).a(str);
        }
        return null;
    }

    @Override // c.a.a.a.j
    public void a(int i) {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        qVarO.a(i);
    }

    @Override // c.a.a.a.m0.o
    public void a(long j, TimeUnit timeUnit) {
        this.e = j > 0 ? timeUnit.toMillis(j) : -1L;
    }

    @Override // c.a.a.a.i
    public void a(c.a.a.a.l lVar) {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        j();
        qVarO.a(lVar);
    }

    protected final void a(c.a.a.a.m0.q qVar) {
        if (q() || qVar == null) {
            throw new e();
        }
    }

    @Override // c.a.a.a.i
    public void a(c.a.a.a.q qVar) {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        j();
        qVarO.a(qVar);
    }

    @Override // c.a.a.a.i
    public void a(s sVar) {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        j();
        qVarO.a(sVar);
    }

    @Override // c.a.a.a.v0.e
    public void a(String str, Object obj) {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        if (qVarO instanceof c.a.a.a.v0.e) {
            ((c.a.a.a.v0.e) qVarO).a(str, obj);
        }
    }

    @Override // c.a.a.a.i
    public boolean b(int i) {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        return qVarO.b(i);
    }

    @Override // c.a.a.a.m0.p
    public SSLSession d() {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        if (!isOpen()) {
            return null;
        }
        Socket socketE = qVarO.e();
        if (socketE instanceof SSLSocket) {
            return ((SSLSocket) socketE).getSession();
        }
        return null;
    }

    @Override // c.a.a.a.m0.i
    public synchronized void f() {
        if (this.d) {
            return;
        }
        this.d = true;
        j();
        try {
            c();
        } catch (IOException unused) {
        }
        this.f252a.a(this, this.e, TimeUnit.MILLISECONDS);
    }

    @Override // c.a.a.a.i
    public void flush() {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        qVarO.flush();
    }

    @Override // c.a.a.a.i
    public s g() {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        j();
        return qVarO.g();
    }

    @Override // c.a.a.a.o
    public InetAddress getRemoteAddress() {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        return qVarO.getRemoteAddress();
    }

    @Override // c.a.a.a.o
    public int h() {
        c.a.a.a.m0.q qVarO = o();
        a(qVarO);
        return qVarO.h();
    }

    @Override // c.a.a.a.m0.o
    public void i() {
        this.f254c = true;
    }

    @Override // c.a.a.a.j
    public boolean isOpen() {
        c.a.a.a.m0.q qVarO = o();
        if (qVarO == null) {
            return false;
        }
        return qVarO.isOpen();
    }

    @Override // c.a.a.a.m0.o
    public void j() {
        this.f254c = false;
    }

    @Override // c.a.a.a.j
    public boolean k() {
        c.a.a.a.m0.q qVarO;
        if (q() || (qVarO = o()) == null) {
            return true;
        }
        return qVarO.k();
    }

    @Override // c.a.a.a.m0.i
    public synchronized void l() {
        if (this.d) {
            return;
        }
        this.d = true;
        this.f252a.a(this, this.e, TimeUnit.MILLISECONDS);
    }

    protected synchronized void m() {
        this.f253b = null;
        this.e = Long.MAX_VALUE;
    }

    protected c.a.a.a.m0.b n() {
        return this.f252a;
    }

    protected c.a.a.a.m0.q o() {
        return this.f253b;
    }

    public boolean p() {
        return this.f254c;
    }

    protected boolean q() {
        return this.d;
    }
}

package c.a.a.a.q0.i;

import c.a.a.a.s;
import c.a.a.a.t;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class f extends c.a.a.a.q0.f implements c.a.a.a.m0.q, c.a.a.a.m0.p, c.a.a.a.v0.e {
    private volatile Socket n;
    private boolean o;
    private volatile boolean p;
    public c.a.a.a.p0.b k = new c.a.a.a.p0.b(f.class);
    public c.a.a.a.p0.b l = new c.a.a.a.p0.b("cz.msebera.android.httpclient.headers");
    public c.a.a.a.p0.b m = new c.a.a.a.p0.b("cz.msebera.android.httpclient.wire");
    private final Map<String, Object> q = new HashMap();

    @Override // c.a.a.a.q0.a
    protected c.a.a.a.r0.c<s> a(c.a.a.a.r0.f fVar, t tVar, c.a.a.a.t0.e eVar) {
        return new h(fVar, null, tVar, eVar);
    }

    @Override // c.a.a.a.q0.f
    protected c.a.a.a.r0.f a(Socket socket, int i, c.a.a.a.t0.e eVar) {
        if (i <= 0) {
            i = 8192;
        }
        c.a.a.a.r0.f fVarA = super.a(socket, i, eVar);
        return this.m.a() ? new m(fVarA, new r(this.m), c.a.a.a.t0.f.a(eVar)) : fVarA;
    }

    @Override // c.a.a.a.v0.e
    public Object a(String str) {
        return this.q.get(str);
    }

    @Override // c.a.a.a.q0.a, c.a.a.a.i
    public void a(c.a.a.a.q qVar) {
        if (this.k.a()) {
            this.k.a("Sending request: " + qVar.g());
        }
        super.a(qVar);
        if (this.l.a()) {
            this.l.a(">> " + qVar.g().toString());
            for (c.a.a.a.e eVar : qVar.l()) {
                this.l.a(">> " + eVar.toString());
            }
        }
    }

    @Override // c.a.a.a.v0.e
    public void a(String str, Object obj) {
        this.q.put(str, obj);
    }

    @Override // c.a.a.a.m0.q
    public void a(Socket socket, c.a.a.a.n nVar) throws IOException {
        q();
        this.n = socket;
        if (this.p) {
            socket.close();
            throw new InterruptedIOException("Connection already shutdown");
        }
    }

    @Override // c.a.a.a.m0.q
    public void a(Socket socket, c.a.a.a.n nVar, boolean z, c.a.a.a.t0.e eVar) {
        f();
        c.a.a.a.x0.a.a(nVar, "Target host");
        c.a.a.a.x0.a.a(eVar, "Parameters");
        if (socket != null) {
            this.n = socket;
            a(socket, eVar);
        }
        this.o = z;
    }

    @Override // c.a.a.a.m0.q
    public final boolean a() {
        return this.o;
    }

    @Override // c.a.a.a.q0.f
    protected c.a.a.a.r0.g b(Socket socket, int i, c.a.a.a.t0.e eVar) {
        if (i <= 0) {
            i = 8192;
        }
        c.a.a.a.r0.g gVarB = super.b(socket, i, eVar);
        return this.m.a() ? new n(gVarB, new r(this.m), c.a.a.a.t0.f.a(eVar)) : gVarB;
    }

    @Override // c.a.a.a.m0.q
    public void b(boolean z, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(eVar, "Parameters");
        q();
        this.o = z;
        a(this.n, eVar);
    }

    @Override // c.a.a.a.q0.f, c.a.a.a.j
    public void c() {
        this.p = true;
        try {
            super.c();
            if (this.k.a()) {
                this.k.a("Connection " + this + " shut down");
            }
            Socket socket = this.n;
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            this.k.a("I/O error shutting down connection", e);
        }
    }

    @Override // c.a.a.a.q0.f, c.a.a.a.j, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            super.close();
            if (this.k.a()) {
                this.k.a("Connection " + this + " closed");
            }
        } catch (IOException e) {
            this.k.a("I/O error closing connection", e);
        }
    }

    @Override // c.a.a.a.m0.p
    public SSLSession d() {
        if (this.n instanceof SSLSocket) {
            return ((SSLSocket) this.n).getSession();
        }
        return null;
    }

    @Override // c.a.a.a.m0.q
    public final Socket e() {
        return this.n;
    }

    @Override // c.a.a.a.q0.a, c.a.a.a.i
    public s g() {
        s sVarG = super.g();
        if (this.k.a()) {
            this.k.a("Receiving response: " + sVarG.j());
        }
        if (this.l.a()) {
            this.l.a("<< " + sVarG.j().toString());
            for (c.a.a.a.e eVar : sVarG.l()) {
                this.l.a("<< " + eVar.toString());
            }
        }
        return sVarG;
    }
}

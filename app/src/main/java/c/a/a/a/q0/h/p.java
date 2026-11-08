package c.a.a.a.q0.h;

import c.a.a.a.b0;
import c.a.a.a.z;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.Cocos2dxEditBox;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class p implements c.a.a.a.j0.p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f242a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final c.a.a.a.m0.b f243b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final c.a.a.a.m0.u.d f244c;
    protected final c.a.a.a.b d;
    protected final c.a.a.a.m0.g e;
    protected final c.a.a.a.v0.h f;
    protected final c.a.a.a.v0.g g;
    protected final c.a.a.a.j0.j h;
    protected final c.a.a.a.j0.o i;
    protected final c.a.a.a.j0.c j;
    protected final c.a.a.a.j0.c k;
    protected final c.a.a.a.j0.q l;
    protected final c.a.a.a.t0.e m;
    protected c.a.a.a.m0.o n;
    protected final c.a.a.a.i0.h o;
    protected final c.a.a.a.i0.h p;
    private final s q;
    private int r;
    private int s;
    private final int t;
    private c.a.a.a.n u;

    public p(c.a.a.a.p0.b bVar, c.a.a.a.v0.h hVar, c.a.a.a.m0.b bVar2, c.a.a.a.b bVar3, c.a.a.a.m0.g gVar, c.a.a.a.m0.u.d dVar, c.a.a.a.v0.g gVar2, c.a.a.a.j0.j jVar, c.a.a.a.j0.o oVar, c.a.a.a.j0.c cVar, c.a.a.a.j0.c cVar2, c.a.a.a.j0.q qVar, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(bVar, "Log");
        c.a.a.a.x0.a.a(hVar, "Request executor");
        c.a.a.a.x0.a.a(bVar2, "Client connection manager");
        c.a.a.a.x0.a.a(bVar3, "Connection reuse strategy");
        c.a.a.a.x0.a.a(gVar, "Connection keep alive strategy");
        c.a.a.a.x0.a.a(dVar, "Route planner");
        c.a.a.a.x0.a.a(gVar2, "HTTP protocol processor");
        c.a.a.a.x0.a.a(jVar, "HTTP request retry handler");
        c.a.a.a.x0.a.a(oVar, "Redirect strategy");
        c.a.a.a.x0.a.a(cVar, "Target authentication strategy");
        c.a.a.a.x0.a.a(cVar2, "Proxy authentication strategy");
        c.a.a.a.x0.a.a(qVar, "User token handler");
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        this.f242a = bVar;
        this.q = new s(bVar);
        this.f = hVar;
        this.f243b = bVar2;
        this.d = bVar3;
        this.e = gVar;
        this.f244c = dVar;
        this.g = gVar2;
        this.h = jVar;
        this.i = oVar;
        this.j = cVar;
        this.k = cVar2;
        this.l = qVar;
        this.m = eVar;
        if (oVar instanceof o) {
            ((o) oVar).a();
        }
        if (cVar instanceof b) {
            ((b) cVar).a();
        }
        if (cVar2 instanceof b) {
            ((b) cVar2).a();
        }
        this.n = null;
        this.r = 0;
        this.s = 0;
        this.o = new c.a.a.a.i0.h();
        this.p = new c.a.a.a.i0.h();
        this.t = this.m.b("http.protocol.max-redirects", 100);
    }

    private v a(c.a.a.a.q qVar) {
        return qVar instanceof c.a.a.a.l ? new r((c.a.a.a.l) qVar) : new v(qVar);
    }

    private void a(w wVar, c.a.a.a.v0.e eVar) throws IOException, c.a.a.a.m {
        c.a.a.a.m0.u.b bVarB = wVar.b();
        v vVarA = wVar.a();
        int i = 0;
        while (true) {
            eVar.a("http.request", vVarA);
            i++;
            try {
                if (this.n.isOpen()) {
                    this.n.a(c.a.a.a.t0.c.d(this.m));
                } else {
                    this.n.a(bVarB, eVar, this.m);
                }
                c(bVarB, eVar);
                return;
            } catch (IOException e) {
                try {
                    this.n.close();
                } catch (IOException unused) {
                }
                if (!this.h.a(e, i, eVar)) {
                    throw e;
                }
                if (this.f242a.c()) {
                    this.f242a.c("I/O exception (" + e.getClass().getName() + ") caught when connecting to " + bVarB + ": " + e.getMessage());
                    if (this.f242a.a()) {
                        this.f242a.a(e.getMessage(), e);
                    }
                    this.f242a.c("Retrying connect to " + bVarB);
                }
            }
        }
    }

    private c.a.a.a.s b(w wVar, c.a.a.a.v0.e eVar) throws IOException, c.a.a.a.j0.l {
        v vVarA = wVar.a();
        c.a.a.a.m0.u.b bVarB = wVar.b();
        IOException e = null;
        while (true) {
            this.r++;
            vVarA.p();
            if (!vVarA.q()) {
                this.f242a.a("Cannot retry non-repeatable request");
                if (e != null) {
                    throw new c.a.a.a.j0.l("Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.", e);
                }
                throw new c.a.a.a.j0.l("Cannot retry request with a non-repeatable request entity.");
            }
            try {
                if (!this.n.isOpen()) {
                    if (bVarB.f()) {
                        this.f242a.a("Proxied connection. Need to start over.");
                        return null;
                    }
                    this.f242a.a("Reopening the direct connection.");
                    this.n.a(bVarB, eVar, this.m);
                }
                if (this.f242a.a()) {
                    this.f242a.a("Attempt " + this.r + " to execute request");
                }
                return this.f.c(vVarA, this.n, eVar);
            } catch (IOException e2) {
                e = e2;
                this.f242a.a("Closing the connection.");
                try {
                    this.n.close();
                } catch (IOException unused) {
                }
                if (!this.h.a(e, vVarA.n(), eVar)) {
                    if (!(e instanceof z)) {
                        throw e;
                    }
                    z zVar = new z(bVarB.b().e() + " failed to respond");
                    zVar.setStackTrace(e.getStackTrace());
                    throw zVar;
                }
                if (this.f242a.c()) {
                    this.f242a.c("I/O exception (" + e.getClass().getName() + ") caught when processing request to " + bVarB + ": " + e.getMessage());
                }
                if (this.f242a.a()) {
                    this.f242a.a(e.getMessage(), e);
                }
                if (this.f242a.c()) {
                    this.f242a.c("Retrying request to " + bVarB);
                }
            }
        }
    }

    private void b() {
        c.a.a.a.m0.o oVar = this.n;
        if (oVar != null) {
            this.n = null;
            try {
                oVar.f();
            } catch (IOException e) {
                if (this.f242a.a()) {
                    this.f242a.a(e.getMessage(), e);
                }
            }
            try {
                oVar.l();
            } catch (IOException e2) {
                this.f242a.a("Error releasing connection", e2);
            }
        }
    }

    protected w a(w wVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) throws b0 {
        c.a.a.a.n nVar;
        c.a.a.a.m0.u.b bVarB = wVar.b();
        v vVarA = wVar.a();
        c.a.a.a.t0.e eVarK = vVarA.k();
        if (c.a.a.a.j0.u.b.b(eVarK)) {
            c.a.a.a.n nVarB = (c.a.a.a.n) eVar.a("http.target_host");
            if (nVarB == null) {
                nVarB = bVarB.b();
            }
            if (nVarB.c() < 0) {
                nVar = new c.a.a.a.n(nVarB.b(), this.f243b.a().a(nVarB).a(), nVarB.d());
            } else {
                nVar = nVarB;
            }
            boolean zB = this.q.b(nVar, sVar, this.j, this.o, eVar);
            c.a.a.a.n nVarG = bVarB.g();
            if (nVarG == null) {
                nVarG = bVarB.b();
            }
            c.a.a.a.n nVar2 = nVarG;
            boolean zB2 = this.q.b(nVar2, sVar, this.k, this.p, eVar);
            if (zB) {
                if (this.q.c(nVar, sVar, this.j, this.o, eVar)) {
                    return wVar;
                }
            }
            if (zB2 && this.q.c(nVar2, sVar, this.k, this.p, eVar)) {
                return wVar;
            }
        }
        if (!c.a.a.a.j0.u.b.c(eVarK) || !this.i.b(vVarA, sVar, eVar)) {
            return null;
        }
        int i = this.s;
        if (i >= this.t) {
            throw new c.a.a.a.j0.m("Maximum redirects (" + this.t + ") exceeded");
        }
        this.s = i + 1;
        this.u = null;
        c.a.a.a.j0.t.i iVarA = this.i.a(vVarA, sVar, eVar);
        iVarA.a(vVarA.o().l());
        URI uriH = iVarA.h();
        c.a.a.a.n nVarA = c.a.a.a.j0.w.d.a(uriH);
        if (nVarA == null) {
            throw new b0("Redirect URI does not specify a valid host name: " + uriH);
        }
        if (!bVarB.b().equals(nVarA)) {
            this.f242a.a("Resetting target auth state");
            this.o.e();
            c.a.a.a.i0.c cVarB = this.p.b();
            if (cVarB != null && cVarB.d()) {
                this.f242a.a("Resetting proxy auth state");
                this.p.e();
            }
        }
        v vVarA2 = a(iVarA);
        vVarA2.a(eVarK);
        c.a.a.a.m0.u.b bVarB2 = b(nVarA, vVarA2, eVar);
        w wVar2 = new w(vVarA2, bVarB2);
        if (this.f242a.a()) {
            this.f242a.a("Redirecting to '" + uriH + "' via " + bVarB2);
        }
        return wVar2;
    }

    protected c.a.a.a.q a(c.a.a.a.m0.u.b bVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.n nVarB = bVar.b();
        String strB = nVarB.b();
        int iC = nVarB.c();
        if (iC < 0) {
            iC = this.f243b.a().b(nVarB.d()).a();
        }
        StringBuilder sb = new StringBuilder(strB.length() + 6);
        sb.append(strB);
        sb.append(':');
        sb.append(Integer.toString(iC));
        return new c.a.a.a.s0.h("CONNECT", sb.toString(), c.a.a.a.t0.f.b(this.m));
    }

    @Override // c.a.a.a.j0.p
    public c.a.a.a.s a(c.a.a.a.n nVar, c.a.a.a.q qVar, c.a.a.a.v0.e eVar) throws IOException, c.a.a.a.m {
        String str;
        eVar.a("http.auth.target-scope", this.o);
        eVar.a("http.auth.proxy-scope", this.p);
        v vVarA = a(qVar);
        vVarA.a(this.m);
        c.a.a.a.m0.u.b bVarB = b(nVar, vVarA, eVar);
        c.a.a.a.n nVar2 = (c.a.a.a.n) vVarA.k().b("http.virtual-host");
        this.u = nVar2;
        if (nVar2 != null && nVar2.c() == -1) {
            int iC = (nVar != null ? nVar : bVarB.b()).c();
            if (iC != -1) {
                this.u = new c.a.a.a.n(this.u.b(), iC, this.u.d());
            }
        }
        w wVar = new w(vVarA, bVarB);
        c.a.a.a.s sVarA = null;
        boolean z = false;
        w wVar2 = wVar;
        boolean zA = false;
        while (!z) {
            try {
                v vVarA2 = wVar2.a();
                c.a.a.a.m0.u.b bVarB2 = wVar2.b();
                Object objA = eVar.a("http.user-token");
                if (this.n == null) {
                    c.a.a.a.m0.e eVarA = this.f243b.a(bVarB2, objA);
                    if (qVar instanceof c.a.a.a.j0.t.a) {
                        ((c.a.a.a.j0.t.a) qVar).a(eVarA);
                    }
                    try {
                        this.n = eVarA.a(c.a.a.a.j0.u.b.a(this.m), TimeUnit.MILLISECONDS);
                        if (c.a.a.a.t0.c.f(this.m) && this.n.isOpen()) {
                            this.f242a.a("Stale connection check");
                            if (this.n.k()) {
                                this.f242a.a("Stale connection detected");
                                this.n.close();
                            }
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
                if (qVar instanceof c.a.a.a.j0.t.a) {
                    ((c.a.a.a.j0.t.a) qVar).a(this.n);
                }
                try {
                    a(wVar2, eVar);
                    String userInfo = vVarA2.h().getUserInfo();
                    if (userInfo != null) {
                        this.o.a(new c.a.a.a.q0.g.b(), new c.a.a.a.i0.r(userInfo));
                    }
                    if (this.u != null) {
                        nVar = this.u;
                    } else {
                        URI uriH = vVarA2.h();
                        if (uriH.isAbsolute()) {
                            nVar = c.a.a.a.j0.w.d.a(uriH);
                        }
                    }
                    if (nVar == null) {
                        nVar = bVarB2.b();
                    }
                    vVarA2.r();
                    a(vVarA2, bVarB2);
                    eVar.a("http.target_host", nVar);
                    eVar.a("http.route", bVarB2);
                    eVar.a("http.connection", this.n);
                    this.f.a(vVarA2, this.g, eVar);
                    sVarA = b(wVar2, eVar);
                    if (sVarA != null) {
                        sVarA.a(this.m);
                        this.f.a(sVarA, this.g, eVar);
                        zA = this.d.a(sVarA, eVar);
                        if (zA) {
                            long jA = this.e.a(sVarA, eVar);
                            if (this.f242a.a()) {
                                if (jA > 0) {
                                    str = "for " + jA + " " + TimeUnit.MILLISECONDS;
                                } else {
                                    str = "indefinitely";
                                }
                                this.f242a.a("Connection can be kept alive " + str);
                            }
                            this.n.a(jA, TimeUnit.MILLISECONDS);
                        }
                        w wVarA = a(wVar2, sVarA, eVar);
                        if (wVarA == null) {
                            z = true;
                        } else {
                            if (zA) {
                                c.a.a.a.x0.g.a(sVarA.c());
                                this.n.i();
                            } else {
                                this.n.close();
                                if (this.p.d().compareTo(c.a.a.a.i0.b.CHALLENGED) > 0 && this.p.b() != null && this.p.b().d()) {
                                    this.f242a.a("Resetting proxy auth state");
                                    this.p.e();
                                }
                                if (this.o.d().compareTo(c.a.a.a.i0.b.CHALLENGED) > 0 && this.o.b() != null && this.o.b().d()) {
                                    this.f242a.a("Resetting target auth state");
                                    this.o.e();
                                }
                            }
                            if (!wVarA.b().equals(wVar2.b())) {
                                a();
                            }
                            wVar2 = wVarA;
                        }
                        if (this.n != null) {
                            if (objA == null) {
                                objA = this.l.a(eVar);
                                eVar.a("http.user-token", objA);
                            }
                            if (objA != null) {
                                this.n.a(objA);
                            }
                        }
                    }
                } catch (y e) {
                    if (this.f242a.a()) {
                        this.f242a.a(e.getMessage());
                    }
                    sVarA = e.a();
                }
            } catch (c.a.a.a.m e2) {
                b();
                throw e2;
            } catch (c.a.a.a.q0.i.e e3) {
                InterruptedIOException interruptedIOException = new InterruptedIOException("Connection has been shut down");
                interruptedIOException.initCause(e3);
                throw interruptedIOException;
            } catch (IOException e4) {
                b();
                throw e4;
            } catch (RuntimeException e5) {
                b();
                throw e5;
            }
        }
        if (sVarA == null || sVarA.c() == null || !sVarA.c().a()) {
            if (zA) {
                this.n.i();
            }
            a();
        } else {
            sVarA.a(new c.a.a.a.m0.a(sVarA.c(), this.n, zA));
        }
        return sVarA;
    }

    protected void a() {
        try {
            this.n.l();
        } catch (IOException e) {
            this.f242a.a("IOException releasing connection", e);
        }
        this.n = null;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0020 A[Catch: URISyntaxException -> 0x0034, TryCatch #0 {URISyntaxException -> 0x0034, blocks: (B:2:0x0000, B:4:0x000b, B:6:0x0011, B:8:0x0017, B:13:0x0030, B:9:0x0020, B:10:0x0025, B:12:0x002b), top: B:18:0x0000 }] */
    protected void a(v vVar, c.a.a.a.m0.u.b bVar) throws b0 {
        URI uriA;
        try {
            URI uriH = vVar.h();
            if (bVar.g() == null || bVar.f()) {
                if (uriH.isAbsolute()) {
                    uriA = c.a.a.a.j0.w.d.a(uriH, null, true);
                } else {
                    uriA = c.a.a.a.j0.w.d.c(uriH);
                }
            } else if (uriH.isAbsolute()) {
                uriA = c.a.a.a.j0.w.d.c(uriH);
            } else {
                uriA = c.a.a.a.j0.w.d.a(uriH, bVar.b(), true);
            }
            vVar.a(uriA);
        } catch (URISyntaxException e) {
            throw new b0("Invalid URI: " + vVar.g().c(), e);
        }
    }

    protected boolean a(c.a.a.a.m0.u.b bVar, int i, c.a.a.a.v0.e eVar) throws c.a.a.a.m {
        throw new c.a.a.a.m("Proxy chains are not supported.");
    }

    protected c.a.a.a.m0.u.b b(c.a.a.a.n nVar, c.a.a.a.q qVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.m0.u.d dVar = this.f244c;
        if (nVar == null) {
            nVar = (c.a.a.a.n) qVar.k().b("http.default-host");
        }
        return dVar.a(nVar, qVar, eVar);
    }

    protected boolean b(c.a.a.a.m0.u.b bVar, c.a.a.a.v0.e eVar) throws IOException, c.a.a.a.m {
        c.a.a.a.n nVarG = bVar.g();
        c.a.a.a.n nVarB = bVar.b();
        while (true) {
            if (!this.n.isOpen()) {
                this.n.a(bVar, eVar, this.m);
            }
            c.a.a.a.q qVarA = a(bVar, eVar);
            qVarA.a(this.m);
            eVar.a("http.target_host", nVarB);
            eVar.a("http.route", bVar);
            eVar.a("http.proxy_host", nVarG);
            eVar.a("http.connection", this.n);
            eVar.a("http.request", qVarA);
            this.f.a(qVarA, this.g, eVar);
            c.a.a.a.s sVarC = this.f.c(qVarA, this.n, eVar);
            sVarC.a(this.m);
            this.f.a(sVarC, this.g, eVar);
            if (sVarC.j().c() < 200) {
                throw new c.a.a.a.m("Unexpected response to CONNECT request: " + sVarC.j());
            }
            if (c.a.a.a.j0.u.b.b(this.m)) {
                if (!this.q.b(nVarG, sVarC, this.k, this.p, eVar) || !this.q.c(nVarG, sVarC, this.k, this.p, eVar)) {
                    if (sVarC.j().c() <= 299) {
                        this.n.i();
                        return false;
                    }
                    c.a.a.a.k kVarC = sVarC.c();
                    if (kVarC != null) {
                        sVarC.a(new c.a.a.a.o0.c(kVarC));
                    }
                    this.n.close();
                    throw new y("CONNECT refused by proxy: " + sVarC.j(), sVarC);
                }
                if (this.d.a(sVarC, eVar)) {
                    this.f242a.a("Connection kept alive");
                    c.a.a.a.x0.g.a(sVarC.c());
                } else {
                    this.n.close();
                }
            }
        }
    }

    protected void c(c.a.a.a.m0.u.b bVar, c.a.a.a.v0.e eVar) throws IOException, c.a.a.a.m {
        int iA;
        c.a.a.a.m0.u.a aVar = new c.a.a.a.m0.u.a();
        do {
            c.a.a.a.m0.u.b bVarB = this.n.b();
            iA = aVar.a(bVar, bVarB);
            switch (iA) {
                case -1:
                    throw new c.a.a.a.m("Unable to establish route: planned = " + bVar + "; current = " + bVarB);
                case Cocos2dxEditBox.kEndActionUnknown /* 0 */:
                    break;
                case 1:
                case BuildConfig.VERSION_CODE /* 2 */:
                    this.n.a(bVar, eVar, this.m);
                    break;
                case Cocos2dxEditBox.kEndActionReturn /* 3 */:
                    boolean zB = b(bVar, eVar);
                    this.f242a.a("Tunnel to target created.");
                    this.n.a(zB, this.m);
                    break;
                case 4:
                    a(bVar, bVarB.c() - 1, eVar);
                    throw null;
                case 5:
                    this.n.a(eVar, this.m);
                    break;
                default:
                    throw new IllegalStateException("Unknown step indicator " + iA + " from RouteDirector.");
            }
        } while (iA > 0);
    }
}

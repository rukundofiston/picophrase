package c.a.a.a.q0.h;

import c.a.a.a.q0.j.g0;
import c.a.a.a.q0.j.z;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class a extends h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f218a = new c.a.a.a.p0.b(getClass());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c.a.a.a.t0.e f219b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c.a.a.a.v0.h f220c;
    private c.a.a.a.m0.b d;
    private c.a.a.a.b e;
    private c.a.a.a.m0.g f;
    private c.a.a.a.n0.l g;
    private c.a.a.a.i0.f h;
    private c.a.a.a.v0.b i;
    private c.a.a.a.v0.i j;
    private c.a.a.a.j0.j k;
    private c.a.a.a.j0.o l;
    private c.a.a.a.j0.c m;
    private c.a.a.a.j0.c n;
    private c.a.a.a.j0.h o;
    private c.a.a.a.j0.i p;
    private c.a.a.a.m0.u.d q;
    private c.a.a.a.j0.q r;
    private c.a.a.a.j0.g s;
    private c.a.a.a.j0.d t;

    protected a(c.a.a.a.m0.b bVar, c.a.a.a.t0.e eVar) {
        this.f219b = eVar;
        this.d = bVar;
    }

    private synchronized c.a.a.a.v0.g S() {
        if (this.j == null) {
            c.a.a.a.v0.b bVarJ = J();
            int iA = bVarJ.a();
            c.a.a.a.r[] rVarArr = new c.a.a.a.r[iA];
            for (int i = 0; i < iA; i++) {
                rVarArr[i] = bVarJ.a(i);
            }
            int iB = bVarJ.b();
            c.a.a.a.u[] uVarArr = new c.a.a.a.u[iB];
            for (int i2 = 0; i2 < iB; i2++) {
                uVarArr[i2] = bVarJ.b(i2);
            }
            this.j = new c.a.a.a.v0.i(rVarArr, uVarArr);
        }
        return this.j;
    }

    public final synchronized c.a.a.a.i0.f A() {
        if (this.h == null) {
            this.h = f();
        }
        return this.h;
    }

    public final synchronized c.a.a.a.j0.d B() {
        return this.t;
    }

    public final synchronized c.a.a.a.j0.g C() {
        return this.s;
    }

    public final synchronized c.a.a.a.m0.g D() {
        if (this.f == null) {
            this.f = m();
        }
        return this.f;
    }

    public final synchronized c.a.a.a.m0.b E() {
        if (this.d == null) {
            this.d = l();
        }
        return this.d;
    }

    public final synchronized c.a.a.a.b F() {
        if (this.e == null) {
            this.e = n();
        }
        return this.e;
    }

    public final synchronized c.a.a.a.n0.l G() {
        if (this.g == null) {
            this.g = o();
        }
        return this.g;
    }

    public final synchronized c.a.a.a.j0.h H() {
        if (this.o == null) {
            this.o = p();
        }
        return this.o;
    }

    public final synchronized c.a.a.a.j0.i I() {
        if (this.p == null) {
            this.p = q();
        }
        return this.p;
    }

    protected final synchronized c.a.a.a.v0.b J() {
        if (this.i == null) {
            this.i = t();
        }
        return this.i;
    }

    public final synchronized c.a.a.a.j0.j K() {
        if (this.k == null) {
            this.k = u();
        }
        return this.k;
    }

    public final synchronized c.a.a.a.t0.e L() {
        if (this.f219b == null) {
            this.f219b = s();
        }
        return this.f219b;
    }

    public final synchronized c.a.a.a.j0.c M() {
        if (this.n == null) {
            this.n = w();
        }
        return this.n;
    }

    public final synchronized c.a.a.a.j0.o N() {
        if (this.l == null) {
            this.l = new n();
        }
        return this.l;
    }

    public final synchronized c.a.a.a.v0.h O() {
        if (this.f220c == null) {
            this.f220c = x();
        }
        return this.f220c;
    }

    public final synchronized c.a.a.a.m0.u.d P() {
        if (this.q == null) {
            this.q = v();
        }
        return this.q;
    }

    public final synchronized c.a.a.a.j0.c Q() {
        if (this.m == null) {
            this.m = y();
        }
        return this.m;
    }

    public final synchronized c.a.a.a.j0.q R() {
        if (this.r == null) {
            this.r = z();
        }
        return this.r;
    }

    protected c.a.a.a.j0.p a(c.a.a.a.v0.h hVar, c.a.a.a.m0.b bVar, c.a.a.a.b bVar2, c.a.a.a.m0.g gVar, c.a.a.a.m0.u.d dVar, c.a.a.a.v0.g gVar2, c.a.a.a.j0.j jVar, c.a.a.a.j0.o oVar, c.a.a.a.j0.c cVar, c.a.a.a.j0.c cVar2, c.a.a.a.j0.q qVar, c.a.a.a.t0.e eVar) {
        return new p(this.f218a, hVar, bVar, bVar2, gVar, dVar, gVar2, jVar, oVar, cVar, cVar2, qVar, eVar);
    }

    @Override // c.a.a.a.q0.h.h
    protected final c.a.a.a.j0.t.c a(c.a.a.a.n nVar, c.a.a.a.q qVar, c.a.a.a.v0.e eVar) throws IOException {
        c.a.a.a.v0.e eVar2;
        c.a.a.a.j0.p pVarA;
        c.a.a.a.m0.u.d dVarP;
        c.a.a.a.j0.g gVarC;
        c.a.a.a.j0.d dVarB;
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        synchronized (this) {
            c.a.a.a.v0.e eVarR = r();
            c.a.a.a.v0.e cVar = eVar == null ? eVarR : new c.a.a.a.v0.c(eVar, eVarR);
            c.a.a.a.t0.e eVarB = b(qVar);
            cVar.a("http.request-config", c.a.a.a.j0.u.a.a(eVarB));
            eVar2 = cVar;
            pVarA = a(O(), E(), F(), D(), P(), S(), K(), N(), Q(), M(), R(), eVarB);
            dVarP = P();
            gVarC = C();
            dVarB = B();
        }
        try {
            if (gVarC == null || dVarB == null) {
                return i.a(pVarA.a(nVar, qVar, eVar2));
            }
            c.a.a.a.m0.u.b bVarA = dVarP.a(nVar != null ? nVar : (c.a.a.a.n) b(qVar).b("http.default-host"), qVar, eVar2);
            try {
                c.a.a.a.j0.t.c cVarA = i.a(pVarA.a(nVar, qVar, eVar2));
                if (gVarC.a(cVarA)) {
                    dVarB.a(bVarA);
                } else {
                    dVarB.b(bVarA);
                }
                return cVarA;
            } catch (RuntimeException e) {
                if (gVarC.a(e)) {
                    dVarB.a(bVarA);
                }
                throw e;
            } catch (Exception e2) {
                if (gVarC.a(e2)) {
                    dVarB.a(bVarA);
                }
                if (e2 instanceof c.a.a.a.m) {
                    throw ((c.a.a.a.m) e2);
                }
                if (e2 instanceof IOException) {
                    throw ((IOException) e2);
                }
                throw new UndeclaredThrowableException(e2);
            }
        } catch (c.a.a.a.m e3) {
            throw new c.a.a.a.j0.f(e3);
        }
    }

    public synchronized void a(c.a.a.a.j0.j jVar) {
        this.k = jVar;
    }

    @Deprecated
    public synchronized void a(c.a.a.a.j0.n nVar) {
        this.l = new o(nVar);
    }

    public synchronized void a(c.a.a.a.r rVar) {
        J().a(rVar);
        this.j = null;
    }

    public synchronized void a(c.a.a.a.r rVar, int i) {
        J().a(rVar, i);
        this.j = null;
    }

    public synchronized void a(c.a.a.a.u uVar) {
        J().a(uVar);
        this.j = null;
    }

    protected c.a.a.a.t0.e b(c.a.a.a.q qVar) {
        return new g(null, L(), qVar.k(), null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        E().c();
    }

    protected c.a.a.a.i0.f f() {
        c.a.a.a.i0.f fVar = new c.a.a.a.i0.f();
        fVar.a("Basic", new c.a.a.a.q0.g.c());
        fVar.a("Digest", new c.a.a.a.q0.g.e());
        fVar.a("NTLM", new c.a.a.a.q0.g.k());
        return fVar;
    }

    protected c.a.a.a.m0.b l() {
        c.a.a.a.m0.c cVar;
        c.a.a.a.m0.v.i iVarA = c.a.a.a.q0.i.p.a();
        c.a.a.a.t0.e eVarL = L();
        String str = (String) eVarL.b("http.connection-manager.factory-class-name");
        if (str != null) {
            try {
                cVar = (c.a.a.a.m0.c) Class.forName(str).newInstance();
            } catch (ClassNotFoundException unused) {
                throw new IllegalStateException("Invalid class name: " + str);
            } catch (IllegalAccessException e) {
                throw new IllegalAccessError(e.getMessage());
            } catch (InstantiationException e2) {
                throw new InstantiationError(e2.getMessage());
            }
        } else {
            cVar = null;
        }
        return cVar != null ? cVar.a(eVarL, iVarA) : new c.a.a.a.q0.i.d(iVarA);
    }

    protected c.a.a.a.m0.g m() {
        return new j();
    }

    protected c.a.a.a.b n() {
        return new c.a.a.a.q0.b();
    }

    protected c.a.a.a.n0.l o() {
        c.a.a.a.n0.l lVar = new c.a.a.a.n0.l();
        lVar.a("default", new c.a.a.a.q0.j.l());
        lVar.a("best-match", new c.a.a.a.q0.j.l());
        lVar.a("compatibility", new c.a.a.a.q0.j.n());
        lVar.a("netscape", new c.a.a.a.q0.j.w());
        lVar.a("rfc2109", new z());
        lVar.a("rfc2965", new g0());
        lVar.a("ignoreCookies", new c.a.a.a.q0.j.s());
        return lVar;
    }

    protected c.a.a.a.j0.h p() {
        return new e();
    }

    protected c.a.a.a.j0.i q() {
        return new f();
    }

    protected c.a.a.a.v0.e r() {
        c.a.a.a.v0.a aVar = new c.a.a.a.v0.a();
        aVar.a("http.scheme-registry", E().a());
        aVar.a("http.authscheme-registry", A());
        aVar.a("http.cookiespec-registry", G());
        aVar.a("http.cookie-store", H());
        aVar.a("http.auth.credentials-provider", I());
        return aVar;
    }

    protected abstract c.a.a.a.t0.e s();

    protected abstract c.a.a.a.v0.b t();

    protected c.a.a.a.j0.j u() {
        return new l();
    }

    protected c.a.a.a.m0.u.d v() {
        return new c.a.a.a.q0.i.i(E().a());
    }

    protected c.a.a.a.j0.c w() {
        return new t();
    }

    protected c.a.a.a.v0.h x() {
        return new c.a.a.a.v0.h();
    }

    protected c.a.a.a.j0.c y() {
        return new x();
    }

    protected c.a.a.a.j0.q z() {
        return new q();
    }
}

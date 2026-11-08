package b.c.a.a;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.q0.h.a f38a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.v0.e f39b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.a.a.a.j0.t.i f40c;
    private final n d;
    private final AtomicBoolean e = new AtomicBoolean();
    private int f;
    private boolean g;
    private volatile boolean h;
    private boolean i;

    public b(c.a.a.a.q0.h.a aVar, c.a.a.a.v0.e eVar, c.a.a.a.j0.t.i iVar, n nVar) {
        p.a(aVar, "client");
        this.f38a = aVar;
        p.a(eVar, "context");
        this.f39b = eVar;
        p.a(iVar, "request");
        this.f40c = iVar;
        p.a(nVar, "responseHandler");
        this.d = nVar;
    }

    private void c() throws MalformedURLException {
        if (a()) {
            return;
        }
        if (this.f40c.h().getScheme() == null) {
            throw new MalformedURLException("No valid URI scheme was provided");
        }
        n nVar = this.d;
        if (nVar instanceof k) {
            ((k) nVar).a(this.f40c);
        }
        c.a.a.a.j0.t.c cVarA = this.f38a.a(this.f40c, this.f39b);
        if (a()) {
            return;
        }
        n nVar2 = this.d;
        nVar2.a(nVar2, cVarA);
        if (a()) {
            return;
        }
        this.d.a(cVarA);
        if (a()) {
            return;
        }
        n nVar3 = this.d;
        nVar3.b(nVar3, cVarA);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x007b  */
    /* JADX WARN: Code duplicated, block: B:34:0x0081 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0009 A[SYNTHETIC] */
    private void d() throws IOException {
        IOException iOException;
        boolean zA;
        c.a.a.a.j0.j jVarK = this.f38a.K();
        IOException e = null;
        boolean zA2 = true;
        while (zA2) {
            try {
                try {
                    try {
                        c();
                        return;
                    } catch (UnknownHostException e2) {
                        iOException = new IOException("UnknownHostException exception: " + e2.getMessage());
                        if (this.f > 0) {
                            int i = this.f + 1;
                            this.f = i;
                            if (jVarK.a(e2, i, this.f39b)) {
                                zA = true;
                            } else {
                                zA = false;
                            }
                        } else {
                            zA = false;
                        }
                        IOException iOException2 = iOException;
                        zA2 = zA;
                        e = iOException2;
                        if (zA2) {
                            this.d.a(this.f);
                        }
                    }
                } catch (NullPointerException e3) {
                    iOException = new IOException("NPE in HttpClient: " + e3.getMessage());
                    int i2 = this.f + 1;
                    this.f = i2;
                    zA = jVarK.a(iOException, i2, this.f39b);
                    IOException iOException3 = iOException;
                    zA2 = zA;
                    e = iOException3;
                    if (zA2) {
                        this.d.a(this.f);
                    }
                }
            } catch (IOException e4) {
                e = e4;
                try {
                    if (a()) {
                        return;
                    }
                    int i3 = this.f + 1;
                    this.f = i3;
                    zA2 = jVarK.a(e, i3, this.f39b);
                    if (zA2) {
                        this.d.a(this.f);
                    }
                } catch (Exception e5) {
                    a.j.b("AsyncHttpRequest", "Unhandled exception origin cause", e5);
                    throw new IOException("Unhandled exception: " + e5.getMessage());
                }
            }
        }
    }

    private synchronized void e() {
        if (!this.h && this.e.get() && !this.g) {
            this.g = true;
            this.d.d();
        }
    }

    public void a(b bVar) {
    }

    public boolean a() {
        boolean z = this.e.get();
        if (z) {
            e();
        }
        return z;
    }

    public boolean a(boolean z) {
        this.e.set(true);
        this.f40c.e();
        return a();
    }

    public void b(b bVar) {
    }

    public boolean b() {
        return a() || this.h;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (a()) {
            return;
        }
        if (!this.i) {
            this.i = true;
            b(this);
        }
        if (a()) {
            return;
        }
        this.d.c();
        if (a()) {
            return;
        }
        try {
            d();
        } catch (IOException e) {
            if (a()) {
                a.j.b("AsyncHttpRequest", "makeRequestWithRetries returned error", e);
            } else {
                this.d.a(0, null, null, e);
            }
        }
        if (a()) {
            return;
        }
        this.d.a();
        if (a()) {
            return;
        }
        a(this);
        this.h = true;
    }
}

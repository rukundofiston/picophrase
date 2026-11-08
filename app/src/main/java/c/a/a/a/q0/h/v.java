package c.a.a.a.q0.h;

import c.a.a.a.b0;
import c.a.a.a.c0;
import c.a.a.a.e0;
import java.net.URI;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class v extends c.a.a.a.s0.a implements c.a.a.a.j0.t.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.a.a.a.q f248c;
    private URI d;
    private String e;
    private c0 f;
    private int g;

    public v(c.a.a.a.q qVar) throws b0 {
        c0 c0VarA;
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        this.f248c = qVar;
        a(qVar.k());
        a(qVar.l());
        if (qVar instanceof c.a.a.a.j0.t.i) {
            c.a.a.a.j0.t.i iVar = (c.a.a.a.j0.t.i) qVar;
            this.d = iVar.h();
            this.e = iVar.b();
            c0VarA = null;
        } else {
            e0 e0VarG = qVar.g();
            try {
                this.d = new URI(e0VarG.c());
                this.e = e0VarG.b();
                c0VarA = qVar.a();
            } catch (URISyntaxException e) {
                throw new b0("Invalid request URI: " + e0VarG.c(), e);
            }
        }
        this.f = c0VarA;
        this.g = 0;
    }

    @Override // c.a.a.a.p
    public c0 a() {
        if (this.f == null) {
            this.f = c.a.a.a.t0.f.b(k());
        }
        return this.f;
    }

    public void a(URI uri) {
        this.d = uri;
    }

    @Override // c.a.a.a.j0.t.i
    public String b() {
        return this.e;
    }

    @Override // c.a.a.a.j0.t.i
    public void e() {
        throw new UnsupportedOperationException();
    }

    @Override // c.a.a.a.q
    public e0 g() {
        c0 c0VarA = a();
        URI uri = this.d;
        String aSCIIString = uri != null ? uri.toASCIIString() : null;
        if (aSCIIString == null || aSCIIString.isEmpty()) {
            aSCIIString = "/";
        }
        return new c.a.a.a.s0.n(b(), aSCIIString, c0VarA);
    }

    @Override // c.a.a.a.j0.t.i
    public URI h() {
        return this.d;
    }

    @Override // c.a.a.a.j0.t.i
    public boolean m() {
        return false;
    }

    public int n() {
        return this.g;
    }

    public c.a.a.a.q o() {
        return this.f248c;
    }

    public void p() {
        this.g++;
    }

    public boolean q() {
        return true;
    }

    public void r() {
        this.f357a.a();
        a(this.f248c.l());
    }
}

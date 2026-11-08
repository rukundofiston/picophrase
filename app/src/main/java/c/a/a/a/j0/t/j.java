package c.a.a.a.j0.t;

import c.a.a.a.c0;
import c.a.a.a.k;
import c.a.a.a.l;
import c.a.a.a.q;
import c.a.a.a.s0.r;
import c.a.a.a.y;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f92a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Charset f93b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c0 f94c;
    private URI d;
    private r e;
    private k f;
    private List<y> g;
    private c.a.a.a.j0.r.a h;

    static class a extends e {
        private final String i;

        a(String str) {
            this.i = str;
        }

        @Override // c.a.a.a.j0.t.h, c.a.a.a.j0.t.i
        public String b() {
            return this.i;
        }
    }

    static class b extends h {
        private final String h;

        b(String str) {
            this.h = str;
        }

        @Override // c.a.a.a.j0.t.h, c.a.a.a.j0.t.i
        public String b() {
            return this.h;
        }
    }

    j() {
        this(null);
    }

    j(String str) {
        this.f93b = c.a.a.a.c.f54a;
        this.f92a = str;
    }

    public static j a(q qVar) {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        j jVar = new j();
        jVar.b(qVar);
        return jVar;
    }

    private j b(q qVar) {
        if (qVar == null) {
            return this;
        }
        this.f92a = qVar.g().b();
        this.f94c = qVar.g().a();
        if (this.e == null) {
            this.e = new r();
        }
        this.e.a();
        this.e.a(qVar.l());
        this.g = null;
        this.f = null;
        if (qVar instanceof l) {
            k kVarC = ((l) qVar).c();
            c.a.a.a.o0.e eVarA = c.a.a.a.o0.e.a(kVarC);
            if (eVarA == null || !eVarA.b().equals(c.a.a.a.o0.e.d.b())) {
                this.f = kVarC;
            } else {
                try {
                    List<y> listA = c.a.a.a.j0.w.e.a(kVarC);
                    if (!listA.isEmpty()) {
                        this.g = listA;
                    }
                } catch (IOException unused) {
                }
            }
        }
        URI uriH = qVar instanceof i ? ((i) qVar).h() : URI.create(qVar.g().c());
        c.a.a.a.j0.w.c cVar = new c.a.a.a.j0.w.c(uriH);
        if (this.g == null) {
            List<y> listE = cVar.e();
            if (listE.isEmpty()) {
                this.g = null;
            } else {
                this.g = listE;
                cVar.b();
            }
        }
        try {
            this.d = cVar.a();
        } catch (URISyntaxException unused2) {
            this.d = uriH;
        }
        if (qVar instanceof d) {
            this.h = ((d) qVar).i();
        } else {
            this.h = null;
        }
        return this;
    }

    public i a() {
        h bVar;
        URI uriA = this.d;
        if (uriA == null) {
            uriA = URI.create("/");
        }
        k aVar = this.f;
        List<y> list = this.g;
        if (list != null && !list.isEmpty()) {
            if (aVar == null && ("POST".equalsIgnoreCase(this.f92a) || "PUT".equalsIgnoreCase(this.f92a))) {
                aVar = new c.a.a.a.j0.s.a(this.g, c.a.a.a.v0.d.f412a);
            } else {
                try {
                    c.a.a.a.j0.w.c cVar = new c.a.a.a.j0.w.c(uriA);
                    cVar.a(this.f93b);
                    cVar.a(this.g);
                    uriA = cVar.a();
                } catch (URISyntaxException unused) {
                }
            }
        }
        if (aVar == null) {
            bVar = new b(this.f92a);
        } else {
            a aVar2 = new a(this.f92a);
            aVar2.a(aVar);
            bVar = aVar2;
        }
        bVar.a(this.f94c);
        bVar.a(uriA);
        r rVar = this.e;
        if (rVar != null) {
            bVar.a(rVar.b());
        }
        bVar.a(this.h);
        return bVar;
    }

    public j a(URI uri) {
        this.d = uri;
        return this;
    }
}

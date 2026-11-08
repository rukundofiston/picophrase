package c.a.a.a.q0.k;

import c.a.a.a.k;
import c.a.a.a.p;
import c.a.a.a.q0.l.f;
import c.a.a.a.q0.l.h;
import c.a.a.a.q0.l.m;
import c.a.a.a.r0.g;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.o0.d f324a;

    public b(c.a.a.a.o0.d dVar) {
        c.a.a.a.x0.a.a(dVar, "Content length strategy");
        this.f324a = dVar;
    }

    protected OutputStream a(g gVar, p pVar) {
        long jA = this.f324a.a(pVar);
        if (jA == -2) {
            return new f(gVar);
        }
        return jA == -1 ? new m(gVar) : new h(gVar, jA);
    }

    public void a(g gVar, p pVar, k kVar) throws IOException {
        c.a.a.a.x0.a.a(gVar, "Session output buffer");
        c.a.a.a.x0.a.a(pVar, "HTTP message");
        c.a.a.a.x0.a.a(kVar, "HTTP entity");
        OutputStream outputStreamA = a(gVar, pVar);
        kVar.a(outputStreamA);
        outputStreamA.close();
    }
}

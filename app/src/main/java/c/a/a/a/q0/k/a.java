package c.a.a.a.q0.k;

import c.a.a.a.e;
import c.a.a.a.k;
import c.a.a.a.p;
import c.a.a.a.q0.l.g;
import c.a.a.a.q0.l.l;
import c.a.a.a.r0.f;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.o0.d f323a;

    public a(c.a.a.a.o0.d dVar) {
        c.a.a.a.x0.a.a(dVar, "Content length strategy");
        this.f323a = dVar;
    }

    public k a(f fVar, p pVar) {
        c.a.a.a.x0.a.a(fVar, "Session input buffer");
        c.a.a.a.x0.a.a(pVar, "HTTP message");
        return b(fVar, pVar);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0047  */
    /* JADX WARN: Code duplicated, block: B:15:0x0052  */
    protected c.a.a.a.o0.b b(f fVar, p pVar) {
        InputStream lVar;
        e eVarD;
        e eVarD2;
        c.a.a.a.o0.b bVar = new c.a.a.a.o0.b();
        long jA = this.f323a.a(pVar);
        if (jA != -2) {
            bVar.a(false);
            if (jA == -1) {
                bVar.a(-1L);
                lVar = new l(fVar);
            } else {
                bVar.a(jA);
                bVar.a(new g(fVar, jA));
            }
            eVarD = pVar.d("Content-Type");
            if (eVarD != null) {
                bVar.b(eVarD);
            }
            eVarD2 = pVar.d("Content-Encoding");
            if (eVarD2 != null) {
                bVar.a(eVarD2);
            }
            return bVar;
        }
        bVar.a(true);
        bVar.a(-1L);
        lVar = new c.a.a.a.q0.l.e(fVar);
        bVar.a(lVar);
        eVarD = pVar.d("Content-Type");
        if (eVarD != null) {
            bVar.b(eVarD);
        }
        eVarD2 = pVar.d("Content-Encoding");
        if (eVarD2 != null) {
            bVar.a(eVarD2);
        }
        return bVar;
    }
}

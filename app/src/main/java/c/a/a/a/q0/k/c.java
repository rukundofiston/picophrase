package c.a.a.a.q0.k;

import c.a.a.a.a0;
import c.a.a.a.b0;
import c.a.a.a.e;
import c.a.a.a.f;
import c.a.a.a.p;

/* JADX INFO: loaded from: classes.dex */
public class c implements c.a.a.a.o0.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f325a;

    static {
        new c();
    }

    public c() {
        this(-1);
    }

    public c(int i) {
        this.f325a = i;
    }

    @Override // c.a.a.a.o0.d
    public long a(p pVar) throws b0 {
        long j;
        c.a.a.a.x0.a.a(pVar, "HTTP message");
        e eVarD = pVar.d("Transfer-Encoding");
        if (eVarD != null) {
            try {
                f[] fVarArrA = eVarD.a();
                int length = fVarArrA.length;
                return (!"identity".equalsIgnoreCase(eVarD.getValue()) && length > 0 && "chunked".equalsIgnoreCase(fVarArrA[length + (-1)].getName())) ? -2L : -1L;
            } catch (a0 e) {
                throw new b0("Invalid Transfer-Encoding header value: " + eVarD, e);
            }
        }
        if (pVar.d("Content-Length") == null) {
            return this.f325a;
        }
        e[] eVarArrB = pVar.b("Content-Length");
        int length2 = eVarArrB.length - 1;
        while (true) {
            if (length2 < 0) {
                j = -1;
                break;
            }
            try {
                j = Long.parseLong(eVarArrB[length2].getValue());
                break;
            } catch (NumberFormatException unused) {
                length2--;
            }
        }
        if (j >= 0) {
            return j;
        }
        return -1L;
    }
}

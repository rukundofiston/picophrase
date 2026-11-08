package c.a.a.a.q0.k;

import c.a.a.a.b0;
import c.a.a.a.e;
import c.a.a.a.p;
import c.a.a.a.v;

/* JADX INFO: loaded from: classes.dex */
public class d implements c.a.a.a.o0.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f326a;

    static {
        new d();
    }

    public d() {
        this(-1);
    }

    public d(int i) {
        this.f326a = i;
    }

    @Override // c.a.a.a.o0.d
    public long a(p pVar) throws b0 {
        c.a.a.a.x0.a.a(pVar, "HTTP message");
        e eVarD = pVar.d("Transfer-Encoding");
        if (eVarD != null) {
            String value = eVarD.getValue();
            if ("chunked".equalsIgnoreCase(value)) {
                if (!pVar.a().c(v.e)) {
                    return -2L;
                }
                throw new b0("Chunked transfer encoding not allowed for " + pVar.a());
            }
            if ("identity".equalsIgnoreCase(value)) {
                return -1L;
            }
            throw new b0("Unsupported transfer encoding: " + value);
        }
        e eVarD2 = pVar.d("Content-Length");
        if (eVarD2 == null) {
            return this.f326a;
        }
        String value2 = eVarD2.getValue();
        try {
            long j = Long.parseLong(value2);
            if (j >= 0) {
                return j;
            }
            throw new b0("Negative content length: " + value2);
        } catch (NumberFormatException unused) {
            throw new b0("Invalid content length: " + value2);
        }
    }
}

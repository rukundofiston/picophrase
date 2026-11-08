package c.a.a.a.q0.h;

import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class r extends v implements c.a.a.a.l {
    private c.a.a.a.k h;
    private boolean i;

    class a extends c.a.a.a.o0.f {
        a(c.a.a.a.k kVar) {
            super(kVar);
        }

        @Override // c.a.a.a.o0.f, c.a.a.a.k
        public void a(OutputStream outputStream) {
            r.this.i = true;
            super.a(outputStream);
        }

        @Override // c.a.a.a.o0.f, c.a.a.a.k
        public void h() {
            r.this.i = true;
            super.h();
        }

        @Override // c.a.a.a.o0.f, c.a.a.a.k
        public InputStream k() {
            r.this.i = true;
            return super.k();
        }
    }

    public r(c.a.a.a.l lVar) {
        super(lVar);
        a(lVar.c());
    }

    public void a(c.a.a.a.k kVar) {
        this.h = kVar != null ? new a(kVar) : null;
        this.i = false;
    }

    @Override // c.a.a.a.l
    public c.a.a.a.k c() {
        return this.h;
    }

    @Override // c.a.a.a.l
    public boolean f() {
        c.a.a.a.e eVarD = d("Expect");
        return eVarD != null && "100-continue".equalsIgnoreCase(eVarD.getValue());
    }

    @Override // c.a.a.a.q0.h.v
    public boolean q() {
        c.a.a.a.k kVar = this.h;
        return kVar == null || kVar.e() || !this.i;
    }
}

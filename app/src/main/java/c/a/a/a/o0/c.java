package c.a.a.a.o0;

import c.a.a.a.k;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class c extends f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final byte[] f177b;

    public c(k kVar) {
        super(kVar);
        if (!kVar.e() || kVar.m() < 0) {
            this.f177b = c.a.a.a.x0.g.b(kVar);
        } else {
            this.f177b = null;
        }
    }

    @Override // c.a.a.a.o0.f, c.a.a.a.k
    public void a(OutputStream outputStream) throws IOException {
        c.a.a.a.x0.a.a(outputStream, "Output stream");
        byte[] bArr = this.f177b;
        if (bArr != null) {
            outputStream.write(bArr);
        } else {
            super.a(outputStream);
        }
    }

    @Override // c.a.a.a.o0.f, c.a.a.a.k
    public boolean a() {
        return this.f177b == null && super.a();
    }

    @Override // c.a.a.a.o0.f, c.a.a.a.k
    public boolean e() {
        return true;
    }

    @Override // c.a.a.a.o0.f, c.a.a.a.k
    public boolean g() {
        return this.f177b == null && super.g();
    }

    @Override // c.a.a.a.o0.f, c.a.a.a.k
    public InputStream k() {
        return this.f177b != null ? new ByteArrayInputStream(this.f177b) : super.k();
    }

    @Override // c.a.a.a.o0.f, c.a.a.a.k
    public long m() {
        byte[] bArr = this.f177b;
        return bArr != null ? bArr.length : super.m();
    }
}

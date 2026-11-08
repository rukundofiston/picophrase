package c.a.a.a.o0;

import c.a.a.a.q0.l.i;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class b extends a {
    private InputStream d;
    private long e = -1;

    public void a(long j) {
        this.e = j;
    }

    public void a(InputStream inputStream) {
        this.d = inputStream;
    }

    @Override // c.a.a.a.k
    public void a(OutputStream outputStream) throws IOException {
        c.a.a.a.x0.a.a(outputStream, "Output stream");
        InputStream inputStreamK = k();
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStreamK.read(bArr);
                if (i == -1) {
                    inputStreamK.close();
                    return;
                }
                outputStream.write(bArr, 0, i);
            }
        } catch (Throwable th) {
            inputStreamK.close();
            throw th;
        }
    }

    @Override // c.a.a.a.k
    public boolean a() {
        InputStream inputStream = this.d;
        return (inputStream == null || inputStream == i.f351a) ? false : true;
    }

    @Override // c.a.a.a.k
    public boolean e() {
        return false;
    }

    @Override // c.a.a.a.k
    public InputStream k() {
        c.a.a.a.x0.b.a(this.d != null, "Content has not been provided");
        return this.d;
    }

    @Override // c.a.a.a.k
    public long m() {
        return this.e;
    }
}

package c.a.a.a.q0.l;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class m extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.r0.g f355a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f356b = false;

    public m(c.a.a.a.r0.g gVar) {
        c.a.a.a.x0.a.a(gVar, "Session output buffer");
        this.f355a = gVar;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f356b) {
            return;
        }
        this.f356b = true;
        this.f355a.flush();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.f355a.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.f356b) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.f355a.a(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.f356b) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.f355a.a(bArr, i, i2);
    }
}

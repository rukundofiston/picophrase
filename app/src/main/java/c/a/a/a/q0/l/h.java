package c.a.a.a.q0.l;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class h extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.r0.g f348a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f349b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f350c = 0;
    private boolean d = false;

    public h(c.a.a.a.r0.g gVar, long j) {
        c.a.a.a.x0.a.a(gVar, "Session output buffer");
        this.f348a = gVar;
        c.a.a.a.x0.a.a(j, "Content length");
        this.f349b = j;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.d) {
            return;
        }
        this.d = true;
        this.f348a.flush();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.f348a.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.d) {
            throw new IOException("Attempted write to closed stream.");
        }
        if (this.f350c < this.f349b) {
            this.f348a.a(i);
            this.f350c++;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.d) {
            throw new IOException("Attempted write to closed stream.");
        }
        long j = this.f350c;
        long j2 = this.f349b;
        if (j < j2) {
            long j3 = j2 - j;
            if (i2 > j3) {
                i2 = (int) j3;
            }
            this.f348a.a(bArr, i, i2);
            this.f350c += (long) i2;
        }
    }
}

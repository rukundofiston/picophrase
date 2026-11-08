package c.a.a.a.q0.l;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class g extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final long f345a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f346b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f347c = false;
    private c.a.a.a.r0.f d;

    public g(c.a.a.a.r0.f fVar, long j) {
        this.d = null;
        c.a.a.a.x0.a.a(fVar, "Session input buffer");
        this.d = fVar;
        c.a.a.a.x0.a.a(j, "Content length");
        this.f345a = j;
    }

    @Override // java.io.InputStream
    public int available() {
        c.a.a.a.r0.f fVar = this.d;
        if (fVar instanceof c.a.a.a.r0.a) {
            return Math.min(((c.a.a.a.r0.a) fVar).length(), (int) (this.f345a - this.f346b));
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f347c) {
            return;
        }
        try {
            if (this.f346b < this.f345a) {
                do {
                } while (read(new byte[2048]) >= 0);
            }
        } finally {
            this.f347c = true;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.f347c) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.f346b >= this.f345a) {
            return -1;
        }
        int iC = this.d.c();
        long j = this.f346b;
        if (iC != -1) {
            this.f346b = j + 1;
        } else if (j < this.f345a) {
            throw new c.a.a.a.a("Premature end of Content-Length delimited message body (expected: " + this.f345a + "; received: " + this.f346b);
        }
        return iC;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.f347c) {
            throw new IOException("Attempted read from closed stream.");
        }
        long j = this.f346b;
        long j2 = this.f345a;
        if (j >= j2) {
            return -1;
        }
        if (((long) i2) + j > j2) {
            i2 = (int) (j2 - j);
        }
        int iA = this.d.a(bArr, i, i2);
        if (iA != -1 || this.f346b >= this.f345a) {
            if (iA > 0) {
                this.f346b += (long) iA;
            }
            return iA;
        }
        throw new c.a.a.a.a("Premature end of Content-Length delimited message body (expected: " + this.f345a + "; received: " + this.f346b);
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        int i;
        if (j <= 0) {
            return 0L;
        }
        byte[] bArr = new byte[2048];
        long jMin = Math.min(j, this.f345a - this.f346b);
        long j2 = 0;
        while (jMin > 0 && (i = read(bArr, 0, (int) Math.min(2048L, jMin))) != -1) {
            long j3 = i;
            j2 += j3;
            jMin -= j3;
        }
        return j2;
    }
}

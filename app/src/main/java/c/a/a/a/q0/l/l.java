package c.a.a.a.q0.l;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class l extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.r0.f f353a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f354b = false;

    public l(c.a.a.a.r0.f fVar) {
        c.a.a.a.x0.a.a(fVar, "Session input buffer");
        this.f353a = fVar;
    }

    @Override // java.io.InputStream
    public int available() {
        c.a.a.a.r0.f fVar = this.f353a;
        if (fVar instanceof c.a.a.a.r0.a) {
            return ((c.a.a.a.r0.a) fVar).length();
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f354b = true;
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.f354b) {
            return -1;
        }
        return this.f353a.c();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (this.f354b) {
            return -1;
        }
        return this.f353a.a(bArr, i, i2);
    }
}

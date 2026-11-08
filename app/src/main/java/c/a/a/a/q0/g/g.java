package c.a.a.a.q0.g;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
class g extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final MessageDigest f202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f203b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private byte[] f204c;

    g(MessageDigest messageDigest) {
        this.f202a = messageDigest;
        messageDigest.reset();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f203b) {
            return;
        }
        this.f203b = true;
        this.f204c = this.f202a.digest();
        super.close();
    }

    public byte[] f() {
        return this.f204c;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.f203b) {
            throw new IOException("Stream has been already closed");
        }
        this.f202a.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.f203b) {
            throw new IOException("Stream has been already closed");
        }
        this.f202a.update(bArr, i, i2);
    }
}

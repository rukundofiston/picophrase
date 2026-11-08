package c.a.a.a.q0.l;

import java.io.IOException;
import java.io.OutputStream;
import org.cocos2dx.lib.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class f extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.r0.g f342a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final byte[] f343b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f344c;
    private boolean d;
    private boolean e;

    public f(int i, c.a.a.a.r0.g gVar) {
        this.f344c = 0;
        this.d = false;
        this.e = false;
        this.f343b = new byte[i];
        this.f342a = gVar;
    }

    @Deprecated
    public f(c.a.a.a.r0.g gVar) {
        this(2048, gVar);
    }

    protected void a(byte[] bArr, int i, int i2) {
        this.f342a.a(Integer.toHexString(this.f344c + i2));
        this.f342a.a(this.f343b, 0, this.f344c);
        this.f342a.a(bArr, i, i2);
        this.f342a.a(BuildConfig.FLAVOR);
        this.f344c = 0;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.e) {
            return;
        }
        this.e = true;
        f();
        this.f342a.flush();
    }

    public void f() {
        if (this.d) {
            return;
        }
        l();
        m();
        this.d = true;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        l();
        this.f342a.flush();
    }

    protected void l() {
        int i = this.f344c;
        if (i > 0) {
            this.f342a.a(Integer.toHexString(i));
            this.f342a.a(this.f343b, 0, this.f344c);
            this.f342a.a(BuildConfig.FLAVOR);
            this.f344c = 0;
        }
    }

    protected void m() {
        this.f342a.a("0");
        this.f342a.a(BuildConfig.FLAVOR);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.e) {
            throw new IOException("Attempted write to closed stream.");
        }
        byte[] bArr = this.f343b;
        int i2 = this.f344c;
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        this.f344c = i3;
        if (i3 == bArr.length) {
            l();
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.e) {
            throw new IOException("Attempted write to closed stream.");
        }
        byte[] bArr2 = this.f343b;
        int length = bArr2.length;
        int i3 = this.f344c;
        if (i2 >= length - i3) {
            a(bArr, i, i2);
        } else {
            System.arraycopy(bArr, i, bArr2, i3, i2);
            this.f344c += i2;
        }
    }
}

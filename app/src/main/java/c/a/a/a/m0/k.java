package c.a.a.a.m0;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class k extends InputStream implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected InputStream f119a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f120b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final l f121c;

    public k(InputStream inputStream, l lVar) {
        c.a.a.a.x0.a.a(inputStream, "Wrapped stream");
        this.f119a = inputStream;
        this.f120b = false;
        this.f121c = lVar;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (!o()) {
            return 0;
        }
        try {
            return this.f119a.available();
        } catch (IOException e) {
            m();
            throw e;
        }
    }

    protected void c(int i) {
        InputStream inputStream = this.f119a;
        if (inputStream == null || i >= 0) {
            return;
        }
        try {
            if (this.f121c != null ? this.f121c.b(inputStream) : true) {
                this.f119a.close();
            }
        } finally {
            this.f119a = null;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f120b = true;
        n();
    }

    @Override // c.a.a.a.m0.i
    public void f() {
        this.f120b = true;
        m();
    }

    @Override // c.a.a.a.m0.i
    public void l() {
        close();
    }

    protected void m() {
        InputStream inputStream = this.f119a;
        if (inputStream != null) {
            try {
                if (this.f121c != null ? this.f121c.a(inputStream) : true) {
                    this.f119a.close();
                }
            } finally {
                this.f119a = null;
            }
        }
    }

    protected void n() {
        InputStream inputStream = this.f119a;
        if (inputStream != null) {
            try {
                if (this.f121c != null ? this.f121c.c(inputStream) : true) {
                    this.f119a.close();
                }
            } finally {
                this.f119a = null;
            }
        }
    }

    protected boolean o() throws IOException {
        if (this.f120b) {
            throw new IOException("Attempted read on closed stream.");
        }
        return this.f119a != null;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (!o()) {
            return -1;
        }
        try {
            int i = this.f119a.read();
            c(i);
            return i;
        } catch (IOException e) {
            m();
            throw e;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!o()) {
            return -1;
        }
        try {
            int i3 = this.f119a.read(bArr, i, i2);
            c(i3);
            return i3;
        } catch (IOException e) {
            m();
            throw e;
        }
    }
}

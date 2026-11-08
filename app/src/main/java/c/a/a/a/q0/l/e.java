package c.a.a.a.q0.l;

import c.a.a.a.h0;
import c.a.a.a.w;
import c.a.a.a.x;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class e extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.r0.f f339a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.x0.d f340b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.a.a.a.l0.b f341c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private boolean h;

    public e(c.a.a.a.r0.f fVar) {
        this(fVar, null);
    }

    public e(c.a.a.a.r0.f fVar, c.a.a.a.l0.b bVar) {
        this.g = false;
        this.h = false;
        c.a.a.a.x0.a.a(fVar, "Session input buffer");
        this.f339a = fVar;
        this.f = 0;
        this.f340b = new c.a.a.a.x0.d(16);
        this.f341c = bVar == null ? c.a.a.a.l0.b.f112c : bVar;
        this.d = 1;
    }

    private int f() throws w, c.a.a.a.a {
        int i = this.d;
        if (i != 1) {
            if (i != 3) {
                throw new IllegalStateException("Inconsistent codec state");
            }
            this.f340b.b();
            if (this.f339a.a(this.f340b) == -1) {
                throw new w("CRLF expected at end of chunk");
            }
            if (!this.f340b.c()) {
                throw new w("Unexpected content at the end of chunk");
            }
            this.d = 1;
        }
        this.f340b.b();
        if (this.f339a.a(this.f340b) == -1) {
            throw new c.a.a.a.a("Premature end of chunk coded message body: closing chunk expected");
        }
        int iB = this.f340b.b(59);
        if (iB < 0) {
            iB = this.f340b.length();
        }
        try {
            return Integer.parseInt(this.f340b.b(0, iB), 16);
        } catch (NumberFormatException unused) {
            throw new w("Bad chunk header");
        }
    }

    private void l() throws w, x, c.a.a.a.a {
        if (this.d == Integer.MAX_VALUE) {
            throw new w("Corrupt data stream");
        }
        try {
            int iF = f();
            this.e = iF;
            if (iF < 0) {
                throw new w("Negative chunk size");
            }
            this.d = 2;
            this.f = 0;
            if (iF == 0) {
                this.g = true;
                m();
            }
        } catch (w e) {
            this.d = Integer.MAX_VALUE;
            throw e;
        }
    }

    private void m() throws w, x {
        try {
            a.a(this.f339a, this.f341c.a(), this.f341c.b(), null);
        } catch (c.a.a.a.m e) {
            w wVar = new w("Invalid footer: " + e.getMessage());
            wVar.initCause(e);
            throw wVar;
        }
    }

    @Override // java.io.InputStream
    public int available() {
        c.a.a.a.r0.f fVar = this.f339a;
        if (fVar instanceof c.a.a.a.r0.a) {
            return Math.min(((c.a.a.a.r0.a) fVar).length(), this.e - this.f);
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.h) {
            return;
        }
        try {
            if (!this.g && this.d != Integer.MAX_VALUE) {
                do {
                } while (read(new byte[2048]) >= 0);
            }
        } finally {
            this.g = true;
            this.h = true;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.h) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.g) {
            return -1;
        }
        if (this.d != 2) {
            l();
            if (this.g) {
                return -1;
            }
        }
        int iC = this.f339a.c();
        if (iC != -1) {
            int i = this.f + 1;
            this.f = i;
            if (i >= this.e) {
                this.d = 3;
            }
        }
        return iC;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.h) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.g) {
            return -1;
        }
        if (this.d != 2) {
            l();
            if (this.g) {
                return -1;
            }
        }
        int iA = this.f339a.a(bArr, i, Math.min(i2, this.e - this.f));
        if (iA != -1) {
            int i3 = this.f + iA;
            this.f = i3;
            if (i3 >= this.e) {
                this.d = 3;
            }
            return iA;
        }
        this.g = true;
        throw new h0("Truncated chunk ( expected size: " + this.e + "; actual size: " + this.f + ")");
    }
}

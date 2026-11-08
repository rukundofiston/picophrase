package c.a.a.a.q0.l;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class c implements c.a.a.a.r0.f, c.a.a.a.r0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private InputStream f333a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private byte[] f334b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c.a.a.a.x0.c f335c;
    private Charset d;
    private boolean e;
    private int f;
    private int g;
    private k h;
    private CodingErrorAction i;
    private CodingErrorAction j;
    private int k;
    private int l;
    private CharsetDecoder m;
    private CharBuffer n;

    private int a(c.a.a.a.x0.d dVar, int i) {
        int i2 = this.k;
        this.k = i + 1;
        if (i > i2 && this.f334b[i - 1] == 13) {
            i--;
        }
        int i3 = i - i2;
        if (!this.e) {
            return a(dVar, ByteBuffer.wrap(this.f334b, i2, i3));
        }
        dVar.a(this.f334b, i2, i3);
        return i3;
    }

    private int a(c.a.a.a.x0.d dVar, ByteBuffer byteBuffer) {
        int iA = 0;
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (this.m == null) {
            CharsetDecoder charsetDecoderNewDecoder = this.d.newDecoder();
            this.m = charsetDecoderNewDecoder;
            charsetDecoderNewDecoder.onMalformedInput(this.i);
            this.m.onUnmappableCharacter(this.j);
        }
        if (this.n == null) {
            this.n = CharBuffer.allocate(1024);
        }
        this.m.reset();
        while (byteBuffer.hasRemaining()) {
            iA += a(this.m.decode(byteBuffer, this.n, true), dVar, byteBuffer);
        }
        int iA2 = iA + a(this.m.flush(this.n), dVar, byteBuffer);
        this.n.clear();
        return iA2;
    }

    private int a(CoderResult coderResult, c.a.a.a.x0.d dVar, ByteBuffer byteBuffer) throws CharacterCodingException {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.n.flip();
        int iRemaining = this.n.remaining();
        while (this.n.hasRemaining()) {
            dVar.a(this.n.get());
        }
        this.n.compact();
        return iRemaining;
    }

    private int b(c.a.a.a.x0.d dVar) {
        int iF = this.f335c.f();
        if (iF > 0) {
            if (this.f335c.b(iF - 1) == 10) {
                iF--;
            }
            if (iF > 0 && this.f335c.b(iF - 1) == 13) {
                iF--;
            }
        }
        if (this.e) {
            dVar.a(this.f335c, 0, iF);
        } else {
            iF = a(dVar, ByteBuffer.wrap(this.f335c.a(), 0, iF));
        }
        this.f335c.c();
        return iF;
    }

    private int g() {
        for (int i = this.k; i < this.l; i++) {
            if (this.f334b[i] == 10) {
                return i;
            }
        }
        return -1;
    }

    @Override // c.a.a.a.r0.f
    public int a(c.a.a.a.x0.d dVar) throws IOException {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        boolean z = true;
        int iE = 0;
        while (z) {
            int iG = g();
            if (iG == -1) {
                if (f()) {
                    int i = this.l;
                    int i2 = this.k;
                    this.f335c.a(this.f334b, i2, i - i2);
                    this.k = this.l;
                }
                iE = e();
                if (iE == -1) {
                }
                if (this.f <= 0 && this.f335c.f() >= this.f) {
                    throw new IOException("Maximum line length limit exceeded");
                }
            } else {
                if (this.f335c.d()) {
                    return a(dVar, iG);
                }
                int i3 = iG + 1;
                int i4 = this.k;
                this.f335c.a(this.f334b, i4, i3 - i4);
                this.k = i3;
            }
            z = false;
            if (this.f <= 0) {
            }
        }
        if (iE == -1 && this.f335c.d()) {
            return -1;
        }
        return b(dVar);
    }

    @Override // c.a.a.a.r0.f
    public int a(byte[] bArr, int i, int i2) throws IOException {
        int iMin;
        if (bArr == null) {
            return 0;
        }
        if (f()) {
            iMin = Math.min(i2, this.l - this.k);
            System.arraycopy(this.f334b, this.k, bArr, i, iMin);
        } else {
            if (i2 > this.g) {
                int i3 = this.f333a.read(bArr, i, i2);
                if (i3 > 0) {
                    this.h.a(i3);
                }
                return i3;
            }
            while (!f()) {
                if (e() == -1) {
                    return -1;
                }
            }
            iMin = Math.min(i2, this.l - this.k);
            System.arraycopy(this.f334b, this.k, bArr, i, iMin);
        }
        this.k += iMin;
        return iMin;
    }

    @Override // c.a.a.a.r0.f
    public c.a.a.a.r0.e a() {
        return this.h;
    }

    protected void a(InputStream inputStream, int i, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(inputStream, "Input stream");
        c.a.a.a.x0.a.a(i, "Buffer size");
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        this.f333a = inputStream;
        this.f334b = new byte[i];
        this.k = 0;
        this.l = 0;
        this.f335c = new c.a.a.a.x0.c(i);
        String str = (String) eVar.b("http.protocol.element-charset");
        Charset charsetForName = str != null ? Charset.forName(str) : c.a.a.a.c.f55b;
        this.d = charsetForName;
        this.e = charsetForName.equals(c.a.a.a.c.f55b);
        this.m = null;
        this.f = eVar.b("http.connection.max-line-length", -1);
        this.g = eVar.b("http.connection.min-chunk-limit", 512);
        this.h = d();
        CodingErrorAction codingErrorAction = (CodingErrorAction) eVar.b("http.malformed.input.action");
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.i = codingErrorAction;
        CodingErrorAction codingErrorAction2 = (CodingErrorAction) eVar.b("http.unmappable.input.action");
        if (codingErrorAction2 == null) {
            codingErrorAction2 = CodingErrorAction.REPORT;
        }
        this.j = codingErrorAction2;
    }

    @Override // c.a.a.a.r0.f
    public int c() {
        while (!f()) {
            if (e() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.f334b;
        int i = this.k;
        this.k = i + 1;
        return bArr[i] & 255;
    }

    protected k d() {
        return new k();
    }

    protected int e() throws IOException {
        int i = this.k;
        if (i > 0) {
            int i2 = this.l - i;
            if (i2 > 0) {
                byte[] bArr = this.f334b;
                System.arraycopy(bArr, i, bArr, 0, i2);
            }
            this.k = 0;
            this.l = i2;
        }
        int i3 = this.l;
        byte[] bArr2 = this.f334b;
        int i4 = this.f333a.read(bArr2, i3, bArr2.length - i3);
        if (i4 == -1) {
            return -1;
        }
        this.l = i3 + i4;
        this.h.a(i4);
        return i4;
    }

    protected boolean f() {
        return this.k < this.l;
    }

    @Override // c.a.a.a.r0.a
    public int length() {
        return this.l - this.k;
    }
}

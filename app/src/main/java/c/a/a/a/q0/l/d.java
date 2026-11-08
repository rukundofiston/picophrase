package c.a.a.a.q0.l;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class d implements c.a.a.a.r0.g, c.a.a.a.r0.a {
    private static final byte[] k = {13, 10};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private OutputStream f336a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c.a.a.a.x0.c f337b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Charset f338c;
    private boolean d;
    private int e;
    private k f;
    private CodingErrorAction g;
    private CodingErrorAction h;
    private CharsetEncoder i;
    private ByteBuffer j;

    private void a(CharBuffer charBuffer) throws IOException {
        if (charBuffer.hasRemaining()) {
            if (this.i == null) {
                CharsetEncoder charsetEncoderNewEncoder = this.f338c.newEncoder();
                this.i = charsetEncoderNewEncoder;
                charsetEncoderNewEncoder.onMalformedInput(this.g);
                this.i.onUnmappableCharacter(this.h);
            }
            if (this.j == null) {
                this.j = ByteBuffer.allocate(1024);
            }
            this.i.reset();
            while (charBuffer.hasRemaining()) {
                a(this.i.encode(charBuffer, this.j, true));
            }
            a(this.i.flush(this.j));
            this.j.clear();
        }
    }

    private void a(CoderResult coderResult) throws IOException {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.j.flip();
        while (this.j.hasRemaining()) {
            a(this.j.get());
        }
        this.j.compact();
    }

    @Override // c.a.a.a.r0.g
    public c.a.a.a.r0.e a() {
        return this.f;
    }

    @Override // c.a.a.a.r0.g
    public void a(int i) throws IOException {
        if (this.f337b.e()) {
            c();
        }
        this.f337b.a(i);
    }

    @Override // c.a.a.a.r0.g
    public void a(c.a.a.a.x0.d dVar) throws IOException {
        if (dVar == null) {
            return;
        }
        int i = 0;
        if (this.d) {
            int length = dVar.length();
            while (length > 0) {
                int iMin = Math.min(this.f337b.b() - this.f337b.f(), length);
                if (iMin > 0) {
                    this.f337b.a(dVar, i, iMin);
                }
                if (this.f337b.e()) {
                    c();
                }
                i += iMin;
                length -= iMin;
            }
        } else {
            a(CharBuffer.wrap(dVar.a(), 0, dVar.length()));
        }
        a(k);
    }

    protected void a(OutputStream outputStream, int i, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(outputStream, "Input stream");
        c.a.a.a.x0.a.a(i, "Buffer size");
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        this.f336a = outputStream;
        this.f337b = new c.a.a.a.x0.c(i);
        String str = (String) eVar.b("http.protocol.element-charset");
        Charset charsetForName = str != null ? Charset.forName(str) : c.a.a.a.c.f55b;
        this.f338c = charsetForName;
        this.d = charsetForName.equals(c.a.a.a.c.f55b);
        this.i = null;
        this.e = eVar.b("http.connection.min-chunk-limit", 512);
        this.f = b();
        CodingErrorAction codingErrorAction = (CodingErrorAction) eVar.b("http.malformed.input.action");
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.g = codingErrorAction;
        CodingErrorAction codingErrorAction2 = (CodingErrorAction) eVar.b("http.unmappable.input.action");
        if (codingErrorAction2 == null) {
            codingErrorAction2 = CodingErrorAction.REPORT;
        }
        this.h = codingErrorAction2;
    }

    @Override // c.a.a.a.r0.g
    public void a(String str) throws IOException {
        if (str == null) {
            return;
        }
        if (str.length() > 0) {
            if (this.d) {
                for (int i = 0; i < str.length(); i++) {
                    a(str.charAt(i));
                }
            } else {
                a(CharBuffer.wrap(str));
            }
        }
        a(k);
    }

    public void a(byte[] bArr) throws IOException {
        if (bArr == null) {
            return;
        }
        a(bArr, 0, bArr.length);
    }

    @Override // c.a.a.a.r0.g
    public void a(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            return;
        }
        if (i2 > this.e || i2 > this.f337b.b()) {
            c();
            this.f336a.write(bArr, i, i2);
            this.f.a(i2);
        } else {
            if (i2 > this.f337b.b() - this.f337b.f()) {
                c();
            }
            this.f337b.a(bArr, i, i2);
        }
    }

    protected k b() {
        return new k();
    }

    protected void c() throws IOException {
        int iF = this.f337b.f();
        if (iF > 0) {
            this.f336a.write(this.f337b.a(), 0, iF);
            this.f337b.c();
            this.f.a(iF);
        }
    }

    @Override // c.a.a.a.r0.g
    public void flush() throws IOException {
        c();
        this.f336a.flush();
    }

    @Override // c.a.a.a.r0.a
    public int length() {
        return this.f337b.f();
    }
}

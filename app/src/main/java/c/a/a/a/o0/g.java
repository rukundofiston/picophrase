package c.a.a.a.o0;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class g extends a implements Cloneable {
    protected final byte[] d;

    public g(String str, e eVar) {
        c.a.a.a.x0.a.a(str, "Source string");
        Charset charsetA = eVar != null ? eVar.a() : null;
        this.d = str.getBytes(charsetA == null ? c.a.a.a.v0.d.f412a : charsetA);
        if (eVar != null) {
            a(eVar.toString());
        }
    }

    @Override // c.a.a.a.k
    public void a(OutputStream outputStream) throws IOException {
        c.a.a.a.x0.a.a(outputStream, "Output stream");
        outputStream.write(this.d);
        outputStream.flush();
    }

    @Override // c.a.a.a.k
    public boolean a() {
        return false;
    }

    public Object clone() {
        return super.clone();
    }

    @Override // c.a.a.a.k
    public boolean e() {
        return true;
    }

    @Override // c.a.a.a.k
    public InputStream k() {
        return new ByteArrayInputStream(this.d);
    }

    @Override // c.a.a.a.k
    public long m() {
        return this.d.length;
    }
}

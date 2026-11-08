package c.a.a.a.q0.i;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class n implements c.a.a.a.r0.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.r0.g f272a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final r f273b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f274c;

    public n(c.a.a.a.r0.g gVar, r rVar, String str) {
        this.f272a = gVar;
        this.f273b = rVar;
        this.f274c = str == null ? c.a.a.a.c.f55b.name() : str;
    }

    @Override // c.a.a.a.r0.g
    public c.a.a.a.r0.e a() {
        return this.f272a.a();
    }

    @Override // c.a.a.a.r0.g
    public void a(int i) throws IOException {
        this.f272a.a(i);
        if (this.f273b.a()) {
            this.f273b.b(i);
        }
    }

    @Override // c.a.a.a.r0.g
    public void a(c.a.a.a.x0.d dVar) throws IOException {
        this.f272a.a(dVar);
        if (this.f273b.a()) {
            this.f273b.b((new String(dVar.a(), 0, dVar.length()) + "\r\n").getBytes(this.f274c));
        }
    }

    @Override // c.a.a.a.r0.g
    public void a(String str) throws IOException {
        this.f272a.a(str);
        if (this.f273b.a()) {
            this.f273b.b((str + "\r\n").getBytes(this.f274c));
        }
    }

    @Override // c.a.a.a.r0.g
    public void a(byte[] bArr, int i, int i2) throws IOException {
        this.f272a.a(bArr, i, i2);
        if (this.f273b.a()) {
            this.f273b.b(bArr, i, i2);
        }
    }

    @Override // c.a.a.a.r0.g
    public void flush() {
        this.f272a.flush();
    }
}

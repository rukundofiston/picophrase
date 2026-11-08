package c.a.a.a.q0.i;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class m implements c.a.a.a.r0.f, c.a.a.a.r0.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.r0.f f269a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.r0.b f270b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final r f271c;
    private final String d;

    public m(c.a.a.a.r0.f fVar, r rVar, String str) {
        this.f269a = fVar;
        this.f270b = fVar instanceof c.a.a.a.r0.b ? (c.a.a.a.r0.b) fVar : null;
        this.f271c = rVar;
        this.d = str == null ? c.a.a.a.c.f55b.name() : str;
    }

    @Override // c.a.a.a.r0.f
    public int a(c.a.a.a.x0.d dVar) throws IOException {
        int iA = this.f269a.a(dVar);
        if (this.f271c.a() && iA >= 0) {
            this.f271c.a((new String(dVar.a(), dVar.length() - iA, iA) + "\r\n").getBytes(this.d));
        }
        return iA;
    }

    @Override // c.a.a.a.r0.f
    public int a(byte[] bArr, int i, int i2) throws IOException {
        int iA = this.f269a.a(bArr, i, i2);
        if (this.f271c.a() && iA > 0) {
            this.f271c.a(bArr, i, iA);
        }
        return iA;
    }

    @Override // c.a.a.a.r0.f
    public c.a.a.a.r0.e a() {
        return this.f269a.a();
    }

    @Override // c.a.a.a.r0.f
    public boolean a(int i) {
        return this.f269a.a(i);
    }

    @Override // c.a.a.a.r0.b
    public boolean b() {
        c.a.a.a.r0.b bVar = this.f270b;
        if (bVar != null) {
            return bVar.b();
        }
        return false;
    }

    @Override // c.a.a.a.r0.f
    public int c() throws IOException {
        int iC = this.f269a.c();
        if (this.f271c.a() && iC != -1) {
            this.f271c.a(iC);
        }
        return iC;
    }
}

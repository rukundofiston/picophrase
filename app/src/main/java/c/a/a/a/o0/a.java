package c.a.a.a.o0;

import c.a.a.a.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected c.a.a.a.e f174a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected c.a.a.a.e f175b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f176c;

    protected a() {
    }

    public void a(c.a.a.a.e eVar) {
        this.f175b = eVar;
    }

    public void a(String str) {
        b(str != null ? new c.a.a.a.s0.b("Content-Type", str) : null);
    }

    public void a(boolean z) {
        this.f176c = z;
    }

    public void b(c.a.a.a.e eVar) {
        this.f174a = eVar;
    }

    @Override // c.a.a.a.k
    public c.a.a.a.e c() {
        return this.f175b;
    }

    @Override // c.a.a.a.k
    public c.a.a.a.e d() {
        return this.f174a;
    }

    @Override // c.a.a.a.k
    public boolean g() {
        return this.f176c;
    }

    @Override // c.a.a.a.k
    @Deprecated
    public void h() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (this.f174a != null) {
            sb.append("Content-Type: ");
            sb.append(this.f174a.getValue());
            sb.append(',');
        }
        if (this.f175b != null) {
            sb.append("Content-Encoding: ");
            sb.append(this.f175b.getValue());
            sb.append(',');
        }
        long jM = m();
        if (jM >= 0) {
            sb.append("Content-Length: ");
            sb.append(jM);
            sb.append(',');
        }
        sb.append("Chunked: ");
        sb.append(this.f176c);
        sb.append(']');
        return sb.toString();
    }
}

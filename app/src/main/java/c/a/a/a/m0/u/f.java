package c.a.a.a.m0.u;

import c.a.a.a.n;
import c.a.a.a.x0.h;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public final class f implements e, Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n f137a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InetAddress f138b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f139c;
    private n[] d;
    private e.b e;
    private e.a f;
    private boolean g;

    public f(b bVar) {
        this(bVar.b(), bVar.d());
    }

    public f(n nVar, InetAddress inetAddress) {
        c.a.a.a.x0.a.a(nVar, "Target host");
        this.f137a = nVar;
        this.f138b = inetAddress;
        this.e = e.b.PLAIN;
        this.f = e.a.PLAIN;
    }

    @Override // c.a.a.a.m0.u.e
    public final n a(int i) {
        c.a.a.a.x0.a.a(i, "Hop index");
        int iC = c();
        c.a.a.a.x0.a.a(i < iC, "Hop index exceeds tracked route length");
        return i < iC - 1 ? this.d[i] : this.f137a;
    }

    public final void a(n nVar, boolean z) {
        c.a.a.a.x0.a.a(nVar, "Proxy host");
        c.a.a.a.x0.b.a(!this.f139c, "Already connected");
        this.f139c = true;
        this.d = new n[]{nVar};
        this.g = z;
    }

    public final void a(boolean z) {
        c.a.a.a.x0.b.a(!this.f139c, "Already connected");
        this.f139c = true;
        this.g = z;
    }

    @Override // c.a.a.a.m0.u.e
    public final boolean a() {
        return this.g;
    }

    @Override // c.a.a.a.m0.u.e
    public final n b() {
        return this.f137a;
    }

    public final void b(boolean z) {
        c.a.a.a.x0.b.a(this.f139c, "No layered protocol unless connected");
        this.f = e.a.LAYERED;
        this.g = z;
    }

    @Override // c.a.a.a.m0.u.e
    public final int c() {
        if (!this.f139c) {
            return 0;
        }
        n[] nVarArr = this.d;
        if (nVarArr == null) {
            return 1;
        }
        return 1 + nVarArr.length;
    }

    public final void c(boolean z) {
        c.a.a.a.x0.b.a(this.f139c, "No tunnel unless connected");
        c.a.a.a.x0.b.a(this.d, "No tunnel without proxy");
        this.e = e.b.TUNNELLED;
        this.g = z;
    }

    public Object clone() {
        return super.clone();
    }

    @Override // c.a.a.a.m0.u.e
    public final InetAddress d() {
        return this.f138b;
    }

    @Override // c.a.a.a.m0.u.e
    public final boolean e() {
        return this.f == e.a.LAYERED;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.f139c == fVar.f139c && this.g == fVar.g && this.e == fVar.e && this.f == fVar.f && h.a(this.f137a, fVar.f137a) && h.a(this.f138b, fVar.f138b) && h.a((Object[]) this.d, (Object[]) fVar.d);
    }

    @Override // c.a.a.a.m0.u.e
    public final boolean f() {
        return this.e == e.b.TUNNELLED;
    }

    @Override // c.a.a.a.m0.u.e
    public final n g() {
        n[] nVarArr = this.d;
        if (nVarArr == null) {
            return null;
        }
        return nVarArr[0];
    }

    public final boolean h() {
        return this.f139c;
    }

    public final int hashCode() {
        int iA = h.a(h.a(17, this.f137a), this.f138b);
        n[] nVarArr = this.d;
        if (nVarArr != null) {
            for (n nVar : nVarArr) {
                iA = h.a(iA, nVar);
            }
        }
        return h.a(h.a(h.a(h.a(iA, this.f139c), this.g), this.e), this.f);
    }

    public void i() {
        this.f139c = false;
        this.d = null;
        this.e = e.b.PLAIN;
        this.f = e.a.PLAIN;
        this.g = false;
    }

    public final b j() {
        if (this.f139c) {
            return new b(this.f137a, this.f138b, this.d, this.g, this.e, this.f);
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((c() * 30) + 50);
        sb.append("RouteTracker[");
        InetAddress inetAddress = this.f138b;
        if (inetAddress != null) {
            sb.append(inetAddress);
            sb.append("->");
        }
        sb.append('{');
        if (this.f139c) {
            sb.append('c');
        }
        if (this.e == e.b.TUNNELLED) {
            sb.append('t');
        }
        if (this.f == e.a.LAYERED) {
            sb.append('l');
        }
        if (this.g) {
            sb.append('s');
        }
        sb.append("}->");
        n[] nVarArr = this.d;
        if (nVarArr != null) {
            for (n nVar : nVarArr) {
                sb.append(nVar);
                sb.append("->");
            }
        }
        sb.append(this.f137a);
        sb.append(']');
        return sb.toString();
    }
}

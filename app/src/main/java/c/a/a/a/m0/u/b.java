package c.a.a.a.m0.u;

import c.a.a.a.n;
import c.a.a.a.x0.h;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b implements e, Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n f128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InetAddress f129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<n> f130c;
    private final e.b d;
    private final e.a e;
    private final boolean f;

    public b(n nVar) {
        this(nVar, (InetAddress) null, (List<n>) Collections.emptyList(), false, e.b.PLAIN, e.a.PLAIN);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b(n nVar, InetAddress inetAddress, n nVar2, boolean z) {
        this(nVar, inetAddress, (List<n>) Collections.singletonList(nVar2), z, z ? e.b.TUNNELLED : e.b.PLAIN, z ? e.a.LAYERED : e.a.PLAIN);
        c.a.a.a.x0.a.a(nVar2, "Proxy host");
    }

    private b(n nVar, InetAddress inetAddress, List<n> list, boolean z, e.b bVar, e.a aVar) {
        c.a.a.a.x0.a.a(nVar, "Target host");
        this.f128a = a(nVar);
        this.f129b = inetAddress;
        this.f130c = (list == null || list.isEmpty()) ? null : new ArrayList(list);
        if (bVar == e.b.TUNNELLED) {
            c.a.a.a.x0.a.a(this.f130c != null, "Proxy required if tunnelled");
        }
        this.f = z;
        this.d = bVar == null ? e.b.PLAIN : bVar;
        this.e = aVar == null ? e.a.PLAIN : aVar;
    }

    public b(n nVar, InetAddress inetAddress, boolean z) {
        this(nVar, inetAddress, (List<n>) Collections.emptyList(), z, e.b.PLAIN, e.a.PLAIN);
    }

    public b(n nVar, InetAddress inetAddress, n[] nVarArr, boolean z, e.b bVar, e.a aVar) {
        this(nVar, inetAddress, (List<n>) (nVarArr != null ? Arrays.asList(nVarArr) : null), z, bVar, aVar);
    }

    private static int a(String str) {
        if ("http".equalsIgnoreCase(str)) {
            return 80;
        }
        return "https".equalsIgnoreCase(str) ? 443 : -1;
    }

    private static n a(n nVar) {
        if (nVar.c() >= 0) {
            return nVar;
        }
        InetAddress inetAddressA = nVar.a();
        String strD = nVar.d();
        return inetAddressA != null ? new n(inetAddressA, a(strD), strD) : new n(nVar.b(), a(strD), strD);
    }

    @Override // c.a.a.a.m0.u.e
    public final n a(int i) {
        c.a.a.a.x0.a.a(i, "Hop index");
        int iC = c();
        c.a.a.a.x0.a.a(i < iC, "Hop index exceeds tracked route length");
        return i < iC - 1 ? this.f130c.get(i) : this.f128a;
    }

    @Override // c.a.a.a.m0.u.e
    public final boolean a() {
        return this.f;
    }

    @Override // c.a.a.a.m0.u.e
    public final n b() {
        return this.f128a;
    }

    @Override // c.a.a.a.m0.u.e
    public final int c() {
        List<n> list = this.f130c;
        if (list != null) {
            return 1 + list.size();
        }
        return 1;
    }

    public Object clone() {
        return super.clone();
    }

    @Override // c.a.a.a.m0.u.e
    public final InetAddress d() {
        return this.f129b;
    }

    @Override // c.a.a.a.m0.u.e
    public final boolean e() {
        return this.e == e.a.LAYERED;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f == bVar.f && this.d == bVar.d && this.e == bVar.e && h.a(this.f128a, bVar.f128a) && h.a(this.f129b, bVar.f129b) && h.a(this.f130c, bVar.f130c);
    }

    @Override // c.a.a.a.m0.u.e
    public final boolean f() {
        return this.d == e.b.TUNNELLED;
    }

    @Override // c.a.a.a.m0.u.e
    public final n g() {
        List<n> list = this.f130c;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.f130c.get(0);
    }

    public final int hashCode() {
        int iA = h.a(h.a(17, this.f128a), this.f129b);
        List<n> list = this.f130c;
        if (list != null) {
            Iterator<n> it = list.iterator();
            while (it.hasNext()) {
                iA = h.a(iA, it.next());
            }
        }
        return h.a(h.a(h.a(iA, this.f), this.d), this.e);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((c() * 30) + 50);
        InetAddress inetAddress = this.f129b;
        if (inetAddress != null) {
            sb.append(inetAddress);
            sb.append("->");
        }
        sb.append('{');
        if (this.d == e.b.TUNNELLED) {
            sb.append('t');
        }
        if (this.e == e.a.LAYERED) {
            sb.append('l');
        }
        if (this.f) {
            sb.append('s');
        }
        sb.append("}->");
        List<n> list = this.f130c;
        if (list != null) {
            Iterator<n> it = list.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append("->");
            }
        }
        sb.append(this.f128a);
        return sb.toString();
    }
}

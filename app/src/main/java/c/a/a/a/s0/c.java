package c.a.a.a.s0;

import c.a.a.a.y;

/* JADX INFO: loaded from: classes.dex */
public class c implements c.a.a.a.f, Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f361a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f362b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final y[] f363c;

    public c(String str, String str2) {
        this(str, str2, null);
    }

    public c(String str, String str2, y[] yVarArr) {
        c.a.a.a.x0.a.a(str, "Name");
        this.f361a = str;
        this.f362b = str2;
        if (yVarArr != null) {
            this.f363c = yVarArr;
        } else {
            this.f363c = new y[0];
        }
    }

    @Override // c.a.a.a.f
    public y a(int i) {
        return this.f363c[i];
    }

    @Override // c.a.a.a.f
    public y a(String str) {
        c.a.a.a.x0.a.a(str, "Name");
        for (y yVar : this.f363c) {
            if (yVar.getName().equalsIgnoreCase(str)) {
                return yVar;
            }
        }
        return null;
    }

    @Override // c.a.a.a.f
    public y[] a() {
        return (y[]) this.f363c.clone();
    }

    @Override // c.a.a.a.f
    public int b() {
        return this.f363c.length;
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c.a.a.a.f)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f361a.equals(cVar.f361a) && c.a.a.a.x0.h.a(this.f362b, cVar.f362b) && c.a.a.a.x0.h.a((Object[]) this.f363c, (Object[]) cVar.f363c);
    }

    @Override // c.a.a.a.f
    public String getName() {
        return this.f361a;
    }

    @Override // c.a.a.a.f
    public String getValue() {
        return this.f362b;
    }

    public int hashCode() {
        int iA = c.a.a.a.x0.h.a(c.a.a.a.x0.h.a(17, this.f361a), this.f362b);
        for (y yVar : this.f363c) {
            iA = c.a.a.a.x0.h.a(iA, yVar);
        }
        return iA;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f361a);
        if (this.f362b != null) {
            sb.append("=");
            sb.append(this.f362b);
        }
        for (y yVar : this.f363c) {
            sb.append("; ");
            sb.append(yVar);
        }
        return sb.toString();
    }
}

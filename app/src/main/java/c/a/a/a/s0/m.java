package c.a.a.a.s0;

import c.a.a.a.y;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class m implements y, Cloneable, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f382a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f383b;

    public m(String str, String str2) {
        c.a.a.a.x0.a.a(str, "Name");
        this.f382a = str;
        this.f383b = str2;
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        m mVar = (m) obj;
        return this.f382a.equals(mVar.f382a) && c.a.a.a.x0.h.a(this.f383b, mVar.f383b);
    }

    @Override // c.a.a.a.y
    public String getName() {
        return this.f382a;
    }

    @Override // c.a.a.a.y
    public String getValue() {
        return this.f383b;
    }

    public int hashCode() {
        return c.a.a.a.x0.h.a(c.a.a.a.x0.h.a(17, this.f382a), this.f383b);
    }

    public String toString() {
        if (this.f383b == null) {
            return this.f382a;
        }
        StringBuilder sb = new StringBuilder(this.f382a.length() + 1 + this.f383b.length());
        sb.append(this.f382a);
        sb.append("=");
        sb.append(this.f383b);
        return sb.toString();
    }
}

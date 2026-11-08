package c.a.a.a.i0;

import java.io.Serializable;
import java.security.Principal;

/* JADX INFO: loaded from: classes.dex */
public class p implements m, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final q f78a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f79b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f80c;

    @Override // c.a.a.a.i0.m
    public Principal a() {
        return this.f78a;
    }

    @Override // c.a.a.a.i0.m
    public String b() {
        return this.f79b;
    }

    public String c() {
        this.f78a.a();
        throw null;
    }

    public String d() {
        this.f78a.b();
        throw null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return c.a.a.a.x0.h.a(this.f78a, pVar.f78a) && c.a.a.a.x0.h.a(this.f80c, pVar.f80c);
    }

    public int hashCode() {
        return c.a.a.a.x0.h.a(c.a.a.a.x0.h.a(17, this.f78a), this.f80c);
    }

    public String toString() {
        return "[principal: " + this.f78a + "][workstation: " + this.f80c + "]";
    }
}

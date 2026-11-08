package c.a.a.a.i0;

import java.io.Serializable;
import java.security.Principal;

/* JADX INFO: loaded from: classes.dex */
public final class j implements Principal, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f74a;

    public j(String str) {
        c.a.a.a.x0.a.a(str, "User name");
        this.f74a = str;
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof j) && c.a.a.a.x0.h.a(this.f74a, ((j) obj).f74a);
    }

    @Override // java.security.Principal
    public String getName() {
        return this.f74a;
    }

    @Override // java.security.Principal
    public int hashCode() {
        return c.a.a.a.x0.h.a(17, this.f74a);
    }

    @Override // java.security.Principal
    public String toString() {
        return "[principal: " + this.f74a + "]";
    }
}

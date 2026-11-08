package c.a.a.a.i0;

import java.io.Serializable;
import java.security.Principal;

/* JADX INFO: loaded from: classes.dex */
public class r implements m, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f81a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f82b;

    public r(String str) {
        String strSubstring;
        c.a.a.a.x0.a.a(str, "Username:password string");
        int iIndexOf = str.indexOf(58);
        if (iIndexOf >= 0) {
            this.f81a = new j(str.substring(0, iIndexOf));
            strSubstring = str.substring(iIndexOf + 1);
        } else {
            this.f81a = new j(str);
            strSubstring = null;
        }
        this.f82b = strSubstring;
    }

    @Override // c.a.a.a.i0.m
    public Principal a() {
        return this.f81a;
    }

    @Override // c.a.a.a.i0.m
    public String b() {
        return this.f82b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof r) && c.a.a.a.x0.h.a(this.f81a, ((r) obj).f81a);
    }

    public int hashCode() {
        return this.f81a.hashCode();
    }

    public String toString() {
        return this.f81a.toString();
    }
}

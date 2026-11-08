package c.a.a.a;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class n implements Cloneable, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final String f164a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final String f165b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final int f166c;
    protected final String d;
    protected final InetAddress e;

    public n(String str, int i) {
        this(str, i, (String) null);
    }

    public n(String str, int i, String str2) {
        c.a.a.a.x0.a.a(str, "Host name");
        this.f164a = str;
        this.f165b = str.toLowerCase(Locale.ROOT);
        this.d = str2 != null ? str2.toLowerCase(Locale.ROOT) : "http";
        this.f166c = i;
        this.e = null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public n(InetAddress inetAddress, int i, String str) {
        this(inetAddress, inetAddress.getHostName(), i, str);
        c.a.a.a.x0.a.a(inetAddress, "Inet address");
    }

    public n(InetAddress inetAddress, String str, int i, String str2) {
        c.a.a.a.x0.a.a(inetAddress, "Inet address");
        this.e = inetAddress;
        c.a.a.a.x0.a.a(str, "Hostname");
        String str3 = str;
        this.f164a = str3;
        this.f165b = str3.toLowerCase(Locale.ROOT);
        this.d = str2 != null ? str2.toLowerCase(Locale.ROOT) : "http";
        this.f166c = i;
    }

    public InetAddress a() {
        return this.e;
    }

    public String b() {
        return this.f164a;
    }

    public int c() {
        return this.f166c;
    }

    public Object clone() {
        return super.clone();
    }

    public String d() {
        return this.d;
    }

    public String e() {
        if (this.f166c == -1) {
            return this.f164a;
        }
        StringBuilder sb = new StringBuilder(this.f164a.length() + 6);
        sb.append(this.f164a);
        sb.append(":");
        sb.append(Integer.toString(this.f166c));
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        if (this.f165b.equals(nVar.f165b) && this.f166c == nVar.f166c && this.d.equals(nVar.d)) {
            InetAddress inetAddress = this.e;
            InetAddress inetAddress2 = nVar.e;
            if (inetAddress == null) {
                if (inetAddress2 == null) {
                    return true;
                }
            } else if (inetAddress.equals(inetAddress2)) {
                return true;
            }
        }
        return false;
    }

    public String f() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.d);
        sb.append("://");
        sb.append(this.f164a);
        if (this.f166c != -1) {
            sb.append(':');
            sb.append(Integer.toString(this.f166c));
        }
        return sb.toString();
    }

    public int hashCode() {
        int iA = c.a.a.a.x0.h.a(c.a.a.a.x0.h.a(c.a.a.a.x0.h.a(17, this.f165b), this.f166c), this.d);
        InetAddress inetAddress = this.e;
        return inetAddress != null ? c.a.a.a.x0.h.a(iA, inetAddress) : iA;
    }

    public String toString() {
        return f();
    }
}

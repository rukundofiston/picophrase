package c.a.a.a.i0;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public static final String e = null;
    public static final String f = null;
    public static final String g = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f68a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f69b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f70c;
    private final int d;

    static {
        new g(null, -1, null, null);
    }

    public g(c.a.a.a.n nVar, String str, String str2) {
        c.a.a.a.x0.a.a(nVar, "Host");
        this.f70c = nVar.b().toLowerCase(Locale.ROOT);
        this.d = nVar.c() < 0 ? -1 : nVar.c();
        this.f69b = str == null ? f : str;
        this.f68a = str2 == null ? g : str2.toUpperCase(Locale.ROOT);
    }

    public g(String str, int i) {
        this(str, i, f, g);
    }

    public g(String str, int i, String str2, String str3) {
        this.f70c = str == null ? e : str.toLowerCase(Locale.ROOT);
        this.d = i < 0 ? -1 : i;
        this.f69b = str2 == null ? f : str2;
        this.f68a = str3 == null ? g : str3.toUpperCase(Locale.ROOT);
    }

    public int a(g gVar) {
        int i;
        if (c.a.a.a.x0.h.a(this.f68a, gVar.f68a)) {
            i = 1;
        } else {
            String str = this.f68a;
            String str2 = g;
            if (str != str2 && gVar.f68a != str2) {
                return -1;
            }
            i = 0;
        }
        if (c.a.a.a.x0.h.a(this.f69b, gVar.f69b)) {
            i += 2;
        } else {
            String str3 = this.f69b;
            String str4 = f;
            if (str3 != str4 && gVar.f69b != str4) {
                return -1;
            }
        }
        int i2 = this.d;
        int i3 = gVar.d;
        if (i2 == i3) {
            i += 4;
        } else if (i2 != -1 && i3 != -1) {
            return -1;
        }
        if (c.a.a.a.x0.h.a(this.f70c, gVar.f70c)) {
            return i + 8;
        }
        String str5 = this.f70c;
        String str6 = e;
        if (str5 == str6 || gVar.f70c == str6) {
            return i;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g)) {
            return super.equals(obj);
        }
        g gVar = (g) obj;
        return c.a.a.a.x0.h.a(this.f70c, gVar.f70c) && this.d == gVar.d && c.a.a.a.x0.h.a(this.f69b, gVar.f69b) && c.a.a.a.x0.h.a(this.f68a, gVar.f68a);
    }

    public int hashCode() {
        return c.a.a.a.x0.h.a(c.a.a.a.x0.h.a(c.a.a.a.x0.h.a(c.a.a.a.x0.h.a(17, this.f70c), this.d), this.f69b), this.f68a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.f68a;
        if (str != null) {
            sb.append(str.toUpperCase(Locale.ROOT));
            sb.append(' ');
        }
        if (this.f69b != null) {
            sb.append('\'');
            sb.append(this.f69b);
            sb.append('\'');
        } else {
            sb.append("<any realm>");
        }
        if (this.f70c != null) {
            sb.append('@');
            sb.append(this.f70c);
            if (this.d >= 0) {
                sb.append(':');
                sb.append(this.d);
            }
        }
        return sb.toString();
    }
}

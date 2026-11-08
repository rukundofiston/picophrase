package c.a.a.a.n0;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f167a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f168b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f169c;
    private final boolean d;

    public f(String str, int i, String str2, boolean z) {
        c.a.a.a.x0.a.b(str, "Host");
        c.a.a.a.x0.a.a(i, "Port");
        c.a.a.a.x0.a.a(str2, "Path");
        this.f167a = str.toLowerCase(Locale.ROOT);
        this.f168b = i;
        if (c.a.a.a.x0.i.b(str2)) {
            this.f169c = "/";
        } else {
            this.f169c = str2;
        }
        this.d = z;
    }

    public String a() {
        return this.f167a;
    }

    public String b() {
        return this.f169c;
    }

    public int c() {
        return this.f168b;
    }

    public boolean d() {
        return this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (this.d) {
            sb.append("(secure)");
        }
        sb.append(this.f167a);
        sb.append(':');
        sb.append(Integer.toString(this.f168b));
        sb.append(this.f169c);
        sb.append(']');
        return sb.toString();
    }
}

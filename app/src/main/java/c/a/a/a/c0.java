package c.a.a.a;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class c0 implements Serializable, Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final String f57a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final int f58b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final int f59c;

    public c0(String str, int i, int i2) {
        c.a.a.a.x0.a.a(str, "Protocol name");
        this.f57a = str;
        c.a.a.a.x0.a.a(i, "Protocol minor version");
        this.f58b = i;
        c.a.a.a.x0.a.a(i2, "Protocol minor version");
        this.f59c = i2;
    }

    public final int a() {
        return this.f58b;
    }

    public int a(c0 c0Var) {
        c.a.a.a.x0.a.a(c0Var, "Protocol version");
        c.a.a.a.x0.a.a(this.f57a.equals(c0Var.f57a), "Versions for different protocols cannot be compared: %s %s", this, c0Var);
        int iA = a() - c0Var.a();
        return iA == 0 ? b() - c0Var.b() : iA;
    }

    public c0 a(int i, int i2) {
        return (i == this.f58b && i2 == this.f59c) ? this : new c0(this.f57a, i, i2);
    }

    public final int b() {
        return this.f59c;
    }

    public boolean b(c0 c0Var) {
        return c0Var != null && this.f57a.equals(c0Var.f57a);
    }

    public final String c() {
        return this.f57a;
    }

    public final boolean c(c0 c0Var) {
        return b(c0Var) && a(c0Var) <= 0;
    }

    public Object clone() {
        return super.clone();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c0)) {
            return false;
        }
        c0 c0Var = (c0) obj;
        return this.f57a.equals(c0Var.f57a) && this.f58b == c0Var.f58b && this.f59c == c0Var.f59c;
    }

    public final int hashCode() {
        return (this.f57a.hashCode() ^ (this.f58b * 100000)) ^ this.f59c;
    }

    public String toString() {
        return this.f57a + '/' + Integer.toString(this.f58b) + '.' + Integer.toString(this.f59c);
    }
}

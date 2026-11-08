package c.a.a.a.s0;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class b implements c.a.a.a.e, Cloneable, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f359a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f360b;

    public b(String str, String str2) {
        c.a.a.a.x0.a.a(str, "Name");
        this.f359a = str;
        this.f360b = str2;
    }

    @Override // c.a.a.a.e
    public c.a.a.a.f[] a() {
        String str = this.f360b;
        return str != null ? g.a(str, (s) null) : new c.a.a.a.f[0];
    }

    public Object clone() {
        return super.clone();
    }

    @Override // c.a.a.a.e
    public String getName() {
        return this.f359a;
    }

    @Override // c.a.a.a.e
    public String getValue() {
        return this.f360b;
    }

    public String toString() {
        return j.f376a.a((c.a.a.a.x0.d) null, this).toString();
    }
}

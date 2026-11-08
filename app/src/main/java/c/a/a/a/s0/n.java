package c.a.a.a.s0;

import c.a.a.a.c0;
import c.a.a.a.e0;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class n implements e0, Cloneable, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c0 f384a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f385b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f386c;

    public n(String str, String str2, c0 c0Var) {
        c.a.a.a.x0.a.a(str, "Method");
        this.f385b = str;
        c.a.a.a.x0.a.a(str2, "URI");
        this.f386c = str2;
        c.a.a.a.x0.a.a(c0Var, "Version");
        this.f384a = c0Var;
    }

    @Override // c.a.a.a.e0
    public c0 a() {
        return this.f384a;
    }

    @Override // c.a.a.a.e0
    public String b() {
        return this.f385b;
    }

    @Override // c.a.a.a.e0
    public String c() {
        return this.f386c;
    }

    public Object clone() {
        return super.clone();
    }

    public String toString() {
        return j.f376a.a((c.a.a.a.x0.d) null, this).toString();
    }
}

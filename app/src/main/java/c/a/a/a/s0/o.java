package c.a.a.a.s0;

import c.a.a.a.c0;
import c.a.a.a.f0;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class o implements f0, Cloneable, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c0 f387a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f388b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f389c;

    public o(c0 c0Var, int i, String str) {
        c.a.a.a.x0.a.a(c0Var, "Version");
        this.f387a = c0Var;
        c.a.a.a.x0.a.a(i, "Status code");
        this.f388b = i;
        this.f389c = str;
    }

    @Override // c.a.a.a.f0
    public c0 a() {
        return this.f387a;
    }

    @Override // c.a.a.a.f0
    public String b() {
        return this.f389c;
    }

    @Override // c.a.a.a.f0
    public int c() {
        return this.f388b;
    }

    public Object clone() {
        return super.clone();
    }

    public String toString() {
        return j.f376a.b((c.a.a.a.x0.d) null, this).toString();
    }
}

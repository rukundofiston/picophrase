package c.a.a.a.s0;

import c.a.a.a.c0;
import c.a.a.a.e0;

/* JADX INFO: loaded from: classes.dex */
public class h extends a implements c.a.a.a.q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f374c;
    private final String d;
    private e0 e;

    public h(e0 e0Var) {
        c.a.a.a.x0.a.a(e0Var, "Request line");
        this.e = e0Var;
        this.f374c = e0Var.b();
        this.d = e0Var.c();
    }

    public h(String str, String str2, c0 c0Var) {
        this(new n(str, str2, c0Var));
    }

    @Override // c.a.a.a.p
    public c0 a() {
        return g().a();
    }

    @Override // c.a.a.a.q
    public e0 g() {
        if (this.e == null) {
            this.e = new n(this.f374c, this.d, c.a.a.a.v.f);
        }
        return this.e;
    }

    public String toString() {
        return this.f374c + ' ' + this.d + ' ' + this.f357a;
    }
}

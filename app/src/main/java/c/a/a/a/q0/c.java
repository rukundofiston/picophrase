package c.a.a.a.q0;

import c.a.a.a.d0;
import c.a.a.a.f0;
import c.a.a.a.s;
import c.a.a.a.s0.i;
import c.a.a.a.t;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class c implements t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f191b = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final d0 f192a;

    public c() {
        this(d.f193a);
    }

    public c(d0 d0Var) {
        c.a.a.a.x0.a.a(d0Var, "Reason phrase catalog");
        this.f192a = d0Var;
    }

    @Override // c.a.a.a.t
    public s a(f0 f0Var, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(f0Var, "Status line");
        return new i(f0Var, this.f192a, a(eVar));
    }

    protected Locale a(c.a.a.a.v0.e eVar) {
        return Locale.getDefault();
    }
}

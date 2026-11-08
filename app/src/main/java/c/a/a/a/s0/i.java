package c.a.a.a.s0;

import c.a.a.a.c0;
import c.a.a.a.d0;
import c.a.a.a.f0;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class i extends a implements c.a.a.a.s {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private f0 f375c;
    private c0 d;
    private int e;
    private String f;
    private c.a.a.a.k g;
    private final d0 h;
    private Locale i;

    public i(f0 f0Var, d0 d0Var, Locale locale) {
        c.a.a.a.x0.a.a(f0Var, "Status line");
        this.f375c = f0Var;
        this.d = f0Var.a();
        this.e = f0Var.c();
        this.f = f0Var.b();
        this.h = d0Var;
        this.i = locale;
    }

    @Override // c.a.a.a.p
    public c0 a() {
        return this.d;
    }

    protected String a(int i) {
        d0 d0Var = this.h;
        if (d0Var == null) {
            return null;
        }
        Locale locale = this.i;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return d0Var.a(i, locale);
    }

    @Override // c.a.a.a.s
    public void a(c.a.a.a.k kVar) {
        this.g = kVar;
    }

    @Override // c.a.a.a.s
    public c.a.a.a.k c() {
        return this.g;
    }

    @Override // c.a.a.a.s
    public f0 j() {
        if (this.f375c == null) {
            c0 c0Var = this.d;
            if (c0Var == null) {
                c0Var = c.a.a.a.v.f;
            }
            int i = this.e;
            String strA = this.f;
            if (strA == null) {
                strA = a(i);
            }
            this.f375c = new o(c0Var, i, strA);
        }
        return this.f375c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(j());
        sb.append(' ');
        sb.append(this.f357a);
        if (this.g != null) {
            sb.append(' ');
            sb.append(this.g);
        }
        return sb.toString();
    }
}

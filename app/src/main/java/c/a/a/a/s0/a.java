package c.a.a.a.s0;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements c.a.a.a.p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected r f357a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    protected c.a.a.a.t0.e f358b;

    protected a() {
        this(null);
    }

    @Deprecated
    protected a(c.a.a.a.t0.e eVar) {
        this.f357a = new r();
        this.f358b = eVar;
    }

    @Override // c.a.a.a.p
    public void a(c.a.a.a.e eVar) {
        this.f357a.a(eVar);
    }

    @Override // c.a.a.a.p
    @Deprecated
    public void a(c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        this.f358b = eVar;
    }

    @Override // c.a.a.a.p
    public void a(String str) {
        if (str == null) {
            return;
        }
        c.a.a.a.h hVarC = this.f357a.c();
        while (hVarC.hasNext()) {
            if (str.equalsIgnoreCase(hVarC.nextHeader().getName())) {
                hVarC.remove();
            }
        }
    }

    @Override // c.a.a.a.p
    public void a(String str, String str2) {
        c.a.a.a.x0.a.a(str, "Header name");
        this.f357a.c(new b(str, str2));
    }

    @Override // c.a.a.a.p
    public void a(c.a.a.a.e[] eVarArr) {
        this.f357a.a(eVarArr);
    }

    @Override // c.a.a.a.p
    public void b(c.a.a.a.e eVar) {
        this.f357a.b(eVar);
    }

    @Override // c.a.a.a.p
    public void b(String str, String str2) {
        c.a.a.a.x0.a.a(str, "Header name");
        this.f357a.a(new b(str, str2));
    }

    @Override // c.a.a.a.p
    public c.a.a.a.e[] b(String str) {
        return this.f357a.c(str);
    }

    @Override // c.a.a.a.p
    public boolean c(String str) {
        return this.f357a.a(str);
    }

    @Override // c.a.a.a.p
    public c.a.a.a.e d(String str) {
        return this.f357a.b(str);
    }

    @Override // c.a.a.a.p
    public c.a.a.a.h d() {
        return this.f357a.c();
    }

    @Override // c.a.a.a.p
    public c.a.a.a.h e(String str) {
        return this.f357a.d(str);
    }

    @Override // c.a.a.a.p
    @Deprecated
    public c.a.a.a.t0.e k() {
        if (this.f358b == null) {
            this.f358b = new c.a.a.a.t0.b();
        }
        return this.f358b;
    }

    @Override // c.a.a.a.p
    public c.a.a.a.e[] l() {
        return this.f357a.b();
    }
}

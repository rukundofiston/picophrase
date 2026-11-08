package c.a.a.a.v0;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class c implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e f410a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f411b;

    public c(e eVar, e eVar2) {
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        this.f410a = eVar;
        this.f411b = eVar2;
    }

    @Override // c.a.a.a.v0.e
    public Object a(String str) {
        Object objA = this.f410a.a(str);
        return objA == null ? this.f411b.a(str) : objA;
    }

    @Override // c.a.a.a.v0.e
    public void a(String str, Object obj) {
        this.f410a.a(str, obj);
    }

    public String toString() {
        return "[local: " + this.f410a + "defaults: " + this.f411b + "]";
    }
}

package c.a.a.a.q0.h;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class g extends c.a.a.a.t0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final c.a.a.a.t0.e f231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final c.a.a.a.t0.e f232b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final c.a.a.a.t0.e f233c;
    protected final c.a.a.a.t0.e d;

    public g(c.a.a.a.t0.e eVar, c.a.a.a.t0.e eVar2, c.a.a.a.t0.e eVar3, c.a.a.a.t0.e eVar4) {
        this.f231a = eVar;
        this.f232b = eVar2;
        this.f233c = eVar3;
        this.d = eVar4;
    }

    @Override // c.a.a.a.t0.e
    public c.a.a.a.t0.e a(String str, Object obj) {
        throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
    }

    @Override // c.a.a.a.t0.e
    public Object b(String str) {
        c.a.a.a.t0.e eVar;
        c.a.a.a.t0.e eVar2;
        c.a.a.a.t0.e eVar3;
        c.a.a.a.x0.a.a(str, "Parameter name");
        c.a.a.a.t0.e eVar4 = this.d;
        Object objB = eVar4 != null ? eVar4.b(str) : null;
        if (objB == null && (eVar3 = this.f233c) != null) {
            objB = eVar3.b(str);
        }
        if (objB == null && (eVar2 = this.f232b) != null) {
            objB = eVar2.b(str);
        }
        return (objB != null || (eVar = this.f231a) == null) ? objB : eVar.b(str);
    }
}

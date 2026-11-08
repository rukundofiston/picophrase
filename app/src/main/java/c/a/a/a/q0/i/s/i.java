package c.a.a.a.q0.i.s;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private h f298a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f299b;

    public void a() {
        this.f299b = true;
        h hVar = this.f298a;
        if (hVar != null) {
            hVar.a();
        }
    }

    public void a(h hVar) {
        this.f298a = hVar;
        if (this.f299b) {
            hVar.a();
        }
    }
}

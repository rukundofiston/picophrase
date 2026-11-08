package c.a.a.a.q0.j;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements c.a.a.a.n0.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<String, c.a.a.a.n0.d> f300a;

    public b() {
        this.f300a = new ConcurrentHashMap(10);
    }

    protected b(c.a.a.a.n0.b... bVarArr) {
        this.f300a = new ConcurrentHashMap(bVarArr.length);
        for (c.a.a.a.n0.b bVar : bVarArr) {
            this.f300a.put(bVar.a(), bVar);
        }
    }

    protected c.a.a.a.n0.d a(String str) {
        return this.f300a.get(str);
    }

    protected Collection<c.a.a.a.n0.d> c() {
        return this.f300a.values();
    }
}

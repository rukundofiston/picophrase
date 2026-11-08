package c.a.a.a.q0.h;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class f implements c.a.a.a.j0.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<c.a.a.a.i0.g, c.a.a.a.i0.m> f230a = new ConcurrentHashMap<>();

    private static c.a.a.a.i0.m a(Map<c.a.a.a.i0.g, c.a.a.a.i0.m> map, c.a.a.a.i0.g gVar) {
        c.a.a.a.i0.m mVar = map.get(gVar);
        if (mVar != null) {
            return mVar;
        }
        int i = -1;
        c.a.a.a.i0.g gVar2 = null;
        for (c.a.a.a.i0.g gVar3 : map.keySet()) {
            int iA = gVar.a(gVar3);
            if (iA > i) {
                gVar2 = gVar3;
                i = iA;
            }
        }
        return gVar2 != null ? map.get(gVar2) : mVar;
    }

    @Override // c.a.a.a.j0.i
    public c.a.a.a.i0.m a(c.a.a.a.i0.g gVar) {
        c.a.a.a.x0.a.a(gVar, "Authentication scope");
        return a(this.f230a, gVar);
    }

    public String toString() {
        return this.f230a.toString();
    }
}

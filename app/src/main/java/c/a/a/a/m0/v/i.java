package c.a.a.a.m0.v;

import c.a.a.a.n;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<String, e> f146a = new ConcurrentHashMap<>();

    public final e a(e eVar) {
        c.a.a.a.x0.a.a(eVar, "Scheme");
        return this.f146a.put(eVar.b(), eVar);
    }

    public final e a(n nVar) {
        c.a.a.a.x0.a.a(nVar, "Host");
        return b(nVar.d());
    }

    public final e a(String str) {
        c.a.a.a.x0.a.a(str, "Scheme name");
        return this.f146a.get(str);
    }

    public final e b(String str) {
        e eVarA = a(str);
        if (eVarA != null) {
            return eVarA;
        }
        throw new IllegalStateException("Scheme '" + str + "' not registered.");
    }
}

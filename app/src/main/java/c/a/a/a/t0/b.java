package c.a.a.a.t0;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends a implements Serializable, Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<String, Object> f402a = new ConcurrentHashMap();

    @Override // c.a.a.a.t0.e
    public e a(String str, Object obj) {
        if (str == null) {
            return this;
        }
        if (obj != null) {
            this.f402a.put(str, obj);
        } else {
            this.f402a.remove(str);
        }
        return this;
    }

    public void a(e eVar) {
        for (Map.Entry<String, Object> entry : this.f402a.entrySet()) {
            eVar.a(entry.getKey(), entry.getValue());
        }
    }

    @Override // c.a.a.a.t0.e
    public Object b(String str) {
        return this.f402a.get(str);
    }

    public Object clone() {
        b bVar = (b) super.clone();
        a(bVar);
        return bVar;
    }
}

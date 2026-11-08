package c.a.a.a.v0;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class a implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e f406a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<String, Object> f407b;

    public a() {
        this(null);
    }

    public a(e eVar) {
        this.f407b = new ConcurrentHashMap();
        this.f406a = eVar;
    }

    @Override // c.a.a.a.v0.e
    public Object a(String str) {
        e eVar;
        c.a.a.a.x0.a.a(str, "Id");
        Object obj = this.f407b.get(str);
        return (obj != null || (eVar = this.f406a) == null) ? obj : eVar.a(str);
    }

    @Override // c.a.a.a.v0.e
    public void a(String str, Object obj) {
        c.a.a.a.x0.a.a(str, "Id");
        if (obj != null) {
            this.f407b.put(str, obj);
        } else {
            this.f407b.remove(str);
        }
    }

    public String toString() {
        return this.f407b.toString();
    }
}

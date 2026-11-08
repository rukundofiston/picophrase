package a.a;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends d<K, V> implements Map<K, V> {
    c<K, V> h;

    /* JADX INFO: renamed from: a.a.a$a, reason: collision with other inner class name */
    class C0000a extends c<K, V> {
        final a<K, V> outer;

        C0000a(a<K, V> outer) {
            this.outer = outer;
        }

        @Override // a.a.c
        protected int a(Object obj) {
            return this.outer.a(obj);
        }

        @Override // a.a.c
        protected Object a(int i, int i2) {
            return this.outer.f15b[(i << 1) + i2];
        }

        @Override // a.a.c
        protected V a(int i, V v) {
            return this.outer.a(i, v);
        }

        @Override // a.a.c
        protected void a() {
            this.outer.clear();
        }

        @Override // a.a.c
        protected void a(int i) {
            this.outer.c(i);
        }

        @Override // a.a.c
        protected void a(K k, V v) {
            this.outer.put(k, v);
        }

        @Override // a.a.c
        protected int b(Object obj) {
            return this.outer.b(obj);
        }

        @Override // a.a.c
        protected Map<K, V> b() {
            return this.outer;
        }

        @Override // a.a.c
        protected int c() {
            return this.outer.f16c;
        }
    }

    private c<K, V> b() {
        if (this.h == null) {
            this.h = new C0000a(this);
        }
        return this.h;
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return b().d();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return b().e();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        a(this.f16c + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return b().f();
    }
}

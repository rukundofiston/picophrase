package c.a.a.a.q0.h;

import java.net.URI;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class u extends AbstractList<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<URI> f246a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<URI> f247b = new ArrayList();

    public void a(URI uri) {
        this.f246a.add(uri);
        this.f247b.add(uri);
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, Object obj) {
        URI uri = (URI) obj;
        this.f247b.add(i, uri);
        this.f246a.add(uri);
    }

    public boolean b(URI uri) {
        return this.f246a.contains(uri);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return this.f246a.contains(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public URI get(int i) {
        return this.f247b.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    public URI remove(int i) {
        URI uriRemove = this.f247b.remove(i);
        this.f246a.remove(uriRemove);
        if (this.f247b.size() != this.f246a.size()) {
            this.f246a.addAll(this.f247b);
        }
        return uriRemove;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i, Object obj) {
        URI uri = (URI) obj;
        URI uri2 = this.f247b.set(i, uri);
        this.f246a.remove(uri2);
        this.f246a.add(uri);
        if (this.f247b.size() != this.f246a.size()) {
            this.f246a.addAll(this.f247b);
        }
        return uri2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.f247b.size();
    }
}

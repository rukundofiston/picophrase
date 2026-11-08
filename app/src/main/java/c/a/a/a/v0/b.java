package c.a.a.a.v0;

import c.a.a.a.q;
import c.a.a.a.r;
import c.a.a.a.s;
import c.a.a.a.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class b implements g, Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final List<r> f408a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final List<u> f409b = new ArrayList();

    public int a() {
        return this.f408a.size();
    }

    public r a(int i) {
        if (i < 0 || i >= this.f408a.size()) {
            return null;
        }
        return this.f408a.get(i);
    }

    @Override // c.a.a.a.r
    public void a(q qVar, e eVar) {
        Iterator<r> it = this.f408a.iterator();
        while (it.hasNext()) {
            it.next().a(qVar, eVar);
        }
    }

    public final void a(r rVar) {
        b(rVar);
    }

    public final void a(r rVar, int i) {
        b(rVar, i);
    }

    @Override // c.a.a.a.u
    public void a(s sVar, e eVar) {
        Iterator<u> it = this.f409b.iterator();
        while (it.hasNext()) {
            it.next().a(sVar, eVar);
        }
    }

    public final void a(u uVar) {
        b(uVar);
    }

    protected void a(b bVar) {
        bVar.f408a.clear();
        bVar.f408a.addAll(this.f408a);
        bVar.f409b.clear();
        bVar.f409b.addAll(this.f409b);
    }

    public int b() {
        return this.f409b.size();
    }

    public u b(int i) {
        if (i < 0 || i >= this.f409b.size()) {
            return null;
        }
        return this.f409b.get(i);
    }

    public void b(r rVar) {
        if (rVar == null) {
            return;
        }
        this.f408a.add(rVar);
    }

    public void b(r rVar, int i) {
        if (rVar == null) {
            return;
        }
        this.f408a.add(i, rVar);
    }

    public void b(u uVar) {
        if (uVar == null) {
            return;
        }
        this.f409b.add(uVar);
    }

    public Object clone() {
        b bVar = (b) super.clone();
        a(bVar);
        return bVar;
    }
}

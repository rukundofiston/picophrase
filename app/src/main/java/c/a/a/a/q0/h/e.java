package c.a.a.a.q0.h;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class e implements c.a.a.a.j0.h, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TreeSet<c.a.a.a.n0.c> f229a = new TreeSet<>(new c.a.a.a.n0.e());

    @Override // c.a.a.a.j0.h
    public synchronized List<c.a.a.a.n0.c> a() {
        return new ArrayList(this.f229a);
    }

    @Override // c.a.a.a.j0.h
    public synchronized void a(c.a.a.a.n0.c cVar) {
        if (cVar != null) {
            this.f229a.remove(cVar);
            if (!cVar.a(new Date())) {
                this.f229a.add(cVar);
            }
        }
    }

    @Override // c.a.a.a.j0.h
    public synchronized boolean a(Date date) {
        boolean z = false;
        if (date == null) {
            return false;
        }
        Iterator<c.a.a.a.n0.c> it = this.f229a.iterator();
        while (it.hasNext()) {
            if (it.next().a(date)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public synchronized String toString() {
        return this.f229a.toString();
    }
}

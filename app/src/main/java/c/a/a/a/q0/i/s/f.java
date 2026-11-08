package c.a.a.a.q0.i.s;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final c.a.a.a.m0.u.b f287b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final int f288c;
    protected final c.a.a.a.m0.t.b d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f286a = new c.a.a.a.p0.b(f.class);
    protected final LinkedList<b> e = new LinkedList<>();
    protected final Queue<h> f = new LinkedList();
    protected int g = 0;

    public f(c.a.a.a.m0.u.b bVar, c.a.a.a.m0.t.b bVar2) {
        this.f287b = bVar;
        this.d = bVar2;
        this.f288c = bVar2.a(bVar);
    }

    public b a(Object obj) {
        if (!this.e.isEmpty()) {
            LinkedList<b> linkedList = this.e;
            ListIterator<b> listIterator = linkedList.listIterator(linkedList.size());
            while (listIterator.hasPrevious()) {
                b bVarPrevious = listIterator.previous();
                if (bVarPrevious.a() == null || c.a.a.a.x0.h.a(obj, bVarPrevious.a())) {
                    listIterator.remove();
                    return bVarPrevious;
                }
            }
        }
        if (b() != 0 || this.e.isEmpty()) {
            return null;
        }
        b bVarRemove = this.e.remove();
        bVarRemove.b();
        try {
            bVarRemove.c().close();
        } catch (IOException e) {
            this.f286a.a("I/O error closing connection", e);
        }
        return bVarRemove;
    }

    public void a() {
        c.a.a.a.x0.b.a(this.g > 0, "There is no entry that could be dropped");
        this.g--;
    }

    public void a(b bVar) {
        c.a.a.a.x0.a.a(this.f287b.equals(bVar.d()), "Entry not planned for this pool");
        this.g++;
    }

    public void a(h hVar) {
        c.a.a.a.x0.a.a(hVar, "Waiting thread");
        this.f.add(hVar);
    }

    public int b() {
        return this.d.a(this.f287b) - this.g;
    }

    public void b(h hVar) {
        if (hVar == null) {
            return;
        }
        this.f.remove(hVar);
    }

    public boolean b(b bVar) {
        boolean zRemove = this.e.remove(bVar);
        if (zRemove) {
            this.g--;
        }
        return zRemove;
    }

    public final int c() {
        return this.f288c;
    }

    public void c(b bVar) {
        int i = this.g;
        if (i < 1) {
            throw new IllegalStateException("No entry created for this pool. " + this.f287b);
        }
        if (i > this.e.size()) {
            this.e.add(bVar);
            return;
        }
        throw new IllegalStateException("No entry allocated from this pool. " + this.f287b);
    }

    public final c.a.a.a.m0.u.b d() {
        return this.f287b;
    }

    public boolean e() {
        return !this.f.isEmpty();
    }

    public boolean f() {
        return this.g < 1 && this.f.isEmpty();
    }

    public h g() {
        return this.f.peek();
    }
}

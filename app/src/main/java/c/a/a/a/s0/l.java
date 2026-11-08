package c.a.a.a.s0;

import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class l implements c.a.a.a.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final List<c.a.a.a.e> f379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int f380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f381c;
    protected String d;

    public l(List<c.a.a.a.e> list, String str) {
        c.a.a.a.x0.a.a(list, "Header list");
        this.f379a = list;
        this.d = str;
        this.f380b = b(-1);
        this.f381c = -1;
    }

    protected boolean a(int i) {
        if (this.d == null) {
            return true;
        }
        return this.d.equalsIgnoreCase(this.f379a.get(i).getName());
    }

    protected int b(int i) {
        if (i < -1) {
            return -1;
        }
        int size = this.f379a.size() - 1;
        boolean zA = false;
        while (!zA && i < size) {
            i++;
            zA = a(i);
        }
        if (zA) {
            return i;
        }
        return -1;
    }

    @Override // c.a.a.a.h, java.util.Iterator
    public boolean hasNext() {
        return this.f380b >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return nextHeader();
    }

    @Override // c.a.a.a.h
    public c.a.a.a.e nextHeader() {
        int i = this.f380b;
        if (i < 0) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        this.f381c = i;
        this.f380b = b(i);
        return this.f379a.get(i);
    }

    @Override // java.util.Iterator
    public void remove() {
        c.a.a.a.x0.b.a(this.f381c >= 0, "No header to remove");
        this.f379a.remove(this.f381c);
        this.f381c = -1;
        this.f380b--;
    }
}

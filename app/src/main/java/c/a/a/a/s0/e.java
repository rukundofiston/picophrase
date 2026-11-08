package c.a.a.a.s0;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class e implements c.a.a.a.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final c.a.a.a.e[] f367a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int f368b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected String f369c;

    public e(c.a.a.a.e[] eVarArr, String str) {
        c.a.a.a.x0.a.a(eVarArr, "Header array");
        this.f367a = eVarArr;
        this.f369c = str;
        this.f368b = b(-1);
    }

    protected boolean a(int i) {
        String str = this.f369c;
        return str == null || str.equalsIgnoreCase(this.f367a[i].getName());
    }

    protected int b(int i) {
        if (i < -1) {
            return -1;
        }
        int length = this.f367a.length - 1;
        boolean zA = false;
        while (!zA && i < length) {
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
        return this.f368b >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return nextHeader();
    }

    @Override // c.a.a.a.h
    public c.a.a.a.e nextHeader() {
        int i = this.f368b;
        if (i < 0) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        this.f368b = b(i);
        return this.f367a[i];
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Removing headers is not supported.");
    }
}

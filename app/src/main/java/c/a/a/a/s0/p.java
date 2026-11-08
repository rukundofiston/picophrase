package c.a.a.a.s0;

import c.a.a.a.a0;
import c.a.a.a.g0;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class p implements g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final c.a.a.a.h f390a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected String f391b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected String f392c;
    protected int d;

    public p(c.a.a.a.h hVar) {
        c.a.a.a.x0.a.a(hVar, "Header iterator");
        this.f390a = hVar;
        this.d = a(-1);
    }

    protected int a(int i) {
        int iC;
        String strA;
        int iB = -1;
        if (i >= 0) {
            iC = c(i);
        } else {
            if (!this.f390a.hasNext()) {
                return -1;
            }
            this.f391b = this.f390a.nextHeader().getValue();
            iC = 0;
        }
        int iD = d(iC);
        if (iD < 0) {
            strA = null;
        } else {
            iB = b(iD);
            strA = a(this.f391b, iD, iB);
        }
        this.f392c = strA;
        return iB;
    }

    protected String a(String str, int i, int i2) {
        return str.substring(i, i2);
    }

    protected boolean a(char c2) {
        return " ,;=()<>@:\\\"/[]?{}\t".indexOf(c2) >= 0;
    }

    protected int b(int i) {
        c.a.a.a.x0.a.a(i, "Search position");
        int length = this.f391b.length();
        do {
            i++;
            if (i >= length) {
                break;
            }
        } while (b(this.f391b.charAt(i)));
        return i;
    }

    protected boolean b(char c2) {
        if (Character.isLetterOrDigit(c2)) {
            return true;
        }
        return (Character.isISOControl(c2) || a(c2)) ? false : true;
    }

    protected int c(int i) {
        c.a.a.a.x0.a.a(i, "Search position");
        int length = this.f391b.length();
        boolean z = false;
        while (!z && i < length) {
            char cCharAt = this.f391b.charAt(i);
            if (c(cCharAt)) {
                z = true;
            } else {
                if (!d(cCharAt)) {
                    if (b(cCharAt)) {
                        throw new a0("Tokens without separator (pos " + i + "): " + this.f391b);
                    }
                    throw new a0("Invalid character after token (pos " + i + "): " + this.f391b);
                }
                i++;
            }
        }
        return i;
    }

    protected boolean c(char c2) {
        return c2 == ',';
    }

    protected int d(int i) {
        c.a.a.a.x0.a.a(i, "Search position");
        boolean z = false;
        while (!z) {
            String str = this.f391b;
            if (str == null) {
                break;
            }
            int length = str.length();
            while (!z && i < length) {
                char cCharAt = this.f391b.charAt(i);
                if (c(cCharAt) || d(cCharAt)) {
                    i++;
                } else {
                    if (!b(this.f391b.charAt(i))) {
                        throw new a0("Invalid character before token (pos " + i + "): " + this.f391b);
                    }
                    z = true;
                }
            }
            if (!z) {
                if (this.f390a.hasNext()) {
                    this.f391b = this.f390a.nextHeader().getValue();
                    i = 0;
                } else {
                    this.f391b = null;
                }
            }
        }
        if (z) {
            return i;
        }
        return -1;
    }

    protected boolean d(char c2) {
        return c2 == '\t' || Character.isSpaceChar(c2);
    }

    @Override // c.a.a.a.g0, java.util.Iterator
    public boolean hasNext() {
        return this.f392c != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return nextToken();
    }

    @Override // c.a.a.a.g0
    public String nextToken() {
        String str = this.f392c;
        if (str == null) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        this.d = a(this.d);
        return str;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Removing tokens is not supported.");
    }
}

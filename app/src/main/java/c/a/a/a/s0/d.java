package c.a.a.a.s0;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class d implements c.a.a.a.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.h f364a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final s f365b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c.a.a.a.f f366c;
    private c.a.a.a.x0.d d;
    private v e;

    public d(c.a.a.a.h hVar) {
        this(hVar, g.f371b);
    }

    public d(c.a.a.a.h hVar, s sVar) {
        this.f366c = null;
        this.d = null;
        this.e = null;
        c.a.a.a.x0.a.a(hVar, "Header iterator");
        this.f364a = hVar;
        c.a.a.a.x0.a.a(sVar, "Parser");
        this.f365b = sVar;
    }

    private void a() {
        this.e = null;
        this.d = null;
        while (this.f364a.hasNext()) {
            c.a.a.a.e eVarNextHeader = this.f364a.nextHeader();
            if (eVarNextHeader instanceof c.a.a.a.d) {
                c.a.a.a.d dVar = (c.a.a.a.d) eVarNextHeader;
                c.a.a.a.x0.d dVarC = dVar.c();
                this.d = dVarC;
                v vVar = new v(0, dVarC.length());
                this.e = vVar;
                vVar.a(dVar.b());
                return;
            }
            String value = eVarNextHeader.getValue();
            if (value != null) {
                c.a.a.a.x0.d dVar2 = new c.a.a.a.x0.d(value.length());
                this.d = dVar2;
                dVar2.a(value);
                this.e = new v(0, this.d.length());
                return;
            }
        }
    }

    private void b() {
        while (true) {
            if (!this.f364a.hasNext() && this.e == null) {
                return;
            }
            v vVar = this.e;
            if (vVar == null || vVar.a()) {
                a();
            }
            if (this.e != null) {
                while (!this.e.a()) {
                    c.a.a.a.f fVarB = this.f365b.b(this.d, this.e);
                    if (fVarB.getName().length() != 0 || fVarB.getValue() != null) {
                        this.f366c = fVarB;
                        return;
                    }
                }
                if (this.e.a()) {
                    this.e = null;
                    this.d = null;
                }
            }
        }
    }

    @Override // c.a.a.a.g, java.util.Iterator
    public boolean hasNext() {
        if (this.f366c == null) {
            b();
        }
        return this.f366c != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return nextElement();
    }

    @Override // c.a.a.a.g
    public c.a.a.a.f nextElement() {
        if (this.f366c == null) {
            b();
        }
        c.a.a.a.f fVar = this.f366c;
        if (fVar == null) {
            throw new NoSuchElementException("No more header elements available");
        }
        this.f366c = null;
        return fVar;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}

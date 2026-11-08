package c.a.a.a.q0.l;

import c.a.a.a.a0;
import c.a.a.a.b0;
import c.a.a.a.p;
import c.a.a.a.s0.u;
import c.a.a.a.x;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<T extends p> implements c.a.a.a.r0.c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.r0.f f327a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.l0.b f328b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<c.a.a.a.x0.d> f329c;
    protected final u d;
    private int e;
    private T f;

    @Deprecated
    public a(c.a.a.a.r0.f fVar, u uVar, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(fVar, "Session input buffer");
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        this.f327a = fVar;
        this.f328b = c.a.a.a.t0.d.a(eVar);
        this.d = uVar == null ? c.a.a.a.s0.k.f377b : uVar;
        this.f329c = new ArrayList();
        this.e = 0;
    }

    public static c.a.a.a.e[] a(c.a.a.a.r0.f fVar, int i, int i2, u uVar) throws x, b0 {
        ArrayList arrayList = new ArrayList();
        if (uVar == null) {
            uVar = c.a.a.a.s0.k.f377b;
        }
        return a(fVar, i, i2, uVar, arrayList);
    }

    public static c.a.a.a.e[] a(c.a.a.a.r0.f fVar, int i, int i2, u uVar, List<c.a.a.a.x0.d> list) throws x, b0 {
        int i3;
        char cCharAt;
        c.a.a.a.x0.a.a(fVar, "Session input buffer");
        c.a.a.a.x0.a.a(uVar, "Line parser");
        c.a.a.a.x0.a.a(list, "Header line list");
        c.a.a.a.x0.d dVar = null;
        c.a.a.a.x0.d dVar2 = null;
        while (true) {
            if (dVar == null) {
                dVar = new c.a.a.a.x0.d(64);
            } else {
                dVar.b();
            }
            i3 = 0;
            if (fVar.a(dVar) == -1 || dVar.length() < 1) {
                break;
            }
            if ((dVar.charAt(0) == ' ' || dVar.charAt(0) == '\t') && dVar2 != null) {
                while (i3 < dVar.length() && ((cCharAt = dVar.charAt(i3)) == ' ' || cCharAt == '\t')) {
                    i3++;
                }
                if (i2 > 0 && ((dVar2.length() + 1) + dVar.length()) - i3 > i2) {
                    throw new x("Maximum line length limit exceeded");
                }
                dVar2.a(' ');
                dVar2.a(dVar, i3, dVar.length() - i3);
            } else {
                list.add(dVar);
                dVar2 = dVar;
                dVar = null;
            }
            if (i > 0 && list.size() >= i) {
                throw new x("Maximum header count exceeded");
            }
        }
        c.a.a.a.e[] eVarArr = new c.a.a.a.e[list.size()];
        while (i3 < list.size()) {
            try {
                eVarArr[i3] = uVar.a(list.get(i3));
                i3++;
            } catch (a0 e) {
                throw new b0(e.getMessage());
            }
        }
        return eVarArr;
    }

    @Override // c.a.a.a.r0.c
    public T a() throws x, b0 {
        int i = this.e;
        if (i == 0) {
            try {
                this.f = (T) a(this.f327a);
                this.e = 1;
            } catch (a0 e) {
                throw new b0(e.getMessage(), e);
            }
        } else if (i != 1) {
            throw new IllegalStateException("Inconsistent parser state");
        }
        this.f.a(a(this.f327a, this.f328b.a(), this.f328b.b(), this.d, this.f329c));
        T t = this.f;
        this.f = null;
        this.f329c.clear();
        this.e = 0;
        return t;
    }

    protected abstract T a(c.a.a.a.r0.f fVar);
}

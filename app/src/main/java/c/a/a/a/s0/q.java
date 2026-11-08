package c.a.a.a.s0;

import c.a.a.a.a0;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class q implements c.a.a.a.d, Cloneable, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f393a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.x0.d f394b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f395c;

    public q(c.a.a.a.x0.d dVar) {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        int iB = dVar.b(58);
        if (iB == -1) {
            throw new a0("Invalid header: " + dVar.toString());
        }
        String strB = dVar.b(0, iB);
        if (strB.length() != 0) {
            this.f394b = dVar;
            this.f393a = strB;
            this.f395c = iB + 1;
        } else {
            throw new a0("Invalid header: " + dVar.toString());
        }
    }

    @Override // c.a.a.a.e
    public c.a.a.a.f[] a() {
        v vVar = new v(0, this.f394b.length());
        vVar.a(this.f395c);
        return g.f371b.a(this.f394b, vVar);
    }

    @Override // c.a.a.a.d
    public int b() {
        return this.f395c;
    }

    @Override // c.a.a.a.d
    public c.a.a.a.x0.d c() {
        return this.f394b;
    }

    public Object clone() {
        return super.clone();
    }

    @Override // c.a.a.a.e
    public String getName() {
        return this.f393a;
    }

    @Override // c.a.a.a.e
    public String getValue() {
        c.a.a.a.x0.d dVar = this.f394b;
        return dVar.b(this.f395c, dVar.length());
    }

    public String toString() {
        return this.f394b.toString();
    }
}

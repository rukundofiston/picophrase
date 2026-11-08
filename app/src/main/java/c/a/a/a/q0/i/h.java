package c.a.a.a.q0.i;

import c.a.a.a.b0;
import c.a.a.a.s;
import c.a.a.a.s0.u;
import c.a.a.a.s0.v;
import c.a.a.a.t;
import c.a.a.a.z;

/* JADX INFO: loaded from: classes.dex */
public class h extends c.a.a.a.q0.l.a<s> {
    public c.a.a.a.p0.b g;
    private final t h;
    private final c.a.a.a.x0.d i;

    @Deprecated
    public h(c.a.a.a.r0.f fVar, u uVar, t tVar, c.a.a.a.t0.e eVar) {
        super(fVar, uVar, eVar);
        this.g = new c.a.a.a.p0.b(h.class);
        c.a.a.a.x0.a.a(tVar, "Response factory");
        this.h = tVar;
        this.i = new c.a.a.a.x0.d(128);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // c.a.a.a.q0.l.a
    public s a(c.a.a.a.r0.f fVar) throws z, b0 {
        int i = 0;
        while (true) {
            this.i.b();
            int iA = fVar.a(this.i);
            if (iA == -1 && i == 0) {
                throw new z("The target server failed to respond");
            }
            v vVar = new v(0, this.i.length());
            if (this.d.a(this.i, vVar)) {
                return this.h.a(this.d.b(this.i, vVar), null);
            }
            if (iA == -1 || a(this.i, i)) {
                throw new b0("The server failed to respond with a valid HTTP response");
            }
            if (this.g.a()) {
                this.g.a("Garbage in response: " + this.i.toString());
            }
            i++;
        }
    }

    protected boolean a(c.a.a.a.x0.d dVar, int i) {
        return false;
    }
}

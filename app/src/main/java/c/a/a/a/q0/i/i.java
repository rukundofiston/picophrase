package c.a.a.a.q0.i;

import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class i implements c.a.a.a.m0.u.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final c.a.a.a.m0.v.i f267a;

    public i(c.a.a.a.m0.v.i iVar) {
        c.a.a.a.x0.a.a(iVar, "Scheme registry");
        this.f267a = iVar;
    }

    @Override // c.a.a.a.m0.u.d
    public c.a.a.a.m0.u.b a(c.a.a.a.n nVar, c.a.a.a.q qVar, c.a.a.a.v0.e eVar) throws c.a.a.a.m {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.m0.u.b bVarB = c.a.a.a.m0.t.d.b(qVar.k());
        if (bVarB != null) {
            return bVarB;
        }
        c.a.a.a.x0.b.a(nVar, "Target host");
        InetAddress inetAddressC = c.a.a.a.m0.t.d.c(qVar.k());
        c.a.a.a.n nVarA = c.a.a.a.m0.t.d.a(qVar.k());
        try {
            boolean zD = this.f267a.b(nVar.d()).d();
            return nVarA == null ? new c.a.a.a.m0.u.b(nVar, inetAddressC, zD) : new c.a.a.a.m0.u.b(nVar, inetAddressC, nVarA, zD);
        } catch (IllegalStateException e) {
            throw new c.a.a.a.m(e.getMessage());
        }
    }
}

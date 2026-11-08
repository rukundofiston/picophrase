package c.a.a.a.q0.h;

import java.io.Closeable;
import java.net.URI;

/* JADX INFO: loaded from: classes.dex */
public abstract class h implements Closeable {
    public h() {
        new c.a.a.a.p0.b(getClass());
    }

    private static c.a.a.a.n a(c.a.a.a.j0.t.i iVar) throws c.a.a.a.j0.f {
        URI uriH = iVar.h();
        if (!uriH.isAbsolute()) {
            return null;
        }
        c.a.a.a.n nVarA = c.a.a.a.j0.w.d.a(uriH);
        if (nVarA != null) {
            return nVarA;
        }
        throw new c.a.a.a.j0.f("URI does not specify a valid host name: " + uriH);
    }

    public c.a.a.a.j0.t.c a(c.a.a.a.j0.t.i iVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(iVar, "HTTP request");
        return a(a(iVar), iVar, eVar);
    }

    protected abstract c.a.a.a.j0.t.c a(c.a.a.a.n nVar, c.a.a.a.q qVar, c.a.a.a.v0.e eVar);
}

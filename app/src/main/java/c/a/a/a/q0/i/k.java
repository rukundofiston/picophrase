package c.a.a.a.q0.i;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class k extends c.a.a.a.u0.a<c.a.a.a.m0.u.b, c.a.a.a.m0.q> {
    public c.a.a.a.p0.b i;
    private final c.a.a.a.m0.u.f j;

    public k(c.a.a.a.p0.b bVar, String str, c.a.a.a.m0.u.b bVar2, c.a.a.a.m0.q qVar, long j, TimeUnit timeUnit) {
        super(str, bVar2, qVar, j, timeUnit);
        this.i = bVar;
        this.j = new c.a.a.a.m0.u.f(bVar2);
    }

    @Override // c.a.a.a.u0.a
    public boolean a(long j) {
        boolean zA = super.a(j);
        if (zA && this.i.a()) {
            this.i.a("Connection " + this + " expired @ " + new Date(b()));
        }
        return zA;
    }

    public void d() {
        try {
            a().close();
        } catch (IOException e) {
            this.i.a("I/O error closing connection", e);
        }
    }

    c.a.a.a.m0.u.b e() {
        return this.j.j();
    }

    c.a.a.a.m0.u.b f() {
        return c();
    }

    c.a.a.a.m0.u.f g() {
        return this.j;
    }

    public boolean h() {
        return !a().isOpen();
    }
}

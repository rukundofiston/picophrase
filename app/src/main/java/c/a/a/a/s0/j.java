package c.a.a.a.s0;

import c.a.a.a.c0;
import c.a.a.a.e0;
import c.a.a.a.f0;

/* JADX INFO: loaded from: classes.dex */
public class j implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f376a;

    static {
        new j();
        f376a = new j();
    }

    protected int a(c0 c0Var) {
        return c0Var.c().length() + 4;
    }

    protected c.a.a.a.x0.d a(c.a.a.a.x0.d dVar) {
        if (dVar == null) {
            return new c.a.a.a.x0.d(64);
        }
        dVar.b();
        return dVar;
    }

    public c.a.a.a.x0.d a(c.a.a.a.x0.d dVar, c0 c0Var) {
        c.a.a.a.x0.a.a(c0Var, "Protocol version");
        int iA = a(c0Var);
        if (dVar == null) {
            dVar = new c.a.a.a.x0.d(iA);
        } else {
            dVar.a(iA);
        }
        dVar.a(c0Var.c());
        dVar.a('/');
        dVar.a(Integer.toString(c0Var.a()));
        dVar.a('.');
        dVar.a(Integer.toString(c0Var.b()));
        return dVar;
    }

    @Override // c.a.a.a.s0.t
    public c.a.a.a.x0.d a(c.a.a.a.x0.d dVar, e0 e0Var) {
        c.a.a.a.x0.a.a(e0Var, "Request line");
        c.a.a.a.x0.d dVarA = a(dVar);
        b(dVarA, e0Var);
        return dVarA;
    }

    @Override // c.a.a.a.s0.t
    public c.a.a.a.x0.d a(c.a.a.a.x0.d dVar, c.a.a.a.e eVar) {
        c.a.a.a.x0.a.a(eVar, "Header");
        if (eVar instanceof c.a.a.a.d) {
            return ((c.a.a.a.d) eVar).c();
        }
        c.a.a.a.x0.d dVarA = a(dVar);
        b(dVarA, eVar);
        return dVarA;
    }

    protected void a(c.a.a.a.x0.d dVar, f0 f0Var) {
        int iA = a(f0Var.a()) + 1 + 3 + 1;
        String strB = f0Var.b();
        if (strB != null) {
            iA += strB.length();
        }
        dVar.a(iA);
        a(dVar, f0Var.a());
        dVar.a(' ');
        dVar.a(Integer.toString(f0Var.c()));
        dVar.a(' ');
        if (strB != null) {
            dVar.a(strB);
        }
    }

    public c.a.a.a.x0.d b(c.a.a.a.x0.d dVar, f0 f0Var) {
        c.a.a.a.x0.a.a(f0Var, "Status line");
        c.a.a.a.x0.d dVarA = a(dVar);
        a(dVarA, f0Var);
        return dVarA;
    }

    protected void b(c.a.a.a.x0.d dVar, e0 e0Var) {
        String strB = e0Var.b();
        String strC = e0Var.c();
        dVar.a(strB.length() + 1 + strC.length() + 1 + a(e0Var.a()));
        dVar.a(strB);
        dVar.a(' ');
        dVar.a(strC);
        dVar.a(' ');
        a(dVar, e0Var.a());
    }

    protected void b(c.a.a.a.x0.d dVar, c.a.a.a.e eVar) {
        String name = eVar.getName();
        String value = eVar.getValue();
        int length = name.length() + 2;
        if (value != null) {
            length += value.length();
        }
        dVar.a(length);
        dVar.a(name);
        dVar.a(": ");
        if (value != null) {
            dVar.a(value);
        }
    }
}

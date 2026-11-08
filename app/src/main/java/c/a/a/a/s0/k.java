package c.a.a.a.s0;

import c.a.a.a.a0;
import c.a.a.a.c0;
import c.a.a.a.f0;
import org.cocos2dx.lib.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class k implements u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final k f377b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final c0 f378a;

    static {
        new k();
        f377b = new k();
    }

    public k() {
        this(null);
    }

    public k(c0 c0Var) {
        this.f378a = c0Var == null ? c.a.a.a.v.f : c0Var;
    }

    protected c0 a(int i, int i2) {
        return this.f378a.a(i, i2);
    }

    @Override // c.a.a.a.s0.u
    public c.a.a.a.e a(c.a.a.a.x0.d dVar) {
        return new q(dVar);
    }

    protected f0 a(c0 c0Var, int i, String str) {
        return new o(c0Var, i, str);
    }

    @Override // c.a.a.a.s0.u
    public boolean a(c.a.a.a.x0.d dVar, v vVar) {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        c.a.a.a.x0.a.a(vVar, "Parser cursor");
        int iB = vVar.b();
        String strC = this.f378a.c();
        int length = strC.length();
        if (dVar.length() < length + 4) {
            return false;
        }
        if (iB < 0) {
            iB = (dVar.length() - 4) - length;
        } else if (iB == 0) {
            while (iB < dVar.length() && c.a.a.a.v0.d.a(dVar.charAt(iB))) {
                iB++;
            }
        }
        int i = iB + length;
        if (i + 4 > dVar.length()) {
            return false;
        }
        boolean z = true;
        for (int i2 = 0; z && i2 < length; i2++) {
            z = dVar.charAt(iB + i2) == strC.charAt(i2);
        }
        if (z) {
            return dVar.charAt(i) == '/';
        }
        return z;
    }

    @Override // c.a.a.a.s0.u
    public f0 b(c.a.a.a.x0.d dVar, v vVar) {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        c.a.a.a.x0.a.a(vVar, "Parser cursor");
        int iB = vVar.b();
        int iC = vVar.c();
        try {
            c0 c0VarC = c(dVar, vVar);
            d(dVar, vVar);
            int iB2 = vVar.b();
            int iA = dVar.a(32, iB2, iC);
            if (iA < 0) {
                iA = iC;
            }
            String strB = dVar.b(iB2, iA);
            for (int i = 0; i < strB.length(); i++) {
                if (!Character.isDigit(strB.charAt(i))) {
                    throw new a0("Status line contains invalid status code: " + dVar.a(iB, iC));
                }
            }
            try {
                return a(c0VarC, Integer.parseInt(strB), iA < iC ? dVar.b(iA, iC) : BuildConfig.FLAVOR);
            } catch (NumberFormatException unused) {
                throw new a0("Status line contains invalid status code: " + dVar.a(iB, iC));
            }
        } catch (IndexOutOfBoundsException unused2) {
            throw new a0("Invalid status line: " + dVar.a(iB, iC));
        }
    }

    public c0 c(c.a.a.a.x0.d dVar, v vVar) {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        c.a.a.a.x0.a.a(vVar, "Parser cursor");
        String strC = this.f378a.c();
        int length = strC.length();
        int iB = vVar.b();
        int iC = vVar.c();
        d(dVar, vVar);
        int iB2 = vVar.b();
        int i = iB2 + length;
        if (i + 4 > iC) {
            throw new a0("Not a valid protocol version: " + dVar.a(iB, iC));
        }
        boolean z = true;
        for (int i2 = 0; z && i2 < length; i2++) {
            z = dVar.charAt(iB2 + i2) == strC.charAt(i2);
        }
        if (z) {
            z = dVar.charAt(i) == '/';
        }
        if (!z) {
            throw new a0("Not a valid protocol version: " + dVar.a(iB, iC));
        }
        int i3 = iB2 + length + 1;
        int iA = dVar.a(46, i3, iC);
        if (iA == -1) {
            throw new a0("Invalid protocol version number: " + dVar.a(iB, iC));
        }
        try {
            int i4 = Integer.parseInt(dVar.b(i3, iA));
            int i5 = iA + 1;
            int iA2 = dVar.a(32, i5, iC);
            if (iA2 == -1) {
                iA2 = iC;
            }
            try {
                int i6 = Integer.parseInt(dVar.b(i5, iA2));
                vVar.a(iA2);
                return a(i4, i6);
            } catch (NumberFormatException unused) {
                throw new a0("Invalid protocol minor version number: " + dVar.a(iB, iC));
            }
        } catch (NumberFormatException unused2) {
            throw new a0("Invalid protocol major version number: " + dVar.a(iB, iC));
        }
    }

    protected void d(c.a.a.a.x0.d dVar, v vVar) {
        int iB = vVar.b();
        int iC = vVar.c();
        while (iB < iC && c.a.a.a.v0.d.a(dVar.charAt(iB))) {
            iB++;
        }
        vVar.a(iB);
    }
}

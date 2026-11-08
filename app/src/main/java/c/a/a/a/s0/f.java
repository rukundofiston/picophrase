package c.a.a.a.s0;

import c.a.a.a.y;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f370a;

    static {
        new f();
        f370a = new f();
    }

    protected int a(c.a.a.a.f fVar) {
        if (fVar == null) {
            return 0;
        }
        int length = fVar.getName().length();
        String value = fVar.getValue();
        if (value != null) {
            length += value.length() + 3;
        }
        int iB = fVar.b();
        if (iB > 0) {
            for (int i = 0; i < iB; i++) {
                length += a(fVar.a(i)) + 2;
            }
        }
        return length;
    }

    protected int a(y yVar) {
        if (yVar == null) {
            return 0;
        }
        int length = yVar.getName().length();
        String value = yVar.getValue();
        return value != null ? length + value.length() + 3 : length;
    }

    protected int a(y[] yVarArr) {
        if (yVarArr == null || yVarArr.length < 1) {
            return 0;
        }
        int length = (yVarArr.length - 1) * 2;
        for (y yVar : yVarArr) {
            length += a(yVar);
        }
        return length;
    }

    public c.a.a.a.x0.d a(c.a.a.a.x0.d dVar, c.a.a.a.f fVar, boolean z) {
        c.a.a.a.x0.a.a(fVar, "Header element");
        int iA = a(fVar);
        if (dVar == null) {
            dVar = new c.a.a.a.x0.d(iA);
        } else {
            dVar.a(iA);
        }
        dVar.a(fVar.getName());
        String value = fVar.getValue();
        if (value != null) {
            dVar.a('=');
            a(dVar, value, z);
        }
        int iB = fVar.b();
        if (iB > 0) {
            for (int i = 0; i < iB; i++) {
                dVar.a("; ");
                a(dVar, fVar.a(i), z);
            }
        }
        return dVar;
    }

    public c.a.a.a.x0.d a(c.a.a.a.x0.d dVar, y yVar, boolean z) {
        c.a.a.a.x0.a.a(yVar, "Name / value pair");
        int iA = a(yVar);
        if (dVar == null) {
            dVar = new c.a.a.a.x0.d(iA);
        } else {
            dVar.a(iA);
        }
        dVar.a(yVar.getName());
        String value = yVar.getValue();
        if (value != null) {
            dVar.a('=');
            a(dVar, value, z);
        }
        return dVar;
    }

    public c.a.a.a.x0.d a(c.a.a.a.x0.d dVar, y[] yVarArr, boolean z) {
        c.a.a.a.x0.a.a(yVarArr, "Header parameter array");
        int iA = a(yVarArr);
        if (dVar == null) {
            dVar = new c.a.a.a.x0.d(iA);
        } else {
            dVar.a(iA);
        }
        for (int i = 0; i < yVarArr.length; i++) {
            if (i > 0) {
                dVar.a("; ");
            }
            a(dVar, yVarArr[i], z);
        }
        return dVar;
    }

    protected void a(c.a.a.a.x0.d dVar, String str, boolean z) {
        if (!z) {
            for (int i = 0; i < str.length() && !z; i++) {
                z = a(str.charAt(i));
            }
        }
        if (z) {
            dVar.a('\"');
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (b(cCharAt)) {
                dVar.a('\\');
            }
            dVar.a(cCharAt);
        }
        if (z) {
            dVar.a('\"');
        }
    }

    protected boolean a(char c2) {
        return " ;,:@()<>\\\"/[]?={}\t".indexOf(c2) >= 0;
    }

    protected boolean b(char c2) {
        return "\"\\".indexOf(c2) >= 0;
    }
}

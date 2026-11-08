package b.c.a.a;

import c.a.a.a.f0;
import c.a.a.a.s;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends c {
    private String[] f;

    public d(String[] strArr) {
        this.f = new String[]{"application/octet-stream", "image/jpeg", "image/png", "image/gif"};
        if (strArr != null) {
            this.f = strArr;
        } else {
            a.j.a("BinaryHttpRH", "Constructor passed allowedContentTypes was null !");
        }
    }

    @Override // b.c.a.a.c, b.c.a.a.n
    public final void a(s sVar) throws IOException {
        f0 f0VarJ = sVar.j();
        c.a.a.a.e[] eVarArrB = sVar.b("Content-Type");
        if (eVarArrB.length != 1) {
            a(f0VarJ.c(), sVar.l(), null, new c.a.a.a.j0.k(f0VarJ.c(), "None, or more than one, Content-Type Header found!"));
            return;
        }
        c.a.a.a.e eVar = eVarArrB[0];
        boolean z = false;
        for (String str : j()) {
            try {
                if (Pattern.matches(str, eVar.getValue())) {
                    z = true;
                }
            } catch (PatternSyntaxException e) {
                a.j.b("BinaryHttpRH", "Given pattern is not valid: " + str, e);
            }
        }
        if (z) {
            super.a(sVar);
            return;
        }
        a(f0VarJ.c(), sVar.l(), null, new c.a.a.a.j0.k(f0VarJ.c(), "Content-Type (" + eVar.getValue() + ") not allowed!"));
    }

    public String[] j() {
        return this.f;
    }
}

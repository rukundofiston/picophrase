package c.a.a.a.q0.j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class p extends b {
    public p() {
    }

    protected p(c.a.a.a.n0.b... bVarArr) {
        super(bVarArr);
    }

    protected static String a(c.a.a.a.n0.f fVar) {
        return fVar.a();
    }

    protected static String b(c.a.a.a.n0.f fVar) {
        String strB = fVar.b();
        int iLastIndexOf = strB.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            return strB;
        }
        if (iLastIndexOf == 0) {
            iLastIndexOf = 1;
        }
        return strB.substring(0, iLastIndexOf);
    }

    protected List<c.a.a.a.n0.c> a(c.a.a.a.f[] fVarArr, c.a.a.a.n0.f fVar) throws c.a.a.a.n0.m {
        ArrayList arrayList = new ArrayList(fVarArr.length);
        for (c.a.a.a.f fVar2 : fVarArr) {
            String name = fVar2.getName();
            String value = fVar2.getValue();
            if (name == null || name.isEmpty()) {
                throw new c.a.a.a.n0.m("Cookie name may not be empty");
            }
            d dVar = new d(name, value);
            dVar.b(b(fVar));
            dVar.d(a(fVar));
            c.a.a.a.y[] yVarArrA = fVar2.a();
            for (int length = yVarArrA.length - 1; length >= 0; length--) {
                c.a.a.a.y yVar = yVarArrA[length];
                String lowerCase = yVar.getName().toLowerCase(Locale.ROOT);
                dVar.a(lowerCase, yVar.getValue());
                c.a.a.a.n0.d dVarA = a(lowerCase);
                if (dVarA != null) {
                    dVarA.a(dVar, yVar.getValue());
                }
            }
            arrayList.add(dVar);
        }
        return arrayList;
    }

    @Override // c.a.a.a.n0.i
    public void a(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        Iterator<c.a.a.a.n0.d> it = c().iterator();
        while (it.hasNext()) {
            it.next().a(cVar, fVar);
        }
    }

    @Override // c.a.a.a.n0.i
    public boolean b(c.a.a.a.n0.c cVar, c.a.a.a.n0.f fVar) {
        c.a.a.a.x0.a.a(cVar, "Cookie");
        c.a.a.a.x0.a.a(fVar, "Cookie origin");
        Iterator<c.a.a.a.n0.d> it = c().iterator();
        while (it.hasNext()) {
            if (!it.next().b(cVar, fVar)) {
                return false;
            }
        }
        return true;
    }
}

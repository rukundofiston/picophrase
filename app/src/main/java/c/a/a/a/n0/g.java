package c.a.a.a.n0;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class g implements Serializable, Comparator<c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f170a = new g();

    private String a(c cVar) {
        String strE = cVar.e();
        if (strE == null) {
            strE = "/";
        }
        if (strE.endsWith("/")) {
            return strE;
        }
        return strE + '/';
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(c cVar, c cVar2) {
        String strA = a(cVar);
        String strA2 = a(cVar2);
        if (strA.equals(strA2)) {
            return 0;
        }
        if (strA.startsWith(strA2)) {
            return -1;
        }
        return strA2.startsWith(strA) ? 1 : 0;
    }
}

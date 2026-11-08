package c.a.a.a.n0;

import java.io.Serializable;
import java.util.Comparator;
import org.cocos2dx.lib.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class e implements Serializable, Comparator<c> {
    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(c cVar, c cVar2) {
        int iCompareTo = cVar.getName().compareTo(cVar2.getName());
        if (iCompareTo == 0) {
            String strC = cVar.c();
            String str = BuildConfig.FLAVOR;
            if (strC == null) {
                strC = BuildConfig.FLAVOR;
            } else if (strC.indexOf(46) == -1) {
                strC = strC + ".local";
            }
            String strC2 = cVar2.c();
            if (strC2 != null) {
                if (strC2.indexOf(46) == -1) {
                    str = strC2 + ".local";
                } else {
                    str = strC2;
                }
            }
            iCompareTo = strC.compareToIgnoreCase(str);
        }
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        String strE = cVar.e();
        if (strE == null) {
            strE = "/";
        }
        String strE2 = cVar2.e();
        return strE.compareTo(strE2 != null ? strE2 : "/");
    }
}

package c.a.a.a.s0;

import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w f401a = new w();

    public static BitSet a(int... iArr) {
        BitSet bitSet = new BitSet();
        for (int i : iArr) {
            bitSet.set(i);
        }
        return bitSet;
    }

    public static boolean a(char c2) {
        return c2 == ' ' || c2 == '\t' || c2 == '\r' || c2 == '\n';
    }

    public String a(c.a.a.a.x0.d dVar, v vVar, BitSet bitSet) {
        StringBuilder sb = new StringBuilder();
        loop0: while (true) {
            boolean z = false;
            while (true) {
                if (!vVar.a()) {
                    char cCharAt = dVar.charAt(vVar.b());
                    if (bitSet != null && bitSet.get(cCharAt)) {
                        break loop0;
                    }
                    if (a(cCharAt)) {
                        a(dVar, vVar);
                        z = true;
                    }
                } else {
                    break loop0;
                }
            }
            if (z && sb.length() > 0) {
                sb.append(' ');
            }
            a(dVar, vVar, bitSet, sb);
        }
        return sb.toString();
    }

    public void a(c.a.a.a.x0.d dVar, v vVar) {
        int iB = vVar.b();
        int iC = vVar.c();
        for (int iB2 = vVar.b(); iB2 < iC && a(dVar.charAt(iB2)); iB2++) {
            iB++;
        }
        vVar.a(iB);
    }

    public void a(c.a.a.a.x0.d dVar, v vVar, StringBuilder sb) {
        if (vVar.a()) {
            return;
        }
        int iB = vVar.b();
        int iB2 = vVar.b();
        int iC = vVar.c();
        if (dVar.charAt(iB) != '\"') {
            return;
        }
        int i = iB + 1;
        int i2 = iB2 + 1;
        boolean z = false;
        while (i2 < iC) {
            char cCharAt = dVar.charAt(i2);
            if (z) {
                if (cCharAt != '\"' && cCharAt != '\\') {
                    sb.append('\\');
                }
                sb.append(cCharAt);
                z = false;
            } else if (cCharAt == '\"') {
                i++;
                break;
            } else if (cCharAt == '\\') {
                z = true;
            } else if (cCharAt != '\r' && cCharAt != '\n') {
                sb.append(cCharAt);
            }
            i2++;
            i++;
        }
        vVar.a(i);
    }

    public void a(c.a.a.a.x0.d dVar, v vVar, BitSet bitSet, StringBuilder sb) {
        int iB = vVar.b();
        int iC = vVar.c();
        for (int iB2 = vVar.b(); iB2 < iC; iB2++) {
            char cCharAt = dVar.charAt(iB2);
            if ((bitSet != null && bitSet.get(cCharAt)) || a(cCharAt)) {
                break;
            }
            iB++;
            sb.append(cCharAt);
        }
        vVar.a(iB);
    }

    public String b(c.a.a.a.x0.d dVar, v vVar, BitSet bitSet) {
        char cCharAt;
        StringBuilder sb = new StringBuilder();
        loop0: while (true) {
            boolean z = false;
            while (true) {
                if (!vVar.a()) {
                    cCharAt = dVar.charAt(vVar.b());
                    if (bitSet != null && bitSet.get(cCharAt)) {
                        break loop0;
                    }
                    if (a(cCharAt)) {
                        a(dVar, vVar);
                        z = true;
                    }
                } else {
                    break loop0;
                }
            }
            if (cCharAt == '\"') {
                if (z && sb.length() > 0) {
                    sb.append(' ');
                }
                a(dVar, vVar, sb);
            } else {
                if (z && sb.length() > 0) {
                    sb.append(' ');
                }
                b(dVar, vVar, bitSet, sb);
            }
        }
        return sb.toString();
    }

    public void b(c.a.a.a.x0.d dVar, v vVar, BitSet bitSet, StringBuilder sb) {
        int iB = vVar.b();
        int iC = vVar.c();
        for (int iB2 = vVar.b(); iB2 < iC; iB2++) {
            char cCharAt = dVar.charAt(iB2);
            if ((bitSet != null && bitSet.get(cCharAt)) || a(cCharAt) || cCharAt == '\"') {
                break;
            }
            iB++;
            sb.append(cCharAt);
        }
        vVar.a(iB);
    }
}

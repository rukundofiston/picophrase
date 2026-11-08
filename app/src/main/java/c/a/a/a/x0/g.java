package c.a.a.a.x0;

import c.a.a.a.k;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static void a(k kVar) throws IOException {
        InputStream inputStreamK;
        if (kVar == null || !kVar.a() || (inputStreamK = kVar.k()) == null) {
            return;
        }
        inputStreamK.close();
    }

    public static byte[] b(k kVar) throws IOException {
        a.a(kVar, "Entity");
        InputStream inputStreamK = kVar.k();
        if (inputStreamK == null) {
            return null;
        }
        try {
            a.a(kVar.m() <= 2147483647L, "HTTP entity too large to be buffered in memory");
            int iM = (int) kVar.m();
            if (iM < 0) {
                iM = 4096;
            }
            c cVar = new c(iM);
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStreamK.read(bArr);
                if (i == -1) {
                    return cVar.g();
                }
                cVar.a(bArr, 0, i);
            }
        } finally {
            inputStreamK.close();
        }
    }
}

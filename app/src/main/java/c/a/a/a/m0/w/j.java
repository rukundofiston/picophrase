package c.a.a.a.m0.w;

import javax.net.ssl.SSLException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class j extends a {
    static {
        new j();
    }

    public final String toString() {
        return "STRICT";
    }

    @Override // c.a.a.a.m0.w.l
    public final void verify(String str, String[] strArr, String[] strArr2) throws SSLException {
        a(str, strArr, strArr2, true);
    }
}

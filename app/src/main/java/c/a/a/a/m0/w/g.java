package c.a.a.a.m0.w;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class g {
    public static SSLContext a() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, null, null);
            return sSLContext;
        } catch (KeyManagementException e) {
            throw new h(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new h(e2.getMessage(), e2);
        }
    }

    public static f b() {
        return new f();
    }
}

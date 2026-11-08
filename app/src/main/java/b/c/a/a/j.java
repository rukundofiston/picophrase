package b.c.a.a;

import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public class j extends c.a.a.a.m0.w.i {
    final SSLContext h;

    class a implements X509TrustManager {
        a(j jVar) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public j(KeyStore keyStore) throws KeyManagementException {
        super(keyStore);
        this.h = SSLContext.getInstance("TLS");
        this.h.init(null, new TrustManager[]{new a(this)}, null);
    }

    public static c.a.a.a.m0.w.i c() {
        try {
            j jVar = new j(d());
            jVar.a(c.a.a.a.m0.w.i.f);
            return jVar;
        } catch (Throwable th) {
            th.printStackTrace();
            return c.a.a.a.m0.w.i.b();
        }
    }

    public static KeyStore d() {
        KeyStore keyStore;
        Throwable th;
        try {
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                keyStore.load(null, null);
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
            }
        } catch (Throwable th3) {
            keyStore = null;
            th = th3;
        }
        return keyStore;
    }

    @Override // c.a.a.a.m0.w.i, c.a.a.a.m0.v.l
    public Socket a() {
        return this.h.getSocketFactory().createSocket();
    }

    @Override // c.a.a.a.m0.w.i, c.a.a.a.m0.v.c
    public Socket b(Socket socket, String str, int i, boolean z) {
        return this.h.getSocketFactory().createSocket(socket, str, i, z);
    }
}

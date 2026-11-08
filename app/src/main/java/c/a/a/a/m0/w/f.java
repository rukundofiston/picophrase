package c.a.a.a.m0.w;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f153a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Set<KeyManager> f154b = new LinkedHashSet();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Set<TrustManager> f155c = new LinkedHashSet();
    private SecureRandom d;

    static class a implements X509TrustManager {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final X509TrustManager f156a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final k f157b;

        a(X509TrustManager x509TrustManager, k kVar) {
            this.f156a = x509TrustManager;
            this.f157b = kVar;
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.f156a.checkClientTrusted(x509CertificateArr, str);
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            if (this.f157b.a(x509CertificateArr, str)) {
                return;
            }
            this.f156a.checkServerTrusted(x509CertificateArr, str);
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return this.f156a.getAcceptedIssuers();
        }
    }

    public f a(KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        a(keyStore, null);
        return this;
    }

    public f a(KeyStore keyStore, k kVar) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers != null) {
            if (kVar != null) {
                for (int i = 0; i < trustManagers.length; i++) {
                    TrustManager trustManager = trustManagers[i];
                    if (trustManager instanceof X509TrustManager) {
                        trustManagers[i] = new a((X509TrustManager) trustManager, kVar);
                    }
                }
            }
            for (TrustManager trustManager2 : trustManagers) {
                this.f155c.add(trustManager2);
            }
        }
        return this;
    }

    public SSLContext a() throws NoSuchAlgorithmException, KeyManagementException {
        KeyManager[] keyManagerArr;
        String str = this.f153a;
        if (str == null) {
            str = "TLS";
        }
        SSLContext sSLContext = SSLContext.getInstance(str);
        TrustManager[] trustManagerArr = null;
        if (this.f154b.isEmpty()) {
            keyManagerArr = null;
        } else {
            Set<KeyManager> set = this.f154b;
            keyManagerArr = (KeyManager[]) set.toArray(new KeyManager[set.size()]);
        }
        if (!this.f155c.isEmpty()) {
            Set<TrustManager> set2 = this.f155c;
            trustManagerArr = (TrustManager[]) set2.toArray(new TrustManager[set2.size()]);
        }
        sSLContext.init(keyManagerArr, trustManagerArr, this.d);
        return sSLContext;
    }
}

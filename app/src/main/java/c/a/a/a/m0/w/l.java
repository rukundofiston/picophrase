package c.a.a.a.m0.w;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public interface l extends HostnameVerifier {
    void verify(String str, SSLSocket sSLSocket);

    void verify(String str, String[] strArr, String[] strArr2);
}

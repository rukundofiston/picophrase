package c.a.a.a.m0.w;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class a implements l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final String[] f148b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f149a = new c.a.a.a.p0.b(getClass());

    static {
        String[] strArr = {"ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org"};
        f148b = strArr;
        Arrays.sort(strArr);
    }

    public static int a(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '.') {
                i++;
            }
        }
        return i;
    }

    private static boolean a(String str, String str2, boolean z) {
        boolean zEndsWith;
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        String lowerCase2 = str2.toLowerCase(Locale.ROOT);
        String[] strArrSplit = lowerCase2.split("\\.");
        if (!(strArrSplit.length >= 3 && strArrSplit[0].endsWith("*") && (!z || a(strArrSplit)))) {
            return lowerCase.equals(lowerCase2);
        }
        String str3 = strArrSplit[0];
        if (str3.length() > 1) {
            String strSubstring = str3.substring(0, str3.length() - 1);
            zEndsWith = lowerCase.startsWith(strSubstring) && lowerCase.substring(strSubstring.length()).endsWith(lowerCase2.substring(str3.length()));
        } else {
            zEndsWith = lowerCase.endsWith(lowerCase2.substring(1));
        }
        if (zEndsWith) {
            return !z || a(lowerCase) == a(lowerCase2);
        }
        return false;
    }

    private static boolean a(String[] strArr) {
        return (strArr.length == 3 && strArr[2].length() == 2 && Arrays.binarySearch(f148b, strArr[1]) >= 0) ? false : true;
    }

    public final void a(String str, X509Certificate x509Certificate) {
        List<String> listA = d.a(x509Certificate, (c.a.a.a.m0.x.a.a(str) || c.a.a.a.m0.x.a.b(str)) ? 7 : 2);
        String strA = new e(x509Certificate.getSubjectX500Principal()).a("cn");
        String[] strArr = null;
        String[] strArr2 = strA != null ? new String[]{strA} : null;
        if (listA != null && !listA.isEmpty()) {
            strArr = (String[]) listA.toArray(new String[listA.size()]);
        }
        verify(str, strArr2, strArr);
    }

    public final void a(String str, String[] strArr, String[] strArr2, boolean z) throws SSLException {
        List<String> listAsList = null;
        String str2 = (strArr == null || strArr.length <= 0) ? null : strArr[0];
        if (strArr2 != null && strArr2.length > 0) {
            listAsList = Arrays.asList(strArr2);
        }
        String strA = c.a.a.a.m0.x.a.b(str) ? d.a(str.toLowerCase(Locale.ROOT)) : str;
        if (listAsList != null) {
            for (String strA2 : listAsList) {
                if (c.a.a.a.m0.x.a.b(strA2)) {
                    strA2 = d.a(strA2);
                }
                if (a(strA, strA2, z)) {
                    return;
                }
            }
            throw new SSLException("Certificate for <" + str + "> doesn't match any of the subject alternative names: " + listAsList);
        }
        if (str2 == null) {
            throw new SSLException("Certificate subject for <" + str + "> doesn't contain a common name and does not have alternative names");
        }
        if (a(strA, c.a.a.a.m0.x.a.b(str2) ? d.a(str2) : str2, z)) {
            return;
        }
        throw new SSLException("Certificate for <" + str + "> doesn't match common name of the certificate subject: " + str2);
    }

    @Override // c.a.a.a.m0.w.l
    public final void verify(String str, SSLSocket sSLSocket) throws IOException {
        c.a.a.a.x0.a.a(str, "Host");
        SSLSession session = sSLSocket.getSession();
        if (session == null) {
            sSLSocket.getInputStream().available();
            session = sSLSocket.getSession();
            if (session == null) {
                sSLSocket.startHandshake();
                session = sSLSocket.getSession();
            }
        }
        a(str, (X509Certificate) session.getPeerCertificates()[0]);
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        try {
            a(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
            return true;
        } catch (SSLException e) {
            if (this.f149a.a()) {
                this.f149a.a(e.getMessage(), e);
            }
            return false;
        }
    }
}

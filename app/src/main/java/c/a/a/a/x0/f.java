package c.a.a.a.x0;

import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static byte[] a(String str) {
        a.a(str, "Input");
        return str.getBytes(c.a.a.a.c.f55b);
    }

    public static byte[] a(String str, String str2) {
        a.a(str, "Input");
        a.c(str2, "Charset");
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }
}

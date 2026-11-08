package c.a.a.a.q0.g;

import java.nio.charset.Charset;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
final class i implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Charset f205a = c.a.a.a.x0.e.a("UnicodeLittleUnmarked");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Charset f206b = c.a.a.a.c.f55b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final SecureRandom f207c;
    private static final byte[] d;
    private static final b e;

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private byte[] f208a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f209b = 0;

        a() {
        }
    }

    static class b extends a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final byte[] f210c = null;
        private final byte[] d = null;

        b() {
        }
    }

    static {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception unused) {
            secureRandom = null;
        }
        f207c = secureRandom;
        byte[] bytes = "NTLMSSP".getBytes(c.a.a.a.c.f55b);
        byte[] bArr = new byte[bytes.length + 1];
        d = bArr;
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        d[bytes.length] = 0;
        e = new b();
    }

    i() {
    }
}

package c.a.a.a.q0.g;

import c.a.a.a.i0.o;
import c.a.a.a.q;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes.dex */
public class d extends l {
    private static final char[] j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private boolean d;
    private String e;
    private long f;
    private String g;
    private String h;
    private String i;

    public d() {
        this(c.a.a.a.c.f55b);
    }

    public d(Charset charset) {
        super(charset);
        this.d = false;
    }

    static String a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length * 2];
        for (int i = 0; i < length; i++) {
            int i2 = bArr[i] & 15;
            int i3 = (bArr[i] & 240) >> 4;
            int i4 = i * 2;
            char[] cArr2 = j;
            cArr[i4] = cArr2[i3];
            cArr[i4 + 1] = cArr2[i2];
        }
        return new String(cArr);
    }

    private c.a.a.a.e b(c.a.a.a.i0.m mVar, q qVar) throws c.a.a.a.i0.i {
        String str;
        byte b2;
        MessageDigest messageDigest;
        String str2;
        String str3;
        byte b3;
        char c2;
        String str4;
        String strA = a("uri");
        String strA2 = a("realm");
        String strA3 = a("nonce");
        String strA4 = a("opaque");
        String strA5 = a("methodname");
        String strA6 = a("algorithm");
        if (strA6 == null) {
            strA6 = "MD5";
        }
        HashSet hashSet = new HashSet(8);
        String str5 = "MD5";
        String strA7 = a("qop");
        if (strA7 != null) {
            str = "qop";
            for (StringTokenizer stringTokenizer = new StringTokenizer(strA7, ","); stringTokenizer.hasMoreTokens(); stringTokenizer = stringTokenizer) {
                hashSet.add(stringTokenizer.nextToken().trim().toLowerCase(Locale.ROOT));
            }
            b2 = ((qVar instanceof c.a.a.a.l) && hashSet.contains("auth-int")) ? (byte) 1 : hashSet.contains("auth") ? (byte) 2 : (byte) -1;
        } else {
            str = "qop";
            b2 = 0;
        }
        if (b2 == -1) {
            throw new c.a.a.a.i0.i("None of the qop methods is supported: " + strA7);
        }
        String strA8 = a("charset");
        if (strA8 == null) {
            strA8 = "ISO-8859-1";
        }
        if (!strA6.equalsIgnoreCase("MD5-sess")) {
            str5 = strA6;
        }
        try {
            MessageDigest messageDigestB = b(str5);
            String name = mVar.a().getName();
            String strB = mVar.b();
            if (strA3.equals(this.e)) {
                this.f++;
            } else {
                this.f = 1L;
                this.g = null;
                this.e = strA3;
            }
            StringBuilder sb = new StringBuilder(256);
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("%08x", Long.valueOf(this.f));
            formatter.close();
            String string = sb.toString();
            if (this.g == null) {
                this.g = h();
            }
            this.h = null;
            this.i = null;
            if (strA6.equalsIgnoreCase("MD5-sess")) {
                sb.setLength(0);
                sb.append(name);
                sb.append(':');
                sb.append(strA2);
                sb.append(':');
                sb.append(strB);
                messageDigest = messageDigestB;
                String strA9 = a(messageDigest.digest(c.a.a.a.x0.f.a(sb.toString(), strA8)));
                sb.setLength(0);
                sb.append(strA9);
                sb.append(':');
                sb.append(strA3);
                sb.append(':');
                strB = this.g;
            } else {
                messageDigest = messageDigestB;
                sb.setLength(0);
                sb.append(name);
                sb.append(':');
                sb.append(strA2);
                sb.append(':');
            }
            sb.append(strB);
            this.h = sb.toString();
            String strA10 = a(messageDigest.digest(c.a.a.a.x0.f.a(this.h, strA8)));
            if (b2 == 2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(strA5);
                sb2.append(':');
                str2 = strA;
                sb2.append(str2);
                this.i = sb2.toString();
                str3 = "auth";
            } else {
                str2 = strA;
                if (b2 == 1) {
                    c.a.a.a.k kVarC = qVar instanceof c.a.a.a.l ? ((c.a.a.a.l) qVar).c() : null;
                    if (kVarC == null || kVarC.e()) {
                        str3 = "auth";
                        g gVar = new g(messageDigest);
                        if (kVarC != null) {
                            try {
                                kVarC.a(gVar);
                            } catch (IOException e) {
                                throw new c.a.a.a.i0.i("I/O error reading entity content", e);
                            }
                        }
                        gVar.close();
                        this.i = strA5 + ':' + str2 + ':' + a(gVar.f());
                        b3 = b2;
                    } else {
                        str3 = "auth";
                        if (!hashSet.contains(str3)) {
                            throw new c.a.a.a.i0.i("Qop auth-int cannot be used with a non-repeatable entity");
                        }
                        this.i = strA5 + ':' + str2;
                        b3 = 2;
                    }
                    b2 = b3;
                } else {
                    str3 = "auth";
                    this.i = strA5 + ':' + str2;
                }
            }
            String strA11 = a(messageDigest.digest(c.a.a.a.x0.f.a(this.i, strA8)));
            if (b2 == 0) {
                sb.setLength(0);
                sb.append(strA10);
                c2 = ':';
                sb.append(':');
                sb.append(strA3);
            } else {
                c2 = ':';
                sb.setLength(0);
                sb.append(strA10);
                sb.append(':');
                sb.append(strA3);
                sb.append(':');
                sb.append(string);
                sb.append(':');
                sb.append(this.g);
                sb.append(':');
                sb.append(b2 == 1 ? r8 : str3);
            }
            sb.append(c2);
            sb.append(strA11);
            String strA12 = a(messageDigest.digest(c.a.a.a.x0.f.a(sb.toString())));
            c.a.a.a.x0.d dVar = new c.a.a.a.x0.d(128);
            dVar.a(e() ? "Proxy-Authorization" : "Authorization");
            dVar.a(": Digest ");
            ArrayList arrayList = new ArrayList(20);
            arrayList.add(new c.a.a.a.s0.m("username", name));
            arrayList.add(new c.a.a.a.s0.m("realm", strA2));
            arrayList.add(new c.a.a.a.s0.m("nonce", strA3));
            arrayList.add(new c.a.a.a.s0.m("uri", str2));
            arrayList.add(new c.a.a.a.s0.m("response", strA12));
            if (b2 != 0) {
                if (b2 == 1) {
                    str3 = "auth-int";
                }
                str4 = str;
                arrayList.add(new c.a.a.a.s0.m(str4, str3));
                arrayList.add(new c.a.a.a.s0.m("nc", string));
                arrayList.add(new c.a.a.a.s0.m("cnonce", this.g));
            } else {
                str4 = str;
            }
            arrayList.add(new c.a.a.a.s0.m("algorithm", strA6));
            if (strA4 != null) {
                arrayList.add(new c.a.a.a.s0.m("opaque", strA4));
            }
            for (int i = 0; i < arrayList.size(); i++) {
                c.a.a.a.s0.m mVar2 = (c.a.a.a.s0.m) arrayList.get(i);
                if (i > 0) {
                    dVar.a(", ");
                }
                String name2 = mVar2.getName();
                c.a.a.a.s0.f.f370a.a(dVar, mVar2, !("nc".equals(name2) || str4.equals(name2) || "algorithm".equals(name2)));
            }
            return new c.a.a.a.s0.q(dVar);
        } catch (m unused) {
            throw new c.a.a.a.i0.i("Unsuppported digest algorithm: " + str5);
        }
    }

    private static MessageDigest b(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (Exception unused) {
            throw new m("Unsupported algorithm in HTTP Digest authentication: " + str);
        }
    }

    public static String h() {
        byte[] bArr = new byte[8];
        new SecureRandom().nextBytes(bArr);
        return a(bArr);
    }

    @Override // c.a.a.a.i0.c
    @Deprecated
    public c.a.a.a.e a(c.a.a.a.i0.m mVar, q qVar) {
        return a(mVar, qVar, new c.a.a.a.v0.a());
    }

    @Override // c.a.a.a.q0.g.a, c.a.a.a.i0.l
    public c.a.a.a.e a(c.a.a.a.i0.m mVar, q qVar, c.a.a.a.v0.e eVar) throws c.a.a.a.i0.i {
        c.a.a.a.x0.a.a(mVar, "Credentials");
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        if (a("realm") == null) {
            throw new c.a.a.a.i0.i("missing realm in challenge");
        }
        if (a("nonce") == null) {
            throw new c.a.a.a.i0.i("missing nonce in challenge");
        }
        g().put("methodname", qVar.g().b());
        g().put("uri", qVar.g().c());
        if (a("charset") == null) {
            g().put("charset", a(qVar));
        }
        return b(mVar, qVar);
    }

    @Override // c.a.a.a.q0.g.a, c.a.a.a.i0.c
    public void a(c.a.a.a.e eVar) throws o {
        super.a(eVar);
        this.d = true;
        if (g().isEmpty()) {
            throw new o("Authentication challenge is empty");
        }
    }

    @Override // c.a.a.a.i0.c
    public boolean a() {
        if ("true".equalsIgnoreCase(a("stale"))) {
            return false;
        }
        return this.d;
    }

    @Override // c.a.a.a.i0.c
    public String b() {
        return "digest";
    }

    @Override // c.a.a.a.i0.c
    public boolean d() {
        return false;
    }

    @Override // c.a.a.a.q0.g.a
    public String toString() {
        return "DIGEST [complete=" + this.d + ", nonce=" + this.e + ", nc=" + this.f + "]";
    }
}

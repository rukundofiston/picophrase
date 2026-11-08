package c.a.a.a.j0.w;

import c.a.a.a.k;
import c.a.a.a.s0.m;
import c.a.a.a.s0.v;
import c.a.a.a.s0.w;
import c.a.a.a.y;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final BitSet f109a = new BitSet(256);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final BitSet f110b = new BitSet(256);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final BitSet f111c = new BitSet(256);
    private static final BitSet d = new BitSet(256);
    private static final BitSet e = new BitSet(256);
    private static final BitSet f = new BitSet(256);
    private static final BitSet g = new BitSet(256);

    static {
        for (int i = 97; i <= 122; i++) {
            f109a.set(i);
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            f109a.set(i2);
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            f109a.set(i3);
        }
        f109a.set(95);
        f109a.set(45);
        f109a.set(46);
        f109a.set(42);
        g.or(f109a);
        f109a.set(33);
        f109a.set(126);
        f109a.set(39);
        f109a.set(40);
        f109a.set(41);
        f110b.set(44);
        f110b.set(59);
        f110b.set(58);
        f110b.set(36);
        f110b.set(38);
        f110b.set(43);
        f110b.set(61);
        f111c.or(f109a);
        f111c.or(f110b);
        d.or(f109a);
        d.set(47);
        d.set(59);
        d.set(58);
        d.set(64);
        d.set(38);
        d.set(61);
        d.set(43);
        d.set(36);
        d.set(44);
        f.set(59);
        f.set(47);
        f.set(63);
        f.set(58);
        f.set(64);
        f.set(38);
        f.set(61);
        f.set(43);
        f.set(36);
        f.set(44);
        f.set(91);
        f.set(93);
        e.or(f);
        e.or(f109a);
    }

    public static String a(Iterable<? extends y> iterable, char c2, Charset charset) {
        StringBuilder sb = new StringBuilder();
        for (y yVar : iterable) {
            String strE = e(yVar.getName(), charset);
            String strE2 = e(yVar.getValue(), charset);
            if (sb.length() > 0) {
                sb.append(c2);
            }
            sb.append(strE);
            if (strE2 != null) {
                sb.append("=");
                sb.append(strE2);
            }
        }
        return sb.toString();
    }

    public static String a(Iterable<? extends y> iterable, Charset charset) {
        return a(iterable, '&', charset);
    }

    private static String a(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = c.a.a.a.c.f54a;
        }
        return a(str, charset, true);
    }

    private static String a(String str, Charset charset, BitSet bitSet, boolean z) {
        char upperCase;
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        ByteBuffer byteBufferEncode = charset.encode(str);
        while (byteBufferEncode.hasRemaining()) {
            int i = byteBufferEncode.get() & 255;
            if (bitSet.get(i)) {
                upperCase = (char) i;
            } else if (z && i == 32) {
                upperCase = '+';
            } else {
                sb.append("%");
                char upperCase2 = Character.toUpperCase(Character.forDigit((i >> 4) & 15, 16));
                upperCase = Character.toUpperCase(Character.forDigit(i & 15, 16));
                sb.append(upperCase2);
            }
            sb.append(upperCase);
        }
        return sb.toString();
    }

    private static String a(String str, Charset charset, boolean z) {
        byte b2;
        if (str == null) {
            return null;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(str.length());
        CharBuffer charBufferWrap = CharBuffer.wrap(str);
        while (charBufferWrap.hasRemaining()) {
            int i = charBufferWrap.get();
            if (i != 37 || charBufferWrap.remaining() < 2) {
                b2 = (z && i == 43) ? (byte) 32 : (byte) i;
            } else {
                char c2 = charBufferWrap.get();
                char c3 = charBufferWrap.get();
                int iDigit = Character.digit(c2, 16);
                int iDigit2 = Character.digit(c3, 16);
                if (iDigit == -1 || iDigit2 == -1) {
                    byteBufferAllocate.put((byte) 37);
                    byteBufferAllocate.put((byte) c2);
                    b2 = (byte) c3;
                } else {
                    i = (iDigit << 4) + iDigit2;
                }
            }
            byteBufferAllocate.put(b2);
        }
        byteBufferAllocate.flip();
        return charset.decode(byteBufferAllocate).toString();
    }

    public static List<y> a(k kVar) throws IOException {
        c.a.a.a.o0.e eVarA = c.a.a.a.o0.e.a(kVar);
        if (eVarA == null || !eVarA.b().equalsIgnoreCase("application/x-www-form-urlencoded")) {
            return Collections.emptyList();
        }
        long jM = kVar.m();
        c.a.a.a.x0.a.a(jM <= 2147483647L, "HTTP entity is too large");
        Charset charsetA = eVarA.a() != null ? eVarA.a() : c.a.a.a.v0.d.f412a;
        InputStream inputStreamK = kVar.k();
        if (inputStreamK == null) {
            return Collections.emptyList();
        }
        try {
            c.a.a.a.x0.d dVar = new c.a.a.a.x0.d(jM > 0 ? (int) jM : 1024);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStreamK, charsetA);
            char[] cArr = new char[1024];
            while (true) {
                int i = inputStreamReader.read(cArr);
                if (i == -1) {
                    break;
                }
                dVar.a(cArr, 0, i);
            }
            inputStreamK.close();
            return dVar.length() == 0 ? Collections.emptyList() : a(dVar, charsetA, '&');
        } catch (Throwable th) {
            inputStreamK.close();
            throw th;
        }
    }

    public static List<y> a(c.a.a.a.x0.d dVar, Charset charset, char... cArr) {
        c.a.a.a.x0.a.a(dVar, "Char array buffer");
        w wVar = w.f401a;
        BitSet bitSet = new BitSet();
        for (char c2 : cArr) {
            bitSet.set(c2);
        }
        v vVar = new v(0, dVar.length());
        ArrayList arrayList = new ArrayList();
        while (!vVar.a()) {
            bitSet.set(61);
            String strA = wVar.a(dVar, vVar, bitSet);
            String strB = null;
            if (!vVar.a()) {
                char cCharAt = dVar.charAt(vVar.b());
                vVar.a(vVar.b() + 1);
                if (cCharAt == '=') {
                    bitSet.clear(61);
                    strB = wVar.b(dVar, vVar, bitSet);
                    if (!vVar.a()) {
                        vVar.a(vVar.b() + 1);
                    }
                }
            }
            if (!strA.isEmpty()) {
                arrayList.add(new m(a(strA, charset), a(strB, charset)));
            }
        }
        return arrayList;
    }

    static String b(String str, Charset charset) {
        return a(str, charset, d, false);
    }

    static String c(String str, Charset charset) {
        return a(str, charset, e, false);
    }

    static String d(String str, Charset charset) {
        return a(str, charset, f111c, false);
    }

    private static String e(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = c.a.a.a.c.f54a;
        }
        return a(str, charset, g, true);
    }

    public static List<y> f(String str, Charset charset) {
        c.a.a.a.x0.d dVar = new c.a.a.a.x0.d(str.length());
        dVar.a(str);
        return a(dVar, charset, '&', ';');
    }
}

package c.a.a.a.o0;

import c.a.a.a.k;
import c.a.a.a.x0.i;
import c.a.a.a.y;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Serializable {
    public static final e d;
    public static final e e;
    public static final e f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f178a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Charset f179b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final y[] f180c;

    static {
        a("application/atom+xml", c.a.a.a.c.f56c);
        d = a("application/x-www-form-urlencoded", c.a.a.a.c.f56c);
        a("application/json", c.a.a.a.c.f54a);
        e = a("application/octet-stream", (Charset) null);
        a("application/svg+xml", c.a.a.a.c.f56c);
        a("application/xhtml+xml", c.a.a.a.c.f56c);
        a("application/xml", c.a.a.a.c.f56c);
        a("multipart/form-data", c.a.a.a.c.f56c);
        a("text/html", c.a.a.a.c.f56c);
        f = a("text/plain", c.a.a.a.c.f56c);
        a("text/xml", c.a.a.a.c.f56c);
        a("*/*", (Charset) null);
    }

    e(String str, Charset charset) {
        this.f178a = str;
        this.f179b = charset;
        this.f180c = null;
    }

    e(String str, Charset charset, y[] yVarArr) {
        this.f178a = str;
        this.f179b = charset;
        this.f180c = yVarArr;
    }

    private static e a(c.a.a.a.f fVar, boolean z) {
        return a(fVar.getName(), fVar.a(), z);
    }

    public static e a(k kVar) {
        c.a.a.a.e eVarD;
        if (kVar != null && (eVarD = kVar.d()) != null) {
            c.a.a.a.f[] fVarArrA = eVarD.a();
            if (fVarArrA.length > 0) {
                return a(fVarArrA[0], true);
            }
        }
        return null;
    }

    public static e a(String str, Charset charset) {
        c.a.a.a.x0.a.b(str, "MIME type");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        c.a.a.a.x0.a.a(a(lowerCase), "MIME type may not contain reserved characters");
        return new e(lowerCase, charset);
    }

    private static e a(String str, y[] yVarArr, boolean z) {
        Charset charsetForName;
        int length = yVarArr.length;
        int i = 0;
        while (true) {
            if (i < length) {
                y yVar = yVarArr[i];
                if (yVar.getName().equalsIgnoreCase("charset")) {
                    String value = yVar.getValue();
                    if (!i.b(value)) {
                        try {
                            charsetForName = Charset.forName(value);
                            break;
                        } catch (UnsupportedCharsetException e2) {
                            if (z) {
                                throw e2;
                            }
                            charsetForName = null;
                            break;
                        }
                    }
                } else {
                    i++;
                }
            }
            charsetForName = null;
            break;
        }
        if (yVarArr == null || yVarArr.length <= 0) {
            yVarArr = null;
        }
        return new e(str, charsetForName, yVarArr);
    }

    private static boolean a(String str) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"' || cCharAt == ',' || cCharAt == ';') {
                return false;
            }
        }
        return true;
    }

    public Charset a() {
        return this.f179b;
    }

    public String b() {
        return this.f178a;
    }

    public String toString() {
        c.a.a.a.x0.d dVar = new c.a.a.a.x0.d(64);
        dVar.a(this.f178a);
        if (this.f180c != null) {
            dVar.a("; ");
            c.a.a.a.s0.f.f370a.a(dVar, this.f180c, false);
        } else if (this.f179b != null) {
            dVar.a("; charset=");
            dVar.a(this.f179b.name());
        }
        return dVar.toString();
    }
}

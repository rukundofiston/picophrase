package c.a.a.a.j0.w;

import c.a.a.a.y;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f106a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f107b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f108c;
    private String d;
    private String e;
    private String f;
    private int g;
    private String h;
    private String i;
    private String j;
    private List<y> k;
    private String l;
    private Charset m;
    private String n;
    private String o;

    public c(URI uri) {
        a(uri);
    }

    private List<y> a(String str, Charset charset) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return e.f(str, charset);
    }

    private void a(URI uri) {
        this.f106a = uri.getScheme();
        this.f107b = uri.getRawSchemeSpecificPart();
        this.f108c = uri.getRawAuthority();
        this.f = uri.getHost();
        this.g = uri.getPort();
        this.e = uri.getRawUserInfo();
        this.d = uri.getUserInfo();
        this.i = uri.getRawPath();
        this.h = uri.getPath();
        this.j = uri.getRawQuery();
        String rawQuery = uri.getRawQuery();
        Charset charset = this.m;
        if (charset == null) {
            charset = c.a.a.a.c.f54a;
        }
        this.k = a(rawQuery, charset);
        this.o = uri.getRawFragment();
        this.n = uri.getFragment();
    }

    private String b(List<y> list) {
        Charset charset = this.m;
        if (charset == null) {
            charset = c.a.a.a.c.f54a;
        }
        return e.a(list, charset);
    }

    private String f(String str) {
        Charset charset = this.m;
        if (charset == null) {
            charset = c.a.a.a.c.f54a;
        }
        return e.b(str, charset);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x008d  */
    /* JADX WARN: Code duplicated, block: B:36:0x0093  */
    /* JADX WARN: Code duplicated, block: B:38:0x0097  */
    /* JADX WARN: Code duplicated, block: B:39:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:41:0x00a6  */
    private String g() {
        String str;
        String strF;
        String strG;
        StringBuilder sb = new StringBuilder();
        String str2 = this.f106a;
        if (str2 != null) {
            sb.append(str2);
            sb.append(':');
        }
        String strG2 = this.f107b;
        if (strG2 == null) {
            if (this.f108c != null) {
                sb.append("//");
                sb.append(this.f108c);
            } else if (this.f != null) {
                sb.append("//");
                String strH = this.e;
                if (strH == null) {
                    String str3 = this.d;
                    if (str3 != null) {
                        strH = h(str3);
                        sb.append(strH);
                        sb.append("@");
                    }
                } else {
                    sb.append(strH);
                    sb.append("@");
                }
                if (c.a.a.a.m0.x.a.b(this.f)) {
                    sb.append("[");
                    sb.append(this.f);
                    str = "]";
                } else {
                    str = this.f;
                }
                sb.append(str);
                if (this.g >= 0) {
                    sb.append(":");
                    sb.append(this.g);
                }
            }
            String str4 = this.i;
            if (str4 != null) {
                strF = i(str4);
            } else {
                String str5 = this.h;
                if (str5 != null) {
                    strF = f(i(str5));
                } else {
                    if (this.j != null) {
                        sb.append("?");
                        strG2 = this.j;
                    } else if (this.k != null) {
                        sb.append("?");
                        strG2 = b(this.k);
                    } else if (this.l != null) {
                        sb.append("?");
                        strG2 = g(this.l);
                    }
                    sb.append(strG2);
                }
            }
            sb.append(strF);
            if (this.j != null) {
                sb.append("?");
                strG2 = this.j;
            } else if (this.k != null) {
                sb.append("?");
                strG2 = b(this.k);
            } else if (this.l != null) {
                sb.append("?");
                strG2 = g(this.l);
            }
            sb.append(strG2);
        } else {
            sb.append(strG2);
        }
        if (this.o == null) {
            if (this.n != null) {
                sb.append("#");
                strG = g(this.n);
            }
            return sb.toString();
        }
        sb.append("#");
        strG = this.o;
        sb.append(strG);
        return sb.toString();
    }

    private String g(String str) {
        Charset charset = this.m;
        if (charset == null) {
            charset = c.a.a.a.c.f54a;
        }
        return e.c(str, charset);
    }

    private String h(String str) {
        Charset charset = this.m;
        if (charset == null) {
            charset = c.a.a.a.c.f54a;
        }
        return e.d(str, charset);
    }

    private static String i(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        while (i < str.length() && str.charAt(i) == '/') {
            i++;
        }
        return i > 1 ? str.substring(i - 1) : str;
    }

    public c a(int i) {
        if (i < 0) {
            i = -1;
        }
        this.g = i;
        this.f107b = null;
        this.f108c = null;
        return this;
    }

    public c a(String str) {
        this.n = str;
        this.o = null;
        return this;
    }

    public c a(Charset charset) {
        this.m = charset;
        return this;
    }

    public c a(List<y> list) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.addAll(list);
        this.j = null;
        this.f107b = null;
        this.l = null;
        return this;
    }

    public URI a() {
        return new URI(g());
    }

    public c b() {
        this.k = null;
        this.j = null;
        this.f107b = null;
        return this;
    }

    public c b(String str) {
        this.f = str;
        this.f107b = null;
        this.f108c = null;
        return this;
    }

    public c c(String str) {
        this.h = str;
        this.f107b = null;
        this.i = null;
        return this;
    }

    public String c() {
        return this.f;
    }

    public c d(String str) {
        this.f106a = str;
        return this;
    }

    public String d() {
        return this.h;
    }

    public c e(String str) {
        this.d = str;
        this.f107b = null;
        this.f108c = null;
        this.e = null;
        return this;
    }

    public List<y> e() {
        return this.k != null ? new ArrayList(this.k) : new ArrayList();
    }

    public String f() {
        return this.d;
    }

    public String toString() {
        return g();
    }
}

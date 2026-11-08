package b.c.a.a;

import android.content.Context;
import c.a.a.a.q;
import c.a.a.a.r;
import c.a.a.a.s;
import c.a.a.a.u;
import c.a.a.a.v;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static h j = new g();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.q0.h.k f32a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.v0.e f33b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<Context, List<l>> f34c;
    private final Map<String, String> d;
    private int e;
    private int f;
    private int g;
    private ExecutorService h;
    private boolean i;

    /* JADX INFO: renamed from: b.c.a.a.a$a, reason: collision with other inner class name */
    class C0004a implements r {
        C0004a() {
        }

        @Override // c.a.a.a.r
        public void a(q qVar, c.a.a.a.v0.e eVar) {
            if (!qVar.c("Accept-Encoding")) {
                qVar.b("Accept-Encoding", "gzip");
            }
            for (String str : a.this.d.keySet()) {
                if (qVar.c(str)) {
                    c.a.a.a.e eVarD = qVar.d(str);
                    a.j.d("AsyncHttpClient", String.format("Headers were overwritten! (%s | %s) overwrites (%s | %s)", str, a.this.d.get(str), eVarD.getName(), eVarD.getValue()));
                    qVar.b(eVarD);
                }
                qVar.b(str, (String) a.this.d.get(str));
            }
        }
    }

    class b implements u {
        b(a aVar) {
        }

        @Override // c.a.a.a.u
        public void a(s sVar, c.a.a.a.v0.e eVar) {
            c.a.a.a.e eVarC;
            c.a.a.a.k kVarC = sVar.c();
            if (kVarC == null || (eVarC = kVarC.c()) == null) {
                return;
            }
            for (c.a.a.a.f fVar : eVarC.a()) {
                if (fVar.getName().equalsIgnoreCase("gzip")) {
                    sVar.a(new d(kVarC));
                    return;
                }
            }
        }
    }

    class c implements r {
        c(a aVar) {
        }

        @Override // c.a.a.a.r
        public void a(q qVar, c.a.a.a.v0.e eVar) {
            c.a.a.a.i0.m mVarA;
            c.a.a.a.i0.h hVar = (c.a.a.a.i0.h) eVar.a("http.auth.target-scope");
            c.a.a.a.j0.i iVar = (c.a.a.a.j0.i) eVar.a("http.auth.credentials-provider");
            c.a.a.a.n nVar = (c.a.a.a.n) eVar.a("http.target_host");
            if (hVar.b() != null || (mVarA = iVar.a(new c.a.a.a.i0.g(nVar.b(), nVar.c()))) == null) {
                return;
            }
            hVar.a(new c.a.a.a.q0.g.b());
            hVar.a(mVarA);
        }
    }

    private static class d extends c.a.a.a.o0.f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        InputStream f36b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        PushbackInputStream f37c;
        GZIPInputStream d;

        public d(c.a.a.a.k kVar) {
            super(kVar);
        }

        @Override // c.a.a.a.o0.f, c.a.a.a.k
        public void h() {
            a.a(this.f36b);
            a.a((InputStream) this.f37c);
            a.a(this.d);
            super.h();
        }

        @Override // c.a.a.a.o0.f, c.a.a.a.k
        public InputStream k() {
            this.f36b = this.f181a.k();
            PushbackInputStream pushbackInputStream = new PushbackInputStream(this.f36b, 2);
            this.f37c = pushbackInputStream;
            if (!a.a(pushbackInputStream)) {
                return this.f37c;
            }
            GZIPInputStream gZIPInputStream = new GZIPInputStream(this.f37c);
            this.d = gZIPInputStream;
            return gZIPInputStream;
        }

        @Override // c.a.a.a.o0.f, c.a.a.a.k
        public long m() {
            c.a.a.a.k kVar = this.f181a;
            if (kVar == null) {
                return 0L;
            }
            return kVar.m();
        }
    }

    public a() {
        this(false, 80, 443);
    }

    public a(c.a.a.a.m0.v.i iVar) {
        this.e = 10;
        this.f = 10000;
        this.g = 10000;
        this.i = true;
        c.a.a.a.t0.b bVar = new c.a.a.a.t0.b();
        c.a.a.a.m0.t.a.a(bVar, this.f);
        c.a.a.a.m0.t.a.a(bVar, new c.a.a.a.m0.t.c(this.e));
        c.a.a.a.m0.t.a.a((c.a.a.a.t0.e) bVar, 10);
        c.a.a.a.t0.c.b(bVar, this.g);
        c.a.a.a.t0.c.a(bVar, this.f);
        c.a.a.a.t0.c.a((c.a.a.a.t0.e) bVar, true);
        c.a.a.a.t0.c.c(bVar, 8192);
        c.a.a.a.t0.f.a(bVar, v.f);
        c.a.a.a.m0.b bVarA = a(iVar, bVar);
        p.a(bVarA != null, "Custom implementation of #createConnectionManager(SchemeRegistry, BasicHttpParams) returned null");
        this.h = a();
        this.f34c = Collections.synchronizedMap(new WeakHashMap());
        this.d = new HashMap();
        this.f33b = new c.a.a.a.v0.n(new c.a.a.a.v0.a());
        c.a.a.a.q0.h.k kVar = new c.a.a.a.q0.h.k(bVarA, bVar);
        this.f32a = kVar;
        kVar.a(new C0004a());
        this.f32a.a(new b(this));
        this.f32a.a(new c(this), 0);
        this.f32a.a(new o(5, 1500));
    }

    public a(boolean z, int i, int i2) {
        this(a(z, i, i2));
    }

    private static c.a.a.a.m0.v.i a(boolean z, int i, int i2) {
        if (z) {
            j.d("AsyncHttpClient", "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
        }
        if (i < 1) {
            i = 80;
            j.d("AsyncHttpClient", "Invalid HTTP port number specified, defaulting to 80");
        }
        if (i2 < 1) {
            i2 = 443;
            j.d("AsyncHttpClient", "Invalid HTTPS port number specified, defaulting to 443");
        }
        c.a.a.a.m0.w.i iVarC = z ? j.c() : c.a.a.a.m0.w.i.b();
        c.a.a.a.m0.v.i iVar = new c.a.a.a.m0.v.i();
        iVar.a(new c.a.a.a.m0.v.e("http", c.a.a.a.m0.v.d.b(), i));
        iVar.a(new c.a.a.a.m0.v.e("https", iVarC, i2));
        return iVar;
    }

    public static String a(boolean z, String str, m mVar) {
        if (str == null) {
            return null;
        }
        if (z) {
            try {
                URL url = new URL(URLDecoder.decode(str, "UTF-8"));
                str = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef()).toASCIIString();
            } catch (Exception e) {
                j.b("AsyncHttpClient", "getUrlWithQueryString encoding URL", e);
            }
        }
        if (mVar == null) {
            return str;
        }
        mVar.a();
        throw null;
    }

    public static void a(c.a.a.a.k kVar) {
        if (kVar instanceof c.a.a.a.o0.f) {
            Field field = null;
            try {
                for (Field field2 : c.a.a.a.o0.f.class.getDeclaredFields()) {
                    if (field2.getName().equals("wrappedEntity")) {
                        field = field2;
                        break;
                    }
                }
                if (field != null) {
                    field.setAccessible(true);
                    c.a.a.a.k kVar2 = (c.a.a.a.k) field.get(kVar);
                    if (kVar2 != null) {
                        kVar2.h();
                    }
                }
            } catch (Throwable th) {
                j.b("AsyncHttpClient", "wrappedEntity consume", th);
            }
        }
    }

    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                j.a("AsyncHttpClient", "Cannot close input stream", e);
            }
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                j.a("AsyncHttpClient", "Cannot close output stream", e);
            }
        }
    }

    public static void a(Class<?> cls) {
        if (cls != null) {
            o.a(cls);
        }
    }

    public static boolean a(PushbackInputStream pushbackInputStream) throws IOException {
        if (pushbackInputStream == null) {
            return false;
        }
        byte[] bArr = new byte[2];
        int i = 0;
        while (i < 2) {
            try {
                int i2 = pushbackInputStream.read(bArr, i, 2 - i);
                if (i2 < 0) {
                    return false;
                }
                i += i2;
            } finally {
                pushbackInputStream.unread(bArr, 0, i);
            }
        }
        pushbackInputStream.unread(bArr, 0, i);
        return 35615 == ((bArr[0] & 255) | ((bArr[1] << 8) & 65280));
    }

    protected b.c.a.a.b a(c.a.a.a.q0.h.k kVar, c.a.a.a.v0.e eVar, c.a.a.a.j0.t.i iVar, String str, n nVar, Context context) {
        return new b.c.a.a.b(kVar, eVar, iVar, nVar);
    }

    public l a(Context context, String str, m mVar, n nVar) {
        return b(this.f32a, this.f33b, new f(a(this.i, str, mVar)), null, nVar, context);
    }

    public l a(Context context, String str, n nVar) {
        return a(context, str, null, nVar);
    }

    public l a(Context context, String str, c.a.a.a.e[] eVarArr, m mVar, n nVar) {
        f fVar = new f(a(this.i, str, mVar));
        if (eVarArr != null) {
            fVar.a(eVarArr);
        }
        return b(this.f32a, this.f33b, fVar, null, nVar, context);
    }

    protected c.a.a.a.m0.b a(c.a.a.a.m0.v.i iVar, c.a.a.a.t0.b bVar) {
        return new c.a.a.a.q0.i.s.g(bVar, iVar);
    }

    protected ExecutorService a() {
        return Executors.newCachedThreadPool();
    }

    public void a(int i) {
        if (i < 1000) {
            i = 10000;
        }
        this.f = i;
        c.a.a.a.t0.e eVarL = this.f32a.L();
        c.a.a.a.m0.t.a.a(eVarL, this.f);
        c.a.a.a.t0.c.a(eVarL, this.f);
    }

    public void a(boolean z) {
        a(z, z, z);
    }

    public void a(boolean z, boolean z2, boolean z3) {
        this.f32a.L().a("http.protocol.reject-relative-redirect", !z2);
        this.f32a.L().a("http.protocol.allow-circular-redirects", z3);
        this.f32a.a(new i(z));
    }

    public l b(Context context, String str, c.a.a.a.e[] eVarArr, m mVar, n nVar) {
        c.a.a.a.j0.t.g gVar = new c.a.a.a.j0.t.g(a(this.i, str, mVar));
        if (eVarArr != null) {
            gVar.a(eVarArr);
        }
        return b(this.f32a, this.f33b, gVar, null, nVar, context);
    }

    protected l b(c.a.a.a.q0.h.k kVar, c.a.a.a.v0.e eVar, c.a.a.a.j0.t.i iVar, String str, n nVar, Context context) {
        List<l> listSynchronizedList;
        if (iVar == null) {
            throw new IllegalArgumentException("HttpUriRequest must not be null");
        }
        if (nVar == null) {
            throw new IllegalArgumentException("ResponseHandler must not be null");
        }
        if (nVar.b() && !nVar.e()) {
            throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
        }
        if (str != null) {
            if ((iVar instanceof c.a.a.a.j0.t.e) && ((c.a.a.a.j0.t.e) iVar).c() != null && iVar.c("Content-Type")) {
                j.c("AsyncHttpClient", "Passed contentType will be ignored because HttpEntity sets content type");
            } else {
                iVar.a("Content-Type", str);
            }
        }
        nVar.a(iVar.l());
        nVar.a(iVar.h());
        b.c.a.a.b bVarA = a(kVar, eVar, iVar, str, nVar, context);
        this.h.submit(bVarA);
        l lVar = new l(bVarA);
        if (context != null) {
            synchronized (this.f34c) {
                listSynchronizedList = this.f34c.get(context);
                if (listSynchronizedList == null) {
                    listSynchronizedList = Collections.synchronizedList(new LinkedList());
                    this.f34c.put(context, listSynchronizedList);
                }
            }
            listSynchronizedList.add(lVar);
            Iterator<l> it = listSynchronizedList.iterator();
            while (it.hasNext()) {
                if (it.next().c()) {
                    it.remove();
                }
            }
        }
        return lVar;
    }

    public void b(int i) {
        if (i < 1000) {
            i = 10000;
        }
        this.g = i;
        c.a.a.a.t0.c.b(this.f32a.L(), this.g);
    }

    public void b(boolean z) {
        this.i = z;
    }

    public void c(int i) {
        if (i < 1000) {
            i = 10000;
        }
        a(i);
        b(i);
    }
}

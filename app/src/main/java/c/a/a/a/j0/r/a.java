package c.a.a.a.j0.r;

import c.a.a.a.n;
import java.net.InetAddress;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class a implements Cloneable {
    public static final a p = new C0005a().a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f83a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n f84b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final InetAddress f85c;
    private final String d;
    private final boolean e;
    private final boolean f;
    private final boolean g;
    private final int h;
    private final boolean i;
    private final Collection<String> j;
    private final Collection<String> k;
    private final int l;
    private final int m;
    private final int n;
    private final boolean o;

    /* JADX INFO: renamed from: c.a.a.a.j0.r.a$a, reason: collision with other inner class name */
    public static class C0005a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f86a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private n f87b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private InetAddress f88c;
        private String e;
        private boolean h;
        private Collection<String> k;
        private Collection<String> l;
        private boolean d = false;
        private boolean f = true;
        private int i = 50;
        private boolean g = true;
        private boolean j = true;
        private int m = -1;
        private int n = -1;
        private int o = -1;
        private boolean p = true;

        C0005a() {
        }

        public C0005a a(int i) {
            this.n = i;
            return this;
        }

        public C0005a a(n nVar) {
            this.f87b = nVar;
            return this;
        }

        public C0005a a(String str) {
            this.e = str;
            return this;
        }

        public C0005a a(InetAddress inetAddress) {
            this.f88c = inetAddress;
            return this;
        }

        public C0005a a(Collection<String> collection) {
            this.l = collection;
            return this;
        }

        public C0005a a(boolean z) {
            this.j = z;
            return this;
        }

        public a a() {
            return new a(this.f86a, this.f87b, this.f88c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p);
        }

        public C0005a b(int i) {
            this.m = i;
            return this;
        }

        public C0005a b(Collection<String> collection) {
            this.k = collection;
            return this;
        }

        public C0005a b(boolean z) {
            this.h = z;
            return this;
        }

        public C0005a c(int i) {
            this.i = i;
            return this;
        }

        public C0005a c(boolean z) {
            this.f86a = z;
            return this;
        }

        public C0005a d(int i) {
            this.o = i;
            return this;
        }

        public C0005a d(boolean z) {
            this.f = z;
            return this;
        }

        public C0005a e(boolean z) {
            this.g = z;
            return this;
        }

        @Deprecated
        public C0005a f(boolean z) {
            this.d = z;
            return this;
        }
    }

    a(boolean z, n nVar, InetAddress inetAddress, boolean z2, String str, boolean z3, boolean z4, boolean z5, int i, boolean z6, Collection<String> collection, Collection<String> collection2, int i2, int i3, int i4, boolean z7) {
        this.f83a = z;
        this.f84b = nVar;
        this.f85c = inetAddress;
        this.d = str;
        this.e = z3;
        this.f = z4;
        this.g = z5;
        this.h = i;
        this.i = z6;
        this.j = collection;
        this.k = collection2;
        this.l = i2;
        this.m = i3;
        this.n = i4;
        this.o = z7;
    }

    public static C0005a f() {
        return new C0005a();
    }

    public String a() {
        return this.d;
    }

    public Collection<String> b() {
        return this.k;
    }

    public Collection<String> c() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public a m0clone() {
        return (a) super.clone();
    }

    public boolean d() {
        return this.g;
    }

    public boolean e() {
        return this.f;
    }

    public String toString() {
        return "[expectContinueEnabled=" + this.f83a + ", proxy=" + this.f84b + ", localAddress=" + this.f85c + ", cookieSpec=" + this.d + ", redirectsEnabled=" + this.e + ", relativeRedirectsAllowed=" + this.f + ", maxRedirects=" + this.h + ", circularRedirectsAllowed=" + this.g + ", authenticationEnabled=" + this.i + ", targetPreferredAuthSchemes=" + this.j + ", proxyPreferredAuthSchemes=" + this.k + ", connectionRequestTimeout=" + this.l + ", connectTimeout=" + this.m + ", socketTimeout=" + this.n + ", decompressionEnabled=" + this.o + "]";
    }
}

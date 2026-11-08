package c.a.a.a.m0.v;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f141a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f142b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f143c;
    private final boolean d;
    private String e;

    public e(String str, int i, j jVar) {
        c.a.a.a.x0.a.a(str, "Scheme name");
        c.a.a.a.x0.a.a(i > 0 && i <= 65535, "Port is invalid");
        c.a.a.a.x0.a.a(jVar, "Socket factory");
        this.f141a = str.toLowerCase(Locale.ENGLISH);
        this.f143c = i;
        if (jVar instanceof f) {
            this.d = true;
        } else {
            if (jVar instanceof b) {
                this.d = true;
                this.f142b = new g((b) jVar);
                return;
            }
            this.d = false;
        }
        this.f142b = jVar;
    }

    @Deprecated
    public e(String str, l lVar, int i) {
        c.a.a.a.x0.a.a(str, "Scheme name");
        c.a.a.a.x0.a.a(lVar, "Socket factory");
        c.a.a.a.x0.a.a(i > 0 && i <= 65535, "Port is invalid");
        this.f141a = str.toLowerCase(Locale.ENGLISH);
        if (lVar instanceof c) {
            this.f142b = new h((c) lVar);
            this.d = true;
        } else {
            this.f142b = new k(lVar);
            this.d = false;
        }
        this.f143c = i;
    }

    public final int a() {
        return this.f143c;
    }

    public final int a(int i) {
        return i <= 0 ? this.f143c : i;
    }

    public final String b() {
        return this.f141a;
    }

    public final j c() {
        return this.f142b;
    }

    public final boolean d() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.f141a.equals(eVar.f141a) && this.f143c == eVar.f143c && this.d == eVar.d;
    }

    public int hashCode() {
        return c.a.a.a.x0.h.a(c.a.a.a.x0.h.a(c.a.a.a.x0.h.a(17, this.f143c), this.f141a), this.d);
    }

    public final String toString() {
        if (this.e == null) {
            this.e = this.f141a + ':' + Integer.toString(this.f143c);
        }
        return this.e;
    }
}

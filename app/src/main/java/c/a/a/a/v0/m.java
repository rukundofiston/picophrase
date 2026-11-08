package c.a.a.a.v0;

import c.a.a.a.q;
import c.a.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class m implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f420a;

    public m() {
        this(null);
    }

    public m(String str) {
        this.f420a = str;
    }

    @Override // c.a.a.a.r
    public void a(q qVar, e eVar) {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        if (qVar.c("User-Agent")) {
            return;
        }
        c.a.a.a.t0.e eVarK = qVar.k();
        String str = eVarK != null ? (String) eVarK.b("http.useragent") : null;
        if (str == null) {
            str = this.f420a;
        }
        if (str != null) {
            qVar.b("User-Agent", str);
        }
    }
}

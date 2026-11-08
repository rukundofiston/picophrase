package c.a.a.a.q0.g;

import c.a.a.a.q;
import c.a.a.a.s0.v;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class l extends a implements Serializable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<String, String> f216b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private transient Charset f217c;

    public l(Charset charset) {
        this.f217c = charset == null ? c.a.a.a.c.f55b : charset;
    }

    String a(q qVar) {
        String str = (String) qVar.k().b("http.auth.credential-charset");
        return str == null ? f().name() : str;
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        return this.f216b.get(str.toLowerCase(Locale.ROOT));
    }

    @Override // c.a.a.a.q0.g.a
    protected void a(c.a.a.a.x0.d dVar, int i, int i2) {
        c.a.a.a.f[] fVarArrA = c.a.a.a.s0.g.f371b.a(dVar, new v(i, dVar.length()));
        this.f216b.clear();
        for (c.a.a.a.f fVar : fVarArrA) {
            this.f216b.put(fVar.getName().toLowerCase(Locale.ROOT), fVar.getValue());
        }
    }

    @Override // c.a.a.a.i0.c
    public String c() {
        return a("realm");
    }

    public Charset f() {
        Charset charset = this.f217c;
        return charset != null ? charset : c.a.a.a.c.f55b;
    }

    protected Map<String, String> g() {
        return this.f216b;
    }
}

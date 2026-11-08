package c.a.a.a.q0.h;

import c.a.a.a.b0;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class n implements c.a.a.a.j0.o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f239b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f240a = new c.a.a.a.p0.b(n.class);

    static {
        new n();
        f239b = new String[]{"GET", "HEAD"};
    }

    @Override // c.a.a.a.j0.o
    public c.a.a.a.j0.t.i a(c.a.a.a.q qVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) throws b0 {
        URI uriC = c(qVar, sVar, eVar);
        String strB = qVar.g().b();
        if (strB.equalsIgnoreCase("HEAD")) {
            return new c.a.a.a.j0.t.g(uriC);
        }
        if (!strB.equalsIgnoreCase("GET") && sVar.j().c() == 307) {
            c.a.a.a.j0.t.j jVarA = c.a.a.a.j0.t.j.a(qVar);
            jVarA.a(uriC);
            return jVarA.a();
        }
        return new c.a.a.a.j0.t.f(uriC);
    }

    protected URI a(String str) throws b0 {
        try {
            c.a.a.a.j0.w.c cVar = new c.a.a.a.j0.w.c(new URI(str).normalize());
            String strC = cVar.c();
            if (strC != null) {
                cVar.b(strC.toLowerCase(Locale.ROOT));
            }
            if (c.a.a.a.x0.i.c(cVar.d())) {
                cVar.c("/");
            }
            return cVar.a();
        } catch (URISyntaxException e) {
            throw new b0("Invalid redirect URI: " + str, e);
        }
    }

    @Override // c.a.a.a.j0.o
    public boolean b(c.a.a.a.q qVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.x0.a.a(sVar, "HTTP response");
        int iC = sVar.j().c();
        String strB = qVar.g().b();
        c.a.a.a.e eVarD = sVar.d("location");
        if (iC != 307) {
            switch (iC) {
                case 301:
                    break;
                case 302:
                    return b(strB) && eVarD != null;
                case 303:
                    return true;
                default:
                    return false;
            }
        }
        return b(strB);
    }

    protected boolean b(String str) {
        for (String str2 : f239b) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public URI c(c.a.a.a.q qVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) throws b0 {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.x0.a.a(sVar, "HTTP response");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        c.a.a.a.j0.v.a aVarA = c.a.a.a.j0.v.a.a(eVar);
        c.a.a.a.e eVarD = sVar.d("location");
        if (eVarD == null) {
            throw new b0("Received redirect response " + sVar.j() + " but no location header");
        }
        String value = eVarD.getValue();
        if (this.f240a.a()) {
            this.f240a.a("Redirect requested to location '" + value + "'");
        }
        c.a.a.a.j0.r.a aVarN = aVarA.n();
        URI uriA = a(value);
        try {
            if (!uriA.isAbsolute()) {
                if (!aVarN.e()) {
                    throw new b0("Relative redirect location '" + uriA + "' not allowed");
                }
                c.a.a.a.n nVarC = aVarA.c();
                c.a.a.a.x0.b.a(nVarC, "Target host");
                uriA = c.a.a.a.j0.w.d.a(c.a.a.a.j0.w.d.a(new URI(qVar.g().c()), nVarC, false), uriA);
            }
            u uVar = (u) aVarA.a("http.protocol.redirect-locations");
            if (uVar == null) {
                uVar = new u();
                eVar.a("http.protocol.redirect-locations", uVar);
            }
            if (aVarN.d() || !uVar.b(uriA)) {
                uVar.a(uriA);
                return uriA;
            }
            throw new c.a.a.a.j0.e("Circular redirect to '" + uriA + "'");
        } catch (URISyntaxException e) {
            throw new b0(e.getMessage(), e);
        }
    }
}

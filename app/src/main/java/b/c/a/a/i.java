package b.c.a.a;

import c.a.a.a.b0;
import c.a.a.a.q;
import c.a.a.a.q0.h.u;
import c.a.a.a.s;
import java.net.URI;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes.dex */
class i extends c.a.a.a.q0.h.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f47a;

    public i(boolean z) {
        this.f47a = z;
    }

    @Override // c.a.a.a.j0.n
    public boolean a(s sVar, c.a.a.a.v0.e eVar) {
        if (!this.f47a) {
            return false;
        }
        if (sVar == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        int iC = sVar.j().c();
        if (iC == 307) {
            return true;
        }
        switch (iC) {
            case 301:
            case 302:
            case 303:
                return true;
            default:
                return false;
        }
    }

    @Override // c.a.a.a.j0.n
    public URI b(s sVar, c.a.a.a.v0.e eVar) throws b0 {
        URI uriA;
        if (sVar == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        c.a.a.a.e eVarD = sVar.d("location");
        if (eVarD == null) {
            throw new b0("Received redirect response " + sVar.j() + " but no location header");
        }
        String strReplaceAll = eVarD.getValue().replaceAll(" ", "%20");
        try {
            URI uri = new URI(strReplaceAll);
            c.a.a.a.t0.e eVarK = sVar.k();
            if (!uri.isAbsolute()) {
                if (eVarK.c("http.protocol.reject-relative-redirect")) {
                    throw new b0("Relative redirect location '" + uri + "' not allowed");
                }
                c.a.a.a.n nVar = (c.a.a.a.n) eVar.a("http.target_host");
                if (nVar == null) {
                    throw new IllegalStateException("Target host not available in the HTTP context");
                }
                try {
                    uri = c.a.a.a.j0.w.d.a(c.a.a.a.j0.w.d.a(new URI(((q) eVar.a("http.request")).g().c()), nVar, true), uri);
                } catch (URISyntaxException e) {
                    throw new b0(e.getMessage(), e);
                }
            }
            if (eVarK.a("http.protocol.allow-circular-redirects")) {
                u uVar = (u) eVar.a("http.protocol.redirect-locations");
                if (uVar == null) {
                    uVar = new u();
                    eVar.a("http.protocol.redirect-locations", uVar);
                }
                if (uri.getFragment() != null) {
                    try {
                        uriA = c.a.a.a.j0.w.d.a(uri, new c.a.a.a.n(uri.getHost(), uri.getPort(), uri.getScheme()), true);
                    } catch (URISyntaxException e2) {
                        throw new b0(e2.getMessage(), e2);
                    }
                } else {
                    uriA = uri;
                }
                if (uVar.b(uriA)) {
                    throw new c.a.a.a.j0.e("Circular redirect to '" + uriA + "'");
                }
                uVar.a(uriA);
            }
            return uri;
        } catch (URISyntaxException e3) {
            throw new b0("Invalid redirect URI: " + strReplaceAll, e3);
        }
    }
}

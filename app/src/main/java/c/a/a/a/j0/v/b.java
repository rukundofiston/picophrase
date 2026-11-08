package c.a.a.a.j0.v;

import c.a.a.a.n;
import c.a.a.a.n0.k;
import c.a.a.a.q;
import c.a.a.a.r;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f95a = new c.a.a.a.p0.b(b.class);

    @Override // c.a.a.a.r
    public void a(q qVar, c.a.a.a.v0.e eVar) {
        URI uri;
        c.a.a.a.e eVarA;
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        if (qVar.g().b().equalsIgnoreCase("CONNECT")) {
            return;
        }
        a aVarA = a.a(eVar);
        c.a.a.a.j0.h hVarJ = aVarA.j();
        if (hVarJ == null) {
            this.f95a.a("Cookie store not specified in HTTP context");
            return;
        }
        c.a.a.a.l0.a<k> aVarI = aVarA.i();
        if (aVarI == null) {
            this.f95a.a("CookieSpec registry not specified in HTTP context");
            return;
        }
        n nVarC = aVarA.c();
        if (nVarC == null) {
            this.f95a.a("Target host not set in the context");
            return;
        }
        c.a.a.a.m0.u.e eVarL = aVarA.l();
        if (eVarL == null) {
            this.f95a.a("Connection route not set in the context");
            return;
        }
        String strA = aVarA.n().a();
        if (strA == null) {
            strA = "default";
        }
        if (this.f95a.a()) {
            this.f95a.a("CookieSpec selected: " + strA);
        }
        if (qVar instanceof c.a.a.a.j0.t.i) {
            uri = ((c.a.a.a.j0.t.i) qVar).h();
        } else {
            try {
                uri = new URI(qVar.g().c());
            } catch (URISyntaxException unused) {
                uri = null;
            }
        }
        String path = uri != null ? uri.getPath() : null;
        String strB = nVarC.b();
        int iC = nVarC.c();
        if (iC < 0) {
            iC = eVarL.b().c();
        }
        boolean z = false;
        if (iC < 0) {
            iC = 0;
        }
        if (c.a.a.a.x0.i.c(path)) {
            path = "/";
        }
        c.a.a.a.n0.f fVar = new c.a.a.a.n0.f(strB, iC, path, eVarL.a());
        k kVarA = aVarI.a(strA);
        if (kVarA == null) {
            if (this.f95a.a()) {
                this.f95a.a("Unsupported cookie policy: " + strA);
                return;
            }
            return;
        }
        c.a.a.a.n0.i iVarA = kVarA.a(aVarA);
        List<c.a.a.a.n0.c> listA = hVarJ.a();
        ArrayList arrayList = new ArrayList();
        Date date = new Date();
        for (c.a.a.a.n0.c cVar : listA) {
            if (cVar.a(date)) {
                if (this.f95a.a()) {
                    this.f95a.a("Cookie " + cVar + " expired");
                }
                z = true;
            } else if (iVarA.b(cVar, fVar)) {
                if (this.f95a.a()) {
                    this.f95a.a("Cookie " + cVar + " match " + fVar);
                }
                arrayList.add(cVar);
            }
        }
        if (z) {
            hVarJ.a(date);
        }
        if (!arrayList.isEmpty()) {
            Iterator<c.a.a.a.e> it = iVarA.a(arrayList).iterator();
            while (it.hasNext()) {
                qVar.a(it.next());
            }
        }
        if (iVarA.b() > 0 && (eVarA = iVarA.a()) != null) {
            qVar.a(eVarA);
        }
        eVar.a("http.cookie-spec", iVarA);
        eVar.a("http.cookie-origin", fVar);
    }
}

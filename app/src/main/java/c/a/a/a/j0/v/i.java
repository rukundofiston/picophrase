package c.a.a.a.j0.v;

import c.a.a.a.n0.m;
import c.a.a.a.s;
import c.a.a.a.u;

/* JADX INFO: loaded from: classes.dex */
public class i implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f101a = new c.a.a.a.p0.b(i.class);

    private static String a(c.a.a.a.n0.c cVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(cVar.getName());
        sb.append("=\"");
        String value = cVar.getValue();
        if (value != null) {
            if (value.length() > 100) {
                value = value.substring(0, 100) + "...";
            }
            sb.append(value);
        }
        sb.append("\"");
        sb.append(", version:");
        sb.append(Integer.toString(cVar.b()));
        sb.append(", domain:");
        sb.append(cVar.c());
        sb.append(", path:");
        sb.append(cVar.e());
        sb.append(", expiry:");
        sb.append(cVar.d());
        return sb.toString();
    }

    private void a(c.a.a.a.h hVar, c.a.a.a.n0.i iVar, c.a.a.a.n0.f fVar, c.a.a.a.j0.h hVar2) {
        while (hVar.hasNext()) {
            c.a.a.a.e eVarNextHeader = hVar.nextHeader();
            try {
                for (c.a.a.a.n0.c cVar : iVar.a(eVarNextHeader, fVar)) {
                    try {
                        iVar.a(cVar, fVar);
                        hVar2.a(cVar);
                        if (this.f101a.a()) {
                            this.f101a.a("Cookie accepted [" + a(cVar) + "]");
                        }
                    } catch (m e) {
                        if (this.f101a.d()) {
                            this.f101a.d("Cookie rejected [" + a(cVar) + "] " + e.getMessage());
                        }
                    }
                }
            } catch (m e2) {
                if (this.f101a.d()) {
                    this.f101a.d("Invalid cookie header: \"" + eVarNextHeader + "\". " + e2.getMessage());
                }
            }
        }
    }

    @Override // c.a.a.a.u
    public void a(s sVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.p0.b bVar;
        String str;
        c.a.a.a.x0.a.a(sVar, "HTTP request");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        a aVarA = a.a(eVar);
        c.a.a.a.n0.i iVarH = aVarA.h();
        if (iVarH == null) {
            bVar = this.f101a;
            str = "Cookie spec not specified in HTTP context";
        } else {
            c.a.a.a.j0.h hVarJ = aVarA.j();
            if (hVarJ == null) {
                bVar = this.f101a;
                str = "Cookie store not specified in HTTP context";
            } else {
                c.a.a.a.n0.f fVarG = aVarA.g();
                if (fVarG != null) {
                    a(sVar.e("Set-Cookie"), iVarH, fVarG, hVarJ);
                    if (iVarH.b() > 0) {
                        a(sVar.e("Set-Cookie2"), iVarH, fVarG, hVarJ);
                        return;
                    }
                    return;
                }
                bVar = this.f101a;
                str = "Cookie origin not specified in HTTP context";
            }
        }
        bVar.a(str);
    }
}

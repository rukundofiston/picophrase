package c.a.a.a.q0.g;

import c.a.a.a.i0.o;
import c.a.a.a.n;
import c.a.a.a.s;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f200a;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f201a;

        static {
            int[] iArr = new int[c.a.a.a.i0.b.values().length];
            f201a = iArr;
            try {
                iArr[c.a.a.a.i0.b.CHALLENGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f201a[c.a.a.a.i0.b.HANDSHAKE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f201a[c.a.a.a.i0.b.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f201a[c.a.a.a.i0.b.FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f201a[c.a.a.a.i0.b.UNCHALLENGED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public f(c.a.a.a.p0.b bVar) {
        this.f200a = bVar == null ? new c.a.a.a.p0.b(getClass()) : bVar;
    }

    public boolean a(n nVar, s sVar, c.a.a.a.j0.c cVar, c.a.a.a.i0.h hVar, c.a.a.a.v0.e eVar) {
        Queue<c.a.a.a.i0.a> queueA;
        try {
            if (this.f200a.a()) {
                this.f200a.a(nVar.e() + " requested authentication");
            }
            Map<String, c.a.a.a.e> mapB = cVar.b(nVar, sVar, eVar);
            if (mapB.isEmpty()) {
                this.f200a.a("Response contains no authentication challenges");
                return false;
            }
            c.a.a.a.i0.c cVarB = hVar.b();
            int i = a.f201a[hVar.d().ordinal()];
            if (i != 1 && i != 2) {
                if (i == 3) {
                    hVar.e();
                } else {
                    if (i == 4) {
                        return false;
                    }
                    if (i != 5) {
                    }
                }
                queueA = cVar.a(mapB, nVar, sVar, eVar);
                if (queueA != null || queueA.isEmpty()) {
                    return false;
                }
                if (this.f200a.a()) {
                    this.f200a.a("Selected authentication options: " + queueA);
                }
                hVar.a(c.a.a.a.i0.b.CHALLENGED);
                hVar.a(queueA);
                return true;
            }
            if (cVarB == null) {
                this.f200a.a("Auth scheme is null");
                cVar.b(nVar, (c.a.a.a.i0.c) null, eVar);
                hVar.e();
                hVar.a(c.a.a.a.i0.b.FAILURE);
                return false;
            }
            if (cVarB != null) {
                c.a.a.a.e eVar2 = mapB.get(cVarB.b().toLowerCase(Locale.ROOT));
                if (eVar2 != null) {
                    this.f200a.a("Authorization challenge processed");
                    cVarB.a(eVar2);
                    if (!cVarB.a()) {
                        hVar.a(c.a.a.a.i0.b.HANDSHAKE);
                        return true;
                    }
                    this.f200a.a("Authentication failed");
                    cVar.b(nVar, hVar.b(), eVar);
                    hVar.e();
                    hVar.a(c.a.a.a.i0.b.FAILURE);
                    return false;
                }
                hVar.e();
            }
            queueA = cVar.a(mapB, nVar, sVar, eVar);
            if (queueA != null) {
            }
            return false;
        } catch (o e) {
            if (this.f200a.d()) {
                this.f200a.d("Malformed challenge: " + e.getMessage());
            }
            hVar.e();
            return false;
        }
    }

    public boolean b(n nVar, s sVar, c.a.a.a.j0.c cVar, c.a.a.a.i0.h hVar, c.a.a.a.v0.e eVar) {
        if (cVar.a(nVar, sVar, eVar)) {
            this.f200a.a("Authentication required");
            if (hVar.d() == c.a.a.a.i0.b.SUCCESS) {
                cVar.b(nVar, hVar.b(), eVar);
            }
            return true;
        }
        int i = a.f201a[hVar.d().ordinal()];
        if (i == 1 || i == 2) {
            this.f200a.a("Authentication succeeded");
            hVar.a(c.a.a.a.i0.b.SUCCESS);
            cVar.a(nVar, hVar.b(), eVar);
            return false;
        }
        if (i == 3) {
            return false;
        }
        hVar.a(c.a.a.a.i0.b.UNCHALLENGED);
        return false;
    }
}

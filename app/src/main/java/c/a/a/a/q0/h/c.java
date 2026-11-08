package c.a.a.a.q0.h;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
abstract class c implements c.a.a.a.j0.c {
    private static final List<String> d = Collections.unmodifiableList(Arrays.asList("Negotiate", "Kerberos", "NTLM", "Digest", "Basic"));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f223a = new c.a.a.a.p0.b(getClass());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f224b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f225c;

    c(int i, String str) {
        this.f224b = i;
        this.f225c = str;
    }

    abstract Collection<String> a(c.a.a.a.j0.r.a aVar);

    @Override // c.a.a.a.j0.c
    public Queue<c.a.a.a.i0.a> a(Map<String, c.a.a.a.e> map, c.a.a.a.n nVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.p0.b bVar;
        String str;
        c.a.a.a.x0.a.a(map, "Map of auth challenges");
        c.a.a.a.x0.a.a(nVar, "Host");
        c.a.a.a.x0.a.a(sVar, "HTTP response");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        c.a.a.a.j0.v.a aVarA = c.a.a.a.j0.v.a.a(eVar);
        LinkedList linkedList = new LinkedList();
        c.a.a.a.l0.a<c.a.a.a.i0.e> aVarF = aVarA.f();
        if (aVarF == null) {
            bVar = this.f223a;
            str = "Auth scheme registry not set in the context";
        } else {
            c.a.a.a.j0.i iVarK = aVarA.k();
            if (iVarK != null) {
                Collection<String> collectionA = a(aVarA.n());
                if (collectionA == null) {
                    collectionA = d;
                }
                if (this.f223a.a()) {
                    this.f223a.a("Authentication schemes in the order of preference: " + collectionA);
                }
                for (String str2 : collectionA) {
                    c.a.a.a.e eVar2 = map.get(str2.toLowerCase(Locale.ROOT));
                    if (eVar2 != null) {
                        c.a.a.a.i0.e eVarA = aVarF.a(str2);
                        if (eVarA != null) {
                            c.a.a.a.i0.c cVarA = eVarA.a(eVar);
                            cVarA.a(eVar2);
                            c.a.a.a.i0.m mVarA = iVarK.a(new c.a.a.a.i0.g(nVar.b(), nVar.c(), cVarA.c(), cVarA.b()));
                            if (mVarA != null) {
                                linkedList.add(new c.a.a.a.i0.a(cVarA, mVarA));
                            }
                        } else if (this.f223a.d()) {
                            this.f223a.d("Authentication scheme " + str2 + " not supported");
                        }
                    } else if (this.f223a.a()) {
                        this.f223a.a("Challenge for " + str2 + " authentication scheme not available");
                    }
                }
                return linkedList;
            }
            bVar = this.f223a;
            str = "Credentials provider not set in the context";
        }
        bVar.a(str);
        return linkedList;
    }

    @Override // c.a.a.a.j0.c
    public void a(c.a.a.a.n nVar, c.a.a.a.i0.c cVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(nVar, "Host");
        c.a.a.a.x0.a.a(cVar, "Auth scheme");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        c.a.a.a.j0.v.a aVarA = c.a.a.a.j0.v.a.a(eVar);
        if (a(cVar)) {
            c.a.a.a.j0.a aVarE = aVarA.e();
            if (aVarE == null) {
                aVarE = new d();
                aVarA.a(aVarE);
            }
            if (this.f223a.a()) {
                this.f223a.a("Caching '" + cVar.b() + "' auth scheme for " + nVar);
            }
            aVarE.a(nVar, cVar);
        }
    }

    protected boolean a(c.a.a.a.i0.c cVar) {
        if (cVar == null || !cVar.a()) {
            return false;
        }
        String strB = cVar.b();
        return strB.equalsIgnoreCase("Basic") || strB.equalsIgnoreCase("Digest");
    }

    @Override // c.a.a.a.j0.c
    public boolean a(c.a.a.a.n nVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(sVar, "HTTP response");
        return sVar.j().c() == this.f224b;
    }

    @Override // c.a.a.a.j0.c
    public Map<String, c.a.a.a.e> b(c.a.a.a.n nVar, c.a.a.a.s sVar, c.a.a.a.v0.e eVar) throws c.a.a.a.i0.o {
        c.a.a.a.x0.d dVar;
        int iB;
        c.a.a.a.x0.a.a(sVar, "HTTP response");
        c.a.a.a.e[] eVarArrB = sVar.b(this.f225c);
        HashMap map = new HashMap(eVarArrB.length);
        for (c.a.a.a.e eVar2 : eVarArrB) {
            if (eVar2 instanceof c.a.a.a.d) {
                c.a.a.a.d dVar2 = (c.a.a.a.d) eVar2;
                dVar = dVar2.c();
                iB = dVar2.b();
            } else {
                String value = eVar2.getValue();
                if (value == null) {
                    throw new c.a.a.a.i0.o("Header value is null");
                }
                dVar = new c.a.a.a.x0.d(value.length());
                dVar.a(value);
                iB = 0;
            }
            while (iB < dVar.length() && c.a.a.a.v0.d.a(dVar.charAt(iB))) {
                iB++;
            }
            int i = iB;
            while (i < dVar.length() && !c.a.a.a.v0.d.a(dVar.charAt(i))) {
                i++;
            }
            map.put(dVar.a(iB, i).toLowerCase(Locale.ROOT), eVar2);
        }
        return map;
    }

    @Override // c.a.a.a.j0.c
    public void b(c.a.a.a.n nVar, c.a.a.a.i0.c cVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(nVar, "Host");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        c.a.a.a.j0.a aVarE = c.a.a.a.j0.v.a.a(eVar).e();
        if (aVarE != null) {
            if (this.f223a.a()) {
                this.f223a.a("Clearing cached auth scheme for " + nVar);
            }
            aVarE.b(nVar);
        }
    }
}

package c.a.a.a.j0.v;

import c.a.a.a.i0.l;
import c.a.a.a.i0.m;
import c.a.a.a.q;
import c.a.a.a.r;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
abstract class d implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.a.a.p0.b f97a = new c.a.a.a.p0.b(getClass());

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f98a;

        static {
            int[] iArr = new int[c.a.a.a.i0.b.values().length];
            f98a = iArr;
            try {
                iArr[c.a.a.a.i0.b.FAILURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f98a[c.a.a.a.i0.b.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f98a[c.a.a.a.i0.b.CHALLENGED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private c.a.a.a.e a(c.a.a.a.i0.c cVar, m mVar, q qVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.b.a(cVar, "Auth scheme");
        return cVar instanceof l ? ((l) cVar).a(mVar, qVar, eVar) : cVar.a(mVar, qVar);
    }

    private void a(c.a.a.a.i0.c cVar) {
        c.a.a.a.x0.b.a(cVar, "Auth scheme");
    }

    void a(c.a.a.a.i0.h hVar, q qVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.i0.c cVarB = hVar.b();
        m mVarC = hVar.c();
        int i = a.f98a[hVar.d().ordinal()];
        if (i != 1) {
            if (i == 2) {
                a(cVarB);
                if (cVarB.d()) {
                    return;
                }
            } else if (i == 3) {
                Queue<c.a.a.a.i0.a> queueA = hVar.a();
                if (queueA != null) {
                    while (!queueA.isEmpty()) {
                        c.a.a.a.i0.a aVarRemove = queueA.remove();
                        c.a.a.a.i0.c cVarA = aVarRemove.a();
                        m mVarB = aVarRemove.b();
                        hVar.a(cVarA, mVarB);
                        if (this.f97a.a()) {
                            this.f97a.a("Generating response to an authentication challenge using " + cVarA.b() + " scheme");
                        }
                        try {
                            qVar.a(a(cVarA, mVarB, qVar, eVar));
                            return;
                        } catch (c.a.a.a.i0.i e) {
                            if (this.f97a.d()) {
                                this.f97a.d(cVarA + " authentication error: " + e.getMessage());
                            }
                        }
                    }
                    return;
                }
                a(cVarB);
            }
            if (cVarB != null) {
                try {
                    qVar.a(a(cVarB, mVarC, qVar, eVar));
                } catch (c.a.a.a.i0.i e2) {
                    if (this.f97a.b()) {
                        this.f97a.b(cVarB + " authentication error: " + e2.getMessage());
                    }
                }
            }
        }
    }
}

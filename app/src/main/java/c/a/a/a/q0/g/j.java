package c.a.a.a.q0.g;

import c.a.a.a.i0.n;
import c.a.a.a.i0.o;
import c.a.a.a.i0.p;
import c.a.a.a.q;

/* JADX INFO: loaded from: classes.dex */
public class j extends c.a.a.a.q0.g.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h f211b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f212c;
    private String d;

    enum a {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        MSG_TYPE1_GENERATED,
        MSG_TYPE2_RECEVIED,
        MSG_TYPE3_GENERATED,
        FAILED
    }

    public j() {
        this(new i());
    }

    public j(h hVar) {
        c.a.a.a.x0.a.a(hVar, "NTLM engine");
        this.f211b = hVar;
        this.f212c = a.UNINITIATED;
        this.d = null;
    }

    @Override // c.a.a.a.i0.c
    public c.a.a.a.e a(c.a.a.a.i0.m mVar, q qVar) throws c.a.a.a.i0.i {
        try {
            p pVar = (p) mVar;
            a aVar = this.f212c;
            if (aVar == a.FAILED) {
                throw new c.a.a.a.i0.i("NTLM authentication failed");
            }
            if (aVar == a.CHALLENGE_RECEIVED) {
                pVar.c();
                throw null;
            }
            if (aVar == a.MSG_TYPE2_RECEVIED) {
                pVar.d();
                throw null;
            }
            throw new c.a.a.a.i0.i("Unexpected state: " + this.f212c);
        } catch (ClassCastException unused) {
            throw new n("Credentials cannot be used for NTLM authentication: " + mVar.getClass().getName());
        }
    }

    @Override // c.a.a.a.q0.g.a
    protected void a(c.a.a.a.x0.d dVar, int i, int i2) throws o {
        a aVar;
        String strB = dVar.b(i, i2);
        this.d = strB;
        if (strB.isEmpty()) {
            aVar = this.f212c == a.UNINITIATED ? a.CHALLENGE_RECEIVED : a.FAILED;
        } else {
            if (this.f212c.compareTo(a.MSG_TYPE1_GENERATED) < 0) {
                this.f212c = a.FAILED;
                throw new o("Out of sequence NTLM response message");
            }
            if (this.f212c != a.MSG_TYPE1_GENERATED) {
                return;
            } else {
                aVar = a.MSG_TYPE2_RECEVIED;
            }
        }
        this.f212c = aVar;
    }

    @Override // c.a.a.a.i0.c
    public boolean a() {
        a aVar = this.f212c;
        return aVar == a.MSG_TYPE3_GENERATED || aVar == a.FAILED;
    }

    @Override // c.a.a.a.i0.c
    public String b() {
        return "ntlm";
    }

    @Override // c.a.a.a.i0.c
    public String c() {
        return null;
    }

    @Override // c.a.a.a.i0.c
    public boolean d() {
        return true;
    }
}

package c.a.a.a.q0.i;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class g implements c.a.a.a.m0.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f264a = new c.a.a.a.p0.b(g.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final c.a.a.a.m0.v.i f265b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final c.a.a.a.m0.j f266c;

    public g(c.a.a.a.m0.v.i iVar) {
        c.a.a.a.x0.a.a(iVar, "Scheme registry");
        this.f265b = iVar;
        this.f266c = new q();
    }

    private c.a.a.a.m0.v.i a(c.a.a.a.v0.e eVar) {
        c.a.a.a.m0.v.i iVar = (c.a.a.a.m0.v.i) eVar.a("http.scheme-registry");
        return iVar == null ? this.f265b : iVar;
    }

    @Override // c.a.a.a.m0.d
    public c.a.a.a.m0.q a() {
        return new f();
    }

    @Override // c.a.a.a.m0.d
    public void a(c.a.a.a.m0.q qVar, c.a.a.a.n nVar, c.a.a.a.v0.e eVar, c.a.a.a.t0.e eVar2) throws SocketException {
        c.a.a.a.x0.a.a(qVar, "Connection");
        c.a.a.a.x0.a.a(nVar, "Target host");
        c.a.a.a.x0.a.a(eVar2, "Parameters");
        c.a.a.a.x0.b.a(qVar.isOpen(), "Connection must be open");
        c.a.a.a.m0.v.e eVarB = a(eVar).b(nVar.d());
        c.a.a.a.x0.b.a(eVarB.c() instanceof c.a.a.a.m0.v.f, "Socket factory must implement SchemeLayeredSocketFactory");
        c.a.a.a.m0.v.f fVar = (c.a.a.a.m0.v.f) eVarB.c();
        Socket socketA = fVar.a(qVar.e(), nVar.b(), eVarB.a(nVar.c()), eVar2);
        a(socketA, eVar, eVar2);
        qVar.a(socketA, nVar, fVar.a(socketA), eVar2);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:39:0x00cb A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:28:0x00ab, please report this as an issue */
    @Override // c.a.a.a.m0.d
    public void a(c.a.a.a.m0.q qVar, c.a.a.a.n nVar, InetAddress inetAddress, c.a.a.a.v0.e eVar, c.a.a.a.t0.e eVar2) throws c.a.a.a.m0.f, SocketException {
        c.a.a.a.x0.a.a(qVar, "Connection");
        c.a.a.a.x0.a.a(nVar, "Target host");
        c.a.a.a.x0.a.a(eVar2, "HTTP parameters");
        int i = 1;
        c.a.a.a.x0.b.a(!qVar.isOpen(), "Connection must not be open");
        c.a.a.a.m0.v.e eVarB = a(eVar).b(nVar.d());
        c.a.a.a.m0.v.j jVarC = eVarB.c();
        InetAddress[] inetAddressArrA = a(nVar.b());
        int iA = eVarB.a(nVar.c());
        int i2 = 0;
        int i3 = 0;
        while (i3 < inetAddressArrA.length) {
            InetAddress inetAddress2 = inetAddressArrA[i3];
            boolean z = i3 == inetAddressArrA.length - i;
            Socket socketA = jVarC.a(eVar2);
            qVar.a(socketA, nVar);
            c.a.a.a.m0.m mVar = new c.a.a.a.m0.m(nVar, inetAddress2, iA);
            InetSocketAddress inetSocketAddress = inetAddress != 0 ? new InetSocketAddress(inetAddress, i2) : null;
            if (this.f264a.a()) {
                this.f264a.a("Connecting to " + mVar);
            }
            try {
                Socket socketA2 = jVarC.a(socketA, mVar, inetSocketAddress, eVar2);
                if (socketA != socketA2) {
                    qVar.a(socketA2, nVar);
                    socketA = socketA2;
                }
                a(socketA, eVar, eVar2);
                qVar.b(jVarC.a(socketA), eVar2);
                return;
            } catch (c.a.a.a.m0.f e) {
                if (z) {
                    throw e;
                }
                if (this.f264a.a()) {
                    this.f264a.a("Connect to " + mVar + " timed out. Connection will be retried using another IP address");
                }
                i3++;
                i = 1;
                i2 = 0;
            } catch (ConnectException e2) {
                if (z) {
                    throw e2;
                }
                if (this.f264a.a()) {
                    this.f264a.a("Connect to " + mVar + " timed out. Connection will be retried using another IP address");
                }
                i3++;
                i = 1;
                i2 = 0;
            }
        }
    }

    protected void a(Socket socket, c.a.a.a.v0.e eVar, c.a.a.a.t0.e eVar2) throws SocketException {
        socket.setTcpNoDelay(c.a.a.a.t0.c.e(eVar2));
        socket.setSoTimeout(c.a.a.a.t0.c.d(eVar2));
        int iB = c.a.a.a.t0.c.b(eVar2);
        if (iB >= 0) {
            socket.setSoLinger(iB > 0, iB);
        }
    }

    protected InetAddress[] a(String str) {
        return this.f266c.a(str);
    }
}

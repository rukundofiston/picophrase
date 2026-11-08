package c.a.a.a.m0.w;

import c.a.a.a.m0.m;
import c.a.a.a.n;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class i implements c.a.a.a.m0.v.f, c.a.a.a.m0.v.b, c.a.a.a.m0.v.c {
    public static final l f = new b();
    public static final l g = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final SSLSocketFactory f158a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.m0.v.a f159b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile l f160c;
    private final String[] d;
    private final String[] e;

    static {
        new j();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public i(KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        f fVarB = g.b();
        fVarB.a(keyStore);
        this(fVarB.a(), g);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public i(SSLContext sSLContext, l lVar) {
        this(sSLContext.getSocketFactory(), null, null, lVar);
        c.a.a.a.x0.a.a(sSLContext, "SSL context");
    }

    public i(SSLSocketFactory sSLSocketFactory, String[] strArr, String[] strArr2, l lVar) {
        c.a.a.a.x0.a.a(sSLSocketFactory, "SSL socket factory");
        this.f158a = sSLSocketFactory;
        this.d = strArr;
        this.e = strArr2;
        this.f160c = lVar == null ? g : lVar;
        this.f159b = null;
    }

    private void a(SSLSocket sSLSocket, String str) throws IOException {
        try {
            this.f160c.verify(str, sSLSocket);
        } catch (IOException e) {
            try {
                sSLSocket.close();
            } catch (Exception unused) {
            }
            throw e;
        }
    }

    public static i b() {
        return new i(g.a(), g);
    }

    private void b(SSLSocket sSLSocket) {
        String[] strArr = this.d;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = this.e;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
        a(sSLSocket);
    }

    public Socket a() {
        return a((c.a.a.a.v0.e) null);
    }

    public Socket a(int i, Socket socket, n nVar, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, c.a.a.a.v0.e eVar) throws IOException {
        c.a.a.a.x0.a.a(nVar, "HTTP host");
        c.a.a.a.x0.a.a(inetSocketAddress, "Remote address");
        if (socket == null) {
            socket = a(eVar);
        }
        if (inetSocketAddress2 != null) {
            socket.bind(inetSocketAddress2);
        }
        try {
            socket.connect(inetSocketAddress, i);
            if (!(socket instanceof SSLSocket)) {
                return a(socket, nVar.b(), inetSocketAddress.getPort(), eVar);
            }
            SSLSocket sSLSocket = (SSLSocket) socket;
            sSLSocket.startHandshake();
            a(sSLSocket, nVar.b());
            return socket;
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException unused) {
            }
            throw e;
        }
    }

    @Override // c.a.a.a.m0.v.j
    public Socket a(c.a.a.a.t0.e eVar) {
        return a((c.a.a.a.v0.e) null);
    }

    public Socket a(c.a.a.a.v0.e eVar) {
        SSLSocket sSLSocket = (SSLSocket) this.f158a.createSocket();
        b(sSLSocket);
        return sSLSocket;
    }

    @Override // c.a.a.a.m0.v.f
    public Socket a(Socket socket, String str, int i, c.a.a.a.t0.e eVar) {
        return a(socket, str, i, (c.a.a.a.v0.e) null);
    }

    public Socket a(Socket socket, String str, int i, c.a.a.a.v0.e eVar) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.f158a.createSocket(socket, str, i, true);
        b(sSLSocket);
        sSLSocket.startHandshake();
        a(sSLSocket, str);
        return sSLSocket;
    }

    @Override // c.a.a.a.m0.v.l
    public Socket a(Socket socket, String str, int i, InetAddress inetAddress, int i2, c.a.a.a.t0.e eVar) {
        c.a.a.a.m0.v.a aVar = this.f159b;
        InetAddress inetAddressA = aVar != null ? aVar.a(str) : InetAddress.getByName(str);
        InetSocketAddress inetSocketAddress = null;
        if (inetAddress != null || i2 > 0) {
            if (i2 <= 0) {
                i2 = 0;
            }
            inetSocketAddress = new InetSocketAddress(inetAddress, i2);
        }
        return a(socket, new m(new n(str, i), inetAddressA, i), inetSocketAddress, eVar);
    }

    @Override // c.a.a.a.m0.v.b
    public Socket a(Socket socket, String str, int i, boolean z) {
        return a(socket, str, i, (c.a.a.a.v0.e) null);
    }

    @Override // c.a.a.a.m0.v.j
    public Socket a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, c.a.a.a.t0.e eVar) throws SocketException {
        c.a.a.a.x0.a.a(inetSocketAddress, "Remote address");
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        n nVarA = inetSocketAddress instanceof m ? ((m) inetSocketAddress).a() : new n(inetSocketAddress.getHostName(), inetSocketAddress.getPort(), "https");
        int iD = c.a.a.a.t0.c.d(eVar);
        int iA = c.a.a.a.t0.c.a(eVar);
        socket.setSoTimeout(iD);
        return a(iA, socket, nVarA, inetSocketAddress, inetSocketAddress2, (c.a.a.a.v0.e) null);
    }

    public void a(l lVar) {
        c.a.a.a.x0.a.a(lVar, "Hostname verifier");
        this.f160c = lVar;
    }

    protected void a(SSLSocket sSLSocket) {
    }

    @Override // c.a.a.a.m0.v.j
    public boolean a(Socket socket) {
        c.a.a.a.x0.a.a(socket, "Socket");
        c.a.a.a.x0.b.a(socket instanceof SSLSocket, "Socket not created by this factory");
        c.a.a.a.x0.b.a(!socket.isClosed(), "Socket is closed");
        return true;
    }

    public Socket b(Socket socket, String str, int i, boolean z) {
        return a(socket, str, i, z);
    }
}

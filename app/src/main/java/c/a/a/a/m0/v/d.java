package c.a.a.a.m0.v;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class d implements l, j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f140a = null;

    public static d b() {
        return new d();
    }

    @Override // c.a.a.a.m0.v.l
    public Socket a() {
        return new Socket();
    }

    @Override // c.a.a.a.m0.v.j
    public Socket a(c.a.a.a.t0.e eVar) {
        return new Socket();
    }

    @Override // c.a.a.a.m0.v.l
    @Deprecated
    public Socket a(Socket socket, String str, int i, InetAddress inetAddress, int i2, c.a.a.a.t0.e eVar) {
        InetSocketAddress inetSocketAddress;
        if (inetAddress != null || i2 > 0) {
            if (i2 <= 0) {
                i2 = 0;
            }
            inetSocketAddress = new InetSocketAddress(inetAddress, i2);
        } else {
            inetSocketAddress = null;
        }
        a aVar = this.f140a;
        return a(socket, new InetSocketAddress(aVar != null ? aVar.a(str) : InetAddress.getByName(str), i), inetSocketAddress, eVar);
    }

    @Override // c.a.a.a.m0.v.j
    public Socket a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, c.a.a.a.t0.e eVar) throws IOException {
        c.a.a.a.x0.a.a(inetSocketAddress, "Remote address");
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        if (socket == null) {
            socket = a();
        }
        if (inetSocketAddress2 != null) {
            socket.setReuseAddress(c.a.a.a.t0.c.c(eVar));
            socket.bind(inetSocketAddress2);
        }
        int iA = c.a.a.a.t0.c.a(eVar);
        try {
            socket.setSoTimeout(c.a.a.a.t0.c.d(eVar));
            socket.connect(inetSocketAddress, iA);
            return socket;
        } catch (SocketTimeoutException unused) {
            throw new c.a.a.a.m0.f("Connect to " + inetSocketAddress + " timed out");
        }
    }

    @Override // c.a.a.a.m0.v.l, c.a.a.a.m0.v.j
    public final boolean a(Socket socket) {
        return false;
    }
}

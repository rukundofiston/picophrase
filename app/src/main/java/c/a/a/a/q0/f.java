package c.a.a.a.q0;

import c.a.a.a.o;
import c.a.a.a.q0.l.n;
import c.a.a.a.r0.g;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class f extends a implements o {
    private volatile boolean i;
    private volatile Socket j = null;

    private static void a(StringBuilder sb, SocketAddress socketAddress) {
        if (!(socketAddress instanceof InetSocketAddress)) {
            sb.append(socketAddress);
            return;
        }
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        sb.append(inetSocketAddress.getAddress() != null ? inetSocketAddress.getAddress().getHostAddress() : inetSocketAddress.getAddress());
        sb.append(':');
        sb.append(inetSocketAddress.getPort());
    }

    protected c.a.a.a.r0.f a(Socket socket, int i, c.a.a.a.t0.e eVar) {
        return new n(socket, i, eVar);
    }

    @Override // c.a.a.a.j
    public void a(int i) {
        f();
        if (this.j != null) {
            try {
                this.j.setSoTimeout(i);
            } catch (SocketException unused) {
            }
        }
    }

    protected void a(Socket socket, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(socket, "Socket");
        c.a.a.a.x0.a.a(eVar, "HTTP parameters");
        this.j = socket;
        int iB = eVar.b("http.socket.buffer-size", -1);
        a(a(socket, iB, eVar), b(socket, iB, eVar), eVar);
        this.i = true;
    }

    protected g b(Socket socket, int i, c.a.a.a.t0.e eVar) {
        return new c.a.a.a.q0.l.o(socket, i, eVar);
    }

    @Override // c.a.a.a.j
    public void c() throws IOException {
        this.i = false;
        Socket socket = this.j;
        if (socket != null) {
            socket.close();
        }
    }

    @Override // c.a.a.a.j, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.i) {
            this.i = false;
            Socket socket = this.j;
            try {
                o();
                try {
                    try {
                        socket.shutdownOutput();
                    } catch (IOException unused) {
                    }
                    socket.shutdownInput();
                } catch (IOException | UnsupportedOperationException unused2) {
                }
            } finally {
                socket.close();
            }
        }
    }

    @Override // c.a.a.a.q0.a
    protected void f() {
        c.a.a.a.x0.b.a(this.i, "Connection is not open");
    }

    @Override // c.a.a.a.o
    public InetAddress getRemoteAddress() {
        if (this.j != null) {
            return this.j.getInetAddress();
        }
        return null;
    }

    @Override // c.a.a.a.o
    public int h() {
        if (this.j != null) {
            return this.j.getPort();
        }
        return -1;
    }

    @Override // c.a.a.a.j
    public boolean isOpen() {
        return this.i;
    }

    protected void q() {
        c.a.a.a.x0.b.a(!this.i, "Connection is already open");
    }

    public String toString() {
        if (this.j == null) {
            return super.toString();
        }
        StringBuilder sb = new StringBuilder();
        SocketAddress remoteSocketAddress = this.j.getRemoteSocketAddress();
        SocketAddress localSocketAddress = this.j.getLocalSocketAddress();
        if (remoteSocketAddress != null && localSocketAddress != null) {
            a(sb, localSocketAddress);
            sb.append("<->");
            a(sb, remoteSocketAddress);
        }
        return sb.toString();
    }
}

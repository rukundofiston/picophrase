package c.a.a.a.m0.v;

import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class g implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f144a;

    g(b bVar) {
        this.f144a = bVar;
    }

    @Override // c.a.a.a.m0.v.j
    public Socket a(c.a.a.a.t0.e eVar) {
        return this.f144a.a(eVar);
    }

    @Override // c.a.a.a.m0.v.f
    public Socket a(Socket socket, String str, int i, c.a.a.a.t0.e eVar) {
        return this.f144a.a(socket, str, i, true);
    }

    @Override // c.a.a.a.m0.v.j
    public Socket a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, c.a.a.a.t0.e eVar) {
        return this.f144a.a(socket, inetSocketAddress, inetSocketAddress2, eVar);
    }

    @Override // c.a.a.a.m0.v.j
    public boolean a(Socket socket) {
        return this.f144a.a(socket);
    }
}

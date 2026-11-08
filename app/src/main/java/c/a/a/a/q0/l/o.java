package c.a.a.a.q0.l;

import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class o extends d {
    public o(Socket socket, int i, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(socket, "Socket");
        i = i < 0 ? socket.getSendBufferSize() : i;
        a(socket.getOutputStream(), i < 1024 ? 1024 : i, eVar);
    }
}

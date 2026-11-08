package c.a.a.a.q0.l;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class n extends c implements c.a.a.a.r0.b {
    private final Socket o;
    private boolean p;

    public n(Socket socket, int i, c.a.a.a.t0.e eVar) {
        c.a.a.a.x0.a.a(socket, "Socket");
        this.o = socket;
        this.p = false;
        i = i < 0 ? socket.getReceiveBufferSize() : i;
        a(socket.getInputStream(), i < 1024 ? 1024 : i, eVar);
    }

    @Override // c.a.a.a.r0.f
    public boolean a(int i) throws SocketException {
        boolean zF = f();
        if (zF) {
            return zF;
        }
        int soTimeout = this.o.getSoTimeout();
        try {
            this.o.setSoTimeout(i);
            e();
            return f();
        } finally {
            this.o.setSoTimeout(soTimeout);
        }
    }

    @Override // c.a.a.a.r0.b
    public boolean b() {
        return this.p;
    }

    @Override // c.a.a.a.q0.l.c
    protected int e() throws IOException {
        int iE = super.e();
        this.p = iE == -1;
        return iE;
    }
}

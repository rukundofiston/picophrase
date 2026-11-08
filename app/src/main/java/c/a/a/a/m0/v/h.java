package c.a.a.a.m0.v;

import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class h extends k implements f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f145b;

    h(c cVar) {
        super(cVar);
        this.f145b = cVar;
    }

    @Override // c.a.a.a.m0.v.f
    public Socket a(Socket socket, String str, int i, c.a.a.a.t0.e eVar) {
        return this.f145b.b(socket, str, i, true);
    }
}

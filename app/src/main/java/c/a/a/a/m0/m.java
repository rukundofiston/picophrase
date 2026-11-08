package c.a.a.a.m0;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class m extends InetSocketAddress {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.n f122a;

    public m(c.a.a.a.n nVar, InetAddress inetAddress, int i) {
        super(inetAddress, i);
        c.a.a.a.x0.a.a(nVar, "HTTP host");
        this.f122a = nVar;
    }

    public c.a.a.a.n a() {
        return this.f122a;
    }

    @Override // java.net.InetSocketAddress
    public String toString() {
        return this.f122a.b() + ":" + getPort();
    }
}

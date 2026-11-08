package c.a.a.a.m0.v;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class k implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final l f147a;

    k(l lVar) {
        this.f147a = lVar;
    }

    @Override // c.a.a.a.m0.v.j
    public Socket a(c.a.a.a.t0.e eVar) {
        return this.f147a.a();
    }

    @Override // c.a.a.a.m0.v.j
    public Socket a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, c.a.a.a.t0.e eVar) {
        InetAddress address;
        int port;
        String hostName = inetSocketAddress.getHostName();
        int port2 = inetSocketAddress.getPort();
        if (inetSocketAddress2 != null) {
            address = inetSocketAddress2.getAddress();
            port = inetSocketAddress2.getPort();
        } else {
            address = null;
            port = 0;
        }
        return this.f147a.a(socket, hostName, port2, address, port, eVar);
    }

    @Override // c.a.a.a.m0.v.j
    public boolean a(Socket socket) {
        return this.f147a.a(socket);
    }

    public boolean equals(Object obj) {
        l lVar;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof k) {
            lVar = this.f147a;
            obj = ((k) obj).f147a;
        } else {
            lVar = this.f147a;
        }
        return lVar.equals(obj);
    }

    public int hashCode() {
        return this.f147a.hashCode();
    }
}

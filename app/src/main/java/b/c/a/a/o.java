package b.c.a.a;

import android.os.SystemClock;
import c.a.a.a.z;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;

/* JADX INFO: loaded from: classes.dex */
class o implements c.a.a.a.j0.j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final HashSet<Class<?>> f51c = new HashSet<>();
    private static final HashSet<Class<?>> d = new HashSet<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f52a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f53b;

    static {
        f51c.add(z.class);
        f51c.add(UnknownHostException.class);
        f51c.add(SocketException.class);
        d.add(InterruptedIOException.class);
        d.add(SSLException.class);
    }

    public o(int i, int i2) {
        this.f52a = i;
        this.f53b = i2;
    }

    static void a(Class<?> cls) {
        f51c.add(cls);
    }

    @Override // c.a.a.a.j0.j
    public boolean a(IOException iOException, int i, c.a.a.a.v0.e eVar) {
        Boolean bool = (Boolean) eVar.a("http.request_sent");
        boolean z = true;
        if (bool == null || bool.booleanValue()) {
        }
        if (i > this.f52a || (!a(f51c, iOException) && a(d, iOException))) {
            z = false;
        }
        if (z && ((c.a.a.a.j0.t.i) eVar.a("http.request")) == null) {
            return false;
        }
        if (z) {
            SystemClock.sleep(this.f53b);
        } else {
            iOException.printStackTrace();
        }
        return z;
    }

    protected boolean a(HashSet<Class<?>> hashSet, Throwable th) {
        Iterator<Class<?>> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().isInstance(th)) {
                return true;
            }
        }
        return false;
    }
}

package c.a.a.a.q0.h;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.SSLException;

/* JADX INFO: loaded from: classes.dex */
public class l implements c.a.a.a.j0.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f236a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f237b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Set<Class<? extends IOException>> f238c;

    static {
        new l();
    }

    public l() {
        this(3, false);
    }

    public l(int i, boolean z) {
        this(i, z, Arrays.asList(InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class));
    }

    protected l(int i, boolean z, Collection<Class<? extends IOException>> collection) {
        this.f236a = i;
        this.f237b = z;
        this.f238c = new HashSet();
        Iterator<Class<? extends IOException>> it = collection.iterator();
        while (it.hasNext()) {
            this.f238c.add(it.next());
        }
    }

    protected boolean a(c.a.a.a.q qVar) {
        return !(qVar instanceof c.a.a.a.l);
    }

    @Override // c.a.a.a.j0.j
    public boolean a(IOException iOException, int i, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(iOException, "Exception parameter");
        c.a.a.a.x0.a.a(eVar, "HTTP context");
        if (i > this.f236a || this.f238c.contains(iOException.getClass())) {
            return false;
        }
        Iterator<Class<? extends IOException>> it = this.f238c.iterator();
        while (it.hasNext()) {
            if (it.next().isInstance(iOException)) {
                return false;
            }
        }
        c.a.a.a.j0.v.a aVarA = c.a.a.a.j0.v.a.a(eVar);
        c.a.a.a.q qVarB = aVarA.b();
        if (b(qVarB)) {
            return false;
        }
        return a(qVarB) || !aVarA.d() || this.f237b;
    }

    @Deprecated
    protected boolean b(c.a.a.a.q qVar) {
        if (qVar instanceof v) {
            qVar = ((v) qVar).o();
        }
        return (qVar instanceof c.a.a.a.j0.t.i) && ((c.a.a.a.j0.t.i) qVar).m();
    }
}

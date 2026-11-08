package c.a.a.a.j0.v;

import c.a.a.a.q;
import c.a.a.a.r;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class f implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Collection<? extends c.a.a.a.e> f100a;

    public f() {
        this(null);
    }

    public f(Collection<? extends c.a.a.a.e> collection) {
        this.f100a = collection;
    }

    @Override // c.a.a.a.r
    public void a(q qVar, c.a.a.a.v0.e eVar) {
        c.a.a.a.x0.a.a(qVar, "HTTP request");
        if (qVar.g().b().equalsIgnoreCase("CONNECT")) {
            return;
        }
        Collection<? extends c.a.a.a.e> collection = (Collection) qVar.k().b("http.default-headers");
        if (collection == null) {
            collection = this.f100a;
        }
        if (collection != null) {
            Iterator<? extends c.a.a.a.e> it = collection.iterator();
            while (it.hasNext()) {
                qVar.a(it.next());
            }
        }
    }
}

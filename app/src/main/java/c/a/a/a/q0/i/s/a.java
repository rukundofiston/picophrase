package c.a.a.a.q0.i.s;

import c.a.a.a.q0.i.l;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Lock f280a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Set<b> f281b;

    protected a() {
        new c.a.a.a.p0.b(getClass());
        this.f281b = new HashSet();
        new l();
        this.f280a = new ReentrantLock();
    }
}

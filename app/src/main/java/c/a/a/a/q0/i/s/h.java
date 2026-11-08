package c.a.a.a.q0.i.s;

import java.util.Date;
import java.util.concurrent.locks.Condition;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Condition f295a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Thread f296b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f297c;

    public h(Condition condition, f fVar) {
        c.a.a.a.x0.a.a(condition, "Condition");
        this.f295a = condition;
    }

    public void a() {
        this.f297c = true;
        this.f295a.signalAll();
    }

    public boolean a(Date date) throws InterruptedException {
        boolean zAwaitUntil;
        if (this.f296b != null) {
            throw new IllegalStateException("A thread is already waiting on this object.\ncaller: " + Thread.currentThread() + "\nwaiter: " + this.f296b);
        }
        if (this.f297c) {
            throw new InterruptedException("Operation interrupted");
        }
        this.f296b = Thread.currentThread();
        try {
            if (date != null) {
                zAwaitUntil = this.f295a.awaitUntil(date);
            } else {
                this.f295a.await();
                zAwaitUntil = true;
            }
            if (this.f297c) {
                throw new InterruptedException("Operation interrupted");
            }
            this.f296b = null;
            return zAwaitUntil;
        } catch (Throwable th) {
            this.f296b = null;
            throw th;
        }
    }

    public void b() {
        if (this.f296b == null) {
            throw new IllegalStateException("Nobody waiting on this object.");
        }
        this.f295a.signalAll();
    }
}

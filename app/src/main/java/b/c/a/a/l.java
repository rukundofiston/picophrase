package b.c.a.a;

import android.os.Looper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WeakReference<b> f48a;

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b f49a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f50b;

        a(l lVar, b bVar, boolean z) {
            this.f49a = bVar;
            this.f50b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f49a.a(this.f50b);
        }
    }

    public l(b bVar) {
        this.f48a = new WeakReference<>(bVar);
    }

    public boolean a() {
        b bVar = this.f48a.get();
        return bVar == null || bVar.a();
    }

    public boolean a(boolean z) {
        b bVar = this.f48a.get();
        if (bVar == null) {
            return false;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return bVar.a(z);
        }
        new Thread(new a(this, bVar, z)).start();
        return true;
    }

    public boolean b() {
        b bVar = this.f48a.get();
        return bVar == null || bVar.b();
    }

    public boolean c() {
        boolean z = a() || b();
        if (z) {
            this.f48a.clear();
        }
        return z;
    }
}

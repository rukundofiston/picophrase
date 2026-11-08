package b.c.a.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import c.a.a.a.f0;
import c.a.a.a.s;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URI;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.Cocos2dxEditBox;

/* JADX INFO: loaded from: classes.dex */
public abstract class c implements n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Handler f41a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f42b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f43c;
    private URI d;
    private Looper e;

    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c f44a;

        a(c cVar, Looper looper) {
            super(looper);
            this.f44a = cVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            this.f44a.a(message);
        }
    }

    public c() {
        this((Looper) null);
    }

    public c(Looper looper) {
        this.d = null;
        this.e = null;
        new WeakReference(null);
        this.e = looper == null ? Looper.myLooper() : looper;
        b(false);
        a(false);
    }

    public c(boolean z) {
        this.d = null;
        this.e = null;
        new WeakReference(null);
        a(z);
        if (e()) {
            return;
        }
        this.e = Looper.myLooper();
        b(false);
    }

    protected Message a(int i, Object obj) {
        return Message.obtain(this.f41a, i, obj);
    }

    @Override // b.c.a.a.n
    public final void a() {
        b(a(3, (Object) null));
    }

    @Override // b.c.a.a.n
    public final void a(int i) {
        b(a(5, new Object[]{Integer.valueOf(i)}));
    }

    public abstract void a(int i, c.a.a.a.e[] eVarArr, byte[] bArr);

    @Override // b.c.a.a.n
    public final void a(int i, c.a.a.a.e[] eVarArr, byte[] bArr, Throwable th) {
        b(a(1, new Object[]{Integer.valueOf(i), eVarArr, bArr, th}));
    }

    public void a(long j, long j2) {
        h hVar = b.c.a.a.a.j;
        Object[] objArr = new Object[3];
        objArr[0] = Long.valueOf(j);
        objArr[1] = Long.valueOf(j2);
        objArr[2] = Double.valueOf(j2 > 0 ? ((j * 1.0d) / j2) * 100.0d : -1.0d);
        hVar.b("AsyncHttpRH", String.format("Progress %d from %d (%2.0f%%)", objArr));
    }

    protected void a(Message message) {
        h hVar;
        String str;
        try {
            switch (message.what) {
                case Cocos2dxEditBox.kEndActionUnknown /* 0 */:
                    Object[] objArr = (Object[]) message.obj;
                    if (objArr != null && objArr.length >= 3) {
                        a(((Integer) objArr[0]).intValue(), (c.a.a.a.e[]) objArr[1], (byte[]) objArr[2]);
                        return;
                    }
                    hVar = b.c.a.a.a.j;
                    str = "SUCCESS_MESSAGE didn't got enough params";
                    hVar.a("AsyncHttpRH", str);
                    return;
                case 1:
                    Object[] objArr2 = (Object[]) message.obj;
                    if (objArr2 != null && objArr2.length >= 4) {
                        b(((Integer) objArr2[0]).intValue(), (c.a.a.a.e[]) objArr2[1], (byte[]) objArr2[2], (Throwable) objArr2[3]);
                        return;
                    }
                    hVar = b.c.a.a.a.j;
                    str = "FAILURE_MESSAGE didn't got enough params";
                    hVar.a("AsyncHttpRH", str);
                    return;
                case BuildConfig.VERSION_CODE /* 2 */:
                    i();
                    return;
                case Cocos2dxEditBox.kEndActionReturn /* 3 */:
                    h();
                    return;
                case 4:
                    Object[] objArr3 = (Object[]) message.obj;
                    if (objArr3 == null || objArr3.length < 2) {
                        hVar = b.c.a.a.a.j;
                        str = "PROGRESS_MESSAGE didn't got enough params";
                        hVar.a("AsyncHttpRH", str);
                        return;
                    } else {
                        try {
                            a(((Long) objArr3[0]).longValue(), ((Long) objArr3[1]).longValue());
                            return;
                        } catch (Throwable th) {
                            b.c.a.a.a.j.b("AsyncHttpRH", "custom onProgress contains an error", th);
                            return;
                        }
                    }
                case 5:
                    Object[] objArr4 = (Object[]) message.obj;
                    if (objArr4 != null && objArr4.length == 1) {
                        b(((Integer) objArr4[0]).intValue());
                        return;
                    }
                    hVar = b.c.a.a.a.j;
                    str = "RETRY_MESSAGE didn't get enough params";
                    hVar.a("AsyncHttpRH", str);
                    return;
                case 6:
                    g();
                    return;
                default:
                    return;
            }
        } catch (Throwable th2) {
            a(th2);
            throw null;
        }
    }

    @Override // b.c.a.a.n
    public void a(n nVar, s sVar) {
    }

    @Override // b.c.a.a.n
    public void a(s sVar) throws IOException {
        if (Thread.currentThread().isInterrupted()) {
            return;
        }
        f0 f0VarJ = sVar.j();
        byte[] bArrA = a(sVar.c());
        if (Thread.currentThread().isInterrupted()) {
            return;
        }
        if (f0VarJ.c() >= 300) {
            a(f0VarJ.c(), sVar.l(), bArrA, new c.a.a.a.j0.k(f0VarJ.c(), f0VarJ.b()));
        } else {
            b(f0VarJ.c(), sVar.l(), bArrA);
        }
    }

    public void a(Throwable th) {
        b.c.a.a.a.j.b("AsyncHttpRH", "User-space exception detected!", th);
        throw new RuntimeException(th);
    }

    @Override // b.c.a.a.n
    public void a(URI uri) {
        this.d = uri;
    }

    public void a(boolean z) {
        if (z) {
            this.e = null;
            this.f41a = null;
        }
        this.f43c = z;
    }

    @Override // b.c.a.a.n
    public void a(c.a.a.a.e[] eVarArr) {
    }

    byte[] a(c.a.a.a.k kVar) throws IOException {
        InputStream inputStreamK;
        if (kVar == null || (inputStreamK = kVar.k()) == null) {
            return null;
        }
        long jM = kVar.m();
        if (jM > 2147483647L) {
            throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
        }
        long j = 0;
        try {
            c.a.a.a.x0.c cVar = new c.a.a.a.x0.c(jM <= 0 ? 4096 : (int) jM);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = inputStreamK.read(bArr);
                    if (i == -1 || Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    j += (long) i;
                    cVar.a(bArr, 0, i);
                    b(j, jM <= 0 ? 1L : jM);
                }
                b.c.a.a.a.a(inputStreamK);
                b.c.a.a.a.a(kVar);
                return cVar.g();
            } catch (Throwable th) {
                b.c.a.a.a.a(inputStreamK);
                b.c.a.a.a.a(kVar);
                throw th;
            }
        } catch (OutOfMemoryError unused) {
            System.gc();
            throw new IOException("File too large to fit into available memory");
        }
    }

    public void b(int i) {
        b.c.a.a.a.j.d("AsyncHttpRH", String.format("Request retry no. %d", Integer.valueOf(i)));
    }

    public final void b(int i, c.a.a.a.e[] eVarArr, byte[] bArr) {
        b(a(0, new Object[]{Integer.valueOf(i), eVarArr, bArr}));
    }

    public abstract void b(int i, c.a.a.a.e[] eVarArr, byte[] bArr, Throwable th);

    public final void b(long j, long j2) {
        b(a(4, new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
    }

    protected void b(Message message) {
        if (b() || this.f41a == null) {
            a(message);
        } else {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            p.a(this.f41a != null, "handler should not be null!");
            this.f41a.sendMessage(message);
        }
    }

    @Override // b.c.a.a.n
    public void b(n nVar, s sVar) {
    }

    public void b(boolean z) {
        a aVar;
        if (!z && this.e == null) {
            z = true;
            b.c.a.a.a.j.c("AsyncHttpRH", "Current thread has not called Looper.prepare(). Forcing synchronous mode.");
        }
        if (z || this.f41a != null) {
            if (z && this.f41a != null) {
                aVar = null;
            }
            this.f42b = z;
        }
        aVar = new a(this, this.e);
        this.f41a = aVar;
        this.f42b = z;
    }

    @Override // b.c.a.a.n
    public boolean b() {
        return this.f42b;
    }

    @Override // b.c.a.a.n
    public final void c() {
        b(a(2, (Object) null));
    }

    @Override // b.c.a.a.n
    public final void d() {
        b(a(6, (Object) null));
    }

    @Override // b.c.a.a.n
    public boolean e() {
        return this.f43c;
    }

    public URI f() {
        return this.d;
    }

    public void g() {
        b.c.a.a.a.j.d("AsyncHttpRH", "Request got cancelled");
    }

    public void h() {
    }

    public void i() {
    }
}

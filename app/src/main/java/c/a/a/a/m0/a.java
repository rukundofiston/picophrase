package c.a.a.a.m0;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class a extends c.a.a.a.o0.f implements i, l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected o f117b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final boolean f118c;

    public a(c.a.a.a.k kVar, o oVar, boolean z) {
        super(kVar);
        c.a.a.a.x0.a.a(oVar, "Connection");
        this.f117b = oVar;
        this.f118c = z;
    }

    private void o() {
        o oVar = this.f117b;
        if (oVar == null) {
            return;
        }
        try {
            if (this.f118c) {
                c.a.a.a.x0.g.a(this.f181a);
                this.f117b.i();
            } else {
                oVar.j();
            }
        } finally {
            n();
        }
    }

    @Override // c.a.a.a.o0.f, c.a.a.a.k
    public void a(OutputStream outputStream) {
        super.a(outputStream);
        o();
    }

    @Override // c.a.a.a.m0.l
    public boolean a(InputStream inputStream) {
        o oVar = this.f117b;
        if (oVar == null) {
            return false;
        }
        oVar.f();
        return false;
    }

    @Override // c.a.a.a.m0.l
    public boolean b(InputStream inputStream) {
        try {
            if (this.f117b != null) {
                if (this.f118c) {
                    inputStream.close();
                    this.f117b.i();
                } else {
                    this.f117b.j();
                }
            }
            return false;
        } finally {
            n();
        }
    }

    @Override // c.a.a.a.m0.l
    public boolean c(InputStream inputStream) {
        try {
            if (this.f117b != null) {
                if (this.f118c) {
                    boolean zIsOpen = this.f117b.isOpen();
                    try {
                        inputStream.close();
                        this.f117b.i();
                    } catch (SocketException e) {
                        if (zIsOpen) {
                            throw e;
                        }
                    }
                } else {
                    this.f117b.j();
                }
            }
            n();
            return false;
        } catch (Throwable th) {
            n();
            throw th;
        }
    }

    @Override // c.a.a.a.o0.f, c.a.a.a.k
    public boolean e() {
        return false;
    }

    @Override // c.a.a.a.m0.i
    public void f() {
        o oVar = this.f117b;
        if (oVar != null) {
            try {
                oVar.f();
            } finally {
                this.f117b = null;
            }
        }
    }

    @Override // c.a.a.a.o0.f, c.a.a.a.k
    @Deprecated
    public void h() {
        o();
    }

    @Override // c.a.a.a.o0.f, c.a.a.a.k
    public InputStream k() {
        return new k(this.f181a.k(), this);
    }

    @Override // c.a.a.a.m0.i
    public void l() {
        o();
    }

    protected void n() {
        o oVar = this.f117b;
        if (oVar != null) {
            try {
                oVar.l();
            } finally {
                this.f117b = null;
            }
        }
    }
}

package b.c.a.a;

import c.a.a.a.f0;
import c.a.a.a.s;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class k extends e {
    private long j;
    private boolean k;

    public void a(c.a.a.a.j0.t.i iVar) {
        if (this.f.exists() && this.f.canWrite()) {
            this.j = this.f.length();
        }
        if (this.j > 0) {
            this.k = true;
            iVar.a("Range", "bytes=" + this.j + "-");
        }
    }

    @Override // b.c.a.a.c, b.c.a.a.n
    public void a(s sVar) {
        if (Thread.currentThread().isInterrupted()) {
            return;
        }
        f0 f0VarJ = sVar.j();
        if (f0VarJ.c() == 416) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            b(f0VarJ.c(), sVar.l(), null);
            return;
        }
        if (f0VarJ.c() >= 300) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            a(f0VarJ.c(), sVar.l(), (byte[]) null, new c.a.a.a.j0.k(f0VarJ.c(), f0VarJ.b()));
        } else {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            c.a.a.a.e eVarD = sVar.d("Content-Range");
            if (eVarD == null) {
                this.k = false;
                this.j = 0L;
            } else {
                a.j.b("RangeFileAsyncHttpRH", "Content-Range: " + eVarD.getValue());
            }
            b(f0VarJ.c(), sVar.l(), a(sVar.c()));
        }
    }

    @Override // b.c.a.a.e, b.c.a.a.c
    protected byte[] a(c.a.a.a.k kVar) throws IOException {
        int i;
        if (kVar == null) {
            return null;
        }
        InputStream inputStreamK = kVar.k();
        long jM = kVar.m() + this.j;
        FileOutputStream fileOutputStream = new FileOutputStream(k(), this.k);
        if (inputStreamK == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[4096];
            while (this.j < jM && (i = inputStreamK.read(bArr)) != -1 && !Thread.currentThread().isInterrupted()) {
                this.j += (long) i;
                fileOutputStream.write(bArr, 0, i);
                b(this.j, jM);
            }
            inputStreamK.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return null;
        } catch (Throwable th) {
            inputStreamK.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            throw th;
        }
    }
}

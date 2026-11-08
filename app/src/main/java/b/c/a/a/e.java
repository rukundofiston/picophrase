package b.c.a.a;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class e extends c {
    protected final File f;
    protected final boolean g;
    protected final boolean h;
    protected File i;

    public e(File file, boolean z) {
        this(file, z, false);
    }

    public e(File file, boolean z, boolean z2) {
        this(file, z, z2, false);
    }

    public e(File file, boolean z, boolean z2, boolean z3) {
        super(z3);
        p.a(file != null, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
        if (!file.isDirectory() && !file.getParentFile().isDirectory()) {
            p.a(file.getParentFile().mkdirs(), "Cannot create parent directories for requested File location");
        }
        if (file.isDirectory() && !file.mkdirs()) {
            a.j.d("FileAsyncHttpRH", "Cannot create directories for requested Directory location, might not be a problem");
        }
        this.f = file;
        this.g = z;
        this.h = z2;
    }

    public abstract void a(int i, c.a.a.a.e[] eVarArr, File file);

    public abstract void a(int i, c.a.a.a.e[] eVarArr, Throwable th, File file);

    @Override // b.c.a.a.c
    public final void a(int i, c.a.a.a.e[] eVarArr, byte[] bArr) {
        a(i, eVarArr, k());
    }

    @Override // b.c.a.a.c
    protected byte[] a(c.a.a.a.k kVar) {
        if (kVar == null) {
            return null;
        }
        InputStream inputStreamK = kVar.k();
        long jM = kVar.m();
        FileOutputStream fileOutputStream = new FileOutputStream(k(), this.g);
        if (inputStreamK == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[4096];
            int i = 0;
            while (true) {
                int i2 = inputStreamK.read(bArr);
                if (i2 == -1 || Thread.currentThread().isInterrupted()) {
                    break;
                }
                i += i2;
                fileOutputStream.write(bArr, 0, i2);
                b(i, jM);
            }
            a.a(inputStreamK);
            fileOutputStream.flush();
            a.a(fileOutputStream);
            return null;
        } catch (Throwable th) {
            a.a(inputStreamK);
            fileOutputStream.flush();
            a.a(fileOutputStream);
            throw th;
        }
    }

    @Override // b.c.a.a.c
    public final void b(int i, c.a.a.a.e[] eVarArr, byte[] bArr, Throwable th) {
        a(i, eVarArr, th, k());
    }

    protected File j() {
        p.a(this.f != null, "Target file is null, fatal!");
        return this.f;
    }

    public File k() {
        if (this.i == null) {
            this.i = j().isDirectory() ? l() : j();
        }
        return this.i;
    }

    protected File l() {
        StringBuilder sb;
        p.a(j().isDirectory(), "Target file is not a directory, cannot proceed");
        p.a(f() != null, "RequestURI is null, cannot proceed");
        String string = f().toString();
        String strSubstring = string.substring(string.lastIndexOf(47) + 1, string.length());
        File file = new File(j(), strSubstring);
        if (!file.exists() || !this.h) {
            return file;
        }
        if (strSubstring.contains(".")) {
            sb = new StringBuilder();
            sb.append(strSubstring.substring(0, strSubstring.lastIndexOf(46)));
            sb.append(" (%d)");
            sb.append(strSubstring.substring(strSubstring.lastIndexOf(46), strSubstring.length()));
        } else {
            sb = new StringBuilder();
            sb.append(strSubstring);
            sb.append(" (%d)");
        }
        String string2 = sb.toString();
        int i = 0;
        while (true) {
            File file2 = new File(j(), String.format(string2, Integer.valueOf(i)));
            if (!file2.exists()) {
                return file2;
            }
            i++;
        }
    }
}

package org.cocos2dx.lib;

import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
class c extends b.c.a.a.e {
    int j;
    File k;
    private long l;
    private long m;
    private Cocos2dxDownloader n;

    public c(Cocos2dxDownloader cocos2dxDownloader, int i, File file, File file2) {
        super(file, true);
        this.k = file2;
        this.n = cocos2dxDownloader;
        this.j = i;
        this.l = k().length();
        this.m = 0L;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x005d  */
    @Override // b.c.a.a.e
    public void a(int i, c.a.a.a.e[] eVarArr, File file) {
        String string;
        StringBuilder sb;
        String str;
        a("onSuccess(i:" + i + " headers:" + eVarArr + " file:" + file);
        if (this.k.exists()) {
            if (this.k.isDirectory()) {
                sb = new StringBuilder();
                str = "Dest file is directory:";
            } else if (this.k.delete()) {
                k().renameTo(this.k);
                string = null;
            } else {
                sb = new StringBuilder();
                str = "Can't remove old file:";
            }
            sb.append(str);
            sb.append(this.k.getAbsolutePath());
            string = sb.toString();
        } else {
            k().renameTo(this.k);
            string = null;
        }
        this.n.onFinish(this.j, 0, string, null);
    }

    @Override // b.c.a.a.e
    public void a(int i, c.a.a.a.e[] eVarArr, Throwable th, File file) {
        a("onFailure(i:" + i + " headers:" + eVarArr + " throwable:" + th + " file:" + file);
        this.n.onFinish(this.j, i, th != null ? th.toString() : BuildConfig.FLAVOR, null);
    }

    @Override // b.c.a.a.c
    public void a(long j, long j2) {
        long j3 = j - this.m;
        long j4 = this.l;
        this.n.onProgress(this.j, j3, j + j4, j2 + j4);
        this.m = j;
    }

    void a(String str) {
        Log.d("Cocos2dxDownloader", str);
    }

    @Override // b.c.a.a.c
    public void h() {
        this.n.runNextTaskIfExists();
    }

    @Override // b.c.a.a.c
    public void i() {
        this.n.onStart(this.j);
    }
}

package org.cocos2dx.lib;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class a extends b.c.a.a.d {
    int g;
    private Cocos2dxDownloader h;
    private long i;

    public a(Cocos2dxDownloader cocos2dxDownloader, int i) {
        super(new String[]{".*"});
        this.h = cocos2dxDownloader;
        this.g = i;
        this.i = 0L;
    }

    @Override // b.c.a.a.c
    public void a(int i, c.a.a.a.e[] eVarArr, byte[] bArr) {
        a("onSuccess(i:" + i + " headers:" + eVarArr);
        this.h.onFinish(this.g, 0, null, bArr);
    }

    @Override // b.c.a.a.c
    public void a(long j, long j2) {
        this.h.onProgress(this.g, j - this.i, j, j2);
        this.i = j;
    }

    void a(String str) {
        Log.d("Cocos2dxDownloader", str);
    }

    @Override // b.c.a.a.c
    public void b(int i, c.a.a.a.e[] eVarArr, byte[] bArr, Throwable th) {
        a("onFailure(i:" + i + " headers:" + eVarArr + " throwable:" + th);
        this.h.onFinish(this.g, i, th != null ? th.toString() : BuildConfig.FLAVOR, null);
    }

    @Override // b.c.a.a.c
    public void h() {
        this.h.runNextTaskIfExists();
    }

    @Override // b.c.a.a.c
    public void i() {
        this.h.onStart(this.g);
    }
}

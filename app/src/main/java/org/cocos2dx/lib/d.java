package org.cocos2dx.lib;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class d extends b.c.a.a.c {
    int f;
    String g;
    String h;
    String i;
    private Cocos2dxDownloader j;

    public d(Cocos2dxDownloader cocos2dxDownloader, int i, String str, String str2, String str3) {
        this.j = cocos2dxDownloader;
        this.f = i;
        this.g = str;
        this.h = str2;
        this.i = str3;
    }

    @Override // b.c.a.a.c
    public void a(int i, c.a.a.a.e[] eVarArr, byte[] bArr) {
        boolean zValueOf = false;
        for (c.a.a.a.e eVar : eVarArr) {
            if (eVar.getName().equals("Accept-Ranges")) {
                zValueOf = Boolean.valueOf(eVar.getValue().equals("bytes"));
                break;
            }
        }
        Cocos2dxDownloader.setResumingSupport(this.g, zValueOf);
        Cocos2dxDownloader.createTask(this.j, this.f, this.h, this.i);
    }

    void a(String str) {
        Log.d("Cocos2dxDownloader", str);
    }

    @Override // b.c.a.a.c
    public void b(int i, c.a.a.a.e[] eVarArr, byte[] bArr, Throwable th) {
        a("onFailure(code:" + i + " headers:" + eVarArr + " throwable:" + th + " id:" + this.f);
        this.j.onFinish(this.f, i, th != null ? th.toString() : BuildConfig.FLAVOR, null);
    }

    @Override // b.c.a.a.c
    public void h() {
        this.j.runNextTaskIfExists();
    }
}

package org.cocos2dx.lib;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CountDownLatch f683a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean[] f684b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f685c;
    private final String d;

    e(CountDownLatch countDownLatch, boolean[] zArr, int i, String str) {
        this.f683a = countDownLatch;
        this.f684b = zArr;
        this.f685c = i;
        this.d = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f684b[0] = Cocos2dxWebViewHelper._shouldStartLoading(this.f685c, this.d);
        this.f683a.countDown();
    }
}

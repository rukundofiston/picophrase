package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class v implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ n f497b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ d f498c;

    v(d dVar, String str, n nVar) {
        this.f498c = dVar;
        this.f496a = str;
        this.f497b = nVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        b0 b0VarA = d.a(this.f498c, this.f496a);
        this.f497b.onPurchaseHistoryResponse(b0VarA.a(), b0VarA.b());
        return null;
    }
}

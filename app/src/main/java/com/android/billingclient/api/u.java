package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzu;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class u implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f493a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ o f494b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ d f495c;

    u(d dVar, String str, o oVar) {
        this.f495c = dVar;
        this.f493a = str;
        this.f494b = oVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        l.a aVarB = d.b(this.f495c, this.f493a);
        if (aVarB.b() != null) {
            this.f494b.onQueryPurchasesResponse(aVarB.a(), aVarB.b());
            return null;
        }
        this.f494b.onQueryPurchasesResponse(aVarB.a(), zzu.zzh());
        return null;
    }
}

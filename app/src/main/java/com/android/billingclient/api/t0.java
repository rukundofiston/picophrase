package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
final class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f491a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final s0 f492b;

    /* JADX WARN: Multi-variable type inference failed */
    t0(Context context, i0 i0Var) {
        this.f491a = context;
        this.f492b = new s0(this, (i0) null, (r0) null);
    }

    t0(Context context, p pVar) {
        this.f491a = context;
        this.f492b = new s0(this, pVar, (r0) null);
    }

    final i0 a() {
        s0.a(this.f492b);
        return null;
    }

    final p b() {
        return this.f492b.f488a;
    }

    final void c() {
        this.f492b.a(this.f491a);
    }

    final void d() {
        this.f492b.a(this.f491a, new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED"));
    }
}

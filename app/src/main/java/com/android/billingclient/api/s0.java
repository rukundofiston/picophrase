package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.internal.play_billing.zzb;

/* JADX INFO: loaded from: classes.dex */
final class s0 extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final p f488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i0 f489b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f490c;
    final /* synthetic */ t0 d;

    /* synthetic */ s0(t0 t0Var, i0 i0Var, r0 r0Var) {
        this.d = t0Var;
        this.f488a = null;
        this.f489b = null;
    }

    /* synthetic */ s0(t0 t0Var, p pVar, r0 r0Var) {
        this.d = t0Var;
        this.f488a = pVar;
        this.f489b = null;
    }

    static /* bridge */ /* synthetic */ i0 a(s0 s0Var) {
        i0 i0Var = s0Var.f489b;
        return null;
    }

    public final void a(Context context) {
        if (!this.f490c) {
            zzb.zzn("BillingBroadcastManager", "Receiver is not registered.");
        } else {
            context.unregisterReceiver(this.d.f492b);
            this.f490c = false;
        }
    }

    public final void a(Context context, IntentFilter intentFilter) {
        if (this.f490c) {
            return;
        }
        context.registerReceiver(this.d.f492b, intentFilter);
        this.f490c = true;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.f488a.onPurchasesUpdated(zzb.zzh(intent, "BillingBroadcastManager"), zzb.zzl(intent.getExtras()));
    }
}

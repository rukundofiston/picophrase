package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzd;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class a0 implements ServiceConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f434a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f435b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private e f436c;
    final /* synthetic */ d d;

    /* synthetic */ a0(d dVar, e eVar, z zVar) {
        this.d = dVar;
        this.f436c = eVar;
    }

    private final void a(h hVar) {
        synchronized (this.f434a) {
            e eVar = this.f436c;
            if (eVar != null) {
                eVar.onBillingSetupFinished(hVar);
            }
        }
    }

    final /* synthetic */ Object a() {
        Bundle bundle;
        int iZzr;
        synchronized (this.f434a) {
            if (this.f435b) {
                return null;
            }
            if (TextUtils.isEmpty(null)) {
                bundle = null;
            } else {
                bundle = new Bundle();
                bundle.putString("accountName", null);
            }
            int i = 3;
            try {
                String packageName = this.d.e.getPackageName();
                int i2 = 17;
                iZzr = 3;
                while (true) {
                    if (i2 < 3) {
                        i2 = 0;
                        break;
                    }
                    if (bundle == null) {
                        try {
                            iZzr = this.d.f.zzr(i2, packageName, "subs");
                        } catch (Exception e) {
                            e = e;
                            i = iZzr;
                            zzb.zzo("BillingClient", "Exception while checking if billing is supported; try to reconnect", e);
                            this.d.f449a = 0;
                            this.d.f = null;
                            iZzr = i;
                        }
                    } else {
                        iZzr = this.d.f.zzc(i2, packageName, "subs", bundle);
                    }
                    if (iZzr == 0) {
                        break;
                    }
                    i2--;
                }
                boolean z = true;
                this.d.i = i2 >= 5;
                this.d.h = i2 >= 3;
                if (i2 < 3) {
                    zzb.zzm("BillingClient", "In-app billing API does not support subscription on this device.");
                }
                for (int i3 = 17; i3 >= 3; i3--) {
                    iZzr = bundle == null ? this.d.f.zzr(i3, packageName, "inapp") : this.d.f.zzc(i3, packageName, "inapp", bundle);
                    if (iZzr == 0) {
                        this.d.j = i3;
                        break;
                    }
                }
                d dVar = this.d;
                dVar.s = dVar.j >= 17;
                d dVar2 = this.d;
                dVar2.r = dVar2.j >= 16;
                d dVar3 = this.d;
                dVar3.q = dVar3.j >= 15;
                d dVar4 = this.d;
                dVar4.p = dVar4.j >= 14;
                d dVar5 = this.d;
                dVar5.o = dVar5.j >= 12;
                d dVar6 = this.d;
                dVar6.n = dVar6.j >= 10;
                d dVar7 = this.d;
                dVar7.m = dVar7.j >= 9;
                d dVar8 = this.d;
                dVar8.l = dVar8.j >= 8;
                d dVar9 = this.d;
                if (dVar9.j < 6) {
                    z = false;
                }
                dVar9.k = z;
                if (this.d.j < 3) {
                    zzb.zzn("BillingClient", "In-app billing API version 3 is not supported on this device.");
                }
                if (iZzr == 0) {
                    this.d.f449a = 2;
                } else {
                    this.d.f449a = 0;
                    this.d.f = null;
                }
            } catch (Exception e2) {
                zzb.zzo("BillingClient", "Exception while checking if billing is supported; try to reconnect", e2);
                this.d.f449a = 0;
                this.d.f = null;
                iZzr = i;
            }
            a(iZzr == 0 ? f0.k : f0.f460a);
            return null;
        }
    }

    final /* synthetic */ void b() {
        this.d.f449a = 0;
        this.d.f = null;
        a(f0.m);
    }

    final void c() {
        synchronized (this.f434a) {
            this.f436c = null;
            this.f435b = true;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzb.zzm("BillingClient", "Billing service connected.");
        this.d.f = zzd.zzo(iBinder);
        d dVar = this.d;
        if (dVar.a(new Callable() { // from class: com.android.billingclient.api.y
            @Override // java.util.concurrent.Callable
            public final Object call() {
                a0.this.a();
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.x
            @Override // java.lang.Runnable
            public final void run() {
                a0.this.b();
            }
        }, dVar.c()) == null) {
            a(this.d.d());
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        zzb.zzn("BillingClient", "Billing service disconnected.");
        this.d.f = null;
        this.d.f449a = 0;
        synchronized (this.f434a) {
            e eVar = this.f436c;
            if (eVar != null) {
                eVar.onBillingServiceDisconnected();
            }
        }
    }
}

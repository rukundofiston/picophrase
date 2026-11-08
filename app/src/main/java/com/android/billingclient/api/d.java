package com.android.billingclient.api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zze;
import com.google.android.gms.internal.play_billing.zzu;
import com.google.android.gms.internal.play_billing.zzz;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
class d extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    volatile int f449a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final String f450b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Handler f451c;
    volatile t0 d;
    Context e;
    volatile zze f;
    volatile a0 g;
    boolean h;
    boolean i;
    int j;
    boolean k;
    boolean l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    boolean t;
    boolean u;
    ExecutorService v;

    private d(Context context, boolean z, p pVar, String str, String str2, q0 q0Var) {
        this.f449a = 0;
        this.f451c = new Handler(Looper.getMainLooper());
        this.j = 0;
        this.f450b = str;
        a(context, pVar, z, (q0) null);
    }

    d(String str, boolean z, Context context, i0 i0Var) {
        this.f449a = 0;
        this.f451c = new Handler(Looper.getMainLooper());
        this.j = 0;
        this.f450b = e();
        Context applicationContext = context.getApplicationContext();
        this.e = applicationContext;
        this.d = new t0(applicationContext, (i0) null);
        this.t = z;
    }

    d(String str, boolean z, Context context, p pVar, q0 q0Var) {
        this(context, z, pVar, e(), null, null);
    }

    static /* bridge */ /* synthetic */ b0 a(d dVar, String str) {
        String strValueOf = String.valueOf(str);
        zzb.zzm("BillingClient", strValueOf.length() != 0 ? "Querying purchase history, item type: ".concat(strValueOf) : new String("Querying purchase history, item type: "));
        ArrayList arrayList = new ArrayList();
        Bundle bundleZzg = zzb.zzg(dVar.m, dVar.t, dVar.f450b);
        String string = null;
        while (dVar.k) {
            try {
                Bundle bundleZzh = dVar.f.zzh(6, dVar.e.getPackageName(), str, string, bundleZzg);
                h hVarA = j0.a(bundleZzh, "BillingClient", "getPurchaseHistory()");
                if (hVarA != f0.k) {
                    return new b0(hVarA, null);
                }
                ArrayList<String> stringArrayList = bundleZzh.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = bundleZzh.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = bundleZzh.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                for (int i = 0; i < stringArrayList2.size(); i++) {
                    String str2 = stringArrayList2.get(i);
                    String str3 = stringArrayList3.get(i);
                    String strValueOf2 = String.valueOf(stringArrayList.get(i));
                    zzb.zzm("BillingClient", strValueOf2.length() != 0 ? "Purchase record found for sku : ".concat(strValueOf2) : new String("Purchase record found for sku : "));
                    try {
                        m mVar = new m(str2, str3);
                        if (TextUtils.isEmpty(mVar.c())) {
                            zzb.zzn("BillingClient", "BUG: empty/null token!");
                        }
                        arrayList.add(mVar);
                    } catch (JSONException e) {
                        zzb.zzo("BillingClient", "Got an exception trying to decode the purchase!", e);
                        return new b0(f0.j, null);
                    }
                }
                string = bundleZzh.getString("INAPP_CONTINUATION_TOKEN");
                String strValueOf3 = String.valueOf(string);
                zzb.zzm("BillingClient", strValueOf3.length() != 0 ? "Continuation token: ".concat(strValueOf3) : new String("Continuation token: "));
                if (TextUtils.isEmpty(string)) {
                    return new b0(f0.k, arrayList);
                }
            } catch (RemoteException e2) {
                zzb.zzo("BillingClient", "Got exception trying to get purchase history, try to reconnect", e2);
                return new b0(f0.l, null);
            }
        }
        zzb.zzn("BillingClient", "getPurchaseHistory is not supported on current device");
        return new b0(f0.o, null);
    }

    final Future a(Callable callable, long j, final Runnable runnable, Handler handler) {
        long j2 = (long) (j * 0.95d);
        if (this.v == null) {
            this.v = Executors.newFixedThreadPool(zzb.zza, new w(this));
        }
        try {
            final Future futureSubmit = this.v.submit(callable);
            handler.postDelayed(new Runnable() { // from class: com.android.billingclient.api.g1
                @Override // java.lang.Runnable
                public final void run() {
                    Future future = futureSubmit;
                    Runnable runnable2 = runnable;
                    if (future.isDone() || future.isCancelled()) {
                        return;
                    }
                    future.cancel(true);
                    zzb.zzn("BillingClient", "Async task is taking too long, cancel it!");
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            }, j2);
            return futureSubmit;
        } catch (Exception e) {
            zzb.zzo("BillingClient", "Async task throws exception!", e);
            return null;
        }
    }

    private void a(Context context, p pVar, boolean z, q0 q0Var) {
        Context applicationContext = context.getApplicationContext();
        this.e = applicationContext;
        this.d = new t0(applicationContext, pVar);
        this.t = z;
        this.u = q0Var != null;
    }

    private final h b(final h hVar) {
        if (Thread.interrupted()) {
            return hVar;
        }
        this.f451c.post(new Runnable() { // from class: com.android.billingclient.api.a1
            @Override // java.lang.Runnable
            public final void run() {
                d.this.a(hVar);
            }
        });
        return hVar;
    }

    static /* bridge */ /* synthetic */ l.a b(d dVar, String str) {
        String strValueOf = String.valueOf(str);
        zzb.zzm("BillingClient", strValueOf.length() != 0 ? "Querying owned items, item type: ".concat(strValueOf) : new String("Querying owned items, item type: "));
        ArrayList arrayList = new ArrayList();
        Bundle bundleZzg = zzb.zzg(dVar.m, dVar.t, dVar.f450b);
        String string = null;
        do {
            try {
                Bundle bundleZzj = dVar.m ? dVar.f.zzj(9, dVar.e.getPackageName(), str, string, bundleZzg) : dVar.f.zzi(3, dVar.e.getPackageName(), str, string);
                h hVarA = j0.a(bundleZzj, "BillingClient", "getPurchase()");
                if (hVarA != f0.k) {
                    return new l.a(hVarA, null);
                }
                ArrayList<String> stringArrayList = bundleZzj.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = bundleZzj.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = bundleZzj.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                for (int i = 0; i < stringArrayList2.size(); i++) {
                    String str2 = stringArrayList2.get(i);
                    String str3 = stringArrayList3.get(i);
                    String strValueOf2 = String.valueOf(stringArrayList.get(i));
                    zzb.zzm("BillingClient", strValueOf2.length() != 0 ? "Sku is owned: ".concat(strValueOf2) : new String("Sku is owned: "));
                    try {
                        l lVar = new l(str2, str3);
                        if (TextUtils.isEmpty(lVar.e())) {
                            zzb.zzn("BillingClient", "BUG: empty/null token!");
                        }
                        arrayList.add(lVar);
                    } catch (JSONException e) {
                        zzb.zzo("BillingClient", "Got an exception trying to decode the purchase!", e);
                        return new l.a(f0.j, null);
                    }
                }
                string = bundleZzj.getString("INAPP_CONTINUATION_TOKEN");
                String strValueOf3 = String.valueOf(string);
                zzb.zzm("BillingClient", strValueOf3.length() != 0 ? "Continuation token: ".concat(strValueOf3) : new String("Continuation token: "));
            } catch (Exception e2) {
                zzb.zzo("BillingClient", "Got exception trying to get purchasesm try to reconnect", e2);
                return new l.a(f0.l, null);
            }
        } while (!TextUtils.isEmpty(string));
        return new l.a(f0.k, arrayList);
    }

    final Handler c() {
        return Looper.myLooper() == null ? this.f451c : new Handler(Looper.myLooper());
    }

    final h d() {
        return (this.f449a == 0 || this.f449a == 3) ? f0.l : f0.j;
    }

    @SuppressLint({"PrivateApi"})
    private static String e() {
        try {
            return (String) Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get(null);
        } catch (Exception unused) {
            return "4.1.0";
        }
    }

    final /* synthetic */ Bundle a(int i, String str, String str2, f fVar, Bundle bundle) throws Exception {
        return this.f.zzg(i, this.e.getPackageName(), str, str2, null, bundle);
    }

    final /* synthetic */ Bundle a(String str, String str2) throws Exception {
        return this.f.zzf(3, this.e.getPackageName(), str, str2, null);
    }

    @Override // com.android.billingclient.api.c
    public final h a(Activity activity, final f fVar) {
        String str;
        Callable callable;
        long j;
        Runnable runnable;
        Handler handler;
        h hVar;
        String str2;
        q qVar;
        g gVar;
        String str3;
        String str4;
        String str5;
        String str6;
        boolean z;
        String str7;
        final int i;
        if (b()) {
            ArrayList<q> arrayListF = fVar.f();
            List listG = fVar.g();
            q qVar2 = (q) zzz.zza(arrayListF, null);
            g gVar2 = (g) zzz.zza(listG, null);
            if (qVar2 == null) {
                throw null;
            }
            final String strE = qVar2.e();
            final String strG = qVar2.g();
            String str8 = "BillingClient";
            if (strG.equals("subs") && !this.h) {
                zzb.zzn("BillingClient", "Current client doesn't support subscriptions.");
                hVar = f0.n;
            } else if (fVar.h() && !this.k) {
                zzb.zzn("BillingClient", "Current client doesn't support extra params for buy intent.");
                hVar = f0.h;
            } else if (arrayListF.size() > 1 && !this.r) {
                zzb.zzn("BillingClient", "Current client doesn't support multi-item purchases.");
                hVar = f0.p;
            } else if (listG.isEmpty() || this.s) {
                boolean z2 = this.u;
                if (!z2 || this.k) {
                    if (this.k) {
                        final Bundle bundleZzf = zzb.zzf(fVar, this.m, this.t, z2, this.f450b);
                        if (arrayListF.isEmpty()) {
                            str2 = "proxyPackageVersion";
                            qVar = qVar2;
                            gVar = gVar2;
                            str3 = strE;
                            str4 = strG;
                            str5 = "BillingClient";
                            ArrayList<String> arrayList = new ArrayList<>(listG.size() - 1);
                            ArrayList<String> arrayList2 = new ArrayList<>(listG.size() - 1);
                            ArrayList<String> arrayList3 = new ArrayList<>();
                            ArrayList<String> arrayList4 = new ArrayList<>();
                            if (listG.size() > 0) {
                                throw null;
                            }
                            bundleZzf.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList4);
                            if (!arrayList3.isEmpty()) {
                                bundleZzf.putStringArrayList("skuDetailsTokens", arrayList3);
                            }
                            if (!arrayList.isEmpty()) {
                                bundleZzf.putStringArrayList("additionalSkus", arrayList);
                                bundleZzf.putStringArrayList("additionalSkuTypes", arrayList2);
                            }
                        } else {
                            ArrayList<String> arrayList5 = new ArrayList<>();
                            ArrayList<String> arrayList6 = new ArrayList<>();
                            str4 = strG;
                            ArrayList<String> arrayList7 = new ArrayList<>();
                            str3 = strE;
                            ArrayList<Integer> arrayList8 = new ArrayList<>();
                            str2 = "proxyPackageVersion";
                            ArrayList<String> arrayList9 = new ArrayList<>();
                            boolean z3 = false;
                            boolean z4 = false;
                            boolean z5 = false;
                            boolean z6 = false;
                            for (q qVar3 : arrayListF) {
                                if (!qVar3.m().isEmpty()) {
                                    arrayList5.add(qVar3.m());
                                }
                                String strJ = qVar3.j();
                                g gVar3 = gVar2;
                                String strI = qVar3.i();
                                int iH = qVar3.h();
                                q qVar4 = qVar2;
                                String strL = qVar3.l();
                                arrayList6.add(strJ);
                                z3 |= !TextUtils.isEmpty(strJ);
                                arrayList7.add(strI);
                                z4 |= !TextUtils.isEmpty(strI);
                                arrayList8.add(Integer.valueOf(iH));
                                z5 |= iH != 0;
                                z6 |= !TextUtils.isEmpty(strL);
                                arrayList9.add(strL);
                                str8 = str8;
                                gVar2 = gVar3;
                                qVar2 = qVar4;
                            }
                            qVar = qVar2;
                            gVar = gVar2;
                            str5 = str8;
                            if (!arrayList5.isEmpty()) {
                                bundleZzf.putStringArrayList("skuDetailsTokens", arrayList5);
                            }
                            if (z3) {
                                bundleZzf.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList6);
                            }
                            if (z4) {
                                bundleZzf.putStringArrayList("SKU_OFFER_ID_LIST", arrayList7);
                            }
                            if (z5) {
                                bundleZzf.putIntegerArrayList("SKU_OFFER_TYPE_LIST", arrayList8);
                            }
                            if (z6) {
                                bundleZzf.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList9);
                            }
                            if (arrayListF.size() > 1) {
                                ArrayList<String> arrayList10 = new ArrayList<>(arrayListF.size() - 1);
                                ArrayList<String> arrayList11 = new ArrayList<>(arrayListF.size() - 1);
                                for (int i2 = 1; i2 < arrayListF.size(); i2++) {
                                    arrayList10.add(((q) arrayListF.get(i2)).e());
                                    arrayList11.add(((q) arrayListF.get(i2)).g());
                                }
                                bundleZzf.putStringArrayList("additionalSkus", arrayList10);
                                bundleZzf.putStringArrayList("additionalSkuTypes", arrayList11);
                            }
                        }
                        if (!bundleZzf.containsKey("SKU_OFFER_ID_TOKEN_LIST") || this.p) {
                            if (TextUtils.isEmpty(qVar.k())) {
                                str6 = null;
                                if (gVar != null) {
                                    throw null;
                                }
                                z = false;
                            } else {
                                bundleZzf.putString("skuPackageName", qVar.k());
                                str6 = null;
                                z = true;
                            }
                            if (!TextUtils.isEmpty(str6)) {
                                bundleZzf.putString("accountName", str6);
                            }
                            Intent intent = activity.getIntent();
                            if (intent == null) {
                                str = str5;
                                zzb.zzn(str, "Activity's intent is null.");
                            } else {
                                str = str5;
                                if (!TextUtils.isEmpty(intent.getStringExtra("PROXY_PACKAGE"))) {
                                    String stringExtra = intent.getStringExtra("PROXY_PACKAGE");
                                    bundleZzf.putString("proxyPackage", stringExtra);
                                    str7 = str2;
                                    try {
                                        bundleZzf.putString(str7, this.e.getPackageManager().getPackageInfo(stringExtra, 0).versionName);
                                    } catch (PackageManager.NameNotFoundException unused) {
                                        bundleZzf.putString(str7, "package not found");
                                    }
                                }
                            }
                            if (this.s && !listG.isEmpty()) {
                                i = 17;
                            } else if (this.q && z) {
                                i = 15;
                            } else if (this.m) {
                                i = 9;
                            } else {
                                i = fVar.a() ? 7 : 6;
                            }
                            final String str9 = str3;
                            final String str10 = str4;
                            Callable callable2 = new Callable() { // from class: com.android.billingclient.api.w0
                                @Override // java.util.concurrent.Callable
                                public final Object call() throws Exception {
                                    return d.this.a(i, str9, str10, fVar, bundleZzf);
                                }
                            };
                            j = 5000;
                            runnable = null;
                            handler = this.f451c;
                            callable = callable2;
                        } else {
                            hVar = f0.q;
                            b(hVar);
                            return hVar;
                        }
                    } else {
                        str = "BillingClient";
                        callable = new Callable() { // from class: com.android.billingclient.api.z0
                            @Override // java.util.concurrent.Callable
                            public final Object call() throws Exception {
                                return d.this.a(strE, strG);
                            }
                        };
                        j = 5000;
                        runnable = null;
                        handler = this.f451c;
                    }
                    try {
                        Bundle bundle = (Bundle) a(callable, j, runnable, handler).get(5000L, TimeUnit.MILLISECONDS);
                        int iZzb = zzb.zzb(bundle, str);
                        String strZzj = zzb.zzj(bundle, str);
                        if (iZzb == 0) {
                            Intent intent2 = new Intent(activity, (Class<?>) ProxyBillingActivity.class);
                            intent2.putExtra("BUY_INTENT", (PendingIntent) bundle.getParcelable("BUY_INTENT"));
                            activity.startActivity(intent2);
                            return f0.k;
                        }
                        StringBuilder sb = new StringBuilder(52);
                        sb.append("Unable to buy item, Error response code: ");
                        sb.append(iZzb);
                        zzb.zzn(str, sb.toString());
                        com.android.billingclient.api.h.a aVarC = com.android.billingclient.api.h.c();
                        aVarC.a(iZzb);
                        aVarC.a(strZzj);
                        h hVarA = aVarC.a();
                        b(hVarA);
                        return hVarA;
                    } catch (CancellationException | TimeoutException e2) {
                        zzb.zzo(str, "Time out while launching billing flow. Try to reconnect", e2);
                        hVar = f0.m;
                        b(hVar);
                        return hVar;
                    } catch (Exception e3) {
                        zzb.zzo(str, "Exception while launching billing flow. Try to reconnect", e3);
                        hVar = f0.l;
                    }
                } else {
                    zzb.zzn("BillingClient", "Current client doesn't support alternative billing.");
                    hVar = f0.s;
                }
            } else {
                zzb.zzn("BillingClient", "Current client doesn't support purchases with ProductDetails.");
                hVar = f0.r;
            }
        } else {
            hVar = f0.l;
        }
        b(hVar);
        return hVar;
    }

    final /* synthetic */ Object a(String str, List list, String str2, s sVar) {
        String strZzj;
        int i;
        String str3;
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i2 = 0;
        while (true) {
            strZzj = "Item is unavailable for purchase.";
            if (i2 >= size) {
                strZzj = BuildConfig.FLAVOR;
                i = 0;
                break;
            }
            int i3 = i2 + 20;
            ArrayList arrayList2 = new ArrayList(list.subList(i2, i3 > size ? size : i3));
            ArrayList<String> arrayList3 = new ArrayList<>();
            int size2 = arrayList2.size();
            for (int i4 = 0; i4 < size2; i4++) {
                arrayList3.add(((p0) arrayList2.get(i4)).a());
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList3);
            bundle.putString("playBillingLibraryVersion", this.f450b);
            try {
                Bundle bundleZzl = this.n ? this.f.zzl(10, this.e.getPackageName(), str, bundle, zzb.zze(this.j, this.t, this.f450b, null, arrayList2)) : this.f.zzk(3, this.e.getPackageName(), str, bundle);
                if (bundleZzl != null) {
                    if (bundleZzl.containsKey("DETAILS_LIST")) {
                        ArrayList<String> stringArrayList = bundleZzl.getStringArrayList("DETAILS_LIST");
                        if (stringArrayList == null) {
                            str3 = "querySkuDetailsAsync got null response list";
                        } else {
                            for (int i5 = 0; i5 < stringArrayList.size(); i5++) {
                                try {
                                    q qVar = new q(stringArrayList.get(i5));
                                    zzb.zzm("BillingClient", "Got sku details: ".concat(qVar.toString()));
                                    arrayList.add(qVar);
                                } catch (JSONException e) {
                                    zzb.zzo("BillingClient", "Got a JSON exception trying to decode SkuDetails.", e);
                                    strZzj = "Error trying to decode SkuDetails.";
                                    arrayList = null;
                                }
                            }
                            i2 = i3;
                        }
                    } else {
                        int iZzb = zzb.zzb(bundleZzl, "BillingClient");
                        strZzj = zzb.zzj(bundleZzl, "BillingClient");
                        if (iZzb != 0) {
                            StringBuilder sb = new StringBuilder(50);
                            sb.append("getSkuDetails() failed. Response code: ");
                            sb.append(iZzb);
                            zzb.zzn("BillingClient", sb.toString());
                            i = iZzb;
                            break;
                        }
                        zzb.zzn("BillingClient", "getSkuDetails() returned a bundle with neither an error nor a detail list.");
                    }
                    i = 6;
                    break;
                }
                str3 = "querySkuDetailsAsync got null sku details list";
                zzb.zzn("BillingClient", str3);
                arrayList = null;
                i = 4;
                break;
            } catch (Exception e2) {
                zzb.zzo("BillingClient", "querySkuDetailsAsync got a remote exception (try to reconnect).", e2);
                i = -1;
                strZzj = "Service connection is disconnected.";
                arrayList = null;
            }
        }
        com.android.billingclient.api.h.a aVarC = com.android.billingclient.api.h.c();
        aVarC.a(i);
        aVarC.a(strZzj);
        sVar.onSkuDetailsResponse(aVarC.a(), arrayList);
        return null;
    }

    @Override // com.android.billingclient.api.c
    public final void a() {
        try {
            this.d.c();
            if (this.g != null) {
                this.g.c();
            }
            if (this.g != null && this.f != null) {
                zzb.zzm("BillingClient", "Unbinding from service.");
                this.e.unbindService(this.g);
                this.g = null;
            }
            this.f = null;
            ExecutorService executorService = this.v;
            if (executorService != null) {
                executorService.shutdownNow();
                this.v = null;
            }
        } catch (Exception e) {
            zzb.zzo("BillingClient", "There was an exception while ending connection!", e);
        } finally {
            this.f449a = 3;
        }
    }

    @Override // com.android.billingclient.api.c
    public final void a(final com.android.billingclient.api.a aVar, final b bVar) {
        h hVarD;
        if (!b()) {
            hVarD = f0.l;
        } else if (TextUtils.isEmpty(aVar.a())) {
            zzb.zzn("BillingClient", "Please provide a valid purchase token.");
            hVarD = f0.i;
        } else if (!this.m) {
            hVarD = f0.f461b;
        } else if (a(new Callable() { // from class: com.android.billingclient.api.x0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                d.this.b(aVar, bVar);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.v0
            @Override // java.lang.Runnable
            public final void run() {
                bVar.onAcknowledgePurchaseResponse(f0.m);
            }
        }, c()) != null) {
            return;
        } else {
            hVarD = d();
        }
        bVar.onAcknowledgePurchaseResponse(hVarD);
    }

    @Override // com.android.billingclient.api.c
    public final void a(e eVar) {
        ServiceInfo serviceInfo;
        String str;
        if (b()) {
            zzb.zzm("BillingClient", "Service connection is valid. No need to re-initialize.");
            eVar.onBillingSetupFinished(f0.k);
            return;
        }
        if (this.f449a == 1) {
            zzb.zzn("BillingClient", "Client is already in the process of connecting to billing service.");
            eVar.onBillingSetupFinished(f0.d);
            return;
        }
        if (this.f449a == 3) {
            zzb.zzn("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            eVar.onBillingSetupFinished(f0.l);
            return;
        }
        this.f449a = 1;
        this.d.d();
        zzb.zzm("BillingClient", "Starting in-app billing setup.");
        this.g = new a0(this, eVar, null);
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        List<ResolveInfo> listQueryIntentServices = this.e.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices != null && !listQueryIntentServices.isEmpty() && (serviceInfo = listQueryIntentServices.get(0).serviceInfo) != null) {
            String str2 = serviceInfo.packageName;
            String str3 = serviceInfo.name;
            if (!"com.android.vending".equals(str2) || str3 == null) {
                str = "The device doesn't have valid Play Store.";
            } else {
                ComponentName componentName = new ComponentName(str2, str3);
                Intent intent2 = new Intent(intent);
                intent2.setComponent(componentName);
                intent2.putExtra("playBillingLibraryVersion", this.f450b);
                if (this.e.bindService(intent2, this.g, 1)) {
                    zzb.zzm("BillingClient", "Service was bonded successfully.");
                    return;
                }
                str = "Connection to Billing service is blocked.";
            }
            zzb.zzn("BillingClient", str);
        }
        this.f449a = 0;
        zzb.zzm("BillingClient", "Billing service unavailable on device.");
        eVar.onBillingSetupFinished(f0.f462c);
    }

    final /* synthetic */ void a(h hVar) {
        if (this.d.b() != null) {
            this.d.b().onPurchasesUpdated(hVar, null);
        } else {
            this.d.a();
            zzb.zzn("BillingClient", "No valid listener is set in BroadcastManager");
        }
    }

    @Override // com.android.billingclient.api.c
    public final void a(final i iVar, final j jVar) {
        h hVarD;
        if (!b()) {
            hVarD = f0.l;
        } else if (a(new Callable() { // from class: com.android.billingclient.api.y0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                d.this.b(iVar, jVar);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.c1
            @Override // java.lang.Runnable
            public final void run() {
                jVar.onConsumeResponse(f0.m, iVar.a());
            }
        }, c()) != null) {
            return;
        } else {
            hVarD = d();
        }
        jVar.onConsumeResponse(hVarD, iVar.a());
    }

    @Override // com.android.billingclient.api.c
    public final void a(r rVar, final s sVar) {
        h hVarD;
        if (b()) {
            final String strA = rVar.a();
            List<String> listB = rVar.b();
            if (TextUtils.isEmpty(strA)) {
                zzb.zzn("BillingClient", "Please fix the input params. SKU type can't be empty.");
                hVarD = f0.f;
            } else if (listB != null) {
                final ArrayList arrayList = new ArrayList();
                for (String str : listB) {
                    n0 n0Var = new n0(null);
                    n0Var.a(str);
                    arrayList.add(n0Var.a());
                }
                if (a(new Callable() { // from class: com.android.billingclient.api.b1
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        d.this.a(strA, arrayList, (String) null, sVar);
                        return null;
                    }
                }, 30000L, new Runnable() { // from class: com.android.billingclient.api.f1
                    @Override // java.lang.Runnable
                    public final void run() {
                        sVar.onSkuDetailsResponse(f0.m, null);
                    }
                }, c()) != null) {
                    return;
                } else {
                    hVarD = d();
                }
            } else {
                zzb.zzn("BillingClient", "Please fix the input params. The list of SKUs can't be empty - set SKU list or SkuWithOffer list.");
                hVarD = f0.e;
            }
        } else {
            hVarD = f0.l;
        }
        sVar.onSkuDetailsResponse(hVarD, null);
    }

    @Override // com.android.billingclient.api.c
    public final void a(String str, final n nVar) {
        h hVarD;
        if (!b()) {
            hVarD = f0.l;
        } else if (a(new v(this, str, nVar), 30000L, new Runnable() { // from class: com.android.billingclient.api.d1
            @Override // java.lang.Runnable
            public final void run() {
                nVar.onPurchaseHistoryResponse(f0.m, null);
            }
        }, c()) != null) {
            return;
        } else {
            hVarD = d();
        }
        nVar.onPurchaseHistoryResponse(hVarD, null);
    }

    @Override // com.android.billingclient.api.c
    public void a(String str, final o oVar) {
        h hVarD;
        if (!b()) {
            hVarD = f0.l;
        } else if (TextUtils.isEmpty(str)) {
            zzb.zzn("BillingClient", "Please provide a valid product type.");
            hVarD = f0.g;
        } else if (a(new u(this, str, oVar), 30000L, new Runnable() { // from class: com.android.billingclient.api.e1
            @Override // java.lang.Runnable
            public final void run() {
                oVar.onQueryPurchasesResponse(f0.m, zzu.zzh());
            }
        }, c()) != null) {
            return;
        } else {
            hVarD = d();
        }
        oVar.onQueryPurchasesResponse(hVarD, zzu.zzh());
    }

    final /* synthetic */ Object b(com.android.billingclient.api.a aVar, b bVar) {
        h hVarA;
        try {
            Bundle bundleZzd = this.f.zzd(9, this.e.getPackageName(), aVar.a(), zzb.zzc(aVar, this.f450b));
            int iZzb = zzb.zzb(bundleZzd, "BillingClient");
            String strZzj = zzb.zzj(bundleZzd, "BillingClient");
            com.android.billingclient.api.h.a aVarC = com.android.billingclient.api.h.c();
            aVarC.a(iZzb);
            aVarC.a(strZzj);
            hVarA = aVarC.a();
        } catch (Exception e) {
            zzb.zzo("BillingClient", "Error acknowledge purchase!", e);
            hVarA = f0.l;
        }
        bVar.onAcknowledgePurchaseResponse(hVarA);
        return null;
    }

    final /* synthetic */ Object b(i iVar, j jVar) {
        int iZza;
        String strZzj;
        String strA = iVar.a();
        try {
            String strValueOf = String.valueOf(strA);
            zzb.zzm("BillingClient", strValueOf.length() != 0 ? "Consuming purchase with token: ".concat(strValueOf) : new String("Consuming purchase with token: "));
            if (this.m) {
                Bundle bundleZze = this.f.zze(9, this.e.getPackageName(), strA, zzb.zzd(iVar, this.m, this.f450b));
                iZza = bundleZze.getInt("RESPONSE_CODE");
                strZzj = zzb.zzj(bundleZze, "BillingClient");
            } else {
                iZza = this.f.zza(3, this.e.getPackageName(), strA);
                strZzj = BuildConfig.FLAVOR;
            }
            com.android.billingclient.api.h.a aVarC = com.android.billingclient.api.h.c();
            aVarC.a(iZza);
            aVarC.a(strZzj);
            h hVarA = aVarC.a();
            if (iZza == 0) {
                zzb.zzm("BillingClient", "Successfully consumed purchase.");
            } else {
                StringBuilder sb = new StringBuilder(63);
                sb.append("Error consuming purchase with token. Response code: ");
                sb.append(iZza);
                zzb.zzn("BillingClient", sb.toString());
            }
            jVar.onConsumeResponse(hVarA, strA);
            return null;
        } catch (Exception e) {
            zzb.zzo("BillingClient", "Error consuming purchase!", e);
            jVar.onConsumeResponse(f0.l, strA);
            return null;
        }
    }

    @Override // com.android.billingclient.api.c
    public final boolean b() {
        return (this.f449a != 2 || this.f == null || this.g == null) ? false : true;
    }
}

package com.google.android.gms.internal.play_billing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.billingclient.api.a;
import com.android.billingclient.api.f;
import com.android.billingclient.api.h;
import com.android.billingclient.api.i;
import com.android.billingclient.api.k;
import com.android.billingclient.api.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public final class zzb {
    public static final int zza = Runtime.getRuntime().availableProcessors();

    public static int zza(Intent intent, String str) {
        if (intent != null) {
            return zzp(intent.getExtras(), "ProxyBillingActivity");
        }
        zzn("ProxyBillingActivity", "Got null intent!");
        return 0;
    }

    public static int zzb(Bundle bundle, String str) {
        if (bundle == null) {
            zzn(str, "Unexpected null bundle received!");
            return 6;
        }
        Object obj = bundle.get("RESPONSE_CODE");
        if (obj == null) {
            zzm(str, "getResponseCodeFromBundle() got null response code, assuming OK");
            return 0;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        String strValueOf = String.valueOf(obj.getClass().getName());
        zzn(str, strValueOf.length() != 0 ? "Unexpected type for bundle response code: ".concat(strValueOf) : new String("Unexpected type for bundle response code: "));
        return 6;
    }

    public static Bundle zzc(a aVar, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("playBillingLibraryVersion", str);
        return bundle;
    }

    public static Bundle zzd(i iVar, boolean z, String str) {
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putString("playBillingLibraryVersion", str);
        }
        return bundle;
    }

    public static Bundle zze(int i, boolean z, String str, String str2, ArrayList arrayList) {
        Bundle bundle = new Bundle();
        if (i >= 9) {
            bundle.putString("playBillingLibraryVersion", str);
        }
        if (i >= 9 && z) {
            bundle.putBoolean("enablePendingPurchases", true);
        }
        if (i >= 14) {
            ArrayList<String> arrayList2 = new ArrayList<>();
            ArrayList<String> arrayList3 = new ArrayList<>();
            ArrayList arrayList4 = new ArrayList();
            int size = arrayList.size();
            boolean z2 = false;
            boolean z3 = false;
            for (int i2 = 0; i2 < size; i2++) {
                arrayList2.add(null);
                z2 |= !TextUtils.isEmpty(null);
                arrayList3.add(null);
                z3 |= !TextUtils.isEmpty(null);
                arrayList4.add(0);
            }
            if (z2) {
                bundle.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList2);
            }
            if (z3) {
                bundle.putStringArrayList("SKU_OFFER_ID_LIST", arrayList3);
            }
        }
        return bundle;
    }

    public static Bundle zzf(f fVar, boolean z, boolean z2, boolean z3, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("playBillingLibraryVersion", str);
        if (fVar.b() != 0) {
            bundle.putInt("prorationMode", fVar.b());
        }
        if (!TextUtils.isEmpty(fVar.c())) {
            bundle.putString("accountId", fVar.c());
        }
        if (!TextUtils.isEmpty(fVar.d())) {
            bundle.putString("obfuscatedProfileId", fVar.d());
        }
        if (fVar.a()) {
            bundle.putBoolean("vr", true);
        }
        if (!TextUtils.isEmpty(null)) {
            bundle.putStringArrayList("skusToReplace", new ArrayList<>(Arrays.asList(null)));
        }
        if (!TextUtils.isEmpty(fVar.e())) {
            bundle.putString("oldSkuPurchaseToken", fVar.e());
        }
        if (!TextUtils.isEmpty(null)) {
            bundle.putString("oldSkuPurchaseId", null);
        }
        if (!TextUtils.isEmpty(null)) {
            bundle.putString("paymentsPurchaseParams", null);
        }
        if (z && z2) {
            bundle.putBoolean("enablePendingPurchases", true);
        }
        if (z3) {
            bundle.putBoolean("enableAlternativeBilling", true);
        }
        return bundle;
    }

    public static Bundle zzg(boolean z, boolean z2, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("playBillingLibraryVersion", str);
        if (z && z2) {
            bundle.putBoolean("enablePendingPurchases", true);
        }
        return bundle;
    }

    public static h zzh(Intent intent, String str) {
        if (intent != null) {
            h.a aVarC = h.c();
            aVarC.a(zzb(intent.getExtras(), str));
            aVarC.a(zzj(intent.getExtras(), str));
            return aVarC.a();
        }
        zzn("BillingHelper", "Got null intent!");
        h.a aVarC2 = h.c();
        aVarC2.a(6);
        aVarC2.a("An internal error occurred.");
        return aVarC2.a();
    }

    public static k zzi(Bundle bundle, String str) {
        return bundle == null ? new k(0, null) : new k(zzp(bundle, "BillingClient"), bundle.getString("IN_APP_MESSAGE_PURCHASE_TOKEN"));
    }

    public static String zzj(Bundle bundle, String str) {
        if (bundle == null) {
            zzn(str, "Unexpected null bundle received!");
            return BuildConfig.FLAVOR;
        }
        Object obj = bundle.get("DEBUG_MESSAGE");
        if (obj == null) {
            zzm(str, "getDebugMessageFromBundle() got null response code, assuming OK");
            return BuildConfig.FLAVOR;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        String strValueOf = String.valueOf(obj.getClass().getName());
        zzn(str, strValueOf.length() != 0 ? "Unexpected type for debug message: ".concat(strValueOf) : new String("Unexpected type for debug message: "));
        return BuildConfig.FLAVOR;
    }

    public static String zzk(int i) {
        return com.google.android.gms.internal.play_billing.zza.zza(i).toString();
    }

    public static List zzl(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        ArrayList arrayList = new ArrayList();
        if (stringArrayList == null || stringArrayList2 == null) {
            l lVarZzq = zzq(bundle.getString("INAPP_PURCHASE_DATA"), bundle.getString("INAPP_DATA_SIGNATURE"));
            if (lVarZzq == null) {
                zzm("BillingHelper", "Couldn't find single purchase data as well.");
                return null;
            }
            arrayList.add(lVarZzq);
        } else {
            int size = stringArrayList.size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Found purchase list of ");
            sb.append(size);
            sb.append(" items");
            zzm("BillingHelper", sb.toString());
            for (int i = 0; i < stringArrayList.size() && i < stringArrayList2.size(); i++) {
                l lVarZzq2 = zzq(stringArrayList.get(i), stringArrayList2.get(i));
                if (lVarZzq2 != null) {
                    arrayList.add(lVarZzq2);
                }
            }
        }
        return arrayList;
    }

    public static void zzm(String str, String str2) {
        if (Log.isLoggable(str, 2)) {
            Log.v(str, str2);
        }
    }

    public static void zzn(String str, String str2) {
        if (Log.isLoggable(str, 5)) {
            Log.w(str, str2);
        }
    }

    public static void zzo(String str, String str2, Throwable th) {
        if (Log.isLoggable(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    private static int zzp(Bundle bundle, String str) {
        if (bundle != null) {
            return bundle.getInt("IN_APP_MESSAGE_RESPONSE_CODE", 0);
        }
        zzn(str, "Unexpected null bundle received!");
        return 0;
    }

    private static l zzq(String str, String str2) {
        if (str == null || str2 == null) {
            zzm("BillingHelper", "Received a null purchase data.");
            return null;
        }
        try {
            return new l(str, str2);
        } catch (JSONException e) {
            zzn("BillingHelper", "Got JSONException while parsing purchase data: ".concat(e.toString()));
            return null;
        }
    }
}

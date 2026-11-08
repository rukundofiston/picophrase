package com.android.billingclient.api;

import android.os.Bundle;
import com.google.android.gms.internal.play_billing.zzb;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class j0 {
    static h a(Bundle bundle, String str, String str2) {
        String str3;
        h hVar = f0.j;
        if (bundle == null) {
            str3 = String.format("%s got null owned items list", str2);
        } else {
            int iZzb = zzb.zzb(bundle, "BillingClient");
            String strZzj = zzb.zzj(bundle, "BillingClient");
            h.a aVarC = h.c();
            aVarC.a(iZzb);
            aVarC.a(strZzj);
            h hVarA = aVarC.a();
            if (iZzb != 0) {
                zzb.zzn("BillingClient", String.format("%s failed. Response code: %s", str2, Integer.valueOf(iZzb)));
                return hVarA;
            }
            if (bundle.containsKey("INAPP_PURCHASE_ITEM_LIST") && bundle.containsKey("INAPP_PURCHASE_DATA_LIST") && bundle.containsKey("INAPP_DATA_SIGNATURE_LIST")) {
                ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = bundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                if (stringArrayList == null) {
                    str3 = String.format("Bundle returned from %s contains null SKUs list.", str2);
                } else if (stringArrayList2 == null) {
                    str3 = String.format("Bundle returned from %s contains null purchases list.", str2);
                } else {
                    if (stringArrayList3 != null) {
                        return f0.k;
                    }
                    str3 = String.format("Bundle returned from %s contains null signatures list.", str2);
                }
            } else {
                str3 = String.format("Bundle returned from %s doesn't contain required fields.", str2);
            }
        }
        zzb.zzn("BillingClient", str3);
        return hVar;
    }
}

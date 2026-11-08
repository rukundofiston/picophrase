package com.android.billingclient.api;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f477a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f478b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final JSONObject f479c;

    public m(String str, String str2) throws org.json.JSONException {
        this.f477a = str;
        this.f478b = str2;
        this.f479c = new JSONObject(this.f477a);
    }

    public String a() {
        return this.f477a;
    }

    public long b() {
        return this.f479c.optLong("purchaseTime");
    }

    public String c() {
        JSONObject jSONObject = this.f479c;
        return jSONObject.optString("token", jSONObject.optString("purchaseToken"));
    }

    public int d() {
        return this.f479c.optInt("quantity", 1);
    }

    public String e() {
        return this.f478b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return TextUtils.equals(this.f477a, mVar.a()) && TextUtils.equals(this.f478b, mVar.e());
    }

    public ArrayList<String> f() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.f479c.has("productIds")) {
            JSONArray jSONArrayOptJSONArray = this.f479c.optJSONArray("productIds");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(jSONArrayOptJSONArray.optString(i));
                }
            }
        } else if (this.f479c.has("productId")) {
            arrayList.add(this.f479c.optString("productId"));
        }
        return arrayList;
    }

    public int hashCode() {
        return this.f477a.hashCode();
    }

    public String toString() {
        String strValueOf = String.valueOf(this.f477a);
        return strValueOf.length() != 0 ? "PurchaseHistoryRecord. Json: ".concat(strValueOf) : new String("PurchaseHistoryRecord. Json: ");
    }
}

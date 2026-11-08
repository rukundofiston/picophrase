package com.android.billingclient.api;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f482a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final JSONObject f483b;

    public q(String str) throws org.json.JSONException {
        this.f482a = str;
        JSONObject jSONObject = new JSONObject(this.f482a);
        this.f483b = jSONObject;
        if (TextUtils.isEmpty(jSONObject.optString("productId"))) {
            throw new IllegalArgumentException("SKU cannot be empty.");
        }
        if (TextUtils.isEmpty(this.f483b.optString("type"))) {
            throw new IllegalArgumentException("SkuType cannot be empty.");
        }
    }

    public String a() {
        return this.f483b.optString("description");
    }

    public String b() {
        return this.f483b.optString("price");
    }

    public long c() {
        return this.f483b.optLong("price_amount_micros");
    }

    public String d() {
        return this.f483b.optString("price_currency_code");
    }

    public String e() {
        return this.f483b.optString("productId");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof q) {
            return TextUtils.equals(this.f482a, ((q) obj).f482a);
        }
        return false;
    }

    public String f() {
        return this.f483b.optString("title");
    }

    public String g() {
        return this.f483b.optString("type");
    }

    public int h() {
        return this.f483b.optInt("offer_type");
    }

    public int hashCode() {
        return this.f482a.hashCode();
    }

    public String i() {
        return this.f483b.optString("offer_id");
    }

    public String j() {
        String strOptString = this.f483b.optString("offerIdToken");
        return strOptString.isEmpty() ? this.f483b.optString("offer_id_token") : strOptString;
    }

    public final String k() {
        return this.f483b.optString("packageName");
    }

    public String l() {
        return this.f483b.optString("serializedDocid");
    }

    final String m() {
        return this.f483b.optString("skuDetailsToken");
    }

    public String toString() {
        String strValueOf = String.valueOf(this.f482a);
        return strValueOf.length() != 0 ? "SkuDetails: ".concat(strValueOf) : new String("SkuDetails: ");
    }
}

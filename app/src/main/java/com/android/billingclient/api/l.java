package com.android.billingclient.api;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f472a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f473b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final JSONObject f474c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List f475a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final h f476b;

        public a(h hVar, List<l> list) {
            this.f475a = list;
            this.f476b = hVar;
        }

        public h a() {
            return this.f476b;
        }

        public List<l> b() {
            return this.f475a;
        }
    }

    public l(String str, String str2) throws org.json.JSONException {
        this.f472a = str;
        this.f473b = str2;
        this.f474c = new JSONObject(this.f472a);
    }

    public String a() {
        return this.f474c.optString("orderId");
    }

    public String b() {
        return this.f472a;
    }

    public int c() {
        return this.f474c.optInt("purchaseState", 1) != 4 ? 1 : 2;
    }

    public long d() {
        return this.f474c.optLong("purchaseTime");
    }

    public String e() {
        JSONObject jSONObject = this.f474c;
        return jSONObject.optString("token", jSONObject.optString("purchaseToken"));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return TextUtils.equals(this.f472a, lVar.b()) && TextUtils.equals(this.f473b, lVar.g());
    }

    public int f() {
        return this.f474c.optInt("quantity", 1);
    }

    public String g() {
        return this.f473b;
    }

    public ArrayList<String> h() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.f474c.has("productIds")) {
            JSONArray jSONArrayOptJSONArray = this.f474c.optJSONArray("productIds");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(jSONArrayOptJSONArray.optString(i));
                }
            }
        } else if (this.f474c.has("productId")) {
            arrayList.add(this.f474c.optString("productId"));
        }
        return arrayList;
    }

    public int hashCode() {
        return this.f472a.hashCode();
    }

    public String toString() {
        String strValueOf = String.valueOf(this.f472a);
        return strValueOf.length() != 0 ? "Purchase. Json: ".concat(strValueOf) : new String("Purchase. Json: ");
    }
}

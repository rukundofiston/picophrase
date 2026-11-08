package iap;

import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class PurchaseEventListener {
    private long mDelegate;

    public PurchaseEventListener(long j) {
        this.mDelegate = j;
    }

    private native void onAcknowledgeFailure(long j, String str, int i, String str2);

    private native void onAcknowledgeSuccess(long j, String str);

    private native void onConsumeFailure(long j, String str, int i, String str2);

    private native void onConsumeSuccess(long j, String str);

    private native void onInitialized(long j, boolean z);

    private native void onProductRequestFailure(long j, String str);

    private native void onProductRequestSuccess(long j, String str);

    private native void onPurchaseCanceled(long j, String str);

    private native void onPurchaseFailure(long j, String str, int i, String str2);

    private native void onPurchaseHistoryRequestSuccess(long j, String str);

    private native void onPurchaseSuccess(long j, String str);

    private native void onRestoreFailure(long j, int i, String str);

    private native void onRestoreSuccess(long j, String str);

    void onAcknowledgeFailure(Product product, int i, String str) {
        try {
            onAcknowledgeFailure(this.mDelegate, product.toJson().toString(), i, str);
        } catch (JSONException unused) {
        }
    }

    void onAcknowledgeSuccess(Product product) {
        try {
            onAcknowledgeSuccess(this.mDelegate, product.toJson().toString());
        } catch (JSONException unused) {
        }
    }

    void onConsumeFailure(Product product, int i, String str) {
        try {
            onConsumeFailure(this.mDelegate, product.toJson().toString(), i, str);
        } catch (JSONException unused) {
        }
    }

    void onConsumeSuccess(Product product) {
        try {
            onConsumeSuccess(this.mDelegate, product.toJson().toString());
        } catch (JSONException unused) {
        }
    }

    public void onInitialized(boolean z) {
        onInitialized(this.mDelegate, z);
    }

    void onPurchaseCanceled(Product product) {
        try {
            onPurchaseCanceled(this.mDelegate, product.toJson().toString());
        } catch (JSONException unused) {
        }
    }

    void onPurchaseFailure(Product product, int i, String str) {
        try {
            onPurchaseFailure(this.mDelegate, product.toJson().toString(), i, str);
        } catch (JSONException unused) {
        }
    }

    void onPurchaseHistoryRequestSuccess(JSONArray jSONArray) {
        onPurchaseHistoryRequestSuccess(this.mDelegate, jSONArray.toString());
    }

    void onPurchaseSuccess(Product product) {
        try {
            onPurchaseSuccess(this.mDelegate, product.toJson().toString());
        } catch (JSONException unused) {
        }
    }

    void onQueryProductListFailure(String str) {
        onProductRequestFailure(this.mDelegate, str);
    }

    void onQueryProductListSuccess(JSONArray jSONArray) {
        onProductRequestSuccess(this.mDelegate, jSONArray.toString());
    }

    void onQueryPurchasesFailure(int i, String str) {
        onRestoreFailure(this.mDelegate, i, str);
    }

    void onQueryPurchasesSuccess(JSONArray jSONArray) {
        onRestoreSuccess(this.mDelegate, jSONArray.toString());
    }
}

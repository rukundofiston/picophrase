package iap;

import com.android.billingclient.api.q;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Product {
    String currencyCode;
    String description;
    String id;
    String price;
    float priceValue;
    int quantity;
    String receipt;
    String receiptCipheredPayload;
    String title;
    String transactionID;
    String type;

    public static Product createFromSkuDetails(q qVar, String str) {
        Product product = new Product();
        product.id = qVar.e();
        product.type = str;
        product.price = qVar.b();
        product.title = qVar.f();
        product.description = qVar.a();
        product.currencyCode = qVar.d();
        product.receipt = BuildConfig.FLAVOR;
        product.receiptCipheredPayload = BuildConfig.FLAVOR;
        product.transactionID = BuildConfig.FLAVOR;
        product.quantity = 1;
        product.priceValue = (float) (qVar.c() / 1000000.0d);
        return product;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        jSONObject.put("type", this.type);
        jSONObject.put("title", this.title);
        jSONObject.put("description", this.description);
        jSONObject.put("price", this.price);
        jSONObject.put("priceValue", this.priceValue);
        jSONObject.put("currencyCode", this.currencyCode);
        jSONObject.put("receipt", this.receipt);
        jSONObject.put("receiptCipheredPayload", this.receiptCipheredPayload);
        jSONObject.put("transactionID", this.transactionID);
        jSONObject.put("quantity", this.quantity);
        return jSONObject;
    }
}

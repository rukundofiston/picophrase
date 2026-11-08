package iap;

import android.app.Activity;
import android.util.Log;
import com.android.billingclient.api.a;
import com.android.billingclient.api.b;
import com.android.billingclient.api.c;
import com.android.billingclient.api.e;
import com.android.billingclient.api.f;
import com.android.billingclient.api.h;
import com.android.billingclient.api.i;
import com.android.billingclient.api.j;
import com.android.billingclient.api.l;
import com.android.billingclient.api.m;
import com.android.billingclient.api.n;
import com.android.billingclient.api.o;
import com.android.billingclient.api.p;
import com.android.billingclient.api.q;
import com.android.billingclient.api.r;
import com.android.billingclient.api.s;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.Cocos2dxEditBox;
import org.cocos2dx.lib.Cocos2dxHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PurchaseManager {
    private static final String TAG = "PurchaseManager";
    private static PurchaseManager sInstance;
    private c mBillingClient;
    private q mCurrentRequestPurchaseSkuDetails;
    private String mLicenseKey;
    private PurchaseEventListener mPurchaseEventListener;
    private HashMap<String, String> mProductTypeMap = new HashMap<>();
    private HashMap<String, q> mSkuDetailsMap = new HashMap<>();
    private List<JSONObject> mPurchaseHistoryList = new ArrayList();
    private p mPurchasesUpdatedListener = new p() { // from class: iap.PurchaseManager.1
        @Override // com.android.billingclient.api.p
        public void onPurchasesUpdated(h hVar, List<l> list) {
            if (hVar.b() == 0 && list != null) {
                Iterator<l> it = list.iterator();
                while (it.hasNext()) {
                    PurchaseManager.this.handlePurchase(it.next());
                }
                return;
            }
            if (hVar.b() == 1) {
                PurchaseManager purchaseManager = PurchaseManager.this;
                PurchaseManager.this.mPurchaseEventListener.onPurchaseCanceled(purchaseManager.getProductFromSkuDetails(purchaseManager.mCurrentRequestPurchaseSkuDetails));
            } else {
                PurchaseManager purchaseManager2 = PurchaseManager.this;
                PurchaseManager.this.mPurchaseEventListener.onPurchaseFailure(purchaseManager2.getProductFromSkuDetails(purchaseManager2.mCurrentRequestPurchaseSkuDetails), hVar.b(), hVar.a());
            }
        }
    };

    private PurchaseManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String billingResponseCodeToString(int i) {
        switch (i) {
            case -3:
                return "SERVICE_TIMEOUT";
            case -2:
                return "FEATURE_NOT_SUPPORTED";
            case -1:
                return "SERVICE_DISCONNECTED";
            case Cocos2dxEditBox.kEndActionUnknown /* 0 */:
                return "OK";
            case 1:
                return "USER_CANCELED";
            case BuildConfig.VERSION_CODE /* 2 */:
                return "SERVICE_UNAVAILABLE";
            case Cocos2dxEditBox.kEndActionReturn /* 3 */:
                return "BILLING_UNAVAILABLE";
            case 4:
                return "ITEM_UNAVAILABLE";
            case 5:
                return "DEVELOPER_ERROR";
            case 6:
                return "ERROR";
            case 7:
                return "ITEM_ALREADY_OWNED";
            case 8:
                return "ITEM_NOT_OWNED";
            default:
                return "UNKNOWN";
        }
    }

    public static PurchaseManager getInstance() {
        if (sInstance == null) {
            sInstance = new PurchaseManager();
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Product getProductFromSkuDetails(q qVar) {
        return Product.createFromSkuDetails(qVar, getProductType(qVar.e()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getProductType(String str) {
        return (String) Objects.requireNonNull(this.mProductTypeMap.get(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public q getSkuDetailsByProductId(String str) {
        return this.mSkuDetailsMap.get(str);
    }

    private List<String> getSkuList() {
        return new ArrayList(this.mProductTypeMap.keySet());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePurchase(l lVar) {
        if (lVar.c() != 1) {
            return;
        }
        Product productFromSkuDetails = getProductFromSkuDetails(this.mCurrentRequestPurchaseSkuDetails);
        productFromSkuDetails.transactionID = lVar.a();
        productFromSkuDetails.receipt = lVar.b();
        productFromSkuDetails.receiptCipheredPayload = lVar.g();
        productFromSkuDetails.quantity = lVar.f();
        this.mPurchaseEventListener.onPurchaseSuccess(productFromSkuDetails);
    }

    public void acknowledge(final String str) {
        if (this.mPurchaseEventListener != null && isInitialized()) {
            this.mBillingClient.a("inapp", new o() { // from class: iap.PurchaseManager.7
                @Override // com.android.billingclient.api.o
                public void onQueryPurchasesResponse(h hVar, List<l> list) {
                    q skuDetailsByProductId = PurchaseManager.this.getSkuDetailsByProductId(str);
                    if (skuDetailsByProductId == null) {
                        return;
                    }
                    final Product productFromSkuDetails = PurchaseManager.this.getProductFromSkuDetails(skuDetailsByProductId);
                    if (hVar.b() != 0) {
                        PurchaseManager.this.mPurchaseEventListener.onConsumeFailure(productFromSkuDetails, hVar.b(), hVar.a());
                        return;
                    }
                    l lVar = null;
                    for (l lVar2 : list) {
                        if (lVar2.h().get(0).equals(str)) {
                            lVar = lVar2;
                            break;
                        }
                    }
                    if (lVar == null) {
                        PurchaseManager.this.mPurchaseEventListener.onConsumeFailure(productFromSkuDetails, 6, "no target purchase");
                        return;
                    }
                    productFromSkuDetails.transactionID = lVar.a();
                    productFromSkuDetails.receipt = lVar.b();
                    productFromSkuDetails.receiptCipheredPayload = lVar.g();
                    productFromSkuDetails.quantity = lVar.f();
                    a.C0010a c0010aB = a.b();
                    c0010aB.a(lVar.e());
                    PurchaseManager.this.mBillingClient.a(c0010aB.a(), new b() { // from class: iap.PurchaseManager.7.1
                        @Override // com.android.billingclient.api.b
                        public void onAcknowledgePurchaseResponse(h hVar2) {
                            if (hVar2.b() == 0) {
                                PurchaseManager.this.mPurchaseEventListener.onAcknowledgeSuccess(productFromSkuDetails);
                            } else {
                                PurchaseManager.this.mPurchaseEventListener.onAcknowledgeFailure(productFromSkuDetails, hVar2.b(), hVar2.a());
                            }
                        }
                    });
                }
            });
        }
    }

    public void consume(final String str) {
        if (this.mPurchaseEventListener != null && isInitialized()) {
            this.mBillingClient.a("inapp", new o() { // from class: iap.PurchaseManager.6
                @Override // com.android.billingclient.api.o
                public void onQueryPurchasesResponse(h hVar, List<l> list) {
                    q skuDetailsByProductId = PurchaseManager.this.getSkuDetailsByProductId(str);
                    if (skuDetailsByProductId == null) {
                        return;
                    }
                    final Product productFromSkuDetails = PurchaseManager.this.getProductFromSkuDetails(skuDetailsByProductId);
                    if (hVar.b() != 0) {
                        PurchaseManager.this.mPurchaseEventListener.onConsumeFailure(productFromSkuDetails, hVar.b(), hVar.a());
                        return;
                    }
                    l lVar = null;
                    for (l lVar2 : list) {
                        if (lVar2.h().get(0).equals(str)) {
                            lVar = lVar2;
                            break;
                        }
                    }
                    if (lVar == null) {
                        PurchaseManager.this.mPurchaseEventListener.onConsumeFailure(productFromSkuDetails, 6, "no target purchase");
                        return;
                    }
                    productFromSkuDetails.transactionID = lVar.a();
                    productFromSkuDetails.receipt = lVar.b();
                    productFromSkuDetails.receiptCipheredPayload = lVar.g();
                    productFromSkuDetails.quantity = lVar.f();
                    i.a aVarB = i.b();
                    aVarB.a(lVar.e());
                    PurchaseManager.this.mBillingClient.a(aVarB.a(), new j() { // from class: iap.PurchaseManager.6.1
                        @Override // com.android.billingclient.api.j
                        public void onConsumeResponse(h hVar2, String str2) {
                            if (hVar2.b() == 0) {
                                PurchaseManager.this.mPurchaseEventListener.onConsumeSuccess(productFromSkuDetails);
                            } else {
                                PurchaseManager.this.mPurchaseEventListener.onConsumeFailure(productFromSkuDetails, hVar2.b(), hVar2.a());
                            }
                        }
                    });
                }
            });
        }
    }

    public Activity getActivity() {
        return Cocos2dxHelper.getActivity();
    }

    public void getPurchaseHistory() {
        this.mPurchaseHistoryList.clear();
        if (this.mPurchaseEventListener != null && isInitialized()) {
            this.mBillingClient.a("inapp", new o() { // from class: iap.PurchaseManager.4
                @Override // com.android.billingclient.api.o
                public void onQueryPurchasesResponse(h hVar, List<l> list) {
                    if (hVar.b() == 0) {
                        for (l lVar : list) {
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("productId", lVar.h().get(0));
                                jSONObject.put("orderId", lVar.a());
                                jSONObject.put("purchaseTime", lVar.d());
                                jSONObject.put("purchaseToken", lVar.e());
                                jSONObject.put("originalJson", lVar.b());
                                jSONObject.put("signature", lVar.g());
                                jSONObject.put("quantity", lVar.f());
                                String str = "UNKNOWN";
                                if (lVar.c() == 1) {
                                    str = "PURCHASED";
                                } else if (lVar.c() == 2) {
                                    str = "PENDING";
                                } else if (lVar.c() == 0) {
                                    str = "UNSPECIFIED_STATE";
                                }
                                jSONObject.put("purchaseState", str);
                                PurchaseManager.this.mPurchaseHistoryList.add(jSONObject);
                            } catch (Exception unused) {
                            }
                        }
                    }
                    PurchaseManager.this.mBillingClient.a("inapp", new n() { // from class: iap.PurchaseManager.4.1
                        @Override // com.android.billingclient.api.n
                        public void onPurchaseHistoryResponse(h hVar2, List<m> list2) {
                            if (hVar2.b() == 0 && list2 != null) {
                                for (m mVar : list2) {
                                    try {
                                        JSONObject jSONObject2 = new JSONObject();
                                        jSONObject2.put("productId", mVar.f().get(0));
                                        jSONObject2.put("purchaseTime", mVar.b());
                                        jSONObject2.put("purchaseToken", mVar.c());
                                        jSONObject2.put("originalJson", mVar.a());
                                        jSONObject2.put("signature", mVar.e());
                                        jSONObject2.put("quantity", mVar.d());
                                        PurchaseManager.this.mPurchaseHistoryList.add(jSONObject2);
                                    } catch (Exception unused2) {
                                    }
                                }
                            }
                            if (PurchaseManager.this.mPurchaseEventListener != null) {
                                PurchaseManager.this.mPurchaseEventListener.onPurchaseHistoryRequestSuccess(new JSONArray((Collection) PurchaseManager.this.mPurchaseHistoryList));
                            }
                        }
                    });
                }
            });
        }
    }

    public void init(String str) {
        c cVar = this.mBillingClient;
        if (cVar != null) {
            cVar.a();
            this.mBillingClient = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("android").getJSONObject("iap");
            this.mLicenseKey = jSONObject.getString("key");
            JSONObject jSONObject2 = jSONObject.getJSONObject("items");
            this.mProductTypeMap.clear();
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject(itKeys.next());
                String string = jSONObject3.getString("id");
                String string2 = jSONObject3.getString("type");
                if (!string2.equals("consumable")) {
                    string2 = "non_consumable";
                }
                this.mProductTypeMap.put(string, string2);
            }
        } catch (JSONException unused) {
            PurchaseEventListener purchaseEventListener = this.mPurchaseEventListener;
            if (purchaseEventListener != null) {
                purchaseEventListener.onInitialized(false);
                return;
            }
        }
        c.a aVarA = c.a(getActivity());
        aVarA.a(this.mPurchasesUpdatedListener);
        aVarA.b();
        c cVarA = aVarA.a();
        this.mBillingClient = cVarA;
        cVarA.a(new e() { // from class: iap.PurchaseManager.2
            @Override // com.android.billingclient.api.e
            public void onBillingServiceDisconnected() {
                if (PurchaseManager.this.mBillingClient != null) {
                    PurchaseManager.this.mBillingClient = null;
                }
            }

            @Override // com.android.billingclient.api.e
            public void onBillingSetupFinished(h hVar) {
                boolean z = true;
                if (hVar.b() == 0) {
                    Log.d(PurchaseManager.TAG, "BillingClient initialize success");
                } else {
                    Log.e(PurchaseManager.TAG, String.format(Locale.ENGLISH, "BillingClient initialize failed: responseCode=%d(%s), debugMessage=%s", Integer.valueOf(hVar.b()), PurchaseManager.billingResponseCodeToString(hVar.b()), hVar.a()));
                    z = false;
                }
                if (PurchaseManager.this.mPurchaseEventListener != null) {
                    PurchaseManager.this.mPurchaseEventListener.onInitialized(z);
                }
            }
        });
    }

    public boolean isInitialized() {
        c cVar = this.mBillingClient;
        return cVar != null && cVar.b();
    }

    public void purchase(String str) {
        q skuDetailsByProductId;
        this.mCurrentRequestPurchaseSkuDetails = null;
        if (this.mPurchaseEventListener == null || !isInitialized() || (skuDetailsByProductId = getSkuDetailsByProductId(str)) == null) {
            return;
        }
        this.mCurrentRequestPurchaseSkuDetails = skuDetailsByProductId;
        f.a aVarI = f.i();
        aVarI.a(skuDetailsByProductId);
        this.mBillingClient.a(getActivity(), aVarI.a());
    }

    public void queryProductListAsync() {
        if (!isInitialized()) {
            PurchaseEventListener purchaseEventListener = this.mPurchaseEventListener;
            if (purchaseEventListener != null) {
                purchaseEventListener.onQueryProductListFailure("BillingClient is not initialized");
                return;
            }
            return;
        }
        r.a aVarC = r.c();
        aVarC.a(getSkuList());
        aVarC.a("inapp");
        this.mBillingClient.a(aVarC.a(), new s() { // from class: iap.PurchaseManager.3
            @Override // com.android.billingclient.api.s
            public void onSkuDetailsResponse(h hVar, List<q> list) {
                String message;
                PurchaseEventListener purchaseEventListener2;
                PurchaseManager.this.mSkuDetailsMap.clear();
                if (PurchaseManager.this.mPurchaseEventListener == null) {
                    return;
                }
                if (list == null) {
                    PurchaseManager.this.mPurchaseEventListener.onQueryProductListFailure("skuDetailsList is empty");
                    return;
                }
                if (hVar.b() == 0) {
                    for (q qVar : list) {
                        PurchaseManager.this.mSkuDetailsMap.put(qVar.e(), qVar);
                    }
                    try {
                        JSONArray jSONArray = new JSONArray();
                        for (q qVar2 : list) {
                            jSONArray.put(Product.createFromSkuDetails(qVar2, PurchaseManager.this.getProductType(qVar2.e())).toJson());
                        }
                        PurchaseManager.this.mPurchaseEventListener.onQueryProductListSuccess(jSONArray);
                        return;
                    } catch (JSONException e) {
                        purchaseEventListener2 = PurchaseManager.this.mPurchaseEventListener;
                        message = e.getMessage();
                    }
                } else {
                    message = String.format(Locale.ENGLISH, "queryProductListAsync failed: responseCode=%d(%s), debugMessage=%s", Integer.valueOf(hVar.b()), PurchaseManager.billingResponseCodeToString(hVar.b()), hVar.a());
                    Log.e(PurchaseManager.TAG, message);
                    purchaseEventListener2 = PurchaseManager.this.mPurchaseEventListener;
                }
                purchaseEventListener2.onQueryProductListFailure(message);
            }
        });
    }

    public void queryPurchases() {
        this.mBillingClient.a("inapp", new o() { // from class: iap.PurchaseManager.5
            @Override // com.android.billingclient.api.o
            public void onQueryPurchasesResponse(h hVar, List<l> list) {
                PurchaseEventListener purchaseEventListener;
                int iB;
                String message;
                if (hVar.b() == 0) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        for (l lVar : list) {
                            q skuDetailsByProductId = PurchaseManager.this.getSkuDetailsByProductId(lVar.h().get(0));
                            if (skuDetailsByProductId != null) {
                                Product productFromSkuDetails = PurchaseManager.this.getProductFromSkuDetails(skuDetailsByProductId);
                                productFromSkuDetails.transactionID = lVar.a();
                                productFromSkuDetails.receipt = lVar.b();
                                productFromSkuDetails.receiptCipheredPayload = lVar.g();
                                productFromSkuDetails.quantity = lVar.f();
                                jSONArray.put(productFromSkuDetails.toJson());
                            }
                        }
                        PurchaseManager.this.mPurchaseEventListener.onQueryPurchasesSuccess(jSONArray);
                        return;
                    } catch (JSONException e) {
                        purchaseEventListener = PurchaseManager.this.mPurchaseEventListener;
                        iB = 6;
                        message = e.getMessage();
                    }
                } else {
                    purchaseEventListener = PurchaseManager.this.mPurchaseEventListener;
                    iB = hVar.b();
                    message = hVar.a();
                }
                purchaseEventListener.onQueryPurchasesFailure(iB, message);
            }
        });
    }

    public void removePurchaseEventListener() {
        this.mPurchaseEventListener = null;
    }

    public void setPurchaseEventListener(PurchaseEventListener purchaseEventListener) {
        this.mPurchaseEventListener = purchaseEventListener;
    }
}

package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.internal.play_billing.zzb;

/* JADX INFO: loaded from: classes.dex */
@UsedByReflection("PlatformActivityProxy")
public class ProxyBillingActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ResultReceiver f429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ResultReceiver f430b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f431c;

    private Intent a() {
        Intent intent = new Intent("com.android.vending.billing.PURCHASES_UPDATED");
        intent.setPackage(getApplicationContext().getPackageName());
        return intent;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x007a A[PHI: r6 r7
  0x007a: PHI (r6v5 int) = (r6v3 int), (r6v8 int) binds: [B:25:0x0077, B:12:0x003e] A[DONT_GENERATE, DONT_INLINE]
  0x007a: PHI (r7v5 android.os.ResultReceiver) = (r7v3 android.os.ResultReceiver), (r7v9 android.os.ResultReceiver) binds: [B:25:0x0077, B:12:0x003e] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        int iZza;
        ResultReceiver resultReceiver;
        super.onActivityResult(i, i2, intent);
        Bundle extras = null;
        if (i == 100) {
            iZza = zzb.zzh(intent, "ProxyBillingActivity").b();
            if (i2 != -1) {
                StringBuilder sb = new StringBuilder(85);
                sb.append("Activity finished with resultCode ");
                sb.append(i2);
                sb.append(" and billing's responseCode: ");
                sb.append(iZza);
                zzb.zzn("ProxyBillingActivity", sb.toString());
            } else if (iZza != 0) {
                i2 = -1;
                StringBuilder sb2 = new StringBuilder(85);
                sb2.append("Activity finished with resultCode ");
                sb2.append(i2);
                sb2.append(" and billing's responseCode: ");
                sb2.append(iZza);
                zzb.zzn("ProxyBillingActivity", sb2.toString());
            } else {
                iZza = 0;
            }
            resultReceiver = this.f429a;
            if (resultReceiver != null) {
                if (intent != null) {
                    extras = intent.getExtras();
                }
                resultReceiver.send(iZza, extras);
            } else {
                Intent intentA = a();
                if (intent != null) {
                    if (intent.getExtras() != null) {
                        intentA.putExtras(intent.getExtras());
                    } else {
                        zzb.zzn("ProxyBillingActivity", "Got null bundle!");
                        intentA.putExtra("RESPONSE_CODE", 6);
                        intentA.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                    }
                }
                sendBroadcast(intentA);
            }
        } else if (i == 101) {
            iZza = zzb.zza(intent, "ProxyBillingActivity");
            resultReceiver = this.f430b;
            if (resultReceiver != null) {
                if (intent != null) {
                    extras = intent.getExtras();
                }
                resultReceiver.send(iZza, extras);
            }
        } else {
            StringBuilder sb3 = new StringBuilder(69);
            sb3.append("Got onActivityResult with wrong requestCode: ");
            sb3.append(i);
            sb3.append("; skipping...");
            zzb.zzn("ProxyBillingActivity", sb3.toString());
        }
        this.f431c = false;
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        PendingIntent pendingIntent;
        int i = 100;
        super.onCreate(bundle);
        if (bundle != null) {
            zzb.zzm("ProxyBillingActivity", "Launching Play Store billing flow from savedInstanceState");
            this.f431c = bundle.getBoolean("send_cancelled_broadcast_if_finished", false);
            if (bundle.containsKey("result_receiver")) {
                this.f429a = (ResultReceiver) bundle.getParcelable("result_receiver");
                return;
            } else {
                if (bundle.containsKey("in_app_message_result_receiver")) {
                    this.f430b = (ResultReceiver) bundle.getParcelable("in_app_message_result_receiver");
                    return;
                }
                return;
            }
        }
        zzb.zzm("ProxyBillingActivity", "Launching Play Store billing flow");
        try {
            if (getIntent().hasExtra("BUY_INTENT")) {
                pendingIntent = (PendingIntent) getIntent().getParcelableExtra("BUY_INTENT");
            } else {
                if (!getIntent().hasExtra("SUBS_MANAGEMENT_INTENT")) {
                    if (getIntent().hasExtra("IN_APP_MESSAGE_INTENT")) {
                        pendingIntent = (PendingIntent) getIntent().getParcelableExtra("IN_APP_MESSAGE_INTENT");
                        this.f430b = (ResultReceiver) getIntent().getParcelableExtra("in_app_message_result_receiver");
                        i = 101;
                    } else {
                        pendingIntent = null;
                    }
                    this.f431c = true;
                    startIntentSenderForResult(pendingIntent.getIntentSender(), i, new Intent(), 0, 0, 0);
                    return;
                }
                pendingIntent = (PendingIntent) getIntent().getParcelableExtra("SUBS_MANAGEMENT_INTENT");
                this.f429a = (ResultReceiver) getIntent().getParcelableExtra("result_receiver");
            }
            this.f431c = true;
            startIntentSenderForResult(pendingIntent.getIntentSender(), i, new Intent(), 0, 0, 0);
            return;
        } catch (IntentSender.SendIntentException e) {
            zzb.zzo("ProxyBillingActivity", "Got exception while trying to start a purchase flow.", e);
            ResultReceiver resultReceiver = this.f429a;
            if (resultReceiver != null) {
                resultReceiver.send(6, null);
            } else {
                ResultReceiver resultReceiver2 = this.f430b;
                if (resultReceiver2 != null) {
                    resultReceiver2.send(0, null);
                } else {
                    Intent intentA = a();
                    intentA.putExtra("RESPONSE_CODE", 6);
                    intentA.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                    sendBroadcast(intentA);
                }
            }
            this.f431c = false;
            finish();
            return;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing() && this.f431c) {
            Intent intentA = a();
            intentA.putExtra("RESPONSE_CODE", 1);
            intentA.putExtra("DEBUG_MESSAGE", "Billing dialog closed.");
            sendBroadcast(intentA);
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        ResultReceiver resultReceiver = this.f429a;
        if (resultReceiver != null) {
            bundle.putParcelable("result_receiver", resultReceiver);
        }
        ResultReceiver resultReceiver2 = this.f430b;
        if (resultReceiver2 != null) {
            bundle.putParcelable("in_app_message_result_receiver", resultReceiver2);
        }
        bundle.putBoolean("send_cancelled_broadcast_if_finished", this.f431c);
    }
}

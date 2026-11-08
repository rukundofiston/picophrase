package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzb;
import org.cocos2dx.lib.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f467b;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f468a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f469b = BuildConfig.FLAVOR;

        /* synthetic */ a(e0 e0Var) {
        }

        public a a(int i) {
            this.f468a = i;
            return this;
        }

        public a a(String str) {
            this.f469b = str;
            return this;
        }

        public h a() {
            h hVar = new h();
            hVar.f466a = this.f468a;
            hVar.f467b = this.f469b;
            return hVar;
        }
    }

    public static a c() {
        return new a(null);
    }

    public String a() {
        return this.f467b;
    }

    public int b() {
        return this.f466a;
    }

    public String toString() {
        String strZzk = zzb.zzk(this.f466a);
        String str = this.f467b;
        StringBuilder sb = new StringBuilder(String.valueOf(strZzk).length() + 32 + String.valueOf(str).length());
        sb.append("Response Code: ");
        sb.append(strZzk);
        sb.append(", Debug Message: ");
        sb.append(str);
        return sb.toString();
    }
}

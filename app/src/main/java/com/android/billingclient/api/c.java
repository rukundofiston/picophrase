package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private volatile boolean f444a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Context f445b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private volatile p f446c;

        /* synthetic */ a(Context context, u0 u0Var) {
            this.f445b = context;
        }

        public a a(p pVar) {
            this.f446c = pVar;
            return this;
        }

        public c a() {
            if (this.f445b == null) {
                throw new IllegalArgumentException("Please provide a valid Context.");
            }
            if (this.f446c == null) {
                throw new IllegalArgumentException("Please provide a valid listener for purchases updates.");
            }
            p pVar = this.f446c;
            if (!this.f444a) {
                throw new IllegalArgumentException("Support for pending purchases must be enabled. Enable this by calling 'enablePendingPurchases()' on BillingClientBuilder.");
            }
            p pVar2 = this.f446c;
            return this.f446c != null ? new d(null, this.f444a, this.f445b, this.f446c, null) : new d(null, this.f444a, this.f445b, null);
        }

        public a b() {
            this.f444a = true;
            return this;
        }
    }

    public static a a(Context context) {
        return new a(context, null);
    }

    public abstract h a(Activity activity, f fVar);

    public abstract void a();

    public abstract void a(com.android.billingclient.api.a aVar, b bVar);

    public abstract void a(e eVar);

    public abstract void a(i iVar, j jVar);

    public abstract void a(r rVar, s sVar);

    public abstract void a(String str, n nVar);

    public abstract void a(String str, o oVar);

    public abstract boolean b();
}

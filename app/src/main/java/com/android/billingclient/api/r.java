package com.android.billingclient.api;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f484a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List f485b;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f486a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private List f487b;

        /* synthetic */ a(k0 k0Var) {
        }

        public a a(String str) {
            this.f486a = str;
            return this;
        }

        public a a(List<String> list) {
            this.f487b = new ArrayList(list);
            return this;
        }

        public r a() {
            String str = this.f486a;
            if (str == null) {
                throw new IllegalArgumentException("SKU type must be set");
            }
            if (this.f487b == null) {
                throw new IllegalArgumentException("SKU list or SkuWithOffer list must be set");
            }
            r rVar = new r();
            rVar.f484a = str;
            rVar.f485b = this.f487b;
            return rVar;
        }
    }

    public static a c() {
        return new a(null);
    }

    public String a() {
        return this.f484a;
    }

    public List<String> b() {
        return this.f485b;
    }
}

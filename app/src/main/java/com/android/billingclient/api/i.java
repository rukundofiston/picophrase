package com.android.billingclient.api;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f470a;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f471a;

        /* synthetic */ a(g0 g0Var) {
        }

        public a a(String str) {
            this.f471a = str;
            return this;
        }

        public i a() {
            String str = this.f471a;
            if (str == null) {
                throw new IllegalArgumentException("Purchase token must be set");
            }
            i iVar = new i(null);
            iVar.f470a = str;
            return iVar;
        }
    }

    /* synthetic */ i(h0 h0Var) {
    }

    public static a b() {
        return new a(null);
    }

    public String a() {
        return this.f470a;
    }
}

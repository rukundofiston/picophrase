package com.android.billingclient.api;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f432a;

    /* JADX INFO: renamed from: com.android.billingclient.api.a$a, reason: collision with other inner class name */
    public static final class C0010a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f433a;

        /* synthetic */ C0010a(t tVar) {
        }

        public C0010a a(String str) {
            this.f433a = str;
            return this;
        }

        public a a() {
            String str = this.f433a;
            if (str == null) {
                throw new IllegalArgumentException("Purchase token must be set");
            }
            a aVar = new a(null);
            aVar.f432a = str;
            return aVar;
        }
    }

    /* synthetic */ a(l0 l0Var) {
    }

    public static C0010a b() {
        return new C0010a(null);
    }

    public String a() {
        return this.f432a;
    }
}

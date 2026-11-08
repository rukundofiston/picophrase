package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzu;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f454a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f455b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f456c;
    private String d;
    private int e = 0;
    private zzu f;
    private ArrayList g;
    private boolean h;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f457a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f458b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f459c;
        private int d = 0;
        private ArrayList e;
        private boolean f;

        /* synthetic */ a(c0 c0Var) {
        }

        public a a(q qVar) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(qVar);
            this.e = arrayList;
            return this;
        }

        public f a() {
            ArrayList arrayList = this.e;
            if (arrayList == null || arrayList.isEmpty()) {
                throw new IllegalArgumentException("Details of the products must be provided.");
            }
            d0 d0Var = null;
            if (this.e.contains(null)) {
                throw new IllegalArgumentException("SKU cannot be null.");
            }
            if (this.e.size() > 1) {
                q qVar = (q) this.e.get(0);
                String strG = qVar.g();
                ArrayList arrayList2 = this.e;
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    q qVar2 = (q) arrayList2.get(i);
                    if (!strG.equals("play_pass_subs") && !qVar2.g().equals("play_pass_subs") && !strG.equals(qVar2.g())) {
                        throw new IllegalArgumentException("SKUs should have the same type.");
                    }
                }
                String strK = qVar.k();
                ArrayList arrayList3 = this.e;
                int size2 = arrayList3.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    q qVar3 = (q) arrayList3.get(i2);
                    if (!strG.equals("play_pass_subs") && !qVar3.g().equals("play_pass_subs") && !strK.equals(qVar3.k())) {
                        throw new IllegalArgumentException("All SKUs must have the same package name.");
                    }
                }
            }
            f fVar = new f(d0Var);
            fVar.f454a = !((q) this.e.get(0)).k().isEmpty();
            fVar.f455b = this.f457a;
            fVar.d = this.f459c;
            fVar.f456c = this.f458b;
            fVar.e = this.d;
            ArrayList arrayList4 = this.e;
            fVar.g = arrayList4 != null ? new ArrayList(arrayList4) : new ArrayList();
            fVar.h = this.f;
            fVar.f = zzu.zzh();
            return fVar;
        }
    }

    /* synthetic */ f(d0 d0Var) {
    }

    public static a i() {
        return new a(null);
    }

    public boolean a() {
        return this.h;
    }

    public final int b() {
        return this.e;
    }

    public final String c() {
        return this.f455b;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.f456c;
    }

    public final ArrayList f() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.g);
        return arrayList;
    }

    public final List g() {
        return this.f;
    }

    final boolean h() {
        return (!this.h && this.f455b == null && this.d == null && this.e == 0 && !this.f454a) ? false : true;
    }
}

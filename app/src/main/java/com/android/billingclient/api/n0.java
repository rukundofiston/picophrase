package com.android.billingclient.api;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f480a;

    /* synthetic */ n0(m0 m0Var) {
    }

    public final n0 a(String str) {
        this.f480a = str;
        return this;
    }

    public final p0 a() {
        if (TextUtils.isEmpty(this.f480a)) {
            throw new IllegalArgumentException("SKU must be set.");
        }
        return new p0(this.f480a, null, null, 0, null);
    }
}

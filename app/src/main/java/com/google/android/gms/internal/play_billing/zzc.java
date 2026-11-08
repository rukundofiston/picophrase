package com.google.android.gms.internal.play_billing;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class zzc extends zzh implements zze {
    zzc(IBinder iBinder) {
        super(iBinder, "com.android.vending.billing.IInAppBillingService");
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final int zza(int i, String str, String str2) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(3);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        Parcel parcelZzp = zzp(5, parcelZzo);
        int i2 = parcelZzp.readInt();
        parcelZzp.recycle();
        return i2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final int zzc(int i, String str, String str2, Bundle bundle) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(i);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        zzj.zzc(parcelZzo, bundle);
        Parcel parcelZzp = zzp(10, parcelZzo);
        int i2 = parcelZzp.readInt();
        parcelZzp.recycle();
        return i2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzd(int i, String str, String str2, Bundle bundle) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(9);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        zzj.zzc(parcelZzo, bundle);
        Parcel parcelZzp = zzp(902, parcelZzo);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzp, Bundle.CREATOR);
        parcelZzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zze(int i, String str, String str2, Bundle bundle) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(9);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        zzj.zzc(parcelZzo, bundle);
        Parcel parcelZzp = zzp(12, parcelZzo);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzp, Bundle.CREATOR);
        parcelZzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzf(int i, String str, String str2, String str3, String str4) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(3);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        parcelZzo.writeString(str3);
        parcelZzo.writeString(null);
        Parcel parcelZzp = zzp(3, parcelZzo);
        Bundle bundle = (Bundle) zzj.zza(parcelZzp, Bundle.CREATOR);
        parcelZzp.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzg(int i, String str, String str2, String str3, String str4, Bundle bundle) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(i);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        parcelZzo.writeString(str3);
        parcelZzo.writeString(null);
        zzj.zzc(parcelZzo, bundle);
        Parcel parcelZzp = zzp(8, parcelZzo);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzp, Bundle.CREATOR);
        parcelZzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzh(int i, String str, String str2, String str3, Bundle bundle) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(6);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        parcelZzo.writeString(str3);
        zzj.zzc(parcelZzo, bundle);
        Parcel parcelZzp = zzp(9, parcelZzo);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzp, Bundle.CREATOR);
        parcelZzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzi(int i, String str, String str2, String str3) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(3);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        parcelZzo.writeString(str3);
        Parcel parcelZzp = zzp(4, parcelZzo);
        Bundle bundle = (Bundle) zzj.zza(parcelZzp, Bundle.CREATOR);
        parcelZzp.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzj(int i, String str, String str2, String str3, Bundle bundle) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(9);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        parcelZzo.writeString(str3);
        zzj.zzc(parcelZzo, bundle);
        Parcel parcelZzp = zzp(11, parcelZzo);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzp, Bundle.CREATOR);
        parcelZzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzk(int i, String str, String str2, Bundle bundle) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(3);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        zzj.zzc(parcelZzo, bundle);
        Parcel parcelZzp = zzp(2, parcelZzo);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzp, Bundle.CREATOR);
        parcelZzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzl(int i, String str, String str2, Bundle bundle, Bundle bundle2) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(10);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        zzj.zzc(parcelZzo, bundle);
        zzj.zzc(parcelZzo, bundle2);
        Parcel parcelZzp = zzp(901, parcelZzo);
        Bundle bundle3 = (Bundle) zzj.zza(parcelZzp, Bundle.CREATOR);
        parcelZzp.recycle();
        return bundle3;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzm(int i, String str, String str2, String str3, Bundle bundle) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(8);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        parcelZzo.writeString("subs");
        zzj.zzc(parcelZzo, bundle);
        Parcel parcelZzp = zzp(801, parcelZzo);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzp, Bundle.CREATOR);
        parcelZzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final void zzn(int i, String str, Bundle bundle, zzg zzgVar) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(12);
        parcelZzo.writeString(str);
        zzj.zzc(parcelZzo, bundle);
        zzj.zzd(parcelZzo, zzgVar);
        zzq(1201, parcelZzo);
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final int zzr(int i, String str, String str2) throws android.os.RemoteException {
        Parcel parcelZzo = zzo();
        parcelZzo.writeInt(i);
        parcelZzo.writeString(str);
        parcelZzo.writeString(str2);
        Parcel parcelZzp = zzp(1, parcelZzo);
        int i2 = parcelZzp.readInt();
        parcelZzp.recycle();
        return i2;
    }
}

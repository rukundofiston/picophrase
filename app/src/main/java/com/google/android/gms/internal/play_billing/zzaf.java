package com.google.android.gms.internal.play_billing;

import java.util.Arrays;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
final class zzaf extends zzx {
    static final zzx zza = new zzaf(null, new Object[0], 0);
    final transient Object[] zzb;

    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzaf(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v13, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v4, types: [int[]] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v4 */
    static zzaf zzf(int i, Object[] objArr, zzw zzwVar) {
        int i2 = i;
        Object[] objArrCopyOf = objArr;
        if (i2 == 0) {
            return (zzaf) zza;
        }
        Object objTable = null;
        if (i2 == 1) {
            Object obj = objArrCopyOf[0];
            obj.getClass();
            Object obj2 = objArrCopyOf[1];
            obj2.getClass();
            zzp.zza(obj, obj2);
            return new zzaf(null, objArrCopyOf, 1);
        }
        zzm.zzb(i2, objArrCopyOf.length >> 1, "index");
        int iMax = Math.max(i2, 2);
        int i3 = 1073741824;
        if (iMax < 751619276) {
            int iHighestOneBit = Integer.highestOneBit(iMax - 1);
            i3 = iHighestOneBit + iHighestOneBit;
            while (((double) i3) * 0.7d < iMax) {
                i3 += i3;
            }
        } else if (iMax >= 1073741824) {
            throw new IllegalArgumentException("collection too large");
        }
        if (i2 == 1) {
            Object obj3 = objArrCopyOf[0];
            obj3.getClass();
            Object obj4 = objArrCopyOf[1];
            obj4.getClass();
            zzp.zza(obj3, obj4);
        } else {
            int i4 = i3 - 1;
            if (i3 <= 128) {
                byte[] bArr = new byte[i3];
                Arrays.fill(bArr, (byte) -1);
                zzv zzvVar2 = null;
                int i5 = 0;
                for (int i6 = 0; i6 < i2; i6++) {
                    int i7 = i6 + i6;
                    int i8 = i5 + i5;
                    Object obj5 = objArrCopyOf[i7];
                    obj5.getClass();
                    Object obj6 = objArrCopyOf[i7 ^ 1];
                    obj6.getClass();
                    zzp.zza(obj5, obj6);
                    int iZza = zzq.zza(obj5.hashCode());
                    while (true) {
                        int i9 = iZza & i4;
                        int i10 = bArr[i9] & 255;
                        if (i10 == 255) {
                            bArr[i9] = (byte) i8;
                            if (i5 < i6) {
                                objArrCopyOf[i8] = obj5;
                                objArrCopyOf[i8 ^ 1] = obj6;
                            }
                            i5++;
                            break;
                        }
                        if (obj5.equals(objArrCopyOf[i10])) {
                            int i11 = i10 ^ 1;
                            Object obj7 = objArrCopyOf[i11];
                            obj7.getClass();
                            zzvVar2 = new zzv(obj5, obj6, obj7);
                            objArrCopyOf[i11] = obj6;
                            break;
                        }
                        iZza = i9 + 1;
                    }
                }
                objTable = i5 == i2 ? bArr : new Object[]{bArr, Integer.valueOf(i5), zzvVar2};
            } else if (i3 <= 32768) {
                short[] sArr = new short[i3];
                Arrays.fill(sArr, (short) -1);
                zzv zzvVar3 = null;
                int i12 = 0;
                for (int i13 = 0; i13 < i2; i13++) {
                    int i14 = i13 + i13;
                    int i15 = i12 + i12;
                    Object obj8 = objArrCopyOf[i14];
                    obj8.getClass();
                    Object obj9 = objArrCopyOf[i14 ^ 1];
                    obj9.getClass();
                    zzp.zza(obj8, obj9);
                    int iZza2 = zzq.zza(obj8.hashCode());
                    while (true) {
                        int i16 = iZza2 & i4;
                        char c3 = (char) sArr[i16];
                        if (c3 == 65535) {
                            sArr[i16] = (short) i15;
                            if (i12 < i13) {
                                objArrCopyOf[i15] = obj8;
                                objArrCopyOf[i15 ^ 1] = obj9;
                            }
                            i12++;
                            break;
                        }
                        if (obj8.equals(objArrCopyOf[c3])) {
                            int i17 = c3 ^ 1;
                            Object obj10 = objArrCopyOf[i17];
                            obj10.getClass();
                            zzvVar3 = new zzv(obj8, obj9, obj10);
                            objArrCopyOf[i17] = obj9;
                            break;
                        }
                        iZza2 = i16 + 1;
                    }
                }
                objTable = i12 == i2 ? sArr : new Object[]{sArr, Integer.valueOf(i12), zzvVar3};
            } else {
                int[] iArr = new int[i3];
                Arrays.fill(iArr, -1);
                zzv zzvVar = null;
                int i19 = 0;
                for (int i18 = 0; i18 < i2; i18++) {
                    int i20 = i18 + i18;
                    int i21 = i19 + i19;
                    Object obj11 = objArrCopyOf[i20];
                    obj11.getClass();
                    Object obj12 = objArrCopyOf[i20 ^ 1];
                    obj12.getClass();
                    zzp.zza(obj11, obj12);
                    int iZza3 = zzq.zza(obj11.hashCode());
                    while (true) {
                        int i22 = iZza3 & i4;
                        int iVal = iArr[i22];
                        if (iVal == -1) {
                            iArr[i22] = i21;
                            if (i19 < i18) {
                                objArrCopyOf[i21] = obj11;
                                objArrCopyOf[i21 ^ 1] = obj12;
                            }
                            i19++;
                            break;
                        }
                        if (obj11.equals(objArrCopyOf[iVal])) {
                            int i23 = iVal ^ 1;
                            Object obj13 = objArrCopyOf[i23];
                            obj13.getClass();
                            zzvVar = new zzv(obj11, obj12, obj13);
                            objArrCopyOf[i23] = obj12;
                            break;
                        }
                        iZza3 = i22 + 1;
                    }
                }
                objTable = i19 == i2 ? iArr : new Object[]{iArr, Integer.valueOf(i19), zzvVar};
            }
        }
        if (objTable instanceof Object[]) {
            Object[] objArr2 = (Object[]) objTable;
            zzwVar.zzc = (zzv) objArr2[2];
            Object obj14 = objArr2[0];
            int iIntValue = ((Integer) objArr2[1]).intValue();
            objArrCopyOf = Arrays.copyOf(objArrCopyOf, iIntValue + iIntValue);
            objTable = obj14;
            i2 = iIntValue;
        }
        return new zzaf(objTable, objArrCopyOf, i2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzx, java.util.Map
    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        Object obj3 = this.zzc;
        Object[] objArr = this.zzb;
        int i = this.zzd;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            Object obj4 = objArr[0];
            obj4.getClass();
            if (!obj4.equals(obj)) {
                return null;
            }
            Object obj2 = objArr[1];
            obj2.getClass();
            return obj2;
        }
        if (obj3 == null) {
            return null;
        }
        if (obj3 instanceof byte[]) {
            byte[] bArr = (byte[]) obj3;
            int length = bArr.length - 1;
            int iZza = zzq.zza(obj.hashCode());
            while (true) {
                int i2 = iZza & length;
                int i3 = bArr[i2] & 255;
                if (i3 == 255) {
                    return null;
                }
                if (obj.equals(objArr[i3])) {
                    return objArr[i3 ^ 1];
                }
                iZza = i2 + 1;
            }
        } else if (obj3 instanceof short[]) {
            short[] sArr = (short[]) obj3;
            int length2 = sArr.length - 1;
            int iZza2 = zzq.zza(obj.hashCode());
            while (true) {
                int i4 = iZza2 & length2;
                char c2 = (char) sArr[i4];
                if (c2 == 65535) {
                    return null;
                }
                if (obj.equals(objArr[c2])) {
                    return objArr[c2 ^ 1];
                }
                iZza2 = i4 + 1;
            }
        } else {
            int[] iArr = (int[]) obj3;
            int length3 = iArr.length - 1;
            int iZza3 = zzq.zza(obj.hashCode());
            while (true) {
                int i5 = iZza3 & length3;
                int i6 = iArr[i5];
                if (i6 == -1) {
                    return null;
                }
                if (obj.equals(objArr[i6])) {
                    return objArr[i6 ^ 1];
                }
                iZza3 = i5 + 1;
            }
        }
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    final zzr zza() {
        return new zzae(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    final zzy zzc() {
        return new zzac(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    final zzy zzd() {
        return new zzad(this, new zzae(this.zzb, 0, this.zzd));
    }
}

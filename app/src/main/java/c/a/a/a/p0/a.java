package c.a.a.a.p0;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: c.a.a.a.p0.a$a, reason: collision with other inner class name */
    static abstract class AbstractC0009a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte[] f182a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f183b;

        AbstractC0009a() {
        }
    }

    static class b extends AbstractC0009a {
        private static final byte[] j = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] k = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final byte[] f184c;
        int d;
        private int e;
        public final boolean f;
        public final boolean g;
        public final boolean h;
        private final byte[] i;

        public b(int i, byte[] bArr) {
            this.f182a = bArr;
            this.f = (i & 1) == 0;
            this.g = (i & 2) == 0;
            this.h = (i & 4) != 0;
            this.i = (i & 8) == 0 ? j : k;
            this.f184c = new byte[2];
            this.d = 0;
            this.e = this.g ? 19 : -1;
        }

        public boolean a(byte[] bArr, int i, int i2, boolean z) {
            byte[] bArr2 = this.i;
            byte[] bArr3 = this.f182a;
            int i3 = 0;
            int i4 = this.e;
            int i5 = i2 + i;
            int i6 = -1;
            int i7 = this.d;
            if (i7 != 0) {
                if (i7 != 1) {
                    if (i7 == 2 && i + 1 <= i5) {
                        int i8 = i + 1;
                        i6 = ((this.f184c[0] & 255) << 16) | ((this.f184c[1] & 255) << 8) | (bArr[i] & 255);
                        this.d = 0;
                        i = i8;
                    }
                } else if (i + 2 <= i5) {
                    int i9 = i + 1;
                    int i10 = i9 + 1;
                    i6 = ((this.f184c[0] & 255) << 16) | ((bArr[i] & 255) << 8) | (bArr[i9] & 255);
                    this.d = 0;
                    i = i10;
                }
            }
            if (i6 != -1) {
                bArr3[0] = bArr2[(i6 >> 18) & 63];
                bArr3[1] = bArr2[(i6 >> 12) & 63];
                bArr3[2] = bArr2[(i6 >> 6) & 63];
                i3 = 4;
                bArr3[3] = bArr2[i6 & 63];
                i4--;
                if (i4 == 0) {
                    if (this.h) {
                        bArr3[4] = 13;
                        i3 = 5;
                    }
                    bArr3[i3] = 10;
                    i3++;
                    i4 = 19;
                }
            }
            while (i + 3 <= i5) {
                int i11 = ((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << 8) | (bArr[i + 2] & 255);
                bArr3[i3] = bArr2[(i11 >> 18) & 63];
                bArr3[i3 + 1] = bArr2[(i11 >> 12) & 63];
                bArr3[i3 + 2] = bArr2[(i11 >> 6) & 63];
                bArr3[i3 + 3] = bArr2[i11 & 63];
                i += 3;
                i3 += 4;
                i4--;
                if (i4 == 0) {
                    if (this.h) {
                        bArr3[i3] = 13;
                        i3++;
                    }
                    bArr3[i3] = 10;
                    i3++;
                    i4 = 19;
                }
            }
            if (z) {
                int i12 = this.d;
                if (i - i12 == i5 - 1) {
                    int i13 = ((i12 > 0 ? this.f184c[0] : bArr[i]) & 255) << 4;
                    this.d -= i12;
                    int i14 = i3 + 1;
                    bArr3[i3] = bArr2[(i13 >> 6) & 63];
                    i3 = i14 + 1;
                    bArr3[i14] = bArr2[i13 & 63];
                    if (this.f) {
                        int i15 = i3 + 1;
                        bArr3[i3] = 61;
                        i3 = i15 + 1;
                        bArr3[i15] = 61;
                    }
                    if (this.g) {
                        if (this.h) {
                            bArr3[i3] = 13;
                            i3++;
                        }
                        bArr3[i3] = 10;
                        i3++;
                    }
                } else if (i - i12 == i5 - 2) {
                    int i16 = (((i12 > 1 ? this.f184c[0] : bArr[i]) & 255) << 10) | (((i12 > 0 ? this.f184c[i12 - 1] : bArr[i + 1 - i12]) & 255) << 2);
                    this.d = 0;
                    int i17 = i3 + 1;
                    bArr3[i3] = bArr2[(i16 >> 12) & 63];
                    int i18 = i17 + 1;
                    bArr3[i17] = bArr2[(i16 >> 6) & 63];
                    i3 = i18 + 1;
                    bArr3[i18] = bArr2[i16 & 63];
                    if (this.f) {
                        bArr3[i3] = 61;
                        i3++;
                    }
                    if (this.g) {
                        if (this.h) {
                            bArr3[i3] = 13;
                            i3++;
                        }
                        bArr3[i3] = 10;
                        i3++;
                    }
                } else if (this.g && i3 > 0 && i4 != 19) {
                    if (this.h) {
                        bArr3[i3] = 13;
                        i3++;
                    }
                    bArr3[i3] = 10;
                    i3++;
                }
            } else if (i == i5 - 1) {
                byte[] bArr4 = this.f184c;
                int i19 = this.d;
                this.d = i19 + 1;
                bArr4[i19] = bArr[i];
            } else if (i == i5 - 2) {
                byte[] bArr5 = this.f184c;
                int i20 = this.d;
                int i21 = i20 + 1;
                this.d = i21;
                bArr5[i20] = bArr[i];
                this.d = i21 + 1;
                bArr5[i21] = bArr[i + 1];
            }
            this.f183b = i3;
            this.e = i4;
            return true;
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        return android.util.Base64.encode(bArr, i, i2, i3);
    }
}

package c.a.a.a.x0;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private byte[] f422a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f423b;

    public c(int i) {
        a.a(i, "Buffer capacity");
        this.f422a = new byte[i];
    }

    private void c(int i) {
        byte[] bArr = new byte[Math.max(this.f422a.length << 1, i)];
        System.arraycopy(this.f422a, 0, bArr, 0, this.f423b);
        this.f422a = bArr;
    }

    public void a(int i) {
        int i2 = this.f423b + 1;
        if (i2 > this.f422a.length) {
            c(i2);
        }
        this.f422a[this.f423b] = (byte) i;
        this.f423b = i2;
    }

    public void a(d dVar, int i, int i2) {
        if (dVar == null) {
            return;
        }
        a(dVar.a(), i, i2);
    }

    public void a(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr == null) {
            return;
        }
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
            throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + bArr.length);
        }
        if (i2 == 0) {
            return;
        }
        int i4 = this.f423b + i2;
        if (i4 > this.f422a.length) {
            c(i4);
        }
        System.arraycopy(bArr, i, this.f422a, this.f423b, i2);
        this.f423b = i4;
    }

    public void a(char[] cArr, int i, int i2) {
        int i3;
        if (cArr == null) {
            return;
        }
        if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) < 0 || i3 > cArr.length) {
            throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + cArr.length);
        }
        if (i2 == 0) {
            return;
        }
        int i4 = this.f423b;
        int i5 = i2 + i4;
        if (i5 > this.f422a.length) {
            c(i5);
        }
        while (i4 < i5) {
            this.f422a[i4] = (byte) cArr[i];
            i++;
            i4++;
        }
        this.f423b = i5;
    }

    public byte[] a() {
        return this.f422a;
    }

    public int b() {
        return this.f422a.length;
    }

    public int b(int i) {
        return this.f422a[i];
    }

    public void c() {
        this.f423b = 0;
    }

    public boolean d() {
        return this.f423b == 0;
    }

    public boolean e() {
        return this.f423b == this.f422a.length;
    }

    public int f() {
        return this.f423b;
    }

    public byte[] g() {
        int i = this.f423b;
        byte[] bArr = new byte[i];
        if (i > 0) {
            System.arraycopy(this.f422a, 0, bArr, 0, i);
        }
        return bArr;
    }
}

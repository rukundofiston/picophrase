package c.a.a.a.x0;

import java.io.Serializable;
import java.nio.CharBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class d implements CharSequence, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private char[] f424a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f425b;

    public d(int i) {
        a.a(i, "Buffer capacity");
        this.f424a = new char[i];
    }

    private void c(int i) {
        char[] cArr = new char[Math.max(this.f424a.length << 1, i)];
        System.arraycopy(this.f424a, 0, cArr, 0, this.f425b);
        this.f424a = cArr;
    }

    public int a(int i, int i2, int i3) {
        if (i2 < 0) {
            i2 = 0;
        }
        int i4 = this.f425b;
        if (i3 > i4) {
            i3 = i4;
        }
        if (i2 > i3) {
            return -1;
        }
        while (i2 < i3) {
            if (this.f424a[i2] == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public String a(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i);
        }
        if (i2 > this.f425b) {
            throw new IndexOutOfBoundsException("endIndex: " + i2 + " > length: " + this.f425b);
        }
        if (i <= i2) {
            return new String(this.f424a, i, i2 - i);
        }
        throw new IndexOutOfBoundsException("beginIndex: " + i + " > endIndex: " + i2);
    }

    public void a(char c2) {
        int i = this.f425b + 1;
        if (i > this.f424a.length) {
            c(i);
        }
        this.f424a[this.f425b] = c2;
        this.f425b = i;
    }

    public void a(int i) {
        if (i <= 0) {
            return;
        }
        int length = this.f424a.length;
        int i2 = this.f425b;
        if (i > length - i2) {
            c(i2 + i);
        }
    }

    public void a(c cVar, int i, int i2) {
        if (cVar == null) {
            return;
        }
        a(cVar.a(), i, i2);
    }

    public void a(d dVar, int i, int i2) {
        if (dVar == null) {
            return;
        }
        a(dVar.f424a, i, i2);
    }

    public void a(String str) {
        if (str == null) {
            str = "null";
        }
        int length = str.length();
        int i = this.f425b + length;
        if (i > this.f424a.length) {
            c(i);
        }
        str.getChars(0, length, this.f424a, this.f425b);
        this.f425b = i;
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
        int i4 = this.f425b;
        int i5 = i2 + i4;
        if (i5 > this.f424a.length) {
            c(i5);
        }
        while (i4 < i5) {
            this.f424a[i4] = (char) (bArr[i] & 255);
            i++;
            i4++;
        }
        this.f425b = i5;
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
        int i4 = this.f425b + i2;
        if (i4 > this.f424a.length) {
            c(i4);
        }
        System.arraycopy(cArr, i, this.f424a, this.f425b, i2);
        this.f425b = i4;
    }

    public char[] a() {
        return this.f424a;
    }

    public int b(int i) {
        return a(i, 0, this.f425b);
    }

    public String b(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i);
        }
        if (i2 > this.f425b) {
            throw new IndexOutOfBoundsException("endIndex: " + i2 + " > length: " + this.f425b);
        }
        if (i > i2) {
            throw new IndexOutOfBoundsException("beginIndex: " + i + " > endIndex: " + i2);
        }
        while (i < i2 && c.a.a.a.v0.d.a(this.f424a[i])) {
            i++;
        }
        while (i2 > i && c.a.a.a.v0.d.a(this.f424a[i2 - 1])) {
            i2--;
        }
        return new String(this.f424a, i, i2 - i);
    }

    public void b() {
        this.f425b = 0;
    }

    public boolean c() {
        return this.f425b == 0;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f424a[i];
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f425b;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i);
        }
        if (i2 > this.f425b) {
            throw new IndexOutOfBoundsException("endIndex: " + i2 + " > length: " + this.f425b);
        }
        if (i <= i2) {
            return CharBuffer.wrap(this.f424a, i, i2);
        }
        throw new IndexOutOfBoundsException("beginIndex: " + i + " > endIndex: " + i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return new String(this.f424a, 0, this.f425b);
    }
}

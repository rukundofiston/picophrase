package a.a;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class d<K, V> {
    static Object[] d;
    static int e;
    static Object[] f;
    static int g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int[] f14a = b.f0a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Object[] f15b = b.f1b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f16c = 0;

    private static int a(int[] iArr, int i, int i2) {
        try {
            return b.a(iArr, i, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private static void a(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (a.class) {
                if (g < 10) {
                    objArr[0] = f;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f = objArr;
                    g++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (a.class) {
                if (e < 10) {
                    objArr[0] = d;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    d = objArr;
                    e++;
                }
            }
        }
    }

    private void e(int i) {
        if (i == 8) {
            synchronized (a.class) {
                if (f != null) {
                    Object[] objArr = f;
                    this.f15b = objArr;
                    f = (Object[]) objArr[0];
                    this.f14a = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    g--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (a.class) {
                if (d != null) {
                    Object[] objArr2 = d;
                    this.f15b = objArr2;
                    d = (Object[]) objArr2[0];
                    this.f14a = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    e--;
                    return;
                }
            }
        }
        this.f14a = new int[i];
        this.f15b = new Object[i << 1];
    }

    int a() {
        int i = this.f16c;
        if (i == 0) {
            return -1;
        }
        int iA = a(this.f14a, i, 0);
        if (iA < 0 || this.f15b[iA << 1] == null) {
            return iA;
        }
        int i2 = iA + 1;
        while (i2 < i && this.f14a[i2] == 0) {
            if (this.f15b[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = iA - 1; i3 >= 0 && this.f14a[i3] == 0; i3--) {
            if (this.f15b[i3 << 1] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public int a(Object obj) {
        return obj == null ? a() : a(obj, obj.hashCode());
    }

    int a(Object obj, int i) {
        int i2 = this.f16c;
        if (i2 == 0) {
            return -1;
        }
        int iA = a(this.f14a, i2, i);
        if (iA < 0 || obj.equals(this.f15b[iA << 1])) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.f14a[i3] == i) {
            if (obj.equals(this.f15b[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.f14a[i4] == i; i4--) {
            if (obj.equals(this.f15b[i4 << 1])) {
                return i4;
            }
        }
        return ~i3;
    }

    public V a(int i, V v) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.f15b;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    public void a(int i) {
        int i2 = this.f16c;
        int[] iArr = this.f14a;
        if (iArr.length < i) {
            Object[] objArr = this.f15b;
            e(i);
            if (this.f16c > 0) {
                System.arraycopy(iArr, 0, this.f14a, 0, i2);
                System.arraycopy(objArr, 0, this.f15b, 0, i2 << 1);
            }
            a(iArr, objArr, i2);
        }
        if (this.f16c != i2) {
            throw new ConcurrentModificationException();
        }
    }

    int b(Object obj) {
        int i = this.f16c * 2;
        Object[] objArr = this.f15b;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public K b(int i) {
        return (K) this.f15b[i << 1];
    }

    public V c(int i) {
        Object[] objArr = this.f15b;
        int i2 = i << 1;
        V v = (V) objArr[i2 + 1];
        int i3 = this.f16c;
        int i4 = 0;
        if (i3 <= 1) {
            a(this.f14a, objArr, i3);
            this.f14a = b.f0a;
            this.f15b = b.f1b;
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.f14a;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i < i5) {
                    int[] iArr2 = this.f14a;
                    int i6 = i + 1;
                    int i7 = i5 - i;
                    System.arraycopy(iArr2, i6, iArr2, i, i7);
                    Object[] objArr2 = this.f15b;
                    System.arraycopy(objArr2, i6 << 1, objArr2, i2, i7 << 1);
                }
                Object[] objArr3 = this.f15b;
                int i8 = i5 << 1;
                objArr3[i8] = null;
                objArr3[i8 + 1] = null;
            } else {
                int i9 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] iArr3 = this.f14a;
                Object[] objArr4 = this.f15b;
                e(i9);
                if (i3 != this.f16c) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    System.arraycopy(iArr3, 0, this.f14a, 0, i);
                    System.arraycopy(objArr4, 0, this.f15b, 0, i2);
                }
                if (i < i5) {
                    int i10 = i + 1;
                    int i11 = i5 - i;
                    System.arraycopy(iArr3, i10, this.f14a, i, i11);
                    System.arraycopy(objArr4, i10 << 1, this.f15b, i2, i11 << 1);
                }
            }
            i4 = i5;
        }
        if (i3 != this.f16c) {
            throw new ConcurrentModificationException();
        }
        this.f16c = i4;
        return v;
    }

    public void clear() {
        int i = this.f16c;
        if (i > 0) {
            int[] iArr = this.f14a;
            Object[] objArr = this.f15b;
            this.f14a = b.f0a;
            this.f15b = b.f1b;
            this.f16c = 0;
            a(iArr, objArr, i);
        }
        if (this.f16c > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return a(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return b(obj) >= 0;
    }

    public V d(int i) {
        return (V) this.f15b[(i << 1) + 1];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (size() != dVar.size()) {
                return false;
            }
            for (int i = 0; i < this.f16c; i++) {
                try {
                    K kB = b(i);
                    V vD = d(i);
                    Object obj2 = dVar.get(kB);
                    if (vD == null) {
                        if (obj2 != null || !dVar.containsKey(kB)) {
                            return false;
                        }
                    } else if (!vD.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f16c; i2++) {
                try {
                    K kB2 = b(i2);
                    V vD2 = d(i2);
                    Object obj3 = map.get(kB2);
                    if (vD2 == null) {
                        if (obj3 != null || !map.containsKey(kB2)) {
                            return false;
                        }
                    } else if (!vD2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public V get(Object obj) {
        int iA = a(obj);
        if (iA >= 0) {
            return (V) this.f15b[(iA << 1) + 1];
        }
        return null;
    }

    public int hashCode() {
        int[] iArr = this.f14a;
        Object[] objArr = this.f15b;
        int i = this.f16c;
        int i2 = 1;
        int i3 = 0;
        int iHashCode = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return iHashCode;
    }

    public boolean isEmpty() {
        return this.f16c <= 0;
    }

    public V put(K k, V v) {
        int i;
        int iA;
        int i2 = this.f16c;
        if (k == null) {
            iA = a();
            i = 0;
        } else {
            int iHashCode = k.hashCode();
            i = iHashCode;
            iA = a(k, iHashCode);
        }
        if (iA >= 0) {
            int i3 = (iA << 1) + 1;
            Object[] objArr = this.f15b;
            V v2 = (V) objArr[i3];
            objArr[i3] = v;
            return v2;
        }
        int i4 = ~iA;
        if (i2 >= this.f14a.length) {
            int i5 = 4;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.f14a;
            Object[] objArr2 = this.f15b;
            e(i5);
            if (i2 != this.f16c) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f14a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f15b, 0, objArr2.length);
            }
            a(iArr, objArr2, i2);
        }
        if (i4 < i2) {
            int[] iArr3 = this.f14a;
            int i6 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i6, i2 - i4);
            Object[] objArr3 = this.f15b;
            System.arraycopy(objArr3, i4 << 1, objArr3, i6 << 1, (this.f16c - i4) << 1);
        }
        int i7 = this.f16c;
        if (i2 == i7) {
            int[] iArr4 = this.f14a;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                Object[] objArr4 = this.f15b;
                int i8 = i4 << 1;
                objArr4[i8] = k;
                objArr4[i8 + 1] = v;
                this.f16c = i7 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public V remove(Object obj) {
        int iA = a(obj);
        if (iA >= 0) {
            return c(iA);
        }
        return null;
    }

    public int size() {
        return this.f16c;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f16c * 28);
        sb.append('{');
        for (int i = 0; i < this.f16c; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            K kB = b(i);
            if (kB != this) {
                sb.append(kB);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vD = d(i);
            if (vD != this) {
                sb.append(vD);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}

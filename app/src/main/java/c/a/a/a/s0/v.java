package c.a.a.a.s0;

/* JADX INFO: loaded from: classes.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f398a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f399b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f400c;

    public v(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        }
        if (i > i2) {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        }
        this.f398a = i;
        this.f399b = i2;
        this.f400c = i;
    }

    public void a(int i) {
        if (i < this.f398a) {
            throw new IndexOutOfBoundsException("pos: " + i + " < lowerBound: " + this.f398a);
        }
        if (i <= this.f399b) {
            this.f400c = i;
            return;
        }
        throw new IndexOutOfBoundsException("pos: " + i + " > upperBound: " + this.f399b);
    }

    public boolean a() {
        return this.f400c >= this.f399b;
    }

    public int b() {
        return this.f400c;
    }

    public int c() {
        return this.f399b;
    }

    public String toString() {
        return '[' + Integer.toString(this.f398a) + '>' + Integer.toString(this.f400c) + '>' + Integer.toString(this.f399b) + ']';
    }
}

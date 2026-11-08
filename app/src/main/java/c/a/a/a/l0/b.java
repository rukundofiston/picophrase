package c.a.a.a.l0;

/* JADX INFO: loaded from: classes.dex */
public class b implements Cloneable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b f112c = new a().a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f113a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f114b;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f115a = -1;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f116b = -1;

        a() {
        }

        public a a(int i) {
            this.f116b = i;
            return this;
        }

        public b a() {
            return new b(this.f115a, this.f116b);
        }

        public a b(int i) {
            this.f115a = i;
            return this;
        }
    }

    b(int i, int i2) {
        this.f113a = i;
        this.f114b = i2;
    }

    public static a c() {
        return new a();
    }

    public int a() {
        return this.f114b;
    }

    public int b() {
        return this.f113a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public b m1clone() {
        return (b) super.clone();
    }

    public String toString() {
        return "[maxLineLength=" + this.f113a + ", maxHeaderCount=" + this.f114b + "]";
    }
}

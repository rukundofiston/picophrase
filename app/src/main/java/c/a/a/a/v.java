package c.a.a.a;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class v extends c0 implements Serializable {
    public static final v d = new v(0, 9);
    public static final v e = new v(1, 0);
    public static final v f = new v(1, 1);

    public v(int i, int i2) {
        super("HTTP", i, i2);
    }

    @Override // c.a.a.a.c0
    public c0 a(int i, int i2) {
        if (i == this.f58b && i2 == this.f59c) {
            return this;
        }
        if (i == 1) {
            if (i2 == 0) {
                return e;
            }
            if (i2 == 1) {
                return f;
            }
        }
        return (i == 0 && i2 == 9) ? d : new v(i, i2);
    }
}

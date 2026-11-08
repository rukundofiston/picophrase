package c.a.a.a.t0;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class a implements e {
    protected a() {
    }

    @Override // c.a.a.a.t0.e
    public long a(String str, long j) {
        Object objB = b(str);
        return objB == null ? j : ((Long) objB).longValue();
    }

    @Override // c.a.a.a.t0.e
    public e a(String str, int i) {
        a(str, Integer.valueOf(i));
        return this;
    }

    @Override // c.a.a.a.t0.e
    public e a(String str, boolean z) {
        a(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    @Override // c.a.a.a.t0.e
    public boolean a(String str) {
        return !b(str, false);
    }

    @Override // c.a.a.a.t0.e
    public int b(String str, int i) {
        Object objB = b(str);
        return objB == null ? i : ((Integer) objB).intValue();
    }

    @Override // c.a.a.a.t0.e
    public e b(String str, long j) {
        a(str, Long.valueOf(j));
        return this;
    }

    @Override // c.a.a.a.t0.e
    public boolean b(String str, boolean z) {
        Object objB = b(str);
        return objB == null ? z : ((Boolean) objB).booleanValue();
    }

    @Override // c.a.a.a.t0.e
    public boolean c(String str) {
        return b(str, false);
    }
}

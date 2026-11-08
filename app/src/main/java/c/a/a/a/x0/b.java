package c.a.a.a.x0;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static void a(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalStateException(str + " is null");
    }

    public static void a(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }
}

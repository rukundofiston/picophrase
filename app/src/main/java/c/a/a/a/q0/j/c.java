package c.a.a.a.q0.j;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class c extends d implements c.a.a.a.n0.n {
    private int[] i;
    private boolean j;

    public c(String str, String str2) {
        super(str, str2);
    }

    @Override // c.a.a.a.n0.n
    public void a(int[] iArr) {
        this.i = iArr;
    }

    @Override // c.a.a.a.q0.j.d, c.a.a.a.n0.c
    public boolean a(Date date) {
        return this.j || super.a(date);
    }

    @Override // c.a.a.a.n0.n
    public void b(boolean z) {
        this.j = z;
    }

    @Override // c.a.a.a.q0.j.d
    public Object clone() {
        c cVar = (c) super.clone();
        int[] iArr = this.i;
        if (iArr != null) {
            cVar.i = (int[]) iArr.clone();
        }
        return cVar;
    }

    @Override // c.a.a.a.n0.n
    public void f(String str) {
    }

    @Override // c.a.a.a.q0.j.d, c.a.a.a.n0.c
    public int[] f() {
        return this.i;
    }
}

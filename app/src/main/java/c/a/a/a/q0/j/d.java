package c.a.a.a.q0.j;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class d implements c.a.a.a.n0.o, c.a.a.a.n0.a, Cloneable, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f301a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Map<String, String> f302b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f303c;
    private String d;
    private Date e;
    private String f;
    private boolean g;
    private int h;

    public d(String str, String str2) {
        c.a.a.a.x0.a.a(str, "Name");
        this.f301a = str;
        this.f302b = new HashMap();
        this.f303c = str2;
    }

    @Override // c.a.a.a.n0.a
    public String a(String str) {
        return this.f302b.get(str);
    }

    @Override // c.a.a.a.n0.o
    public void a(int i) {
        this.h = i;
    }

    public void a(String str, String str2) {
        this.f302b.put(str, str2);
    }

    @Override // c.a.a.a.n0.o
    public void a(boolean z) {
        this.g = z;
    }

    @Override // c.a.a.a.n0.c
    public boolean a() {
        return this.g;
    }

    @Override // c.a.a.a.n0.c
    public boolean a(Date date) {
        c.a.a.a.x0.a.a(date, "Date");
        Date date2 = this.e;
        return date2 != null && date2.getTime() <= date.getTime();
    }

    @Override // c.a.a.a.n0.c
    public int b() {
        return this.h;
    }

    @Override // c.a.a.a.n0.o
    public void b(String str) {
        this.f = str;
    }

    @Override // c.a.a.a.n0.o
    public void b(Date date) {
        this.e = date;
    }

    @Override // c.a.a.a.n0.c
    public String c() {
        return this.d;
    }

    @Override // c.a.a.a.n0.a
    public boolean c(String str) {
        return this.f302b.containsKey(str);
    }

    public Object clone() {
        d dVar = (d) super.clone();
        dVar.f302b = new HashMap(this.f302b);
        return dVar;
    }

    @Override // c.a.a.a.n0.c
    public Date d() {
        return this.e;
    }

    @Override // c.a.a.a.n0.o
    public void d(String str) {
        this.d = str != null ? str.toLowerCase(Locale.ROOT) : null;
    }

    @Override // c.a.a.a.n0.c
    public String e() {
        return this.f;
    }

    @Override // c.a.a.a.n0.o
    public void e(String str) {
    }

    @Override // c.a.a.a.n0.c
    public int[] f() {
        return null;
    }

    @Override // c.a.a.a.n0.c
    public String getName() {
        return this.f301a;
    }

    @Override // c.a.a.a.n0.c
    public String getValue() {
        return this.f303c;
    }

    public String toString() {
        return "[version: " + Integer.toString(this.h) + "][name: " + this.f301a + "][value: " + this.f303c + "][domain: " + this.d + "][path: " + this.f + "][expiry: " + this.e + "]";
    }
}

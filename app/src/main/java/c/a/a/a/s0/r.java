package c.a.a.a.s0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class r implements Cloneable, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.e[] f396a = new c.a.a.a.e[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<c.a.a.a.e> f397b = new ArrayList(16);

    public void a() {
        this.f397b.clear();
    }

    public void a(c.a.a.a.e eVar) {
        if (eVar == null) {
            return;
        }
        this.f397b.add(eVar);
    }

    public void a(c.a.a.a.e[] eVarArr) {
        a();
        if (eVarArr == null) {
            return;
        }
        Collections.addAll(this.f397b, eVarArr);
    }

    public boolean a(String str) {
        for (int i = 0; i < this.f397b.size(); i++) {
            if (this.f397b.get(i).getName().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public c.a.a.a.e b(String str) {
        for (int i = 0; i < this.f397b.size(); i++) {
            c.a.a.a.e eVar = this.f397b.get(i);
            if (eVar.getName().equalsIgnoreCase(str)) {
                return eVar;
            }
        }
        return null;
    }

    public void b(c.a.a.a.e eVar) {
        if (eVar == null) {
            return;
        }
        this.f397b.remove(eVar);
    }

    public c.a.a.a.e[] b() {
        List<c.a.a.a.e> list = this.f397b;
        return (c.a.a.a.e[]) list.toArray(new c.a.a.a.e[list.size()]);
    }

    public c.a.a.a.h c() {
        return new l(this.f397b, null);
    }

    public void c(c.a.a.a.e eVar) {
        if (eVar == null) {
            return;
        }
        for (int i = 0; i < this.f397b.size(); i++) {
            if (this.f397b.get(i).getName().equalsIgnoreCase(eVar.getName())) {
                this.f397b.set(i, eVar);
                return;
            }
        }
        this.f397b.add(eVar);
    }

    public c.a.a.a.e[] c(String str) {
        ArrayList arrayList = null;
        for (int i = 0; i < this.f397b.size(); i++) {
            c.a.a.a.e eVar = this.f397b.get(i);
            if (eVar.getName().equalsIgnoreCase(str)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(eVar);
            }
        }
        return arrayList != null ? (c.a.a.a.e[]) arrayList.toArray(new c.a.a.a.e[arrayList.size()]) : this.f396a;
    }

    public Object clone() {
        return super.clone();
    }

    public c.a.a.a.h d(String str) {
        return new l(this.f397b, str);
    }

    public String toString() {
        return this.f397b.toString();
    }
}

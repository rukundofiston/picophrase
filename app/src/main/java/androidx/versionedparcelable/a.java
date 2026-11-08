package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final java.util.Map<String, Method> f24a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final java.util.Map<String, Method> f25b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final java.util.Map<String, Class> f26c;

    public a(java.util.Map<String, Method> aVar, java.util.Map<String, Method> aVar2, java.util.Map<String, Class> aVar3) {
        this.f24a = aVar;
        this.f25b = aVar2;
        this.f26c = aVar3;
    }

    private Class a(Class<? extends c> cls) throws ClassNotFoundException {
        Class cls2 = this.f26c.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.f26c.put(cls.getName(), cls3);
        return cls3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Method b(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        Method method = this.f25b.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class clsA = a((Class<? extends c>) cls);
        System.currentTimeMillis();
        Method declaredMethod = clsA.getDeclaredMethod("write", cls, a.class);
        this.f25b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    private Method b(String str) throws NoSuchMethodException, ClassNotFoundException {
        Method method = this.f24a.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class);
        this.f24a.put(str, declaredMethod);
        return declaredMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(c cVar) {
        try {
            a(a((Class<? extends c>) cVar.getClass()).getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(cVar.getClass().getSimpleName() + " does not have a Parcelizer", e);
        }
    }

    public int a(int i, int i2) {
        return !a(i2) ? i : g();
    }

    public <T extends Parcelable> T a(T t, int i) {
        return !a(i) ? t : (T) h();
    }

    public <T extends c> T a(T t, int i) {
        return !a(i) ? t : (T) j();
    }

    protected <T extends c> T a(String str, a aVar) {
        try {
            return (T) b(str).invoke(null, aVar);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    public CharSequence a(CharSequence charSequence, int i) {
        return !a(i) ? charSequence : f();
    }

    public String a(String str, int i) {
        return !a(i) ? str : i();
    }

    protected abstract void a();

    protected abstract void a(Parcelable parcelable);

    protected void a(c cVar) {
        if (cVar == null) {
            a((String) null);
            return;
        }
        b(cVar);
        a aVarB = b();
        a(cVar, aVarB);
        aVarB.a();
    }

    protected <T extends c> void a(T t, a aVar) {
        try {
            b(t.getClass()).invoke(null, t, aVar);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (!(e4.getCause() instanceof RuntimeException)) {
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
            }
            throw ((RuntimeException) e4.getCause());
        }
    }

    protected abstract void a(CharSequence charSequence);

    protected abstract void a(String str);

    protected abstract void a(boolean z);

    public void a(boolean z, boolean z2) {
    }

    protected abstract void a(byte[] bArr);

    protected abstract boolean a(int i);

    public boolean a(boolean z, int i) {
        return !a(i) ? z : d();
    }

    public byte[] a(byte[] bArr, int i) {
        return !a(i) ? bArr : e();
    }

    protected abstract a b();

    protected abstract void b(int i);

    public void b(int i, int i2) {
        b(i2);
        c(i);
    }

    public void b(Parcelable parcelable, int i) {
        b(i);
        a(parcelable);
    }

    public void b(c cVar, int i) {
        b(i);
        a(cVar);
    }

    public void b(CharSequence charSequence, int i) {
        b(i);
        a(charSequence);
    }

    public void b(String str, int i) {
        b(i);
        a(str);
    }

    public void b(boolean z, int i) {
        b(i);
        a(z);
    }

    public void b(byte[] bArr, int i) {
        b(i);
        a(bArr);
    }

    protected abstract void c(int i);

    public boolean c() {
        return false;
    }

    protected abstract boolean d();

    protected abstract byte[] e();

    protected abstract CharSequence f();

    protected abstract int g();

    protected abstract <T extends Parcelable> T h();

    protected abstract String i();

    protected <T extends c> T j() {
        String strI = i();
        if (strI == null) {
            return null;
        }
        return (T) a(strI, b());
    }
}

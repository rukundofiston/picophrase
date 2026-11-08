package c.a.a.a.q0.h;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class i implements InvocationHandler {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Constructor<?> f234b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.a.a.a.s f235a;

    static {
        try {
            f234b = Proxy.getProxyClass(i.class.getClassLoader(), c.a.a.a.j0.t.c.class).getConstructor(InvocationHandler.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    i(c.a.a.a.s sVar) {
        this.f235a = sVar;
    }

    public static c.a.a.a.j0.t.c a(c.a.a.a.s sVar) {
        try {
            return (c.a.a.a.j0.t.c) f234b.newInstance(new i(sVar));
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        } catch (InvocationTargetException e3) {
            throw new IllegalStateException(e3);
        }
    }

    public void a() throws IOException {
        c.a.a.a.x0.g.a(this.f235a.c());
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        if (method.getName().equals("close")) {
            a();
            return null;
        }
        try {
            return method.invoke(this.f235a, objArr);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                throw cause;
            }
            throw e;
        }
    }
}

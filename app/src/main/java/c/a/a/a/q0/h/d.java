package c.a.a.a.q0.h;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class d implements c.a.a.a.j0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f226a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<c.a.a.a.n, byte[]> f227b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.a.a.a.m0.r f228c;

    public d() {
        this(null);
    }

    public d(c.a.a.a.m0.r rVar) {
        this.f226a = new c.a.a.a.p0.b(d.class);
        this.f227b = new ConcurrentHashMap();
        this.f228c = rVar == null ? c.a.a.a.q0.i.j.f268a : rVar;
    }

    @Override // c.a.a.a.j0.a
    public c.a.a.a.i0.c a(c.a.a.a.n nVar) {
        c.a.a.a.x0.a.a(nVar, "HTTP host");
        byte[] bArr = this.f227b.get(c(nVar));
        if (bArr != null) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
                c.a.a.a.i0.c cVar = (c.a.a.a.i0.c) objectInputStream.readObject();
                objectInputStream.close();
                return cVar;
            } catch (IOException e) {
                if (this.f226a.d()) {
                    this.f226a.b("Unexpected I/O error while de-serializing auth scheme", e);
                }
            } catch (ClassNotFoundException e2) {
                if (this.f226a.d()) {
                    this.f226a.b("Unexpected error while de-serializing auth scheme", e2);
                }
                return null;
            }
        }
        return null;
    }

    @Override // c.a.a.a.j0.a
    public void a(c.a.a.a.n nVar, c.a.a.a.i0.c cVar) {
        c.a.a.a.x0.a.a(nVar, "HTTP host");
        if (cVar == null) {
            return;
        }
        if (!(cVar instanceof Serializable)) {
            if (this.f226a.a()) {
                this.f226a.a("Auth scheme " + cVar.getClass() + " is not serializable");
                return;
            }
            return;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(cVar);
            objectOutputStream.close();
            this.f227b.put(c(nVar), byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            if (this.f226a.d()) {
                this.f226a.b("Unexpected I/O error while serializing auth scheme", e);
            }
        }
    }

    @Override // c.a.a.a.j0.a
    public void b(c.a.a.a.n nVar) {
        c.a.a.a.x0.a.a(nVar, "HTTP host");
        this.f227b.remove(c(nVar));
    }

    protected c.a.a.a.n c(c.a.a.a.n nVar) {
        if (nVar.c() <= 0) {
            try {
                return new c.a.a.a.n(nVar.b(), this.f228c.a(nVar), nVar.d());
            } catch (c.a.a.a.m0.s unused) {
            }
        }
        return nVar;
    }

    public String toString() {
        return this.f227b.toString();
    }
}

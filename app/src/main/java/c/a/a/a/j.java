package c.a.a.a;

import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public interface j extends Closeable {
    void a(int i);

    void c();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    boolean isOpen();

    boolean k();
}

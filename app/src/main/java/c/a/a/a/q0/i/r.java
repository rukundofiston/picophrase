package c.a.a.a.q0.i;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.cocos2dx.lib.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.a.a.a.p0.b f278a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f279b;

    public r(c.a.a.a.p0.b bVar) {
        this(bVar, BuildConfig.FLAVOR);
    }

    public r(c.a.a.a.p0.b bVar, String str) {
        this.f278a = bVar;
        this.f279b = str;
    }

    private void a(String str, InputStream inputStream) throws IOException {
        String str2;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = inputStream.read();
            if (i == -1) {
                break;
            }
            if (i == 13) {
                str2 = "[\\r]";
            } else if (i == 10) {
                sb.append("[\\n]\"");
                sb.insert(0, "\"");
                sb.insert(0, str);
                this.f278a.a(this.f279b + " " + sb.toString());
                sb.setLength(0);
            } else if (i < 32 || i > 127) {
                sb.append("[0x");
                sb.append(Integer.toHexString(i));
                str2 = "]";
            } else {
                sb.append((char) i);
            }
            sb.append(str2);
        }
        if (sb.length() > 0) {
            sb.append('\"');
            sb.insert(0, '\"');
            sb.insert(0, str);
            this.f278a.a(this.f279b + " " + sb.toString());
        }
    }

    public void a(int i) throws IOException {
        a(new byte[]{(byte) i});
    }

    public void a(byte[] bArr) throws IOException {
        c.a.a.a.x0.a.a(bArr, "Input");
        a("<< ", new ByteArrayInputStream(bArr));
    }

    public void a(byte[] bArr, int i, int i2) throws IOException {
        c.a.a.a.x0.a.a(bArr, "Input");
        a("<< ", new ByteArrayInputStream(bArr, i, i2));
    }

    public boolean a() {
        return this.f278a.a();
    }

    public void b(int i) throws IOException {
        b(new byte[]{(byte) i});
    }

    public void b(byte[] bArr) throws IOException {
        c.a.a.a.x0.a.a(bArr, "Output");
        a(">> ", new ByteArrayInputStream(bArr));
    }

    public void b(byte[] bArr, int i, int i2) throws IOException {
        c.a.a.a.x0.a.a(bArr, "Output");
        a(">> ", new ByteArrayInputStream(bArr, i, i2));
    }
}

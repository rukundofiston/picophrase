package c.a.a.a.i0;

import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f71a = b.UNCHALLENGED;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f72b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m f73c;
    private Queue<a> d;

    public Queue<a> a() {
        return this.d;
    }

    public void a(b bVar) {
        if (bVar == null) {
            bVar = b.UNCHALLENGED;
        }
        this.f71a = bVar;
    }

    @Deprecated
    public void a(c cVar) {
        if (cVar == null) {
            e();
        } else {
            this.f72b = cVar;
        }
    }

    public void a(c cVar, m mVar) {
        c.a.a.a.x0.a.a(cVar, "Auth scheme");
        c.a.a.a.x0.a.a(mVar, "Credentials");
        this.f72b = cVar;
        this.f73c = mVar;
        this.d = null;
    }

    @Deprecated
    public void a(m mVar) {
        this.f73c = mVar;
    }

    public void a(Queue<a> queue) {
        c.a.a.a.x0.a.a(queue, "Queue of auth options");
        this.d = queue;
        this.f72b = null;
        this.f73c = null;
    }

    public c b() {
        return this.f72b;
    }

    public m c() {
        return this.f73c;
    }

    public b d() {
        return this.f71a;
    }

    public void e() {
        this.f71a = b.UNCHALLENGED;
        this.d = null;
        this.f72b = null;
        this.f73c = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("state:");
        sb.append(this.f71a);
        sb.append(";");
        if (this.f72b != null) {
            sb.append("auth scheme:");
            sb.append(this.f72b.b());
            sb.append(";");
        }
        if (this.f73c != null) {
            sb.append("credentials present");
        }
        return sb.toString();
    }
}

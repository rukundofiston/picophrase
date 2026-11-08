package c.a.a.a.j0.w;

import c.a.a.a.n;
import c.a.a.a.x0.i;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Stack;
import org.cocos2dx.lib.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static n a(URI uri) {
        int iIndexOf;
        if (uri == null || !uri.isAbsolute()) {
            return null;
        }
        int port = uri.getPort();
        String host = uri.getHost();
        if (host == null && (host = uri.getAuthority()) != null) {
            int iIndexOf2 = host.indexOf(64);
            if (iIndexOf2 >= 0) {
                int i = iIndexOf2 + 1;
                host = host.length() > i ? host.substring(i) : null;
            }
            if (host != null && (iIndexOf = host.indexOf(58)) >= 0) {
                int i2 = iIndexOf + 1;
                int i3 = 0;
                for (int i4 = i2; i4 < host.length() && Character.isDigit(host.charAt(i4)); i4++) {
                    i3++;
                }
                if (i3 > 0) {
                    try {
                        port = Integer.parseInt(host.substring(i2, i3 + i2));
                    } catch (NumberFormatException unused) {
                    }
                }
                host = host.substring(0, iIndexOf);
            }
        }
        String scheme = uri.getScheme();
        if (i.b(host)) {
            return null;
        }
        try {
            return new n(host, port, scheme);
        } catch (IllegalArgumentException unused2) {
            return null;
        }
    }

    public static URI a(URI uri, n nVar, boolean z) {
        int iC;
        c.a.a.a.x0.a.a(uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        c cVar = new c(uri);
        if (nVar != null) {
            cVar.d(nVar.d());
            cVar.b(nVar.b());
            iC = nVar.c();
        } else {
            cVar.d(null);
            cVar.b((String) null);
            iC = -1;
        }
        cVar.a(iC);
        if (z) {
            cVar.a((String) null);
        }
        if (i.c(cVar.d())) {
            cVar.c("/");
        }
        return cVar.a();
    }

    public static URI a(URI uri, URI uri2) {
        c.a.a.a.x0.a.a(uri, "Base URI");
        c.a.a.a.x0.a.a(uri2, "Reference URI");
        String string = uri2.toString();
        if (string.startsWith("?")) {
            return b(uri, uri2);
        }
        boolean zIsEmpty = string.isEmpty();
        if (zIsEmpty) {
            uri2 = URI.create("#");
        }
        URI uriResolve = uri.resolve(uri2);
        if (zIsEmpty) {
            String string2 = uriResolve.toString();
            uriResolve = URI.create(string2.substring(0, string2.indexOf(35)));
        }
        return b(uriResolve);
    }

    private static URI b(URI uri) {
        if (uri.isOpaque() || uri.getAuthority() == null) {
            return uri;
        }
        c.a.a.a.x0.a.a(uri.isAbsolute(), "Base URI must be absolute");
        String path = uri.getPath() == null ? BuildConfig.FLAVOR : uri.getPath();
        String[] strArrSplit = path.split("/");
        Stack<String> stack = new Stack();
        for (String str : strArrSplit) {
            if (!str.isEmpty() && !".".equals(str)) {
                if (!"..".equals(str)) {
                    stack.push(str);
                } else if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : stack) {
            sb.append('/');
            sb.append(str2);
        }
        if (path.lastIndexOf(47) == path.length() - 1) {
            sb.append('/');
        }
        try {
            URI uri2 = new URI(uri.getScheme().toLowerCase(Locale.ROOT), uri.getAuthority().toLowerCase(Locale.ROOT), sb.toString(), null, null);
            if (uri.getQuery() == null && uri.getFragment() == null) {
                return uri2;
            }
            StringBuilder sb2 = new StringBuilder(uri2.toASCIIString());
            if (uri.getQuery() != null) {
                sb2.append('?');
                sb2.append(uri.getRawQuery());
            }
            if (uri.getFragment() != null) {
                sb2.append('#');
                sb2.append(uri.getRawFragment());
            }
            return URI.create(sb2.toString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static URI b(URI uri, URI uri2) {
        String string = uri.toString();
        if (string.indexOf(63) > -1) {
            string = string.substring(0, string.indexOf(63));
        }
        return URI.create(string + uri2.toString());
    }

    public static URI c(URI uri) {
        c.a.a.a.x0.a.a(uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        c cVar = new c(uri);
        if (cVar.f() != null) {
            cVar.e(null);
        }
        if (i.c(cVar.d())) {
            cVar.c("/");
        }
        if (cVar.c() != null) {
            cVar.b(cVar.c().toLowerCase(Locale.ROOT));
        }
        cVar.a((String) null);
        return cVar.a();
    }
}

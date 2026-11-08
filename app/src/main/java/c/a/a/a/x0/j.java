package c.a.a.a.x0;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f426a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f427b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f428c;
    private final String d;
    private final String e;

    protected j(String str, String str2, String str3, String str4, String str5) {
        a.a(str, "Package identifier");
        this.f426a = str;
        this.f427b = str2 == null ? "UNAVAILABLE" : str2;
        this.f428c = str3 == null ? "UNAVAILABLE" : str3;
        this.d = str4 == null ? "UNAVAILABLE" : str4;
        this.e = str5 == null ? "UNAVAILABLE" : str5;
    }

    public static j a(String str, ClassLoader classLoader) {
        Properties properties;
        a.a(str, "Package identifier");
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        try {
            InputStream resourceAsStream = classLoader.getResourceAsStream(str.replace('.', '/') + "/version.properties");
            if (resourceAsStream != null) {
                try {
                    properties = new Properties();
                    properties.load(resourceAsStream);
                    try {
                        resourceAsStream.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    resourceAsStream.close();
                    throw th;
                }
            } else {
                properties = null;
            }
        } catch (IOException unused2) {
            properties = null;
        }
        if (properties != null) {
            return a(str, properties, classLoader);
        }
        return null;
    }

    protected static j a(String str, Map<?, ?> map, ClassLoader classLoader) {
        String str2;
        String str3;
        String str4;
        a.a(str, "Package identifier");
        if (map != null) {
            String str5 = (String) map.get("info.module");
            if (str5 != null && str5.length() < 1) {
                str5 = null;
            }
            String str6 = (String) map.get("info.release");
            if (str6 != null && (str6.length() < 1 || str6.equals("${pom.version}"))) {
                str6 = null;
            }
            String str7 = (String) map.get("info.timestamp");
            str4 = (str7 == null || (str7.length() >= 1 && !str7.equals("${mvn.timestamp}"))) ? str7 : null;
            str2 = str5;
            str3 = str6;
        } else {
            str2 = null;
            str3 = null;
            str4 = null;
        }
        return new j(str, str2, str3, str4, classLoader != null ? classLoader.toString() : null);
    }

    public static String a(String str, String str2, Class<?> cls) {
        j jVarA = a(str2, cls.getClassLoader());
        return String.format("%s/%s (Java/%s)", str, jVarA != null ? jVarA.a() : "UNAVAILABLE", System.getProperty("java.version"));
    }

    public final String a() {
        return this.f428c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.f426a.length() + 20 + this.f427b.length() + this.f428c.length() + this.d.length() + this.e.length());
        sb.append("VersionInfo(");
        sb.append(this.f426a);
        sb.append(':');
        sb.append(this.f427b);
        if (!"UNAVAILABLE".equals(this.f428c)) {
            sb.append(':');
            sb.append(this.f428c);
        }
        if (!"UNAVAILABLE".equals(this.d)) {
            sb.append(':');
            sb.append(this.d);
        }
        sb.append(')');
        if (!"UNAVAILABLE".equals(this.e)) {
            sb.append('@');
            sb.append(this.e);
        }
        return sb.toString();
    }
}

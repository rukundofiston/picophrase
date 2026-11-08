package c.a.a.a.m0.w;

import javax.security.auth.x500.X500Principal;
import org.cocos2dx.lib.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f150a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f151b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f152c;
    private int d;
    private int e;
    private int f;
    private char[] g;

    public e(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.f150a = name;
        this.f151b = name.length();
    }

    private int a(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 >= this.f151b) {
            throw new IllegalStateException("Malformed DN: " + this.f150a);
        }
        char c2 = this.g[i];
        if (c2 >= '0' && c2 <= '9') {
            i2 = c2 - '0';
        } else if (c2 >= 'a' && c2 <= 'f') {
            i2 = c2 - 'W';
        } else {
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f150a);
            }
            i2 = c2 - '7';
        }
        char c3 = this.g[i4];
        if (c3 >= '0' && c3 <= '9') {
            i3 = c3 - '0';
        } else if (c3 >= 'a' && c3 <= 'f') {
            i3 = c3 - 'W';
        } else {
            if (c3 < 'A' || c3 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f150a);
            }
            i3 = c3 - '7';
        }
        return (i2 << 4) + i3;
    }

    private String a() {
        int i = this.f152c;
        this.d = i;
        this.e = i;
        while (true) {
            int i2 = this.f152c;
            if (i2 >= this.f151b) {
                char[] cArr = this.g;
                int i3 = this.d;
                return new String(cArr, i3, this.e - i3);
            }
            char[] cArr2 = this.g;
            char c2 = cArr2[i2];
            if (c2 != ' ') {
                if (c2 != ';') {
                    if (c2 == '\\') {
                        int i4 = this.e;
                        this.e = i4 + 1;
                        cArr2[i4] = b();
                        i2 = this.f152c;
                    } else if (c2 != '+' && c2 != ',') {
                        int i5 = this.e;
                        this.e = i5 + 1;
                        cArr2[i5] = cArr2[i2];
                    }
                    this.f152c = i2 + 1;
                }
                char[] cArr3 = this.g;
                int i6 = this.d;
                return new String(cArr3, i6, this.e - i6);
            }
            int i7 = this.e;
            this.f = i7;
            this.f152c = i2 + 1;
            this.e = i7 + 1;
            cArr2[i7] = ' ';
            while (true) {
                int i8 = this.f152c;
                if (i8 >= this.f151b) {
                    break;
                }
                char[] cArr4 = this.g;
                if (cArr4[i8] != ' ') {
                    break;
                }
                int i9 = this.e;
                this.e = i9 + 1;
                cArr4[i9] = ' ';
                this.f152c = i8 + 1;
            }
            int i10 = this.f152c;
            if (i10 != this.f151b) {
                char[] cArr5 = this.g;
                if (cArr5[i10] == ',' || cArr5[i10] == '+' || cArr5[i10] == ';') {
                }
            }
            char[] cArr6 = this.g;
            int i11 = this.d;
            return new String(cArr6, i11, this.f - i11);
        }
    }

    private char b() {
        int i = this.f152c + 1;
        this.f152c = i;
        if (i == this.f151b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f150a);
        }
        char c2 = this.g[i];
        if (c2 != ' ' && c2 != '%' && c2 != '\\' && c2 != '_' && c2 != '\"' && c2 != '#') {
            switch (c2) {
                case '*':
                case '+':
                case ',':
                    break;
                default:
                    switch (c2) {
                        case ';':
                        case '<':
                        case '=':
                        case '>':
                            break;
                        default:
                            return c();
                    }
                    break;
            }
        }
        return this.g[this.f152c];
    }

    private char c() {
        int i;
        int i2;
        int iA = a(this.f152c);
        this.f152c++;
        if (iA < 128) {
            return (char) iA;
        }
        if (iA < 192 || iA > 247) {
            return '?';
        }
        if (iA <= 223) {
            i2 = iA & 31;
            i = 1;
        } else if (iA <= 239) {
            i = 2;
            i2 = iA & 15;
        } else {
            i = 3;
            i2 = iA & 7;
        }
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = this.f152c + 1;
            this.f152c = i4;
            if (i4 == this.f151b || this.g[i4] != '\\') {
                return '?';
            }
            int i5 = i4 + 1;
            this.f152c = i5;
            int iA2 = a(i5);
            this.f152c++;
            if ((iA2 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (iA2 & 63);
        }
        return (char) i2;
    }

    private String d() {
        int i = this.f152c;
        if (i + 4 >= this.f151b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f150a);
        }
        this.d = i;
        while (true) {
            this.f152c = i + 1;
            int i2 = this.f152c;
            if (i2 != this.f151b) {
                char[] cArr = this.g;
                if (cArr[i2] != '+' && cArr[i2] != ',' && cArr[i2] != ';') {
                    if (cArr[i2] == ' ') {
                        this.e = i2;
                        do {
                            this.f152c = i2 + 1;
                            i2 = this.f152c;
                            if (i2 >= this.f151b) {
                                break;
                            }
                        } while (this.g[i2] == ' ');
                    } else {
                        if (cArr[i2] >= 'A' && cArr[i2] <= 'F') {
                            cArr[i2] = (char) (cArr[i2] + ' ');
                        }
                        i = this.f152c;
                    }
                }
            }
            this.e = this.f152c;
            break;
        }
        int i3 = this.e;
        int i4 = this.d;
        int i5 = i3 - i4;
        if (i5 < 5 || (i5 & 1) == 0) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f150a);
        }
        int i6 = i5 / 2;
        byte[] bArr = new byte[i6];
        int i7 = i4 + 1;
        for (int i8 = 0; i8 < i6; i8++) {
            bArr[i8] = (byte) a(i7);
            i7 += 2;
        }
        return new String(this.g, this.d, i5);
    }

    private String e() {
        char[] cArr;
        while (true) {
            int i = this.f152c;
            if (i >= this.f151b || this.g[i] != ' ') {
                break;
            }
            this.f152c = i + 1;
        }
        int i2 = this.f152c;
        if (i2 == this.f151b) {
            return null;
        }
        this.d = i2;
        do {
            this.f152c = i2 + 1;
            i2 = this.f152c;
            if (i2 >= this.f151b) {
                break;
            }
            cArr = this.g;
            if (cArr[i2] == '=') {
                break;
            }
        } while (cArr[i2] != ' ');
        int i3 = this.f152c;
        if (i3 >= this.f151b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f150a);
        }
        this.e = i3;
        if (this.g[i3] == ' ') {
            while (true) {
                int i4 = this.f152c;
                if (i4 >= this.f151b) {
                    break;
                }
                char[] cArr2 = this.g;
                if (cArr2[i4] == '=' || cArr2[i4] != ' ') {
                    break;
                }
                this.f152c = i4 + 1;
            }
            char[] cArr3 = this.g;
            int i5 = this.f152c;
            if (cArr3[i5] != '=' || i5 == this.f151b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f150a);
            }
        }
        int i6 = this.f152c;
        do {
            this.f152c = i6 + 1;
            i6 = this.f152c;
            if (i6 >= this.f151b) {
                break;
            }
        } while (this.g[i6] == ' ');
        int i7 = this.e;
        int i8 = this.d;
        if (i7 - i8 > 4) {
            char[] cArr4 = this.g;
            if (cArr4[i8 + 3] == '.' && (cArr4[i8] == 'O' || cArr4[i8] == 'o')) {
                char[] cArr5 = this.g;
                int i9 = this.d;
                if (cArr5[i9 + 1] == 'I' || cArr5[i9 + 1] == 'i') {
                    char[] cArr6 = this.g;
                    int i10 = this.d;
                    if (cArr6[i10 + 2] == 'D' || cArr6[i10 + 2] == 'd') {
                        this.d += 4;
                    }
                }
            }
        }
        char[] cArr7 = this.g;
        int i11 = this.d;
        return new String(cArr7, i11, this.e - i11);
    }

    private String f() {
        int i = this.f152c + 1;
        this.f152c = i;
        this.d = i;
        while (true) {
            this.e = i;
            int i2 = this.f152c;
            if (i2 == this.f151b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f150a);
            }
            char[] cArr = this.g;
            if (cArr[i2] == '\"') {
                do {
                    this.f152c = i2 + 1;
                    i2 = this.f152c;
                    if (i2 >= this.f151b) {
                        break;
                    }
                } while (this.g[i2] == ' ');
                char[] cArr2 = this.g;
                int i3 = this.d;
                return new String(cArr2, i3, this.e - i3);
            }
            if (cArr[i2] == '\\') {
                cArr[this.e] = b();
            } else {
                cArr[this.e] = cArr[i2];
            }
            this.f152c++;
            i = this.e + 1;
        }
    }

    public String a(String str) {
        String strF;
        this.f152c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = this.f150a.toCharArray();
        String strE = e();
        if (strE == null) {
            return null;
        }
        do {
            int i = this.f152c;
            if (i == this.f151b) {
                return null;
            }
            char c2 = this.g[i];
            if (c2 == '\"') {
                strF = f();
            } else if (c2 != '#') {
                strF = (c2 == '+' || c2 == ',' || c2 == ';') ? BuildConfig.FLAVOR : a();
            } else {
                strF = d();
            }
            if (str.equalsIgnoreCase(strE)) {
                return strF;
            }
            int i2 = this.f152c;
            if (i2 >= this.f151b) {
                return null;
            }
            char[] cArr = this.g;
            if (cArr[i2] != ',' && cArr[i2] != ';' && cArr[i2] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.f150a);
            }
            this.f152c++;
            strE = e();
        } while (strE != null);
        throw new IllegalStateException("Malformed DN: " + this.f150a);
    }
}

package c.a.a.a;

import java.nio.charset.CharacterCodingException;

/* JADX INFO: loaded from: classes.dex */
public class x extends CharacterCodingException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f421a;

    public x(String str) {
        this.f421a = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.f421a;
    }
}

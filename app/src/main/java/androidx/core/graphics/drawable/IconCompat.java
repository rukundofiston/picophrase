package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.Cocos2dxEditBox;

/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    static final PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Object f21b;
    public String j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f20a = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f22c = null;
    public Parcelable d = null;
    public int e = 0;
    public int f = 0;
    public ColorStateList g = null;
    PorterDuff.Mode h = k;
    public String i = null;

    static class a {
        static int a(Object obj) {
            if (Build.VERSION.SDK_INT >= 28) {
                return c.a(obj);
            }
            try {
                return ((Integer) obj.getClass().getMethod("getResId", new Class[0]).invoke(obj, new Object[0])).intValue();
            } catch (IllegalAccessException e) {
                Log.e("IconCompat", "Unable to get icon resource", e);
                return 0;
            } catch (NoSuchMethodException e2) {
                Log.e("IconCompat", "Unable to get icon resource", e2);
                return 0;
            } catch (InvocationTargetException e3) {
                Log.e("IconCompat", "Unable to get icon resource", e3);
                return 0;
            }
        }

        static Drawable a(Icon icon, Context context) {
            return icon.loadDrawable(context);
        }

        /* JADX WARN: Code duplicated, block: B:32:0x00aa  */
        /* JADX WARN: Code duplicated, block: B:35:0x00b3  */
        static Icon a(IconCompat iconCompat, Context context) {
            Bitmap bitmapA;
            Icon iconCreateWithResource;
            Bitmap bitmapDecodeStream;
            ColorStateList colorStateList;
            PorterDuff.Mode mode;
            switch (iconCompat.f20a) {
                case -1:
                    return (Icon) iconCompat.f21b;
                case Cocos2dxEditBox.kEndActionUnknown /* 0 */:
                default:
                    throw new IllegalArgumentException("Unknown type");
                case 1:
                    bitmapA = (Bitmap) iconCompat.f21b;
                    iconCreateWithResource = Icon.createWithBitmap(bitmapA);
                    colorStateList = iconCompat.g;
                    if (colorStateList != null) {
                        iconCreateWithResource.setTintList(colorStateList);
                    }
                    mode = iconCompat.h;
                    if (mode != IconCompat.k) {
                        iconCreateWithResource.setTintMode(mode);
                    }
                    return iconCreateWithResource;
                case BuildConfig.VERSION_CODE /* 2 */:
                    iconCreateWithResource = Icon.createWithResource(iconCompat.b(), iconCompat.e);
                    colorStateList = iconCompat.g;
                    if (colorStateList != null) {
                        iconCreateWithResource.setTintList(colorStateList);
                    }
                    mode = iconCompat.h;
                    if (mode != IconCompat.k) {
                        iconCreateWithResource.setTintMode(mode);
                    }
                    return iconCreateWithResource;
                case Cocos2dxEditBox.kEndActionReturn /* 3 */:
                    iconCreateWithResource = Icon.createWithData((byte[]) iconCompat.f21b, iconCompat.e, iconCompat.f);
                    colorStateList = iconCompat.g;
                    if (colorStateList != null) {
                        iconCreateWithResource.setTintList(colorStateList);
                    }
                    mode = iconCompat.h;
                    if (mode != IconCompat.k) {
                        iconCreateWithResource.setTintMode(mode);
                    }
                    return iconCreateWithResource;
                case 4:
                    iconCreateWithResource = Icon.createWithContentUri((String) iconCompat.f21b);
                    colorStateList = iconCompat.g;
                    if (colorStateList != null) {
                        iconCreateWithResource.setTintList(colorStateList);
                    }
                    mode = iconCompat.h;
                    if (mode != IconCompat.k) {
                        iconCreateWithResource.setTintMode(mode);
                    }
                    return iconCreateWithResource;
                case 5:
                    if (Build.VERSION.SDK_INT >= 26) {
                        bitmapDecodeStream = (Bitmap) iconCompat.f21b;
                        iconCreateWithResource = b.a(bitmapDecodeStream);
                        colorStateList = iconCompat.g;
                        if (colorStateList != null) {
                            iconCreateWithResource.setTintList(colorStateList);
                        }
                        mode = iconCompat.h;
                        if (mode != IconCompat.k) {
                            iconCreateWithResource.setTintMode(mode);
                        }
                        return iconCreateWithResource;
                    }
                    bitmapDecodeStream = (Bitmap) iconCompat.f21b;
                    bitmapA = IconCompat.a(bitmapDecodeStream, false);
                    iconCreateWithResource = Icon.createWithBitmap(bitmapA);
                    colorStateList = iconCompat.g;
                    if (colorStateList != null) {
                        iconCreateWithResource.setTintList(colorStateList);
                    }
                    mode = iconCompat.h;
                    if (mode != IconCompat.k) {
                        iconCreateWithResource.setTintMode(mode);
                    }
                    return iconCreateWithResource;
                case 6:
                    if (Build.VERSION.SDK_INT >= 30) {
                        iconCreateWithResource = Api30Impl.a(iconCompat.c());
                    } else {
                        if (context == null) {
                            throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: " + iconCompat.c());
                        }
                        InputStream inputStreamA = iconCompat.a(context);
                        if (inputStreamA == null) {
                            throw new IllegalStateException("Cannot load adaptive icon from uri: " + iconCompat.c());
                        }
                        int i = Build.VERSION.SDK_INT;
                        bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamA);
                        if (i >= 26) {
                            iconCreateWithResource = b.a(bitmapDecodeStream);
                        } else {
                            bitmapA = IconCompat.a(bitmapDecodeStream, false);
                            iconCreateWithResource = Icon.createWithBitmap(bitmapA);
                        }
                    }
                    colorStateList = iconCompat.g;
                    if (colorStateList != null) {
                        iconCreateWithResource.setTintList(colorStateList);
                    }
                    mode = iconCompat.h;
                    if (mode != IconCompat.k) {
                        iconCreateWithResource.setTintMode(mode);
                    }
                    return iconCreateWithResource;
            }
        }

        static String b(Object obj) {
            if (Build.VERSION.SDK_INT >= 28) {
                return c.b(obj);
            }
            try {
                return (String) obj.getClass().getMethod("getResPackage", new Class[0]).invoke(obj, new Object[0]);
            } catch (IllegalAccessException e) {
                Log.e("IconCompat", "Unable to get icon package", e);
                return null;
            } catch (NoSuchMethodException e2) {
                Log.e("IconCompat", "Unable to get icon package", e2);
                return null;
            } catch (InvocationTargetException e3) {
                Log.e("IconCompat", "Unable to get icon package", e3);
                return null;
            }
        }

        static Uri c(Object obj) {
            if (Build.VERSION.SDK_INT >= 28) {
                return c.d(obj);
            }
            try {
                return (Uri) obj.getClass().getMethod("getUri", new Class[0]).invoke(obj, new Object[0]);
            } catch (IllegalAccessException e) {
                Log.e("IconCompat", "Unable to get icon uri", e);
                return null;
            } catch (NoSuchMethodException e2) {
                Log.e("IconCompat", "Unable to get icon uri", e2);
                return null;
            } catch (InvocationTargetException e3) {
                Log.e("IconCompat", "Unable to get icon uri", e3);
                return null;
            }
        }
    }

    static class b {
        static Drawable a(Drawable drawable, Drawable drawable2) {
            return new AdaptiveIconDrawable(drawable, drawable2);
        }

        static Icon a(Bitmap bitmap) {
            return Icon.createWithAdaptiveBitmap(bitmap);
        }
    }

    static class c {
        static int a(Object obj) {
            return ((Icon) obj).getResId();
        }

        static String b(Object obj) {
            return ((Icon) obj).getResPackage();
        }

        static int c(Object obj) {
            return ((Icon) obj).getType();
        }

        static Uri d(Object obj) {
            return ((Icon) obj).getUri();
        }
    }

    static class Api30Impl {
        static Icon a(Uri uri) {
            return Icon.createWithAdaptiveBitmapContentUri(uri);
        }
    }

    static Bitmap a(Bitmap bitmap, boolean z) {
        int iMin = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * 0.6666667f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(3);
        float f = iMin;
        float f2 = 0.5f * f;
        float f3 = 0.9166667f * f2;
        if (z) {
            float f4 = 0.010416667f * f;
            paint.setColor(0);
            paint.setShadowLayer(f4, 0.0f, f * 0.020833334f, 1023410176);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.setShadowLayer(f4, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - iMin)) / 2.0f, (-(bitmap.getHeight() - iMin)) / 2.0f);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f2, f2, f3, paint);
        canvas.setBitmap(null);
        return bitmapCreateBitmap;
    }

    private static String a(int i) {
        switch (i) {
            case 1:
                return "BITMAP";
            case BuildConfig.VERSION_CODE /* 2 */:
                return "RESOURCE";
            case Cocos2dxEditBox.kEndActionReturn /* 3 */:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            case 6:
                return "URI_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    public int a() {
        if (this.f20a == -1 && Build.VERSION.SDK_INT >= 23) {
            return a.a(this.f21b);
        }
        if (this.f20a == 2) {
            return this.e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public InputStream a(Context context) {
        StringBuilder sb;
        String str;
        Exception exc;
        Uri uriC = c();
        String scheme = uriC.getScheme();
        if ("content".equals(scheme) || "file".equals(scheme)) {
            try {
                return context.getContentResolver().openInputStream(uriC);
            } catch (Exception e) {
                exc = e;
                sb = new StringBuilder();
                str = "Unable to load image from URI: ";
            }
        } else {
            try {
                return new FileInputStream(new File((String) this.f21b));
            } catch (FileNotFoundException e2) {
                exc = e2;
                sb = new StringBuilder();
                str = "Unable to load image from path: ";
            }
        }
        sb.append(str);
        sb.append(uriC);
        Log.w("IconCompat", sb.toString(), exc);
        return null;
    }

    public void a(boolean z) {
        this.i = this.h.name();
        switch (this.f20a) {
            case -1:
                if (z) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                break;
            case Cocos2dxEditBox.kEndActionUnknown /* 0 */:
            default:
                return;
            case 1:
            case 5:
                if (z) {
                    Bitmap bitmap = (Bitmap) this.f21b;
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                    this.f22c = byteArrayOutputStream.toByteArray();
                    return;
                }
                break;
            case BuildConfig.VERSION_CODE /* 2 */:
                this.f22c = ((String) this.f21b).getBytes(Charset.forName("UTF-16"));
                return;
            case Cocos2dxEditBox.kEndActionReturn /* 3 */:
                this.f22c = (byte[]) this.f21b;
                return;
            case 4:
            case 6:
                this.f22c = this.f21b.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
        this.d = (Parcelable) this.f21b;
    }

    public String b() {
        if (this.f20a == -1 && Build.VERSION.SDK_INT >= 23) {
            return a.b(this.f21b);
        }
        if (this.f20a == 2) {
            String str = this.j;
            return (str == null || TextUtils.isEmpty(str)) ? ((String) this.f21b).split(":", -1)[0] : this.j;
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public Uri c() {
        if (this.f20a == -1 && Build.VERSION.SDK_INT >= 23) {
            return a.c(this.f21b);
        }
        int i = this.f20a;
        if (i == 4 || i == 6) {
            return Uri.parse((String) this.f21b);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    public void d() {
        Parcelable parcelable;
        this.h = PorterDuff.Mode.valueOf(this.i);
        switch (this.f20a) {
            case -1:
                parcelable = this.d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                break;
            case Cocos2dxEditBox.kEndActionUnknown /* 0 */:
            default:
                return;
            case 1:
            case 5:
                parcelable = this.d;
                if (parcelable == null) {
                    byte[] bArr = this.f22c;
                    this.f21b = bArr;
                    this.f20a = 3;
                    this.e = 0;
                    this.f = bArr.length;
                    return;
                }
                break;
            case BuildConfig.VERSION_CODE /* 2 */:
            case 4:
            case 6:
                String str = new String(this.f22c, Charset.forName("UTF-16"));
                this.f21b = str;
                if (this.f20a == 2 && this.j == null) {
                    this.j = str.split(":", -1)[0];
                    return;
                }
                return;
            case Cocos2dxEditBox.kEndActionReturn /* 3 */:
                this.f21b = this.f22c;
                return;
        }
        this.f21b = parcelable;
    }

    public String toString() {
        int height;
        if (this.f20a == -1) {
            return String.valueOf(this.f21b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(a(this.f20a));
        switch (this.f20a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f21b).getWidth());
                sb.append("x");
                height = ((Bitmap) this.f21b).getHeight();
                sb.append(height);
                break;
            case BuildConfig.VERSION_CODE /* 2 */:
                sb.append(" pkg=");
                sb.append(this.j);
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(a())));
                break;
            case Cocos2dxEditBox.kEndActionReturn /* 3 */:
                sb.append(" len=");
                sb.append(this.e);
                if (this.f != 0) {
                    sb.append(" off=");
                    height = this.f;
                    sb.append(height);
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f21b);
                break;
        }
        if (this.g != null) {
            sb.append(" tint=");
            sb.append(this.g);
        }
        if (this.h != k) {
            sb.append(" mode=");
            sb.append(this.h);
        }
        sb.append(")");
        return sb.toString();
    }
}

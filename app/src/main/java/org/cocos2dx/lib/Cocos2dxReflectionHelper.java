package org.cocos2dx.lib;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class Cocos2dxReflectionHelper {
    public static <T> T getConstantValue(Class cls, String str) {
        StringBuilder sb;
        String str2;
        String string;
        try {
            return (T) cls.getDeclaredField(str).get(null);
        } catch (IllegalAccessException unused) {
            sb = new StringBuilder();
            sb.append(str);
            str = " is not accessible";
            sb.append(str);
            string = sb.toString();
            Log.e("error", string);
            return null;
        } catch (IllegalArgumentException unused2) {
            sb = new StringBuilder();
            str2 = "arguments error when get ";
            sb.append(str2);
            sb.append(str);
            string = sb.toString();
            Log.e("error", string);
            return null;
        } catch (NoSuchFieldException unused3) {
            string = "can not find " + str + " in " + cls.getName();
            Log.e("error", string);
            return null;
        } catch (Exception unused4) {
            sb = new StringBuilder();
            str2 = "can not get constant";
            sb.append(str2);
            sb.append(str);
            string = sb.toString();
            Log.e("error", string);
            return null;
        }
    }

    public static <T> T invokeInstanceMethod(Object obj, String str, Class[] clsArr, Object[] objArr) {
        StringBuilder sb;
        String str2;
        Class<?> cls = obj.getClass();
        try {
            return (T) cls.getMethod(str, clsArr).invoke(obj, objArr);
        } catch (IllegalAccessException unused) {
            sb = new StringBuilder();
            sb.append(str);
            str = " is not accessible";
            sb.append(str);
            Log.e("error", sb.toString());
            return null;
        } catch (IllegalArgumentException unused2) {
            sb = new StringBuilder();
            str2 = "arguments are error when invoking ";
            sb.append(str2);
            sb.append(str);
            Log.e("error", sb.toString());
            return null;
        } catch (NoSuchMethodException unused3) {
            sb = new StringBuilder();
            sb.append("can not find ");
            sb.append(str);
            sb.append(" in ");
            str = cls.getName();
            sb.append(str);
            Log.e("error", sb.toString());
            return null;
        } catch (InvocationTargetException unused4) {
            sb = new StringBuilder();
            str2 = "an exception was thrown by the invoked method when invoking ";
            sb.append(str2);
            sb.append(str);
            Log.e("error", sb.toString());
            return null;
        }
    }
}

package b.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: b.b.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0002a extends Binder implements a {

        /* JADX INFO: renamed from: b.b.a.a$a$a, reason: collision with other inner class name */
        private static class C0003a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f31a;

            C0003a(IBinder iBinder) {
                this.f31a = iBinder;
            }

            @Override // b.b.a.a
            public int a() throws android.os.RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    this.f31a.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.a.a
            public int a(int i) throws android.os.RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    parcelObtain.writeInt(i);
                    this.f31a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.a.a
            public int a(boolean z) throws android.os.RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.f31a.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f31a;
            }

            @Override // b.b.a.a
            public int b(int i) throws android.os.RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    parcelObtain.writeInt(i);
                    this.f31a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.a.a
            public int c(int i) throws android.os.RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    parcelObtain.writeInt(i);
                    this.f31a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.enhance.gameservice.IGameTuningService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0003a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }
    }

    int a() throws android.os.RemoteException;

    int a(int i) throws android.os.RemoteException;

    int a(boolean z) throws android.os.RemoteException;

    int b(int i) throws android.os.RemoteException;

    int c(int i) throws android.os.RemoteException;
}

package b.a.a.a.a;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<String, a> f27a = new HashMap<>();

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final File f28a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f29b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f30c;
        public long d;
        public long e = -1;

        public a(String str, File file, String str2) {
            this.f28a = file;
        }

        public AssetFileDescriptor a() {
            if (this.f30c != 0) {
                return null;
            }
            try {
                return new AssetFileDescriptor(ParcelFileDescriptor.open(this.f28a, 268435456), b(), this.d);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        public void a(RandomAccessFile randomAccessFile, ByteBuffer byteBuffer) {
            long j = this.f29b;
            try {
                randomAccessFile.seek(j);
                randomAccessFile.readFully(byteBuffer.array());
                if (byteBuffer.getInt(0) != 67324752) {
                    Log.w("zipro", "didn't find signature at start of lfh");
                    throw new IOException();
                }
                this.e = j + 30 + ((long) (byteBuffer.getShort(26) & 65535)) + ((long) (byteBuffer.getShort(28) & 65535));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        public long b() {
            return this.e;
        }
    }

    public b(String str) throws IOException {
        new HashMap();
        ByteBuffer.allocate(4);
        a(str);
    }

    private static int a(int i) {
        return ((i & 255) << 24) + ((65280 & i) << 8) + ((16711680 & i) >>> 8) + ((i >>> 24) & 255);
    }

    private static int a(RandomAccessFile randomAccessFile) throws IOException {
        return a(randomAccessFile.readInt());
    }

    void a(String str) throws IOException {
        String str2 = str;
        File file = new File(str2);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        long length = randomAccessFile.length();
        if (length < 22) {
            throw new IOException();
        }
        long j = 65557 > length ? length : 65557L;
        randomAccessFile.seek(0L);
        int iA = a(randomAccessFile);
        if (iA == 101010256) {
            Log.i("zipro", "Found Zip archive, but it looks empty");
            throw new IOException();
        }
        if (iA != 67324752) {
            Log.v("zipro", "Not a Zip archive");
            throw new IOException();
        }
        randomAccessFile.seek(length - j);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) j);
        byte[] bArrArray = byteBufferAllocate.array();
        randomAccessFile.readFully(bArrArray);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        int length2 = bArrArray.length - 22;
        while (length2 >= 0 && (bArrArray[length2] != 80 || byteBufferAllocate.getInt(length2) != 101010256)) {
            length2--;
        }
        if (length2 < 0) {
            Log.d("zipro", "Zip: EOCD not found, " + str2 + " is not zip");
        }
        short s = byteBufferAllocate.getShort(length2 + 8);
        long j2 = ((long) byteBufferAllocate.getInt(length2 + 12)) & 4294967295L;
        long j3 = ((long) byteBufferAllocate.getInt(length2 + 16)) & 4294967295L;
        if (j3 + j2 > length) {
            Log.w("zipro", "bad offsets (dir " + j3 + ", size " + j2 + ", eocd " + length2 + ")");
            throw new IOException();
        }
        if (s == 0) {
            Log.w("zipro", "empty archive?");
            throw new IOException();
        }
        MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, j3, j2);
        map.order(ByteOrder.LITTLE_ENDIAN);
        int s2 = 65535;
        byte[] bArr = new byte[65535];
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(30);
        byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < s) {
            if (map.getInt(i3) != 33639248) {
                Log.w("zipro", "Missed a central dir sig (at " + i3 + ")");
                throw new IOException();
            }
            int i4 = map.getShort(i3 + 28) & s2;
            int i5 = map.getShort(i3 + 30) & s2;
            int i6 = map.getShort(i3 + 32) & s2;
            map.position(i3 + 46);
            map.get(bArr, i, i4);
            map.position(i);
            String str3 = new String(bArr, i, i4);
            a aVar = new a(str2, file, str3);
            aVar.f30c = map.getShort(i3 + 10) & 65535;
            map.getInt(i3 + 12);
            map.getLong(i3 + 16);
            map.getLong(i3 + 20);
            aVar.d = map.getLong(i3 + 24) & 4294967295L;
            aVar.f29b = ((long) map.getInt(i3 + 42)) & 4294967295L;
            byteBufferAllocate2.clear();
            aVar.a(randomAccessFile, byteBufferAllocate2);
            this.f27a.put(str3, aVar);
            i3 += i4 + 46 + i5 + i6;
            i2++;
            str2 = str;
            file = file;
            s2 = 65535;
            i = 0;
        }
    }

    public AssetFileDescriptor b(String str) {
        a aVar = this.f27a.get(str);
        if (aVar != null) {
            return aVar.a();
        }
        return null;
    }
}

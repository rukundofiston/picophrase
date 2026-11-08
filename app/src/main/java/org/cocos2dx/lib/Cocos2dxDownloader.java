package org.cocos2dx.lib;

import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javax.net.ssl.SSLException;

/* JADX INFO: loaded from: classes.dex */
public class Cocos2dxDownloader {
    private static HashMap<String, Boolean> _resumingSupport = new HashMap<>();
    private int _countOfMaxProcessingTasks;
    private int _id;
    private String _tempFileNameSufix;
    private AsyncHttpClient _httpClient = new AsyncHttpClient();
    private HashMap _taskMap = new HashMap();
    private Queue<Runnable> _taskQueue = new LinkedList();
    private int _runningTaskCount = 0;

    static class DownloadTask {
        RequestHandle handle = null;
        AsyncHttpResponseHandler handler = null;
        long bytesReceived;
        long totalBytesReceived;
        long totalBytesExpected;

        DownloadTask() {
            resetStatus();
        }

        void resetStatus() {
            this.bytesReceived = 0L;
            this.totalBytesReceived = 0L;
            this.totalBytesExpected = 0L;
        }
    }

    static class DataTaskHandler extends BinaryHttpResponseHandler {
        int id;
        private Cocos2dxDownloader downloader;
        private long lastBytesWritten;

        public DataTaskHandler(Cocos2dxDownloader cocos2dxDownloader, int i) {
            super(new String[]{".*"});
            this.downloader = cocos2dxDownloader;
            this.id = i;
            this.lastBytesWritten = 0L;
        }

        void LogD(String str) {
            Log.d("Cocos2dxDownloader", str);
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onProgress(long j, long j2) {
            this.downloader.onProgress(this.id, j - this.lastBytesWritten, j, j2);
            this.lastBytesWritten = j;
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onStart() {
            this.downloader.onStart(this.id);
        }

        @Override // com.loopj.android.http.BinaryHttpResponseHandler, com.loopj.android.http.AsyncHttpResponseHandler
        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            LogD("onFailure(i:" + i + " headers:" + headerArr + " throwable:" + th);
            this.downloader.onFinish(this.id, i, th != null ? th.toString() : BuildConfig.FLAVOR, null);
        }

        @Override // com.loopj.android.http.BinaryHttpResponseHandler, com.loopj.android.http.AsyncHttpResponseHandler
        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            LogD("onSuccess(i:" + i + " headers:" + headerArr);
            this.downloader.onFinish(this.id, 0, null, bArr);
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onFinish() {
            this.downloader.runNextTaskIfExists();
        }
    }

    static class HeadTaskHandler extends AsyncHttpResponseHandler {
        int id;
        String host;
        String url;
        String path;
        private Cocos2dxDownloader downloader;

        public HeadTaskHandler(Cocos2dxDownloader cocos2dxDownloader, int i, String str, String str2, String str3) {
            this.downloader = cocos2dxDownloader;
            this.id = i;
            this.host = str;
            this.url = str2;
            this.path = str3;
        }

        void LogD(String str) {
            Log.d("Cocos2dxDownloader", str);
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            Boolean boolValueOf = false;
            for (Header header : headerArr) {
                if (header.getName().equals("Accept-Ranges")) {
                    boolValueOf = Boolean.valueOf(header.getValue().equals("bytes"));
                    break;
                }
            }
            Cocos2dxDownloader.setResumingSupport(this.host, boolValueOf);
            Cocos2dxDownloader.createTask(this.downloader, this.id, this.url, this.path);
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onFinish() {
            this.downloader.runNextTaskIfExists();
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            LogD("onFailure(code:" + i + " headers:" + headerArr + " throwable:" + th + " id:" + this.id);
            this.downloader.onFinish(this.id, i, th != null ? th.toString() : BuildConfig.FLAVOR, null);
        }
    }

    static class FileTaskHandler extends FileAsyncHttpResponseHandler {
        int id;
        File finalFile;
        private long initFileLen;
        private long lastBytesWritten;
        private Cocos2dxDownloader downloader;

        public FileTaskHandler(Cocos2dxDownloader cocos2dxDownloader, int i, File file, File file2) {
            super(file, true);
            this.finalFile = file2;
            this.downloader = cocos2dxDownloader;
            this.id = i;
            this.initFileLen = getTargetFile().length();
            this.lastBytesWritten = 0L;
        }

        void LogD(String str) {
            Log.d("Cocos2dxDownloader", str);
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onProgress(long j, long j2) {
            long j3 = j - this.lastBytesWritten;
            long j4 = this.initFileLen;
            this.downloader.onProgress(this.id, j3, j + j4, j2 + j4);
            this.lastBytesWritten = j;
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onStart() {
            this.downloader.onStart(this.id);
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onFinish() {
            this.downloader.runNextTaskIfExists();
        }

        @Override // com.loopj.android.http.FileAsyncHttpResponseHandler
        public void onFailure(int i, Header[] headerArr, Throwable th, File file) {
            LogD("onFailure(i:" + i + " headers:" + headerArr + " throwable:" + th + " file:" + file);
            this.downloader.onFinish(this.id, i, th != null ? th.toString() : BuildConfig.FLAVOR, null);
        }

        @Override // com.loopj.android.http.FileAsyncHttpResponseHandler
        public void onSuccess(int i, Header[] headerArr, File file) {
            String string = null;
            LogD("onSuccess(i:" + i + " headers:" + headerArr + " file:" + file);
            if (this.finalFile.exists()) {
                if (this.finalFile.isDirectory()) {
                    string = "Dest file is directory:" + this.finalFile.getAbsolutePath();
                } else if (this.finalFile.delete()) {
                    getTargetFile().renameTo(this.finalFile);
                } else {
                    string = "Can't remove old file:" + this.finalFile.getAbsolutePath();
                }
            } else {
                getTargetFile().renameTo(this.finalFile);
            }
            this.downloader.onFinish(this.id, 0, string, null);
        }
    }

    class ProgressRunnable implements Runnable {
        final /* synthetic */ int id;
        final /* synthetic */ long dl;
        final /* synthetic */ long dlnow;
        final /* synthetic */ long dltotal;

        ProgressRunnable(int i, long j, long j2, long j3) {
            this.id = i;
            this.dl = j;
            this.dlnow = j2;
            this.dltotal = j3;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxDownloader cocos2dxDownloader = Cocos2dxDownloader.this;
            cocos2dxDownloader.nativeOnProgress(cocos2dxDownloader._id, this.id, this.dl, this.dlnow, this.dltotal);
        }
    }

    class FinishRunnable implements Runnable {
        final /* synthetic */ int id;
        final /* synthetic */ int errCode;
        final /* synthetic */ String errStr;
        final /* synthetic */ byte[] data;

        FinishRunnable(int i, int i2, String str, byte[] bArr) {
            this.id = i;
            this.errCode = i2;
            this.errStr = str;
            this.data = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cocos2dxDownloader cocos2dxDownloader = Cocos2dxDownloader.this;
            cocos2dxDownloader.nativeOnFinish(cocos2dxDownloader._id, this.id, this.errCode, this.errStr, this.data);
        }
    }

    static class CreateTaskRunnable implements Runnable {
        final /* synthetic */ String path;
        final /* synthetic */ Cocos2dxDownloader downloader;
        final /* synthetic */ int id;
        final /* synthetic */ String url;

        class FailRunnable implements Runnable {
            final /* synthetic */ String errStr;

            FailRunnable(String str) {
                this.errStr = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxDownloader cocos2dxDownloader = CreateTaskRunnable.this.downloader;
                cocos2dxDownloader.nativeOnFinish(cocos2dxDownloader._id, CreateTaskRunnable.this.id, 0, this.errStr, null);
            }
        }

        CreateTaskRunnable(String str, Cocos2dxDownloader cocos2dxDownloader, int i, String str2) {
            this.path = str;
            this.downloader = cocos2dxDownloader;
            this.id = i;
            this.url = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            RequestHandle requestHandle = null;
            DownloadTask downloadTask = new DownloadTask();
            if (this.path.length() == 0) {
                downloadTask.handler = new DataTaskHandler(this.downloader, this.id);
                downloadTask.handle = this.downloader._httpClient.get(Cocos2dxHelper.getActivity(), this.url, downloadTask.handler);
            }
            if (this.path.length() != 0) {
                try {
                    String host = new URI(this.url).getHost();
                    if (host.startsWith("www.")) {
                        host = host.substring(4);
                    }
                    String str = host;
                    Boolean bool = false;
                    Boolean bool2 = true;
                    if (Cocos2dxDownloader._resumingSupport.containsKey(str)) {
                        bool = (Boolean) Cocos2dxDownloader._resumingSupport.get(str);
                        bool2 = false;
                    }
                    if (bool2.booleanValue()) {
                        downloadTask.handler = new HeadTaskHandler(this.downloader, this.id, str, this.url, this.path);
                        requestHandle = this.downloader._httpClient.head(Cocos2dxHelper.getActivity(), this.url, null, null, downloadTask.handler);
                    } else {
                        File file = new File(this.path + this.downloader._tempFileNameSufix);
                        if (!file.isDirectory()) {
                            File parentFile = file.getParentFile();
                            if (parentFile.isDirectory() || parentFile.mkdirs()) {
                                File file2 = new File(this.path);
                                if (!file2.isDirectory()) {
                                    downloadTask.handler = new FileTaskHandler(this.downloader, this.id, file, file2);
                                    Header[] headerArr = null;
                                    long length = file.length();
                                    if (bool.booleanValue() && length > 0) {
                                        ArrayList arrayList = new ArrayList();
                                        arrayList.add(new BasicHeader("Range", "bytes=" + length + "-"));
                                        headerArr = (Header[]) arrayList.toArray(new Header[arrayList.size()]);
                                    } else if (length > 0) {
                                        try {
                                            PrintWriter printWriter = new PrintWriter(file);
                                            printWriter.print(BuildConfig.FLAVOR);
                                            printWriter.close();
                                        } catch (FileNotFoundException unused) {
                                        }
                                    }
                                    requestHandle = this.downloader._httpClient.get(Cocos2dxHelper.getActivity(), this.url, headerArr, null, downloadTask.handler);
                                }
                            }
                        }
                    }
                    downloadTask.handle = requestHandle;
                } catch (URISyntaxException unused2) {
                }
            }
            if (downloadTask.handle != null) {
                this.downloader._taskMap.put(Integer.valueOf(this.id), downloadTask);
                return;
            }
            Cocos2dxHelper.runOnGLThread(new FailRunnable("Can't create DownloadTask for " + this.url));
        }
    }

    class CancelAllRunnable implements Runnable {
        CancelAllRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = Cocos2dxDownloader.this._taskMap.entrySet().iterator();
            while (it.hasNext()) {
                RequestHandle requestHandle = ((DownloadTask) ((Map.Entry) it.next()).getValue()).handle;
                if (requestHandle != null) {
                    requestHandle.cancel(true);
                }
            }
        }
    }

    public static void cancelAllRequests(Cocos2dxDownloader cocos2dxDownloader) {
        Cocos2dxHelper.getActivity().runOnUiThread(cocos2dxDownloader.new CancelAllRunnable());
    }

    public static Cocos2dxDownloader createDownloader(int i, int i2, String str, int i3) {
        Cocos2dxDownloader cocos2dxDownloader = new Cocos2dxDownloader();
        cocos2dxDownloader._id = i;
        cocos2dxDownloader._httpClient.setEnableRedirects(true);
        if (i2 > 0) {
            cocos2dxDownloader._httpClient.setTimeout(i2 * 1000);
        }
        AsyncHttpClient.allowRetryExceptionClass(SSLException.class);
        cocos2dxDownloader._httpClient.setURLEncodingEnabled(false);
        cocos2dxDownloader._tempFileNameSufix = str;
        cocos2dxDownloader._countOfMaxProcessingTasks = i3;
        return cocos2dxDownloader;
    }

    public static void createTask(Cocos2dxDownloader cocos2dxDownloader, int i, String str, String str2) {
        cocos2dxDownloader.enqueueTask(new CreateTaskRunnable(str2, cocos2dxDownloader, i, str));
    }

    public static void setResumingSupport(String str, Boolean bool) {
        _resumingSupport.put(str, bool);
    }

    public void enqueueTask(Runnable runnable) {
        synchronized (this._taskQueue) {
            if (this._runningTaskCount < this._countOfMaxProcessingTasks) {
                Cocos2dxHelper.getActivity().runOnUiThread(runnable);
                this._runningTaskCount++;
            } else {
                this._taskQueue.add(runnable);
            }
        }
    }

    native void nativeOnFinish(int i, int i2, int i3, String str, byte[] bArr);

    native void nativeOnProgress(int i, int i2, long j, long j2, long j3);

    public void onFinish(int i, int i2, String str, byte[] bArr) {
        if (((DownloadTask) this._taskMap.get(Integer.valueOf(i))) == null) {
            return;
        }
        this._taskMap.remove(Integer.valueOf(i));
        Cocos2dxHelper.runOnGLThread(new FinishRunnable(i, i2, str, bArr));
    }

    void onProgress(int i, long j, long j2, long j3) {
        DownloadTask downloadTask = (DownloadTask) this._taskMap.get(Integer.valueOf(i));
        if (downloadTask != null) {
            downloadTask.bytesReceived = j;
            downloadTask.totalBytesReceived = j2;
            downloadTask.totalBytesExpected = j3;
        }
        Cocos2dxHelper.runOnGLThread(new ProgressRunnable(i, j, j2, j3));
    }

    public void onStart(int i) {
        DownloadTask downloadTask = (DownloadTask) this._taskMap.get(Integer.valueOf(i));
        if (downloadTask != null) {
            downloadTask.resetStatus();
        }
    }

    public void runNextTaskIfExists() {
        synchronized (this._taskQueue) {
            Runnable runnablePoll = this._taskQueue.poll();
            if (runnablePoll != null) {
                Cocos2dxHelper.getActivity().runOnUiThread(runnablePoll);
            } else {
                this._runningTaskCount--;
            }
        }
    }
}

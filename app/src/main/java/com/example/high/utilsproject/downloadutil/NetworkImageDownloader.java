package com.example.high.utilsproject.downloadutil;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.hehigh.hlog.HLog;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by high on 16-7-2.
 */
public class NetworkImageDownloader {
    private static final String TAG = "NetworkImageDownloader";

    static NetworkImageDownloader sInst;

    private final OkHttpClient client;

    private NetworkImageDownloader() {
        client = new OkHttpClient();
    }

    public static NetworkImageDownloader getInst() {
        if (sInst == null) {
            sInst = new NetworkImageDownloader();
        }
        return sInst;
    }

    public void run(String localPath, String url, @Nullable ImageView imageView, @Nullable Context context) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new ImageDownloadCallback(localPath, imageView, context));
    }

    private class ImageDownloadCallback implements Callback {
        private final String mLocalPath;
        private final WeakReference<ImageView> mImageView;
        private final WeakReference<Context> mContext;

        public ImageDownloadCallback(String localPath, @Nullable ImageView imageView, @Nullable Context context) {
            this.mLocalPath = localPath;
            this.mImageView = new WeakReference<ImageView>(imageView);
            this.mContext = new WeakReference<Context>(context);
        }

        @Override
        public void onFailure(Call call, IOException e) {
            HLog.e(TAG, "Image download onFailure");
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            File localFile = new File(mLocalPath);
            InputStreamToFile.save(response.body().byteStream(), localFile);
            Context context = mContext.get();
            if (context != null) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageView imageView = mImageView.get();
                        if (imageView != null) {
                            File localFile = new File(mLocalPath);
                            imageView.setImageURI(Uri.fromFile(localFile));
                        }
                    }
                });
            }
        }
    }
}

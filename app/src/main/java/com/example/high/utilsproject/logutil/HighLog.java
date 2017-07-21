package com.example.high.utilsproject.logutil;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by high on 16-6-19.
 */
public class HighLog {
    public static void printStack(String tag){
        RuntimeException here = new RuntimeException("here");
        here.fillInStackTrace();
        Log.e("High" + tag, "Called: ", here);
    }

    public static void printLog(String tag, String msg) {
        Log.e("High" + tag, msg);
    }

    public static void printBundle(String tag, Bundle bundle) {
        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            Log.e("High" + tag, "bundle key:" + key + " value:" + (value != null ? value.toString() : "null"));
        }
    }
}

package com.hehigh.hlog;

import android.os.Bundle;
import android.util.Log;

import java.util.Date;

/**
 * Created by high on 17-1-20.
 */

public class HLog {
    public static boolean on = true;

    public static void switchOff() {
        on = false;
    }

    public static void switchOn() {
        on = true;
    }

    public static void s(String tag){
        RuntimeException here = new RuntimeException("here");
        here.fillInStackTrace();
        Log.e(tag, "Called: ", here);
    }

    public static void t(String tag, String msg, long mills) {
        Log.e(tag, msg + ": " + new Date(mills).toString());
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void b(String tag, String msg, Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        sb.append(msg);
        if (bundle == null) {
            sb.append("\nbundle: null");
        } else {
            sb.append("\nbundle:\n");
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                sb.append(key);
                sb.append(": ");
                sb.append((value != null ? value.toString() : "null"));
                sb.append("\n");
            }
        }
        e(tag, sb.toString());
    }
}

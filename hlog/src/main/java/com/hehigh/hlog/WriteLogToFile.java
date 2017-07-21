package com.hehigh.hlog;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by high on 17-5-23.
 */

public class WriteLogToFile {
    public static void writeLog(String tag, String msg) {

        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        String timeStr = format.format(date);
        File sdcard = Environment.getExternalStorageDirectory();
        if (sdcard != null) {
            File log = new File(sdcard.getAbsolutePath() + File.separator + "temp.log");
            write(log, timeStr + " " + tag + ": " + msg);
        }
    }

    static void write(File file, String msg) {
        if (TextUtils.isEmpty(msg) || file == null) {
            return;
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }
        Scanner sc = new Scanner(msg);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
            while (sc.hasNextLine()) {
                fos.write(sc.nextLine().getBytes());
                fos.write("\n".getBytes());
            }
            fos.flush();
        } catch (IOException e) {
        } catch (Exception e) {
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
            }
        }
    }
}

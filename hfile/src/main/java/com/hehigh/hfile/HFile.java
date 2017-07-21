package com.hehigh.hfile;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Scanner;

/**
 * Created by high on 17-6-19.
 */

public class HFile {
    private static String convertHashToString(byte[] md5Bytes) {
        String returnVal = "";
        for (int i = 0; i < md5Bytes.length; i++) {
            returnVal += Integer.toString(( md5Bytes[i] & 0xff ) + 0x100, 16).substring(1);
        }
        return returnVal;
    }

    public static String fileToMD5(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            MessageDigest digest = MessageDigest.getInstance("MD5");
            int numRead = 0;
            while (numRead != -1) {
                numRead = inputStream.read(buffer);
                if (numRead > 0)
                    digest.update(buffer, 0, numRead);
            }
            byte [] md5Bytes = digest.digest();
            return convertHashToString(md5Bytes);
        } catch (Exception e) {
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static String readStringFromFile(File file) {
        StringBuilder sb = new StringBuilder();
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (reader != null) {
            char[] buf = new char[1024];
            try {
                int size = 0;
                while (true) {
                    size = reader.read(buf);
                    if (size > 0) {
                        sb.append(buf, 0, size);
                    } else {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sb.toString();
    }

    public static void writeStringToFile(File file, String content) {
        if (TextUtils.isEmpty(content) || file == null) {
            return;
        }
        if (file.exists()) {
            file.delete();
            file = new File(file.getAbsolutePath());
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
        Scanner sc = new Scanner(content.toString());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
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

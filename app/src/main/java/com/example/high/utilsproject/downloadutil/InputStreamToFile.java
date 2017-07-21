package com.example.high.utilsproject.downloadutil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hejinhai on 2015/7/13.
 */
public class InputStreamToFile {
    public static void save(InputStream inStream, File file){
        if(inStream == null){
            return;
        }
        FileOutputStream out = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            out = new FileOutputStream(file);
            byte buf[] = new byte[1024];
            int numread = 0;
            numread = inStream.read(buf);
            while (numread > 0){
                out.write(buf,0,numread);
                numread = inStream.read(buf);
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(out != null ) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

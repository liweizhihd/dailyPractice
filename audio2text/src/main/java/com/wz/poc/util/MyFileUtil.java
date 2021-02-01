package com.wz.poc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author liweizhi
 * @date 2021/1/8
 */
public class MyFileUtil {
    public static void saveFile(InputStream byteStream) {
        String dest = "C:\\Users\\liweizhi\\Desktop\\download.mp3";
        saveFile(byteStream, dest);
    }

    public static void saveFile(InputStream byteStream, String dest) {
        File file = new File(dest);
        byte[] buf = new byte[2048];
        int len = 0;

        try (FileOutputStream fos = new FileOutputStream(file)) {
            while ((len = byteStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

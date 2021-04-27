package com.czg.xmind.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {


    public static void write(byte[] bytes, File file) {
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fileOutputStream = null;
        try {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);

            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static boolean clearDir(File dir) {
        if (!dir.isDirectory()) {
            return dir.delete();
        } else {
            File[] files = dir.listFiles();
            if (files != null && files.length != 0) {
                boolean cleared = true;
                File[] var6 = files;
                int var5 = files.length;

                for (int var4 = 0; var4 < var5; ++var4) {
                    File sub = var6[var4];
                    cleared &= delete(sub);
                }

                return cleared;
            } else {
                return true;
            }
        }
    }

    public static boolean delete(File f) {
        if (f.isFile()) {
            return f.delete();
        } else if (f.isDirectory()) {
            boolean b = clearDir(f);
            b &= f.delete();
            return b;
        } else {
            return false;
        }
    }
}

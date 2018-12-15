package com.finvendor.server.common.infra.download;

import com.finvendor.common.util.LogUtil;

import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class URLReader {

    public static void copyURLToFile(URL url, File file) throws IOException {

        InputStream input = null;
        FileOutputStream output = null;
        try {
            input = url.openStream();
            if (file.exists()) {
                if (file.isDirectory())
                    throw new IOException("File '" + file + "' is a directory");

                if (!file.canWrite())
                    throw new IOException("File '" + file + "' cannot be written");
            } else {
                File parent = file.getParentFile();
                if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
                    throw new IOException("File '" + file + "' could not be created");
                }
            }

            output = new FileOutputStream(file);

            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }

            System.out.println("File '" + file + "' downloaded successfully!");
        } finally {

            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LogUtil.logError(e.getMessage());
                }
            }

            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    LogUtil.logError(e.getMessage());
                }
            }

        }
    }

    public static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        //URL pointing to the file
        String sUrl = "https://www.nseindia.com/content/historical/EQUITIES/2018/AUG/cm07AUG2018bhav.csv.zip";

        URL url = new URL(sUrl);

        //File where to be downloaded
        File file = new File("d:/ayush/bhav.zip");

        URLReader.copyURLToFile(url, file);
        URLReader.unzip(file.getPath(), "d:/ayush/bhav");
    }

}
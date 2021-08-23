package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class checksum {
    public static String CheckSum(MessageDigest digest, File file) throws IOException {

        FileInputStream in = new FileInputStream(file);
        byte[] byteArray = new byte[1024];

        int count = 0;
        while ((count = in.read(byteArray)) != -1) {
            digest.update(byteArray, 0, count);
        }

        in.close();

        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < bytes.length; x++) {

            sb.append(Integer.toString((bytes[x] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static List<String> CheckSumCheck(Path filepath) throws IOException, NoSuchAlgorithmException {
        List<Path> paths = DirectoryMonitor.listFiles(filepath);

        List<String> checkSum = new ArrayList<>();

        for (int x = 0; x <= paths.size() - 1; x++) {

            File file = new File(String.valueOf(paths.get(x)));
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            checkSum.add(CheckSum(md5Digest, file));
        }
        return checkSum;
    }
}

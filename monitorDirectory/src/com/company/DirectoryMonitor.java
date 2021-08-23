package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

public class DirectoryMonitor {

    public static void FileMove() throws IOException {

        Path path1 = Paths.get("/Users/yashveerraibasgeet/Desktop/source");
        File path2 = new File("/Users/yashveerraibasgeet/Desktop/destination");

        List<Path> paths = listFiles(path1);
        List<Path> fileNames = listNames(paths);

        System.out.println("Files to be moved:- " + fileNames);

        for(int x = 0; x <= paths.size() - 1; x++){

                File initialPath = new File(String.valueOf(paths.get(x)));
                File finalPath = new File(path2.getPath() + "/" + fileNames.get(x));

                Files.move(initialPath.toPath(), finalPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        System.out.println("Files moved to another directory");
    }

    // list all files from this path
    public static List<Path> listFiles(Path path1) throws IOException {

        List<Path> result;
        try (Stream<Path> walk = Files.walk(path1, Integer.MAX_VALUE)) {
            result = walk
                    .filter(Files::isRegularFile)
                    .limit(30)
                    .collect(Collectors.toList());
        }
        return result;

    }

    //list all files names
    public static List<Path> listNames(List<Path> paths){
        List<Path> fileNames = paths.stream()
                .map(Path::getFileName)
                .collect(Collectors.toList());

        return fileNames;
    }

}
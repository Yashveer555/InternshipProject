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

public class DirectoryMonitor {

    public static void FileMove(Path path) throws IOException {

        File path2 = new File("/Users/yashveerraibasgeet/Desktop/destination");

        List<Path> paths = listFiles(path);
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

    //find file path from filename and the directory path;
    public static List<Path> filePath(String filename, Path pathDirectory) throws IOException {

        try (Stream<Path> walk = Files.walk(pathDirectory, Integer.MAX_VALUE)) {
            return walk
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().equalsIgnoreCase(filename))
                    .collect(Collectors.toList());
        }
    }

}
package com.company;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class database {

    public static void main(String args[]) throws SQLException{
        String URL = "jdbc:mysql://localhost:3306/BMS";
        String uname = "root";
        String password = "";
        String query = "SELECT * FROM FILE_MONITOR";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection(URL, uname, password);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);

            Path pathDirectory = Paths.get("/Users/yashveerraibasgeet/Desktop/source");

            while (result.next()) {

                String filename = result.getString(2);
                System.out.println("File_NAME :- " + filename);
                String CheckSum = result.getString(3);

                List<Path> pathname;
                try (Stream<Path> walk = Files.walk(pathDirectory, Integer.MAX_VALUE)) {
                    pathname = walk
                            .filter(Files::isRegularFile)
                            .filter(p -> p.getFileName().toString().equalsIgnoreCase(filename))
                            .collect(Collectors.toList());
                }

                System.out.println(pathname);

                List<String> checkSums = checksum.CheckSumCheck(pathDirectory);

                if (checkSums.contains(CheckSum)){

                }
            }

        }
        catch (SQLException | IOException | NoSuchAlgorithmException throwable) {
            throwable.printStackTrace();
        }
    }
}

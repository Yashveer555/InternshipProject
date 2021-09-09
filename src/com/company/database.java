package com.company;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class database {

    public static void main(String args[]) throws SQLException{
        while(true) {
            String URL = "jdbc:mysql://localhost:3306/BMS";
            String uname = "BMS";
            String password = "1234567";
            String query1 = "SELECT * FROM FILE_MONITOR";
            String query2 = "UPDATE FILE_MONITOR SET STATUS = 1 WHERE FILE_NAME = ";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                Connection con = DriverManager.getConnection(URL, uname, password);
                Statement statement1 = con.createStatement();
                ResultSet result = statement1.executeQuery(query1);

                Path pathDirectory = Paths.get("/Users/yashveerraibasgeet/Desktop/source");

                while (result.next()) {

                    String filename = result.getString(2);

                    String CheckSum = result.getString(3);

                    int status = result.getInt(12);

                    //gives the path of the file that will be modified
                    List<Path> pathname = DirectoryMonitor.filePath(filename, pathDirectory);

                    //creates a list of all checksums for all the files found in source Directory
                    List<String> checkSums = checksum.CheckSumCheck(pathDirectory);

                    //compare checksum calculated against the list of checksums and that status is 0
                    if (checkSums.contains(CheckSum) && status == 0) {

                        System.out.println("File_NAME :- " + filename);

                        File file = new File(String.valueOf(pathname.get(0)));
                        Scanner sc = new Scanner(file);

                        /*while (sc.hasNextLine()) {

                            String str = sc.nextLine();
                            List<String> content = FileFilter.parseLine(str);

                            if (!content.isEmpty()) {

                                String[] elements = content.get(3).split(",");

                                String query3 = "select * from SUBC_ACCOUNT_MAPPING WHERE SUBSCRIPTION_NUMBER = " + elements[7] + " AND SUBSCRIPTION_ID = " + elements[5] + " AND ACCOUNT_NUMBER = " + elements[2];

                                Statement statement2 = con.createStatement();
                                ResultSet result2 = statement2.executeQuery(query3);

                                while(result2.next()) {

                                    String SUBSCRIPTION_NUMBER = result2.getString(2);
                                    String SUBSCRIPTION_ID = result2.getString(3);
                                    String ACCOUNT_NUMBER = result2.getString(4);

                                    if (Objects.equals(SUBSCRIPTION_NUMBER, elements[7]) && Objects.equals(SUBSCRIPTION_ID, elements[5]) && Objects.equals(ACCOUNT_NUMBER,elements[2])) {*/

                                        FileFilter.checkFileAgainstParam(String.valueOf(pathname.get(0)));
                                        Statement statement3 = con.createStatement();
                                        statement3.executeUpdate(query2 + "'" + filename + "'");

                                    //}
                                //}
                            //}
                        //}

                    }
                }

            }
            catch (SQLException | IOException | NoSuchAlgorithmException throwable) {
                throwable.printStackTrace();
            }
        }
    }
}


package com.company;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class database {

    public static void main(String args[]) throws SQLException{
        String URL = "jdbc:mysql://localhost:3306/BMS";
        String uname = "root";
        String password = "Yrbprod@1412";
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
                    File file = new File(String.valueOf(pathname.get(0)));

                    Scanner sc = new Scanner(file);
                    while(sc.hasNextLine()){
                        String str = sc.nextLine();
                        parseLine(str);

                    }
                }
            }

        }
        catch (SQLException | IOException | NoSuchAlgorithmException throwable) {
            throwable.printStackTrace();
        }
    }
    public static void parseLine(String str){
        String GENERIC_INFO, SPLIT_CDR_INFO, TRANSACTION_INFO, SUBS_INFO, SESSION_INFO, SERVICE_INFO, MONETARY, NON_MONETARY, PACKAGE_INFO, ACC_BALANCE_INFO, CON_ACC_COUNTER_INFO, RATING_BALANCE_INFO_1, RATING_COUNTER_INFO_2, RESERVED_GRP1, RESERVED_GRP2 ;
        Scanner sc = new Scanner(str);
        sc.useDelimiter("[|]");
        while(sc.hasNext()) {
            GENERIC_INFO = sc.next();
            SPLIT_CDR_INFO = sc.next();
            TRANSACTION_INFO = sc.next();
            SUBS_INFO = sc.next();
            SESSION_INFO = sc.next();
            SERVICE_INFO = sc.next();
            MONETARY = sc.next();
            NON_MONETARY = sc.next();
            PACKAGE_INFO = sc.next();
            ACC_BALANCE_INFO = sc.next();
            CON_ACC_COUNTER_INFO = sc.next();
            RATING_BALANCE_INFO_1 = sc.next();
            RATING_COUNTER_INFO_2 = sc.next();
            RESERVED_GRP1 = sc.next();
            RESERVED_GRP2 = sc.next();

            System.out.println(GENERIC_INFO);
            System.out.println(SPLIT_CDR_INFO);
            System.out.println(TRANSACTION_INFO);
            System.out.println(SUBS_INFO);
            System.out.println(SESSION_INFO);
            System.out.println(SERVICE_INFO);
            System.out.println(MONETARY);
            System.out.println(NON_MONETARY);
            System.out.println(PACKAGE_INFO);
            System.out.println(ACC_BALANCE_INFO);
            System.out.println(CON_ACC_COUNTER_INFO);
            System.out.println(RATING_BALANCE_INFO_1);
            System.out.println(RATING_COUNTER_INFO_2);
            System.out.println(RESERVED_GRP1);
            System.out.println(RESERVED_GRP2);
            System.out.println(" ");
        }
    }
}

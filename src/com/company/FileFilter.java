package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FileFilter {
    public static void checkFileAgainstParam(String filename) throws IOException {
        File fileToBeModified = new File(filename);

        try{
            File modifiedFile = new File("/Users/yashveerraibasgeet/Desktop/source/newfile/" + "new_" + DirectoryMonitor.listNames(List.of(fileToBeModified.toPath())).get(0));
            if(modifiedFile.createNewFile()){
                System.out.println("File created" + modifiedFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter modify = new FileWriter("/Users/yashveerraibasgeet/Desktop/source/newfile/" + "new_" + DirectoryMonitor.listNames(List.of(fileToBeModified.toPath())).get(0))) {

            String staticValue = String.valueOf(System.currentTimeMillis());
            modify.write(staticValue + "\r\n");
            modify.write("\r\n");

            Scanner sc = new Scanner(fileToBeModified);

            while (sc.hasNextLine()) {

                String str = sc.nextLine();
                List<String> content = parseLine(str);

                if(!content.isEmpty()) {

                    String[] elementToMap = content.get(3).split(",");

                    String query3 = "select * from SUBC_ACCOUNT_MAPPING WHERE SUBSCRIPTION_NUMBER = " + elementToMap[7] + " AND SUBSCRIPTION_ID = " + elementToMap[5] + " AND ACCOUNT_NUMBER = " + elementToMap[2];
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BMS", "BMS", "1234567");
                    Statement statement2 = con.createStatement();
                    ResultSet result2 = statement2.executeQuery(query3);

                    while (result2.next()) {

                        String SUBSCRIPTION_NUMBER = result2.getString(2);
                        String SUBSCRIPTION_ID = result2.getString(3);
                        String ACCOUNT_NUMBER = result2.getString(4);

                        if (Objects.equals(SUBSCRIPTION_NUMBER, elementToMap[7]) && Objects.equals(SUBSCRIPTION_ID, elementToMap[5]) && Objects.equals(ACCOUNT_NUMBER, elementToMap[2])) {

                            String input1 = content.get(0);
                            String input2 = content.get(1);
                            String input3 = content.get(2);
                            String input4 = content.get(3);
                            String input5 = content.get(4);
                            String input6 = content.get(5);
                            String input7 = content.get(6);
                            String input8 = content.get(8);
                            String input9 = content.get(9);

                            String[] element1 = input1.split(",");
                            String[] element2 = input2.split(",");
                            String[] element3 = input3.split(",", 7);
                            String[] element4 = input4.split(",");
                            String[] element5 = input5.split(",");
                            String[] element6 = input6.split(",");
                            String[] element7 = input7.split(",");
                            String[] element8 = input8.split(",");
                            String[] element9 = input9.split(",");

                            //Check param for GENERIC_INFO
                            Pattern pattern1 = Pattern.compile("[NTE]");
                            Pattern pattern2 = Pattern.compile("[0-1]");
                            Pattern pattern3 = Pattern.compile("[CP]");
                            Pattern pattern4 = Pattern.compile("[+-]?[\\d]+(?:[\\.][\\d]+)?[\\s]+[+-]?[\\d]+(?:[\\.][\\d]+)?");
                            Pattern pattern5 = Pattern.compile("[+-]?[\\d]+(?:[\\.][\\d]+)?[\\s]+[+-]?[\\d]+(?:[\\.][\\d]+)?");
                            Pattern pattern6 = Pattern.compile("[+-]?[\\d]+(?:[\\.][\\d]+)?[\\s]+[+-]?[\\d]+(?:[\\.][\\d]+)?");

                            Matcher matcher1 = pattern1.matcher(element1[1]);
                            Matcher matcher2 = pattern2.matcher(element1[2]);
                            Matcher matcher3 = pattern3.matcher(element1[3]);
                            Matcher matcher4 = pattern4.matcher(element1[4]);
                            Matcher matcher5 = pattern5.matcher(element1[5]);
                            Matcher matcher6 = pattern6.matcher(element1[6]);

                            while (matcher1.find()) {
                                modify.write(matcher1.group());
                            }
                            modify.write(",");

                            while (matcher2.find()) {
                                modify.write(matcher2.group());
                            }
                            modify.write(",");


                            while (matcher3.find()) {
                                modify.write(matcher3.group());
                            }
                            modify.write(",");


                            while (matcher4.find()) {
                                modify.write(matcher4.group());
                            }
                            modify.write(",");


                            while (matcher5.find()) {
                                modify.write(matcher5.group());
                            }
                            modify.write(",");


                            while (matcher6.find()) {
                                modify.write(matcher6.group());
                            }
                            modify.write("|");


                            //Check param for SPLIT_CDR_INFO

                            Pattern pattern7 = Pattern.compile("[012]");
                            Pattern pattern8 = Pattern.compile("[0123]");
                            Pattern pattern9 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d|0)");

                            Matcher matcher7 = pattern7.matcher(element2[0]);
                            Matcher matcher8 = pattern8.matcher(element2[1]);
                            Matcher matcher9 = pattern9.matcher(element2[2]);

                            while (matcher7.find()) {
                                modify.write(matcher7.group());
                            }
                            modify.write(",");

                            while (matcher8.find()) {
                                modify.write(matcher8.group());
                            }
                            modify.write(",");

                            while (matcher9.find()) {
                                modify.write(matcher9.group());
                            }
                            modify.write("|");

                            //Check param for TRANSACTION_INFO

                            Pattern pattern10 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");
                            Pattern pattern11 = Pattern.compile("^[a-zA-Z0-9_]*$");
                            Pattern pattern12 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");

                            Matcher matcher10 = pattern10.matcher(element3[0]);
                            Matcher matcher11 = pattern11.matcher(element3[1]);
                            Matcher matcher12 = pattern12.matcher(element3[3]);

                            while (matcher10.find()) {
                                modify.write(matcher10.group());
                            }
                            modify.write(",");

                            while (matcher11.find()) {
                                modify.write(matcher11.group());
                            }
                            modify.write(",");

                            while (matcher12.find()) {
                                modify.write(matcher12.group());
                            }
                            modify.write("|");

                            //Check param for SUBS_INFO
                            Pattern pattern13 = Pattern.compile("[ACSG]");
                            Pattern pattern14 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");
                            Pattern pattern15 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");
                            Pattern pattern16 = Pattern.compile("[1-2]");
                            Pattern pattern17 = Pattern.compile("[1-2]");
                            Pattern pattern18 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");
                            Pattern pattern19 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");

                            Matcher matcher13 = pattern13.matcher(element4[0]);
                            Matcher matcher14 = pattern14.matcher(element4[1]);
                            Matcher matcher15 = pattern15.matcher(element4[2]);
                            Matcher matcher16 = pattern16.matcher(element4[3]);
                            Matcher matcher17 = pattern17.matcher(element4[4]);
                            Matcher matcher18 = pattern18.matcher(element4[5]);
                            Matcher matcher19 = pattern19.matcher(element4[6]);

                            while (matcher13.find()) {
                                modify.write(matcher13.group());
                            }
                            modify.write(",");

                            while (matcher14.find()) {
                                modify.write(matcher14.group());
                            }
                            modify.write(",");

                            while (matcher15.find()) {
                                modify.write(matcher15.group());
                            }
                            modify.write(",");

                            while (matcher16.find()) {
                                modify.write(matcher16.group());
                            }
                            modify.write(",");

                            while (matcher17.find()) {
                                modify.write(matcher17.group());
                            }
                            modify.write(",");

                            while (matcher18.find()) {
                                modify.write(matcher18.group());
                            }
                            modify.write(",");

                            while (matcher19.find()) {
                                modify.write(matcher19.group());
                            }
                            modify.write("|");

                            //Check param for SESSION_INFO
                            Pattern pattern20 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");
                            Pattern pattern21 = Pattern.compile("^[a-zA-Z0-9!@#$&()`.+,\\\"-;]*$");
                            Pattern pattern22 = Pattern.compile("[0-9]|10");
                            Pattern pattern23 = Pattern.compile("^(?:[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9]|0|-1)$");
                            Pattern pattern24 = Pattern.compile("[0-9]|10");
                            Pattern pattern25 = Pattern.compile("^(?:[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9]|[0])$");

                            Matcher matcher20 = pattern20.matcher(element5[0]);
                            Matcher matcher21 = pattern21.matcher(element5[1]);
                            Matcher matcher22 = pattern22.matcher(element5[2]);
                            Matcher matcher23 = pattern23.matcher(element5[3]);
                            Matcher matcher24 = pattern24.matcher(element5[4]);
                            Matcher matcher25 = pattern25.matcher(element5[5]);

                            while (matcher20.find()) {
                                modify.write(matcher20.group());
                            }
                            modify.write(",");

                            while (matcher21.find()) {
                                modify.write(matcher21.group());
                            }
                            modify.write(",");

                            while (matcher22.find()) {
                                modify.write(matcher22.group());
                            }
                            modify.write(",");

                            while (matcher23.find()) {
                                modify.write(matcher23.group());
                            }
                            modify.write(",");

                            while (matcher24.find()) {
                                modify.write(matcher24.group());
                            }
                            modify.write(",");

                            while (matcher25.find()) {
                                modify.write(matcher25.group());
                            }
                            modify.write("|");

                            //Check param for SERVICE_INFO
                            Pattern pattern26 = Pattern.compile("^[1-9][0-9]?$|^100|0$");
                            Pattern pattern27 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d|0)");
                            Pattern pattern28 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d|0)");
                            Pattern pattern29 = Pattern.compile("[0-6]");

                            Matcher matcher26 = pattern26.matcher(element6[1]);
                            Matcher matcher27 = pattern27.matcher(element6[2]);
                            Matcher matcher28 = pattern28.matcher(element6[3]);
                            Matcher matcher29 = pattern29.matcher(element6[4]);

                            while (matcher26.find()) {
                                modify.write(matcher26.group());
                            }
                            modify.write(",");

                            while (matcher27.find()) {
                                modify.write(matcher27.group());
                            }
                            modify.write(",");

                            while (matcher28.find()) {
                                modify.write(matcher28.group());
                            }
                            modify.write(",");

                            while (matcher29.find()) {
                                modify.write(matcher29.group());
                            }
                            modify.write("|");

                            //Check param for CONSUMPTION (MONETARY)
                            Pattern pattern30 = Pattern.compile("^0*(-?[1-9]\\d{2,}|[5-9]\\d|0)$");
                            Pattern pattern31 = Pattern.compile("^0*(-?[1-9]\\d{2,}|[5-9]\\d|0)$");
                            Pattern pattern32 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d|0)$");

                            Matcher matcher30 = pattern30.matcher(element7[0]);
                            Matcher matcher31 = pattern31.matcher(element7[1]);
                            Matcher matcher32 = pattern32.matcher(element7[2]);

                            while (matcher30.find()) {
                                modify.write(matcher30.group());
                            }
                            modify.write(",");

                            while (matcher31.find()) {
                                modify.write(matcher31.group());
                            }
                            modify.write(",");

                            while (matcher32.find()) {
                                modify.write(matcher32.group());
                            }
                            modify.write("|");

                            //Check param for PACKAGE_INFO
                            Pattern pattern33 = Pattern.compile("^0*<*([1-9]\\d{2,}|[5-9]\\d|0)$");
                            Pattern pattern34 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d|0)$");

                            Matcher matcher33 = pattern33.matcher(element8[0]);
                            Matcher matcher34 = pattern34.matcher(element8[1]);

                            while (matcher33.find()) {
                                modify.write(matcher33.group());

                            }
                            modify.write(",");

                            while (matcher34.find()) {
                                modify.write(matcher34.group());
                            }

                            modify.write(">");
                            modify.write("|");

                            //Check param for ACC_BALANCE_INFO
                            Pattern pattern35 = Pattern.compile("^(0[1-9]|[1-9][0-9]|0|00)$");
                            Pattern pattern36 = Pattern.compile("^[a-zA-Z0-9]*$");
                            Pattern pattern37 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");
                            Pattern pattern38 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");
                            Pattern pattern39 = Pattern.compile("^(0|[1-9][0-9]?|100)$");
                            Pattern pattern40 = Pattern.compile("[2-8]");
                            Pattern pattern41 = Pattern.compile("^(0|[0-1][0-6]?)$");
                            Pattern pattern42 = Pattern.compile("ACSG");
                            Pattern pattern43 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");
                            Pattern pattern44 = Pattern.compile("^0*([1-9]\\d{2,}|[5-9]\\d)$");

                            Matcher matcher35 = pattern35.matcher(element9[0]);
                            Matcher matcher36 = pattern36.matcher(element9[1]);
                            Matcher matcher37 = pattern37.matcher(element9[2]);
                            Matcher matcher38 = pattern38.matcher(element9[3]);
                            Matcher matcher39 = pattern39.matcher(element9[4]);
                            Matcher matcher40 = pattern40.matcher(element9[5]);
                            Matcher matcher41 = pattern41.matcher(element9[6]);
                            Matcher matcher42 = pattern42.matcher(element9[7]);
                            Matcher matcher43 = pattern43.matcher(element9[8]);
                            Matcher matcher44 = pattern44.matcher(element9[9]);

                            while (matcher35.find()) {
                                modify.write(matcher35.group());
                            }
                            modify.write(",");

                            while (matcher36.find()) {
                                modify.write(matcher36.group());
                            }
                            modify.write(",");

                            while (matcher37.find()) {
                                modify.write(matcher37.group());
                            }
                            modify.write(",");

                            while (matcher38.find()) {
                                modify.write(matcher38.group());
                            }
                            modify.write(",");

                            while (matcher39.find()) {
                                modify.write(matcher39.group());
                            }
                            modify.write(",");

                            while (matcher40.find()) {
                                modify.write(matcher40.group());
                            }
                            modify.write(",");

                            while (matcher41.find()) {
                                modify.write(matcher41.group());
                            }
                            modify.write(",");

                            while (matcher42.find()) {
                                modify.write(matcher42.group());
                            }
                            modify.write(",");

                            while (matcher43.find()) {
                                modify.write(matcher43.group());
                            }
                            modify.write(",");

                            while (matcher44.find()) {
                                modify.write(matcher44.group());
                            }

                            modify.write("\r\n");
                            modify.write("\r\n");
                        }

                    }
                }
            }
            modify.write("");
            modify.write(staticValue);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        DirectoryMonitor.FileMove(Paths.get("/Users/yashveerraibasgeet/Desktop/source/newfile/" + "new_" + DirectoryMonitor.listNames(List.of(fileToBeModified.toPath())).get(0)));

    }

    public static List<String> parseLine(String str) {
        Scanner sc = new Scanner(str);
        sc.useDelimiter("[|]");
        ArrayList<String> fileContent = new ArrayList<>();
        while (sc.hasNext()) {
            for (int i = 0; i <=14; i++){
                fileContent.add(sc.next());
            }
        }
        return fileContent;
    }

}
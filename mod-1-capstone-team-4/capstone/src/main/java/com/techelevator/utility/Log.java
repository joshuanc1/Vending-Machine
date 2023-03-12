package com.techelevator.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;


public class Log {

    //Parameters

    private DecimalFormat df = new DecimalFormat();
    private File logFile = new File("C:\\Users\\Student\\workspace\\pair-projects\\mod-1-capstone-team-4\\capstone\\src\\main\\java\\com\\techelevator\\utility\\Log.txt");
    private Date currentDate = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");

    //Constructor
    public Log (){}



    //Methods

    //Logs when customer feeds money
    public void logFeedMoney(double feedMoney, double totalMoney) {

        try (PrintWriter feedWriter = new PrintWriter(new FileOutputStream(logFile, true))) {
            feedWriter.println(formatter.format(currentDate) + " FEED MONEY: $" + feedMoney + " $" + totalMoney);

        } catch (FileNotFoundException fnf){
            System.out.println(fnf.getMessage());
        }

    }

    //Logs when item is sold
    public void logItemSold(String itemNameAndKey, double itemPrice, double totalItemAmount) {

        try (PrintWriter feedWriter = new PrintWriter(new FileOutputStream(logFile, true))) {
            feedWriter.println(formatter.format(currentDate)+ " " + itemNameAndKey + " $" + itemPrice + " $" + Double.valueOf(df.format(totalItemAmount)));

        } catch (FileNotFoundException fnf){
            System.out.println(fnf.getMessage());
        }

    }

    //Logs when change is returned
    public void logGiveChange(double changeToReturn) {

        try (PrintWriter feedWriter = new PrintWriter(new FileOutputStream(logFile, true))) {
            feedWriter.println(formatter.format(currentDate) + " GIVE CHANGE: " + " $" + changeToReturn + " $0.00");

        } catch (FileNotFoundException fnf){
            System.out.println(fnf.getMessage());
        }

    }



}
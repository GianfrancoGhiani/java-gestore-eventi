package org.java.esercitazione;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //imports
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome, please insert an event name");
        String eventTitle = input.nextLine();
        int sitsNum = 0;
        do{
            System.out.println("How many sits your location has?");
             sitsNum= Integer.parseInt(input.nextLine());
        } while (sitsNum < 1);
        System.out.println("When does this event be? ");
        String year = null;
        do {
            System.out.println("please insert the year with this type of format('yyyy')");
             year= input.nextLine();
        } while (year.length()<4);
        String month = null;
        do {
            System.out.println("please insert the month with this type of format('mm')");
             month= input.nextLine();
        } while (month.length()<2 && !(Integer.parseInt(month)<12) && !(Integer.parseInt(month)>0));
        String day = null;
        do {
            System.out.println("please insert the day with this type of format('dd')");
            day= input.nextLine();
        } while (day.length()<2 && !(Integer.parseInt(day)<32) && !(Integer.parseInt(day)>0));
        String completeDate = year + "-"+ month+"-"+day;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = null;
        Evento event = null;
        try{
            data = LocalDate.parse(completeDate, formatter);
            event = new Evento(eventTitle, data, sitsNum);
        }catch (DateTimeException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (event != null){
                System.out.println(event.toString());
                System.out.println("do you want to book any sit? y/n");
                String book = input.nextLine();
                if (Character.toLowerCase(book.charAt(0))=='y'){
                    System.out.println("how many people are you?");
                    int peopleNum = Integer.parseInt(input.nextLine());
                    int bookCount = 0;
                    try {
                        for (int i = 0; i < peopleNum; i++) {
                            event.prenota();
                            bookCount++;
                        }
                    } catch (FullFilledSitException e){
                        System.out.println(e.getMessage());
                        System.out.println("Do you have booked " + bookCount+ " sits, but there is no more space");
                    } finally {
                        System.out.print("Booked sits: "+event.getPostiPrenotati());
                        System.out.println(", Available sits: "+(event.getPostiTotali()-event.getPostiPrenotati()));
                    }
                }
                System.out.println("do you want to cancel your booked sits? y/n");
                String cancel = input.nextLine();
                if (Character.toLowerCase(cancel.charAt(0))=='y'){
                    System.out.println("how many sits do you want to cancel?");
                    int canceledSit = Integer.parseInt(input.nextLine());

                    try {
                        for (int i = 0; i < canceledSit; i++) {
                            event.disdici();

                        }
                    } catch (NotBookedSitException e){
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.print("Booked sits: "+event.getPostiPrenotati());
                        System.out.println(", Available sits: "+(event.getPostiTotali()-event.getPostiPrenotati()));
                    }

                }



            }
        }

        input.close();
    }
}
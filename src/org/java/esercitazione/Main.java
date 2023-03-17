package org.java.esercitazione;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //imports
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome, please insert an event name");
        String eventTitle = input.nextLine();
        String tempSitsNum = null;
//      for every value that represents an int num, always if i use it as a string,
//      i want to control that it is really a int num
        do{
            System.out.println("How many sits your location has?");
             tempSitsNum= input.nextLine();
        } while (!canBeIntParsed(tempSitsNum) || (Integer.parseInt(tempSitsNum) < 1));
//      parsing the number of total sits to int
        int sitsNum = Integer.parseInt(tempSitsNum);

        System.out.println("When does this event be? ");
//      i'll control every single value, such as year, month and day values before creating
//      the completeDate string to parse into LocalDate
        String year = null;
        do {
            System.out.println("please insert the year with this type of format('yyyy')");
             year= input.nextLine();
        } while ((year.length() != 4) || !canBeIntParsed(year));
        String month = null;
        do {
            System.out.println("please insert the month with this type of format('mm')");
             month= input.nextLine();
        } while (!canBeIntParsed(month) || (month.length()<2) || (!(Integer.parseInt(month)<13)) || (!(Integer.parseInt(month)>0)));
        String day = null;

//      changing max_date value based on month
        int max_date= 32;
        if(month.equals("04") ||month.equals("06") ||month.equals("09") ||month.equals("11")){
            max_date = 31;
        }else if(month.equals("02")) {
            max_date = 29;
        }
        do {
            System.out.println("please insert the day with this type of format('dd')");
            day= input.nextLine();
        } while (!canBeIntParsed(day) || (day.length()<2 || !(Integer.parseInt(day)<max_date) || !(Integer.parseInt(day)>0)));

        String completeDate = year + "-"+ month+"-"+day;

        //Preparing String to LocalTime conversion
        System.out.println("At what time is it?");
        String hour = null;
        int hourInt = 0;
        do {
            System.out.println("please specify the hour here (0/23)");
            hour = input.nextLine();
        }while (Integer.parseInt(hour) <0 || Integer.parseInt(hour) >23);
        String min = null;
        do {
            System.out.println("please specify the min here (0/59)");
            min = input.nextLine();
        }while (Integer.parseInt(min) <0 || Integer.parseInt(min) > 59);
        String completeTime = hour+":"+min;

        System.out.println("How much does the ticket cost?");
        String ticketPrice = input.nextLine();

        // formatters and date/time initialization
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate data = null;
        LocalTime time = null;

        //initializing event
        Evento event = null;
        try{
            //date parsing
            data = LocalDate.parse(completeDate, formatter);

//          event = new Evento(eventTitle, data, sitsNum);

            //time parsing
            time = LocalTime.parse(completeTime, timeFormatter);
            event = new Concerto(eventTitle, data, sitsNum, time , new BigDecimal(ticketPrice));




        }catch (DateTimeException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (event != null){
                System.out.println(event);
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


        //BONUS


        //import
        Random rand =new Random();
        ProgrammaEventi progr = new ProgrammaEventi("test1");
        int counter = 0;
        List<Evento> events = new ArrayList<>();

        try{
            for (int i = 0; i < 6; i++) {
                events.add(new Evento("evento num." + i, LocalDate.now().plusDays(rand.nextInt(1, 4)), 25));
                counter++;
            }
            for (Evento ev : events) {
                progr.aggiungiEvento(ev);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        } finally{
            if(counter != 0){
                LocalDate randomData = LocalDate.now().plusDays(rand.nextInt(1, 4));
                System.out.println("Lista di eventi in data "+randomData);
                System.out.println(progr.listaEventiPerData(randomData));
                System.out.println(progr.contatoreEventi());
                System.out.println(progr.ordinaPerData());
                progr.svuotaEventi();
                System.out.println(progr);
            }

        }
    }
    public static boolean canBeIntParsed(String n){
        try {
            Integer.parseInt(n);
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("You must insert an int");
            return false;
        }

    }
}
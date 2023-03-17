package org.java.esercitazione;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = null;
        Evento event = null;
        try{
            data = LocalDate.parse("2023-03-23", formatter);
            event = new Evento("concerto", data, 12);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (event != null){
                System.out.println(event.toString());
                System.out.println(event.getPostiTotali());
                System.out.println(event.getPostiPrenotati());
                event.prenota();
                event.prenota();
                event.prenota();
                System.out.println(event.getPostiPrenotati());
                event.disdici();
                event.disdici();
                System.out.println(event.getPostiPrenotati());

            }
        }


    }
}
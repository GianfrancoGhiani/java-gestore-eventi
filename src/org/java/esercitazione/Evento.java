package org.java.esercitazione;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Evento {
    //variables
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    //constructor
    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalArgumentException, DateTimeException, ParseException {
        if(titolo.isBlank()){
            throw new IllegalArgumentException("The event must have a title");
        }
        if (postiTotali <= 0){
            throw new IllegalArgumentException("The location must have at least one sit");
        }
        if (data.isBefore(LocalDate.now())){
            throw new DateTimeException("The event must be today or later");
        }
        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }
    //getter and setter

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo)  throws IllegalArgumentException{
        if(titolo.isBlank()){
            throw new IllegalArgumentException("The event must have a title");
        }
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws DateTimeException{
        if (data.isBefore(LocalDate.now())){
            throw new DateTimeException("The event must be after today");
        }
        this.data = data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    //methods
    //throws a FullFilledSitException if the booked sits are equals to the total sits or a DateTimeException if the event was before today

    public void prenota() throws FullFilledSitException, DateTimeException {
        if (data.isBefore(LocalDate.now())){
            throw new DateTimeException("The event must be after today");
        }
        if (postiTotali == postiPrenotati){
            throw new FullFilledSitException("The location is filled, try with another event");
        }else {
            postiPrenotati++;
        }
    }
    //throws a NotBookedSitException if the booked sits are equals to 0 or a DateTimeException if the event was before today
    public void disdici() throws NotBookedSitException, DateTimeException {
        if (data.isBefore(LocalDate.now())){
            throw new DateTimeException("The event must be after today");
        }
        if (postiPrenotati == 0){
            throw new NotBookedSitException("There is no booked sit");
        }else {
            postiPrenotati--;
        }
    }

    //override
    @Override
    public String toString() {
        return
                "Evento:" + data +
                "- '" + titolo + '\'';
    }
}

package org.java.esercitazione;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento{
    //import
    DecimalFormat decForm = new DecimalFormat("##.##€");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private LocalTime orario;
    private BigDecimal prezzo;

    //constructor
    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime orario, BigDecimal prezzo) throws IllegalArgumentException, DateTimeException, ParseException {

        super(titolo, data, postiTotali);
        if (prezzo.compareTo(new BigDecimal("0")) != 1){
            throw new IllegalArgumentException("The price will be greater than 0");
        }
        this.orario = orario;
        this.prezzo = new BigDecimal(prezzo.toString());
    }
    //getter and setter

    public LocalTime getOrario() {
        return orario;
    }

    public void setOrario(LocalTime orario) {
        this.orario = orario;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) throws IllegalArgumentException{
        if (prezzo.compareTo(new BigDecimal("0")) != 1){
            throw new IllegalArgumentException("The price will be greater than 0");
        }
        this.prezzo = new BigDecimal(prezzo.toString());

    }
    public String getFormattedDateTime(){
        return super.getData() + " " +getOrario().format(timeFormatter);
    }
    public String getFormattedPrice(){
        return decForm.format(getPrezzo());
    }
    //override


    @Override
    public String toString() {
        return
                getFormattedDateTime()  +" - " + super.getTitolo()  +" - " + getFormattedPrice();
    }
}

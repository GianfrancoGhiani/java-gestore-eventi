package org.java.esercitazione;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Evento {
//    La consegna è di creare una classe Evento che abbia le seguenti proprietà:
//      ● titolo
//      ● data
//      ● numero di posti in totale (capienza della location)
//      ● numero di posti prenotati
//    Quando si istanzia un nuovo evento questi attributi devono essere tutti valorizzati nel
//    costruttore, tranne posti prenotati che va inizializzato a 0.
//    Inserire il controllo che la data non sia già passata e che il numero di posti totali sia positivo.
//    In caso contrario sollevare opportune eccezioni.
//    Aggiungere metodi getter e setter in modo che:
//        ● titolo sia in lettura e in scrittura
//        ● data sia in lettura e scrittura
//        ● numero di posti totale sia solo in lettura
//        ● numero di posti prenotati sia solo in lettura
//    Vanno inoltre implementati dei metodi public che svolgono le seguenti funzioni:
//        1. prenota: aggiunge uno ai posti prenotati. Se l’evento è già passato o non ha posti
//              disponibili deve sollevare un’eccezione.
//        2. disdici: riduce di uno i posti prenotati. Se l’evento è già passato o non ci sono
//              prenotazioni deve sollevare un’eccezione.
//        3. l’override del metodo toString() in modo che venga restituita una stringa
//              contenente: data formattata - titolo
//    Aggiungere eventuali metodi (public e private) che vi aiutino a svolgere le funzioni richieste.
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
            throw new DateTimeException("The event must be after today");
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

    @Override
    public String toString() {
        return
                "Evento:" + data +
                "- '" + titolo + '\'';
    }
}

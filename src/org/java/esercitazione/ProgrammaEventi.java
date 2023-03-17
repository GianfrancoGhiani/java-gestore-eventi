package org.java.esercitazione;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProgrammaEventi {
    private String titolo;
    private List<Evento> eventi;

    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    public void aggiungiEvento(String titolo, LocalDate data, int postiTotali){
        Evento tempEvent =null;
        try{
            tempEvent = new Evento(titolo, data, postiTotali);
        }catch (ParseException e){
            e.getMessage();
        }
        if (tempEvent != null) {
            eventi.add(tempEvent);
        }
    }
    public List<Evento> listaEventiPerData(LocalDate data){
        List<Evento> lista = new ArrayList<>();
        for (Evento event: eventi) {
            if(event.getData().equals(data)){
                lista.add(event);
            }
        }
        return lista;
    }
    public int contatoreEventi(){
        return eventi.size();
    }
    public void svuotaEventi(){
        eventi.clear();
    }
    public String ordinaPerData(){
        List<Evento> tempList = new ArrayList<>();
        tempList.addAll(eventi);
        Collections.sort(tempList, new Comparator<Evento>() {
            @Override
            public int compare(Evento o1, Evento o2) {
                if(o1.getData() == null || o2.getData() == null){
                    return 0;
                }
                return o1.getData().compareTo(o2.getData());
            }
        });
        String eventsString = "";
        for (Evento event : tempList) {
            eventsString += event.toString();
        }
        return "Titolo: " + titolo+", i suoi eventi, ordinati per data sono: "+
                eventsString
                ;
    }

    @Override
    public String toString() {
        return "ProgrammaEventi{" +
                "titolo='" + titolo + '\'' +
                ", eventi=" + eventi +
                '}';
    }
}

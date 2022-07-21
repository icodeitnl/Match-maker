package model;

import java.time.LocalDate;

public abstract class Session implements Comparable<Session>{
    // class attributes shared by all instances
    final static double MAX_DUUR = 4;
    final double KOSTEN_OPEN_SESSION = 12;
    final double OPNAME_KOSTEN = 1.5;
    protected Muzikant organisator;
    protected LocalDate datum;
    protected double duur;
    protected boolean opgenomen;


    public Session(Muzikant organisator, LocalDate datum, double duur, boolean opgenomen){

        this.organisator = organisator;
        this.datum = datum;
        this.duur = duur;
        this.setOpgenomen(opgenomen);
    }
    public abstract double berekenKosten();

    // de methode compareTo()de sessions op datum sorteert.

    public int compareTo(Session andereSession){
        return this.getDatum().compareTo(andereSession.getDatum());
    }

    @Override
    public String toString(){
        if (isOpgenomen()) {
            return String.format("Session op %s van %s uur \n\tOrganizator: %s", getDatum(), getDuur(), getOrganisator());
        }else {
            return String.format("Session op %s van %s uur (geen opname)\n\tOrganizator: %s", getDatum(), getDuur(), getOrganisator());
        }
    }
    public double getDuur() {
        return duur;
    }
    // 2. Een session mag maximaal 4 uur duren. Geef een foutmelding als een session van meer dan 4 uur wordt ingevoerd
    // en zet de duur van deze session op de standaard duur van 1 uur.
    public void setDuur(double duur) {

        if (duur > MAX_DUUR){
            System.out.println("Waarschuwing! Sessies langer dan 4 uur niet mogelijk, standaard waarde van 1 uur gebruikt.");
            duur = 1;
        }
        this.duur = duur;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Muzikant getOrganisator() {
        return organisator;
    }

    public void setOrganisator(Muzikant organisator) {
        this.organisator = organisator;
    }

    public boolean isOpgenomen() {
        return opgenomen;
    }

    public void setOpgenomen(boolean opgenomen) {
        this.opgenomen = opgenomen;
    }
}

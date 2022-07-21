package model;

import java.time.LocalDate;

public class GeslotenSession extends Session {

    private int aantalMuzikanten;

    public GeslotenSession(Muzikant organisator, LocalDate datum,double duur, boolean opgenomen, int aantalMuzikanten){
        super(organisator, datum, duur, opgenomen);
        final int MAX_AANTAL_MUZIKANTEN = 10;
        final  int MIN_AANTAL_DEELNEMERS = 1;
        if (aantalMuzikanten > MIN_AANTAL_DEELNEMERS && aantalMuzikanten <= MAX_AANTAL_MUZIKANTEN){
            this.setAantalMuzikanten(aantalMuzikanten);
        } else {
            this.setAantalMuzikanten(1);
            System.out.println(String.format("Waarschuwing! Ongeldig aantal muzikanten %s, standaard waarde 1 gebruikt!", aantalMuzikanten));
        }
    }
    public GeslotenSession(Muzikant organisator, int aantalMuzikanten){
        this(organisator, LocalDate.now(), 1, false, aantalMuzikanten);

    }
    @Override
    public double berekenKosten(){
    final double OPNAME_KOSTEN = 1.5;
    final  int BASIS_LENGTE = 1;
    final double SESSION_PER_PERSON = 5;
    final  double EXTRA_TIJD = 0.5;
    final  double LANGERE_SESSIE = 2.5;

        double basisbedrag = SESSION_PER_PERSON * getAantalMuzikanten();
        double kosten = 0;
        if (getDuur() > BASIS_LENGTE) {
            if (isOpgenomen()) {
            kosten = (basisbedrag + (getDuur() - BASIS_LENGTE)*LANGERE_SESSIE/EXTRA_TIJD)*OPNAME_KOSTEN;
        } else
            kosten = basisbedrag + Math.floor((getDuur() - BASIS_LENGTE)/EXTRA_TIJD)*LANGERE_SESSIE;
        }
        return kosten;
    }
    public String toString(){
        return String.format("%s\tKosten: %.2f voor %s muzikanten", super.toString(), berekenKosten(), getAantalMuzikanten());
    }

    public int getAantalMuzikanten() {
        return aantalMuzikanten;
    }

    public void setAantalMuzikanten(int aantalMuzikanten) {
        this.aantalMuzikanten = aantalMuzikanten;
    }
}
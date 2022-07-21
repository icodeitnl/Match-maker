package model;

import java.time.LocalDate;

public class OpenSession extends Session {

    private int maximumAantalMuzikanten;
    private int aantalLuisteraars;

    public OpenSession(Muzikant organisator, LocalDate datum, int maximumAantalMuzikanten,int aantalLuisteraars){
        super(organisator, datum, 3, true );

        this.maximumAantalMuzikanten = maximumAantalMuzikanten;
        this.aantalLuisteraars = aantalLuisteraars;
    }

    @Override
    public double berekenKosten(){
        double result = KOSTEN_OPEN_SESSION;
        if (opgenomen && aantalLuisteraars < 100){
             result *= OPNAME_KOSTEN;
        }
        return result;
    }

    @Override
    public String toString(){
        return String.format("%s\tMaximaal 5 muzikanten\n\tKosten: %.2f voor %s muzikanten", super.toString(), this.berekenKosten(), aantalLuisteraars);
    }
}

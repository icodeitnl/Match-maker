package model;

public class Muzikant {

    private String artistenaam;
    private int ervaring;
    private String instrument;
    
    public Muzikant (String artistenaam, int ervaring, String instrument){

        this.setArtistenaam(artistenaam);
        this.setErvaring(ervaring);
        this.setInstrument(instrument);
    }

    @Override
    public String toString() {
        return String.format(" %s op %s met %d jaar ervaring\n", getArtistenaam(), getInstrument(), getErvaring());
    }


    public String getArtistenaam() {
        return artistenaam;
    }

    public void setArtistenaam(String artistenaam) {
        this.artistenaam = artistenaam;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public int getErvaring() {
        return ervaring;
    }

    public void setErvaring(int ervaring) {
        this.ervaring = ervaring;
    }
}



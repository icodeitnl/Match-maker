package controller;

import database.*;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.*;

public class SessionLauncher {
    public static void main(String args[]){
        DBaccess dBaccess = new DBaccess("Session", "userSessionService", "pwSessionService");

        MuzikantDAO muzikantDAO = new MuzikantDAO(dBaccess);
        GeslotenSessionDAO geslotensessionDAO = new GeslotenSessionDAO(dBaccess);

        dBaccess.openConnection();
        Connection connection = dBaccess.getConnection();

        //  Class attributes were updated
        //  Database ontwerp van de tabel Muzikant afwijkent van de attributen in het klassendiagram

        System.out.println("\n/** @author  Katerina Novozhenova\n" +
                " *  Online sessions OOP\n" +
                " */");
        System.out.println();

        // Muzikant toevoegen testen
        System.out.println("-----Testing adding a new Muzikant-----");
        System.out.println();
        Muzikant muzikant1  = new Muzikant("Piet", 12, "Gitaar");
        Muzikant muzikant2  = new Muzikant("DJ Urk", 6, "Piano");
        Muzikant muzikant3 = new Muzikant("Sting", 34, "bas");
        Muzikant muzikant4 = new Muzikant("Paul Weller", 23, "gitaar");

        System.out.println("This is the new muzikant: " + muzikant4);

        // slaGeslotenSessionOp testen
        System.out.println("-----Testing adding a new Gesloten Session method-----");
        System.out.println();
        GeslotenSession GS1 = new GeslotenSession(muzikant1, LocalDate.of(2020,10,1), 5, false, 3);
        System.out.println("Dit is een Gesloten Sessie zonder opname: " + GS1);
        geslotensessionDAO.slaSessionOp(GS1);
        GeslotenSession GS2 = new GeslotenSession(muzikant2, LocalDate.of(2020,10,2), 3, true, 3);
        System.out.println("Dit is een Gesloten Sessie met opname: " + GS2);
        geslotensessionDAO.slaSessionOp(GS2);
        OpenSession OS1 = new OpenSession(muzikant3, LocalDate.now(), 80, 5);
        System.out.println("Dit is een Open Sessie: " + OS1);
        geslotensessionDAO.slaSessionOp(OS1);


        System.out.println();

        // leesGeslotenSessions() testen

        // 3. Test de methode leesGeslotenSessions() in je main methode.

        System.out.println("-----Testing the leesGeslotenSessions() method-----");
        System.out.println();
        ArrayList<GeslotenSession> geslotenSessions = leesGeslotenSessions();
        for (GeslotenSession geslotenSession:geslotenSessions) {
            System.out.println(geslotenSession);
        }
        System.out.println();

        // Agenda testen
        // 4. Maak een Agenda aan.
        // 5. Laat zien dat de exception in de methode voegSessionToe() van de klasse Agenda wordt afgehandeld
        System.out.println("-----Testing creating a new agenda method-----");
        System.out.println();
        Agenda newAgenda = new Agenda(04,2020);
        for (GeslotenSession geslotenSession: geslotenSessions) {
            try{
                newAgenda.voegSessionToe(geslotenSession);
                System.out.println("Het is gelukt! Een nieuwe agenda is gemaakt.");
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            } finally {
                System.out.println("The 'try catch' is finished.");
            }
        }
        System.out.println();
        System.out.println("------This is the new agenda: \n\n" + newAgenda);

        //
        System.out.println("------This is the new agenda where minimum amout of musicians is 4: \n" );

        newAgenda.printGeslotenSessions(4);
        System.out.println();

        // 6. Lees alle muzikanten, die in de database zitten en toon deze op het scherm.

}
    //2. Maak een methode met als signature public static
    //ArrayList<GeslotenSession> leesGeslotenSessions()in de Launcher
    public static ArrayList<GeslotenSession> leesGeslotenSessions() {
        ArrayList<GeslotenSession> geslotenSessions = new ArrayList<>();
        File file = new File("resources/GeslotenSessions.csv");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArray = line.split(",");
                //Muzikant organisator, LocalDate datum,double duur, boolean opgenomen, int aantalMuzikanten
                String artistenaam = lineArray[0];
                String instrument = lineArray[1];
                int ervaring = Integer.parseInt(lineArray[2]);
                LocalDate datum = LocalDate.parse(lineArray[3]);
                double duur = Double.parseDouble(lineArray[4]);
                boolean opgenomen = Boolean.parseBoolean(lineArray[5]);
                int aantalMuzikanten = Integer.parseInt(lineArray[6]);
                //String artistenaam, int ervaring, String instrument
                Muzikant organisator = new Muzikant(artistenaam, ervaring, instrument);
                GeslotenSession session = new GeslotenSession(organisator, datum, duur, opgenomen, aantalMuzikanten);

                geslotenSessions.add(session);
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found." + exception.getMessage());
        }
        return geslotenSessions;
    }
}
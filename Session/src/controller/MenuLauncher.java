package controller;

import database.*;
import model.*;
import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class MenuLauncher {

    private static DBaccess dBaccess = new DBaccess("Session", "userSessionService", "pwSessionService");

    public static void main(String args[]) {

        DBaccess dBaccess = new DBaccess("Session", "userSessionService", "pwSessionService");
        System.out.println("\n/** @author  Katerina Novozhenova\n" +
                " *  Online sessions Menu\n" +
                " */");
        System.out.println();
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("************************ MENU LAUNCHER *******************************");
            System.out.println();
            System.out.println("Deze applicatie kent de volgende keuzes in het Menu:\n");
            System.out.print("1. Kies een voor een nieuwe muzikant aanmaken en opslaan in de database. \n");
            System.out.print("2. Kies twee voor een nieuwe (open of gesloten) session aanmaken\n");
            System.out.print("3. Kies drie voor een overzicht tonen van alle muzikanten in de database\n");
            System.out.print("   die een op te geven muziekinstrument bespelen.\n");
            System.out.print("4. Kies vier voor Exit\n");
            System.out.println();
            System.out.println("***********************************************************************");
            System.out.print("\n Maak uw keuze: (1),(2),(3) or (4): ");
            System.out.println();
            choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nU kiest voor een nieuwe muzikant aanmaken en opslaan in de database.");
                    System.out.println();
                    printArtistsUserInput();

                    break;
                case 2:
                    System.out.println("\nU kiest voor een nieuwe session aanmaken.");
                    System.out.println();
                    printSessionUserChoice();

                    break;
                case 3:
                    System.out.println("\nU kiest voor een overzicht van alle muzikanten toonen.");
                    System.out.println();
                    printArtistsUserInputInstrument();
                    break;
                case 4:
                    System.out.println("\nExiting Program...");
                    System.out.println();
                    System.exit(0);
                    break;
                default:
                    System.out.println(choice + " is not a valid Menu Option! Please select another option.");
            }
        } while (choice != 4 /*Exit loop when choice is 4*/);
    }
    // end main

    public static void printArtistsUserInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("What is the name of the artist that you want to add? ");
        String artistenaam = "";
        artistenaam += input.nextLine();
        System.out.print("\nHow many years of experience the artist has? ");
        int ervaring = input.nextInt();
        System.out.print("\nWhat instrument is he/she playing? ");
        String instrument = input.next();
        input.nextLine();

        Muzikant artistUserInput = new Muzikant(artistenaam, ervaring, instrument);
        MuzikantDAO muzikantDAO = new MuzikantDAO(dBaccess);
        dBaccess.openConnection();
        muzikantDAO.slaMuzikantOp(artistUserInput);
        dBaccess.closeConnection();

        System.out.println("The new artist added to the database is: " + artistUserInput);
    }

    public static void printSessionUserChoice() {
        Scanner input = new Scanner(System.in);
        System.out.print("What session do you want to create: (1) Open (2) Gesloten ?\n");

        int choice = input.nextInt();
        if (choice == 1) {
            System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>");
            System.out.println("An Open Session will be created");
            System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>");
            System.out.println();
            displayArtists();
            printOpenSessionToUserInput();
        } else if (choice == 2) {
            System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>");
            System.out.println("A Gesloten Session will be created");
            System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>");
            System.out.println();
            displayArtists();
            printGeslotenSessionToUserInput();
        } else {
            System.out.println("\nChose a valid option.");
        }
    }

    public static void displayArtists() {
        MuzikantDAO muzikantDAO = new MuzikantDAO(dBaccess);
        dBaccess.openConnection();

        ArrayList<Muzikant> artistsList = muzikantDAO.getMuzikanten();
        System.out.println("\n*****************  ARTISTEN LIJST  ****************");
        System.out.println();
        System.out.println("Here is the list of the artists you may chose from:");
        System.out.println();
        System.out.println("***************************************************");
        System.out.println();
        int i = 0;
        for (Muzikant artist : artistsList) {
            System.out.println(++i + ". " + artist);
        }
        System.out.println();
        dBaccess.closeConnection();
    }

    public static void printOpenSessionToUserInput() {

        Scanner input = new Scanner(System.in);
        MuzikantDAO muzikantDAO = new MuzikantDAO(dBaccess);
        dBaccess.openConnection();
        Muzikant result = null;
        ArrayList<Muzikant> listArtists = muzikantDAO.getMuzikanten();
        while (result == null) {
            System.out.print("\nWhat is the name of the artist that you want to play with? ");
            String nameFilter = input.nextLine();
            for (Muzikant muzikant : listArtists) {
                if (muzikant.getArtistenaam().equals(nameFilter)) {
                    result = muzikant;
                    System.out.println("\nYou have selected: " + result.getArtistenaam() + " to join in Open Session.");
                }
            }
            if (result == null) {
                System.out.println("\nThis artist does not exist database");
                System.out.println();
            }
        }
        System.out.print("\nWhat is your date of choice in ISO 8601 (YYYY-MM-DD) notation? ");
        System.out.println();
        LocalDate datumSession = LocalDate.parse(input.next());
        System.out.print("\nHow many attendees are you expecting? ");
        System.out.println();
        int luisteraars = input.nextInt();
        System.out.print("\nHow many artists will be participating? ");
        System.out.println();
        int maxMuzikanten = input.nextInt();
        Session userOS = new OpenSession(result, datumSession, luisteraars, maxMuzikanten);
        System.out.println("\nSuccess! Your Open Session has been created.");
        System.out.println(userOS);
        dBaccess.closeConnection();
    }

    public static void printGeslotenSessionToUserInput() {
        Scanner input = new Scanner(System.in);
        MuzikantDAO muzikantDAO = new MuzikantDAO(dBaccess);
        dBaccess.openConnection();

        Muzikant result = null;
        ArrayList<Muzikant> listArtists = muzikantDAO.getMuzikanten();

        while (result == null) {
            System.out.print("\nWhat is the name of the artist that you want to play with? ");
            System.out.println();
            String nameArtist = input.nextLine();

            for (Muzikant muzikant : listArtists) {
                if (muzikant.getArtistenaam().equals(nameArtist)) {
                    result = muzikant;
                }
            }

            if (result == null) {
                System.out.println("\nThis artist is not in our database");
            }
        }

        System.out.println("\nYou have selected: " + result.getArtistenaam() + " to play with.");
        System.out.print("\nWhat is your date of choice in ISO 8601 (YYYY-MM-DD) notation? ");
        System.out.println();
        LocalDate datumSession = LocalDate.parse(input.next());

        System.out.print("\nHow long will the session last? ");
        double duur = input.nextDouble();
        System.out.printf("\nWill there be a recording: (1) Yes (2) No?");
        System.out.println();
        int record = input.nextInt();
        boolean opgenomen = false;
        while (opgenomen) {
            if (record == 1) {
                opgenomen = true;
            } else if (record == 2) {
                opgenomen = false;
            } else {
                System.out.println("\nChose 1 if the session will be recorded, chose 2 if the session will not be recorded");
                System.out.printf("\nWill there be a recording: (1) Yes (2) No?");
                record = input.nextInt();
            }
        }
        System.out.print("\nHow many musicians are allowed to join?");
        System.out.println();
        int aantalMuzikanten = input.nextInt();
        Session userGS = new GeslotenSession(result, datumSession, duur, opgenomen, aantalMuzikanten);
        System.out.println("\nSuccess!Your Gesloten Session has been created.");
        System.out.println(userGS);
        dBaccess.closeConnection();
    }

    public static void printArtistsUserInputInstrument() {
        MuzikantDAO muzikantDAO = new MuzikantDAO(dBaccess);
        dBaccess.openConnection();
        Scanner input = new Scanner(System.in);
        System.out.print("\nWhat instrument are you looking for? ");
        System.out.println();
        String instrument = input.next();
        ArrayList<Muzikant> listArtists = muzikantDAO.getMuzikantByInstrument(instrument);
        for (Muzikant muzikant : listArtists) {
            System.out.println("The artists that plays " + instrument + " is " + muzikant);
        }
        System.out.println();
        dBaccess.closeConnection();
    }

    //  Method named "readArtist" of the class Muzikant reserves space for the instance named "organisator"
    //  that returns and assigns value of naam,ervaring,instrument to each new row (and adds a generated by DB unique PK ID auto increment
    //  from CSV
    public static Muzikant readArtist (String[] rowArtist) {
        String naam = rowArtist[0];
        int ervaring = Integer.parseInt(rowArtist[1]);
        String instrument = rowArtist[2];
        Muzikant organisator = new Muzikant(naam, ervaring, instrument);
        return organisator;
    }

    // Read Gesloten Session from CSV
    public static GeslotenSession readGS(String[] rowGS, Muzikant organisator) {
        LocalDate datum = LocalDate.parse(rowGS[3]);
        double duur = Double.parseDouble(rowGS[4]);
        boolean opgenomen = Boolean.parseBoolean(rowGS[5]);
        int aantalMuzikanten = Integer.parseInt(rowGS[6]);
        GeslotenSession sessionG = new GeslotenSession(organisator, datum, duur, opgenomen, aantalMuzikanten);
        return sessionG;
    }

    // Merge GS with Artist on Muzikant "organisator"
    public static GeslotenSession mergeGS(Scanner scanner) {
        String merge = scanner.nextLine(); // elke regel is een String genaamd line
        String[] mergeRows = merge.split(","); // elke regel wordt gezet in een String array die alles splitst na komma

        Muzikant organisator = readArtist(mergeRows);
        GeslotenSession mergedGSession = readGS(mergeRows, organisator);


        return mergedGSession;
    }
    // Read CSV file into ArrayList
    public static ArrayList<GeslotenSession> arrayOfGS() {
        ArrayList<GeslotenSession> gS = new ArrayList<>();
        File file = new File("resources/GeslotenSessions.csv");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                GeslotenSession sessionOverload = mergeGS (scanner);
                gS.add(sessionOverload);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Bestand niet aanwezig.");
        }
        return gS;
    }
}


package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Agenda  {
    private ArrayList<Session> sessions;
    private int maand;
    private int jaar;

    public Agenda(int maand, int jaar) {
        sessions = new ArrayList<>();
        this.maand = maand;
        this.jaar = jaar;
    }

    public void voegSessionToe(Session session) throws IllegalArgumentException  {
        if ((session.getDatum().getYear() == this.jaar) && (session.getDatum().getMonthValue() == this.maand)) {
           getSessions().add(session);
        } else {
            throw new IllegalArgumentException("Deze session wordt niet toegevoegd, verkeerde maand en/of jaar!");
        }
    }
    //    2. Alle gesloten sessions met minimaal 4 muzikanten kunnen printen naar de console.

    public void printGeslotenSessions (int minimumAantalMuzikanten) {
        Collections.sort(sessions);
        for (Session session: getSessions()) {
            if(session instanceof GeslotenSession ){
                if (((GeslotenSession)session).getAantalMuzikanten() >=minimumAantalMuzikanten) {
                System.out.println(session);

                }
            }
        }
    }

    // 3. Een overzicht van de georganiseerde sessions. Sla op in de folder resources van je project.
    public void maakSessionsBestand(Muzikant muzikant) {
        Collections.sort(sessions);
        try {
            File file = new File("resources/MuzikantSessions.txt");
            PrintWriter printer = new PrintWriter(file);

            for (Session session : sessions) {
                if (session.getOrganisator().equals(muzikant)) {
                    printer.println(session);
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("SQL Exception: " + exception.getMessage());
        }
    }

      @Override
      public String toString() {
          StringBuilder result = new StringBuilder();
          Collections.sort(getSessions());
          for (Session session: getSessions()) {
              result.append(session + "\n");
          }
          return result.toString();
      }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
}

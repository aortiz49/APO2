package datosEstudiantes;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Processing {

    private ArrayList<Event> eventos;

    public Processing() {
        eventos = new ArrayList<Event>();
    }

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Array of lines.
     */
    private ArrayList<String> lines;

    /**
     * Array of events.
     */
    private ArrayList<Event> events;


    public void loadEvents(String pRutaArchivo)
            throws IOException, DateFormatException, ParseException {
        File file = new File(pRutaArchivo);
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Write the lines to an arraylist.
        lines = new ArrayList<>();

        String line;
        String id;
        String activity;
        String time;
        Date date;
        events = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            id = line.substring(0, line.indexOf("\t"));
            activity = line.substring(line.indexOf("\t"), line.lastIndexOf("\t")).trim();
            time = line.substring(line.lastIndexOf("\t")).trim();


            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                date = dateFormat.parse(time);
                System.out.println();
            } catch (ParseException e) {
                SimpleDateFormat otherFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = otherFormat.parse(time);
                System.out.println(date.toString());
            }
            events.add(new Event(id, activity, date));

        }

    }


    public void generateStudentReport(String pID) throws Exception {

        boolean found = false;
        ArrayList<String> eventList = new ArrayList<>();
        ArrayList<Date> dateList = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {

            if (events.get(i).getIdEstudiante().equals(pID)) {
                eventList.add(events.get(i).getNomActividad());
                dateList.add(events.get(i).getFecha());
                found = true;
            }

        }
        if(!found) {
            throw new Exception("Id not found!");
        }


        PrintWriter pw =
                new PrintWriter(new FileWriter(new File("./data/out/report+<" + pID + ">.txt")));

        pw.println("Activities done by id" + pID);
        pw.println("ACTIVITY - DATE");

        String month;
        String day;
        String time;
        String year;

        for (int i = 0; i < eventList.size(); i++) {
            String[] tokens = dateList.get(i).toString().split(" ");
            month = Integer.toString(dateList.get(0).getMonth() + 1);
            day = tokens[2];
            time = tokens[3];
            year = tokens[5];

            pw.println(eventList.get(i) + " - " + "The " + day + "/" + month + "/" + year + " at "
                               + time);
        }
        pw.println("-------------------------------------------"
                           + "--------------------------------------------");
        pw.println("TOTAL EVENTS: " + eventList.size());


        pw.close();

    }


    public static void main(String[] args) {

        Processing procesador = new Processing();


        try {
            procesador.loadEvents("./data/in/rawDataStudents.txt");

            System.out.println("Enter a student's id number: ");
            Scanner input = new Scanner(System.in);

            String id = input.nextLine();
            procesador.generateStudentReport(id);

            System.out.println("DONE!");

            BufferedReader br = new BufferedReader(new FileReader("./data/out/report+<" + id + ">"
                                                                          + ".txt"));
            String line;
            while((line = br.readLine())!=null) {
                System.out.println(line);
            }




        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

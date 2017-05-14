
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hadida
 */
public class AthanNotifier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {

        Document doc = null;

        //creates a doc, sets a user
        doc = Jsoup.connect("http://www.kitchenermasjid.com/").userAgent("Mozilla/17.0").get();

//            String title = doc.title();
        Element body = doc.body();
        Elements links = null;
        String deal = null;
        

        //Access calender
        Calendar cal = Calendar.getInstance();
        //sets format (Hours, minutes, seconds)
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        System.out.println("Time right now  " + sdf.format(cal.getTime()));

        String currentTime = sdf.format(cal.getTime());

        String converter = body.text();
        ArrayList<String> times = new ArrayList<String>();
        Set<String> athanTimes = new HashSet<String>();
        String[] allLinks = {"td.odd", "td.even", "tr.even"};
//            String[] checker = {"Fajr", "Asr", "Zhur","Maghrib", "Isha"};
        ArrayList<String> checker = new ArrayList<String>();
        checker.add("Fajr");
        checker.add("Zhur");
        checker.add("Asr");
        checker.add("Maghrib");
        checker.add("Isha");

//            Elements links2 = doc.select("td.even");
//            Elements links3 = doc.select("tr.even");
        //since I declared all links as an array, I select the entire array
        for (int i = 0; i < allLinks.length; i++) {
            links = doc.select(allLinks[i]);

            for (Element athan : links) {

                String text = athan.getAllElements().text();
                int uptill = text.indexOf(' ');

                //iff first character is not a letter.
                if (uptill > 0 && !Character.isLetter(text.charAt(0))) {
                    deal = text.substring(0, uptill);
//                   int number = Integer.parseInt(text);

//                     System.out.println(text);
                    times.add(text);

                } else {
//                     System.out.println(text);

                }

                if (checker.contains(deal)) {
//                    System.out.println("What");

                }

//                int x = Integer.parseInt(text);
//                System.out.println(x);
//                System.out.println(text);
            }
        }

//            System.out.println(links2.text());
//            System.out.println(links3.text());
        Elements text = doc.select(allLinks[allLinks.length - 1]);

        StringBuilder test = new StringBuilder(text.text());
        int uptill = test.indexOf(" ");
        test.delete(0, uptill + 1);
//        System.out.println(test);
        test.delete(0, test.indexOf("PM") + 3);
        //convert to word and add
        times.add(test.toString());

        //since as for loop progresses it updates the size .
        int fixedPos = times.size();
        
        //remove odd ones out that we do not need (only mosque needs)
      
        
  
         
            for (int i = 0; i < times.size(); i++) {
              
            if(i%2 == 1){
                times.set(i, "0");
            }
            
        }
        
            //removes them
           for (int i = 0; i < times.size(); i++) {
            times.remove("0");
        }
        
//       times.remove(3);
//       times.remove(times.size()-1);
        
        for (int i = 0; i < times.size(); i++) {

            System.out.println(times.get(i));
            String convert = timeConverter(times.get(i));

            String answer = timeCalculator(currentTime, convert);

        }

    }

    public static String timeCalculator(String currentTime, String athanTime) throws ParseException {

        String time1 = "16:00:50";
        String time2 = "19:00:00";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = format.parse(currentTime);
        Date date2 = format.parse(athanTime);
        long diff = date2.getTime() - date1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;

        System.out.print(diffHours + " hours, ");
        System.out.print(diffMinutes + " minutes, ");
        System.out.print(diffSeconds + " seconds.");

        String timeLeft = diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds + " seconds.";

        return timeLeft;

    }

    public static String timeConverter(String time) throws ParseException {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = parseFormat.parse(time);
        System.out.println(parseFormat.format(date) + " = " + displayFormat.format(date));
//       int timeConverted = Integer.parseInt(displayFormat.format(date));

        return displayFormat.format(date);
    }

}

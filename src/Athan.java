

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
public class Athan extends javax.swing.JFrame {

    /**
     * Creates new form Athan
     */
    public Athan() {
        initComponents();
    }

    private String timer;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        textpane = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textpane.setEditable(false);
        textpane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textpaneMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(textpane);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel1.setText("Times Until Next Athan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textpaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textpaneMouseClicked
        // TODO add your handling code here:
         textpane.setText(timer);
    }//GEN-LAST:event_textpaneMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException, ParseException, InterruptedException {
        /* Set the Nimbus look and feel */
        
          /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Athan().setVisible(true);
                Athan timer = new Athan();
                
                
            }
        });
        while(true){
               Document doc = null;
        AthanNotifier run = new AthanNotifier();

        //creates a doc, sets a user
        doc = Jsoup.connect("http://www.kitchenermasjid.com/").userAgent("Mozilla/17.0").get();

//            String title = doc.title();
        Element body = doc.body();
        Elements links = null;
        String deal = null;

        int marker = 0;

        //Access calender
        Calendar cal = Calendar.getInstance();
        //sets format (Hours, minutes, seconds)
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        System.out.println("Time right now  " + sdf.format(cal.getTime()));

        String currentTime = sdf.format(cal.getTime());
//            System.out.println(currentTime);

        String converter = body.text();
        ArrayList<String> times = new ArrayList<String>();
        ArrayList<String> realTimes = new ArrayList<String>();
        ArrayList<Integer> closestTime = new ArrayList<Integer>();
        String[] allLinks = {"td.odd", "td.even", "tr.even"};

        ArrayList<String> checker = new ArrayList<String>();
        checker.add("Fajr");
        checker.add("Zhur");
        checker.add("Asr");
        checker.add("Maghrib");
        checker.add("Isha");

        //since I declared all links as an array, I select the entire array
        for (int i = 0; i < allLinks.length; i++) {
            links = doc.select(allLinks[i]);

            for (Element athan : links) {

                String text = athan.getAllElements().text();
                int uptill = text.indexOf(' ');

                //iff first character is not a letter.
                if (uptill > 0 && !Character.isLetter(text.charAt(0))) {
                    

                    times.add(text);

                }
                
                

            }
        }

        Elements text = doc.select(allLinks[allLinks.length - 1]);

        StringBuilder test = new StringBuilder(text.text());
        int uptill = test.indexOf(" ");
        test.delete(0, uptill + 1);

        test.delete(0, test.indexOf("PM") + 3);
        //convert to word and add
        times.add(test.toString());

     

        //remove odd ones out that we do not need (only mosque needs)
        for (int i = 0; i < times.size(); i++) {

            if (i % 2 == 1 || i == 8) {
                times.set(i, "0");
            }
          

        }
        

        //removes them
        for (int i = 0; i < times.size(); i++) {
                times.remove("0");
        }
        
        //calls the methods 
        for (int i = 0; i < times.size(); i++) {

            String convert = timeConverter(times.get(i));

//            Thread.sleep(1500);
            String answer = timeCalculator(currentTime, convert, closestTime);

            System.out.println(answer);
            realTimes.add(answer);

        }
        

        Collections.sort(closestTime);
        for (int i = 0; i < closestTime.size(); i++) {
            if (closestTime.get(i) < 0) {
                closestTime.set(i,0);

                //updates latest spot of when it is 25
                marker = i;
            }

        }


        for (int i = 0; i <= marker; i++) {
            closestTime.remove(0);

        }

        
            System.out.println("Sorted . .. . . . .. ");

            for (int i = 0; i < closestTime.size(); i++) {
                System.out.println(closestTime.get(i));
            }
        
        
        for (int i = 0; i < realTimes.size(); i++) {
            int upuntill = realTimes.get(i).indexOf(" ");

            if (closestTime.get(0) == Integer.parseInt(realTimes.get(i).substring(0, upuntill))) {

//                Thread.sleep(1000);
                System.out.println("The next prayer is in " + realTimes.get(i));
                 
               
               
            }
        }
}
    }
        
        

  
    
    
    
       public static String timeCalculator(String currentTime, String athanTime, ArrayList<Integer> closestTime) throws ParseException {

           
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = format.parse(currentTime);
        Date date2 = format.parse(athanTime);
        long diff = date2.getTime() - date1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        
          

        String timeLeft = diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds + " seconds.";

        
//           System.out.println("My dreaming ends " + (int)diffHours);
        closestTime.add((int) diffHours);
        

       

        return timeLeft;

    }

    public static String timeConverter(String time) throws ParseException {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = parseFormat.parse(time);
//        System.out.println(parseFormat.format(date) + " = " + displayFormat.format(date));
//       int timeConverted = Integer.parseInt(displayFormat.format(date));

        
        return displayFormat.format(date);
    }
    
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane textpane;
    // End of variables declaration//GEN-END:variables
}
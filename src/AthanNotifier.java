
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    
    
    
    public static void main(String[] args) {
      
        Document doc = null;
        
        try{
            //creates a doc, sets a user
            doc = Jsoup.connect("http://www.kitchenermasjid.com/").userAgent("Mozilla/17.0").get();
            
//            String title = doc.title();
            Element body =  doc.body();
            
            
            //Access calender
            Calendar cal = Calendar.getInstance();
            //sets format (Hours, minutes, seconds)
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            System.out.println( "Time right now  " + sdf.format(cal.getTime()) );
            
            String converter  = body.text();
            
            Elements links = doc.select("td.odd");
            Elements links2 = doc.select("td.even");
            Elements links3 = doc.select("tr.even");
            
            
            for (Element athan: links) {
                
            }
            
            System.out.println(links.text() + " " + links2.text() + " " + links3.text());
            
        }catch(IOException e){
            
        }
        
       
        
        
        
    }
    
    
    public static int timeConverter(int time){
        return 0;
    }
    
}

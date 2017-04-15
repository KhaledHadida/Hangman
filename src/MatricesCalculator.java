/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author hadik9595
 */
public class MatricesCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean done = false;
          int temp = 0;
        
        int counter = 0;
        Scanner in = new Scanner(System.in);
        int[] numbers = new int[12];
        numbers[0] = 2;
        numbers[1] = 6;
        numbers[2] = 7;
        numbers[3] = 9;
        numbers[4] = 2;
        numbers[5] = 5;
        numbers[6] = 1;
        numbers[7] = 1;
        numbers[8] = 8;
        numbers[9] = 4;
        numbers[10] = 7;
        numbers[11] = 4;
        
        //asks user to input numbers
        System.out.println("Enter your components/coordinates, left to right (including after the |)");
//        for (int i = 0; i < numbers.length; i++) {
//            numbers[i] = in.nextInt();
//        }
//        
        
        
        
        
        //print out answers, in reduced echelon form
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(numbers[counter]);
                System.out.print(" ");
                  counter++;
            }
            System.out.print(" | ");
            

           
                 System.out.print(numbers[counter]);          
                  counter++;
           
          
            System.out.println("");
        }
        
        while(!done){
           
            //take care of first coloum 
            for (int i = 0; i < 9; i =  i+4) {
               
                 if(numbers[temp] %numbers[i] == 0){
              System.out.println("well");
             
             
                     System.out.println("i is " + i + " temp is " + temp );
              
          }
                
                 
                   
            }
         
            
        }
        //display answer 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(numbers[counter]);
                System.out.print(" ");
                  counter++;
            }
            System.out.print(" | ");
            

           
                 System.out.print(numbers[counter]);          
                  counter++;
           
          
            System.out.println("");
        }
        
        
        
        //process of conversion to 
        
        
        
        
        
        
        
    }
}

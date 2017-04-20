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
        
        //procedure to solve any reduced row echelon form
        
        // STEP 1: if first number != 1, multiply it by its reciprocal
        // STEP 2 : Add X times FIRST ROW to the SECOND ROW (X Being multiple of second row's first number that is opposite [+,-]) 
        
        
        
        
        
        
        
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
//        for (int i = 0; i < 11; i = i + 3) {
//            for (int j = 0; j < 3; j++) {
//                System.out.print(numbers[j]);
//                System.out.print(" ");
//                  counter++;
//            }
//            System.out.print(" | ");
//            
//
//           
//                 System.out.print(numbers[i]);          
//                  counter++;
//           
//          
//            System.out.println("");
//        }
        
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
            System.out.print(" ");
            
            if(i == 2 || i == 5 || i == 10){
                System.out.print(" | ");
            }
        }
        
        while(!done){
           
            //take care of first coloum 
            for (int g = 0; g < 9; g = g+4) {
                 for (int i = 0; i < 9; i =  i+4) {
               
                 if(numbers[g] %numbers[i] == 0){
            
               System.out.println("Yes it tacos!");
             
                    
              
          }
               
                
            
            }
            }
           
         done = true;
            
        }
        //display answer 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(numbers[counter]);
                System.out.print(" ");
                
            }
            System.out.print(" | ");
            

           
                 System.out.print(numbers[counter]);          
                  
           
          
            System.out.println("");
        }
        
        
        
        //process of conversion to 
        
        
        
        
        
        
        
    }
}

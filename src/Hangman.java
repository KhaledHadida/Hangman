/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author hadik9595
 */
public class Hangman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //scanner for input
        Scanner in = new Scanner(System.in);

        //Brief explanation to my counter strategy
        //User inputs "hello" as the word, other player inputs 'h'
        //since we instructed it to decrease by 1 when a letter within the word matches the guess
        //at the same time increase it by 1 for everytime the loop is running (up to word length)
        //this means the counter at the end of the for loop would be less than the length
        //therefore, will never surpass the length of the word.
        int counter = 1;
        //lives for player
        int lives = 6;
        //a integer used to count everytime user gets 1 letter correct
        int correct = 0;
        //checks if game is done
        boolean isFinished = false;



        //asks user to input word
        System.out.println("Enter a word");
        //stores word that will be converted to dashes
        String word = in.nextLine();

        //stores the word that is in letters
        String realWord = word;
        //clears screen
        for (int i = 0; i < 25; i++) {
            System.out.println("");
        }

        //Clears the word, converts all letters to '-'
        for (int i = 0; i < word.length(); i++) {
            String temp = word.substring(i, i + 1);
            char temp1 = temp.charAt(0);

            word = word.replace(temp1, '-');

        }

        //Stringbuilder is initialized, easier to manipulate than string
        StringBuilder myName = new StringBuilder(word);

        //a loop keeping the game running, as long as lives > 0 and game is not finished
        while (lives > 0 && !isFinished) {

            //keeps the player updated. and asks for a letter
            System.out.println("Player 2: You have " + lives + " lives left. Guess a letter:");
            //stores the user input
            String letter = in.nextLine();
            //if user inputs more than 1 letter, it takes first one letter it sees
            char guessedLetter = letter.charAt(0);

            //for loop that goes through each letter in the word
            for (int i = 0; i < word.length();) {
                //stores letter in char
                char selectedLetter = realWord.charAt(i);
                
                //if the guess is correct
                if (selectedLetter == guessedLetter) {


                    //sets the letter in that position 'i' as the guessed letter.
                    myName.setCharAt(i, guessedLetter);

                    //decreases counter
                    counter--;
                    //increases number of correct letters 
                    correct++;
                }

                //increases counter
                counter++;

                //if the user inputs a wrong letter
                if (counter > word.length()) {
                    //decrease number of lives 
                    lives--;
                    //output to user amount of lives left
                    System.out.println("Wrong, you now have " + lives + " lives left");

                }
                //increases i to move on to the next letter in the word.
                i++;
            }
            
            //for loop used to print out all letters in the hidden word
            for (int i = 0; i < word.length(); i++) {
                System.out.print(myName.charAt(i) + " ");

            }
            System.out.println("");

            //sets counter back to 1 at the end of every cycle
            counter = 1;

            //if the player guesses all the right letters, toggle game finished
            if (correct == word.length()) {
                isFinished = true;
            }

        }

        //if player runs out of lives, game over.
        if (lives == 0) {
            System.out.println("Looks like you're out of lives, Game over.");
            System.out.println("The word was '" + realWord + "' ");
        } else {
            //otherwise they win
            System.out.println("Congratulations, you've won!");
        }

    }
}

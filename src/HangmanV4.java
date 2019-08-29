import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanV4 {
    String phrase;
    StringBuilder hiddenPhrase=new StringBuilder();
    ArrayList<Character> previousGuesses=new ArrayList<>();
    ArrayList<Character> preMisses=new ArrayList<>();
    ArrayList<Character> preCorrect=new ArrayList<>();

    //get a random Phrase
    String randomPhrase(String filename){
        List<String> phraseList=null;
        try{
            phraseList= Files.readAllLines(Paths.get(filename));
        }catch (IOException e){
            System.out.println(e);
        }
        Random random=new Random();
        int i1=random.nextInt(phraseList.size());
        this.phrase=phraseList.get(i1);
        return this.phrase;
    }

    //return initial hiddenPhrase
    StringBuilder generateHiddenPhrase(){
        for(int j=0;j<this.phrase.length();j++){
            if(Character.isLetter(this.phrase.charAt(j))==true){
                this.hiddenPhrase.append('*');
            }else{
                this.hiddenPhrase.append(this.phrase.charAt(j));
            }
        }
        return hiddenPhrase;
    }

    //get player's input
    char playerGuess(Scanner scanner){
        // ask player to only input one letter
        String str1="";
        while(str1.length()!=1){
            System.out.println("Please guess a letter: ");
            str1=scanner.next();
        }
        char guess=str1.charAt(0);
        return guess;
    }

    //returns whether a letter matches and modifies the partially hidden phrase, and modifies the hidden phrase if there is a match.
    boolean processGuess(String lowPhrase, char lowGuess){
        this.previousGuesses.add(lowGuess);
        if(lowPhrase.indexOf(lowGuess)!=-1){
            System.out.println("Yes, you are right.");
            this.preCorrect.add(lowGuess);
            for(int i=0;i<lowPhrase.length();i++){
                if(lowGuess==lowPhrase.charAt(i)) {
                    this.hiddenPhrase.setCharAt(i, this.phrase.charAt(i));
                }
            }
            return true;
        }else{
            this.preMisses.add(lowGuess);
            System.out.println("Sorry, you missed");
            return false;
        }
    }

    //make every letter in phrase to lower case
    String makeLow(){
        String lowPhrase="";
        for(int i=0;i<this.phrase.length();i++){
            lowPhrase+=Character.toLowerCase(phrase.charAt(i));
        }
        return lowPhrase;
    }

    public static void main(String[] args) {
        HangmanV4 hangmanV4=new HangmanV4();
        hangmanV4.randomPhrase("words.txt");
//        System.out.println(hangmanV4.phrase);
        String lowPhrase=hangmanV4.makeLow();
//        System.out.println(lowPhrase);
        hangmanV4.generateHiddenPhrase();
        System.out.println(hangmanV4.hiddenPhrase);
        int misses;
        int TOTALCHANCES=5;
        int chancesLeft=TOTALCHANCES;
        Scanner scanner;
        scanner=new Scanner(System.in);
        while(chancesLeft>0){
            char guess=hangmanV4.playerGuess(scanner);
            char lowGuess=Character.toLowerCase(guess);
            if(!Character.isLetter(guess)){
                System.out.println("You don't input a letter");
                continue;
            }
            if(hangmanV4.preMisses.contains(lowGuess)){
                System.out.println("Please do not enter the wrong letter repeatedly!!!");
                continue;
            }
            if(hangmanV4.preCorrect.contains(lowGuess)) {
                System.out.println("Please do not enter the correct letter repeatedly!!!");
                continue;
            }
            hangmanV4.processGuess(lowPhrase,lowGuess);
            misses=hangmanV4.preMisses.size();
            chancesLeft=TOTALCHANCES-misses;
            System.out.println("Misses: "+misses);
            System.out.println("Incorrect letters: "+hangmanV4.preMisses);
            System.out.println("Chances left: "+chancesLeft);
            System.out.println("previous guesses"+hangmanV4.previousGuesses);
            System.out.println(hangmanV4.hiddenPhrase);
            if(hangmanV4.hiddenPhrase.indexOf("*")==-1){
                break;
            }
        }
        if(chancesLeft==0){
            System.out.println("Sorry, You lose!!!");
            System.out.println("The correct answer is: "+hangmanV4.phrase);
        }else{
            System.out.println("Congratulation, you win!!!");
        }
    }
}

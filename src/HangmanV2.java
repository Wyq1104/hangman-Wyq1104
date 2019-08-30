import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanV2 {
    //get a random Phrase
    String randomPhrase(String filename){
        List<String> phraseList=null;
        try{
            phraseList=Files.readAllLines(Paths.get(filename));
        }catch (IOException e){
            System.out.println(e);
        }
        Random random=new Random();
        int i1=random.nextInt(phraseList.size());
        String  phrase=phraseList.get(i1);
        return phrase;
    }

    //return initial hiddenPhrase
    StringBuilder generateHiddenPhrase(String phrase){
        StringBuilder hiddenCode=new StringBuilder();
        for(int j=0;j<phrase.length();j++){
            if(Character.isLetter(phrase.charAt(j))==true){
                hiddenCode.append('*');
            }else{
                hiddenCode.append(phrase.charAt(j));
            }
        }
        return hiddenCode;
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

    //    returns whether a letter matches and modifies the partially hidden phrase, and modifies the hidden phrase if there is a match.
    boolean processGuess(StringBuilder hiddenCode, String lowPhrase, char lowGuess, String phrase,
                         ArrayList<Character> preCorrect, ArrayList<Character> preMissed,
                         ArrayList<Character> preGuesses){
        preGuesses.add(lowGuess);
        if(lowPhrase.indexOf(lowGuess)!=-1){
            System.out.println("Yes, you are right.");
            preCorrect.add(lowGuess);
            for(int i=0;i<lowPhrase.length();i++){
                if(lowGuess==lowPhrase.charAt(i)){
                    hiddenCode.setCharAt(i,phrase.charAt(i));
                }
            }
            return true;
        }else{
            preMissed.add(lowGuess);
            System.out.println("Sorry, you missed");
            return false;
        }

    }

    //modify list of wrong and correct letter
//    void modifyLetterLists(ArrayList<Character> preMissed, ArrayList<Character> preCorrect, String lowPhrase,
//                           char lowGuess){
//        if(lowPhrase.indexOf(lowGuess)==-1){
//            preMissed.add(lowGuess);
//            System.out.println("Sorry, you missed");
//        }else{
//            preCorrect.add(lowGuess);
//        }
//    }

    //make every letter in phrase to lower case
    String makeLow(String phrase){
        String lowPhrase="";
        for(int i=0;i<phrase.length();i++){
            lowPhrase+=Character.toLowerCase(phrase.charAt(i));
        }
        return lowPhrase;
    }

    //count the number of letter in the phrase
//    int getTotalLetters(String phrase){
//        int totalLetters=0;
//        for(int j=0;j<phrase.length();j++) {
//            if (Character.isLetter(phrase.charAt(j)) == true) {
//                totalLetters++;
//            }
//        }
//        return totalLetters;
//    }

    public static void main(String[] args) {
        HangmanV2 hangmanV2=new HangmanV2();
        String phrase=hangmanV2.randomPhrase("phrases.txt");
//        System.out.println(phrase);
        String lowPhrase=hangmanV2.makeLow(phrase);
//        System.out.println(lowPhrase);
        StringBuilder hiddenCode=hangmanV2.generateHiddenPhrase(phrase);
        System.out.println(hiddenCode);
//        int totalLetters=hangmanV2.getTotalLetters(phrase);
//        System.out.println(totalLetters);
//        int correctLetters=0;
        int misses;
        int TOTALCHANCES=5;
        int chancesLeft=TOTALCHANCES;
        Scanner scanner;
        scanner=new Scanner(System.in);
        ArrayList<Character> preMissed= new ArrayList<>();
        ArrayList<Character> preCorrect=new ArrayList<>();
        ArrayList<Character> preGuesses=new ArrayList<>();

        while (chancesLeft>0){
            char guess=hangmanV2.playerGuess(scanner);
            char lowGuess=Character.toLowerCase(guess);
        // when player input not reasonably, go to next loop
            if(!Character.isLetter(guess)){
                System.out.println("You don't input a letter");
                continue;
            }
            if(preMissed.contains(lowGuess)){
                System.out.println("Please do not enter the wrong letter repeatedly!!!");
                continue;
            }
            if(preCorrect.contains(lowGuess)) {
                System.out.println("Please do not enter the correct letter repeatedly!!!");
                continue;
            }
            hangmanV2.processGuess(hiddenCode,lowPhrase,lowGuess,phrase,preCorrect,preMissed,preGuesses);
//            hangmanV2.modifyLetterLists(preMissed,preCorrect,lowPhrase,lowGuess);
            misses=preMissed.size();
            chancesLeft=TOTALCHANCES-misses;
            System.out.println("Misses: "+misses);
            System.out.println("Incorrect letters: "+preMissed);
            System.out.println("Chances left: "+chancesLeft);
            System.out.println("previous guesses"+preGuesses);
            System.out.println(hiddenCode);
            if(hiddenCode.indexOf("*")==-1){
                break;
            }
        }
        if(chancesLeft==0){
            System.out.println("Sorry, You lose!!!");
            System.out.println("The correct answer is: "+phrase);
        }else{
            System.out.println("Congratulation, you win!!!");
        }

    }
}

public class GuessBot {
//    String alphabets="abcedfghijklmnopqrstuvwxyz";
    //sort by possibilities
    String alphabets="etaoinshrdlcumnwfgypbvkjxqz";
    int guessTimes=0;
    char guessOneChar(){
         char guess=this.alphabets.charAt(guessTimes);
         guessTimes++;
         return guess;
    }
}

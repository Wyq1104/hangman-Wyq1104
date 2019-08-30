import java.util.ArrayList;

public class GuessBot {
//    String alphabets="abcedfghijklmnopqrstuvwxyz";
    //sort by possibilities
    StringBuilder alphabets=new StringBuilder("esiarntolcdupmghbyfvkwzxqj");
    ArrayList<String> hiddenWords=new ArrayList<>();
    int guessTimes=0;
    ArrayList<String> readPhrase(StringBuilder hiddenPhrase){
        ArrayList<String> hiddenWords=new ArrayList<>();
        String hiddenWord="";
        for(int i=0;i<hiddenPhrase.length();i++){
            if(hiddenPhrase.charAt(i)=='*'){
                hiddenWord+=hiddenPhrase.charAt(i);
                if(i==hiddenPhrase.length()-1){
                    hiddenWords.add(hiddenWord);
                }
            }else{
                if(hiddenWord!="") {
                    hiddenWords.add(hiddenWord);
                    hiddenWord = "";
                }
            }
        }
        return hiddenWords;
    }
    char meditation(ArrayList<Character> preMisses, ArrayList<Character> preCorrect, ArrayList<Character> previousGuesses){
        for(String hiddenword: this.hiddenWords){
            //Guess one-character word
            if(hiddenword.length()==1&&hiddenword.charAt(0)=='*'){
                if(!previousGuesses.contains('a')){
                    alphabets.deleteCharAt(alphabets.indexOf("a"));
                    return 'a';
                }else{
                    alphabets.deleteCharAt(alphabets.indexOf("i"));
                    return 'i';
                }
            }
            //Guess two-character word
//            if(hiddenword.length()==2&&hiddenword.charAt(0)=='*'&&hiddenword.charAt(1)=='*'){
//                if(previousGuesses.isEmpty()){
//                    return 'a';
//                }else if(previousGuesses==new ArrayList<Character>('a')){
//                    return 'o';
//                }else if(previousGuesses==)
//            }
        }

        return this.guessOneChar();
    }
    char guessOneChar(){
         char guess=this.alphabets.charAt(guessTimes);
         guessTimes++;
         return guess;
    }
}

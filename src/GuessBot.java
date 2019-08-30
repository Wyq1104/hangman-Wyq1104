import java.util.ArrayList;

public class GuessBot {
//    String alphabets="abcedfghijklmnopqrstuvwxyz";
    //sort by possibilities
    String alphabets="esiarntolcdupmghbyfvkwzxqj";
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
    void meditation(){
        for(String hiddenword: this.hiddenWords){
            if(hiddenword.length()==1){
                this.alphabets="aietonshrdlcumnwfgypbvkjxqz";
            }
        }
    }
    char guessOneChar(){
         char guess=this.alphabets.charAt(guessTimes);
         guessTimes++;
         return guess;
    }
}

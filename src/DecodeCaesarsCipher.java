import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DecodeCaesarsCipher {
    private static final int UP_LETTER_1_INDEX = 1040;
    private static final int LOW_LETTER_33_INDEX = 1103;
    private static final int UP_LETTER_6_INDEX = 1045;
    private static final int LOW_LETTER_6_INDEX = 1077;
    private static final int UP_LETTER_7_INDEX = 1025;
    private static final int LOW_LETTER_7_INDEX = 1105;
    private static final int UP_LETTER_33_INDEX = 1071;
    private static final int UP_LETTER_8_INDEX = 1046;
    private static final int LOW_LETTER_8_INDEX = 1078;

    private static List<Character> symbolsFromText = new ArrayList<>();

    public static void main(String[] args) {

        readSymbolsFromFile();

        // для расшифровки необходимо 11 итераций.
        doIterations(11);

        printDecodedText(symbolsFromText);
    }

    private static int calcUnicodeIndexAfterMove(int curIndex) {

        if ((curIndex >= UP_LETTER_1_INDEX) && (curIndex <= LOW_LETTER_33_INDEX)) {
            if (curIndex == UP_LETTER_6_INDEX) {
                curIndex = UP_LETTER_7_INDEX;
            } else if (curIndex == LOW_LETTER_6_INDEX) {
                curIndex = LOW_LETTER_7_INDEX;
            } else {
                    curIndex += 1;
                    if (((curIndex == (UP_LETTER_33_INDEX + 1))) || ((curIndex == (LOW_LETTER_33_INDEX + 1)))) {
                        curIndex -= 32;
                    }
            }
        } else if (curIndex == UP_LETTER_7_INDEX) {
            curIndex = UP_LETTER_8_INDEX;
        } else if (curIndex == LOW_LETTER_7_INDEX) {
            curIndex = LOW_LETTER_8_INDEX;
        }

        return  curIndex;
    }

    private static void printDecodedText(List<Character> decodedTExt){
        for(Character ch:decodedTExt){
            System.out.print(ch);
            if (ch =='.') {
                System.out.println("");
            }
        }
    }

    private static void doIterations(int n){
        for(int i = 0; i < n; i++){
            int symNumberInUnicode = 0;
            for(int j = 0; j < symbolsFromText.size(); j++){
                symbolsFromText.set(j, (char)calcUnicodeIndexAfterMove(symbolsFromText.get(j)));
            }
        }
    }

    private static void readSymbolsFromFile(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("text.txt")));
            int symNumberInUnicode = 0;
            char ch;
            while((symNumberInUnicode = br.read()) != -1){
                ch = (char)symNumberInUnicode;
                symbolsFromText.add(ch);
            }
        } catch (IOException e){
            System.out.println("Ошибка ввода-вывода!");
        }
    }


}

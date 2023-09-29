package CLP_Core_Java;

public class TurnsCountString {
    private static boolean isOriginalString(String word) {
        return word.equals(new StringBuilder(word).reverse().toString());
    }

    // Rotate the string by 'k' characters from the end to the beginning
    private static String rotateString(String word, int k) {
        int length = word.length();
        k = k % length; // Handle cases where k is larger than the string length
        return word.substring(length - k) + word.substring(0, length - k);
    }

    private static int operationCount(String word, int input1,int input2){

        /*write your code here and return appropriate value*/
        int turns = 0;
        int length = word.length();

        while (!isOriginalString(word)) {
            // John's turn
            if (turns % 2 == 0) {
                word = rotateString(word, input1);
            }
            // Michael's turn
            else {
                word = rotateString(word, input2);
            }
            System.out.println(turns);
            turns++;
            if(turns>word.length()){
                break;
            }
        }

        return turns;
    }
}

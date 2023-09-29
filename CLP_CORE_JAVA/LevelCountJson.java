package CLP_Core_Java;

public class LevelCountJson {
    public static int levelCount(String word){

        /*write your code here and return appropriate value*/
        int maxDepth = 0;
        int currentDepth = 0;

        for (char c : word.toCharArray()) {
            if (c == '{') {
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (c == '}') {
                maxDepth = Math.max(maxDepth, currentDepth);
                currentDepth--;
            }
        }

        return maxDepth+1;

    }

}

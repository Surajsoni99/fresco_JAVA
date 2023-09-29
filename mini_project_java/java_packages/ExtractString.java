package mini_project_java.java_packages;

import java.util.regex.*;
import java.util.*;
public class ExtractString {
    public static Integer getSum(String paragraph){
        Map<String, Integer> numberWords = new HashMap<>();
        numberWords.put("one", 1);
        numberWords.put("two", 2);
        numberWords.put("three", 3);
        numberWords.put("four", 4);
        numberWords.put("five", 5);
        numberWords.put("six", 6);
        numberWords.put("seven", 7);
        numberWords.put("eight", 8);
        numberWords.put("nine", 9);

        // Use regex to find English number words in the paragraph
        Pattern pattern = Pattern.compile("\\b(?:one|two|three|four|five|six|seven|eight|nine)\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(paragraph);

        int totalSum = 0;

        // Iterate through matches and calculate the sum
        while (matcher.find()) {
            String matchedWord = matcher.group().toLowerCase();
            if (numberWords.containsKey(matchedWord)) {
                int numberValue = numberWords.get(matchedWord);
                totalSum += numberValue;
            }
        }
        System.out.println(totalSum);
        return totalSum;
    }
}


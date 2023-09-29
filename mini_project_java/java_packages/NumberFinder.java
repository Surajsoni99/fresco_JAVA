package mini_project_java.java_packages;

import java.util.*;
import java.util.regex.*;

public class NumberFinder {

    public static String getEncodedString(String text){
        List<Integer> numbers = extractNumbers(text);
        StringBuilder ans = new StringBuilder();
        for (int num : numbers) {
            if (isKeithComposite(num) && isComposite(num)) {
                ans.append(num);
            }
        }
        System.out.println(ans);
        return ans.toString();
    }

    // Function to extract numbers from a string
    public static List<Integer> extractNumbers(String input) {
        List<Integer> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\d+\\b"); // Regular expression to match numbers
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            numbers.add(number);
        }

        return numbers;
    }

    // Function to check if a number is a Keith Composite number
    public static boolean isKeithComposite(int num) {
        System.out.println(num);
        String numStr = String.valueOf(num);
        int n = numStr.length();
        List<Integer> sequence = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            sequence.add(Integer.parseInt(String.valueOf(numStr.charAt(i))));
        }

        int sum = 0;
        int i = n;

        while (sum < num) {
            sum = 0;
            for (int j = 1; j <= n; j++) {
                sum += sequence.get(i - j);
            }
            if (sum == num) {
                return true;
            }
            sequence.add(sum);
            i++;
        }

        return false;
    }

    public static boolean isComposite(int number) {
        if (number <= 1) {
            return false; // Numbers less than or equal to 1 are not composite
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return true; // Number is divisible by a number other than 1 and itself
            }
        }

        return false; // Number is not divisible by any number other than 1 and itself
    }
}


package mini_project_java.regular_expressions;

import java.util.regex.Pattern;
import java.util.regex.*;

public class RegEx
{

    public String findCardTypeNumbers(String conversation, String cardType)
    {
        //Write your code here

        // Define regex patterns for each card type
        String visaPattern = "\\b4\\d{15}|\\b4\\d{12}";
        String amexPattern = "3[47]\\d{13}";
        String discoverPattern = "6(?:011|5\\d{2})\\d{12}";
        String jcbPattern = "(2131|1800)\\d{11}|35\\d{14}";

        // Create a regex pattern based on the card type
        String regexPattern = "";
        switch (cardType) {
            case "Visa":
                regexPattern = visaPattern;
                break;
            case "American Express":
                regexPattern = amexPattern;
                break;
            case "Discover":
                regexPattern = discoverPattern;
                break;
            case "JCB":
                regexPattern = jcbPattern;
                break;
            default:
                return ("Not a valid card type");
        }

        // Use regex to find card numbers in the passage
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(conversation);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String cardNumber = matcher.group();
            result.append(cardNumber).append(" ");
        }
        System.out.println(result);

        if (result.length() > 0) {
            // Remove the trailing space and print the result
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        } else {
            result.append("");
            return result.toString();
        }
    }
}

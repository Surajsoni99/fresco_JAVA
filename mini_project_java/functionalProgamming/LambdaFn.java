package mini_project_java.functionalProgamming;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaFn {

    public List<Long> functionalProgramming(List<String> listOfIntegers)
    {
        //Write your code here

        List<Long> listOfLongs = listOfIntegers.stream()
                .map(str -> {
                    try {
                        return Long.parseLong(str);
                    } catch (NumberFormatException e) {
                        // Handle invalid format or non-numeric strings if needed
                        return null; // Indicate failure with a null value
                    }
                })
                .filter(value -> value != null) // Filter out null values (failed conversions)
                .collect(Collectors.toList());


        List<Long> outputList = listOfLongs.stream().filter(aa -> calculateDigPower(aa).equals(aa)).collect(Collectors.toList());


        return outputList;
    }

    public Long calculateDigPower(Long num) {
        if (num == null || num < 0) {
            // Handle invalid input or negative numbers as needed
            return null;
        }

        long originalNum = num; // Store the original number
        int dig = getNoOfDigits(num);
        long digSum = 0;

        while (num > 0) {
            long r = num % 10;
            num = num / 10;
            digSum += Math.pow(r, dig); // Add the digit raised to the power of dig
        }

        return digSum;
    }


    public Integer getNoOfDigits(Long num){
        int i=0;
        while(num>0){
            long r = num % 10;
            i++;
            num=num/10;
        }
        return i;
    }

}

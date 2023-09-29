package mini_project_java.generic_class;

import java.util.*;
import java.text.DecimalFormat;


class ScoreList<T extends Number> {
    private ArrayList<T> scores = new ArrayList<>();

    public void addElement(T score) {
        scores.add(score);
    }

    public void removeElement(T score) {
        scores.remove(score);
    }

    public T getElement(int index) {
        return scores.get(index);
    }

    public String averageValues() {
        double totalScore = 0.00;
        for (T score : scores) {
            if(score instanceof Integer){
                totalScore += score.intValue();
            }
            else if(score instanceof Double){
                totalScore += score.doubleValue();
            }
        }

        if (scores.isEmpty()) {
            return "0.00"; // Handle the case where the list is empty to avoid division by zero
        }

        double avg = totalScore / scores.size(); // Calculate the average

        // Format the result to two decimal places
        DecimalFormat df = new DecimalFormat("#0.00");
        String formattedAvg = df.format(avg);

        // Parse the formatted result back to Double
        return formattedAvg;
    }

    // Add methods for calculating average scores here
}








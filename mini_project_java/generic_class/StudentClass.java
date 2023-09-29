package mini_project_java.generic_class;

import java.util.*;


public class StudentClass {

    public String getQuery(String studentData,String query){
        StringBuilder ans = new StringBuilder();
        String queryParams[] = query.split(",");
        StudentList studentList = new StudentList<>();
        String students[] = studentData.split(" ");
        for(String student: students){
            studentList.addElement(student);
        }
        if(Integer.valueOf(queryParams[0])<4){
            //student
            switch(Integer.valueOf(queryParams[0])){
                case 1: {
                    ArrayList<String> stud =  studentList.beginsWith(queryParams[1].toUpperCase());
                    for(String st: stud){
                        ans.append(st);
                        ans.append("\n"); // Add a newline character
                    }
                    return ans.toString();
                }
                case 2:{
                    String bloodGrp[] = queryParams[1].split(" ");
                    String searchGrp = queryParams[2];
                    ArrayList<String> list = studentList.bloodGroupOf(bloodGrp, searchGrp);
                    for(String name: list){
                        ans.append(name+"\n");
                    }
                    return ans.toString();
                }
                case 3: {
                    Integer val = studentList.thresholdScore(Integer.valueOf(queryParams[1]));
                    ans.append(val);
                    ans.append(" students scored "+queryParams[1]+" above");
                    return ans.toString();
                }
            }
        }
        else{
            switch(Integer.valueOf(queryParams[0])){

                case 4:{
                    ScoreList scoreList = new ScoreList<>();
                    String scores[] = studentData.split(" ");
                    for(String s: scores){
                        scoreList.addElement(Integer.valueOf(s));;
                    }
                    String score = scoreList.averageValues();
                    ans.append(score);
                    return ans.toString();
                }
                case 5:{
                    ScoreList scoreList = new ScoreList<>();
                    double sum=0.00;
                    for(String s: students){
                        sum+=Double.valueOf(s);
                        scoreList.addElement(Double.valueOf(s));;
                    }
                    System.out.println(sum/students.length);
                    String score = scoreList.averageValues();
                    ans.append(score);
                    return ans.toString();
                }
            }
            //score
        }
        return null;

    }

}


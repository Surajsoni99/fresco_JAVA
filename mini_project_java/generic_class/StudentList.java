package mini_project_java.generic_class;

import java.security.KeyStore.Entry;
import java.util.*;

class StudentList<T> {
    private ArrayList<T> students = new ArrayList<>();

    public void addElement(T student) {
        students.add(student);
    }

    public void removeElement(T student) {
        students.remove(student);
    }

    public T getElement(int index) {
        return students.get(index);
    }

    public ArrayList<T> beginsWith(String letter) {
        ArrayList<T> result = new ArrayList<>();
        for (T student : students) {
            if (student.toString().startsWith(letter)) {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList<T> bloodGroupOf(String[] studentsgrp, String bloodGroup) {
        ArrayList<T> result = new ArrayList<>();
        Map<T, String> map = new LinkedHashMap();
        int i=0;
        for(String std: studentsgrp){
            map.put(students.get(i),std);
            i++;
        }
        for(Map.Entry<T,String> entry: map.entrySet()){
            if(entry.getValue().equals(bloodGroup)){
                result.add(entry.getKey());
            }
        }
        // Implement logic to find students with the specified blood group
        return result;
    }

    public Integer thresholdScore(Integer threshold) {
        Integer count = 0;
        // Implement logic to count students with scores higher than the threshold
        for (T student : students) {
            if (Integer.valueOf(student.toString()) >= threshold) {
                count++;
            }
        }
        return count;
    }
}



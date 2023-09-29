package mini_project_java.collections.arraylist;

import java.util.*;
import java.text.DecimalFormat;

class Passanger
{
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }
    float fare;
    public Passanger(int id, float fare)
    {
        this.id=id;
        this.fare=fare;
    }
}




public class BusProb {



    public float getFareInFormatByCap(int cap, int totalCap){
        float fare;
        switch(cap){
            case 1: {
                fare = (float)(totalCap + totalCap * 0.6);
            }
            case 2:{
                fare = (float)(totalCap + totalCap * 0.3);
            }
            default:{
                fare = (float)(totalCap);
            }
        }

        // Parse the formatted string back to a float
        float roundedFloat = Math.round(fare * 10.0f) / 10.0f;;
        return roundedFloat;

    }
    public float getFare(int currentCap, int totalCap) {
        float per = (float) currentCap / totalCap * 100;
        float fare=0.0f;
        if (per <= 25) {
            fare = (float)(totalCap + totalCap * 0.6);
        } else if (per <= 50) {
            fare = (float)(totalCap + totalCap * 0.3);
        } else if (per > 50) {
            fare = (float)totalCap;
            if (fare % 2 != 0 || fare % 4 != 0) {
                fare = (float) Math.ceil(fare);
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.0");

        // Format the float value to one decimal place
        String roundedValue = decimalFormat.format(fare);

        // Parse the formatted string back to a float
        float roundedFloat = Float.parseFloat(roundedValue);

        return roundedFloat;
    }

    public String output(int capacity, int stops, List<String> listOfInputStrings, String query)
    {
        StringBuilder outstring=new StringBuilder();
        //Write your code here
        ArrayList<Passanger> list = new ArrayList<>();
        String queryPart[] = query.split(",");
        ArrayList<Integer> passIds = new ArrayList<>();
        switch(Integer.valueOf(queryPart[0])){
            case 1:{
                Integer inside =0,outside=0;
                for(String stopsDetails: listOfInputStrings){
                    String passengers[] = stopsDetails.split(" ");
                    for(int i=0;i<passengers.length;i++){
                        if(passengers[i].charAt(0)=='+'){
                            Integer id = Integer.valueOf(passengers[i].substring(0));
                            inside++;
                        }
                        else{
                            Integer id = Integer.valueOf(passengers[i].substring(0));
                            outside++;
                        }
                    }
                }
                outstring.append(inside.toString() + " passengers got on the bus and "+outside.toString()+" passengers got out of the bus");
                return outstring.toString();
            }
            case 2:{
                int cnt=0;
                int fare1=0,fare2=0,fare3=0;
                for(String stopsDetails: listOfInputStrings){
                    String passengers[] = stopsDetails.split(" ");
                    for(int i=0;i<passengers.length;i++){
                        if(passengers[i].charAt(0)=='+'){
                            cnt++;
                            Integer id = Integer.valueOf(passengers[i].substring(0));
                            float fare = getFare(cnt,capacity);
                            //check if exists in list
                            if(!passIds.contains(id)){
                                Passanger prob = new Passanger(id, fare);
                                list.add(prob);
                                passIds.add(id);
                            }
                            else{
                                for(Passanger pass: list){
                                    if(pass.id == id){
                                        float currfare = pass.fare;
                                        currfare = currfare + fare;
                                        pass.setFare(currfare);
                                    }
                                }
                            }
                        }
                        else{
                            cnt--;
                        }
                    }
                    for(Passanger pass: list){
                        if(pass.fare == getFareInFormatByCap(1, capacity)){
                            fare1++;
                        }
                        else if(pass.fare == getFareInFormatByCap(2, capacity)){
                            fare2++;
                        }
                        else if(pass.fare == getFareInFormatByCap(3, capacity)){
                            fare3++;
                        }
                    }
                }
                outstring.append(fare1 + " passengers traveled with a fare of "+getFareInFormatByCap(1, capacity)+", ");
                outstring.append(fare2 + " passengers traveled with a fare of "+ getFareInFormatByCap(2, capacity)+" and ");
                outstring.append(fare3 + " passengers traveled with a fare of "+getFareInFormatByCap(3, capacity));
                System.out.println(outstring);
                return outstring.toString();
            }
            case 3:{
                String passId1[] = queryPart[1].split(" ");
                Integer passId = Integer.valueOf(passId1[1]);
                for(Passanger pass: list){
                    if(pass.id == passId){
                        outstring.append("Passenger " + pass.id + " spent a total fare of "+pass.getFare());
                        break;
                    }
                }
                System.out.println(outstring);
                return outstring.toString();
            }
            case 4:{
                String passId1[] = queryPart[1].split(" ");
                Integer passId = Integer.valueOf(passId1[1]);
                Map<Integer,Integer> passVsCount = new HashMap<>();
                for(String stopsDetails: listOfInputStrings){
                    String passengers[] = stopsDetails.split(" ");
                    for(int i=0;i<passengers.length;i++){
                        if(passengers[i].charAt(0)=='+'){
                            //check if exists in list
                            Integer id = Integer.valueOf(passengers[i].substring(0));
                            if(passVsCount.containsKey(id)){
                                Integer count = passVsCount.get(id);
                                count++;
                                passVsCount.put(id, count);
                            }
                            else{
                                passVsCount.put(id, 1);
                            }
                        }
                    }
                }
                outstring.append("Passenger "+passId+" has got on the bus for "+passVsCount.get(passId)+" times");
                System.out.println(outstring);
                return outstring.toString();
            }
            case 5:{
                String passId1[] = queryPart[1].split(" ");
                Integer passId = Integer.valueOf(passId1[1]);
                Map<Integer,Integer> passVsCount = new HashMap<>();
                for(String stopsDetails: listOfInputStrings){
                    String passengers[] = stopsDetails.split(" ");
                    for(int i=0;i<passengers.length;i++){
                        if(passengers[i].charAt(0)=='+'){
                            //check if exists in list
                            Integer id = Integer.valueOf(passengers[i].substring(0));
                            if(!passVsCount.containsKey(id)){
                                passVsCount.put(id, 1);
                            }
                        }
                        else{
                            Integer id = Integer.valueOf(passengers[i].substring(0));
                            passVsCount.put(id, 0);
                        }
                    }
                }
                String in = passVsCount.get(passId)==0?" not inside":" inside";
                outstring.append("Passenger "+passId+ " was"+in+" the bus at the end of the trip");
                System.out.println(outstring);
                return outstring.toString();
            }

        }
        return "";
    }
}


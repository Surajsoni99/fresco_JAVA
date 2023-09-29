package mini_project_java.collections.treemap;
import java.util.*;
public class TreemapHandson {

    public TreeMap<Integer,String> createPlayerPositionMap(String cricketDataset)
    {
        TreeMap<Integer,String> posMap = new TreeMap<>();
        String dataset[] = cricketDataset.split("\\|");
        for(String data: dataset){
            String playerData[] = data.split(",");
            posMap.put(Integer.valueOf(playerData[0]), playerData[1]);
        }
        return posMap;

    }
    public TreeMap<String,Integer> createPlayerScoreMap(String cricketDataset)
    {
        TreeMap<String, Integer> scoreMap = new TreeMap<>();
        String dataset[] = cricketDataset.split("\\|");
        for(String data: dataset){
            String playerData[] = data.split(",");
            scoreMap.put(playerData[1],Integer.valueOf(playerData[2]));
        }
        return scoreMap;

    }
    public TreeMap<String,ArrayList<Integer>> createMatchesMap(String cricketDataset)
    {
        TreeMap<String, ArrayList<Integer>> playerVsScores = new TreeMap<>();
        String dataset[] = cricketDataset.split("\n");
        for(String data: dataset){
            String match[] = data.split("\\|");
            for(String mat: match){
                String player[] = mat.split(",");
                if(player[0].charAt(0)=='1'){
                    if(playerVsScores.containsKey(player[1])){
                        ArrayList<Integer> score = playerVsScores.get(player[1]);
                        score.add(Integer.valueOf(player[2]));
                        playerVsScores.put(player[1], score);
                    }
                    else{
                        ArrayList<Integer> score = new ArrayList<>();
                        score.add(Integer.valueOf(player[2]));
                        playerVsScores.put(player[1], score);
                    }
                }
            }
        }
        return playerVsScores;
    }


    public String getQuery(String cricketDataset,String query)
    {
        String queryParts[] = query.split(" ");
        StringBuilder ans = new StringBuilder();
        switch(Integer.valueOf(queryParts[0])){
            case 1:{
                TreeMap<Integer,String> posMap = createPlayerPositionMap(cricketDataset);
                Integer start = Integer.valueOf(queryParts[1]);
                Integer end = Integer.valueOf(queryParts[2]);
                while(start<=end){
                    ans.append(start + " " + posMap.get(start) + "\n");
                    start++;
                }
                return ans.toString();
            }
            case 2:{
                TreeMap<String,Integer> scoreMap = createPlayerScoreMap(cricketDataset);
                Integer threshold = Integer.valueOf(queryParts[1]);
                ArrayList<String> thresholdPlayers =  new ArrayList<>();
                for(Map.Entry<String, Integer> entry: scoreMap.entrySet()){
                    if(entry.getValue()>threshold){
                        thresholdPlayers.add(entry.getKey());
                    }
                }
                TreeMap<Integer,String> posMap = createPlayerPositionMap(cricketDataset);
                for(Map.Entry<Integer, String> entry: posMap.entrySet()){
                    if(thresholdPlayers.contains(entry.getValue())){
                        ans.append(entry.getKey() + " "+ entry.getValue()+"\n");
                    }
                }
                return ans.toString();
            }
            default:{
                //player vs opening avg
                TreeMap<String, ArrayList<Integer>> playerVsScores = createMatchesMap(cricketDataset);
                TreeMap<Integer, String> avgScoreMap = new TreeMap<>();
                Integer highestAvg = 0;
                String opener="";
                for(Map.Entry<String, ArrayList<Integer>> entry: playerVsScores.entrySet()){
                    ArrayList<Integer> arr = entry.getValue();
                    Integer sum = 0;
                    for(Integer i: arr){
                        sum+=i;
                    }
                    Integer avr = sum.intValue()/arr.size();
                    //System.out.println(avr);
                    if(avr>highestAvg){
                        highestAvg = avr;
                        opener = entry.getKey();
                    }
                    avgScoreMap.put(avr, entry.getKey());
                }
                ans.append("The Efficient Opener is "+ opener);
                return ans.toString();

            }
        }
    }


}










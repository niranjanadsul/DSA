package MorganStanleyTest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class VolleyballGameTracker {
    private Map<String,Integer> teamScores;
    private PriorityQueue<TestScore> maxHeap;
    public VolleyballGameTracker(String[] teamNames){
        teamScores = new HashMap<>();
        maxHeap = new PriorityQueue<TestScore>();
        for(String s:teamNames){
            teamScores.put(s,0);
        }
    }

    public void addMatch(String team1, String team2, String score){
        String[] scores = score.split(":");
        int team1score=Integer.parseInt(scores[0]);
        int team2score=Integer.parseInt(scores[1]);
        if(team1score>team2score){
            teamScores.put(team1,teamScores.get(team1)+2);
        }
        if(team1score<team2score){
            teamScores.put(team2,teamScores.get(team2)+2);
        }
        if(team1score==team2score){
            teamScores.put(team1,teamScores.get(team1)+1);
            teamScores.put(team2,teamScores.get(team2)+1);
        }
    }

    public String findFirst(){
        this.maxHeap = new PriorityQueue<>((o1, o2) -> o2.score- o1.score);
        for(Map.Entry<String, Integer> entry:this.teamScores.entrySet()){
            TestScore ts=new TestScore(entry.getKey(), entry.getValue());
            this.maxHeap.add(ts);
        }
        return this.maxHeap.poll().team;
    }


}

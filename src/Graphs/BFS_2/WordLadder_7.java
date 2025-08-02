package Graphs.BFS_2;

import java.lang.reflect.Array;
import java.util.*;

public class WordLadder_7 {
    //https://leetcode.com/problems/word-ladder/description/
    /*A transformation sequence from word beginWord to word endWord using a
    dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
    Every adjacent pair of words differs by a single letter.
    Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
    sk == endWord
    Given two words, beginWord and endWord, and a dictionary wordList,
    return the number of words in the shortest transformation sequence from beginWord to endWord,
    or 0 if no such sequence exists.

    Example 1:
    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    Output: 5
    Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog",
    which is 5 words long.

    Example 2:
    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
    Output: 0
    Explanation: The endWord "cog" is not in wordList,
    therefore there is no valid transformation sequence.*/
    //Refer Notes
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord))
            return 0;

        Queue<String> queue=new LinkedList<>();
        queue.add(beginWord);
        set.remove(beginWord);
        int step=0;
        while (!queue.isEmpty()){
            int size=queue.size();
            while(size>0) {
                String word = queue.remove();
                if(word.equals(endWord))
                    return step+1;
                List<String> neighbours = findNeighbours(word,set);
                for(String neighbour:neighbours){
                    queue.add(neighbour);
                    set.remove(neighbour);
                }
                size--;
            }
            step++;
        }
        return 0;
    }

    public List<String> findNeighbours(String word, HashSet<String> set){
        List<String> neighbours=new ArrayList<>();
        for(int i =0;i<word.length();i++){
            char[] wordChars = word.toCharArray();
            for(char c='a';c<='z';c++){
                wordChars[i]=c;
                String neighbour = String.valueOf(wordChars);
                if(set.contains(neighbour))
                    neighbours.add(neighbour);
            }
        }
        return neighbours;
    }

    public static void main(String[] args) {
        WordLadder_7 wordLadder7=new WordLadder_7();
        wordLadder7.ladderLength("ymain","oecij",
                Arrays.asList("ymann","yycrj","oecij","ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain"));
    }
}

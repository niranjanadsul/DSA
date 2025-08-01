package Graphs.BFS_2;

import java.util.*;

public class WordLadder2_8 {
    //https://leetcode.com/problems/word-ladder-ii/description/
    /*A transformation sequence from word beginWord to word endWord using a dictionary wordList is
     a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

    Every adjacent pair of words differs by a single letter.
    Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
    sk == endWord
    Given two words, beginWord and endWord, and a dictionary wordList,
    return all the shortest transformation sequences from beginWord to endWord,
    or an empty list if no such sequence exists.
    Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

    Example 1:

    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
    Explanation: There are 2 shortest transformation sequences:
    "hit" -> "hot" -> "dot" -> "dog" -> "cog"
    "hit" -> "hot" -> "lot" -> "log" -> "cog"
    */

    //for interview prep this approach is sufficient

    //but this gives time limit exceed in leetcode due to competitive programming and has an optimized algo
    //refer the optimal algo from notes
    //use word ladder 1 to find the minimum steps.
    // while doing so keep track of ech word and level in which the word appeared with the help of map
    // now backtrack this map from end word
    //while backtracking from the endWord keep track of the levels to avoid the repeated words in sequence
    //as we backtrack from endword we eliminate unnecessary paths
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        List<List<String>> ls=new ArrayList<>();
        if(!set.contains(endWord))
            return ls;

        Queue<String[]> queue=new LinkedList<>();
                            //currentWord, words till current word
        queue.add(new String[]{beginWord,beginWord});
        set.remove(beginWord);//level 0 has been processed so remove the node from set
        String sequenceSeparator = ",";
        boolean toBreakAfterLevelProcessed=false;
        while (!queue.isEmpty() && !toBreakAfterLevelProcessed){
            int size=queue.size();
            List<String> nodesToRemoveAfterLevelTraversal = new ArrayList<>();
            while(size>0) {
                String[] words = queue.remove();
                String word=words[0];
                String sequence=words[1];
                if(word.equals(endWord)) {
                    toBreakAfterLevelProcessed=true;
                    ls.add(Arrays.asList(sequence.split(sequenceSeparator)));
                }
                List<String> neighbours = findNeighbours(word,set, sequence);
                nodesToRemoveAfterLevelTraversal.addAll(neighbours);
                for(String neighbour:neighbours){
                    queue.add(new String[]{neighbour,sequence+sequenceSeparator+neighbour});
                }
                size--;
            }
            nodesToRemoveAfterLevelTraversal.forEach(set::remove);
            //level has been processed so remove the nodes processed in this level from set
        }
        return ls;
    }

    public List<String> findNeighbours(String word, HashSet<String> set, String sequence){
        List<String> neighbours=new ArrayList<>();
        for(int i =0;i<word.length();i++){
            char[] wordChars = word.toCharArray();
            char orgCharAtIndex=wordChars[i];
            for(char c='a';c<='z';c++){
                if(orgCharAtIndex!=c) {
                    wordChars[i] = c;
                    String neighbour = String.valueOf(wordChars);
                    if (set.contains(neighbour) && !sequence.contains(neighbour))
                        neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    public static void main(String[] args) {
        WordLadder2_8 wordLadder7=new WordLadder2_8();
        int size;
        size=wordLadder7.findLadders("red","tax",
                Arrays.asList("ted","tex","red","tax","tad","den","rex","pee")).size();
        System.out.println(size);
        size=wordLadder7.findLadders("cet","ism",
                Arrays.asList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim")).size();
        System.out.println(size);
    }
}


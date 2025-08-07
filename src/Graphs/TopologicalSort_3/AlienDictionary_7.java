package Graphs.TopologicalSort_3;

import java.util.*;

public class AlienDictionary_7 {
    //https://www.geeksforgeeks.org/problems/alien-dictionary/1
    /*
    * A new alien language uses the English alphabet, but the order of letters is unknown.
    * You are given a list of words[] from the alien language’s dictionary,
    * where the words are claimed to be sorted lexicographically according to the language’s rules.
    Your task is to determine the correct order of letters in this alien language based on the given words.
    *  If the order is valid, return a string containing the unique letters in lexicographically increasing
    *  order as per the new language's rules. If there are multiple valid orders, return any one of them.
    However, if the given arrangement of words is inconsistent with any possible letter ordering,
    * return an empty string ("").
    A string a is lexicographically smaller than a string b if, at the first position where they differ,
    *  the character in a appears earlier in the alien language than the corresponding character in b.
    *  If all characters in the shorter word match the beginning of the longer word,
    * the shorter word is considered smaller.
    Note: Your implementation will be tested using a driver code.
    * It will print true if your returned order correctly follows the alien language’s lexicographic rules;
    * otherwise, it will print false.

    Examples:

    Input: words[] = ["baa", "abcd", "abca", "cab", "cad"]
    Output: true
    Explanation: A possible corrct order of letters in the alien dictionary is "bdac".
    The pair "baa" and "abcd" suggests 'b' appears before 'a' in the alien dictionary.
    The pair "abcd" and "abca" suggests 'd' appears before 'a' in the alien dictionary.
    The pair "abca" and "cab" suggests 'a' appears before 'c' in the alien dictionary.
    The pair "cab" and "cad" suggests 'b' appears before 'd' in the alien dictionary.
    So, 'b' → 'd' → 'a' → 'c' is a valid ordering.

    Input: words[] = ["caa", "aaa", "aab"]
    Output: true
    Explanation: A possible correct order of letters in the alien dictionary is "cab".
    The pair "caa" and "aaa" suggests 'c' appears before 'a'.
    The pair "aaa" and "aab" suggests 'a' appear before 'b' in the alien dictionary.
    So, 'c' → 'a' → 'b' is a valid ordering.

    Input: words[] = ["ab", "cd", "ef", "ad"]
    Output: ""
    Explanation: No valid ordering of letters is possible.
    The pair "ab" and "ef" suggests "a" appears before "e".
    The pair "ef" and "ad" suggests "e" appears before "a", which contradicts the ordering rules.*/

    //here we need to process words in the form of adjacent pair
    //accordingly generate a graph of which letter appears before other letter
    //apply toposort and find out the sequence
    //if there is cycle then return empty string

    // TC=O(N*M) where N=number of input word and M == length of word


    public static void main(String[] args) {
        AlienDictionary_7 alienDictionary7=new AlienDictionary_7();
        System.out.println(alienDictionary7.findOrder(new String[]{
                "abc"
        }));
        System.out.println(alienDictionary7.findOrder(new String[]{
                "abc", "ab"
        }));
        System.out.println(alienDictionary7.findOrder(new String[]{
                "dddc", "a", "ad","ab" ,"b" ,"be" ,"cd" ,"cded", "f", "fg"
        }));
        System.out.println(alienDictionary7.findOrder(new String[]{
                "baa", "abcd", "abca", "cab", "cad"
        }));//bdac
        System.out.println(alienDictionary7.findOrder(new String[]{
                "caa", "aaa", "aab"
        }));//cab
        System.out.println(alienDictionary7.findOrder(new String[]{
                "ab", "cd", "ef", "ad"
        }));//""
    }

    public String findOrder(String[] words) {

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[26];
        boolean[] exists = new boolean[26];

        // Initialize graph
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }

        // Mark characters that exist in the input
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                exists[ch - 'a'] = true;
            }
        }

        // Build the graph based on adjacent word comparisons
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());
            int j = 0;

            while (j < len && w1.charAt(j) == w2.charAt(j)) {
                j++;
            }

            if (j < len) {
                int u = w1.charAt(j) - 'a';
                int v = w2.charAt(j) - 'a';
                graph.get(u).add(v);
                inDegree[v]++;
            } else if (w1.length() > w2.length()) {

                // Invalid input
                return "";
            }
        }

        // Topological Sort
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (exists[i] && inDegree[i] == 0) {
                q.offer(i);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!q.isEmpty()) {
            int u = q.poll();
            result.append((char) (u + 'a'));

            for (int v : graph.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        // Check for cycle
        for (int i = 0; i < 26; i++) {
            if (exists[i] && inDegree[i] != 0) {
                return "";
            }
        }

        return result.toString();
    }
}

package Array.TwoPointer;

public class TheCelebrityProblem_3 {
    //https://www.geeksforgeeks.org/problems/the-celebrity-problem/1
    /*A celebrity is a person who is known to all but does not know anyone at a party.
     A party is being organized by some people.
     A square matrix mat[][] of size n*n is used to represent people at the party such that
     if an element of row i and column j is set to 1 it means ith person knows jth person.
     You need to return the index of the celebrity in the party, if the celebrity does not exist,
     return -1.

    Note: Follow 0-based indexing.

    Examples:

    Input: mat[][] = [[1, 1, 0],
                    [0, 1, 0],
                    [0, 1, 1]]
    Output: 1
    Explanation: 0th and 2nd person both know 1st person and 1st person does not know anyone. Therefore, 1 is the celebrity person.
    Input: mat[][] = [[1, 1],
                    [1, 1]]
    Output: -1
    Explanation: Since both the people at the party know each other. Hence none of them is a celebrity person.
    Input: mat[][] = [[1]]
    Output: 0*/

    //who is Celebrity
    // A person who knows no one but known by ALL
    // celebrity is known by N-1 people
    // and that is why there cannot be 2 celebrities because then the N-1 condition won't satisfy
    // in naive approach we will iterate through the array and find the KnowsMe and IKnow arrays
    //where ever KnowsMe == N-1 and IKnow ==0 is the celebrity
    // this takes N*N time and O(N) space

    //But there is an efficient approach using two pointers
    //one at the start and other at the end
    //if pointer a knows b then a is definitely not Celebrity hence move a ahead
    //if becomes greater than B means there is no celebrity
    //if a == b then check all the other columns in the row for 0 (knows no one)
    // and all the other rows for this column to be 1 (known by ALL)
    // as to ensure this index is a celebrity
    //TC= O(n)
    public int celebrity(int mat[][]) {
        int start=0,end=mat.length-1;
        while(start<end){
            //let's bet on start person
            //either start is a Celebrity or not
            //start is a celebrity if end knows him
            //start is not a celebrity if end does not know him because N-1 condition false
            if(mat[end][start]==1)
                end--;//start might be celebrity but end is definitely not
            else
                start++;//start is definitely not celebrity
        }

        //as we were betting on start
        //start will be pointing to the celebrity
        //let's confirm if start knows no one
        for(int i=0;i<mat[start].length;i++){
            if(i!=start && mat[start][i]==1)
                return -1;
        }
        //let's confirm if ALL others know start
        for(int i=0;i<mat.length;i++){
            if(i!=start && mat[i][start]!=1)
                return -1;
        }
        return start;
    }
}

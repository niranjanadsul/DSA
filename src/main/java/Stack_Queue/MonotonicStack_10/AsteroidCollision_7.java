package Stack_Queue.MonotonicStack_10;

import java.util.Stack;

public class AsteroidCollision_7 {
    //https://leetcode.com/problems/asteroid-collision/description/
    /*We are given an array asteroids of integers representing asteroids in a row.
    The indices of the asteriod in the array represent their relative position in space.

    For each asteroid, the absolute value represents its size,
    and the sign represents its direction (positive meaning right, negative meaning left).
    Each asteroid moves at the same speed.

    Find out the state of the asteroids after all collisions. If two asteroids meet,
    the smaller one will explode. If both are the same size, both will explode.
    Two asteroids moving in the same direction will never meet.

    Example 1:

    Input: asteroids = [5,10,-5]
    Output: [5,10]
    Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
    Example 2:

    Input: asteroids = [8,-8]
    Output: []
    Explanation: The 8 and -8 collide exploding each other.
    Example 3:

    Input: asteroids = [10,2,-5]
    Output: [10]
    Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.*/
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st=new Stack<>();
        for(int ast:asteroids){
            boolean isAsteroidDestroyed = false;
            while(!st.isEmpty() && !isSameDirection(st.peek(),ast) && !isAsteroidDestroyed){
                if(Math.abs(st.peek())<Math.abs(ast))
                    st.pop();
                else if(Math.abs(st.peek())==Math.abs(ast)){
                    st.pop();
                    isAsteroidDestroyed=true;
                }else
                    isAsteroidDestroyed=true;
            }
            if(!isAsteroidDestroyed)
                st.push(ast);
        }
        int[] arr=new int[st.size()];
        int i=arr.length-1;
        while(!st.isEmpty())
            arr[i--]=st.pop();
        return arr;
    }

    //the condition for collision is that
    //earlier asteroid in the stack is moving right
    //and the new asteroid is moving left
    private boolean isSameDirection(int st, int ast){
        String stDir=st<0?"left":"right";
        String astDir=ast<0?"left":"right";
        if(stDir.equals("right") && astDir.equals("left"))
            return false;
        return true;
    }
}

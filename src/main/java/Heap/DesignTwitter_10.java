package Heap;

import java.util.*;

public class DesignTwitter_10 {
    //https://leetcode.com/problems/design-twitter/
    /*Design a simplified version of Twitter where users can post tweets,
    follow/unfollow another user, and is able to see the 10 most recent tweets
    in the user's news feed.
    Implement the Twitter class:

    Twitter() Initializes your twitter object.
    void postTweet(int userId, int tweetId) Composes a new tweet with ID
        tweetId by the user userId. Each call to this function will be made
        with a unique tweetId.
    List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs
        in the user's news feed. Each item in the news feed must be posted by users
        who the user followed or by the user themself. Tweets must be ordered from most
        recent to least recent.
    void follow(int followerId, int followeeId) The user with ID followerId started
        following the user with ID followeeId.
    void unfollow(int followerId, int followeeId) The user with ID followerId started
        unfollowing the user with ID followeeId.


    Example 1:

    Input
    ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed",
        "unfollow", "getNewsFeed"]
    [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
    Output
    [null, null, [5], null, null, [6, 5], null, [5]]

    Explanation
    Twitter twitter = new Twitter();
    twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet
                                id -> [5]. return [5]
    twitter.follow(1, 2);    // User 1 follows user 2.
    twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet
                                ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because
                                it is posted after tweet id 5.
    twitter.unfollow(1, 2);  // User 1 unfollows user 2.
    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1
                                tweet id -> [5], since user 1 is no longer following user 2.*/

    //we will use only 2 data structures for storag
    //to store the follower info where get/put and remove operation has to be in O(1)
    //we will use a Map containing Hashset that has O(1) Time complexity

    //As we have further requirement to get the most recent tweets of a user and his followed user
    //we will maintain a counter that will increment after every tweet
    //so the tweets will be stored in a Map of userid and list of int array
    //array will contain the count and tweet Id. Count at which the tweet was posted
    public HashMap<Integer, HashSet<Integer>> follows;
    public HashMap<Integer,List<int[]>> tweets;
    public int count;
    public DesignTwitter_10() {
        follows=new HashMap<>();
        tweets=new HashMap<>();
        count=0;
    }

    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId,ArrayList::new).add(new int[]{count++,tweetId});
    }

    //use a max heap to get the most recently posted tweets of a user and his followees
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> news=new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[0]-a[0]);
        pq.addAll(tweets.computeIfAbsent(userId,ArrayList::new));
        for(int followeeID:follows.computeIfAbsent(userId,HashSet::new)){
            pq.addAll(tweets.computeIfAbsent(followeeID,ArrayList::new));
        }
        while(!pq.isEmpty() && news.size()<10){
            news.add(pq.poll()[1]);
        }
        return news;
    }

    public void follow(int followerId, int followeeId) {
        follows.computeIfAbsent(followerId,HashSet::new).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        follows.computeIfAbsent(followerId,HashSet::new).remove(followeeId);
    }

    public static void main(String[] args) {
        DesignTwitter_10 designTwitter10=new DesignTwitter_10();
        designTwitter10.postTweet(1,5);
        designTwitter10.getNewsFeed(1);
        designTwitter10.follow(1,2);
        designTwitter10.postTweet(2,6);
        designTwitter10.getNewsFeed(1);
        designTwitter10.unfollow(1,2);
        designTwitter10.getNewsFeed(1);
    }
}

package Heap;

import java.util.*;

public class KMaximumSumCombination_13 {
    public static class Pair{
        int x,y;
        public Pair(int x,int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        Arrays.sort(a);
        Arrays.sort(b);
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)->y[0]-x[0]);
        HashSet<Pair> set=new HashSet<>();

        int i=a.length-1; //start from maximum end
        int j=b.length-1;
        pq.add(new int[]{a[i]+b[j],i,j});
        set.add(new Pair(i,j));
        while(!pq.isEmpty() && ans.size()<k){
            int[] arr=pq.poll();
            ans.add(arr[0]);
            int x= arr[1];
            int y=arr[2];
            Pair p1=new Pair(x,y-1);
            Pair p2=new Pair(x-1,y);
            if(y-1>=0 && !set.contains(p1)){
                pq.add(new int[]{a[x]+b[y-1],x,y-1});
                set.add(p1);
            }
            if(x-1>=0 && !set.contains(p2)){
                pq.add(new int[]{a[x-1]+b[y],x-1,y});
                set.add(p2);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        KMaximumSumCombination_13 kMaximumSumCombination13=new KMaximumSumCombination_13();
        System.out.println(kMaximumSumCombination13.topKSumPairs(new int[]{3,2},new int[]{1,4},2));
        System.out.println(kMaximumSumCombination13.topKSumPairs(new int[]{1,4,2,3},new int[]{2,5,1,6},3));
    }
}

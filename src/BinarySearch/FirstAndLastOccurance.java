package BinarySearch;

public class FirstAndLastOccurance {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{Integer.MAX_VALUE,-1};
        bfs(0,nums.length-1,target,nums,ans);
        if(ans[0]==Integer.MAX_VALUE)
            ans[0]=-1;
        return ans;
    }

    public void bfs(int start, int end, int target, int[] nums, int[] ans){
        if(start>end)
            return;
        int mid = start +(end-start)/2;
        if(nums[mid]==target){
            ans[0]=Math.min(mid,ans[0]);
            ans[1]=Math.max(mid,ans[1]);
            bfs(start,mid-1,target,nums,ans);
            bfs(mid+1,end,target,nums,ans);
        }else if(nums[mid]<target){
            bfs(mid+1,end,target,nums,ans);
        }else{
            bfs(start,mid-1,target,nums,ans);
        }
    }

    public static void main(String[] arg){
        int[] nums = new int[]{5,7,7,8,8,10};
        FirstAndLastOccurance firstAndLastOccurance=new FirstAndLastOccurance();
        int[] ans = firstAndLastOccurance.searchRange(nums,6);
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }
}

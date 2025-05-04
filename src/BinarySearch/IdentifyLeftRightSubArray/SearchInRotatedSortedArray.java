package BinarySearch.IdentifyLeftRightSubArray;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int ans = -1;
        ans = bfs(0,nums.length-1,target,nums);
        return ans;
    }

    //In a rotated sorted array, at any index any one subarray left or right will be sorted
    //if mid is target then return mid
    //search for sorted subarray and check if target fits in that subarray
    //else search in the unsorted subarray
    public int bfs(int start, int end, int target, int[] nums){
        if(start>end)
            return -1;
        int mid = start +(end-start)/2;
        if(nums[mid]==target){
            return mid;
        }
        if(nums[start]<=nums[mid]){
            //left subarray is sorted
            //check if target is in the sorted sub array else go to other subarray
            if(nums[start]<=target && target<=nums[mid])
                return bfs(start,mid-1,target,nums);
            else
                return bfs(mid+1,end,target,nums);
        }else{
            //right subarray is sorted
            //check if target is in the sorted sub array else go to other subarray
            if(nums[mid]<=target && target<=nums[end])
                return bfs(mid+1,end,target,nums);
            else
                return bfs(start,mid-1,target,nums);
        }
    }

    public int bfsInRotatedSortedArrayWithDuplicates(int start, int end, int target, int[] nums){
        if(start>end)
            return -1;
        int mid = start +(end-start)/2;
        if(nums[mid]==target){
            return mid;
        }

        
        //The only change is the handling for edge case
        //nums[start]=nums[mid]=nums[end]
        //this will lead to difficulty in identifying sorted subarray
        //hence we will shrink search space by modifying low and high by 1 index
        // low+1 and high-1 until this condition is true
        while(start<=end &&nums[start]==nums[mid]&& nums[mid]==nums[end]){
            start+=1;
            end-=1;
        }
        if(start>end)
            return -1;


        if(nums[start]<=nums[mid]){
            //left subarray is sorted
            //check if target is in the sorted sub array else go to other subarray
            if(nums[start]<=target && target<=nums[mid])
                return bfsInRotatedSortedArrayWithDuplicates(start,mid-1,target,nums);
            else
                return bfsInRotatedSortedArrayWithDuplicates(mid+1,end,target,nums);
        }else{
            //right subarray is sorted
            //check if target is in the sorted sub array else go to other subarray
            if(nums[mid]<=target && target<=nums[end])
                return bfsInRotatedSortedArrayWithDuplicates(mid+1,end,target,nums);
            else
                return bfsInRotatedSortedArrayWithDuplicates(start,mid-1,target,nums);
        }
    }

    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int min_index=-1;
        int start = 0,end = nums.length-1;
        while(start<=end){
            int mid = start +(end-start)/2;
            //in a rotated sorted array at any index
            // left subarray or right subarray will be sorted
            //so we must identify the sorted subaaray and then set minimum as the start of that subarray
            //then search in unsorted subarray
            if(nums[start] <= nums[mid]){
                //left subarray is sorted
                if(min>nums[start]){
                    min = nums[start];
                    min_index=start;
                }
                start=mid+1;
            }else{
                //right subarray is sorted
                if(min>nums[mid]){
                    min = nums[mid];
                    min_index=mid;
                }
                end = mid-1;
            }
        }
        return min;
    }



    public static void main(String[] arg){
        int[] nums = new int[]{5,1,3};
        SearchInRotatedSortedArray searchInRotatedSortedArray=new SearchInRotatedSortedArray();
        int ans = searchInRotatedSortedArray.search(nums,5);
        System.out.println(ans);
        System.out.println(searchInRotatedSortedArray.bfsInRotatedSortedArrayWithDuplicates(0,0,
                0,new int[]{1}));

        System.out.println(searchInRotatedSortedArray.findMin(new int[]{3,4,5,1,2}));
        System.out.println(searchInRotatedSortedArray.findMin(new int[]{4,5,6,7,0,1,2}));
    }
}

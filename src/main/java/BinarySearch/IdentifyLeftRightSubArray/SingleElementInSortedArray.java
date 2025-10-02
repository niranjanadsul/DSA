package BinarySearch.IdentifyLeftRightSubArray;

public class SingleElementInSortedArray {

    /*
    * single element has condition preceding and succeding elements do not match
    * edge cases: array size is 1, or last and first position
    * in the left subarray every repeated element should have first occurance at even index
    * and second occurance at odd index.
    * In right sub array, repeated elements should have first occurance at odd index
    * and second occurance at even index
    * if at any index this rule breaks then single element should be at the left
    * */
    public int singleNonDuplicate(int[] nums) {
        //edge cases
        if(nums.length==1)
            return nums[0];
        if(nums[0]!= nums[1])
            return nums[0];
        if(nums[nums.length-2]!= nums[nums.length-1])
            return nums[nums.length-1];

        int start=1,end = nums.length-2;
        int ele = -1;
        while(start<=end){
            int mid = start + (end-start)/2;

            if(nums[mid-1]!=nums[mid] && nums[mid]!=nums[mid+1]) {
                ele = nums[mid];
                break;
            }

            if(mid%2==0){
                //mid is even
                if(nums[mid]==nums[mid+1]){
                    //in left sub array
                    start=mid+1;
                }else{
                    end = mid-1;
                }

            }else{
                //mid is odd
                if(nums[mid]==nums[mid+1]){
                    //in right sub array
                    end=mid-1;
                }else{
                    start = mid+1;
                }
            }
        }
        return ele;
    }

    public static void main(String[] args){
        SingleElementInSortedArray singleElementInSortedArray=new SingleElementInSortedArray();
        System.out.println(singleElementInSortedArray.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
    }
}

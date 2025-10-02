package BinarySearch;

public class SpaceStationMission_VISA {

    public int completeMissions(int[] alphaToBeta, int[] betaToAlpha, int missions){
        //it is guaranteed that all missions are possible
        int time=0;
        for(int i=0;i<missions;i++){
            int floor=floor(alphaToBeta,time);
            if(floor==-1 || floor<time)
                floor=ceil(alphaToBeta,time);
            time = floor+100;
            //reached at beta
            floor=floor(betaToAlpha,time);
            if(floor==-1 || floor<time)
                floor=ceil(betaToAlpha,time);
            time=floor+100;
            //reached back to alpha
        }
        return time;
    }

    public static void main(String[] args) {
        SpaceStationMission_VISA spaceStationMissionVisa=new SpaceStationMission_VISA();
        System.out.println(spaceStationMissionVisa.completeMissions(new int[]{0,200,500}, new int[]{99,210,450},1));//310
    }

    public int floor(int[] num, int target){
        int start=0,end=num.length-1;
        int floor=-1;
        while(start<=end){
            int mid = start+(end-start)/2;
            if(num[mid]==target)
                return target;
            if(num[mid]<target) {
                floor=Math.max(num[mid],floor);
                start=mid+1;
            }else{
                end=mid-1;
            }

        }
        return floor;
    }

    public int ceil(int[] num, int target){
        int start=0,end=num.length-1;
        int ceiling=Integer.MAX_VALUE;
        while(start<=end){
            int mid = start+(end-start)/2;
            if(num[mid]==target)
                return target;
            if(num[mid]>target) {
                ceiling=Math.min(num[mid],ceiling);
                end=mid-1;
            }else{
                start=mid+1;
            }

        }
        if(ceiling==Integer.MAX_VALUE)
            ceiling=-1;
        return ceiling;
    }
}

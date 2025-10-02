import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void amazon(){
        int[] warehouseCapacity = new int[]{0,2,5,9,12,18};
        int[][] additionalHubs = new int[][]{{2,5},{1,3}};
        int[] cost= new int[additionalHubs.length];

        int itr=0;
        for(int[] hubs:additionalHubs){
            int c = 0;
            int h1 = hubs[0]-1;
            int h2 = hubs[1]-1;
            int h3 = warehouseCapacity.length-1;

            for(int i=0;i<=h1;i++){
                c+=warehouseCapacity[h1]-warehouseCapacity[i];
            }
            for(int i=h1+1;i<=h2;i++){
                c+=warehouseCapacity[h2]-warehouseCapacity[i];
            }
            for(int i=h2+1;i<=h3;i++){
                c+=warehouseCapacity[h3]-warehouseCapacity[i];
            }
            cost[itr]=c;
            itr++;
        }
        for(int cst:cost) {
            System.out.println(cst);
        }
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        amazon();
        System.exit(1);

        String s1 = "-0333";
        String s2 = "2";

        System.out.println(getNumber(s1)*getNumber(s2));

        Node root =createBinaryTree();
        TreeMap<Integer,TreeMap<Integer,List<Integer>>> gridMap = new TreeMap<>();
//        Comparator<Integer> ascOrder = (Comparator<Integer>) gridMap.comparator();
//        getTopView(0,0,root,gridMap,ascOrder);
//        printTopView(gridMap);

        getTopView(0,0,root,gridMap,Collections.reverseOrder());
        printBottomView(gridMap);
    }

    public static int getNumber(String s){
        int number = 0;
        int leftEnd = 0;
        if(s.contains("-"))
            leftEnd=1;
        for(int i = s.length()-1;i>=leftEnd;i--){
            int base = 1;
            int times = s.length()-1-i;
            while(times>0){
                base*=10;
                times--;
            }
            number+=(s.charAt(i)-'0')*base;
        }
        if(s.contains("-"))
            number*=-1;
        return number;
    }

    public static void getTopView(int row, int col, Node node,
                                  TreeMap<Integer,TreeMap<Integer,List<Integer>>> gridMap,
                                  Comparator<Integer> order){
        if(node==null){
            return;
        }
        gridMap.computeIfAbsent(col,k -> new TreeMap<>(order))
                .computeIfAbsent(row,k->new ArrayList<>())
                .add(node.data);

        getTopView(row+1,col-1,node.left,gridMap,order);
        getTopView(row+1,col+1,node.right,gridMap,order);
    }

    public static void printTopView(TreeMap<Integer,TreeMap<Integer,List<Integer>>> gridMap){
        for(Map.Entry<Integer,TreeMap<Integer,List<Integer>>> entry : gridMap.entrySet()){
            TreeMap<Integer,List<Integer>> rowMap = entry.getValue();
            for(Map.Entry<Integer,List<Integer>> rowEntry: rowMap.entrySet()){
                int value = rowEntry.getValue().get(0);
                System.out.println(value);
                break;
            }
        }
    }

    public static void printBottomView(TreeMap<Integer,TreeMap<Integer,List<Integer>>> gridMap){
        for(Map.Entry<Integer,TreeMap<Integer,List<Integer>>> entry : gridMap.entrySet()){
            TreeMap<Integer,List<Integer>> rowMap = entry.getValue();
            for(Map.Entry<Integer,List<Integer>> rowEntry: rowMap.entrySet()){
                int index = rowEntry.getValue().size()-1;
                System.out.println(rowEntry.getValue().get(index));
                break;
            }
        }
    }

    public static Node createBinaryTree(){
        Node firstNode = new Node(1);
        Node secondNode = new Node(2);
        Node thirdNode = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        // Connect binary tree nodes
        firstNode.left = secondNode;
        firstNode.right = thirdNode;
        secondNode.left = node4;
        secondNode.right = node5;
        thirdNode.left = node6;
        thirdNode.right = node7;

        return firstNode;
    }
}
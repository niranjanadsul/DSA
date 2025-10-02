package UBSTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchSystem {

    public static void main(String[] args) {
        int  n = 8;
        int sum=Stream.iterate(new int[]{0,1},
                x -> new int[]{x[1],x[0]+x[1]}).limit(n).map(x->x[0]).collect(Collectors.toList()).stream().distinct().filter(i->i%2==0).mapToInt(i->i).sum();
        System.out.println(sum);
    }
}

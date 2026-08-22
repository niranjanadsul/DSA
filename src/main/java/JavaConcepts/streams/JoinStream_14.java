package JavaConcepts.streams;

import java.util.Arrays;
import java.util.stream.Collectors;

public class JoinStream_14 {
    public static void main(String[] args) {
        //lets join string without stream
        System.out.println("Join string without stream**********");
        String[] arr = {"a", "b", "c", "d"};
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
            sb.append(",");
        }
        String result = sb.toString();
        result = result.substring(0, result.length() - 1);
        System.out.println(result);

        System.out.println("Join string with stream**********");
        //now lets join string with stream
        //without delimiter
        System.out.println("Join string without delimiter**********");
        String result2 = Arrays.stream(arr).collect(Collectors.joining());
        System.out.println(result2);

        //with delimiter
        System.out.println("Join string with delimiter**********");
        String result3 = Arrays.stream(arr).collect(Collectors.joining(","));
        System.out.println(result3);

        //transform to upper case and join with delimiter
        System.out.println("Join string with delimiter and transform to upper case**********");
        String result4 = Arrays.stream(arr).map(String::toUpperCase).collect(Collectors.joining(","));
        System.out.println(result4);
    }
}

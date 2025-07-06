package Array.TwoPointer;

import java.util.Arrays;

public class MinimumChangesToMakeAnagram_2 {
    //Asked for JP morgan
    //https://www.geeksforgeeks.org/minimum-number-of-manipulations-required-to-make-two-strings-anagram-without-deletion-of-character/
    //Two pointer approach
    static int minManipulation(String s) {
        String s1=s.substring(0,s.length()/2);
        String s2=s.substring(s.length()/2);
        // Sort the characters of both strings
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int i = 0, j = 0, count = 0;

        // Compare characters in sorted strings
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                i++;
                j++;
            }
            else if (arr1[i] < arr2[j]) {
                i++;
                count++;
            }
            else {
                j++;
                count++;
            }
        }

        // Count the remaining characters in both strings
        while (i < arr1.length) {
            i++;
            count++;
        }

        while (j < arr2.length) {
            j++;
            count++;
        }

        // Return the count divided by 2
        return count / 2;
    }

    public static void main(String[] args) {
        System.out.println(minManipulation("123212"));//1
        System.out.println(minManipulation("123456"));//3
    }
}

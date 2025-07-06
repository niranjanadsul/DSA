package JPMorganTest;

public class ReplaceOccurancesTargetFromString {
    //remove all occurrances of STring AWS from input string

    public String removeAWS(String s, String part){
        char[] input = s.toCharArray();
        char[] target = part.toCharArray();
        char[] resultStack = new char[input.length];
        int stackSize = 0, targetLength = target.length;
        char targetEndChar = target[targetLength - 1];

        for (char currentChar : input) {
            resultStack[stackSize++] = currentChar;

            if (currentChar == targetEndChar && stackSize >= targetLength) {
                int i = stackSize - 1, j = targetLength - 1;

                while (j >= 0 && resultStack[i] == target[j]) {
                    i--;
                    j--;
                }

                if (j < 0) {
                    stackSize = i + 1;
                }
            }
        }
        String ans=new String(resultStack, 0, stackSize);
        return ans.isEmpty()?"-1":ans;
    }

    public static void main(String[] args) {
        ReplaceOccurancesTargetFromString replaceOccurancesTargetFromString=
                new ReplaceOccurancesTargetFromString();
        System.out.println(replaceOccurancesTargetFromString.removeAWS("AWAWSSG","AWS"));//G
        System.out.println(replaceOccurancesTargetFromString.removeAWS("AWAWSS","AWS"));//-1
        System.out.println(replaceOccurancesTargetFromString.removeAWS("AWAWSS","AWS"));//-1

    }
}

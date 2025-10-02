package Graphs.MinimumSpanningTree_5;

import java.util.*;

public class AccountsMerge_6 {
    //https://leetcode.com/problems/accounts-merge/solutions/6940291/striver-s-method-with-dry-run-optimal-approach/
    /*Given a list of accounts where each element accounts[i] is a list of strings,
    where the first element accounts[i][0] is a name,
    and the rest of the elements are emails representing emails of the account.
    Now, we would like to merge these accounts. Two accounts definitely belong to the same person
    if there is some common email to both accounts. Note that even if two accounts have the same name,
    they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

    After merging the accounts, return the accounts in the following format:
    the first element of each account is the name,
    and the rest of the elements are emails in sorted order.
    The accounts themselves can be returned in any order.

    Example 1:

    Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],
    ["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],
    ["John","johnnybravo@mail.com"]]
    Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
    ["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
    Explanation:
    The first and second John's are the same person as they have the common email "johnsmith@mail.com".
    The third John and Mary are different people as none of their email addresses are used by other
    accounts.
    We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'],
    ['John', 'johnnybravo@mail.com'],
    ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

    Example 2:

    Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],
    ["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
    ["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],
    ["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
    Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],
    ["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],
    ["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],
    ["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],
    ["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]*/


    /*Complexity Analysis
    Time Complexity:

    O(N * K * α(N))
        N = number of accounts
        K = maximum number of emails per account
        α(N) = inverse Ackermann function (almost constant) for Union-Find operations.
        Sorting emails in each group: O(T log T), where T is total number of unique emails.
        Overall, the solution is efficient for large input sizes.

    Space Complexity:

    O(N + T)
        N = number of accounts (for DSU arrays)
        T = total number of unique emails (for maps and groupings).*/
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int[] par=new int[accounts.size()];
        for(int i =0;i<par.length;i++)
            par[i]=i;
        int[] size=new int[accounts.size()];
        Arrays.fill(size,1);
        //initially every account is a separate component
        //we need to merge these components

        //TC = O(numberOfAccount * count Of Max Emails * time for disjoint set operations)
        Map<String,Integer> emailToAccountNo = new HashMap<>();
        for(int i=0;i<accounts.size();i++){
            for(int j=1;j<accounts.get(i).size();j++){
                String email=accounts.get(i).get(j);
                if (emailToAccountNo.containsKey(email)) unionSet(par, size, emailToAccountNo.get(email), i);
                else emailToAccountNo.put(email, i);
            }
        }

        for(int i=0;i<accounts.size();i++){
            for(int j=1;j<accounts.get(i).size();j++){
                String email=accounts.get(i).get(j);
                if (emailToAccountNo.containsKey(email)) unionSet(par, size, emailToAccountNo.get(email), i);
                else emailToAccountNo.put(email, i);
            }
        }

        Map<Integer, TreeSet<String>> hm=new HashMap<>();
        for(int i=0;i<par.length;i++){
            List<String> currentAccEmails= accounts.get(i).subList(1,accounts.get(i).size());
            int parentAccNo=par[i];
            hm.computeIfAbsent(parentAccNo,x->new TreeSet<>()).addAll(currentAccEmails);
        }

        List<List<String>> ans=new ArrayList<>();
        for(Map.Entry<Integer, TreeSet<String>> entry:hm.entrySet()){
            ArrayList<String> acc=new ArrayList<>();
            acc.add(accounts.get(entry.getKey()).get(0));
            acc.addAll(entry.getValue());
            ans.add(acc);
        }
        return ans;
    }

    public int find(int par[], int x) {
        if(par[x]==x)
            return x;
        return par[x]=find(par,par[x]);//path compression
    }

    public boolean unionSet(int[] par, int[] size, int x, int z) {
        //union by size
        int UPu=find(par,x);
        int UPv=find(par,z);
        if(UPu==UPv)
            return true;
        if(size[UPu]<size[UPv]){
            par[UPu]=UPv;
            size[UPv]+=size[UPu];
        }else{
            par[UPv]=UPu;
            size[UPu]+=size[UPv];
        }
        return false;
    }

    public static void main(String[] args) {
        AccountsMerge_6 accountsMerge6=new AccountsMerge_6();
        List<List<String>> accounts = new ArrayList<>();

        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accountsMerge6.accountsMerge(accounts);
    }
}

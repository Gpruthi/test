import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

       // System.out.println("LCS : "+ longestCommonSubsequence("AGGTAB", "GXTXAYB"));
       // System.out.println("LCS : "+ longestCommonSubsequences("AGGTAB", "GXTXAYB"));

        int arr[] = { 12, 4,  7,  9,  2,  23, 25, 41, 30,
                40, 28, 42, 30, 44, 48, 43, 50 };

        List<Integer> li = new ArrayList();
        int cho[] = {23, 14, 15, 14, 56, 29, 14};

      //  System.out.println("chocolate dis : " + chocolateDistributionProblem(arr, 7));
        System.out.println("chocolates dis 2 : " + chocolateDistributionProblem2(cho));
        System.out.println(fib(9));
    }




    public static int chocolateDistributionProblem(int arr[], int m) {
        int size = arr.length;
        if(m==0 || size ==0)
            return 0;

        if(m>size)
            return 0;

        int min_diff = Integer.MAX_VALUE;

        Arrays.sort(arr);

        for(int i=0; i+m-1 < size;++i) {
            if(arr[i+m-1] - arr[i] < min_diff)
                min_diff = arr[i+m-1] - arr[i];
        }

        return min_diff;

    }

    public static int chocolateDistributionProblem2(int arr[]) {
        int size = arr.length;
        if(size ==0)
            return 0;

        int[] chocolates = new int[arr.length];
        chocolates[0]=1;
        for(int i=1; i< arr.length; ++i) {
            if(arr[i-1] < arr[i])
                chocolates[i] = chocolates[i-1] +1;
            if(arr[i-1] > arr[i]) {
                if(chocolates[i-1] ==1) {
                    for (int m =i; m >= 2; --m) {
                        if (chocolates[m-2] > chocolates[m - 1])
                            chocolates[m-1] = chocolates[m-1] + 1;
                    }
                    if (i == 1) {
                        chocolates[i - 1] = chocolates[i - 1] + 1;
                    }
                    chocolates[i]=1;
                } else
                    chocolates[i]=1;
            }
            if(arr[i-1] ==arr[i])
                chocolates[i] = chocolates[i-1];


        }
        for(int i=0; i<chocolates.length;++i)
            System.out.println(chocolates[i]);


        return Arrays.stream(chocolates).sum();

    }


    private static int longestCommonSubsequence(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        int[][] sol = new int[m+1][n+1];

        for(int i=0;i<=m;++i) {
            for(int j=0; j<=n;++j){
                if(i==0 || j==0)
                    sol[i][j] =0;
                else if(s1.charAt(i-1) == s2.charAt(j-1))
                    sol[i][j] = sol[i-1][j-1] +1;
                else
                    sol[i][j] = Math.max(sol[i-1][j], sol[i][j-1]);
            }
        }
        return sol[m][n];
    }

    public static String longestCommonSubsequences(String str1, String str2)
    {
        List<String> subsequences1 = generateSubsequences(str1);
        List<String> subsequences2 = generateSubsequences(str2);

        String lcs = "";
        for (String subsequence1 : subsequences1) {
            for (String subsequence2 : subsequences2) {
                if (subsequence1.equals(subsequence2)
                        && subsequence1.length()
                        > lcs.length()) {
                    lcs = subsequence1;
                }
            }
        }
        return lcs;
    }

    public static List<String> generateSubsequences(String str)
    {
        List<String> subsequences = new ArrayList<>();
        generateSubsequencesHelper(str, "", 0,
                subsequences);
        return subsequences;
    }

    public static void generateSubsequencesHelper(String str, String subsequence, int index, List<String> subsequences) {
        if (index == str.length()) {
            subsequences.add(subsequence);
            return;
        }
        generateSubsequencesHelper(str, subsequence,
                index + 1, subsequences);
        generateSubsequencesHelper(
                str, subsequence + str.charAt(index), index + 1,
                subsequences);
    }

    public static int  fib(int n) {
        int a=0;
        int b=1;
        int c=0;

        if(n <=1)
            return n;
        else {
            for(int i=1;i<n;++i) {
                c = a + b;
                a = b;
                b = c;
            }
            return c;
        }
    }

    public static List<Integer> findDuplicates(List<Integer> arr) {

        HashMap<Integer, Integer> map = new HashMap<>();

        List<Integer> list = Arrays.asList(10, 28, 87, 10, 20, 76, 28, 80, 80, 80);

        for(Integer dup : arr) {
            if(map.containsKey(dup)) {
                map.put(dup, map.get(dup)+1);
            }else {
                map.put(dup, 1);
            }
        }

        List<Integer> duplicates = arr.stream().distinct().collect(Collectors.toList());

        return duplicates;
    }
































}
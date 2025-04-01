import com.sun.org.apache.bcel.internal.generic.SWAP;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.*;

/**
 * ClassName: Solution
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author CYT
 * @Create 2024/4/16 19:07
 * @Version 1.0
 */
public class Solution {
}
class Solution1{
    public static void main(String[] args) {
        String str = "youyouyou";
        int count = minChar(str);
        System.out.println(count);
    }
    public static int minChar(String str){
        int length = str.length();
        int count = 0;
        char[] chars = str.toCharArray();
        for (int i = 1; i < length ; i++) {
            if(chars[i-1] == 'y' && chars[i] == 'u'){
                count++;
            }
        }
        return count;
    }
}
class Solution2{
    public static int maxMatches(int n,int[] a, int[] b, int[] c) {

        int matches = 0;

        for (int i = 0; i < n; i++) {
            if(a[i] + b[i] == c[i]){
                matches++;
            }else{
                int num = a[i] + b[i];
                for (int j = 0;j < n;j++){
                    if(num == c[j]){
                        int temp = c[i];
                        c[i] = c[j];
                        c[j] = temp;
                        matches++;
                    }
                }
            }
        }

        return matches;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        int[] b = {2, 3, 4, 5};
        int[] c = {4, 5, 6, 7};

        int maxMatches = maxMatches(4,a, b, c);
        System.out.println("最大匹配数量：" + maxMatches);
    }
}


class PrimeNumberMerge {

    // 是否为素数
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    // 找数组中的所有素数
    public static ArrayList<Integer> findPrimes(int[] arr) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int num : arr) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }
        return primes;
    }
    // 合并
    public static int[] mergePrimes(int[] arr) {
        ArrayList<Integer> primes = findPrimes(arr);
        ArrayList<Integer> merged = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (!primes.contains(arr[i])) {
                merged.add(arr[i]);
                continue;
            }
            if (i < arr.length - 1 && primes.contains(arr[i + 1])) {
                int mergedNum = arr[i] + arr[i + 1];
                merged.add(mergedNum);
                i++;
            } else {
                merged.add(arr[i]);
            }
        }
        int[] result = new int[merged.size()];
        for (int i = 0; i < merged.size(); i++) {
            result[i] = merged.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,5,4};

        while (true) {
            int[] mergedArr = mergePrimes(arr);
            if (mergedArr.length == arr.length) {
                break;
            }
            arr = mergedArr;
        }
        System.out.println("最终数组：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("数组长度为：");
        System.out.println(arr.length);
    }
}

class Soultion3{
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] depth;
    static int diameter;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        graph = new ArrayList<>();
        visited = new boolean[n];
        depth = new int[n];

        for (int i = 0;i < n;i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i = 0;i < n-1;i++){
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            sc.nextLine();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        diameter = 0;
        dfs(0,-1,0);
        for (int i = 0; i < n; i++) {
            System.out.println("f(" + (i+1) + ") = " + calculateFi(i, -1, 0));
        }

    }
    static void dfs(int node, int parent, int currentDepth) {
        visited[node] = true;
        depth[node] = currentDepth;

        for (int neighbor : graph.get(node)) {
            if (neighbor != parent && !visited[neighbor]) {
                dfs(neighbor, node, currentDepth + 1);
            }
        }

        // 更新树的直径
        diameter = Math.max(diameter, currentDepth);
    }

    // 计算每个节点的f(i)值
    static int calculateFi(int node, int parent, int parentDepth) {
        int maxDepth = depth[node];

        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                maxDepth = Math.max(maxDepth, calculateFi(neighbor, node, depth[node] + 1));
            }
        }

        // 根据定义，f(i) = 对i节点上再连接一个新的叶子节点后，树的直径长度
        // 所以 f(i) = max(树的直径长度, parentDepth + 1)
        return Math.max(maxDepth, parentDepth + 1);
    }

//    public static int[] dfs(int node,int parent){
//        visited[node] = true;
//        int maxDepth = 0;
//        int diameter = 0;
//
//        for(int neighbor : graph.get(node)){
//            if(neighbor!=parent){
//                if(!visited[neighbor]) {
//                    int[] result = dfs(neighbor, node);
//                    maxDepth = Math.max(maxDepth, result[0]);
//                    diameter = Math.max(diameter, result[1]);
//
//                }
//            }
//        }
//        depth[node] = maxDepth + 1;
//        int f1 = Math.max(maxDepth,diameter + 1);
//        diameter = Math.max(diameter,maxDepth + 1);
//        return new int[]{depth[node],diameter,f1};
//    }


}
class selectSort{
    public static void main(String[] args) {
        int[] arr = {7,1,3,5,2,4,6,7,6,2};
        printArray(arr);
//        select(arr);
        bubbleSort(arr);
        printArray(arr);
    }
    public static void printArray(int[] arr){
        for(int i = 0;i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void select(int[] arr){
        if(null == arr || arr.length < 2){
            return;
        }
        // 0 ~ n-1
        // 1 ~ n-1
        // ...

        for(int i = 0; i < arr.length; i++){
            int minIndex = i;
            for(int j = i + 1;j < arr.length;j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr,i,minIndex);
        }
    }
    public static void swap(int[] arr, int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubbleSort(int[] arr){
        if(null == arr || arr.length < 2){
            return;
        }
        int N = arr.length;
        for(int end = N - 1; end >= 0; end--){
            for(int second = 1;second <= end ; second ++){
                if(arr[second-1] >arr[second]){
                    swap(arr,second-1,second);
                }
            }
        }
    }

    public static void insertSort(int[] arr){
        if(null == arr || arr.length < 2){
            return;
        }
        for(int end = 1; end < arr.length;end++){
            int newNumIndex = end;
            while(newNumIndex - 1 >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]){
                swap(arr,newNumIndex - 1,newNumIndex);
                newNumIndex--;
            }
        }
    }
}
class Solution6 {
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}

class Solution7{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个正整数: ");
        int N = scanner.nextInt();

        for (int i = N; i > 0; i--) {
            // 打印前导空格
            for (int j = 0; j < N - i; j++) {
                System.out.print(" ");
            }
            // 打印数字
            for (int j = 0; j < i; j++) {
                System.out.print(i);
            }
            // 换行
            System.out.println();
        }

        scanner.close();
    }
}
class Solution8{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串长度：");
        int N = scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入字符串：");
        String str = scanner.nextLine();

        Map<Character,Integer> oddMap = new HashMap<>();
        Map<Character,Integer> evenMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            char ch = str.charAt(i);
            if((i + 1) % 2 == 0){
                oddMap.put(ch,oddMap.getOrDefault(ch,0)+1);
            }else{
                evenMap.put(ch,evenMap.getOrDefault(ch,0)+1);
            }
        }
        int count = 0;
        for (char ch : oddMap.keySet()) {
            int oddCount = oddMap.getOrDefault(ch,0);
            int evenCount = evenMap.getOrDefault(ch,0);
            if (oddCount == evenCount){
                count++;
            }
        }
        scanner.close();
        System.out.println("满足条件的种类"+count);
    }
}

class Solution9{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();
        int[] arr = new int[n];
        for(int i = 0;i < n;i++){
            arr[i] = scanner.nextInt();
        }
        if (k > 0) {
            int max = -1;
            // 找到第一次的最大值
            for (int i = 0; i < n; i++) {
                max = Math.max(arr[i], max);
            }
            // 更新数组
            for (int i = 0; i < n; i++) {
                arr[i] = max - arr[i];
            }

            if (k % 2 == 0) {
                max = -1;
                for (int i = 0; i < n; i++) {
                    max = Math.max(arr[i], max);
                }
                for (int i = 0; i < n; i++) {
                    arr[i] = max - arr[i];
                }
            }
        }
        for(int a : arr){
            System.out.print(a+" ");
        }
    }
}
class Solution10{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Map<Integer,String> map = new LinkedHashMap<>();
        for(int i = 0;i < n;i++){
            String str = scanner.next();
            int x = scanner.nextInt();
            if (map.containsKey(x)){
                continue;
            }
            map.put(x,str);
        }
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            max = Math.max(entry.getKey(), max);
        }
        System.out.println(map.get(max));
    }
}
class Solution11{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < m; i++) {
            int num = scanner.nextInt();
            stack.push(num);
            while (stack.size() > 1) {
                int top = stack.pop();
                if (stack.peek() == top) {
                    stack.pop();
                    stack.push(top + 1);
                } else {
                    stack.push(top);
                    break;
                }
            }
        }

        // 输出栈中的数字，自底向上
        for (int num : stack) {
            System.out.print(num + " ");
        }
    }
}
class Solution12{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[] stack = new int[m];
        int top = -1;

        for (int i = 0; i < m; i++) {
            int num = scanner.nextInt();
            stack[++top] = num;


            while (top > 0 && stack[top] == stack[top - 1]) {
                //合并
                stack[--top]++;
            }
        }

        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " ");
        }
    }
}
class Solution13{
    public static void main(String[] args) {
        String str = "abc.cad.bbc";
        String s2 = "\\.\\s*";
        String[] split = str.split(s2);
        for (String s : split) {
            System.out.print(s+" ");
        }
    }
}
class Solution14{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        BigInteger N = new BigInteger(str);
        BigInteger ZERO = BigInteger.ZERO;
        BigInteger m = new BigInteger("100");
        BigInteger count = BigInteger.ZERO;
        if (N.compareTo(ZERO)>0) {
            count = N.divide(m);
        }
        // 打印结果
        System.out.println("0 到 " + N + " 之间有 " + count + " 个数是 100 的整数倍。");
    }
}
class Solution15{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取 n 和 m，表示网格的大小
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 初始化网格板，false 表示白色单元格
        boolean[][] grid = new boolean[n][m];

        // 读取操作数 k
        int k = scanner.nextInt();

        // 执行 k 个操作
        for (int i = 0; i < k; i++) {
            // 读取操作类型
            char operation = scanner.next().charAt(0);
            int x = scanner.nextInt() - 1;  // 将输入的坐标转换为从0开始的索引
            int y = scanner.nextInt() - 1;

            if (operation == 'c') {
                // 将位置 (x, y) 涂成黑色
                grid[x][y] = true;
            } else if (operation == 'l') {
                // 向左寻找第一个白色单元格
                boolean found = false;
                for (int j = y - 1; j >= 0; j--) {
                    if (!grid[x][j]) {
                        System.out.println("(" + (x + 1) + ", " + (j + 1) + ")");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("(-1, -1)");
                }
            } else if (operation == 'r') {
                // 向右寻找第一个白色单元格
                boolean found = false;
                for (int j = y + 1; j < m; j++) {
                    if (!grid[x][j]) {
                        System.out.println("(" + (x + 1) + ", " + (j + 1) + ")");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("(-1, -1)");
                }
            } else if (operation == 'u') {
                // 向上寻找第一个白色单元格
                boolean found = false;
                for (int j = x - 1; j >= 0; j--) {
                    if (!grid[j][y]) {
                        System.out.println("(" + (j + 1) + ", " + (y + 1) + ")");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("(-1, -1)");
                }
            } else if (operation == 'd') {
                // 向下寻找第一个白色单元格
                boolean found = false;
                for (int j = x + 1; j < n; j++) {
                    if (!grid[j][y]) {
                        System.out.println("(" + (j + 1) + ", " + (y + 1) + ")");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("(-1, -1)");
                }
            }
        }
        scanner.close();
    }
}
class Solution16{
    public static void main(String[] args) {
        String[] stringArray = new String[]{"141414","fafafaf","fafafaffafa"};
        stringArray[0] = "555555";
        System.out.println(Arrays.toString(stringArray));
     }
}
class Solution17{
    public static void main(String[] args) {
        String str = "ababaaaba";
        int n = str.length();
        int[] pi = new int[n];
        pi[0] = 0;
        for(int i = 1, j = 0; i < n; i++){
            while(j > 0 && str.charAt(i) != str.charAt(j)){
                j = pi[j - 1];
            }
            if(str.charAt(i) == str.charAt(j)){
                j++;
            }
            pi[i] = j;
        }
        System.out.println(Arrays.toString(pi));
    }
}
class Solution18{
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,4};
        int target = 6;
        int[] ints = twoSum(nums, target);
        System.out.println(Arrays.toString(ints));
    }
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == num) {
                    result[0] = i;
                    result[1] = j;

                }
            }
        }
        return result;
    }
}
class Solution19{
    public static void main(String[] args) {

    }
}
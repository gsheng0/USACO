import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JustStalling {
    public static int[] sorted;
    public static int[] reversed;
    public static int run(){
        int num = r.nextInt();
        int[] cows = new int[num];
        int[] stalls = new int[num];
        for(int i = 0; i < 4; i++){
            cows[i] = r.nextInt();
        }
        for(int i = 0; i < 4; i++){
            stalls[i] = r.nextInt();
        }
        Arrays.sort(cows);
        sorted = Arrays.copyOf(cows, num);
        reversed = spliceAndReverse(sorted);
        ArrayList<int[]> permutations = new ArrayList<>();
        permutations.add(sorted);
        while(true){
            cows = nextPermutation(cows);
            if(equals(cows, sorted)){
                break;
            }
            permutations.add(Arrays.copyOf(cows, num));
        }
        int size = 0;
        for(int i = 0; i < permutations.size(); i++){
            if(isValid(permutations.get(i), stalls)){
                size++;
            }
        }
        return size;
    }
    public static int test(){
        return r.nextInt();
    }
    public static int[] nextPermutation(int[] nums) {
        if(nums.length == 1){
            return nums;
        }
        else if(nums.length == 2){
            return new int[] {nums[1], nums[0]};
        }
        int first = -1;
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] < nums[i + 1]){
                first = i;
            }
        }
        if(first == -1){

            Arrays.sort(nums);
            return (nums);
        }
        int second = -1;
        for(int i = first + 1; i < nums.length; i++){
            if(nums[first] < nums[i]){
                second = i;
            }
        }

        if(second == -1){
            return nums;
        }
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
        reverse(nums, first + 1, nums.length);


        return nums;
    }
    public static void reverse(int[] arr, int l, int r){
        int[] output = new int[r - l];
        for(int i = 0; i < r - l; i++){
            output[i] = arr[r - 1 - i];
        }
        for(int i = 0; i < output.length; i++){
            arr[l + i] = output[i];
        }
    }
    public static void reverse(int[] arr){
        reverse(arr, 0, arr.length);
    }
    public static void reverse(int[] arr, int l){
        reverse(arr, l, arr.length);
    }
    public static int[] spliceAndReverse(int[] arr, int start, int end){
        int length = end - start;
        int[] out = new int[length];
        for(int i = 0; i < length; i++){
            out[i] = arr[end - 1 - i];
        }
        return out;
    }
    public static int[] spliceAndReverse(int[] arr){
        return spliceAndReverse(arr, 0, arr.length);
    }
    public static boolean isValid(int[] cows, int[] stalls){
        for(int i = 0; i < cows.length; i++){
            if(cows[i] > stalls[i]){
                return false;
            }
        }
        return true;
    }
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static boolean equals(int[] a, int[] b){
        for(int i = 0; i < a.length; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }

    private static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        String next() { // reads in the next string
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() { // reads in the next int
            return Integer.parseInt(next());
        }
        public long nextLong() { // reads in the next long
            return Long.parseLong(next());
        }
        public double nextDouble() { // reads in the next double
            return Double.parseDouble(next());
        }
    }

    private static InputReader r = new InputReader(System.in);
    private static PrintWriter pw = new PrintWriter(System.out);
    private static InputStream createInputStream() throws Exception {
        Class clazz = Template.class;
        return clazz.getResourceAsStream("/" + clazz.getSimpleName() + ".in");
    }
    private static InputStream createInputStream(String name) throws Exception {
        Class clazz = Template.class;
        return clazz.getResourceAsStream("/" + name + ".in");
    }
    private static InputStream createInputStream(String name, String suffix) throws Exception {
        Class clazz = Template.class;
        return clazz.getResourceAsStream("/" + name + "." + suffix);
    }
    public static void testCases(int num) throws Exception{
        for(int i = 1; i <= num; i++) {
            r = new InputReader(createInputStream("2"));
            int out = run();
            r = new InputReader(createInputStream("2", "out"));
            int answer = test();
            System.out.println("Test " + i + " " + (answer == out ? "Passed" : "Failed"));
        }
        pw.close();
    }
    public static void testSample() throws Exception {
        r = new InputReader(createInputStream());
        int out = run();
        System.out.println(out);
        pw.close();
    }
    public static void main(String[] args) throws Exception {
        testCases(12);
    }
}

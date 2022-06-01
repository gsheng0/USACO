package misc;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Differences {
    public static final Class clazz = Differences.class;
    private static class Diff implements Comparable<Diff>{
        int x, y;
        int difference;
        public Diff(int x, int y){
            this.x = x;
            this.y = y;
            difference = Math.abs(x - y);
        }


        @Override
        public int compareTo(Diff o) {
            if(this.difference > o.difference){
                return 1;
            }
            else if(this.difference == o.difference){
                return 0;
            }
            return -1;
        }
    }
    public static int[] run(){
        int cases = 1;
        for(int z = 0; z < cases; z++){
            int length = r.nextInt();
            System.out.println(length);
            int[] nums = new int[length];

            for(int i = 0; i < length; i++){
                nums[i] = r.nextInt();
            }
            System.out.print("Original Array: ");
            print(nums);
            for(int first = 0; first < length; first++){
                System.out.println("\tFirst Element: " + nums[first]);
                int[] current = new int[length];
                int[] tracker = new int[length];
                boolean found = true;

                current[0] = nums[first]; //first element of the potential solution
                tracker[first] = 1; //marking off the first element in tracker
                int previousElement = current[0];
                int previousGap = -1;


                for(int index = 1; index < length; index++){//fills up rest of array
                    System.out.print("\t\tCurrent working array: ");
                    print(current);
                    int nextIndex = -1;
                    int smallestGapSoFar = Integer.MAX_VALUE;
                    for(int i = 0; i < length; i++){ //checks tracker to see if element has been used up
                        if(tracker[i] == 0){ //if element has not been used yet

                            int currentGap = Math.abs(previousElement - nums[i]);
                            System.out.println("\t\t\tLooking at : " + nums[i]);
                            if(currentGap > previousGap && currentGap < smallestGapSoFar){
                                System.out.println("\t\t\tNew Possible next value: " + nums[i]);
                                nextIndex = i;
                                smallestGapSoFar = currentGap;
                            }
                            else{
                                System.out.println("\t\t\t" + nums[i] + " is not a possible value");
                                System.out.println("\t\t\t\tThe current gap is " + currentGap);
                                System.out.println("\t\t\t\tThe current smallest gap is " + smallestGapSoFar);
                                System.out.println("\t\t\t\tThe previous gap is " + previousGap);
                            }
                            previousElement = nums[nextIndex];
                        }
                    }
                    if(nextIndex == -1){
                        System.out.println("\t\tNo New possible element found");
                        found = false;
                        break;
                    }
                    current[index] = nums[nextIndex];
                    tracker[nextIndex] = 1;
                    previousGap = Math.abs(current[index] - current[index - 1]);
                    previousElement = current[index - 1];

                }
                if(found){
                    return current;
                }

            }
        }
        return new int[]{-1};
    }
    public static int[] run2(){
        int length = r.nextInt();
        int[] nums = new int[length];
        ArrayList<Diff> differences = new ArrayList<>();
        for(int i = 0; i < length; i++){
            for(int j = i + 1; j < length; j++){
                differences.add(new Diff(nums[i], nums[j]));
            }
        }
        Collections.sort(differences);
        int[] out = new int[length];
        return out;
    }
    public static int test(){
        return r.nextInt();
    }
    public static void print(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public boolean equals(int[] a, int[] b){
        if(a.length != b.length){
            return false;
        }
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
        return clazz.getResourceAsStream("/" + clazz.getSimpleName() + ".in");
    }
    private static InputStream createInputStream(String name) throws Exception {
        return clazz.getResourceAsStream("/" + name + ".in");
    }
    private static InputStream createInputStream(String name, String suffix) throws Exception {
        return clazz.getResourceAsStream("/" + name + "." + suffix);
    }
    public static void testCases(int num) throws Exception{
        for(int i = 1; i <= num; i++) {
            r = new InputReader(createInputStream("" + i));
            int[] out = run();
            r = new InputReader(createInputStream("" + i, "out"));
            int answer = test();
            //System.out.println("Test " + i + " " + (answer == out ? "Passed" : "Failed"));
        }
        pw.close();
    }
    public static void testSample() throws Exception {
        r = new InputReader(createInputStream());
        int[] out = run();
        System.out.println("OUTPUT");
        print(out);
        pw.close();
    }
    public static void main(String[] args) throws Exception {
        testSample();
    }
}

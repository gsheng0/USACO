package misc;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class LivestockLineup {
    public static class Cow implements Comparable<Cow>{
        String name = "";
        public Cow(String name){
            this.name = name;
        }
        public int compareTo(Cow other){
            for(int i = 0; i < Math.min(other.name.length(), this.name.length()); i++){
                if(other.name.charAt(i) > this.name.charAt(i)){
                    return 1;
                }
                else if(other.name.charAt(i) < this.name.charAt(i)){
                    return -1;
                }
            }
            return 0;
        }
        public boolean equals(Cow other){
            return this.name.equals(other.name);
        }
        public String toString() { return name; }

    }
    public static class Requirement{
        Cow cow1, cow2;
        public Requirement(Cow cow1, Cow cow2){
            this.cow1 = cow1;
            this.cow2 = cow2;
        }
        public Requirement(String name1, String name2){
            this.cow1 = new Cow(name1);
            this.cow2 = new Cow(name2);
        }

    }
    public static int binarySearch(ArrayList<Cow> cows, Cow cow){
        return binarySearch(cows, cow, 0, cows.size());
    }
    public static int binarySearch(ArrayList<Cow> cows, Cow cow, int l, int r){
        int m = l + (r - l) / 2;
        if(r <= l){
            return -1;
        }
        Cow current = cows.get(m);
        if(current.equals(cow)){
            return m;
        }
        if(current.compareTo(cow) > 0){
            return binarySearch(cows, cow, l, m - 1);
        }
        else if(current.compareTo(cow) < 0) {
            return binarySearch(cows, cow, m + 1, r);
        }
        return -1;


    }
    public static void run(){
        int lines = r.nextInt();
        Cow beatrice = new Cow("Beatrice");
        Cow sue = new Cow("Sue");
        Cow belinda = new Cow("Belinda");
        Cow bessie = new Cow("Bessie");
        Cow betsy = new Cow("Betsy");
        Cow blue = new Cow("Blue");
        Cow bella = new Cow("Bella");
        Cow buttercup = new Cow("Buttercup");
        ArrayList<Cow> cows = new ArrayList<>();
        cows.add(beatrice);
        cows.add(sue);
        cows.add(belinda);
        cows.add(bessie);
        cows.add(betsy);
        cows.add(blue);
        cows.add(bella);
        cows.add(buttercup);

        Collections.sort(cows);
        Requirement[] reqs = new Requirement[lines];
        for(int i = 0; i < lines; i++){
            Cow first = new Cow(r.next());
            for(int x = 0; x < 4; x++)
                r.next();
            Cow second = new Cow(r.next());
            reqs[i] = new Requirement(first, second);
        }

        ArrayList<Cow> list = helper(cows, reqs);
        System.out.println(list.size());
    }
    public static ArrayList<Cow> copy(ArrayList<Cow> in){
        ArrayList<Cow> out = new ArrayList<>();
        for(int i = 0; i < in.size(); i++){
            out.add(in.get(i));
        }
        return out;
    }
    public static ArrayList<Cow> helper(ArrayList<Cow> initial, Requirement[] reqs){
        if(reqs.length == 0){
            return initial;
        }
        Requirement current = reqs[0];
        Requirement[] remaining = subarray(reqs, 1);
        Cow cow1 = current.cow1;
        Cow cow2 = current.cow2;
        int index1 = binarySearch(initial, cow1);
        int index2 = binarySearch(initial, cow2);
        ArrayList<Cow> variation = copy(initial);
        ArrayList<ArrayList<Cow>> lists = new ArrayList<>();
        try{
            variation.remove(index1);
            if(index1 < index2){//if cow1 is earlier than cow2 in the list
                //index of cow2 goes down by 1
                variation.add(index2, cow1); //adds cow1 at index [index of cow2 + 1]
            }
            else{
                variation.add(index2 + 1, cow1);
            }
            lists.add(helper(variation, remaining));

        }
        catch(Exception e){}
        variation = copy(initial);
        try{
            variation.remove(index1);
            if(index1 < index2){//if cow1 is earlier than cow2 in the list
                //index of cow2 goes down by 1
                variation.add(index2 - 2, cow1); //adds cow1 at index [index of cow2 - 1]
            }
            else{
                variation.add(index2 - 1, cow1);
            }
            lists.add(helper(variation, remaining));
        }
        catch(Exception e){}
        variation = copy(initial);
        try{
            variation.remove(index1);
            if(index2 < index1){//if cow1 is earlier than cow2 in the list
                //index of cow2 goes down by 1
                variation.add(index1 - 2, cow2); //adds cow1 at index [index of cow2 - 1]
            }
            else{
                variation.add(index1 - 1, cow2);
            }
            lists.add(helper(variation, remaining));
        }
        catch(Exception e){}
        variation = copy(initial);
        try{
            variation.remove(index1);
            if(index2 < index1){//if cow1 is earlier than cow2 in the list
                //index of cow2 goes down by 1
                variation.add(index1, cow2); //adds cow1 at index [index of cow2 - 1]
            }
            else{
                variation.add(index1 + 1, cow2);
            }
            lists.add(helper(variation, remaining));
        }
        catch(Exception e){}
        ArrayList<Cow> max = new ArrayList<>();
        for(int i = 1; i < lists.size(); i++){
            if(compare(max, lists.get(i)) == -1){
                max = lists.get(i);
            }
        }
        return max;
    }
    public static int compare(ArrayList<Cow> cows, ArrayList<Cow> cows2){
        for(int i = 0; i < Math.min(cows.size(), cows2.size()); i++){
            if(cows.get(i).compareTo(cows2.get(i)) == 1){
                return 1;
            }
            else if(cows.get(i).compareTo(cows2.get(i)) == -1){
                return -1;
            }
        }
        if(cows.size() == cows2.size()){
            return 0;
        }
        else if(cows.size() < cows2.size()){
            return 1;
        }
        else return -1;
    }
    public static Requirement[] subarray(Requirement[] reqs, int start, int end){
        Requirement[] out = new Requirement[end - start];
        for(int i = 0; i  < out.length; i++){
            out[i] = reqs[i + start];
        }
        return out;
    }
    public static Requirement[] subarray(Requirement[] reqs, int start){
        return subarray(reqs, start, reqs.length);
    }
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void print(ArrayList<Cow> cows){
        for(Cow cow : cows){
            System.out.println(cow);
        }
    }



    private static InputStream createInputStream() throws Exception {
        Class clazz = LivestockLineup.class;
        return clazz.getResourceAsStream("/" + clazz.getSimpleName() + ".in");
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

    public static void main(String[] args) throws Exception {
        // comment out below line when submitting
        r = new InputReader(createInputStream());
        run();
        pw.close();
    }
}
/*
    class BinarySearch {
        // Returns index of x if it is present in arr[l..
        // r], else return -1
        int binarySearch(int arr[], int l, int r, int x)
        {
        if (r >= l) {
        int mid = l + (r - l) / 2;

        // If the element is present at the
        // middle itself
        if (arr[mid] == x)
        return mid;

        // If element is smaller than mid, then
        // it can only be present in left subarray
        if (arr[mid] > x)
        return binarySearch(arr, l, mid - 1, x);

        // Else the element can only be present
        // in right subarray
        return binarySearch(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
        }

// Driver method to test above
public static void main(String args[])
        {
        BinarySearch ob = new BinarySearch();
        int arr[] = { 2, 3, 4, 10, 40 };
        int n = arr.length;
        int x = 10;
        int result = ob.binarySearch(arr, 0, n - 1, x);
        if (result == -1)
        System.out.println("Element not present");
        else
        System.out.println("Element found at index " + result);
        }
        }
/* This code is contributed by Rajat Mishra */


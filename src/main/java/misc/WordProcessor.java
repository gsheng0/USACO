package misc;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class WordProcessor {
    public static void run(){
        int numWords = r.nextInt();
        int limit = r.nextInt();
        String[] words = new String[numWords];
        ArrayList<ArrayList<String>> out = new ArrayList<>();
        ArrayList<String> current = new ArrayList<>();
        int currLength = 0;
        for(int i = 0; i < words.length; i++){
            String word = r.next();
            if(currLength + word.length() > limit){
                out.add(current);
                currLength = 0;
                current = new ArrayList<>();
                current.add(word);
            }
            else{
                current.add(word);
                currLength += word.length();
            }
        }
        out.add(current);
        System.out.println();
        for(int i = 0; i < out.size(); i++){
            print(out.get(i));
        }


    }
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void print(String[] arr){
        for(String str : arr){
            System.out.print(str + " ");
        }
        System.out.println();
    }
    public static void print(ArrayList<String> arr){
        for(String str : arr){
            System.out.print(str + " ");
        }
        System.out.println();
    }



    private static InputStream createInputStream() throws Exception {
        Class clazz = WordProcessor.class;
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

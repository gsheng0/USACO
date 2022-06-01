package misc;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Photoshoot {
    private static class Permutation {
        int[] permutation;
        int counter = 0;
        boolean hasDup = false;
        public Permutation(int length){
            permutation = new int[length];
        }
        public void add(int element){
            if(counter < permutation.length){
                for(int i = 0; i < counter; i++){
                    if(permutation[i] == element){
                        hasDup = true;
                    }
                }
                permutation[counter] = element;
                counter++;
            }
        }
        public int get(int index){
            if(index >= counter){
                return Integer.MIN_VALUE;
            }
            return permutation[index];
        }
        public boolean hasDup() { return hasDup; }
        public String toString(){
            String output = "";
            for(int i = 0; i < permutation.length; i++){
                output += permutation[i] + " ";
            }
            return output;
        }
    }
    public static void run(){
        int output_size = r.nextInt();
        int[] input = new int[output_size - 1];
        for(int i = 0; i < input.length; i++){
            input[i] = r.nextInt();
        }
        ArrayList<Permutation> output = new ArrayList<>();
        int first = input[0];
        first--;
        while(first > 0){
            Permutation current = new Permutation(output_size);
            current.add(first);
            for(int i = 1; i < output_size; i++){
                current.add(input[i - 1] - current.get(i - 1));
            }
            if(!current.hasDup()){
                output.add(current);

            }
            first--;
        }
        for(int i = 0; i < output.size(); i++){
            System.out.println(output.get(i));
        }
    }
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }



    private static InputStream createInputStream() throws Exception {
        Class clazz = Photoshoot.class;
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

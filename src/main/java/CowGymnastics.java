import java.io.*;
import java.util.StringTokenizer;

public class CowGymnastics {
    private static void run () {
        int rounds = r.nextInt();
        int cows = r.nextInt();
        //arr[round][position] = cow
        int[][] record = new int[cows][rounds];
        for(int i = 0; i < rounds; i++){
            for(int x = 0; x < cows; x++)
                record[r.nextInt() - 1][i] = x + 1;
        }
        //record[cow][round] = position
        int counter = 0;
        for(int i = 0; i < cows; i++){

            for(int j = i + 1; j < cows; j++){
                boolean greater = true;
                boolean lesser = true;
                for(int x = 0; x < rounds; x++){
                    if(record[i][x] > record[j][x]){
                        lesser = false;
                    }
                    else if(record[i][x] < record[j][x]){
                        greater = false;
                    }
                }
                if(!greater && !lesser){
                    continue;
                }
                else{
                    counter++;
                }
            }
        }
        System.out.println(counter);

    }
    public static void print(int[][] arr){
        for(int[] arr1 : arr){
            for(int i : arr1){
                System.out.print(i + " " );
            }
            System.out.println();
        }
    }


    private static InputStream createInputStream() throws Exception {
        Class clazz = CowGymnastics.class;
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
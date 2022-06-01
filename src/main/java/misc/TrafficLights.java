package misc;

import java.io.*;
import java.util.StringTokenizer;

public class TrafficLights {
    private static void run () {
        int length = r.nextInt();
        int lights = r.nextInt();
        int[] street = new int[length + 1];
        street[0] = 1;
        street[street.length - 1] = 1;
        for(int i = 0; i < lights; i++){
            street[r.nextInt()] = 1;
            int prev = 0;
            int max = -1;
            for(int x = 1; x < street.length; x++){
                if(street[x] == 1){
                    int currLength = x - prev;
                    if(currLength > max){
                        max = currLength;
                    }
                    prev = x;
                }

            }
            print(street);
            System.out.println(max);
        }
    }
    public static void print(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " " );
        }
        System.out.println();
    }

    private static InputStream createInputStream() throws Exception {
        Class clazz = TrafficLights.class;
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

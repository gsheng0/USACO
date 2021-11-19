import java.io.*;
import java.util.StringTokenizer;

public class MilkPails {
    private static void run () {
        int[] pails = new int[3];
        for(int i = 0; i < 3; i++){
            pails[i] = r.nextInt();
        }

        System.out.println(maxPail(pails[0], pails[1], pails[2], 0));


    }
    public static int maxPail(int x, int y, int m, int sum){
        if(x + sum > m && y + sum > m){
            return sum;
        }
        if(x + sum > m){
            return maxPail(x, y, m, sum + y);
        }
        else if(y + sum > m){
            return maxPail(x, y, m, sum + x);
        }

        return Math.max(maxPail(x, y, m, sum + x), maxPail(x, y, m, sum + y));
    }

    private static InputStream createInputStream() throws Exception {
        Class clazz = MilkPails.class;
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
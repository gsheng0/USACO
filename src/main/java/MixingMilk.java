import java.io.*;
import java.util.StringTokenizer;

public class MixingMilk {
    private static void run () {
        int lineCnt = 3;
        int cap[] = new int[lineCnt];
        int amount[] = new int[lineCnt];
        for (int i=0; i<lineCnt; i++) {
            cap[i] = r.nextInt();
            amount[i] = r.nextInt();
        }

        int curr = 0;
        for(int i = 0; i < 100; i++){
            if(curr == 2){
                int sum = amount[curr] + amount[0];
                if(sum > cap[0]){
                    amount[curr] = sum - cap[0];
                    amount[0] = cap[0];
                }
                else{
                    amount[0] = amount[curr] + amount[0];
                    amount[curr] = 0;
                }
                curr = 0;
            }
            else{
                int sum = amount[curr] + amount[curr + 1];
                if(sum > cap[curr + 1]){
                    amount[curr] = sum - cap[curr + 1];
                    amount[curr + 1] = cap[curr + 1];
                }
                else{
                    amount[curr + 1] += amount[curr];
                    amount[curr] = 0;
                }
                curr++;
            }
        }

        for (int i=0; i<lineCnt; i++) {
            pw.println(amount[i]);
        }


    }

    private static InputStream createInputStream() throws Exception {
        Class clazz = MilkMeasurement.class;
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
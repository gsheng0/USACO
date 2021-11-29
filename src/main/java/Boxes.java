import java.io.*;
import java.util.StringTokenizer;

public class Boxes {
    public static final Class clazz = Boxes.class;
    public static int run(){
        return 0;
    }
    public static int test(){
        return r.nextInt();
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
            int out = run();
            r = new InputReader(createInputStream("" + i, "out"));
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
        testSample();
    }
}

import java.io.*;
import java.util.StringTokenizer;

public abstract class Template<E> {
    private Class clazz;
    InputReader r = new InputReader(System.in);
    PrintWriter pw = new PrintWriter(System.out);
    public void setClazz(Class clazz){
        this.clazz = clazz;
    }
    public InputReader getInputReader() { return r; }
    public abstract E run();

    public abstract E test();

    public static class InputReader {
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



    private static InputStream createInputStream(Class clazz) throws Exception {
        return clazz.getResourceAsStream("/" + clazz.getSimpleName() + ".in");
    }

    private static InputStream createInputStream(Class clazz, String name) throws Exception {
        return clazz.getResourceAsStream("/" + name + ".in");
    }

    private static InputStream createInputStream(Class clazz, String name, String suffix) throws Exception {
        return clazz.getResourceAsStream("/" + name + "." + suffix);
    }

    public void testCases(int num) throws Exception {
        for (int i = 1; i <= num; i++) {
            r = new InputReader(createInputStream(clazz, "" + i));
            E out = run();
            r = new InputReader(createInputStream(clazz, "" + i, "out"));
            E answer = test();
            boolean passed = answer.equals(out);
            System.out.println("Test " + i + " " + (passed ? "Passed" : "Failed"));
            if (!passed) {
                System.out.println("\tExpected : " + answer);
                System.out.println("\tRecieved: " + out);
            }
        }
        pw.close();
    }
    public void testCases(String[] names) throws Exception{
        for(String name : names){
            r = new InputReader(createInputStream(clazz, name));
            E out = run();
            r = new InputReader(createInputStream(clazz, name, "out"));
            E answer = test();
            boolean passed = answer.equals(out);
            System.out.println("Test " + name + " " + (passed ? "Passed" : "Failed"));
            if(!passed){
                System.out.println("\tExpected: " + answer);
                System.out.println("\tRecieved: " + out);
            }
        }
        pw.close();
    }

    public void testSample() throws Exception {
        r = new InputReader(createInputStream(clazz));
        E out = run();
        E answer = test();
        boolean passed = out.equals(answer);
        if(passed){
            System.out.println("Sample test passed");
        }
        else{
            System.out.println("Sample test failed");
            System.out.println("\tExpected " + answer);
            System.out.println("\tReceived " + out);
        }
        pw.close();
    }
}

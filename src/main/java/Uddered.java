import java.io.*;
import java.util.StringTokenizer;

public class Uddered {
    public static void run(){
        String a = r.next();
        String str = r.next();
        int counter = 0;
        int location = 27;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            int index = a.indexOf("" + c);
            if(index <= location){
                counter++;
                location = index;
            }
            else if(index > location){
                location = index;
            }
        }
        System.out.println(counter);
    }



    private static InputStream createInputStream() throws Exception {
        Class clazz = Uddered.class;
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

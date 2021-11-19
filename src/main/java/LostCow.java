import java.io.*;
import java.util.StringTokenizer;

public class LostCow {
    public static void run(){

        //farmer: 3 cow: 6
        //3 4 1 6(found)
        // 1+3+5 = 9
        //
        int x = r.nextInt();
        int y = r.nextInt();
        int travelled = 0;
        int target = x;
        int counter = 0;
        while(true){
            if (counter % 2 == 0) {
                //add
                int prev = target;//3
                target = x + (int)Math.pow(2, counter);//4
                if(target >= y && prev < y){
                    travelled += Math.abs(y - prev);
                    break;
                }
                else{
                    travelled += Math.abs(target - prev);//1
                }
            }
            else{
                int prev = target;//4
                target = x - (int)Math.pow(2, counter);//1
                if(target <= y && prev > y){
                    travelled += Math.abs(y - prev);
                    break;
                }
                else{
                    travelled += Math.abs(target - prev);//4
                }

            }
            counter++;
        }
        System.out.println(travelled);
    }
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }



    private static InputStream createInputStream() throws Exception {
        Class clazz = LostCow.class;
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

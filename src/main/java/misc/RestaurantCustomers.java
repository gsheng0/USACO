package misc;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RestaurantCustomers {
    private static class Person{
        private int enter, exit;
        public Person(int enter, int exit){
            this.enter = enter;
            this.exit = exit;
        }
        public int getEnter() { return enter; }
        public int getExit() { return exit; }


    }
    private static void run () {
        int customers = r.nextInt();
        int max = 0;
        int[] enter = new int[customers];
        int[] exit = new int[customers];
        for(int i = 0; i < customers; i++){
            int enterTime = r.nextInt();
            int exitTime = r.nextInt();
            enter[i] = enterTime;
            exit[i] = exitTime;
        }
        Arrays.sort(enter);
        Arrays.sort(exit);

        int counter = 0;
        int enterCounter = 0;
        int exitCounter = 0;
        while(true){
            if(enter[enterCounter] < exit[exitCounter]){
                counter++;
                enterCounter++;
                if(counter > max){
                    max = counter;
                }
            }
            else if(enter[enterCounter] > exit[exitCounter]){
                counter--;
                exitCounter++;
            }

            if(enterCounter >= customers){
                break;
            }
        }

        System.out.println(max);






//        int offset = minHour; //subtract offset from actual time values to get corresponding array index
//        int size = maxHour - minHour + 1;
//        int[] tracker = new int[size];
//        for(int i = 0; i < persons.length; i++){
//            for(int x = persons[i].getEnter() - offset; x <= persons[i].getExit() - offset; x++){
//                tracker[x]++;
//            }
//        }
//        for(int i = 0; i < tracker.length; i++){
//            if(tracker[i] > max){
//                max = tracker[i];
//            }
//        }
//        System.out.println(max);

    }

    private static InputStream createInputStream() throws Exception {
        Class clazz = RestaurantCustomers.class;
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
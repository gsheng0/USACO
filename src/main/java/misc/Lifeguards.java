package misc;

import java.io.*;
import java.util.StringTokenizer;

public class Lifeguards {
    private static class Lifeguard{
        int start;
        int end;
        public Lifeguard(int start, int end){
            this.start = start;
            this.end = end;
        }
        public int getDuration() { return end - start; }
    }

    private static void run () {
        int num = r.nextInt();
        Lifeguard[] lifeguards = new Lifeguard[num];
        int max = Integer.MIN_VALUE;
        int[] timeline = new int[1000];

        for(int i = 0; i < num; i++){
            lifeguards[i] = new Lifeguard(r.nextInt(), r.nextInt());
            if(lifeguards[i].end > max){
                max = lifeguards[i].end;
            }
            int[] current = generateTimelineArr(lifeguards[i].end, lifeguards[i].start, lifeguards[i].end);
            timeline = mergeTimelineArr(max, current, timeline);
        }

        int maxUptime = Integer.MIN_VALUE;
        for(int i = 0; i < num; i++){
            int[] without = subtractTimelineArr(timeline, generateTimelineArr(max, lifeguards[i].start, lifeguards[i].end));
            int uptime = 0;
            for(int x = 0; x < without.length; x++){
                if(without[x] != 0){
                    uptime++;
                }
            }
            if(uptime > maxUptime){
                maxUptime = uptime;
            }
        }
        System.out.println(maxUptime);


    }
    public static int[] generateTimelineArr(int length, int start, int end){
        int[] out = new int[length];
        for(int i = start; i < end; i++){
            out[i] = 1;
        }
        return out;
    }
    public static int[] subtractTimelineArr(int[] orig, int[] other){
        int[] out = new int[orig.length];
        for(int i = 0; i < orig.length; i++){
            out[i] = orig[i] - other[i];
        }
        return out;

    }
    public static int[] mergeTimelineArr(int length, int[] a, int[] b){
        int[] out = new int[length];
        for(int i = 0; i < out.length; i++){
            if(i < a.length && i < b.length)
                out[i] += a[i] + b[i];
            else if(i < a.length)
                out[i] += a[i];
            else if(i < b.length)
                out[i] += b[i];
            else
                break;
        }
        return out;
    }
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }



    private static InputStream createInputStream() throws Exception {
        Class clazz = Lifeguards.class;
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

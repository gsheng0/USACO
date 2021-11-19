import java.io.*;
import java.util.StringTokenizer;

public class FieldReduction {
    private static class Cow{
        int x, y;
        public Cow(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private static void run () {
        int numCows = r.nextInt();
        Cow[] cows = new Cow[numCows];
        int sumX = 0;
        int sumY = 0;
        for(int i = 0; i < cows.length; i++){
            cows[i] = new Cow(r.nextInt(), r.nextInt());
            sumX += cows[i].x;
            sumY += cows[i].y;
        }
        double averageX = sumX/((double)numCows);
        double averageY = sumY/((double)numCows);
        int index = -1;
        double maxDistance = 0.0;
        for(int i = 0; i < cows.length; i++){
            double distance = dist(averageX, averageY, cows[i]);
            if( distance > maxDistance){
                index = i;
            }
        }
        System.out.println(area(cows, index));



    }
    public static double dist(double averageX, double averageY, Cow cow){
        double diffX = cow.x - averageX;
        double diffY = cow.y - averageY;
        return Math.sqrt((diffX * diffX) + (diffY * diffY));
    }
    private static int area(Cow[] cows, int exclude){
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for(int i = 0; i < cows.length; i++){
            if(i == exclude){
                continue;
            }
            Cow cow = cows[i];
            if(cow.x < minX){
                minX = cow.x;
            }
            if(cow.x > maxX){
                maxX = cow.x;
            }
            if(cow.y < minY){
                minY = cow.y;
            }
            if(cow.y > maxY){
                maxY = cow.y;
            }
        }
        return (maxX - minX) * (maxY - minY);
    }


    private static InputStream createInputStream() throws Exception {
        Class clazz = FieldReduction.class;
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
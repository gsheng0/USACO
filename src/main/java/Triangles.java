import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Triangles {
    public static class Point {
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public String toString() { return x + ", " + y; }
        public boolean equals(Point other) {
            return other.x == this.x && other.y == this.y;
        }
    }
    private static void run () {
        int num = r.nextInt();
        Point[] points = new Point[num];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < num; i++){
            points[i] = (new Point(r.nextInt(), r.nextInt()));
        }
        for(Point point : points){
            int maxHorizontal = 0;
            int minHorizontal = 0;
            int maxVertical = 0;
            int minVertical = 0;
            for(Point other : points){

                if(other.equals(point)){
                    continue;
                }
                System.out.println("\tComparing Point: " +  other);
                if(other.x == point.x){
                    //vertical edge
                    if(other.y < minVertical){
                        minVertical = other.y;
                    }
                    else if(other.y > maxVertical){
                        maxVertical = other.y;
                    }
                }
                if(other.y == point.y){
                    //horizontal edge
                    if(other.x < minHorizontal){
                        minHorizontal = other.x;
                    }
                    else if(other.x > maxHorizontal){
                        maxHorizontal = other.x;
                    }
                }

                int area1 = Math.abs((maxHorizontal - point.x) * (maxVertical - point.y));
                int area2 = Math.abs((point.x - minHorizontal) * (maxVertical - point.y));
                int area3 = Math.abs((point.x - minHorizontal) * (point.y - minVertical));
                int area4 = Math.abs((maxHorizontal - point.x) * (point.y - minVertical));
                int max1 = Math.max(area1, area2);
                int max2 = Math.max(area3, area4);
                if(max < Math.max(max1, max2)){
                    max = Math.max(max1, max2);
                }
            }


        }
        System.out.println(max);

    }
    public static void print(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " " );
        }
        System.out.println();
    }

    private static InputStream createInputStream() throws Exception {
        Class clazz = Triangles.class;
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

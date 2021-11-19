import org.w3c.dom.css.Rect;

import java.io.*;
import java.util.StringTokenizer;

public class Billboard {
    private static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public String toString() { return x + ", " + y; }
    }
    private static class Rectangle{
        Point topRight, bottomLeft;
        public Rectangle(Point bottomLeft, Point topRight){
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
        }
        public int getArea(){
            int deltaX = topRight.x - bottomLeft.x;
            int deltaY = topRight.y - bottomLeft.y;
            return deltaX * deltaY;
        }
        public boolean contains(Point point){
            if(point.x >= bottomLeft.x && point.x <= topRight.x){
                if(point.y >= bottomLeft.y && point.y <= topRight.y){
                    return true;
                }
            }
            return false;
        }
        public Rectangle getIntersection(Rectangle other){
            int x1 = Math.max(this.bottomLeft.x, other.bottomLeft.x);
            int y1 = Math.max(this.bottomLeft.y, other.bottomLeft.y);
            System.out.println("\t" + x1 + ", " + y1);
            int x2 = Math.min(this.topRight.x, other.topRight.x);
            int y2 = Math.min(this.topRight.y, other.topRight.y);
            System.out.println("\t" + x2 + ", " + y2);
            return new Rectangle(new Point(x1, y1), new Point(x2, y2));
        }
    }
    private static void run () {
        Point p1 = new Point(r.nextInt(), r.nextInt());
        Point p2 = new Point(r.nextInt(), r.nextInt());
        Point p3 = new Point(r.nextInt(), r.nextInt());
        Point p4 = new Point(r.nextInt(), r.nextInt());
        Point p5 = new Point(r.nextInt(), r.nextInt());
        Point p6 = new Point(r.nextInt(), r.nextInt());
        Rectangle r1 = new Rectangle(p1, p2);
        Rectangle r2 = new Rectangle(p3, p4);
        Rectangle r3 = new Rectangle(p5, p6);

        Rectangle inter13 = r1.getIntersection(r3);
        Rectangle inter23 = r2.getIntersection(r3);

        int out = r1.getArea() - inter13.getArea();
        out += r2.getArea() - inter23.getArea();
        System.out.println(out);

    }




    private static InputStream createInputStream() throws Exception {
        Class clazz = Billboard.class;
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
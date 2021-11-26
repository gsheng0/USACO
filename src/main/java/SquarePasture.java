import org.w3c.dom.css.Rect;

import java.io.*;
import java.util.StringTokenizer;

public class SquarePasture{
    private static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Point copy() {
            return new Point(x, y);
        }
        public String toString() { return x + ", " + y; }
    }
    private static class Rectangle{
        public static final Rectangle AREA_ZERO = new Rectangle(new Point(0, 0), new Point(0, 0));
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
            int x2 = Math.min(this.topRight.x, other.topRight.x);
            int y2 = Math.min(this.topRight.y, other.topRight.y);
            return new Rectangle(new Point(x1, y1), new Point(x2, y2));
        }
        public Rectangle copy(){
            return new Rectangle(this.bottomLeft.copy(), this.topRight.copy());
        }
        public int getHeight() { return topRight.y - bottomLeft.y;}
        public int getWidth() { return topRight.x - bottomLeft.x; }
        public Rectangle subtract(Rectangle other){
            Rectangle intersection = other.getIntersection(this);
            Rectangle rect = this.copy();
            if(intersection.getHeight() == rect.getHeight()){
                int intersectionLeft = intersection.bottomLeft.x;
                int intersectionRight = intersection.topRight.x;
                int rectLeft = rect.bottomLeft.x;
                int rectRight = rect.topRight.x;
                //intersection is the rectangle
                if(intersectionRight == rectRight && intersectionLeft == rectLeft){
                    return Rectangle.AREA_ZERO;
                }
                //rectangle is on left side of rectangle
                if(intersectionLeft == rectLeft){
                    rect.topRight.x = rectRight;
                    return rect;
                }
                //rectangle is on right side of rectangle
                if(intersectionRight == rectRight){
                    rect.bottomLeft.x = rectLeft;
                    return rect;
                }
            }
            if(intersection.getWidth() == rect.getWidth()){
                int intersectionBottom = intersection.bottomLeft.y;
                int intersectionTop = intersection.topRight.y;
                int rectBottom = rect.bottomLeft.y;
                int rectTop = rect.topRight.y;
                //case where intersection is the rectangle has been covered
                //intersection is on upper side of rect
                if(intersectionTop == rectTop){
                    rect.bottomLeft.y = intersectionBottom;
                }
                //intersection is on lower side of rect
                if(intersectionBottom == rectBottom) {
                    rect.topRight.y = intersectionTop;
                }
            }
            return rect;
        }
        public Rectangle getMinimumRectangle(Rectangle other){
            int x1 = Math.min(this.bottomLeft.x, other.bottomLeft.x);
            int y1 = Math.min(this.bottomLeft.y, other.bottomLeft.y);
            int x2 = Math.max(this.topRight.x, other.topRight.x);
            int y2 = Math.max(this.topRight.y, other.topRight.y);
            return new Rectangle(new Point(x1, y1), new Point(x2, y2));
        }
    }
    private static void run () {
        //first one is lanwnmower
        Point p1 = new Point(r.nextInt(), r.nextInt());
        Point p2 = new Point(r.nextInt(), r.nextInt());
        Point p3 = new Point(r.nextInt(), r.nextInt());
        Point p4 = new Point(r.nextInt(), r.nextInt());
        Rectangle r1 = new Rectangle(p1, p2);
        Rectangle r2 = new Rectangle(p3, p4);
        Rectangle min = r1.getMinimumRectangle(r2);
        int maxDim = Math.max(min.getHeight(), min.getWidth());
        System.out.println(maxDim * maxDim);

    }



    private static InputStream createInputStream() throws Exception {
        Class clazz = SquarePasture.class;
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
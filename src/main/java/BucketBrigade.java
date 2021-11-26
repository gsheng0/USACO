import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BucketBrigade {
    public static class Grid{
        public static final char LAKE = 'L';
        public static final char EMPTY = '.';
        public static final char COW = 'C';
        public static final char BARN = 'B';
        public static final char ROCK = 'R';
        char[][] grid = new char[10][10];
        Point spot;
        public Grid(){}
        public Grid(char[][] grid){
            this.grid = grid;
        }
        public boolean isValid(Point p){
            return p.isValid() && (grid[p.x][p.y] == '.' || grid[p.x][p.y] == 'B');
        }
        public void placeCow(Point p){
            if(!p.isValid()){
                return;
            }
            grid[p.x][p.y] = Grid.COW;
        }
        public Grid copy(){
            char[][] out = new char[10][10];
            for(int x = 0; x < 10; x++){
                for(int y = 0; y < 10; y++){
                    out[x][y] = grid[x][y];
                }
            }
            return new Grid(out);
        }
        public void print(){
            for(int x = 0; x < 10; x++){
                for(int y= 0; y < 10; y++){
                    System.out.print(grid[x][y] + " ");
                }
                System.out.println();
            }
        }
        public void setSpot(int x, int y){
            spot = new Point(x, y);
        }
        public void setSpot(Point point){
            spot = point;
        }
        public char peek(char direction){
            int x = spot.x;
            int y = spot.y;
            if(!spot.isValid()){
                return 'X';
            }
            if(direction == 'L'){
                if(x > 0){
                    return grid[x - 1][y];
                }
                return 'X';
            }
            if(direction == 'R'){
                if(x < 9){
                    return grid[x + 1][y];
                }
                return 'X';
            }
            if(direction == 'N'){
                if(y < 9){
                    return grid[x][y + 1];
                }
                return 'X';
            }
            if(direction == 'S'){
                if(y > 0){
                    return grid[x][y - 1];
                }
                return 'X';
            }
            return 'X';
        }
    }
    public static class Point{
        int x = 0;
        int y = 0;
        public Point(){}
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Point north(){
            Point copy = this.copy();
            copy.y += 1;
            return copy;
        }
        public Point south(){
            Point copy = this.copy();
            copy.y -= 1;
            return copy;
        }
        public Point east(){
            Point copy = this.copy();
            copy.x -= 1;
            return copy;
        }
        public Point west() {
            Point copy = this.copy();
            copy.x += 1;
            return copy;
        }
        public boolean isValid(){
            return x >= 0 && x < 10 && y >= 0 && y < 10;
        }
        public Point copy() {
            return new Point(x, y);
        }
        public boolean equals(Point other){
            return other.x == x && other.y == y;
        }
    }
    public static void run(){
        Grid grid = new Grid();
        Point lake = new Point(0, 0);
        Point barn = new Point(0, 0);
        for(int i = 0; i < 10; i++){
            String row = r.next();
            for(int x = 0; x < 10; x++){
                grid.grid[i][x] = row.charAt(x);
                if(row.charAt(x) == Grid.LAKE){
                    lake = new Point(i, x);
                }
                if(row.charAt(x) == Grid.BARN){
                    lake = new Point(i, x);
                }
            }
        }

        int out = helper(grid, lake, barn, 0);
        grid.print();
        System.out.println(out);
    }
    public static int helper(Grid grid, Point lastCow, Point barn, int count){
        if(lastCow.equals(barn)){
            return count;
        }
        int north = 101;
        int south = 101;
        int east = 101;
        int west = 101;
        if(grid.isValid(lastCow.north())){
            System.out.println("Checking north");
            Point point = lastCow.north();
            grid.placeCow(point);
            north = helper(grid, point, barn, count + 1);
        }
        if(grid.isValid(lastCow.south())){
            System.out.println("Checking south");
            Point point = lastCow.south();
            grid.placeCow(point);
            south = helper(grid, point, barn, count + 1);
        }
        if(grid.isValid(lastCow.east())){
            System.out.println("Checking east");
            Point point = lastCow.east();
            grid.placeCow(point);
            east = helper(grid, point, barn, count + 1);
        }
        if(grid.isValid(lastCow.west())){
            System.out.println("Checking west");
            Point point = lastCow.west();
            grid.placeCow(point);
            west = helper(grid, point, barn, count + 1);
        }
        return Math.min(Math.min(east, west), Math.min(north, south));

    }
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void print(String[] arr){
        for(String str : arr){
            System.out.print(str + " ");
        }
        System.out.println();
    }
    public static void print(ArrayList<String> arr){
        for(String str : arr){
            System.out.print(str + " ");
        }
        System.out.println();
    }



    private static InputStream createInputStream() throws Exception {
        Class clazz = BucketBrigade.class;
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

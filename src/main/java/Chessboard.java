import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Chessboard {
    private static TreeSet<String> tracker = new TreeSet<>();
    private static class Board{
        boolean[][] board = new boolean[8][8];
        boolean finished = false;
        int freeSpaces = 64;
        int row = 0;
        public Board(boolean finished){
            this.finished = finished;
        }
        public Board(){}
        public Board copy(){
            Board board = new Board();
            boolean[][] newBoard = new boolean[8][8];
            for(int x = 0; x < 8; x++){
                for(int y = 0; y < 8; y++){
                    newBoard[x][y] = this.board[x][y];
                }
            }
            board.finished = this.finished;
            board.freeSpaces = this.freeSpaces;
            board.row = this.row;
            return board;
        }
        public String toString(){
            String out = "";
            for(int x = 0; x < 8; x++){
                for(int y = 0; y < 8; y++){
                    out += board[x][y] ? "." : "*";
                }
            }
            return out;
        }
        public void read(String line){
            if(finished){
                return;
            }
            for(int i = 0; i < 8; i++){
                board[row][i] = line.charAt(i) == '*';
            }
            row++;
            if(row == 8){
                finished = true;
            }
        }
        public void placeQueen(int x, int y){
            if(board[x][y]){
                return;
            }
            board[x][y] = true;
            freeSpaces--;
            for(int i = 0; i < 8; i++){
                if(!board[x][i]){
                    freeSpaces--;
                    board[x][i] = true;
                }
                if(!board[i][y]){
                    freeSpaces--;
                    board[i][y] = true;
                }
                if(x + i < 8 && y + i < 8){
                    if(!board[x + i][y + i]){
                        freeSpaces--;
                        board[x + i][y + i] = true;
                    }
                }
                if(x - i >= 0 && y - i >= 0){
                    if(!board[x - i][y - i]){
                        freeSpaces--;
                        board[x - i][y - i] = true;
                    }
                }
                if(x + i < 8 && y - i >= 0){
                    if(!board[x + i][y - i]){
                        freeSpaces--;
                        board[x + i][y - i] = true;
                    }
                }
                if(x - i >= 0 && y + i < 8){
                    if(!board[x - i][y + i]){
                        freeSpaces--;
                        board[x - i][y + i] = true;
                    }
                }
            }
        }
    }
    public static void print(boolean[][] arr){
        for(boolean[] arr1 : arr){
            for(boolean x : arr1){
                if(x){
                    System.out.print("x ");
                }
                else{
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void run(){
        System.out.println(possiblePositions(8.0));
        Board board = new Board();
        for(int i = 0; i < 8; i++){
            board.read(r.next());
        }
        int sum = placeQueens(board, 2);
        System.out.println(sum);

    }

    public static int placeQueens(Board board, int queensLeft){
        if(queensLeft == 0){
            print(board.board);
            System.out.println();
            return 1;
        }

        if(board.freeSpaces == 0 || board.freeSpaces < queensLeft){
            return 0;
        }

        if(tracker.contains(board.toString())){
            return 0;
        }
        tracker.add(board.toString());

        int sum = 0;
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                if(!board.board[x][y]){
                    Board out = board.copy();
                    out.placeQueen(x, y);

                    sum += placeQueens(out, queensLeft - 1);
                }
            }
        }
        return sum;
    }
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    static double possiblePositions(double n)
    {
        double term1 = Math.pow(n, 4);
        double term2 = Math.pow(n, 3);
        double term3 = Math.pow(n, 2);
        double term4 = n / 3;
        double ans = (Math.ceil(term1 / 2)) -
                (Math.ceil(5 * term2) / 3) +
                (Math.ceil(3 * term3) / 2) - term4;

        return (long)ans;
    }




    private static InputStream createInputStream() throws Exception {
        Class clazz = Chessboard.class;
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

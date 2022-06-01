package misc;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Chessboard {
    private static TreeSet<String> tracker = new TreeSet<>();
    private static class Board{
        int[][] board = new int[8][8];
        boolean finished = false;
        int freeSpaces = 64;
        int row = 0;
        public Board(boolean finished){
            this.finished = finished;
        }
        public Board(){}

        public void print(){
            for(int x = 0; x < 8; x++){
                for(int y = 0; y < 8; y++){
                    System.out.print(board[x][y] + "  ");
                }
                System.out.println();
            }
        }
        public Board copy(){
            Board board = new Board();
            int[][] newBoard = new int[8][8];
            for(int x = 0; x < 8; x++){
                for(int y = 0; y < 8; y++){
                    newBoard[x][y] = this.board[x][y];
                }
            }
            board.finished = this.finished;
            board.freeSpaces = this.freeSpaces;
            board.row = this.row;
            board.board = newBoard;

            return board;
        }
        public String toString(){
            String out = "";
            for(int x = 0; x < 8; x++){
                for(int y = 0; y < 8; y++){
                    out += board[x][y];
                }
            }
            return out;
        }
        public void read(String line){
            if(finished){
                return;
            }
            for(int i = 0; i < 8; i++){
                board[row][i] = (line.charAt(i) == '*') ? 1 : 0;
                if (board[row][i] == 1)
                    freeSpaces --;
            }
            row++;
            if(row == 8){
                finished = true;
            }
        }
        public void placeQueen(int x, int y){
            if(board[x][y] != 0){
                return;
            }
            board[x][y] = 2;
            freeSpaces--;
            for(int i = 0; i < 8; i++){
                if(board[x][i] == 0){
                    freeSpaces--;
                    board[x][i] = 3;
                }
                if(board[i][y] == 0){
                    freeSpaces--;
                    board[i][y] = 3;
                }
                if(x + i < 8 && y + i < 8){
                    if(board[x + i][y + i] == 0){
                        freeSpaces--;
                        board[x + i][y + i] = 3;
                    }
                }
                if(x - i >= 0 && y - i >= 0){
                    if(board[x - i][y - i] == 0){
                        freeSpaces--;
                        board[x - i][y - i] = 3;
                    }
                }
                if(x + i < 8 && y - i >= 0){
                    if(board[x + i][y - i] == 0){
                        freeSpaces--;
                        board[x + i][y - i] = 3;
                    }
                }
                if(x - i >= 0 && y + i < 8){
                    if(board[x - i][y + i] == 0){
                        freeSpaces--;
                        board[x - i][y + i] = 3;
                    }
                }
            }
        }
    }


    public static void run(){
//        System.out.println(possiblePositions(8.0));
        Board board = new Board();
        for(int i = 0; i < 8; i++){
            board.read(r.next());
        }
        int sum = placeQueens(board, 8);
        System.out.println(sum);

    }

    public static int placeQueens(Board board, int queensLeft){
        if(tracker.contains(board.toString())){
            return 0;
        }
        tracker.add(board.toString());

        if(queensLeft == 0){
            board.print();
            System.out.println();
            return 1;
        }

        if( board.freeSpaces < queensLeft){
            return 0;
        }

        int sum = 0;
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                if(board.board[x][y] == 0){
                    Board out = board.copy();
                    out.placeQueen(x, y);

                    sum += placeQueens(out, queensLeft - 1);
                }
            }
        }
        return sum;
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

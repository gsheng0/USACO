import java.io.*;
import java.util.StringTokenizer;

public class Mowing {
    private static class Move {
        char direction;
        int steps;
        public Move(char direction, int steps){
            this.direction = direction;
            this.steps = steps;
        }
        public String toString() {
            return "" + direction + " " + steps;
        }
    }
    private static void run () {
        int lines = r.nextInt();
        Move[] moves = new Move[lines];
        for(int i = 0; i < lines; i++){
            moves[i] = new Move(r.next().charAt(0), r.nextInt());
            System.out.println(moves[i].toString());
        }
        int[][] lawn = new int[100][100];
        int[] pos = new int[]{50, 50};
        int time = 1;
        int maxTime = Integer.MAX_VALUE;
        for(Move move : moves){
            int steps = move.steps;
            char direction = move.direction;
            for(int i = 0; i < steps; i++){
                if(direction == 'N'){
                    pos[1]++;
                }
                else if(direction == 'S'){
                    pos[1]--;
                }
                else if(direction == 'E'){
                    pos[0]++;
                }
                else if(direction == 'W'){
                    pos[0]--;
                }
                int lastMowed = lawn[pos[0]][pos[1]];
                if(lastMowed != 0){
                    if(maxTime >= time - lastMowed) {
                        maxTime = time - lastMowed;
                    }
                }
                lawn[pos[0]][pos[1]] = time;
                time++;
            }
        }

        System.out.println(maxTime);

    }
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }



    private static InputStream createInputStream() throws Exception {
        Class clazz = Mowing.class;
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
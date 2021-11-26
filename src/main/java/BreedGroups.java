import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BreedGroups {
    public static void run(){
        int num = r.nextInt();
        int[] arr = new int[num];
        ArrayList<Integer> odds = new ArrayList<>();
        ArrayList<Integer> evens = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            arr[i] = r.nextInt();
            if(arr[i] % 2 == 0){
                evens.add(arr[i]);
            }
            else{
                odds.add(arr[i]);
            }
        }
        int counter = 0;
        int groups = 0;
        int evenCount = evens.size();
        int oddCount = odds.size();
        while(true){
            if(evenCount == 0 && oddCount == 0){ //if used up all numbers
                break;
            }
            if(counter % 2 == 0){
                //need even group
                if(evenCount > 0){
                    evenCount--;
                    groups++;
                }
                else if(evenCount == 0){
                    if(oddCount >= 2){
                        oddCount -= 2;
                        groups++;
                    }
                    else{
                        if(oddCount == 1){
                            groups--;
                        }
                        break;
                    }
                }
            }
            else if(counter % 2 == 1){
                if (oddCount > 0) {
                    oddCount--;
                    groups++;
                }
                else{
                    break;
                }
            }
            counter++;
        }
        System.out.println(groups);
    }



    private static InputStream createInputStream() throws Exception {
        Class clazz = BreedGroups.class;
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

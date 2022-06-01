package misc;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MilkMeasurement {
    private static class Cow {
        private String name = "";
        private int amount = 7;
        public Cow(String name){
            this.name = name;
        }
        public void change(int change){
            amount += change;
        }
        public boolean equals(Cow cow){
            return cow.name.equals(this.name);
        }
        public String toString() { return name + " " + amount;}
    }
    private static class Record {
        private int day;
        private int change;
        private String cow;
        public Record(int day, int change, String cow){
            this.day = day;
            this.change = change;
            this.cow = cow;
        }
    }

    public static class RecordBook {
        private ArrayList<ArrayList<Record>> recordBook = new ArrayList<>();

        public RecordBook() {
            for(int i = 0; i < 101; i++){
                recordBook.add(new ArrayList<>());
            }
        }

        public void add(Record record){

            recordBook.get(record.day).add(record);
        }
    }
    public static void print(ArrayList<Cow> cows){
        for(Cow cow : cows){
            System.out.println(cow.toString());
        }
    }
    private static void run () {
        int counter = 0;
        Cow mildred = new Cow("Mildred");
        Cow elsie = new Cow("Elsie");
        Cow bessie = new Cow("Bessie");
        ArrayList<Cow> max = new ArrayList<>();
        ArrayList<Cow> cows = new ArrayList<>();
        RecordBook book = new RecordBook();
        max.add(mildred);
        max.add(elsie);
        max.add(bessie);
        cows.add(mildred);
        cows.add(elsie);
        cows.add(bessie);

        int maxDay = 0;
        int numRecords = r.nextInt();
        for(int i = 0; i < numRecords; i++){
            int day = r.nextInt();
            String name = r.next();
            int change = r.nextInt();
            Record curr = new Record(day, change, name);
            if(day > maxDay){
                maxDay = day;
            }
            book.add(curr);
        }
        System.out.println("Max DAY: " + maxDay);
        for(int i = 0; i <= maxDay; i++){
            for(int x = 0; x < book.recordBook.get(i).size(); x++){
                Record curr = book.recordBook.get(i).get(x);
                if(curr.cow.equals("Mildred")){
                    mildred.change(curr.change);
                }
                else if(curr.cow.equals("Elsie")){
                    elsie.change(curr.change);
                }
                else if(curr.cow.equals("Bessie")){
                    bessie.change(curr.change);
                }
            }
            if(book.recordBook.get(i).size() > 0) {

                int currMax = 0;
                ArrayList<Cow> temp = new ArrayList<>();
                for (Cow cow : cows) {
                    if (currMax < cow.amount) {
                        currMax = cow.amount;
                        temp = new ArrayList<>();
                        temp.add(cow);
                    } else if (currMax == cow.amount) {
                        temp.add(cow);
                    }
                }
                if (temp.size() != max.size()) {
                    counter++;
                } else {
                    for (int j = 0; j < temp.size(); j++) {
                        if (!temp.get(j).equals(max.get(j))) {
                            counter++;
                            break;
                        }
                    }
                }
                max = temp;
                print(max);
            }
        }

        System.out.println(counter);

    }

    private static InputStream createInputStream() throws Exception {
        Class clazz = MilkMeasurement.class;
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

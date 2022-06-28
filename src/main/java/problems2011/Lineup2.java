package problems2011;

import template.Template;

import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class Lineup2 extends Template<Integer> {
    public Lineup2(){
        super.setFilename("Lineup");
    }
    public static class Cow implements Comparable<Cow> {
        int pos, breed;
        public Cow(int pos, int breed){
            this.pos = pos;
            this.breed = breed;
        }
        public String toString(){
            return pos + ": " + breed;
        }
        public int compareTo(Cow other){
            Integer a = pos;
            Integer b = other.pos;
            return a.compareTo(b);
        }
    }
    public Integer run(){
        InputReader r = super.getInputReader();
        int numCows = r.nextInt();
        ArrayList<Cow> cows = new ArrayList<>();
        for(int i = 0; i < numCows; i++){
            cows.add(new Cow(r.nextInt(), r.nextInt()));
        }
        print(cows);
        return 1;

    }
    public void print(ArrayList<Cow> cows){
        for(Cow cow : cows){
            System.out.println(cow);
        }
    }

    public Integer test(){
        return 4;
    }
    public static void main(String[] args) throws Exception {
        Lineup2 app = new Lineup2();
        app.testSample();
    }
}

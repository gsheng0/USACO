package problems2011;

import template.Template;

import java.awt.event.ComponentListener;
import java.util.*;

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
        HashSet<Integer> breeds = new HashSet<>();
        for(int i = 0; i < numCows; i++){
            int pos = r.nextInt();
            int breed = r.nextInt();
            cows.add(new Cow(pos, breed));
            breeds.add(breed);
        }

        Collections.sort(cows);
        int left = 0;
        int right = -1;
        int min = Integer.MAX_VALUE;
        ArrayList<Integer> encountered = new ArrayList<>();
        HashMap<Integer, Integer> breedCounter = new HashMap<>();

        while(true){
            if(encountered.size() == breeds.size()){
                min = Math.min(min, cows.get(right).pos - cows.get(left).pos);
                //System.out.println("Minimum So Far: " + cows.get(left).pos + " " + cows.get(right).pos);
                int removedBreed = cows.get(left).breed;
                int removedBreedCount = breedCounter.get(removedBreed);
                //System.out.println("Num left of removed breed: " + removedBreedCount);
                left++;
                breedCounter.replace(removedBreed, breedCounter.get(removedBreed) - 1);
                if(breedCounter.get(removedBreed) < 1) {
                    //System.out.println("Lost a breed in photo");
                    encountered.remove(new Integer(removedBreed));
                }

            }
            else{
                right++;
                if(right == cows.size()){
                    break;
                }
                int newBreed = cows.get(right).breed;
                //System.out.println("Breed of cow at position " + cows.get(right).pos + ": " + newBreed);
                if(!breedCounter.containsKey(newBreed)){
                    breedCounter.put(newBreed, 1);
                }
                else{
                    breedCounter.replace(newBreed, breedCounter.get(newBreed) + 1);
                    //System.out.println("Encountered breed " + newBreed + " again. There are " + breedCounter.get(newBreed) + " total.");
                }

                if(!encountered.contains(newBreed)){
                    encountered.add(newBreed);
                }

            }

        }


        return min;

    }
    public void print(ArrayList<Cow> cows){
        for(Cow cow : cows){
            System.out.println(cow);
        }
    }

    public Integer test(){
        InputReader r = super.getInputReader();
        return r.nextInt();
    }
    public static void main(String[] args) throws Exception {
        Lineup2 app = new Lineup2();
        String[] inputNames = new String[12];
        String[] outputNames = new String[12];
        String[] suffixes = new String[12];
        for(int i = 0; i < 12; i++){
            int cardinal = i + 1;
            suffixes[i] = "" + cardinal;
            inputNames[i] = "I";
            outputNames[i] = "O";
        }
        app.testCases(inputNames, outputNames, suffixes, suffixes);
        //app.testSample();
    }
}

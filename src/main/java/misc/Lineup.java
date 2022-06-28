package misc;

import template.Template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Lineup extends Template<ArrayList<String>> {
    public Lineup(){
        super.setFilename(Lineup.class);
    }
    public static class CowSet implements Comparable<CowSet>{
        private ArrayList<String> list;
        private Integer id = -1;
        private static int tracker = 1;
        public CowSet(String a, String b){
            id = tracker;
            tracker++;
            list = new ArrayList<>();
            if(a.compareTo(b) > 0){
                list.add(b);
            }
            list.add(a);
            if(a.compareTo(b) < 0){
                list.add(b);
            }
        }
        public CowSet(String c){
            id = tracker;
            tracker++;
            list = new ArrayList<>();
            list.add(c);
        }
        public CowSet(ArrayList<String> list){
            this.list = list;
        }
        public String getFirst() { return list.get(0); }
        public String getLast() { return list.get(list.size() - 1); }
        public ArrayList<String> getList() { return list; }

        @Override
        public int hashCode() {
            int total = 0;
            for(String c : list){
                total += c.hashCode();
            }
            return total * total / list.size();
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof CowSet)){
                return false;
            }
            CowSet other = (CowSet)obj;
            if(other.list.size() != this.list.size()){
                return false;
            }
            for(int i = 0; i < other.list.size(); i++){
                if(!other.list.get(i).equals(this.list.get(i))){
                    return false;
                }
            }
            return true;

        }
        public String toString(){
            return "CowSet " + id + ": " + list.toString();
        }
        public void merge(CowSet other){
            CowSet merged = CowSet.merge(this, other);
            System.out.println("MERGING MERGIN MERGING MERGING");
            System.out.println("\tList 1: " + list.toString());
            System.out.println("\tList 2: " + other.list.toString());
            System.out.println("\tMerged List: " + merged.list.toString());
            this.list.clear();
            this.list.addAll(merged.list);
            //by setting other.list equal to this.list
            //the link that other.list has to the other instances of that same list is broken
            //in other words, it does not copy into the list, but replaces the reference
            //
            other.list.clear();
            other.list.addAll(merged.list);
            other.id = this.id;
        }
        public static CowSet merge(CowSet a, CowSet b){
            String aFirst = a.getFirst();
            String aLast = a.getLast();
            String bFirst = b.getFirst();
            String bLast = b.getLast();
            ArrayList<String> aList = a.getList();
            ArrayList<String> bList = b.getList();

            ArrayList<String> out = new ArrayList<>();
            if(aFirst.equals(bLast)){
                out.addAll(bList);
                for(int i = 1; i < aList.size(); i++){
                    out.add(aList.get(i));
                }
            }
            else if(bFirst.equals(aLast)){
                out.addAll(aList);
                for(int i = 1; i < bList.size(); i++){
                    out.add(bList.get(i));
                }
            }
            else if(aFirst.equals(bFirst)){
                //need to reverse one of the two lists
                if(aLast.compareTo(bLast) > 0){
                    //need to reverse b
                    for(int i = 0; i < bList.size(); i++){
                        out.add(bList.get(bList.size() - 1 - i));
                    }
                    out.remove(out.size() - 1);
                    out.addAll(aList);
                }
                else if(bLast.compareTo(aLast) > 0){
                    for(int i = 0; i < aList.size(); i++){
                        out.add(aList.get(aList.size() - 1 - i));
                    }
                    out.remove(out.size() - 1);
                    out.addAll(bList);
                }
            }
            else if(aLast.equals(bLast)){
                if(aFirst.compareTo(bFirst) > 0){
                    out.addAll(bList);
                    out.remove(out.size() - 1);
                    for(int i = 0; i < aList.size(); i++){
                        out.add(aList.get(aList.size() - 1 - i));
                    }
                }
                else if(aFirst.compareTo(bFirst) < 0){
                    out.addAll(aList);
                    out.remove(out.size() - 1);
                    for(int i = 0; i < bList.size(); i++){
                        out.add(bList.get(bList.size() - 1 - i));
                    }
                }
            }
            return new CowSet(out);
        }

        @Override
        public int compareTo(CowSet o) {
            return -1 * o.getFirst().compareTo(this.getFirst());
        }
    }


    @Override
    public ArrayList<String> run() {
        Template.InputReader r = super.getInputReader();
        int lines = r.nextInt();
        HashMap<String, ArrayList<CowSet>> map = new HashMap<>();
        String[] tracker = new String[] {"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};
        for(int i = 0; i < tracker.length; i++){
            String c = tracker[i];
            map.put(c, new ArrayList<>());
            map.get(c).add(new CowSet(c));
        }

        for(int i = 0; i < lines; i++){
            String a = r.next();
            r.next();
            r.next();
            r.next();
            r.next();
            String b = r.next();
            CowSet cowSet = new CowSet(a, b);

            //Checking the associated cowset for each of the cows
            //if the cow is still by itself, then remove it, and add the new pairing of
            if(map.get(a).get(0).getList().size() == 1){
                map.get(a).remove(0);
            }
            map.get(a).add(cowSet);
            if(map.get(b).get(0).getList().size() == 1){
                map.get(b).remove(0);
            }
            map.get(b).add(cowSet);
        }

        for(String key : map.keySet()){
            ArrayList<CowSet> list = map.get(key);
            if(list.size() == 2){
                CowSet a = list.get(0);
                CowSet b = list.get(1);
                CowSet merged = CowSet.merge(list.get(0), list.get(1));
                for(String cow : merged.getList()){
                    ArrayList<CowSet> set = map.get(cow);
                    set.remove(a);
                    set.remove(b);
                    set.add(merged);
                }
            }
        }

        HashSet<CowSet> hashSet = new HashSet<>();

        for(String key : map.keySet()) {
            for (CowSet set : map.get(key)) {
                hashSet.add(set);
            }
        }
        ArrayList<CowSet> list = new ArrayList<>();
        list.addAll(hashSet);
        Collections.sort(list);

        ArrayList<String> out = new ArrayList<>();
        for(CowSet set : list){
            out.addAll(set.list);
        }

        return out;

    }

    @Override
    public ArrayList<String> test() {
        InputReader r = super.getInputReader();
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            out.add(r.next());
        }
        return out;
    }
    public static void main(String[] args) throws Exception {
        Lineup lineup = new Lineup();
        lineup.testCases(10);
//        ArrayList<Character> a = new ArrayList<>();
//
//        a.add('c');
        //a.add('d');
        //a.add('a');
//        ArrayList<Character> b = new ArrayList<>();
//        b.add('c');
//        b.add('b');
        //b.add('e');
//        CowSet setA = new CowSet(b);
//        CowSet setB = new CowSet(a);
//        CowSet out = CowSet.merge(setA, setB);
//        System.out.println(out.getList());

    }
}
//[Beatrice, Belinda, Bella, Bessie, Betsy, Blue, Buttercup, Sue]
/*

Beatrice    a
Belinda     b
Bella       c
Bessie      d
Betsy       e
Blue        f
Buttercup   g
Sue         h
 */

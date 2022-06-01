import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Lineup extends Template<ArrayList<Character>> {
    public Lineup(){
        super.setClazz(Lineup.class);
    }
    public static class CowSet implements Comparable<CowSet>{
        private ArrayList<Character> list;
        public CowSet(char a, char b){
            list = new ArrayList<>();
            if(a > b){
                list.add(b);
            }
            list.add(a);
            if(b > a){
                list.add(b);
            }
        }
        public CowSet(char c){
            list = new ArrayList<>();
            list.add(c);
        }
        public CowSet(ArrayList<Character> list){
            this.list = list;
        }
        public Character getFirst() { return list.get(0); }
        public Character getLast() { return list.get(list.size() - 1); }
        public ArrayList<Character> getList() { return list; }

        @Override
        public int hashCode() {
            int total = 0;
            for(Character c : list){
                total += c;
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
                if(other.list.get(i).charValue() != this.list.get(i).charValue()){
                    return false;
                }
            }
            return true;

        }


        public String toString(){
            return "CowSet: " + list.toString();
        }
        public void merge(CowSet other){
            CowSet merged = CowSet.merge(this, other);
            this.list.clear();
            this.list.addAll(merged.list);

            other.list.clear();
            other.list.addAll(merged.list);
        }
        public static CowSet merge(CowSet a, CowSet b){
            char aFirst = a.getFirst();
            char aLast = a.getLast();
            char bFirst = b.getFirst();
            char bLast = b.getLast();
            ArrayList<Character> aList = a.getList();
            ArrayList<Character> bList = b.getList();

            ArrayList<Character> out = new ArrayList<>();
            if(aFirst == bLast){
                out.addAll(bList);
                for(int i = 1; i < aList.size(); i++){
                    out.add(aList.get(i));
                }
            }
            else if(bFirst == aLast){
                out.addAll(aList);
                for(int i = 1; i < bList.size(); i++){
                    out.add(bList.get(i));
                }
            }
            else if(aFirst == bFirst){
                //need to reverse one of the two lists
                if(aLast > bLast){
                    //need to reverse b
                    for(int i = 0; i < bList.size(); i++){
                        out.add(bList.get(bList.size() - 1 - i));
                    }
                    out.remove(out.size() - 1);
                    out.addAll(aList);
                }
                else if(bLast > aLast){
                    for(int i = 0; i < aList.size(); i++){
                        out.add(aList.get(aList.size() - 1 - i));
                    }
                    out.remove(out.size() - 1);
                    out.addAll(bList);
                }
            }
            else if(aLast == bLast){
                if(aFirst > bFirst){
                    out.addAll(bList);
                    out.remove(out.size() - 1);
                    for(int i = 0; i < aList.size(); i++){
                        out.add(aList.get(aList.size() - 1 - i));
                    }
                }
                else if(aFirst < bFirst){
                    out.addAll(aList);
                    out.remove(out.size() - 1);
                    for(int i = 0; i < bList.size(); i++){
                        out.add(bList.get(bList.size() - 1 - i));
                    }
                }
            }
            System.out.println(out);
            return new CowSet(out);
        }

        @Override
        public int compareTo(CowSet o) {
            return -1 * o.getFirst().compareTo(this.getFirst());
        }
    }


    @Override
    public ArrayList<Character> run() {
        InputReader r = super.getInputReader();
        int lines = r.nextInt();
        HashMap<Character, ArrayList<CowSet>> map = new HashMap<>();
        char[] tracker = new char[8];
        for(int i = 0; i < tracker.length; i++){
            char c = (char)('a' + i);
            map.put(c, new ArrayList<>());
            map.get(c).add(new CowSet(c));
        }

        for(int i = 0; i < lines; i++){
            char a = r.next().charAt(0);
            r.next();
            r.next();
            r.next();
            r.next();
            char b = r.next().charAt(0);
            CowSet cowSet = new CowSet(a, b);

            if(map.get(a).get(0).getList().size() == 1){
                map.get(a).remove(0);
            }
            map.get(a).add(cowSet);
            if(map.get(b).get(0).getList().size() == 1){
                map.get(b).remove(0);
            }
            map.get(b).add(cowSet);
        }

        for(Character key : map.keySet()){
            System.out.println("Key: " + key);
            ArrayList<CowSet> list = map.get(key);
            if(list.size() == 2){
                list.get(0).merge(list.get(1));
            }
        }

        HashSet<CowSet> hashSet = new HashSet<>();
        for(Character key : map.keySet()) {
            System.out.println("Key: " + key);
            for (CowSet set : map.get(key)) {
                hashSet.add(set);
                System.out.print("\t");
                for (int i = 0; i < set.getList().size(); i++) {
                    System.out.print(set.getList().get(i) + " ");
                }
                System.out.println();

            }
        }
        System.out.println(hashSet);
        ArrayList<CowSet> list = new ArrayList<>();
        list.addAll(hashSet);
        Collections.sort(list);
        System.out.println(list);

        ArrayList<Character> out = new ArrayList<>();
        for(CowSet set : list){
            out.addAll(set.list);
        }
        System.out.println(out);
        return out;

    }

    @Override
    public ArrayList<Character> test() {
        ArrayList<Character> out = new ArrayList<>();
        out.add('a');
        out.add('h');
        out.add('b');
        out.add('d');
        out.add('e');
        out.add('f');
        out.add('c');
        out.add('g');
        return out;
    }
    public static void main(String[] args) throws Exception {
        Lineup lineup = new Lineup();
        lineup.testSample();
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

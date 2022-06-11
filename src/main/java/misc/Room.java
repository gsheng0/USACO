package misc;

import template.Template;

import java.util.ArrayList;

public class Room extends Template<ArrayList<Integer>> {
    public Room(){ super.setFilename(Room.class);}
    public class Slot{
        int start, end;
        public Slot(int start, int end){
            this.start = start;
            this.end = end;
        }
        public String toString() { return start + " " + end; }
    }
    public ArrayList<Integer> run(){
        Template.InputReader r = super.getInputReader();
        ArrayList<Integer> out = new ArrayList<>();
        int num = r.nextInt();
        Slot[] slots = new Slot[num];
        for(int i = 0; i < num; i++){
            slots[i] = new Slot(r.nextInt(), r.nextInt());
            System.out.println(slots[i]);
        }
        out.add(1);
        ArrayList<Slot> list = new ArrayList<>();
        list.add(slots[0]);
        for(int i = 1; i < slots.length; i++){
            Slot current = slots[i];
            boolean found = false;
            for(int x = 0; x < list.size(); x++){
                if(list.get(x).end < current.start){
                    list.set(x, current);
                    out.add(x + 1);
                    found = true;
                    break;
                }
            }
            if(!found){
                list.add(current);
                out.add(list.size());
            }
        }
        out.add(0, list.size());

        return out;

    }
    public ArrayList<Integer> test(){
        ArrayList<Integer> out = new ArrayList<>();
        out.add(2);
        out.add(1);
        out.add(2);
        out.add(1);
        return out;
    }
    public static void main(String args[]) throws Exception{
        Room room = new Room();
        room.testSample();
    }
}

package misc;

import template.Template;

import java.util.TreeSet;

public class Boxes extends Template<Integer> {
    public Boxes(){
        super.setClass(Boxes.class);
    }
    public Integer run(){
        Template.InputReader r = getInputReader();
        int count = r.nextInt();
        int[] boxes = new int[count];
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < count; i++){
            boxes[i] = r.nextInt();
        }
        for(int i = 0; i < count; i++){
            int current = boxes[i];
            if(current > 2 && !set.contains(current - 1)){
                set.add(current - 1);
            }
            else if(!set.contains(current)){
                set.add(current);
            }
            else if(!set.contains(current + 1)){
                set.add(current + 1);
            }
        }
        return set.size();
    }
    public Integer test(){
        return 5;
    }

    public static void main(String[] args) throws Exception{
        Boxes boxes = new Boxes();
        boxes.testSample();
    }
}
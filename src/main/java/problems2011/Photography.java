package problems2011;

import template.Template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Photography extends Template<ArrayList<Integer>>
{
    private int cows = -1;
    public Photography(){
        super.setFilename("Photography");
    }
    public ArrayList<Integer> run(){
        InputReader r = super.getInputReader();
        HashMap<Integer, Integer>[] maps = new HashMap[5];
        int size = r.nextInt();
        cows = size;
        for(int i = 0; i < 5; i++){
            maps[i] = new HashMap<>();
            for(int x = 0; x < size; x++){
                maps[i].put(r.nextInt(), x);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            list.add(i + 1);
        }
        Collections.sort(list, (a, b) -> compare(a, b, maps));
        return list;
    }
    public int compare(int a, int b, HashMap<Integer, Integer>[] maps){
        int counter = 0;
        for(int i = 0; i < 5; i++){
            if(maps[i].get(a) > maps[i].get(b)){
                counter++;
            }
        }
        return counter > 2 ? 1 : -1;

    }
    public ArrayList<Integer> test(){
        InputReader r = super.getInputReader();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < cows; i++){
            list.add(r.nextInt());
        }
        return list;
    }
    public static void main(String[] args) throws Exception {
        Photography app = new Photography();
        String[] inputNames = new String[10];
        String[] outputNames = new String[10];
        String[] suffixes = new String[10];
        for(int i = 0; i < 10; i++){
            int cardinal = i + 1;
            suffixes[i] = "" + cardinal;
            inputNames[i] = "I";
            outputNames[i] = "O";
        }
        app.testCases(inputNames, outputNames, suffixes, suffixes);
    }
}

package misc;

import template.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeFactor extends Template<List<Integer>> {
    public PrimeFactor(){
        super.setFilename("PrimeFactor");
    }
    public List<Integer> run(){
        InputReader r = super.getInputReader();
        int num = r.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 2; i <= Math.sqrt(num); i++){
            while(num % i == 0){
                list.add(i);
                num /= i;
            }
        }
        return list;
    }
    public List<Integer> test(){
        return Arrays.asList(5, 7, 7);
    }
    public static void main(String[] args) throws Exception {
        PrimeFactor app = new PrimeFactor();
        app.testSample();
    }
}

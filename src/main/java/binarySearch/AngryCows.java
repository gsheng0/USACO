package binarySearch;

import misc.Util;
import template.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AngryCows extends Template<Integer> {
    public AngryCows(){
        super.setFilename("AngryCows");
    }
    public Integer run(){
        InputReader r = super.getInputReader();
        int numHayBales = r.nextInt();
        int numCows = r.nextInt();
        int[] hayBales = new int[numHayBales];
        for(int i = 0; i < numHayBales; i++){
            hayBales[i] = r.nextInt();
        }
        Arrays.sort(hayBales);

        int max = Integer.MAX_VALUE;
        int minRadius = Integer.MAX_VALUE/2;
        for(int i = max; i >= 1; i /= 2){
            while(getNumCowsNeeded(hayBales, minRadius - i) <= numCows){
                minRadius -= i;
            }
        }

        return minRadius;
    }
    public int getNumCowsNeeded(int[] hayBales, int radius){
        if(radius < 0){
            return Integer.MAX_VALUE;
        }
        int range = hayBales[0] + radius * 2;
        int cows = 1;
        for(int i = 1; i < hayBales.length; i++){
            if(range < hayBales[i]){
                range = hayBales[i] + radius * 2;
                cows++;
            }
        }
        return cows;
    }
    public Integer test(){
        return 5;
    }

    public static void main(String[] args) throws Exception {
        AngryCows cow = new AngryCows();
        cow.testSample();
    }
}

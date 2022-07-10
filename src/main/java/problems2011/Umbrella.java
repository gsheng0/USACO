package problems2011;

import misc.Util;
import template.Template;

import java.util.Arrays;

public class Umbrella extends Template<Integer> {
    public Umbrella(){
        super.setFilename("Umbrella");
    }

    public Integer run(){
        InputReader r = super.getInputReader();
        int numCows = r.nextInt();
        int maxRange = r.nextInt();
        int[] cows = new int[numCows];
        int[] costs = new int[maxRange];
        for(int i = 0; i < cows.length; i++){
            cows[i] = r.nextInt();
        }

        for(int i = 0; i < costs.length; i++){
            costs[i] = r.nextInt();
        }

        Arrays.sort(cows);

        //Computing the minimum cost to cover a group of cows spanning any length from 1 to maxRange
        for(int i = maxRange - 2; i >= 0; i--){
            /*
            Thinking iteratively,
            The cheapest way to cover a group the length of the entire thing would be to buy the largest size umbrella
            If the group was separable, then a smaller one could be used, if that option was cheaper
             */
            costs[i] = Math.min(costs[i], costs[i + 1]);
        }

        int[] out = new int[numCows + 1];
        Arrays.fill(out, Integer.MAX_VALUE);
        out[0] = 0;

        for(int n = 1; n <= numCows; n++){
            for(int i = 0; i < n; i++){
                int costOfFirstSegment = out[i];
                int widthOfSecondSegment = cows[n - 1] - cows[i];
                int costOfSecondSegment = costs[widthOfSecondSegment];
                out[n] = Math.min(out[n], costOfFirstSegment + costOfSecondSegment);
                //out[n] - the current minimum
                //costs[cows[n - 1] - cows[i]] - the cost of buying an umbrella to cover the length of stalls from cow[i] to the current one
                //out[i] - the minimum to cover all the stalls right before cow[i]
            }
        }

        return out[numCows];

    }

    public Integer test(){
        return super.getInputReader().nextInt();
    }
    public static void main(String[] args) throws Exception {
        Umbrella app = new Umbrella();

        int cases = 10;
        String[] inputNames = new String[cases];
        String[] outputNames = new String[cases];
        String[] suffixes = new String[cases];
        for(int i = 0; i < cases; i++){
            int cardinal = i + 1;
            suffixes[i] = "" + cardinal;
            inputNames[i] = "I";
            outputNames[i] = "O";
        }
        //app.testSample();
        app.testCases(inputNames, outputNames, suffixes, suffixes);
    }
}

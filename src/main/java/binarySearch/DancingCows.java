package binarySearch;

import template.Template;

import java.util.PriorityQueue;
import java.util.Queue;

public class DancingCows extends Template<Integer> {
    public DancingCows(){
        super.setFilename("DancingCows");
    }
    public Integer run(){
        InputReader r = super.getInputReader();
        int numCows = r.nextInt();
        int maxTime = r.nextInt();
        int[] times = new int[numCows];
        for(int i = 0; i < numCows; i++){
            times[i] = r.nextInt();
        }
        int max = Integer.MAX_VALUE;
        int stageSize = Integer.MAX_VALUE/2;
        for(int i = max; i >= 1; i /= 2){
            while(getTimeNeeded(times, stageSize - i) <= maxTime){
                stageSize -= i;
            }
        }
        return stageSize;
    }
    public int getTimeNeeded(int[] times, int stageSize){
        if(stageSize < 1){
            return Integer.MAX_VALUE;
        }
        if(stageSize >= times.length){
            int max = times[0];
            for(int i = 1; i < times.length; i++){
                max = Math.max(max, times[i]);
            }
            return max;
        }
        Queue<Integer> queue = new PriorityQueue<>();
        //loading the stage
        for(int i = 0; i < stageSize; i++){
            queue.add(times[i]);
        }
        for(int i = stageSize; i < times.length; i++){
            queue.add(queue.poll() + times[i]);
        }

        while(queue.size() > 1){
            queue.poll();
        }
        return queue.poll();

    }
    public Integer test(){
        InputReader r = super.getInputReader();
        return r.nextInt();
    }
    public static void main(String[] args) throws Exception {
        DancingCows app = new DancingCows();
        app.testCases(10);
    }
}

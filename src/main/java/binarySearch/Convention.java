package binarySearch;

import misc.Util;
import template.Template;

import java.util.Arrays;

public class Convention extends Template<Integer> {
    public Convention(){
        super.setFilename("Convention");
    }
    public Integer run(){
        InputReader r = super.getInputReader();
        int numCows = r.nextInt();
        int numBuses = r.nextInt();
        int capacity = r.nextInt();
        int[] arrivalTimes = new int[numCows];
        for(int i = 0; i < numCows; i++){
            arrivalTimes[i] = r.nextInt();
        }
        return calculateMinimumWaitingTime(arrivalTimes, numBuses, capacity);
    }
    public boolean possible(int[] arrivalTimes, int numBuses, int capacity, int maximum){
        int latest = arrivalTimes[0] + maximum;
        int numCows = 1;
        for(int i = 1; i < arrivalTimes.length; i++){
            if(arrivalTimes[i] > latest || numCows == capacity){
                //if the arrival time is , have the next cow be picked up by the next bus
                //resetting the first variable
                latest = arrivalTimes[i] + maximum;
                numCows = 1;
                numBuses--;
                if(numBuses == 0){
                    return false;
                }
            }
            else{ //if time is less than the latest for this bus and the number of cows is less than the capacity
                numCows++;
            }
        }
        return true;
    }
    public int calculateMinimumWaitingTime(int[] arrivalTimes, int numBuses, int capacity){
        Arrays.sort(arrivalTimes);
        int max = Integer.MAX_VALUE;
        int waitingTime = Integer.MAX_VALUE;
        for(int i = max; i >= 1; i /= 2){
            while(possible(arrivalTimes, numBuses, capacity, waitingTime - i)){
                waitingTime -= i;
            }
        }
        return waitingTime;

    }
    public Integer test() {
        return 4;
    }
    public static void main(String[] args) throws Exception {
        Convention app = new Convention();
        app.testSample();
    }
}

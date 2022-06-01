package binarySearch;

import misc.HighCard;
import template.Template;

import java.util.Arrays;

public class MaxMedian extends Template<Integer> {
    public MaxMedian(){
        System.out.println("SET CLAZZZ VARIABLE");
        super.setClass(HighCard.class);
    }
    public Integer run(){
        int[] arr = new int[]{1, 3, 5};
        int operations = 2;
        return maxMedian(arr, operations);
    }
    public int maxMedian(int[] nums, int operations){
        Arrays.sort(nums);
        int max = Integer.MAX_VALUE;
        int maxMedian = 0;
        for(int i = max; i >= 1; i /= 2){
            System.out.println("i: " + i);
            while(getNumOperations(nums, maxMedian + i) <= operations){
                maxMedian += i;
            }
        }
        return maxMedian;
    }
    public int getNumOperations(int[] nums, int targetMedian){
        int numOperations = 0;
        for(int i = nums.length/2; i < nums.length; i++){
            numOperations += Math.max(0, targetMedian - nums[i]);
        }
        return numOperations;
    }
    public Integer test() { return 5; }
    public static void main(String[] args) throws Exception {
        MaxMedian max = new MaxMedian();
        max.testSample();
    }
}

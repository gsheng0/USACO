import java.util.LinkedList;

public class CircularBarn extends Template<Integer>{
    private class Node<E>
    {
        E val;
        Node<E> next;
        public Node(E val){
            this.val = val;
        }
    }
    public CircularBarn(){super.setClazz(CircularBarn.class);}
    public Integer run(){
        InputReader r = super.getInputReader();
        int num = r.nextInt();
        int[] rooms = new int[num]; //room[room number] = number of cows in that room
        int[] exSum  = new int[num];
        int[] sum = new int[num];
        int totalCows = 0;
        for(int i = 0; i < num; i++){
            rooms[i] = r.nextInt();
            totalCows += rooms[i];
        }
        for(int i = 0; i < num; i++){
            exSum[i] = totalCows - rooms[i];
        }
        int first = 0;
        for(int i = 1; i < num; i++){
            first += rooms[i] * i;
        }
        sum[0] = first;
        int min = first;
        for(int i = 1; i < num; i++){
            sum[i] = sum[i - 1] - exSum[i - 1] + (rooms[i - 1] * 4);
            if(min > sum[i]){
                min = sum[i];
            }
        }
        return min;
    }
    public Integer test(){
        return 48;
    }
    public static void main(String[] args) throws Exception{
        CircularBarn barn = new CircularBarn();
        barn.testSample();
    }
}
/*


4 -> 57 = 1(7) + 2(8) + 3(6) + 4(4) = 7 + 16 + 18 + 16
7 -> 48 = 1(8) + 2(6) + 3(4) + 4(4) = 8 + 12 + 12 + 16
8 -> 54 = 1(6) + 2(4) + 3(4) + 4(7) = 6 + 8 + 12 + 28
6 -> 65 = 1(4) + 2(4) + 3(7) + 4(8) = 4 + 8 + 21 + 32
4 -> 76 = 1(4) + 2(7) + 3(8) + 4(6) = 4 + 14 + 24 + 24

48 = 57 - (7 + 8 + 6 + 4) + 16 = 57 - 25 + 16 = 57 - 9 = 48
54 = 48 - (8 + 6 + 4 + 4) + 28 = 48 - 22 + 28 = 48 + 6 = 54
65 = 54 - (6 + 4 + 4 + 7) + 32 = 54 - 21 + 32 = 54 + 11 = 65


 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HighCard extends Template<Integer>{
    public HighCard(){
        super.setClazz(HighCard.class);
    }
    public Integer run(){
        InputReader r = super.getInputReader();
        int num = r.nextInt();
        int[] tracker = new int[num * 2];
        ArrayList<Integer> other = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < num; i++){
            other.add(r.nextInt());
            tracker[other.get(i) - 1]++;
        }
        for(int i = 0; i < tracker.length; i++){
            if(tracker[i] == 0){
                list.add(i + 1);
            }
        }

        Collections.sort(other);
        Collections.sort(list);
        int counter = 0;
        for(int i = other.size() - 1; i >= 0; i--){
            int otherCard = other.get(i);
            int index = -1;
            for(int j = 0; j < list.size(); j++){
                if(list.get(j) > otherCard){
                    index = j;
                }
            }
            if(index != -1){
                list.remove(index);
                counter++;
            }
        }
        return counter;
    }
    public Integer test(){
        return 2;
    }
    public static void main(String[] args) throws Exception{
        HighCard card = new HighCard();
        card.testSample();
    }

}

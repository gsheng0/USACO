import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FieldReductionRedux extends Template<Integer>{
    public class Point{
        double x, y;
        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
        public double distanceFrom(Point other){
            double deltaX = other.x - x;
            double deltaY = other.y - y;
            return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        }
        public String toString(){
            return x + ", " + y;
        }
    }

    public FieldReductionRedux(){
        super.setClazz(FieldReductionRedux.class);
    }
    public Integer run(){
        InputReader r = super.getInputReader();
        int num = r.nextInt();
        Point[] points = new Point[num];
        Point avg = new Point(0,0);
        for(int i = 0; i < num; i++){
            points[i] = new Point(r.nextInt(), r.nextInt());
            avg.x += points[i].x;
            avg.y += points[i].y;
        }
        avg.x /= num;
        avg.y /= num;
        List<Point> list = Arrays.asList(points);
        list.sort((o1, o2) -> {
            double dist1 = o1.distanceFrom(avg);
            double dist2 = o2.distanceFrom(avg);
            if(dist1 > dist2){
                return 1;
            }
            else if(dist1 < dist2){
                return -1;
            }
            return 0;
        });



        return -1;
    }
    public Integer test(){
        return 12;
    }
    public static void main(String[] args) throws Exception{
        FieldReductionRedux r = new FieldReductionRedux();
        r.testSample();
    }
}

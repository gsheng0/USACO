public class Mountains extends Template<Integer> {
    public Mountains(){
        super.setClazz(Mountains.class);
    }
    private static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y =y;
        }
        public String toString() { return x + ", " + y;}
        public boolean equals(Point other){
            return other.x == x && other.y == y;
        }
    }
    private static class Mountain{
        Point peak;
        int left;
        int right;
        public Mountain(int x, int y){
            peak = new Point(x, y);
            left = x - y;
            right = x + y;
        }
        public boolean coveredBy(Mountain other){
            if(this.left > other.left && this.right < other.right){
                return true;
            }
            return false;
        }
    }
    public Integer run(){
        InputReader r = super.getInputReader();
        int count = r.nextInt();
        Mountain[] mountains = new Mountain[count];
        for(int i = 0; i < count; i++){
            mountains[i] = new Mountain(r.nextInt(), r.nextInt());
        }
        boolean[] tracker = new boolean[count];
        for(int i = 0; i < count; i++){
            for(int j = 0; j < count; j++){
                if(i == j || tracker[i]) continue;
                //System.out.print("Is mountain " + i + " covered by Mountain " + j + "? ");
                if(mountains[i].coveredBy(mountains[j])){
                    //System.out.println("Yes");
                    tracker[i] = true;
                }
            }
        }
        int counter = 0;
        for(int i = 0; i < tracker.length; i++){
            if(!tracker[i]){
                counter++;
            }
        }
        return counter;
    }
    public Integer test(){
        return 2;
    }
    public static void main(String[] args) throws Exception{
        Mountains m = new Mountains();
        m.testSample();
    }
}

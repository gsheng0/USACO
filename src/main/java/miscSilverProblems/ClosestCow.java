package miscSilverProblems;

import template.Template;

public class ClosestCow extends Template<Integer> {
    public ClosestCow(){
        super.setFilename("ClosestCow");
    }

    @Override
    public Integer run() {
        InputReader r = super.getInputReader();
        int numPatches = r.nextInt();
        int numOtherCows = r.nextInt();
        int numCows = r.nextInt();
        int[] field = new int[200000];
        int max = -1;
        for(int i = 0; i < numPatches; i++){
            int pos = r.nextInt();
            field[pos] = r.nextInt();
            max = Math.max(pos, max);
        }

        for(int i = 0; i < numOtherCows; i++){
            int pos = r.nextInt();
            field[pos] = -1;
            max = Math.max(pos, max);
        }
        return 5;

    }

    @Override
    public Integer test() {
        return null;
    }
}

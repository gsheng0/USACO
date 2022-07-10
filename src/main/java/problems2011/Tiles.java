package problems2011;

import template.Template;

public class Tiles extends Template<Integer> {
    public Tiles(){
        super.setFilename("Tiles");
    }
    @Override
    public Integer run() {
        int inf = 100000;
        InputReader r = super.getInputReader();
        int numTiles = r.nextInt();
        int targetArea = r.nextInt();
        int[] tileSideLengths = new int[numTiles];
        for(int i = 0; i < numTiles; i++){
            tileSideLengths[i] = r.nextInt();
        }

        int[][] arr = new int[targetArea + 1][numTiles + 1];

        for(int i = 1; i <= targetArea; i++){
            arr[i][0] = inf;
        }

        for(int j = 1; j <= numTiles; j++){
            for(int i = 0; i <= targetArea; i++){
                arr[i][j] = inf;
                for(int k = 1; k * k <= i; k++){
                    int x = (tileSideLengths[j - 1] - k) * (tileSideLengths[j - 1] - k);
                    int current = x + arr[i - k*k][j - 1];
                    if(current < arr[i][j]){
                        arr[i][j] = current;
                    }
                }
            }
        }

        for(int i = 0; i < targetArea + 1; i++){
            for(int j = 0; j < numTiles + 1; j++){
                if(arr[i][j] == inf){
                    System.out.print("\tinf");
                }
                else{
                    System.out.print("\t" + arr[i][j]);
                }
            }
            System.out.println();
        }

        if(arr[targetArea][numTiles] == inf){
            return -1;
        }
        return arr[targetArea][numTiles];
    }

    public int getSwapPrice(int before, int after){
        return (before - after) * (before - after);
    }

    @Override
    public Integer test() {
        //return super.getInputReader().nextInt();
        return 5;
    }
    public static void main(String[] args) throws Exception {
        Tiles app = new Tiles();
        String[] inputNames = new String[12];
        String[] outputNames = new String[12];
        String[] suffixes = new String[12];
        for(int i = 0; i < 12; i++){
            int cardinal = i + 1;
            suffixes[i] = "" + cardinal;
            inputNames[i] = "I";
            outputNames[i] = "O";
        }
        app.testSample();
        //app.testCases(inputNames, outputNames, suffixes, suffixes);
    }
}

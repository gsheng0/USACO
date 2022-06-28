package problems2011;

import misc.Util;
import template.Template;

import java.util.ArrayList;

public class Pageant extends Template<Integer> {
    public Pageant(){
        super.setFilename("Pageant");
    }

    public static class Path{
        int[] pointA;
        int[] pointB;
        int distance = 0;
        ArrayList<ArrayList<int[]>> paths = new ArrayList<>();
        public Path(int[] pointA, int[] pointB){
            Util.print("Point A: ", pointA);
            Util.print("Point B: ", pointB);
            this.pointA = pointA;
            this.pointB = pointB;
            distance = Math.abs(pointA[0] - pointB[0]) + Math.abs(pointA[1] - pointB[1]);
            int xInc = -1;
            int yInc = -1;
            if(pointA[0] < pointB[0]){
                xInc = 1;
            }
            if (pointA[1] < pointB[1]) {
                yInc = 1;
            }
            ArrayList<int[]> path = new ArrayList<>();
            int[] curr = new int[]{pointA[0], pointA[1]};
            for(int x = pointA[0]; x < pointB[0]; x += xInc){
                curr = new int[]{x, curr[1]};
                path.add(curr);
            }
            for(int y = pointA[1]; y < pointB[1]; y += yInc){
                curr = new int[]{curr[0], y};
                path.add(curr);
            }
            path.remove(0);
            System.out.println("Path 1: ");
            for(int[] arr : path){
                Util.print(arr);
            }
            paths.add(path);
            path = new ArrayList<>();
            for(int y = pointA[1]; y < pointB[1]; y += yInc){
                curr = new int[]{curr[0], y};
                path.add(curr);
            }
            for(int x = pointA[0]; x < pointB[0]; x += xInc){
                curr = new int[]{x, curr[1]};
                path.add(curr);
            }
            path.remove(0);
            System.out.println("Path 2: ");
            for(int[] arr : path){
                Util.print(arr);
            }
            paths.add(path);

        }


    }

    public void markSpots(char[][] matrix, int x, int y, char mark){
        matrix[x][y] = mark;
        //checking squares directly above, below, left, and right of the square
        for(int offset = -1; offset < 2; offset++){
            if(offset == 0){
                continue;
            }

            try{
                if(matrix[x + offset][y] == 'X') {
                    markSpots(matrix, x + offset, y, mark);
                }
            }
            catch(Exception e){}
            try{
                if(matrix[x][y + offset] == 'X'){
                    markSpots(matrix, x, y + offset, mark);
                }
            }
            catch(Exception e){}
        }

    }

    public void print(char[][] matrix){
        for(char[] line : matrix){
            for(char c : line){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public Path findShortestPath(char[][] matrix, char a, char b){
        ArrayList<int[]> out = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int[] minA = new int[2];
        int[] minB = new int[2];
        for(int x1 = 0; x1 < matrix.length; x1++){
            for(int y1 = 0; y1 < matrix[0].length; y1++){
                if(matrix[x1][y1] != a){
                    continue;
                }
                for(int x2 = 0; x2 < matrix.length; x2++){
                    for(int y2 = 0; y2 < matrix[0].length; y2++){
                        if(matrix[x2][y2] != b){
                            continue;
                        }

                        //x1, y1 is a
                        //x2, y2 is b
                        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                        if(distance < min){
                            min = distance;
                            minA = new int[]{x1, y1};
                            minB = new int[]{x2, y2};
                        }
                    }
                }
            }
        }
        return new Path(minA, minB);
    }

    @Override
    public Integer run() {
        InputReader r = super.getInputReader();
        int height = r.nextInt();
        int width = r.nextInt();
        char[][] matrix = new char[height][width];
        for(int y = 0; y < height; y++){
            String line = r.next();
            for(int x = 0; x < width; x++){
                matrix[y][x] = line.charAt(x);
            }
        }

        int counter = 0;
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(matrix[y][x] == 'X'){
                    markSpots(matrix, y, x, (char)('A' + counter));
                    counter++;
                }
            }
        }
        print(matrix);
        Path ab = findShortestPath(matrix, 'A', 'C');
        for(int[] point : ab.paths.get(0)){
            matrix[point[0]][point[1]] = '0';
        }
        for(int[] point : ab.paths.get(1)){
            matrix[point[0]][point[1]] = '1';
        }
        print(matrix);

        return 1;
    }

    @Override
    public Integer test() {
        return 4;
    }
    public static void main(String[] args) throws Exception {
        Pageant app = new Pageant();
        app.testSample();
    }
}

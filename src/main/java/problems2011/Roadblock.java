package problems2011;

import template.Template;

import java.util.*;

public class Roadblock extends Template<Integer> {
    public static class Node{
        HashMap<Node, Integer> adjacentNodes = new HashMap<>();
        int val;
        public Node(int val){
            this.val = val;
        }
        void addNeighbor(Node node, int length){
            adjacentNodes.put(node, length);
        }
        public String toString(){
            return "Node " + val;
        }
        Node[] getSortedKeySetOfNeighbors(){
            Node[] keyset = new Node[adjacentNodes.size()];
            int counter = 0;
            for(Node node : adjacentNodes.keySet()){
                keyset[counter] = node;
                counter++;
            }
            Arrays.sort(keyset, (nodeA, nodeB) -> {
                Integer distA = adjacentNodes.get(nodeA);
                Integer distB = adjacentNodes.get(nodeB);
                return distA.compareTo(distB);
            });
            return keyset;
        }
    }
    public static class Pair<A, B>{
        A a;
        B b;
        public Pair(A a, B b){
            this.a = a;
            this.b = b;
        }
        public String toString(){
            return "{" + a + ", " + b + "}";
        }

    }
    public Roadblock(){
        super.setFilename("Roadblock");
    }
    @Override
    public Integer run() {
        InputReader r = super.getInputReader();
        int numFields = r.nextInt() + 1;
        int numPaths = r.nextInt();
        int[][] adjacencyMatrix = new int[numFields][numFields];
        Node[] nodes = new Node[numFields];
        for(int x = 0; x < adjacencyMatrix.length; x++){
            nodes[x] = new Node(x);
            for(int y = 0; y < adjacencyMatrix[0].length; y++){
                adjacencyMatrix[x][y] = -1;
            }
        }

        for(int i = 0; i < numPaths; i++){
            int nodeA = r.nextInt();
            int nodeB = r.nextInt();
            int length = r.nextInt();

            adjacencyMatrix[nodeA][nodeB] = length;
            adjacencyMatrix[nodeB][nodeA] = length;

            nodes[nodeA].addNeighbor(nodes[nodeB], length);
            nodes[nodeB].addNeighbor(nodes[nodeA], length);
        }
        numFields--;

        ArrayList<Pair<Node, Integer>> shortestPaths = getShortestPaths(nodes);
        int originalLength = shortestPaths.get(numFields).b;
        ArrayList<Node> path = getShortestPathToNode(shortestPaths, nodes, numFields);
        System.out.println("Path: " + path.toString());
        int max = originalLength;
        for(int i = 0; i < path.size() - 1; i++){
            System.out.println("Doubling path length from " + path.get(i) + " to " + path.get(i + 1));
            int originalPathLength = path.get(i).adjacentNodes.get(path.get(i + 1));
            path.get(i).adjacentNodes.replace(path.get(i + 1), originalPathLength * 2);
            path.get(i + 1).adjacentNodes.replace(path.get(i), originalPathLength * 2);
            int newLength = getShortestPaths(nodes).get(numFields).b;
            max = Math.max(newLength, max);

            path.get(i).adjacentNodes.replace(path.get(i + 1), originalPathLength);
            path.get(i + 1).adjacentNodes.replace(path.get(i), originalPathLength);

        }
        return max - originalLength;

    }
    public ArrayList<Node> getShortestPathToNode(ArrayList<Pair<Node, Integer>> shortestPaths, Node[] nodes, int val){
        ArrayList<Node> out = new ArrayList<>();
        Node before = nodes[val];
        while(before.val != 1){
            out.add(0, before);
            before = shortestPaths.get(before.val).a;
        }
        out.add(0, nodes[1]);
        return out;
    }
    public int getLengthOfPath(ArrayList<Node> path){
        int out = 0;
        for(int i = 0; i < path.size() - 1; i++){
            out += path.get(i).adjacentNodes.get(path.get(i + 1));
        }
        return out;
    }
    public ArrayList<Pair<Node, Integer>> getShortestPaths(Node[] nodes){
        ArrayList<Pair<Node, Integer>> out = new ArrayList<>();
        //Assume a pair, "p", at index "i"
        //The integer contained in p is the total distance it takes to go from node 1 to node i
        //The node contained in p is the node travelled to directly before node i, in the path taken to get to this node
        HashSet<Node> visited = new HashSet<>();
        out.add(new Pair<>(null, -1));
        for(int i = 1; i < nodes.length; i++){
            out.add(new Pair<>(null, Integer.MAX_VALUE));
        }
        out.get(1).b = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes[1]);
        while(queue.size() > 0){
            Node current = queue.poll();
            if(visited.contains(current)){
                continue;
            }
            for(Node dest : current.getSortedKeySetOfNeighbors()){
                int distanceFromCurrentToDest = current.adjacentNodes.get(dest);
                int distanceFromOriginToCurrent = out.get(current.val).b;
                int distanceFromOriginToDest = distanceFromCurrentToDest + distanceFromOriginToCurrent;
                int currentShortestDistance = out.get(dest.val).b;

                if(currentShortestDistance > distanceFromOriginToDest){
                    out.get(dest.val).b = distanceFromOriginToDest;
                    out.get(dest.val).a = current;
                }

                queue.add(dest);
            }
            visited.add(current);
        }
        return out;
    }


    public Integer test() {
        InputReader r = super.getInputReader();
        return r.nextInt();
    }
    public static void createBidirectionalPath(Node a, Node b, int dist){
        a.addNeighbor(b, dist);
        b.addNeighbor(a, dist);
    }
    public static void createBidirectionalPath(Node[] nodes, int a, int b, int dist){
        createBidirectionalPath(nodes[a], nodes[b], dist);
    }
    public static void main(String[] args) throws Exception {
        Roadblock app = new Roadblock();
        app.testCases(10);
    }
}

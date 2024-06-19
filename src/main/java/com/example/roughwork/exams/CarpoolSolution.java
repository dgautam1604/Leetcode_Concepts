package com.example.roughwork.exams;// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution2 {
    static Map<Integer, List<Integer>> adjacencyList;


    Solution2(){
        adjacencyList=new HashMap<>();
    }


    public int solution(int[] A, int[] B) {
        // Implement your solution here
        for (int i = 0; i < A.length; i++) {
            // Add connection from A[i] to B[i]
            addConnection(A[i], B[i]);

            // Add connection from B[i] to A[i]
            addConnection(B[i], A[i]);
        }
        return calculateMinimumFuelWithCarpooling();
    }

    private static void addConnection(int fromNode, int toNode) {
        adjacencyList.computeIfAbsent(fromNode, k -> new ArrayList<>()).add(toNode);
    }


    public static int calculateMinimumFuelWithCarpooling() {
        int sumofFuel=0;
        for (Integer node : adjacencyList.keySet()) {
            List<List<Integer>> paths = new ArrayList<>();
            List<Integer> currentPath = new ArrayList<>();
            findPathsToZeroDFS(node, 0, paths, currentPath);
            long minFuel = calculateMinFuelForNodeWithCarpooling(paths);
            sumofFuel= (int) (sumofFuel+minFuel);
            System.out.println("Minimum fuel needed for Node " + node + " to reach Node 0 with carpooling: " + minFuel + "L");
        }
        return sumofFuel;
    }

    private static void findPathsToZeroDFS(int currentNode, int destinationNode,       List<List<Integer>> paths, List<Integer> currentPath) {
        currentPath.add(currentNode);

        if (currentNode == destinationNode) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            for (int neighbor : adjacencyList.getOrDefault(currentNode, new ArrayList<>())) {
                if (!currentPath.contains(neighbor)) {
                    findPathsToZeroDFS(neighbor, destinationNode, paths, currentPath);
                }
            }
        }

        currentPath.remove(currentPath.size() - 1);
    }

    private static long calculateMinFuelForNodeWithCarpooling(List<List<Integer>> paths) {
        long minFuel = 0;

        for (List<Integer> path : paths) {
            int pathLength = path.size() - 1;

            // Calculate fuel based on the number of people carpooling (5 people at a time)
            long fuelNeeded = (pathLength / 5) + ((pathLength % 5 == 0) ? 0 : 1);

            // As it takes 1L of fuel for each 2 nodes
            minFuel += fuelNeeded;
        }

        return minFuel;


//        long minFuel = 0;
//
//        for (List<Integer> path : paths) {
//            int edgesCount = 0;
//
//            for (int i = 0; i < path.size() - 1; i++) {
//                int currentNode = path.get(i);
//                int nextNode = path.get(i + 1);
//
//                // Check if the edge (connection) is unique
//                if (adjacencyList.get(currentNode).contains(nextNode)) {
//                    edgesCount++;
//                }
//            }
//
//            // Calculate fuel based on the number of people carpooling (5 people at a time)
//            long fuelNeeded = (edgesCount / 5) + ((edgesCount % 5 == 0) ? 0 : 1);
//
//            // As it takes 1L of fuel for each edge
//            minFuel += fuelNeeded;
//        }
//
//        return minFuel;
    }


}

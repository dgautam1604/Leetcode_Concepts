package com.example.roughwork.exams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    Map<Integer, List<Integer>> adjacencyList;
    Solution(){
        adjacencyList=new HashMap<>();
    }
    public int solution(int[] A, int[] B) {
        // Populate the adjacency list based on the given connections
        for (int i = 0; i < A.length; i++) {
            // Add connection from A[i] to B[i]
            addConnection(A[i], B[i]);

            // Add connection from B[i] to A[i] (assuming it's an undirected graph)
            addConnection(B[i], A[i]);
        }

        // Now you have a graph represented by the adjacency list
        // You can perform any graph-related operations as needed

        // For example, you can print the adjacency list
        printAdjacencyList();

        // Return any relevant result based on your problem statement
        // For now, returning the number of nodes in the graph
        return adjacencyList.size();
    }

    // Helper method to add a connection to the adjacency list
    private void addConnection(int fromNode, int toNode) {
        adjacencyList.computeIfAbsent(fromNode, k -> new ArrayList<>()).add(toNode);
    }

    // Helper method to print the adjacency list
    private void printAdjacencyList() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " is connected to: ");
            System.out.println(entry.getValue());
        }
    }

    // Method to calculate minimum fuel needed for each node to reach node 0
    public void calculateMinimumFuelWithCarpooling() {
        int sumofFuel=0;
        for (Integer node : adjacencyList.keySet()) {
            List<List<Integer>> paths = new ArrayList<>();
            List<Integer> currentPath = new ArrayList<>();
            findPathsToZeroDFS(node, 0, paths, currentPath);
            long minFuel = calculateMinFuelForNodeWithCarpooling(paths);
            sumofFuel= (int) (sumofFuel+minFuel);
            System.out.println("Minimum fuel needed for Node " + node + " to reach Node 0 with carpooling: " + minFuel + "L");
        }
    }

    // Recursive DFS method to find paths from a node to node 0
    private void findPathsToZeroDFS(int currentNode, int destinationNode, List<List<Integer>> paths, List<Integer> currentPath) {
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

    // Helper method to calculate minimum fuel needed with carpooling based on common paths
    private static long calculateMinFuelForNodeWithCarpooling(List<List<Integer>> paths) {
        long minFuel = 0;

        for (List<Integer> path : paths) {
            int pathLength = path.size() - 1;  // Number of edges, not nodes

            // Calculate fuel based on the number of people carpooling (5 people at a time)
            long fuelNeeded = (pathLength / 5) + ((pathLength % 5 == 0) ? 0 : 1);

            // As it takes 1L of fuel for each edge
            minFuel += fuelNeeded;
        }

        return minFuel;
    }

    public static void main(String[] args) {
        int[] A = {0, 1, 1};
        int[] B = {1, 2, 3};

        Solution graphSolution = new Solution();
        int result = graphSolution.solution(A, B);
        System.out.println("Number of nodes in the graph: " + result);

        // Calculate minimum fuel needed for each node to reach node 0 with carpooling
        graphSolution.calculateMinimumFuelWithCarpooling();
    }
}

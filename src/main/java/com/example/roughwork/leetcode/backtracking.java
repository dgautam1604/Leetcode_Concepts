package com.example.roughwork.leetcode;

import java.util.*;

public class backtracking {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> l=new ArrayList<>();
//        List<List<Integer>> l2=new ArrayList<>();
        backtrack(l,new ArrayList<>(),nums, 0);
        return l;
    }

    private static void backtrack(List<List<Integer>> l,List<Integer> temp, int[] nums, int start) {
//        l2.add(temp);
//        System.out.println(l2);
        l.add(new ArrayList<>(temp));
        System.out.println(l);
        for(int i=start;i< nums.length;i++){
            temp.add(nums[i]);
            backtrack(l,temp,nums,i+1);
            temp.remove(temp.size()-1);
        }
    }
    public static String maximumOddBinaryNumber(String s) {
        String output="";
        Map<String, Integer> m=new HashMap<>();
        for(int i=0;i<s.length();i++){
            String str=s.substring(i,i+1);
            m.put(str,m.containsKey(str)?m.get(str)+1:1);
//            m.containsKey(str)?
        }
        System.out.println(m);
        StringBuilder ans = new StringBuilder();
        ans.append("1".repeat(m.containsKey("1")?m.get("1") - 1:0));
        ans.append("0".repeat(m.containsKey("0")?m.get("0"):0));
        ans.append("1");
        return ans.toString();
    }
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        recurse(res, 0, 0, "", n);
        System.out.println(res);
        return res;
    }

    public static void recurse(List<String> res, int left, int right, String s, int n) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }

        if (left < n) {
            recurse(res, left + 1, right, s + "(", n);
        }

        if (right < left) {
            recurse(res, left, right + 1, s + ")", n);
        }
    }

    public static int[] sortedSquares(int[] nums) {
        int left=0,right= nums.length-1;
        int[] m=new int[nums.length];
        int x= m.length-1;
        while(left<right){
            int a = (int) Math.pow(nums[left],2);
            int b = (int) Math.pow(nums[right],2);
            if(a>b){
                m[x]=a;
                x--;left++;
            }else{
                m[x]=b;
                x--;right--;
            }
        }
        m[x]=(int) Math.pow(nums[left],2);
        for(int p:m){
            System.out.println(p);
        }
        return m;
    }
    public static int minOperations2(int[] nums, int k) {
        // Count occurrences of each element
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int toupdate= 0;
        for (int num : count.keySet()) {
            if (num < k) {
                toupdate+= count.get(num);
            } else {
                continue;
            }
        }

        return toupdate;
    }
    public static int minOperations(int[] nums, int k) {
        long[] longNums = Arrays.stream(nums).asLongStream().toArray();
        int toupdate = 0;

        while (true) {
            Arrays.sort(longNums);
            if (longNums[0] >= k) {
                break;
            }

            if (longNums.length >= 2) {
                long x = longNums[0];
                long y = longNums[1];

                longNums = Arrays.copyOfRange(longNums, 2, longNums.length);
                long newValue = Math.min(x, y) * 2 + Math.max(x, y);
                longNums = Arrays.copyOf(longNums, longNums.length + 1);
                longNums[longNums.length - 1] = newValue;

                toupdate++;
            } else {
                // If there are less than two elements, break the loop
                break;
            }
        }

        return toupdate;
    }


    public static int[] countServers2(int n, int[][] edges, int signalSpeed) {
        Map<Integer, List<int[]>> graph = buildGraph(edges);
        int[] result = new int[n];

        for (int node = 0; node < n; node++) {
            int[] reachableNodes = dfs(graph, node, signalSpeed);
        }

        return result;
    }
    private static int[] dfs(Map<Integer, List<int[]>> graph, int start, int signalSpeed) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        dfsHelper(graph, start, signalSpeed, visited, result);
        result.remove(start);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void dfsHelper(Map<Integer, List<int[]>> graph, int current, int signalSpeed,
                                  Set<Integer> visited, List<Integer> result) {
        if (!visited.contains(current)) {
            visited.add(current);
            result.add(current);

            for (int[] neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                int neighborNode = neighbor[0];
                int neighborWeight = neighbor[1];

                if (neighborWeight % signalSpeed == 0) {
                    dfsHelper(graph, neighborNode, signalSpeed, visited, result);
                }
            }
        }
    }

    private static Map<Integer, List<int[]>> buildGraph(int[][] edges) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, weight});
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, weight});
        }

        return graph;
    }

    private static boolean areConnectable(Map<Integer, List<int[]>> graph, int a, int b, int signalSpeed) {
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a, 0});
        visited.add(a);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (currentNode == b && currentDistance % signalSpeed == 0) {
                return true;
            }

            for (int[] neighbor : graph.getOrDefault(currentNode, Collections.emptyList())) {
                int neighborNode = neighbor[0];
                int neighborWeight = neighbor[1];

                if (!visited.contains(neighborNode)) {
                    visited.add(neighborNode);
                    queue.add(new int[]{neighborNode, currentDistance + neighborWeight});
                }
            }
        }

        return false;
    }

    public static int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length+1;
        int[] ans = new int[n];
        ArrayList<ArrayList<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            g.add(new ArrayList<>());
        for (int i = 0; i < n-1; ++i) {
            g.get(edges[i][0]).add(i);
            g.get(edges[i][1]).add(i);
        }
        for (int i = 0; i < n; ++i) {
            int[] which = new int[n];
            boolean[] vis = new boolean[n];
            vis[i] = true;
            which[i] = -1;
            long[] dists = new long[n];
            HashMap<Integer,Integer> cts = new HashMap<>();
            int tc = 0;
            for (int j = 0; j < n; ++j)
                dists[j] = 1234567890123456L;
            dists[i] = 0;
            ArrayDeque<Integer> q = new ArrayDeque<>();
            for (int e : g.get(i)) {
                int v = edges[e][0];
                if (v==i)
                    v = edges[e][1];
                which[v] = v;
                q.add(v);
                vis[v] = true;
                dists[v] = edges[e][2];
                cts.put(v,0);
                if (dists[v]%signalSpeed==0) {
                    ++tc;
                    cts.put(which[v],cts.get(which[v])+1);
                }
            }
            while (!q.isEmpty()) {
                int u = q.removeFirst();
                for (int e : g.get(u)) {
                    int v = edges[e][0];
                    if (v==u)
                        v = edges[e][1];
                    if (vis[v])
                        continue;
                    which[v] = which[u];
                    q.add(v);
                    vis[v] = true;
                    dists[v] = dists[u]+edges[e][2];
                    if (dists[v]%signalSpeed==0) {
                        ++tc;
                        cts.put(which[v],cts.get(which[v])+1);
                    }
                }
            }
            for (int e : g.get(i)) {
                int v = edges[e][0];
                if (v==i)
                    v = edges[e][1];
                int ct = cts.get(v);
                ans[i] += ct*(tc-ct);
            }
            ans[i]/=2;
        }
        return ans;
    }
    public static void main(String[] args) {
//        subsets(new int[]{1,2,3});
//        maximumOddBinaryNumber("010");
//        generateParenthesis(3);
//        sortedSquares(new int[]{-7,-3,2,3,11});

//        int[] nums1 = {1000000000,999999999,1000000000,999999999,1000000000,999999999};
//
//        int k1 = 1000000000;
//        int result1 = minOperations(nums1, k1);
//        System.out.println("Input: " + Arrays.toString(nums1) + ", k = " + k1 + "\nOutput: " + result1);

        int n = 7;
        int[][] edges = {{0,6,3},{6,5,3},{0,3,1},{3,2,7},{3,1,6},{3,4,2}};
        int signalSpeed = 3;
        int[] result = countPairsOfConnectableServers(edges, signalSpeed);
        System.out.println(Arrays.toString(result));


    }
}

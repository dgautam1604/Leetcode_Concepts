package com.example.roughwork.Prep.Graph;

import java.util.*;

public class Graph {
    public static void depthFirstPrint(Map<String, String[]> l, String src){
        // Default initialization of Stack
        Stack stack1 = new Stack();

        // Initialization of Stack using Generics for practice
        //Stack<String> stack2 = new Stack<String>();
        stack1.push(src);
        while (!stack1.empty()){
            String current= (String) stack1.pop();
            System.out.println(current);
            for (String s:l.get(current)){
                stack1.push(s);
            }
        }
    }
    public static void depthFirstRecursive(Map<String, String[]> l, String src){
        System.out.println(src);
        for (String s:l.get(src)){
            depthFirstRecursive(l,s);
        }
    }
    public static void breadthFirstPrint(Map<String, String[]> l, String src){
        Queue<String> ll= new LinkedList<String>();
        ll.add(src);
        while (!ll.isEmpty()){
            String current= (String) ll.remove();
            System.out.println(current);
            for (String s:l.get(current)){
                ll.add(s);
            }
        }
    }
    public static boolean hasPathRecursive(Map<String, String[]> l, String src, String dst){
        if(src.equals(dst))
            return true;
        for ( String s: l.get(src))
            if(hasPathRecursive(l,s,dst))
                return true;
        return false;
    }
    public static boolean hasPathIterative (Map<String, String[]> l, String src, String dst){
        Queue<String> ll= new LinkedList<String>();
        ll.add(src);
        while (!ll.isEmpty()){
            String current= (String) ll.remove();
            if(current.equals(src))
                return true;
//            System.out.println(current);
            for (String s:l.get(current)){
                ll.add(s);
            }
        }
        return false;
    }

    public static boolean hasPathRecursiveForCycles(
            Map<String, String[]> l, String src, String dst, HashSet<String> set){
        if(src.equals(dst))
            return true;
        if (set.contains(src))
            return false;

        set.add(src);
        for ( String s: l.get(src))
            if(hasPathRecursiveForCycles(l,s,dst,set))
                return true;
        return false;
    }
//Connected Component meaning no. of islands
    public static int ConnectedComponentCount(Map<String, String[]> graph){
        HashSet<String> set=new HashSet<>();
        int count=0;

        for ( String s: graph.keySet())
            if(explore(graph,s,set))
                count++;
        return count;
    }
    public static boolean explore(
            Map<String, String[]> graph, String current,HashSet<String> set){
        if(set.contains(current))
            return false;
        set.add(current);
        for(String s:graph.get(current)){
            explore(graph,s,set);
        }
        return true;
    }
    //largest Component meaning no. of islands
    public static int largestComponentCount(Map<String, String[]> graph){
        HashSet<String> set=new HashSet<>();
        int largest=0;

        for ( String s: graph.keySet()) {
            int size=exploreSize(graph,s,set);
            if (size>largest)
                largest=size;
        }
        return largest;
    }
    public static int exploreSize(
            Map<String, String[]> graph, String node,HashSet<String> set){
        if(set.contains(node))
            return 0;
        set.add(node);
        int size=1;
        for(String s:graph.get(node)){
            size+=exploreSize(graph,s,set);
        }
        return size;
    }
    //Shortest Path
    public static int shortestPath(Map<String, List<String>> graph, String src,String dst){

        HashSet<String> visited=new HashSet<>();
        Map<String,Integer> h=new HashMap<>();
        Queue<String> ll= new LinkedList<String>();
        ll.add(src);
        h.put(src,0);
        while (!ll.isEmpty()){
            String current=  ll.remove();
            int distance=h.get(current);
            if(current.equals(dst))
                return distance;
//            System.out.println(current);
            for (String neighbor:graph.get(current)){
                if(!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    ll.add(neighbor);
                    h.put(neighbor,distance+1);
                }
            }
        }
        return -1;
    }
    public static Map<String, List<String>> buildGraphFromEdges(List<String[]> edges){
        Map<String, List<String>> l=new HashMap<>();
        for(String[] e:edges) {
            String a=e[0];
            String b=e[1];
            if(!l.containsKey(a))
                l.put(a,new ArrayList<String>());
            if(!l.containsKey(b))
                l.put(b,new ArrayList<String>());
            List<String> aList=l.get(a);
            aList.add(b);
            l.put(a,aList);
            List<String> bList=l.get(b);
            bList.add(a);
            l.put(b,bList);
        }
        return  l;
    }

    public static void main(String[] args) {
        Map<String, String[]> l=new HashMap<>();
        l.put("a",new String[]{"c","b"});
        l.put("b",new String[]{"d"});
        l.put("c",new String[]{"e"});
        l.put("d",new String[]{"f"});
        l.put("e",new String[]{});
        l.put("f",new String[]{});

//        depthFirstPrint(l,"a");
//        breadthFirstPrint(l,"a");
        List<String[]> ll=new ArrayList<>();
        ll.add(new String[]{"w","x"});
        ll.add(new String[]{"x","y"});
        ll.add(new String[]{"z","y"});
        ll.add(new String[]{"w","v"});
        ll.add(new String[]{"z","v"});

        System.out.println(buildGraphFromEdges(ll));
        System.out.println(shortestPath(buildGraphFromEdges(ll),"w","z"));
    }
}

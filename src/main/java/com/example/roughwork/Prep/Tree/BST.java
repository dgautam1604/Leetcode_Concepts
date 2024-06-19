package com.example.roughwork.Prep.Tree;

import java.util.*;

public class BST {
    public static class TreeNode { public int val;
      public TreeNode left;
      public TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    public static int sumOfLeftLeaves(TreeNode root) {
        Queue<TreeNode> s=new LinkedList<>();
        s.add(root);
        int leftSum=0;

        while(!s.isEmpty()){
            TreeNode current=s.remove();
            if(current.left!=null) {
                if(current.left.right==null && current.left.left==null)
                    leftSum += current.left.val;
                s.add(current.left);
            }
            if(current.right!=null) {
                s.add(current.right);
            }
        }
        return leftSum;

    }

    public TreeNode invertTree(TreeNode root) {
        if(root==null)
            return null;
        Queue<TreeNode> s=new LinkedList<>();
        s.add(root);

        while(!s.isEmpty()){
            TreeNode current=s.remove();
            TreeNode left=current.left;
            current.left=current.right;
            current.right=left;

            if(current.left!=null) {
                s.add(current.left);
            }
            if(current.right!=null) {
                s.add(current.right);
            }
        }
        return root;
    }
    static void printInorder(TreeNode node)
    {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
    static void printPreorder(TreeNode node)
    {
        if (node == null)
            return;
        System.out.print(node.val + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }
    static void printPostorder(TreeNode node)
    {
        if (node == null)
            return;

        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.val + " ");
    }

    static void bfs(TreeNode root){
        if(root==null)
            return;
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode current=q.remove();
            if(current.left!=null)
                q.add(current.left);
            if(current.right!=null)
                q.add(current.right);
        }
    }
//    ----------------IS SYMMETRIC ----------------------------

    public static boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return isMirror(root.left,root.right);
    }
    static boolean isMirror(TreeNode root1, TreeNode root2){
        if(root1==null && root2==null){
            return true;
        }
        if(root1==null || root2==null){
            return false;
        }
        if(root1.val!=root2.val){
            return false;
        }
        return isMirror(root1.left,root2.right) && isMirror(root1.right,root2.left);
    }
//---------------------IS VALID BST---------------------------------------------------------
    static TreeNode prev = null;
    static boolean result = true;
    public static boolean isValidBST1(TreeNode root) {
        helper(root);
        return result;
    }
    public static void helper(TreeNode root){
        if(root == null)
            return;

        helper(root.left);
        if(prev == null)
            prev = root;
        else if(root.val <= prev.val)
            result = false;
        else
            prev = root;
        helper(root.right);
    }

    public boolean isValidBST2(TreeNode root) {
        return isValidBSTCode(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBSTCode(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBSTCode(root.left, minVal, root.val) && isValidBSTCode(root.right, root.val, maxVal);
    }

    //---------------------Level Order---------------------------------------------------------
    public static List<List<Integer>>  levelOrder(TreeNode root){
        List<List<Integer>> l = new ArrayList <> ();
        List<Integer> temp = new ArrayList <> ();
        if(root == null)
            return l;
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.addLast (root);
        while(!queue.isEmpty())
        {
            int size=queue.size();
            while(size -- > 0)
            {
                TreeNode fnt=queue.removeFirst();
                temp.add(fnt.val);
                if(fnt.left != null)
                    queue.add(fnt.left);
                if(fnt.right != null)
                    queue.add(fnt.right);
            }
            l.add(new ArrayList<>(temp));
            temp.clear();
        }
        return l;
    }
    //---------------------MAX DEPTH---------------------------------------------------------
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        int left = 0,right=0;
        if(root.left!=null)
            left=maxDepth(root.left);
        if(root.right!=null)
            right=maxDepth(root.right);
        return 1+Math.max(left,right);
    }
    //---------------------Create a BST---------------------------------------------------------
    public static TreeNode sortedArrayToBST(int arr[],int start, int end){
        if(start>end)
            return null;

        int mid = start + (end - start) / 2; //mid index
        TreeNode n=new TreeNode(arr[mid]);

        n.left=sortedArrayToBST(arr,start, mid-1);
        n.right=sortedArrayToBST(arr,mid+1, end);

        return n;

    }




    // ------------------------insert a new key in BST----------------
    TreeNode insertRec(TreeNode root, int key)
    {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        // Otherwise, recur down the tree
        else if (key < root.val)
            root.left = insertRec(root.left, key);
        else if (key > root.val)
            root.right = insertRec(root.right, key);

        // Return the (unchanged) node pointer
        return root;
    }

    public void insertIterative(int key,TreeNode root)
    {
        TreeNode node = new TreeNode(key);
        if (root == null) {
            root = node;
            return;
        }
        TreeNode prev = null;
        TreeNode temp = root;
        while (temp != null) {
            if (temp.val > key) {
                prev = temp;
                temp = temp.left;
            }
            else if (temp.val < key) {
                prev = temp;
                temp = temp.right;
            }
        }
        if (prev.val > key)
            prev.left = node;
        else
            prev.right = node;
    }
    // ------------------------Delete a key from BST----------------
    TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return root;

        // Recursive calls for ancestors of node to be deleted
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        // We reach here when root is the node to be deleted.
        // If one of the children is empty
        if (root.left == null) {
            TreeNode temp = root.right;
            return temp;
        } else if (root.right == null) {
            TreeNode temp = root.left;
            return temp;
        }

        // If both children exist
        else {
            TreeNode succParent = root;
            // Find successor
            TreeNode succ = root.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }

            // Delete successor.  Since successor
            // is always left child of its parent
            // we can safely make successor's right
            // right child as left of its parent.
            // If there is no succ, then assign
            // succ.right to succParent.right
            if (succParent != root)
                succParent.left = succ.right;
            else
                succParent.right = succ.right;

            // Copy Successor Data to root
            root.val = succ.val;

            // Delete Successor and return root
            return root;
        }
    }

    //flatten tree to a tree in the form of linked list with all left nodes be null and right nodes be in
    //preod=rder traversal of the original array
//    Input: root = [1,2,5,3,4,null,6]
//Output: [1,null,2,null,3,null,4,null,5,null,6]
    public static void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }
//---------------------MAIN---------------------------------------------------------

    public static void main(String[] args)
    {
        //root = [1,2,2,3,4,4,3]
       TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode();
        root.right.right = new TreeNode(6);
        flatten(root);
//        printInorder(root);
//        System.out.println();
//        printPreorder(root);
//        System.out.println();
//        printPostorder(root);
//        System.out.println();
//        System.out.println(isSymmetric(root)); //Input: root = [1,2,2,3,4,4,3;  Output: true

        // 1
//        / \
//        2  2
//       /\  /\
//       3 4 4 3

        int[] arr={1,2,2,3,4,4,3};
        TreeNode root2=sortedArrayToBST(arr,0,arr.length-1);
        System.out.println("Root 2");
        printInorder(root2);
    }

}

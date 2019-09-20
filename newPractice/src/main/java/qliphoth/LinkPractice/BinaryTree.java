package qliphoth.LinkPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {

    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }

    /**
     *  二叉树中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) {
            return list;
        }
        Stack<TreeNode> s = new Stack<>();
        while(root!=null||!s.empty()) {
            while(root!=null){
                s.push(root);
                root = root.left;
            }
            TreeNode node = s.pop();
            list.add(node.val);
            root = node.right;
        }
        return list;
    }

    /**
     *  二叉树后序遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) {
            return list;
        }
        Stack<TreeNode> s = new Stack<>();
        while(root!=null||!s.empty()) {

        }
        return list;
    }

    /**
     * 55.题目二 判断平衡二叉树：修改原获取depth的函数，用-1代表不是平衡二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return searchBalanced(root)!=-1;
    }

    private int searchBalanced(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int leftDepth = searchBalanced(root.left);
        int rightDepth = searchBalanced(root.right);
        if(leftDepth==-1||rightDepth==-1||Math.abs(leftDepth-rightDepth)>1) {
            return -1;
        } else {
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
}

package qliphoth.LinkPractice;

public class BinaryTree {

    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
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

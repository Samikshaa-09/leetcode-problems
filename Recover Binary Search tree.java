class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                // Process current node
                if (prev != null && prev.val > curr.val) {
                    if (first == null) first = prev;
                    second = curr;
                }
                prev = curr;
                curr = curr.right;
            } else {
                // Find in-order predecessor
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Create temporary link to return to current node
                    predecessor.right = curr;
                    curr = curr.left;
                } else {
                    // Revert the temporary link
                    predecessor.right = null;

                    // Process current node
                    if (prev != null && prev.val > curr.val) {
                        if (first == null) first = prev;
                        second = curr;
                    }
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        // Swap misplaced node values
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
}

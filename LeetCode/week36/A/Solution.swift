 public class TreeNode {
    public var val: Int
    public var left: TreeNode?
    public var right: TreeNode?
    public init(_ val: Int) {
        self.val = val
        self.left = nil
        self.right = nil
    }
 }
 class Solution1 {
    func mergeTrees(_ t1: TreeNode?, _ t2: TreeNode?) -> TreeNode? {
        guard let tree1 = t1 else {
            return t2
        }
        guard let tree2 = t2 else {
            return t1
        }
        
        var root = TreeNode(tree1.val + tree2.val)
        root.left = self.mergeTrees(tree1.left, tree2.left)
        root.right = self.mergeTrees(tree1.right, tree2.right)
        return root;
    }
 }


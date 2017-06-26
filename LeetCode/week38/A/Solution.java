import java.util.*;

public class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        System.out.println(nums);
        
        int ans = 1;
        for (int i = nums.length - 1; i >= 0 && i >= nums.length - 3; i -- ) {
            ans *= nums[i];
        }
        int tmp = nums[0] * nums[1] * nums[nums.length - 1];
        if (tmp > ans) {
            ans = tmp;
        }
        return ans;
    }
    
    public static void main(String [] args) {
        Solution sol = new Solution();
        int [] nums = new int[] {1, 0, -8, -7};
        int ans = sol.maximumProduct(nums);
        System.out.println(ans);
    }
    
}
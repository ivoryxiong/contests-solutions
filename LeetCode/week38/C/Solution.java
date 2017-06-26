/**
@see http://www.cnblogs.com/saltless/archive/2011/06/01/2065619.html
**/
class Solution {
    long [][] ans = new long[1001][1001];    
    public int kInversePairs(int n, int m) {
        if (n == 1) {
            if (m == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int len = n > m ? n : m;
        long [][] ans = new long[1001][len + 1];    
        ans[2][0] = 1;
        ans[2][1] = 1;
        long c = 0;
        long mod  =  1000000007L;
        for (int i = 3; i <= n ; i ++) {
            ans[i][0] = 1;
            for (int j = 1; j <= len; j ++) {
                c = (ans[i][j-1] + ans[i-1][j])%mod;
                int b = j - i;
                if (b >= 0) {
                    c = (c - ans[i-1][b] + mod) % mod;
                }
                ans[i][j] = c;
                c = 0;
            }
        }
        return (int)ans[n][m];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.kInversePairs(1000,1000));
    }
}
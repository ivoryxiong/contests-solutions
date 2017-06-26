import java.util.Arrays;

public class Solution {

    public int leastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        int[] prev = new int[26];
        for (int i = 0; i < 26; i ++) {
            cnt[i] = 0;
            prev[i] = -1;
        }
        int rnd = 0;
        int maxn = 0;
        for (int i = 0; i < tasks.length; i ++) {
            cnt[tasks[i] - 'A'] += 1;
            rnd += 1;
        }
       
/*
for(int i = 0; i < 26; i ++) {
            if (cnt[i] > maxn) {
                maxn = i;
            }
        }
        
        prev[maxn] = 1;
        cnt[maxn] -= 1;
        rnd -= 1;
        int t = 1;
*/
        int t =0;
        while (rnd > 0) {
            t += 1;
            Arrays.sort(cnt);
            boolean has = false;
            for(int i = 25; i >= 0; i --) {
                if (cnt[i] == 0) {
                    continue;
                }
                
                if (prev[i] <= 0) {
                    prev[i] = t;
                    cnt[i] -= 1;
                 has=true;
                 rnd -= 1;
                System.out.println(i);
                    break;
                }
                
                if (t - prev[i] > n) {
                    prev[i] = t;
                    cnt[i] -= 1;
                    rnd -= 1;
                 has=true;
                System.out.println(i);
                    break;
                }
            }
            if (!has) {
                System.out.println("null"); 
            }
        }
        return t;
    
    }
    
    public static void main(String [] args) {
        Solution sol = new Solution();
        char[]cs={'A','A','A','B','B','B'};
        int n = 1;
        int ans = sol.leastInterval(cs, n);
        System.out.println("ans " + ans);
    }
}

class Solution {
    
    func triangleNumber(_ nums: [Int]) -> Int {
        let len = 1001
        var cnt = Array(repeating: 0, count: len)
        var sum = Array(repeating: 0, count: len)
        var m = 0
        for i in nums {
            if i == 0 {
                continue
            }
            cnt[i] += 1;
            if i > m {
                m = i
            }
        }
        for i in (1..<cnt.count) {
            sum[i] = sum[i-1] + cnt[i]
        }
        
        //        print(cnt)
        //        print(sum)
        print(m)
        var ans = 0
        for i in 0...m {
            if cnt[i] == 0 {
                continue
            }
            
            if (cnt[i] > 1) {// eq
                let l = min(i * 2 - 1,m)
                ans += cnt[i] * (cnt[i] - 1) / 2 * (sum[l] - cnt[i])
                //                print("\(i) \(i) \(l) : \(ans)")
            }
            
            if (cnt[i] > 2) {//tr
                ans += cnt[i] * (cnt[i] - 1) * (cnt[i] - 2) / 6
                //                print("\(i) \(i) \(i) : \(ans)")
                
            }
        }
        for i in 0..<m {
            for j in (i+1)..<m {
                if cnt[j] == 0 {
                    continue
                }
                let l = min(m+1, i + j)
                let s = j + 1
                if (l - 1 <= m && s <= l) {
                    ans += cnt[i] * cnt[j] * (sum[l-1] - sum[s - 1]);
                } else {
                    break
                }
                //                print("\(i) \(j) \(l) : \(ans)")
            }
        }
        
        return ans
    }
}

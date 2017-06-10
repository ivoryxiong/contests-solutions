class Solution {
    func findIntegers(_ n: Int) -> Int {
        if (n < 3) {
            return n + 1
        }
        
        //step1: 转化成数组
        var digits : [Int] = []
        var tmp = n
        while tmp > 0 {
            digits.append(tmp&1)
            tmp = tmp >> 1
        }
        digits = digits.reversed()
        
        //step2: 检查n是否是满足条件的数
        var cnt = 1
        for i in 1..<digits.count {
            if (digits[i] == 1 && digits[i-1] == 1) {
                cnt = 0
            }
        }
        
        //step3: 统计出二进制为x位时，满足条件的数的个数
        var ret = [0, 2, 3]
        for _ in 3...(digits.count + 1) {
            ret.append(ret[ret.count - 1] + ret[ret.count - 2])
        }
        
        //step4: 计算<n的满足条件的数的个数，不断把前面的1变为0
        var prev = 0
        for i in 0..<digits.count {
            if digits[i] == 1 {
                var lenidx = digits.count - (i+1)
                if lenidx > 0 {
                    cnt += ret[lenidx]
                } else {
                    cnt += 1
                }
                prev += 1
                if (prev >= 2) {
                    break
                }
            } else {
                prev = 0
            }
        }
        
        return cnt
        
    }
}

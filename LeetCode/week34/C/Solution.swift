import Foundation

class Solution {
    func arrayNesting(_ nums: [Int]) -> Int {
        let len = nums.count
        var visited = Array(repeating:0, count:len)
        var ans = 1
        for i in 0..<len {
            var now = i
            var step = 0
            while visited[now] == 0 {
                step += 1
                visited[now] = step
                now = nums[now]
            }
            if step > ans {
                ans = step
            }
        }
        return ans
    }
}

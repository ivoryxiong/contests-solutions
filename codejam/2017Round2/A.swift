import Foundation

class CodeJam {
    //https://code.google.com/codejam/contest/5314486/dashboard  A
    static func maxFresh(_ size: Int, group : [Int]) -> Int {
        var rnd : [Int] = Array(repeating: 0, count: size)
        for g in group {
            rnd[g%size] += 1
        }
//        if size > 3 {
//            print(rnd)
//        }
        var ans = rnd[0]
        //2 in 1
        for i in 1..<size {
            if rnd[i] <= 0 {
                continue
            }
            if i + i < size {
                let k = min(rnd[i], rnd[size - i])
                ans += k
                rnd[i] -= k
                rnd[size - i] -= k
            } else if i + i == size {
                ans += (rnd[i]/2)
                rnd[i] = rnd[i] % 2
            } else {
                break;
            }
        }
        
        // 3 in 1
        if size > 3 {
            //1 + 1 + 2
            let k = min(rnd[1]/2, rnd[2]);
            ans += k;
            rnd[1] -= 2*k;
            rnd[2] -= k;
        }
        
        // same in 1
        var has = false
        for i in 1..<size {
            if rnd[i] == 0 {
                continue
            }
            ans += (rnd[i] / size)
            rnd[i] %= size;
            if (rnd[i] > 0) {
                has = true
            }
        }
        
        if (has) {
            ans += 1
        }
        return ans
    }
}


if let line = readLine(strippingNewline:true) {
    if let T = Int(line) {
        for i in 1...T {
//            var mp = readLine(strippingNewline:true)?.components(separatedBy:CharacterSet.whitespaces)
            let values = readLine(strippingNewline:true)?.components(separatedBy: CharacterSet.whitespaces).flatMap { Int($0.trimmingCharacters(in: .whitespaces)) }
            if let groups = readLine(strippingNewline:true)?.components(separatedBy: CharacterSet.whitespaces).flatMap ({ Int($0.trimmingCharacters(in: .whitespaces)) }) {
                let ans = CodeJam.maxFresh(Int(values![1]), group: groups)
                print("Case #\(i): \(ans)")
            }
        }
    }
}

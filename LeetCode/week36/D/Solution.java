import java.util.*;

class Seg implements Comparable<Seg> {
    int beg;
    int end;
    
    Seg(int b, int e) {
        beg = b;
        end = e;
    }
    
    @Override
    public int compareTo(Seg o) {
        if (this.beg == o.beg) {
            return this.end - o.end;
        } else {
            return this.beg - o.beg;
        }
    }
}

public class Solution {
    public String addBoldTag(String s, String[] dict) {
        List<Seg> segs = new ArrayList<Seg>();
        
        for (String p : dict) {
            int idx = 0;
            int beg = -1;
            do {
                beg = s.indexOf(p, idx);
                if (beg >= 0) {
                    Seg seg = new Seg(beg, beg + p.length() - 1);
                    segs.add(seg);
                }
                idx += 1;
            } while(beg >= 0 && idx < s.length());

        }
        if (segs.size() > 0) {
            Collections.sort(segs);
            Seg seg = segs.get(0);
            ArrayList<Seg> merged = new ArrayList<Seg>();
            for (int i =1; i < segs.size(); i ++) {
                Seg tmp = segs.get(i);
                if (tmp.beg <= seg.end + 1) {
                    seg.beg = Math.min(seg.beg, tmp.beg);
                    seg.end = Math.max(seg.end, tmp.end);
                } else {
                    merged.add(seg);
                    seg = tmp;
                }
            }
            merged.add(seg);
            
            StringBuffer sb = new StringBuffer(s);
            for (int i= merged.size() - 1; i >=0 ;i --) {
                Seg tmp = merged.get(i);
                System.out.println("seg :\t" + tmp.beg + "\t" + tmp.end);
                sb.insert(tmp.end + 1, "</b>");
                sb.insert(tmp.beg, "<b>");
            }
            return sb.toString();
        } else {
            return s;
        }
    }
    
    public static void main(String args[]) {
        Solution sol = new Solution();
        String ret;
        String[] ps ;
        ps = new String[] {"abc","123"};
        ret = sol.addBoldTag("abcxyz123", ps);
        System.out.println(ret);
        ps = new String[] {"aaa","aab", "bc"};
        ret = sol.addBoldTag("aaabbcc", ps);
        System.out.println(ret);
        ps = new String[] {"aaa","aa", "a"};
        ret = sol.addBoldTag("", ps);
        System.out.println(ret);

    }
}

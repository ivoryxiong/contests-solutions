import java.util.LinkedList;
import java.util.List;


public class StringIterator {

    int idx = 0;
    int cidx = 0;
    List<Character> chars = new LinkedList<Character>();
    List<Integer> sums = new LinkedList<Integer>();
    public StringIterator(String compressedString) {
        int sum = 0;
        int total = 0;
        String fake = compressedString + "$";
        for (int i =0 ;i < fake.length(); i ++) {
            char c = fake.charAt(i);
            if (c >= '0' && c <= '9') {
                sum = sum * 10 + (c - '0');
            } else {
                total += sum;
                if (total > 0) {
                    this.sums.add(total);
                }
                sum = 0;
                if ((c >= 'A' && c <= 'Z' ) || (c >='a' && c <= 'z')) {
                    this.chars.add(new Character(c));
                }
            }
        }
        
//        System.out.println(this.chars);
//        System.out.println(this.sums);
    }
    
    public char next() {
        char ret = ' ';
        if (this.cidx < this.sums.size() && this.idx < this.sums.get(this.cidx)) {
            ret = this.chars.get(this.cidx);
        } else {
            this.cidx ++;
            if (this.cidx < this.sums.size()) {
                ret = this.chars.get(this.cidx);
            }
        }
        this.idx ++;
//        System.out.println(ret);
        return ret;
    }
    
    public boolean hasNext() {
        boolean over = false;
        over = (this.cidx >= this.chars.size()) || (this.cidx == this.chars.size() - 1 && this.idx >= this.sums.get(this.cidx));
        return !over;
    }
    
    public static void main(String [] args) {
        //L1e2t1C1o1d1e1
        StringIterator iterator = new StringIterator("");
        
        iterator.next(); // return 'L'
        iterator.next(); // return 'e'
        iterator.next(); // return 'e'
        iterator.next(); // return 't'
        iterator.next(); // return 'C'
        iterator.next(); // return 'o'
        iterator.next(); // return 'd'
        System.out.println(iterator.hasNext()); // return true
        iterator.next(); // return 'e'
        System.out.println(iterator.hasNext()); // return false
        iterator.next(); // return ' '
    }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

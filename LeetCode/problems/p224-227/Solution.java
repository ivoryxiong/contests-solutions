import java.util.*;
import java.util.function.*;;

class Solution {
	private List < Integer > tokenize(String s) {
		List < Integer > tokens = new ArrayList < Integer > ();
		int num = -1;
		String x = s + " ";
		Map < Character, Integer > op = new HashMap < Character, Integer > ();
		op.put(Character.valueOf('('), -1);
		op.put(Character.valueOf(')'), -2);
		op.put(Character.valueOf('+'), -3);
		op.put(Character.valueOf('-'), -4);
		op.put(Character.valueOf('*'), -5);
		op.put(Character.valueOf('/'), -6);

		for (int i = 0; i < x.length(); i++) {
			char c = x.charAt(i);
			if (c >= '0' && c <= '9') {
				if (num < 0) {
					num = c - '0';
				} else {
					num = num * 10 + (c - '0');
				}
			} else {
				if (num >= 0) {
					tokens.add(num);
					num = -1;
				}
				if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
					tokens.add(op.get(Character.valueOf(c)));
				}
			}
		}
		return tokens;
	}

	public int calculate(String s) {
		LinkedList < Integer > nums = new LinkedList < Integer > ();
		LinkedList < Integer > ops = new LinkedList < Integer > ();
		List < Integer > tokens = this.tokenize(s);
		for (Integer token: tokens) {
			if (token < 0) {
				if (token == -1) {
					ops.addLast(token);
				} else if (token == -2) {
					doCal(nums, ops, token, (Integer op1, Integer op2) -> (op1 == -1));
					ops.removeLast();
				} else {
					doCal(nums, ops, token, (Integer op1, Integer op2) -> ((op1 + 10) / 2 > (op2 + 10) / 2));
					ops.addLast(token);
				}
			} else {
				nums.add(Integer.valueOf(token));
			}
		}

		this.doCal(nums, ops, 0, (Integer op1, Integer op2) -> (false));
		return nums.get(0);
	}

	private void doCal(LinkedList < Integer > nums, LinkedList < Integer > ops, Integer curOp, BiPredicate<Integer, Integer> predicate) {
		while (ops.size() > 0) {
			int op = ops.getLast();
			if (predicate.test(op, curOp)) {
				break;
			}
			ops.removeLast();
			int n1 = nums.removeLast();
			int n2 = nums.removeLast();
			if (op == -6) {
				nums.addLast(n2 / n1);
			} else if (op == -5) {
				nums.addLast(n2 * n1);
			} else if (op == -4) {
				nums.addLast(n2 - n1);
			} else {
				nums.add(n2 + n1);
			}
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String content = "(((1+2)/1+10)/2+4)/5";
		int ans = sol.calculate(content);
		System.out.println(content + " = " + ans);
	}
}
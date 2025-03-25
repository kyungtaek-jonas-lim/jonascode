package solutions.java;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Stack;

/*
# Problem
	- `Link`: https://leetcode.com/problems/valid-parentheses/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Mar 25, 2025
	- `Answer`: isValid / isValidAdvanced
*/
public class ValidParentheses {
	public static void main(String[] args) {
		System.out.println(isValid("()")); // true
		System.out.println(isValid("()[]{}")); // true
		System.out.println(isValid("(]")); // false
		System.out.println(isValid("([])")); // true
		
		System.out.println("---");
		
		System.out.println(isValidAdvanced("()")); // true
		System.out.println(isValidAdvanced("()[]{}")); // true
		System.out.println(isValidAdvanced("(]")); // false
		System.out.println(isValidAdvanced("([])")); // true
	}
	
	/*
    # Option #1
    - O(N)
	 */
    public static boolean isValid(String s) {
    	Deque<Character> deque = new ArrayDeque<>();
    	Character polledChar = null;
        for (char c: s.toCharArray()) {
        	if (c == ')') {
        		polledChar = deque.pollLast();
        		if (polledChar == null || '(' != polledChar) return false;
        	} else if (c == '}') {
        		polledChar = deque.pollLast();
        		if (polledChar == null || '{' != polledChar) return false;
        	} else if (c == ']') {
        		polledChar = deque.pollLast();
        		if (polledChar == null || '[' != polledChar) return false;
        	} else {
        		deque.add(c);
        	}
        }
        if (deque.isEmpty()) return true;
        return false;
    }
    
    /*
    # Option #2
    - O(N)
    - A little bit more organized source code than Option #1
     */
    public static boolean isValidAdvanced(String s) {
    	Stack<Character> stack = new Stack<>();
    	Map<Character, Character> map = Map.of(
    				')', '(',
    				']', '[',
    				'}', '{'
    			);
    	for (char c: s.toCharArray()) {
    		if (map.containsKey(c)) {
    			char top = stack.isEmpty() ? '#' : stack.pop();
    			if (top != map.get(c)) return false;
    		} else {
    			stack.push(c);
    		}
    	}
    	
    	return stack.isEmpty();
    }
}

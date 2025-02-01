package solutions.java;

import java.util.HashSet;
import java.util.Set;

/**
 * @url https://leetcode.com/problems/contains-duplicate/
 * @author Kyungtaek Lim (Jonas)
 * @date Jan 30, 2025
 */
public class ContainsDuplicate {

	public static void main(String[] args) {
		System.out.println(containsDuplicate(new int[]{1, 2, 3, 1})); // true
        System.out.println(containsDuplicate(new int[]{1, 2, 3, 4})); // false
        System.out.println(containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2})); // true
	}

	/**
	 * @option 1
	 * @description Common way
	 * @timeComplexity O(n)
	 * @param nums
	 * @return
	 */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) return true;
            set.add(n);
        }
        return false;
    }
}

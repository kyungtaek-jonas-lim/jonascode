package solutions.java;

/*
# Problem
	- `Link`: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: May 2, 2025
	- `Answer`: twoSum
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int s = numbers[left] + numbers[right];
            if (s == target) return new int[] {left + 1, right + 1};
            else if (s < target) left++;
            else right--;
        }
        return null;
    }
}

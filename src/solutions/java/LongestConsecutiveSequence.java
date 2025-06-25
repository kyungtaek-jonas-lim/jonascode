package solutions.java;

import java.util.HashSet;
import java.util.Set;

/*
# Problem
	- `Link`: https://leetcode.com/problems/longest-consecutive-sequence/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 25
	- `Answer`: longestConsecutive
 */
public class LongestConsecutiveSequence {
	
	/*
    # Option #1
    - O(n)
	 */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int result = 0;
        for (int num: nums) set.add(num);
        for (int num: set) {
            if (!set.contains(num - 1)) {
                int temp = 1, search = num + 1;
                while (set.contains(search)) {
                    search++;
                    temp++;
                }
                result = Math.max(result, temp);
            }
        }
        return result;
    }
}

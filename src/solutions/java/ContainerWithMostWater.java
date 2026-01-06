package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/container-with-most-water/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 3, 2025
 	- `Answer`: maxArea / maxAreaSimple
*/

public class ContainerWithMostWater {
		
	// Not efficient (Time Limit Exceeded) (O(n^2))
    // public static int maxArea(int[] height) {
    //     int result = 0;
    //     int h = 0;
    //     int w = 0;
    //     int left = 0;
    //     int right = 0;
    //     for (int i = 0; i < height.length - 1; i++) {
    //         left = height[i];
    //         for (int j = i + 1; j < height.length; j++) {
    //             right = height[j];
    //             w = j - i;
    //             h = Math.min(left, right);
    //             result = Math.max(result, w * h);
    //         }
    //     }
    //     return result;
    // }

	/*
	 * Option #1 
	 * Common way
	 * O(n)
	 * We move the smaller pointer inward at each step because the area is limited by the smaller height, and moving it gives a chance to find a taller boundary for a larger area.
	 */
    public static int maxArea(int[] height) {
    	int left = 0;
    	int right = height.length - 1;
		int area = 0;
    	int result = 0;
    	
    	while (left < right) {
    		if (height[left] < height[right]) {
    			area = height[left] * (right - left);
    			left++;
    		} else {
    			area = height[right] * (right - left);
    			right--;
    		}
    		result = result < area ? area : result;
    	}
    	return result;
    }

	/*
	 * Option #2
	 * Simple way
	 * O(n)
	 */
    public static int maxAreaSimple(int[] height) {
        final int n = height.length;
        int l = 0, r = n - 1;
        int result = 0;

        while (l < r) {
            result = Math.max(result, (r - l) * Math.min(height[l], height[r]));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return result;
    }
	
	public static void main(String[] args) {
		System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
		System.out.println(maxArea(new int[] {1, 1}));
	}
}
package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 11, 2025
 	- `Answer`: maxProfit / maxProfitAdvanced
 */

public class BestTimetoBuyandSellStock {
	
	public static void main(String[] args) {
		
		int[] a = new int[] {7,1,5,3,6,4};
		System.out.println(maxProfitAdvanced(a));
		
	}

	/*
	 * Option #1 
	 * Common way
	 * O(n^2)
	 */
    // Time Limit Exceeded
	public static int maxProfit(int[] prices) {	
        int totalMax = 0;
        for (int i = 0 ; i < prices.length; i ++) {
            final int buy = prices[i];
            int max = 0;
            for (int j = i + 1 ; j < prices.length; j++) {
                if (buy < prices[j]) {
                    final int difference = prices[j] - buy;
                    max = difference > max ? difference : max;
                }
            }
            totalMax = max > totalMax ? max : totalMax;
        }
        return totalMax;
	}
	

	/*
	 * Option #2
	 * Advanced way
	 * O(n)
	 */
	public static int maxProfitAdvanced(int[] prices) {
        int result = 0;
        int min = (int) Math.pow(10, 4);
        for (int i = 0; i < prices.length; i++) {
        	if (min > prices[i]) min = prices[i];
        	else if (result < prices[i] - min) {
        		result = prices[i] - min;
        	}
        }
//        for (int price: prices) {
//            if (min > price) min = price;
//            else if (result < price - min) {
//                result = price - min;
//            }
//        }
        return result;
	}
}

package solutions.java;

import java.util.Scanner;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/sum-of-two-integers/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 4, 2025
 	- `Answer`: getSum / getSumAdvanced
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/reference/BitwiseOperators.md
 */

public class GetSum {
	public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        // Input the first and second numbers 
        System.out.print("Enter the first integer: ");
        int first = scanner.nextInt();
        System.out.print("Enter the second integer: ");
        int second = scanner.nextInt();
		
//        final int sum = getSum(first, second);
        final int sum = getSumAdvanced(first, second);        
		System.out.println("[RESULT] The sum is `" + sum + "`");
		
		scanner.close();
	}

	/*
	 * Option #1 
	 * Common way
	 * O(n)
	 */
    public static int getSum(int a, int b) {
    	int num = 0;
        for (int i = 0; i < a; i++) {
        	num++;
        }
        
        for (int i = 0; i < b; i++) {
        	num++;
        }
        return num;
    }
    
    /*
     * Option #2
     * Advanced way
     * O(1)
     * https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/explanation/GetSum.md
     */
    public static int getSumAdvanced(int a, int b) {
    	while (b != 0) {
    		// 1. Calculate carry (common bits that are set in both a and b)
    		int carry = a & b;
    		// 2. Perform XOR operation to add without carry 
    		a = a ^ b;
    		// Shift carry left by 1 so that it adds to the next higher bit
    		b = carry << 1;
    	}
    	return a;
    }

    // ===================================================
    // Minus: Extra Function
    // Instead of "*"
    public static int getDifference(int a, int b) {
        // Continue until there is no borrow left
        while (b != 0) {
            // Calculate the borrow by using NOT on a and AND with b
            // This identifies which bits in a need to be borrowed
            int borrow = (~a) & b;  // Borrow bits where a is 0 and b is 1

            // XOR is used to compute the difference without borrow
            // It works by subtracting where there's no need to borrow
            a = a ^ b;

            // Shift the borrow left by 1 to add it to the next higher bit
            b = borrow << 1;  // Moving the borrow to the next bit
        }
        return a;  // Return the final result after handling all borrows
    }

    // ===================================================
    // Production: Extra Function
    // Instead of "*"
    public int getProduct(int a, int b) {
        int result = 0;

        // Perform multiplication using bitwise shift and add
        while (b != 0) {
            // If the least significant bit of b is 1, add a to the result
            if ((b & 1) == 1) {
                result = result + a;
            }

            // Shift a to the left (multiply by 2) and b to the right (divide by 2)
            a = a << 1;  // a * 2
            b = b >> 1;  // b / 2
        }
        return result;
    }
    
    // ===================================================
    // Division: Extra Function
    // Instead of "/" and "%"
    public int getQuotient(int a, int b) {
        // Handle edge case when b is 0
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }

        int result = 0;
        int sign = (a < 0) ^ (b < 0) ? -1 : 1;  // Determine the sign of the result

        a = Math.abs(a);  // Work with absolute values of a and b
        b = Math.abs(b);

        // Perform division using bitwise shift
        while (a >= b) {
            int temp = b, multiple = 1;
            
            // Left shift the divisor until it's larger than the dividend
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;  // Track how many times we shifted
            }

            // Subtract the shifted divisor from the dividend and add the multiple
            a -= temp;
            result += multiple;
        }

        return result * sign;  // Apply the sign to the result
    }

}

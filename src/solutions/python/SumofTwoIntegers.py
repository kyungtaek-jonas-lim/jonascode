
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/sum-of-two-integers/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 26, 2025
 	- `Answer`: getSum
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/reference/BitwiseOperators.md
'''
class Solution:

    '''
    Sum
    - O(1)
    - https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/explanation/GetSum.md
    '''
    def getSum(self, a: int, b: int) -> int:
        while b != 0:
            carry = a & b
            a = a ^ b
            b = carry << 1
        return a
    
    '''
    Minus
    '''
    def get_difference(self, a: int, b: int) -> int:
        MAX = 0xFFFFFFFF
        MASK = 0x7FFFFFFF  # For handling negative numbers

        # Continue until there is no borrow left
        while b != 0:
            # Identify borrow bits: where a has 0 and b has 1
            borrow = (~a) & b

            # XOR computes difference without borrow
            a = a ^ b

            # Shift borrow left by 1 to apply it to the next bit position
            b = borrow << 1

            # Simulate 32-bit integer
            a &= MAX
            b &= MAX

        # If a is negative in 32-bit, convert it to Python negative
        return a if a <= MASK else ~(a ^ MAX)


    '''
    Production
    '''
    def get_product(self, a: int, b: int) -> int:
        result = 0

        # Repeat while b is not zero
        while b != 0:
            # If the least significant bit of b is 1, add 'a' to result
            if b & 1:
                result += a

            # Left shift 'a' (equivalent to a * 2), right shift 'b' (equivalent to b // 2)
            a <<= 1
            b >>= 1

        return result


    '''
    Division
    '''
    def get_quotient(self, a: int, b: int) -> int:
        if b == 0:
            raise ZeroDivisionError("Division by zero")

        result = 0
        # Determine the sign of the result
        sign = -1 if (a < 0) ^ (b < 0) else 1

        a = abs(a)
        b = abs(b)

        # Subtract shifted divisor from dividend until dividend is smaller
        while a >= b:
            temp = b
            multiple = 1

            # Shift divisor left until it's just below or equal to 'a'
            while a >= (temp << 1):
                temp <<= 1
                multiple <<= 1

            a -= temp
            result += multiple

        return sign * result


if __name__ == '__main__':
    sol = Solution()
    print(sol.getSum(1, 2)) # 3
    print(sol.getSum(2, 3)) # 5

    print("---")
    print(sol.get_difference(5, 3))     # 2
    print(sol.get_difference(1, 3))     # -2
    print(sol.get_difference(100, 200)) # -100
    print(sol.get_difference(7, 0))     # 7
    print(sol.get_difference(0, 7))     # -7
    print(sol.get_difference(-3, -1))   # -2


    print("---")
    print(sol.get_product(1, 2)) # 2
    print(sol.get_product(2, 3)) # 6

    print("---")
    print(sol.get_quotient(1, 2)) # 0
    print(sol.get_quotient(2, 3)) # 0
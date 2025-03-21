'''
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-common-subsequence/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 21, 2025
 	- `Answer`: longestCommonSubsequence
 '''
class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        # Store lengths of both input strings for reuse 
        text1_length = len(text1)
        text2_length = len(text2)

        # Ensure text1 is the longer string for space optimization
        # Why? We'll create a 1D DP array of size len(text2) + 1
        # So it's best to make the shorter string define the DP width (memory efficient)
        if text1_length < text2_length:
            return self.longestCommonSubsequence(text1=text2, text2=text1)

        # Initialize two 1D arrays to simulate two rows of the DP table:
        # 'prev' stores the results from the previous row (i - 1)
        # 'curr' stores the current row (i)
        prev = [0] * (text2_length + 1)
        curr = [0] * (text2_length + 1)

        # Loop through text1 and text2 using 1-based indexing logic
        # i and j start from 1, so we subtract 1 when accessing string characters
        for i in range(1, text1_length + 1):
            for j in range(1, text2_length + 1):
                if text1[i - 1] == text2[j - 1]:
                    # Characters match: extend LCS by 1 from diagonal value
                    curr[j] = prev[j - 1] + 1
                else:
                    # Characters don't match:
                    # Take the maximum between:
                    # - prev[j]     -> Excluding current char from text1
                    # - curr[j - 1] -> Excluding current char from text2
                    curr[j] = max(prev[j], curr[j - 1])

            # Swap current and previous arrays
            # Now 'prev' becomes the latest computed row,
            # and 'curr' gets reused for the next iteration
            prev, curr = curr, prev

        # After the last swap, 'prev' contains the final LCS lengths
        # The last element holds the total length of the longest common subsequence
        return prev[text2_length]

if __name__ == "__main__":
    sol = Solution()
    print(sol.longestCommonSubsequence("abcde", "ace")); # 3
    print(sol.longestCommonSubsequence(-"abc", "abc")); # 3
    print(sol.longestCommonSubsequence("abc", "def")); # 0
    print(sol.longestCommonSubsequence("ezupkr", "ubmrapg")); # 2
    print(sol.longestCommonSubsequence("bsbininm", "jmjkbkjkv")); # 1
    print(sol.longestCommonSubsequence("oxcpqrsvwf", "shmtulqrypy")); # 2
    print(sol.longestCommonSubsequence("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq")); # 6 ("mhziwb")
    print(sol.longestCommonSubsequence("abc", "abbbbbc")); # 3
    print(sol.longestCommonSubsequence("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); # 210
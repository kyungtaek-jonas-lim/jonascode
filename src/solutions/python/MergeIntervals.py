from typing import List
'''
# Problem
	- `Link`: https://leetcode.com/problems/merge-intervals/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Mar 26, 2025
	- `Answer`: merge / mergeAdvanced
'''

class Solution:

    '''
    # Option #1
    - O(n log n)
    '''
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        
        # Sort
        intervals.sort()
        
        # Remove overlapped intervals
        result = [intervals[0]]
        current = 0
        for i in range(1, len(intervals)):
            if result[current][0] <= intervals[i][1] and result[current][1] >= intervals[i][0]:
                result[current][1] = max(result[current][1], intervals[i][1])
            else:
                result.append(intervals[i])
                current += 1
        return result
    
    '''
    # Option #2
    - O(n log n)
    '''
    def mergeAdvanced(self, intervals: List[List[int]]) -> List[List[int]]:

        # Sort intervals based on the start time
        intervals.sort(key=lambda x: x[0])

        result = []
        for interval in intervals:
            # If merged is empty or no overlap, just add interval
            if not result or result[-1][1] < interval[0]:
                result.append(interval)
            else:
                result[-1][1] = max(result[-1][1], interval[1])
        
        return result

    
if __name__ == '__main__':
    sol = Solution()
    print(sol.merge([[1,3],[2,6],[8,10],[15,18]])) # [[1,6],[8,10],[15,18]]
    print(sol.merge([[1,4],[4,5]])) # [[1,5]]
    print(sol.merge([[1,4],[2,3]])) # [[1,4]]
    print(sol.merge([[2,3],[4,5],[6,7],[8,9],[1,10]])) # [[1,10]]
    print(sol.merge([[2,3],[2,2],[3,3],[1,3],[5,7],[2,2],[4,6]])) # [[1,3],[4,7]]
    
    print("---")
    print(sol.mergeAdvanced([[1,3],[2,6],[8,10],[15,18]])) # [[1,6],[8,10],[15,18]]
    print(sol.mergeAdvanced([[1,4],[4,5]])) # [[1,5]]
    print(sol.mergeAdvanced([[1,4],[2,3]])) # [[1,4]]
    print(sol.mergeAdvanced([[2,3],[4,5],[6,7],[8,9],[1,10]])) # [[1,10]]
    print(sol.mergeAdvanced([[2,3],[2,2],[3,3],[1,3],[5,7],[2,2],[4,6]])) # [[1,3],[4,7]]
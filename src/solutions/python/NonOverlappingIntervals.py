from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/non-overlapping-intervals/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 7, 2025
 	- `Answer`: eraseOverlapIntervals / eraseOverlapIntervalsAdvanced
'''

class Solution:

    '''
    # Option #1
    - O (n log n)
    '''
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        
        # Sort the intervals in ascending order based on the start value
        intervals.sort(key=lambda x: x[0])

        # Initialize with the end value of the first interval
        max_value = intervals[0][1]

        # Overlapping interval Count
        result = 0
        for start, end in intervals[1:]:
            
            # If it's overlapped, count up and reset the max variable to the min value
            if start < max_value:
                result += 1
                max_value = min(max_value, end)
            else: # If it's not overlapped, reset the max variable to the max value.
                max_value = end

        return result
    

    '''
    # Option #2
    - O(n log n)
    '''
    def eraseOverlapIntervalsAdvanced(self, intervals: List[List[int]]) -> int:

        # Sort intervals by their end time
        intervals.sort(key=lambda x: x[1])
        
        # Track the end of the last added interval
        end = float('-inf')
        count = 0  # Count of non-overlapping intervals

        for start, finish in intervals:
            if start >= end:
                # No overlap, include this interval
                end = finish
                count += 1
            # Else: overlap, so we skip this interval (i.e., remove it)

        # Number of removals = total intervals - non-overlapping intervals kept
        return len(intervals) - count

        
if __name__ == "__main__":
    sol = Solution()
    print(sol.eraseOverlapIntervals([[1,2],[2,3],[3,4],[1,3]])) # 1
    print(sol.eraseOverlapIntervals([[1,2],[1,2],[1,2]])) # 2
    print(sol.eraseOverlapIntervals([[1,2],[2,3]])) # 0
    print(sol.eraseOverlapIntervals([[0,2],[1,3],[2,4],[3,5],[4,6]])) # 2
    print(sol.eraseOverlapIntervals([[-52,31],[-73,-26],[82,97],[-65,-11],[-62,-49],[95,99],[58,95],[-31,49],[66,98],[-63,2],[30,47],[-40,-26]])) # 7

    
    print("---")
    
    print(sol.eraseOverlapIntervalsAdvanced([[1,2],[2,3],[3,4],[1,3]])) # 1
    print(sol.eraseOverlapIntervalsAdvanced([[1,2],[1,2],[1,2]])) # 2
    print(sol.eraseOverlapIntervalsAdvanced([[1,2],[2,3]])) # 0
    print(sol.eraseOverlapIntervalsAdvanced([[0,2],[1,3],[2,4],[3,5],[4,6]])) # 2
    print(sol.eraseOverlapIntervalsAdvanced([[-52,31],[-73,-26],[82,97],[-65,-11],[-62,-49],[95,99],[58,95],[-31,49],[66,98],[-63,2],[30,47],[-40,-26]])) # 7
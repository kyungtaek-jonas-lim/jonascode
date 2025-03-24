from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/non-overlapping-intervals/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 24, 2025
 	- `Answer`: eraseOverlapIntervals / eraseOverlapIntervalsAdvanced
'''

class Solution:

    '''
    # Option #1
    - O (n log n)
    '''
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:

        # Sort in Ascending
        intervals.sort()

        # Initialize First range as a Standard range
        start = intervals[0][0]
        end = intervals[0][1]
        
        # Start from 1
        result = 0
        for i in range(1, len(intervals)):

            # ------------------
            # Overlapped
            if intervals[i][0] < end and intervals[i][1] > start:
                
                # If it's a overlapped range, it counts first
                result += 1

                # If the start points are the same, set the minimum end point as the standard end point
                if intervals[i][0] == start:
                    end = min(end, intervals[i][1])
                
                # If the end points are the same, pass
                elif intervals[i][1] == end:
                    pass

                # If one range includes the other range, the short range become standard range.
                elif start < intervals[i][0] and intervals[i][1] < end:
                    end = intervals[i][1]
                    start = intervals[i][0]
                            
            # ------------------
            # Non-Overlapped
            else:
                # If it's non-overlapped range, update the standard range.
                end = intervals[i][1]
                start = intervals[i][0]
                
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
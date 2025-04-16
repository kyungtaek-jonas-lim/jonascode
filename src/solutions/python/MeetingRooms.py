from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/meeting-rooms/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 16, 2025
	- `Answer`: canAttendMeetings
'''
# Definition for an interval.
class Interval:
    def __init__(self, start=0, end=0):
        self.start = start
        self.end = end

class Solution:

    '''
    # Option #1
    - Sort (O(n log n)) + Iterate (O(n))
    - O(n log n)
    '''
    def canAttendMeetings(self, intervals: List[Interval]) -> bool:
        if not intervals:
            return True
        
        # Sort
        intervals.sort(key=lambda x: x.start)
        
        end = intervals[0].end
        
        for interval in intervals[1:]:
            if end > interval.start: # Next start before it ends
                return False
            end = interval.end
        
        return True
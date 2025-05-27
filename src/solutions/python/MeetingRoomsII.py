from typing import List
import heapq

'''
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/meeting-rooms-ii/
        - `LintCode`: https://www.lintcode.com/problem/919/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 16, 2025
	- `Answer`: minMeetingRooms / minMeetingRoomsLineSweep / minMeetingRoomsHeap
'''
# Definition for an interval.
class Interval:
    def __init__(self, start=0, end=0):
        self.start = start
        self.end = end

class Solution:

    """
    # Option #1
    - O(n^2)
    """
    def minMeetingRooms(self, intervals: List[Interval]) -> int:
        intervals_length = len(intervals)
        if intervals_length <= 1:
            return intervals_length

        # Sort
        intervals.sort(key=lambda x: (x.start, x.end))

        result = 1
        active_meetings = []

        for i in range(1, intervals_length):
            interval = intervals[i]

            current_desired_cnt = 1
            j = 0
            while j < len(active_meetings):
                pre = active_meetings[j]
                if pre.end > interval.start:
                    current_desired_cnt += 1
                    j += 1
                else:
                    active_meetings.pop(j)
            result = max(result, current_desired_cnt)
            active_meetings.append(interval)

        return result

    """
    # Option #2
    - Line Sweep
    - O(n log n)
    - ref: https://www.youtube.com/watch?v=FdzJmTCVyJU
    """
    def minMeetingRoomsLineSweep(self, intervals: List[Interval]) -> int:
        """
        * Collects starts and ends separately and sort them in ascending.
        * If the start[i] < end[j], i++ and currentMeetingCnt++
        * Else, j++ and  currentMeetingCnt--
        """

        intervals_length = len(intervals)
        if intervals_length <= 1:
            return intervals_length

        starts = [interval.start for interval in intervals]
        ends = [interval.end for interval in intervals]

        starts.sort()
        ends.sort()

        starts_index = 0
        ends_index = 0
        result = 0
        current_desire_meeting_rooms = 0

        while starts_index < intervals_length:
            if starts[starts_index] < ends[ends_index]:
                current_desire_meeting_rooms += 1
                starts_index += 1
                result = max(result, current_desire_meeting_rooms)
            else:
                ends_index += 1
                current_desire_meeting_rooms -= 1

        return result

    """
    # Option #3
    - Heap
    - O(n log n)
    """
    def minMeetingRoomsHeap(self, intervals: List[Interval]) -> int:
        intervals_length = len(intervals)
        if intervals_length <= 1:
            return intervals_length

        # Sort with starts
        intervals.sort(key=lambda x: x.start)

        # To-be-sorted with ends
        result = 1
        heap = []
        heapq.heappush(heap, intervals[0].end)

        for i in range(1, intervals_length):
            interval = intervals[i]

            # Dismiss previous meetings
            while heap and heap[0] <= interval.start:
                heapq.heappop(heap)
            heapq.heappush(heap, interval.end)
            result = max(result, len(heap))

        return result

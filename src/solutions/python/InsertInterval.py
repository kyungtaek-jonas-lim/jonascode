import sys
from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/insert-interval/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 17, 2025 (insert)
 	- `Answer`: insert / insertAdvanced (TODO)
'''

class Solution:

    '''
	 * Option #1
    '''
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:

        # Edge case #1: intervals has no items
        if len(intervals) == 0:
            return [newInterval]
        
        my_list = list()

        # Edge case #2: newInterval is smaller than the smallest item of intervals
        if newInterval[1] < intervals[0][0]:
            my_list.append(newInterval)
            for interval in intervals:
                my_list.append(interval)
            return my_list
        
        # Edge case #3: newInterval is bigger than the biggest item of intervals
        if newInterval[0] > intervals[-1][1]:
            for interval in intervals:
                my_list.append(interval)
            my_list.append(newInterval)
            return my_list

        is_appended = False
        waitInterval = [sys.maxsize, -1]
        for interval in intervals:
            # Overlapped
            if newInterval[1] >= interval[0] and newInterval[0] <= interval[1]:
                waitInterval[0] = min(waitInterval[0], min(interval[0], newInterval[0]))
                waitInterval[1] = max(waitInterval[1], max(interval[1], newInterval[1]))
            else:
                if not is_appended and waitInterval[0] != sys.maxsize:
                    my_list.append(waitInterval)
                    is_appended = True
                elif not is_appended and newInterval[1] < interval[0]:
                    my_list.append(newInterval)
                    is_appended = True
                my_list.append(interval)
        
        # Edge case #4: The overlapped item is the last item of intervals
        if not is_appended:
            my_list.append(waitInterval)
        return my_list



    '''
	 * Option #2
    '''
    def insertAdvanced(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        # TODO
        pass


if __name__ == '__main__':
    sol = Solution()
    print(sol.insert(intervals=[[1,3],[6,9]], newInterval=[2,5]))
    print(sol.insert(intervals=[[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval=[4,8]))
    print(sol.insert(intervals=[[1,5]], newInterval=[6,8]))
    print(sol.insert(intervals=[[1,5]], newInterval=[2,3]))
    print(sol.insert(intervals=[[2,4],[5,7],[8,10],[11,13]], newInterval=[3,6]))
    print(sol.insert(intervals=[[3,5],[12,15]], newInterval=[6,6]))
    print(sol.insert(intervals=[[1,3],[6,9]], newInterval=[2,5]))
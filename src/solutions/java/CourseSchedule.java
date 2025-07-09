package solutions.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
# Problem
	- `Link`: https://leetcode.com/problems/course-schedule/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 25
	- `Answer`: canFinish
 */
public class CourseSchedule {
	
	/*
	# Option #1
	- Recursive DFS
	- O (n + p) (n = numCourses, p = the number of prerequisites)
	 */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] prerequisite: prerequisites) {
            map.get(prerequisite[0]).add(prerequisite[1]);
        }

        Map<Integer, Boolean> visited = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(map, i, visited)) return false;
        }

        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> map, int curr, Map<Integer, Boolean> visited) {
        
    	if (visited.containsKey(curr)) return visited.get(curr);
        visited.put(curr, false);
        
        List<Integer> pres = map.get(curr);
        for (int i: pres) {
            if (!dfs(map, i, visited)) return false;
        }
        
        visited.put(curr, true);
        
        return true;
    }
}

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

        for (int i = 0; i < numCourses; i++) {
            Set<Integer> set = new HashSet<>();
            if (!dfs(map, i, set)) return false;
        }

        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> map, int curr, Set<Integer> set) {
        if (map.get(curr) == null) return true;
        if (set.contains(curr)) return false;
        set.add(curr);
        for (int i: map.get(curr)) {
            if (!dfs(map, i, set)) return false;
        }
        set.remove(curr);
        map.remove(curr);
        return true;
    }
}

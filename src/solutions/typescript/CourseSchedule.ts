/*
# Problem
	- `Link`: https://leetcode.com/problems/course-schedule/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 9, 2025
	- `Answer`: canFinish
*/

/*
# Option #1
- O (n + p) (n = numCourses, p = the number of prerequisites)
*/
function canFinish(numCourses: number, prerequisites: number[][]): boolean {
    const map = new Map<number, Set<number>>();
    for (let i = 0; i < numCourses; i++) {
        map.set(i, new Set<number>());
    }

    const n = prerequisites.length;
    for (const prerequisite of prerequisites) {
        map.get(prerequisite[0])!.add(prerequisite[1]);
    }

    const visited = new Map<number, boolean>();
    for (let i = 0; i < numCourses; i++) {
        if (!dfs(map, i, visited)) return false;
    }

    return true;
};

function dfs(map: Map<number, Set<number>>, curr: number, visited: Map<number, boolean>): boolean {
    if (visited.has(curr)) return visited.get(curr)!;
    visited.set(curr, false);

    const set = map.get(curr)!;
    
    for (const p of set) {
        if (!dfs(map, p, visited)) return false;
    }
    visited.set(curr, true);

    return true;
}
package solutions.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
# Problem
	- `Link`: https://leetcode.com/problems/clone-graph/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 25
	- `Answer`: cloneGraph
 */

public class CloneGraph {
	
	class Node {
		public int val;
		public List<Node> neighbors;
		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}
		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}
		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}
	
	
	/*
	# Option #1
	- Recursive DFS
	- O(N + E) (N == the number of Nodes, E == the number of Edges)
	 */
	public Node cloneGraphDfs(Node node) {
        Map<Integer, Node> map = new HashMap<>();
        return dfs(node, map);
	}

	private Node dfs(Node node, Map<Integer, Node> map) {
		if (node == null) return null;
		if (map.containsKey(node.val)) return map.get(node.val);
		
		Node newNode = new Node(node.val);
		map.put(node.val, newNode);
		for (Node neighbor: node.neighbors) {
			newNode.neighbors.add(dfs(neighbor, map));
		}
		return newNode;
	}
}

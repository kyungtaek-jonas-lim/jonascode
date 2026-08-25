from typing import List, Optional
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 3, 2025
	- `Answer`: (serializeBfs, deserializeBfs) / (serializeBfsDifferent, deserializeBfsDifferent) / (serializeDfsRecursive, deserializeDfsRecursive) / (serializeDfsBetter, deserializeDfsBetter)
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Codec:
    
    '''
    # Option #1
    - BFS
    - O(n)
    '''
    def serializeBfs(self, root: TreeNode) -> str:
        
        result: List[str] = []

        deque = collections.deque()
        deque.append(root)

        while deque:
            node: TreeNode = deque.popleft()
            if not node:
                result.append("N")
                continue

            result.append(str(node.val))
            deque.append(node.left)
            deque.append(node.right)

        return ",".join(result)
    

    def deserializeBfs(self, data: str) -> TreeNode:
        
        list: List[str] = data.split(",")
        if list[0] == "N":
            return None
        
        deque = collections.deque()
        root = TreeNode(int(list[0]))
        deque.append(root)
        i: int = 1

        while deque:
            node: TreeNode = deque.popleft()
            
            if list[i] != "N":
                node.left = TreeNode(int(list[i]))
                deque.append(node.left)
            i += 1

            if list[i] != "N":
                node.right = TreeNode(int(list[i]))
                deque.append(node.right)
            i += 1
        
        return root

    
    '''
    # Option #2
    - BFS Different
    - O(n)
    - August 25, 2026
    '''
    def serializeBfsDifferent(self, root: Optional[TreeNode]) -> str:
        if not root:
            return "null"
        
        queue = collections.deque([root])
        result: List[str] = []
        while queue:
            n = len(queue)
            for i in range(n):
                node = queue.popleft()
                if not node:
                    result.append("null")
                else:
                    result.append(str(node.val))
                    queue.append(node.left)
                    queue.append(node.right)
        return ",".join(result)


    def deserializeBfsDifferent(self, data: str) -> Optional[TreeNode]:
        if data == "null":
            return None

        strings: List[str] = data.split(",")
        root: Optional[TreeNode] = TreeNode(int(strings[0]))
        queue = [root]
        head = 0
        
        for i in range(1, len(strings)):
            node: Optional[TreeNode] = None
            if strings[i] != "null":
                node = TreeNode(int(strings[i]))
                queue.append(node)
            
            if i % 2 == 1:
                queue[head].left = node
            else:
                queue[head].right = node
                head += 1
        
        return root
            
    
    '''
    # Option #3
    - DFS Recursive
    - O(n)
    '''
    def serializeDfsRecursive(self, root: TreeNode) -> str:
        result: List[str] = []
        
        def dfs(node: TreeNode):
            if not node:
                result.append("N")
                return
            
            result.append(str(node.val))
            dfs(node.left)
            dfs(node.right)
        
        dfs(root)
        return ",".join(result)

    def deserializeDfsRecursive(self, data: str) -> TreeNode:
        list: List[str] = data.split(",")
        self.i = 0
        
        def dfs() -> TreeNode:
            if list[self.i] == "N":
                self.i += 1
                return None
            
            node = TreeNode(int(list[self.i]))
            self.i += 1

            node.left = dfs()
            node.right = dfs()
            return node

        return dfs()
            

    
    '''
    # Option #4
    - DFS Recursive Better
    - O(n)
    - August 25, 2026
    '''
    def serializeDfsBetter(self, root: Optional[TreeNode]) -> str:
        if not root:
            return "null"
        left, right = self.serializeDfsBetter(root.left), self.serializeDfsBetter(root.right)
        return f"{root.val},{left},{right}"
        

    def deserializeDfsBetter(self, data: str) -> Optional[TreeNode]:
        if data == "null":
            return None
        strings = data.split(",")
        i = [0]

        def dfs() -> Optional[TreeNode]:
            if i[0] >= len(strings):
                return None

            if strings[i[0]] == "null":
                i[0] += 1
                return None

            node = TreeNode(int(strings[i[0]]))
            i[0] += 1
            node.left = dfs()
            node.right = dfs()
            return node

        return dfs()
        
        

# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))



if __name__ == "__main__":

    # [1,2,3,null,null,4,5]
    root: TreeNode = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.right.left = TreeNode(4)
    root.right.right = TreeNode(5)
    print(root.val)
    print(root.left.val)
    print(root.right.val)
    print(root.right.left.val)
    print(root.right.right.val)

    sol = Codec()
    string = sol.serialize(root=root)

    print(string)

    root = sol.deserialize(data=string)
    print(root.val)
    print(root.left.val)
    print(root.right.val)
    print(root.right.left.val)
    print(root.right.right.val)

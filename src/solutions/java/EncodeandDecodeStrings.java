package solutions.java;

import java.util.ArrayList;
import java.util.List;

/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/encode-and-decode-strings/
        - `LintCode`: https://www.lintcode.com/problem/659/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 20
	- `Answer`: encode / decode
 */
public class EncodeandDecodeStrings {
	
	/*
    # Option #1
    - Using Header (metadata in it)
	 */
    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();
        for (String s: strs) {
            encoded.append(s.length()).append('#').append(s);
        }
        return encoded.toString();
    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        
        int start = 0, n = str.length();
        while (start < n) {
            int sharp = str.indexOf('#', start);
            int count = Integer.valueOf(str.substring(start, sharp));
            start = sharp + 1 + count;
            result.add(str.substring(sharp + 1, start));
        }
        return result;
    }
}

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
    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();
        for (String s: strs) {
        	encoded.append(s.length() + "#" + s);
        }
        return encoded.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        
    	List<String> decoded = new ArrayList<>();
    	int current = 0, delimeter_index = 0;
    	int sLength = s.length(), cLength = 0;
    	int start = 0;
    	while (current < sLength) {
    		delimeter_index = s.indexOf('#', current);
    		
    		// Last string
    		if (delimeter_index == -1) {
    			decoded.add(s.substring(current));
    			break;
    		}
    		
    		// The other string
    		cLength = Integer.parseInt(s.substring(current, delimeter_index));
    		start = delimeter_index + 1;
    		current = start + cLength;
    		decoded.add(s.substring(start, current));
    	}
    	return decoded;
    }
}

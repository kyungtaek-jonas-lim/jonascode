

/*
# Problem
	- `Link`: https://leetcode.com/problems/minimum-window-substring/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 2, 2025
	- `Answer`: minWindow / minWindowAdvanced
*/

/**
 * Option #1
 * - Time Complexity: O(n^2)
 */
function minWindow(s: string, t: string): string {
    
    let result: string = "";
    let n: number = s.length, m: number = t.length;

    // Edge Case
    if (n < m) return result;
    
    // Get counts for valid character
    const cnts = new Map<string, number>();
    let typeCnt: number = 0;
    for (const c of t) {
        if (!cnts.has(c)) {
            typeCnt++;
        }
        cnts.set(c, (cnts.get(c) ?? 0) + 1);
    }

    // Find the valid start index
    let startIndex: number = 0;
    for (; startIndex < n; startIndex++) {
        const c = s[startIndex];
        if (cnts.has(c)) break;
    }

    // Switch Window
    const currentCnts = new Map<string, number>();
    let currentStr: string = "";
    let currentTypeCnt: number = 0;
    for (const c of s.slice(startIndex)) {
        
        currentStr += c; // Expand the window
        if (cnts.has(c)) {

            // Mark
            currentCnts.set(c, (currentCnts.get(c) ?? 0) + 1);

            if (cnts.get(c) == currentCnts.get(c)) {
                currentTypeCnt++;
            }
            
            // If it meets the condition, narrow the substring down to the minimum length by moving the left index
            while (currentTypeCnt == typeCnt && currentStr.length > 0) {
                
                if (result.length > currentStr.length || result.length == 0) {
                    result = currentStr;
                }
                
                const c1 = currentStr[0];
                if (cnts.has(c1)) {
                    currentCnts.set(c1, currentCnts.get(c1)! - 1);
                    if (cnts.get(c1)! > currentCnts.get(c1)!) {
                        currentTypeCnt--;
                    }
                }
                currentStr = currentStr.slice(1);
            }

        }
    }

    return result;
};



/**
 * Option #2
 * - Time Complexity: O(n)
 * 
 */function minWindowAdvanced(s: string, t: string): string {
    
    let result: number[] = [-1, -1];
    let resultLength: number = Number.MAX_VALUE;
    let n: number = s.length, m: number = t.length;

    // Edge Case
    if (n < m) return "";
    
    // Get counts for valid character
    const cnts = new Map<string, number>();
    let typeCnt: number = 0;
    for (const c of t) {
        if (!cnts.has(c)) {
            typeCnt++;
        }
        cnts.set(c, (cnts.get(c) ?? 0) + 1);
    }

    // Find the valid start index
    let startIndex: number = 0;
    for (; startIndex < n; startIndex++) {
        const c = s[startIndex];
        if (cnts.has(c)) break;
    }

    // Switch Window
    const currentCnts = new Map<string, number>();
    let currentStr: string = "";
    let currentTypeCnt: number = 0;
    let left: number = 0;
    for (let i = startIndex; i < s.length; i++) {
        
        const c = s[i];
        if (cnts.has(c)) {
            
            // Mark
            currentCnts.set(c, (currentCnts.get(c) ?? 0) + 1);
            if (currentCnts.get(c)! === cnts.get(c)!) currentTypeCnt++;

            while (typeCnt === currentTypeCnt) {
                
                let currentLength = (i - left + 1);
                if (currentLength < resultLength) {
                    resultLength = currentLength;
                    result = [left, i];
                }

                const c1 = s[left];
                if (cnts.has(c1)) {
                    if (currentCnts.get(c1)! === cnts.get(c1)!) {
                        currentTypeCnt--;
                    }
                    currentCnts.set(c1, currentCnts.get(c1)! - 1);
                }

                left++;
            }
        }
    }
    return resultLength === Number.MAX_VALUE ? "" : s.slice(result[0], result[1] + 1);
};










// ==================================================================
// Python Solution Conversion
// https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/python/MinimumWindowSubstring.py


// /**
//  * Option #1
//  * - Time Complexity: O(n + m) (n: length of s, m: length of t)
//  */
// function minWindow(s: string, t: string): string {
//     // Edge Case
//     const len_s: number = s.length;
//     const len_t: number = t.length;
//     if (len_s === len_t) {
//         const sortedS = s.split("").sort().join("");
//         const sortedT = t.split("").sort().join("");
//         if (sortedS === sortedT) {
//             return s;
//         } else {
//             return "";
//         }
//     }

//     // Get Counts of Letters of t
//     const t_cnt: number[] = new Array(128).fill(0);
//     let total_required_cnt: number = 0;
//     for (const c of t) {
//         t_cnt[c.charCodeAt(0)] += 1;
//         total_required_cnt += 1;
//     }

//     // Compare Counts of Letters of s
//     let result: string = "";
//     let min_length: number = Infinity;
//     let left: number = -1;
//     let right: number = 0;
//     const s_cnt: number[] = new Array(128).fill(0); // Get Counts of Letters of s, which t has
//     const extra_cnt: number[] = new Array(128).fill(0); // Get Extra Counts to minimize the substring
//     let matched_cnt: number = 0;

//     for (const c of s) {
//         const index: number = c.charCodeAt(0);
//         if (t_cnt[index] > 0) {
//             // Initialize left
//             if (left < 0) {
//                 left = right;
//             }

//             // If the right amount of the character is already found
//             if (s_cnt[index] === t_cnt[index]) {
//                 extra_cnt[index] += 1;

//                 // Narrow them down
//                 while (true) {
//                     const temp_index: number = s[left].charCodeAt(0);
//                     // If t has s[left] but if there's no extra character, stop
//                     if (t_cnt[temp_index] > 0) {
//                         if (extra_cnt[temp_index] > 0) { // Remove the extra characters
//                             extra_cnt[temp_index] -= 1;
//                         } else {
//                             break;
//                         }
//                     }
//                     left += 1;
//                 }
//             } else {
//                 // For the character, it encountered not enough numbers yet
//                 s_cnt[index] += 1;
//                 matched_cnt += 1;
//             }

//             // Update result
//             if (total_required_cnt === matched_cnt) {
//                 if (min_length > right - left) {
//                     min_length = right - left;
//                     result = s.slice(left, right + 1);
//                 }
//             }
//         }
//         right += 1;
//     }
//     return result;
// }

// /**
//  * Option #2
//  * - Time Complexity: O(n + m) (n: length of s, m: length of t)
//  */
// function minWindowAdvanced(s: string, t: string): string {
//     // Edge Case #1: if the target string is shorter
//     if (t.length > s.length) {
//         return "";
//     }

//     // Edge Case #2: If the lengths are the same
//     if (t.length === s.length) {
//         if (t.split("").sort().join("") !== s.split("").sort().join("")) {
//             return "";
//         } else {
//             return s;
//         }
//     }

//     // Store the candidate characters in an array
//     const candidate_cnt: number[] = new Array(128).fill(0);
//     let candidate_total_character_cnt: number = 0;
//     for (const c of t) {
//         if (!candidate_cnt[c.charCodeAt(0)]) {
//             candidate_total_character_cnt += 1;
//         }
//         candidate_cnt[c.charCodeAt(0)] += 1;
//     }

//     // Get the minimum window substring
//     const queue_cnt: number[] = new Array(128).fill(0);
//     let queue_character_total_cnt: number = 0;
//     let start: number = -1;
//     let end: number = -1;
//     let left: number = 0;

//     for (let right = 0; right < s.length; right++) {
//         const c: string = s[right];

//         // If this is not one of the target characters, skip the turn
//         if (!candidate_cnt[c.charCodeAt(0)]) {
//             continue;
//         }

//         queue_cnt[c.charCodeAt(0)] += 1;

//         // If the count of the scanned target characters are the same count
//         if (queue_cnt[c.charCodeAt(0)] === candidate_cnt[c.charCodeAt(0)]) {
//             queue_character_total_cnt += 1;
//         }

//         // If all of the counts of the target characters meet the whole target string
//         // Narrow them down to minimize the substring
//         while (queue_character_total_cnt === candidate_total_character_cnt) {
//             // Check the minimum length
//             if (start === -1 || end - start > right - left) {
//                 start = left;
//                 end = right;
//             }

//             // Move to the left index to the right cause it's already checked
//             const c_temp: string = s[left];
//             if (queue_cnt[c_temp.charCodeAt(0)] > 0) {
//                 queue_cnt[c_temp.charCodeAt(0)] -= 1;

//                 if (queue_cnt[c_temp.charCodeAt(0)] < candidate_cnt[c_temp.charCodeAt(0)]) {
//                     queue_character_total_cnt -= 1;
//                 }
//             }

//             // Move the left index to the right and see if it's still required
//             left += 1;
//         }
//     }

//     return start === -1 ? "" : s.slice(start, end + 1);
// }


// /**
//  * Option #3
//  * - Time Complexity: O(n + m) (n: length of s, m: length of t)
//  */
// function minWindowBest(s: string, t: string): string {
//     if (!s || !t) {
//         return "";
//     }

//     // Count required characters
//     const t_count: Map<string, number> = new Map();
//     for (const c of t) {
//         t_count.set(c, (t_count.get(c) ?? 0) + 1);
//     }
//     const required: number = t_count.size;

//     // Sliding window pointers and tracking
//     let left: number = 0;
//     let formed: number = 0;
//     const window_counts: Map<string, number> = new Map();
//     let min_len: number = Infinity;
//     let min_window: [number, number] = [0, 0];

//     for (let right = 0; right < s.length; right++) {
//         const c: string = s[right];
//         // Count current character in window
//         window_counts.set(c, (window_counts.get(c) ?? 0) + 1);

//         // Check if current character's count matches t's requirement
//         if (t_count.has(c) && window_counts.get(c) === t_count.get(c)) {
//             formed += 1;
//         }

//         // Try to contract the window while it's valid
//         while (formed === required) {
//             if (right - left + 1 < min_len) {
//                 min_len = right - left + 1;
//                 min_window = [left, right];
//             }

//             // Shrink the window
//             const char_left: string = s[left];
//             window_counts.set(char_left, (window_counts.get(char_left) ?? 0) - 1);
//             if (t_count.has(char_left) && window_counts.get(char_left) < t_count.get(char_left)) {
//                 formed -= 1;
//             }
//             left += 1;
//         }
//     }

//     const [l, r] = min_window;
//     return min_len === Infinity ? "" : s.slice(l, r + 1);
// }
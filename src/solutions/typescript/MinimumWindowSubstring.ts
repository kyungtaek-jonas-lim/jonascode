

/*
# Problem
	- `Link`: https://leetcode.com/problems/minimum-window-substring/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 2, 2025
	- `Answer`: minWindow / minWindowAdvanced / minWindowSimpleAndBest
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
 * - Using index instead of actually slicing the string
 */
function minWindowAdvanced(s: string, t: string): string {

    // Edge Case
    let n: number = s.length, m: number = t.length;
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
    let result: number[] = [-1, -1];
    let resultLength: number = Number.MAX_VALUE;
    const currentCnts = new Map<string, number>();
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





/**
 * Option #3
 * - Time Complexity: O(n)
 * - Simple and Best
 */
function minWindowSimpleAndBest(s: string, t: string): string {
    
    const numbersOfCharacters: Map<string, number> = new Map();
    for (const c of t) {
        numbersOfCharacters.set(c, (numbersOfCharacters.get(c)?? 0) + 1);
    }

    const result: number[] = [-1, s.length + 1]
    let curr: number = 0;
    const goal: number = numbersOfCharacters.size;
    let left: number = 0, n: number = s.length;
    const numbersOfCurrent: Map<string, number> = new Map();

    for (let i = 0; i < n; i++) {
        const c = s[i];
        if (!numbersOfCharacters.has(c)) continue;

        numbersOfCurrent.set(c, (numbersOfCurrent.get(c) ?? 0) + 1);
        
        if (numbersOfCurrent.get(c) === numbersOfCharacters.get(c)) {
            curr++;
        }

        while (curr == goal) {
            if (i - left < result[1] - result[0]) {
                result[1] = i;
                result[0] = left;
            }

            const temp: string = s[left++];
            if (numbersOfCurrent.has(temp)) {
                numbersOfCurrent.set(temp, numbersOfCurrent.get(temp)! - 1);
                if (numbersOfCurrent.get(temp)! < numbersOfCharacters.get(temp)!) {
                    curr--;
                }
            }
        }
    }

    if (result[0] === -1) return "";
    return s.substring(result[0], result[1] + 1);
};
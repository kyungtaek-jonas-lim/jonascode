

/*
# Problem
	- `Link`: https://leetcode.com/problems/minimum-window-substring/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 2, 2025
	- `Answer`: minWindow / minWindowAdvanced / minWindowSimpleAndBest / minWindowAdditional / minWindowNew
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




/**
 * Option #4
 * - Time Complexity: O(n + m)
 * - Complicated
 * - Jan 2, 2026
 */
function minWindowAdditional(s: string, t: string): string {

    // Validation
    const n: number = s.length, m: number = t.length;
    if (n < m) return "";
    if (n === m) {
        if ((s.split('').sort((a, b) => a.localeCompare(b)).join('')) !== (t.split('').sort((a, b) => a.localeCompare(b)).join(''))) return "";
        return s;
    }
    
    // Initialization
    const validCharCnts: Map<string, number> = new Map();
    const currValidCharCnts: Map<string, number> = new Map();
    let totalValidCharCnts: number = 0;
    let currTotalValidCharCnts: number = 0;

    for (const t1 of t) {
        if (validCharCnts.has(t1)) {
            validCharCnts.set(t1, validCharCnts.get(t1)! + 1);
        } else {
            totalValidCharCnts++;
            validCharCnts.set(t1, 1);
            currValidCharCnts.set(t1, 0);
        }
    }

    // ----------------------
    // Count Start
    let result: string = s + t;
    let left: number = 0;

    // Set The first index
    for (let i = 0; i < n; i++) {
        const c: string = s.charAt(i);
        if (validCharCnts.has(c)) {
            left = i;
            break;
        }
    }

    // Search
    for (let i = left; i < n; i++) {

        const c: string = s.charAt(i);

        if (validCharCnts.has(c)) {
            const targetCnt: number = validCharCnts.get(c)!;
            currValidCharCnts.set(c, currValidCharCnts.get(c)! + 1);
            const currCnt: number = currValidCharCnts.get(c)!;

            if (targetCnt === currCnt) {
                currTotalValidCharCnts++;
            } else if (targetCnt < currCnt) {
                let tobeLeft: number = left;
                let tobeLeftChar: string = s.charAt(tobeLeft);

                while (!currValidCharCnts.has(tobeLeftChar) || (currValidCharCnts.get(tobeLeftChar)! > validCharCnts.get(tobeLeftChar)!)) {
                    if (currValidCharCnts.has(tobeLeftChar)) {
                        currValidCharCnts.set(tobeLeftChar, currValidCharCnts.get(tobeLeftChar)! - 1);
                    }
                    if (tobeLeft === i) break;
                    tobeLeftChar = s.charAt(++tobeLeft);
                }
                left = tobeLeft;
            }

            if (currTotalValidCharCnts === totalValidCharCnts) {
                if (result.length > (i - left + 1)) {
                    result = s.substring(left, i + 1);
                }
            }

        }
    }


    return result === s + t ? "" : result;
};



/**
 * Option #6
 * - Time Complexity: O(n + m)
 * - New
 * - July 15, 2026
 */
function minWindowNew(s: string, t: string): string {
    
    // 1. Edge Case
    const sLength: number = s.length, tLength: number = t.length;
    if (sLength === tLength) {
        if (s.split('').sort().join('') === t.split('').sort().join('')) return s;
        return "";
    }

    // 2. Check T
    const cntT: Array<number> = new Array(128).fill(0);
    let cntKindT: number = 0;
    for (let i = 0; i < tLength; i++) {
        const c: number = t.charCodeAt(i);
        if (cntT[c] === 0) {
            cntKindT++;
        }
        cntT[c]++;
    }

    // 3. Check S
    const cntS: Array<number> = new Array(128).fill(0);
    const exceededS: Array<boolean> = new Array(128).fill(false);
    let cntKindS: number = 0;
    let start: number = 0;
    let result: string = s;
    let found: boolean = false;

    for (let i = 0; i < sLength; i++) {
        if (cntT[s.charCodeAt(i)] !== 0) {
            start = i;
            break;
        }
    }

    function updateResult(x: number, y: number) {
        found = true;
        if (result.length > y - x + 1) {
            result = s.slice(x, y + 1);
        }
    }

    for (let i = start; i < sLength; i++) {
        const c: number = s.charCodeAt(i);
        if (cntT[c] != 0) {
            
            cntS[c]++;

            if (cntT[c] === cntS[c]) {

                cntKindS++;
                if (cntKindT === cntKindS) {
                    updateResult(start, i);
                }

            } else if (cntT[c] < cntS[c]) {

                exceededS[c] = true;

                for (let j = start; j < i; j++) {

                    const cTemp: number = s.charCodeAt(j);
                    if (cntT[cTemp] > 0) {

                        if (!exceededS[cTemp]) {
                            break;
                        } else {

                            cntS[cTemp]--;
                            if (cntT[cTemp] == cntS[cTemp]) exceededS[cTemp] = false;
                            
                            start = j + 1;
                            if (cntKindT === cntKindS) {
                                updateResult(start, i);
                            }
                        }

                    } else {
                        start = j + 1;
                        if (cntKindT === cntKindS) {
                            updateResult(start, i);
                        }
                    }
                }
            }
        }
    }

    return found ? result : "";
};
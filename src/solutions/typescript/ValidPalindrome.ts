
/**
# Problem
	- `Link`: https://leetcode.com/problems/valid-palindrome/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 13
	- `Answer`: isPalindrome / isPalindromeAdvanced
 */


/*
# Option #1
- O(n)
- Space complexity: O(n)
*/
function isPalindrome(s: string): boolean {
    
    const n: number = s.length;
    const lowerA: number = 'a'.charCodeAt(0), lowerZ: number = 'z'.charCodeAt(0);
    const upperA: number = 'A'.charCodeAt(0), upperZ: number = 'Z'.charCodeAt(0);
    const number0: number = '0'.charCodeAt(0), number9: number = '9'.charCodeAt(0);

    const palindrome: string[] = [];
    for (let i = 0; i < n; i++) {
        const charCode: number = s.charCodeAt(i);
        if (charCode >= lowerA && charCode <= lowerZ) {
            palindrome.push(s[i]);
        } else if (charCode >= upperA && charCode <= upperZ) {
            palindrome.push(s[i].toLowerCase());
        } else if (charCode >= number0 && charCode <= number9) {
            palindrome.push(s[i]);
        }
    }

    const m: number = palindrome.length;
    const end: number = Math.floor(m / 2);
    for (let i = 0; i < end; i++) {
        if (palindrome[i] !== palindrome[m - 1 - i]) return false;
    }
    return true;
};



/*
# Option #2
- O(n)
- Space complexity: O(1)
*/
function isPalindromeAdvanced(s: string): boolean {
    
    const n: number = s.length;
    s = s.toLowerCase();

    const numberStart: number = '0'.charCodeAt(0);
    const numberEnd: number = '9'.charCodeAt(0);
    const alphabetStart: number = 'a'.charCodeAt(0);
    const alphabetEnd: number = 'z'.charCodeAt(0);

    function isAlphanumeric(c: string): boolean {
        const ascii: number = c.charCodeAt(0)
        if ((ascii >= numberStart && ascii <= numberEnd)
            || (ascii >= alphabetStart && ascii <= alphabetEnd)) {
                return true;
        }
        return false;
    }

    let left: number = 0, right: number = n - 1;
    while (left < right) {
        if (!isAlphanumeric(s[left])) {
            left++;
            continue;
        }
        if (!isAlphanumeric(s[right])) {
            right--;
            continue;
        }
        
        if (s[left] !== s[right]) return false;
        left++;
        right--;
    }

    return true;
};
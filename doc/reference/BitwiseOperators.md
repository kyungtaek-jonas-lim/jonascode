
# Bitwise Operators

### 1. **`&` (AND)**:
   - **Behavior**: The result is `1` only if both bits are `1` at the same position.
   - **Example**:  
     `1010` (10)  
     `1100` (12)  
     `------------`  
     `1000` (8)  
   - **Explanation**: The result is `1` only when both bits are `1` in the same position. Otherwise, the result is `0`.

### 2. **`|` (OR)**:
   - **Behavior**: The result is `1` if at least one of the bits is `1` at the same position.
   - **Example**:  
     `1010` (10)  
     `1100` (12)  
     `------------`  
     `1110` (14)  
   - **Explanation**: The result is `1` if either of the bits is `1` at the same position. The result is `0` only if both bits are `0`.

### 3. **`^` (XOR)**:
   - **Behavior**: The result is `1` only if the bits are different at the same position.
   - **Example**:  
     `1010` (10)  
     `1100` (12)  
     `------------`  
     `0110` (6)  
   - **Explanation**: The result is `1` if the bits are different (i.e., one is `0` and the other is `1`). If the bits are the same, the result is `0`.

---

### 4. **`~` (NOT, bitwise negation)**:
   - **Behavior**: Inverts each bit, turning `0`s into `1`s and `1`s into `0`s.
   - **Example**:  
     `1010` (10) becomes `0101` (5) after negation.

### 5. **`<<` (Left Shift)**:
   - **Behavior**: Shifts all bits to the left by the specified number of positions, and fills the rightmost bits with `0`.
   - **Example**:  
     `0001` (1) shifted left by 2 positions becomes `0100` (4).
   - **Explanation**: Left shifting a number increases its value by a factor of `2` for each position shifted.

### 6. **`>>` (Right Shift)**:
   - **Behavior**: Shifts all bits to the right by the specified number of positions. The leftmost bits are filled with `0` for positive numbers or the sign bit (`1`) for negative numbers (sign extension).
   - **Example with a positive number**:  
     `0100` (4) shifted right by 2 positions becomes `0001` (1).  
     - **Explanation**: Right shifting a positive number by 2 positions results in division by 4 (or `2^2`).
   
   - **Example with a negative number**:  
     `1111 1100` (-4) shifted right by 2 positions becomes `1111 1111` (-1).  
     - **Explanation**: Right shifting a negative number fills the leftmost positions with `1` (sign extension), keeping it negative.

---

### Summary:

- **`&` (AND)**: 1 only when both bits are 1.
- **`|` (OR)**: 1 when at least one bit is 1.
- **`^` (XOR)**: 1 when the bits are different.
- **`~` (NOT)**: Inverts all bits.
- **`<<` (Left Shift)**: Shifts bits to the left, filling with `0`s.
- **`>>` (Right Shift)**: Shifts bits to the right, filling with `0` for positive numbers and `1` for negative numbers (sign extension).

These operators are often used in **low-level programming** and **bit manipulation** tasks.
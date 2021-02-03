package com.weizhi.prac.leetcodepp.alg3;

import java.util.Arrays;

/**
 * Design a stack which supports the following operations.
 * <p>
 * Implement the CustomStack class:
 * <p>
 * CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack or do nothing if the stack reached the maxSize.
 * void push(int x) Adds x to the top of the stack if the stack hasn't reached the maxSize.
 * int pop() Pops and returns the top of stack or -1 if the stack is empty.
 * void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements in the stack, just increment all the elements in the stack.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-a-stack-with-increment-operation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author liweizhi
 * @date 2021/2/3
 */
public class D3N1381DesignaStackWithIncrementOperation {


    static class CustomStack {

        private int[] elements;
        private int maxSize;
        private int topIndex;

        public CustomStack(int maxSize) {
            super();
            this.maxSize = maxSize;
            this.elements = new int[maxSize];
            this.topIndex = -1;
        }

        public void push(int x) {
            if (this.topIndex == this.maxSize - 1) {
                return;
            }
            this.elements[++this.topIndex] = x;
        }

        public int pop() {
            if (this.topIndex < 0) {
                return -1;
            }
            return this.elements[this.topIndex--];
        }

        public void increment(int k, int val) {
            for (int i = 0; i < Math.min(k, this.topIndex + 1); i++) {
                this.elements[i] += val;
            }
        }
    }

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack(3);
        customStack.push(1);
        System.out.println(Arrays.toString(customStack.elements));
        customStack.push(2);
        System.out.println(Arrays.toString(customStack.elements));
        customStack.push(3);
        System.out.println(Arrays.toString(customStack.elements));
        customStack.push(4);
        System.out.println(Arrays.toString(customStack.elements));

        customStack.increment(2, 100);
        System.out.println(Arrays.toString(customStack.elements));
        customStack.increment(5, 100);
        System.out.println(Arrays.toString(customStack.elements));

        System.out.println(customStack.pop());
        System.out.println(Arrays.toString(customStack.elements));
        System.out.println(customStack.pop());
        System.out.println(Arrays.toString(customStack.elements));
        System.out.println(customStack.pop());
        System.out.println(Arrays.toString(customStack.elements));
        System.out.println(customStack.pop());
        System.out.println(Arrays.toString(customStack.elements));
    }
}

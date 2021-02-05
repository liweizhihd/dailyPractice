package com.weizhi.prac.leetcodepp.alg3;

import java.util.LinkedList;

/**
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 * <p>
 * Implement the MyQueue class:
 * <p>
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 思路:
 * 两个栈,一个用来push,一个用来pop
 * push时直接压入pushStack
 * 当pop时,如果popStack为空,则将pushStack中的元素逐一弹入popStack,然后弹出popStack的栈顶元素
 *
 * @author liweizhi
 * @date 2021/2/5
 */
public class D5N232ImplementQueueusingStacks {

    static class MyQueue {

        LinkedList<Integer> pushStack = new LinkedList<>();
        LinkedList<Integer> popStack = new LinkedList<>();

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            pushStack.addFirst(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            transferElements();
            return popStack.removeFirst();
        }

        private void transferElements() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.addFirst(pushStack.removeFirst());
                }
            }
        }

        /**
         * Get the front element.
         */
        public int peek() {
            transferElements();
            return popStack.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return popStack.isEmpty() && pushStack.isEmpty();
        }
    }
}

package com.lagostout.interviewcake

import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle
import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

// https://www.interviewcake.com/question/java/bst-checker

class BinarySearchTreeChecker {

    static boolean isValid(BinaryTreeNode root) {
        Stack<Frame> stack = new LinkedList<>()
        boolean isValid = true
        stack.push([root, null, null] as Frame)
        while (!stack.isEmpty()) {
            Frame frame = stack.pop()
            BinaryTreeNode node = frame.node
            if (frame.min != null) {
                isValid = node.value > frame.min
            }
            if (isValid && frame.max != null) {
                isValid = node.value < frame.max
            }
            if (!isValid) break
            if (node.left != null) {
                stack.push([node.left, frame.min, node.value] as Frame)
            }
            if (node.right != null) {
                stack.push([node.right, node.value, frame.max] as Frame)
            }
        }
        isValid
    }

    private static class Frame {
        public BinaryTreeNode node
        public Integer min
        public Integer max
        Frame(node, min, max) {
            this.node = node
            this.min = min
            this.max = max
        }
        @Override
        String toString() {
            return new ReflectionToStringBuilder(
                    this, ToStringStyle.MULTI_LINE_STYLE).toString()
        }
    }

    public static class BinaryTreeNode {

        public int value
        public BinaryTreeNode left
        public BinaryTreeNode right

        public BinaryTreeNode(int value) {
            this.value = value
        }

        public BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue)
            return this.left
        }

        public BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue)
            return this.right
        }

        @Override
        String toString() {
            return new ReflectionToStringBuilder(
                    this, new MultilineRecursiveToStringStyle()).toString()
        }

    }
    
}

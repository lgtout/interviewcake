package com.lagostout.interviewcake

import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

// https://www.interviewcake.com/question/java/balanced-binary-tree

class BalancedBinaryTree {

    static boolean isBalanced(BinaryTreeNode binaryTreeNode) {
        LinkedList<UnexploredNode> unexploredNodes = new LinkedList<>()
        int minLeafDepth = Integer.MAX_VALUE
        int maxLeafDepth = Integer.MIN_VALUE
        boolean isBalanced = true
        boolean foundLeaf = false
        unexploredNodes.push(new UnexploredNode(binaryTreeNode, 0))
        while (!unexploredNodes.isEmpty()) {
            UnexploredNode unexploredNode = unexploredNodes.pop()
            int currentDepth = unexploredNode.level
            BinaryTreeNode node = unexploredNode.node
            if (node.right == null && node.left == null) {
                minLeafDepth = Math.min(minLeafDepth, currentDepth)
                maxLeafDepth = Math.max(maxLeafDepth, currentDepth)
                isBalanced = maxLeafDepth - minLeafDepth <= 1
                foundLeaf = true
            } else {
                int depth = currentDepth + 1
                if (node.right != null) {
                    unexploredNodes.push(new UnexploredNode(node.right, depth))
                }
                if (node.left != null) {
                    unexploredNodes.push(new UnexploredNode(node.left, depth))
                }
                if (foundLeaf)
                    isBalanced = depth - maxLeafDepth <= 1
            }
            if (!isBalanced) break
        }
        isBalanced
    }

    static class UnexploredNode {
        BinaryTreeNode node
        int level

        UnexploredNode(BinaryTreeNode node, int level) {
            this.node = node
            this.level = level
        }
    }

    static class BinaryTreeNode {

        int value
        BinaryTreeNode left
        BinaryTreeNode right

        BinaryTreeNode(int value) {
            this.value = value
        }

        BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue)
            return this.left
        }

        BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue)
            return this.right
        }

        @Override
        String toString() {
            return new ReflectionToStringBuilder(
                    this, ToStringStyle.SHORT_PREFIX_STYLE).toString()
        }
    }

}

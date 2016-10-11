package com.lagostout.interviewcake

import spock.lang.Specification
import com.lagostout.interviewcake.BalancedBinaryTree.BinaryTreeNode
import spock.lang.Unroll

class BalancedBinaryTreeSpec extends Specification {

    @Unroll('#node #expected')
    def 'is balanced'(BinaryTreeNode node, boolean expected) {
        expect:
        BalancedBinaryTree.isBalanced(node) == expected
        where:
        [node, expected] << [
                [singleNodeBinaryTree(), true],
                [unbalancedBinaryTree1(), false],
                [exactlyBalancedBinaryTree1(), true],
                [inexactlyBalancedBinaryTree1(), true]
        ]
    }

    BinaryTreeNode singleNodeBinaryTree() {
        new BinaryTreeNode(1)
    }

    BinaryTreeNode unbalancedBinaryTree1() {
        BinaryTreeNode node = new BinaryTreeNode(1)
        node.insertLeft(2).insertRight(3).insertRight(4)
        node.insertRight(5)
        node
    }

    BinaryTreeNode unbalancedBinaryTree2() {
        BinaryTreeNode node = new BinaryTreeNode(1)
        int value = 1

        node.insertLeft(value++)
        node.left.with {
            it.insertLeft(value++)
            it.insertRight(value++)
        }
        node.insertRight(value++)
        node.right.with {
            it.insertLeft(value++)
            it.insertRight(value++)
        }

        node.right.insertLeft(value++)
        node.right.left.with {
            it.insertLeft(value++)
            it.insertRight(value++)
        }
        node.right.insertRight(value++)
        node.right.right.with {
            it.insertLeft(value++)
            it.insertRight(value++)
        }

        node.left.insertLeft(value++)
        node.left.left.with {
            it.insertLeft(value++)
            it.insertRight(value++)
        }
        node.left.insertRight(value++)
        node.left.right.with {
            it.insertLeft(value++)
            it.insertRight(value++)
        }
    }

    BinaryTreeNode exactlyBalancedBinaryTree1() {
        BinaryTreeNode node = new BinaryTreeNode(1)
        node.insertLeft(2)
        node.insertRight(3)
        with (node.left) {
            it.insertLeft(4)
            it.insertRight(5)
        }
        with (node.right) {
            it.insertLeft(6)
            it.insertRight(7)
        }
        node
    }

    BinaryTreeNode inexactlyBalancedBinaryTree1() {
        BinaryTreeNode node = new BinaryTreeNode(1)
        node.insertLeft(2)
        node.insertRight(3)
        with (node.left) {
            it.insertLeft(4)
            it.insertRight(5)
        }
        node
    }
}

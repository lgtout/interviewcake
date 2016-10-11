package com.lagostout.interviewcake

import com.lagostout.interviewcake.BinarySearchTreeChecker.BinaryTreeNode
import spock.lang.Specification
import spock.lang.Unroll

class BinarySearchTreeCheckerSpec extends Specification {

    @Unroll()
    def 'is valid'(BinaryTreeNode node, boolean expected) {
        expect:
        BinarySearchTreeChecker.isValid(node) == expected
        where:
        [node, expected] << [
                [bst1(), true],
                [bst2(), true],
                [bst3(), true],
                [bst4(), true],
                [bst5(), false],
                [bst6(), false],
                [bst7(), true],
                [bst8(), false],
                [bst9(), true],
                [bst10(), false],
                [bst11(), true],
                [bst12(), false],
                [bst13(), true],
                [bst14(), false],
        ]
    }

    BinaryTreeNode bst1() {
        new BinaryTreeNode(50)
    }

    BinaryTreeNode bst2() {
        def node = new BinaryTreeNode(50)
        node.insertLeft(40)
        node
    }

    BinaryTreeNode bst3() {
        def node = new BinaryTreeNode(50)
        node.insertRight(60)
        node
    }

    BinaryTreeNode bst4() {
        def node = new BinaryTreeNode(50)
        node.insertLeft(40)
        node.insertRight(60)
        println node
        node
    }

    BinaryTreeNode bst5() {
        def node = new BinaryTreeNode(50)
        node.insertLeft(60)
        node
    }

    BinaryTreeNode bst6() {
        def node = new BinaryTreeNode(50)
        node.insertRight(40)
        node
    }

    BinaryTreeNode bst7() {
        def node = new BinaryTreeNode(50)
        node.insertLeft(40).insertRight(45)
        node
    }

    BinaryTreeNode bst8() {
        def node = new BinaryTreeNode(50)
        node.insertLeft(40).insertRight(55)
        node
    }

    BinaryTreeNode bst9() {
        def node = new BinaryTreeNode(50)
        node.insertRight(60).insertLeft(55)
        node
    }

    BinaryTreeNode bst10() {
        def node = new BinaryTreeNode(50)
        node.insertRight(60).insertLeft(45)
        node
    }

    BinaryTreeNode bst11() {
        def node = new BinaryTreeNode(50)
        node.insertLeft(40).insertLeft(30)
        node
    }

    BinaryTreeNode bst12() {
        def node = new BinaryTreeNode(50)
        node.insertLeft(40).insertLeft(60)
        node
    }

    BinaryTreeNode bst13() {
        def node = new BinaryTreeNode(50)
        node.insertRight(60).insertRight(70)
        node
    }

    BinaryTreeNode bst14() {
        def node = new BinaryTreeNode(50)
        node.insertRight(60).insertRight(40)
        node
    }

}

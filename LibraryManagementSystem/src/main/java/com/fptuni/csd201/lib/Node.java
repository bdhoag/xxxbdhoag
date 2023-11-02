package com.fptuni.csd201.lib;

/**
 *
 * @author Thai Thanh Nguyen
 */
public class Node {

    int info;
    Node left, right;

    Node(int x) {
        info = x;
        left = right = null;
    }

    Node() {
    }

    Node(int x, Node p, Node q) {
        info = x;
        left = p;
        right = q;
    }
}

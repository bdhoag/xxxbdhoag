/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Thai Thanh Nguyen
 */
public class BinarySearchTree {

    Node root;

    BinarySearchTree() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    void clear() {
        root = null;
    }

    Node search(int x) {
        if (isEmpty()) {
            System.out.println("The empty tree!");
            return null;
        }
        Node cur = root;
        while (cur != null) {
            if (cur.info == x) {
                break;
            }
            if (x < cur.info) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return cur;
    }

    Node search(Node p, int x) {
        if (p == null) {
            return (null);
        }
        if (p.info == x) {
            return (p);
        }
        if (x < p.info) {
            return (search(p.left, x));
        } else {
            return (search(p.right, x));
        }
    }

    void insert(int x) {
        if (isEmpty()) {
            root = new Node(x);
            return;
        }
        Node f, p;
        p = root;
        f = null;
        while (p != null) {
            if (p.info == x) {
                System.out.println(" The key " + x
                        + " already exists, no insertion");
                return;
            }
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x < f.info) {
            f.left = new Node(x);
        } else {
            f.right = new Node(x);
        }
    }

    void breadth() {
        if (isEmpty()) {
            System.out.println("The empty tree!");
            return;
        }
        ArrayList<Node> arr = new ArrayList<>();
        arr.add(root);
        Node p;
        while (!arr.isEmpty()) {
            p = arr.get(0);
            arr.remove(0);
            if (p.left != null) {
                arr.add(p.left);
            }
            if (p.right != null) {
                arr.add(p.right);
            }
            visit(p);
        }
    }

    void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    int count() {
        return countSubTree(root);
    }

    int countSubTree(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + countSubTree(p.left) + countSubTree(p.right);
    }

    void visit(Node p) {
        System.out.print(p.info + " ");
    }

    void dele(int x) {
        Node dNode = search(x);
        if (dNode == null) {
            System.out.println("Not found!");
            return;
        }
        //dNode is exist in tree
        if (isLeaf(dNode)) {
            deleteLeaf(dNode);
        } else if (isNodeHasTwoChild(dNode)) {
            deleteByCopying(dNode);
//            deleteByMerging(p);
        } else {
            deleteNodeHasOneChild(dNode);
        }
        System.out.println("Delete successfully!");
    }

    boolean isLeaf(Node p) {
        return p.left == null && p.right == null;
    }

    boolean isNodeHasTwoChild(Node p) {
        return p.left != null && p.right != null;
    }

    void deleteLeaf(Node p) {
        //the tree has one element
        if (p == root) {
            clear();
            return;
        }
        //Node p has parent
        Node parent = getParentOf(p);
        if (parent.left == p) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }

    Node getParentOf(Node p) {
        if (p == null) {
            return null;
        }
        Node cur = root;
        while (cur != null) {
            if (cur.left == p || cur.right == p) {
                return cur;
            }
            if (p.info < cur.info) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    void deleteNodeHasOneChild(Node p) {
        if (p == root) {
            root = p.left == null ? p.right : p.left;
            return;
        }
        Node parent = getParentOf(p);
        if (parent.left == p) {
            parent.left = p.left == null ? p.right : p.left;
        } else {
            parent.right = p.left == null ? p.right : p.left;
        }
    }

    Node getPredecessor(Node p) {
        Node pred = p.left;
        while (pred.right != null) {
            pred = pred.right;
        }
        return pred;
    }

    Node getSuccessor(Node p) {
        Node succ = p.right;
        while (succ.left != null) {
            succ = succ.left;
        }
        return succ;
    }

//    void deleteByMerging(int x) {
//    }
    void deleteByCopying(Node p) {
        Node succ = getSuccessor(p);
        dele(succ.info);
        int temp = succ.info;
        succ.info = p.info;
        p.info = temp;
    }

    Node min() {
        if (isEmpty()) {
            System.out.println("The empty tree!");
        }
        Node cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    Node max() {
        if (isEmpty()) {
            System.out.println("The empty tree!");
        }
        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }

    int sum() {
        if (isEmpty()) {
            System.out.println("The empty tree!");
        } else {
            return sumSubTree(root);
        }
        return 0;
    }

    int sumSubTree(Node p) {
        if (p == null) {
            return 0;
        }
        return p.info + sumSubTree(p.left) + sumSubTree(p.right);
    }

    int avg() {
        if (isEmpty()) {
            System.out.println("The empty tree!");
        } else {
            return sum() / count();
        }
        return 0;
    }

    int getHeight(Node root) {
        return root.left != null && root.right != null ? 1 + Math.max(getHeight(root.left), getHeight(root.right))
                : (root.left == null && root.right == null ? 1
                        : (root.left == null ? 1 + getHeight(root.right)
                                : 1 + getHeight(root.left)));
    }

    int getMaxCostPath(Node root) {
        return root.left != null && root.right != null ? root.info + Math.max(getMaxCostPath(root.left), getMaxCostPath(root.right))
                : (root.left == null && root.right == null ? root.info
                        : (root.left == null ? root.info + getMaxCostPath(root.right)
                                : root.info + getMaxCostPath(root.left)));
    }

    void balance(int data[], int first, int last) {
        if (first <= last) {
            int middle = (first + last) / 2;
            insert(data[middle]);
            balance(data, first, middle - 1);
            balance(data, middle + 1, last);
        }
    }

    void balance() {
        int[] data = toArray();
        Arrays.sort(data);
        clear();
        balance(data, 0, data.length - 1);
    }

    boolean isBalance(Node root) {
        if (root.left == null) {
            return getHeight(root.right) <= 1;
        }
        if (root.right == null) {
            return getHeight(root.left) <= 1;
        }
        return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1;
    }

    int[] toArray() {
        int[] data = new int[count()];
        data[0] = root.info;
        int count = 0, index = 1;
        Node p;
        while (count < count()) {
            p = search(data[count++]);
            if (p.left != null) {
                data[index++] = p.left.info;
            }
            if (p.right != null) {
                data[index++] = p.right.info;
            }
        }
        return data;
    }

    boolean isBSTree(Node root) {
        return (root.left == null || (root.info > root.left.info && isBSTree(root.left)))
                && (root.right == null || (root.info < root.right.info && isBSTree(root.right)));
    }

    boolean isMaxHeap(Node root) {
        return (root.left == null || (root.info > root.left.info && isMaxHeap(root.left)))
                && (root.right == null || (root.info > root.right.info && isMaxHeap(root.right)));
    }

    boolean isMinHeap(Node root) {
        return (root.left == null || (root.info < root.left.info && isMinHeap(root.left)))
                && (root.right == null || (root.info < root.right.info && isMinHeap(root.right)));
    }
}

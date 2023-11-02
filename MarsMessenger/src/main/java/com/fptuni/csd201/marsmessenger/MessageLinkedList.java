/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.marsmessenger;

import com.fptuni.csd201.object.MessagePackage;

/**
 *
 * @author hoang
 */
public class MessageLinkedList {

    private Node head, tail;

    public MessageLinkedList() {
        head = null;
        tail = null;
    }

    public Node getHead() {
        return head;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void clear() {
        head = null;
        tail = null;
    }

    // null exception
    // head & tail is correct
    //    1. `void addToHead(MessagePackage x)` - add a node with info x  at the head of  a list.
    public void addToHead(MessagePackage x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
    }

    //2. `void addToTail(MessagePackage x)` - add a node with info x  at the tail of  a list.
    public void addToTail(MessagePackage x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            tail = head = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    //3. `void addAfter(Node p, MessagePackage x)` - add a node with info x  after the node p.
    public void addAfter(Node p, MessagePackage x) {
        if (search(p) == null) {
            System.out.println("Not Found!");
        } else if (p == tail) {
            addToTail(x);
        } else {
            Node newNode = new Node(x);
            newNode.setNext(p.getNext());
            p.setNext(newNode);
        }
    }

    //9. `Node search(MessagePackage x)` - 
    //search and return the reference to the first node having the 
    //same index.
    public Node search(MessagePackage x) {
        if (isEmpty()) {
            return null;
        }
        Node cur = head;
        while (cur != null && cur.getInfo().getIndex() != x.getIndex()) {
            cur = cur.getNext();
        }
        return cur;
    }

    public Node search(Node p) {
        if (isEmpty()) {
            return null;
        }
        Node cur = head;
        while (cur != null && cur != p) {
            cur = cur.getNext();
        }
        return cur;
    }

    //16. `void addBefore(Node p, int x)` - add a node with value x  before the node p.
    public void addBefore(Node p, MessagePackage x) {
        if (search(p) == null) {
            System.out.println("Not Found!");
        } else if (p == head) {
            addToHead(x);
        } else {
            Node cur = head;
            while (cur.getNext() != p) {
                cur = cur.getNext();
            }
            addAfter(cur, x);
        }
    }

    //23. `void insert(int x)` - insert node with value x into sorted list so that the new list is sorted.
    //Use for sorted list
    public void insert(MessagePackage x) {
        if (isEmpty()) {
            head = tail = new Node(x);
        } else {
            Node cur = head;
            while (cur != null) {
                if (x.getIndex() > tail.getInfo().getIndex()) {
                    addToTail(x);
                    break;
                } else if (x.getIndex() < cur.getInfo().getIndex()) {
                    addBefore(cur, x);
                    break;
                }
                cur = cur.getNext();
            }
        }
    }

}

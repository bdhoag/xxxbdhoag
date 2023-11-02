package com.fptuni.csd201.marsmessenger;

import com.fptuni.csd201.object.MessagePackage;

public class Node {

    MessagePackage info;
    Node next = null;

    Node() {
    }

    Node(MessagePackage info, Node nextNode) {
        this.info = info;
        next = nextNode;
    }

    Node(MessagePackage info) {
        this(info, null);
    }

    public MessagePackage getInfo() {
        return info;
    }

    public void setInfo(MessagePackage info) {
        this.info = info;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.bookservice;

import com.fptuni.csd201.object.Book;

/**
 *
 * @author hoang
 */
public class Node {

    Book info;
    Node left;
    Node right;

    Node(Book info) {
        this.info = info;
        left = right = null;
    }

    Node() {
    }

    Node(Book info, Node p, Node q) {
        this.info = info;
        left = p;
        right = q;
    }
}

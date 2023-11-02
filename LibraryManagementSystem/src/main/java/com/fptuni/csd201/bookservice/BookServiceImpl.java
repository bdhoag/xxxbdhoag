/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.csd201.bookservice;

import com.fptuni.csd201.object.Book;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Hoang
 */
public class BookServiceImpl implements BookService {

    Node rootByCode; //use for task 1-8
    Node rootByName; //use for task 9

    boolean isEmpty(Node root) {
        return root == null;
    }

    void clear() {
        rootByCode = null;
    }

    @Override
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }
        return insert(book);
    }

    boolean insert(Book b) {
        if (isEmpty(rootByCode)) {
            rootByCode = new Node(b);
            return true;
        }
        Node f, p;
        p = rootByCode;
        f = null;
        while (p != null) {
            if (p.info.equals(b)) {
                return false;
            }
            f = p;
            if (b.compareTo(p.info) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (b.compareTo(f.info) < 0) {
            f.left = new Node(b);
        } else {
            f.right = new Node(b);
        }
        return true;
    }

    @Override
    public void printBook(Book book) {
        if (book == null) {
            System.out.println("Not Found!");
            return;
        }
        System.out.println(book);
    }

    @Override
    public void showBook(int method) {
        if (isEmpty(rootByCode)) {
            System.out.println("Empty library!");
            return;
        }
        switch (method) {
            case 1:
                inOrder(rootByCode);
                break;
            case 2:
                breadth(rootByCode);
                break;
            default:
                System.out.println("Not support yet!");
        }
    }

    void breadth(Node root) {
        if (isEmpty(root)) {
            System.out.println("Empty library!");
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
            printBook(p.info);
        }
    }

    void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        printBook(p.info);
        inOrder(p.right);
    }

    @Override
    public Book searchBookbyCode(String bookCode) {
        if (bookCode == null || search(bookCode) == null) {
            return null;
        }
        return search(bookCode).info;
    }

    Node search(String code) {
        if (isEmpty(rootByCode)) {
            System.out.println("Empty library!");
            return null;
        }
        Node cur = rootByCode;
        while (cur != null) {
            if (cur.info.getCode().equals(code)) {
                break;
            }
            if (code.compareTo(cur.info.getCode()) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return cur;
    }

    @Override
    public int countBook() {
        return countSubTree(rootByCode);
    }

    int countSubTree(Node p) {
        if (p == null) {
            return 0;
        }
        return p.info.getQuantity() + countSubTree(p.left) + countSubTree(p.right);
    }

    @Override
    public boolean removeBook(String bookCode) {
        if (bookCode == null || search(bookCode) == null) {
            System.out.println("Not Found!");
            return false;
        }
        dele(search(bookCode));
        return true;
    }

    void dele(Node p) {

        //Node is exist in tree
        if (p.left == null && p.right == null) {
            deleteLeaf(p);
        } else if (p.left != null && p.right != null) {
            deleteByCopying(p);
        } else {
            deleteNodeHasOneChild(p);
        }
    }

    void deleteLeaf(Node p) {
        //the tree has one element
        if (p == rootByCode) {
            clear();
        } else {
            //Node p has parent
            Node parent = getParentOf(p);
            if (parent.left == p) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        System.out.println("Delete successfully!");
    }

    Node getParentOf(Node p) {
        if (p == null) {
            return null;
        }
        Node cur = rootByCode;
        while (cur != null) {
            if (cur.left == p || cur.right == p) {
                return cur;
            }
            if (p.info.compareTo(cur.info) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    void deleteNodeHasOneChild(Node p) {
        if (p == rootByCode) {
            rootByCode = p.left == null ? p.right : p.left;
            return;
        } else {
            Node parent = getParentOf(p);
            if (parent.left == p) {
                parent.left = p.left == null ? p.right : p.left;
            } else {
                parent.right = p.left == null ? p.right : p.left;
            }
        }
        System.out.println("Delete successfully!");
    }

    Node getSuccessor(Node p) {
        Node succ = p.right;
        while (succ.left != null) {
            succ = succ.left;
        }
        return succ;
    }

    void deleteByCopying(Node p) {
        Node succ = getSuccessor(p);
        dele(succ);
        Book temp = succ.info;
        succ.info = p.info;
        p.info = temp;
    }

    @Override
    public void printAvailableBook() {
        breadthAvailableBook();
    }

    void breadthAvailableBook() {
        if (isEmpty(rootByCode)) {
            System.out.println("Empty library!");
            return;
        }
        ArrayList<Node> arr = new ArrayList<>();
        arr.add(rootByCode);
        Node p;
        boolean found = false;
        while (!arr.isEmpty()) {
            p = arr.get(0);
            arr.remove(0);
            if (p.left != null) {
                arr.add(p.left);
            }
            if (p.right != null) {
                arr.add(p.right);
            }
            if (p.info.getLended() < p.info.getQuantity()) {
                printBook(p.info);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Not found any available book!");
        }
    }

    @Override
    public void balancing() {
        balance();
    }

    void balance(ArrayList<Book> data, int first, int last) {
        if (first <= last) {
            int middle = (first + last) / 2;
            insert(data.get(middle));
            balance(data, first, middle - 1);
            balance(data, middle + 1, last);
        }
    }

    void balance() {
        if (isEmpty(rootByCode)) {
            return;
        }
        ArrayList<Book> data = toArrayList();
        Collections.sort(data);
        clear();
        balance(data, 0, data.size() - 1);
    }

    ArrayList<Book> toArrayList() {
        ArrayList<Book> data = new ArrayList<>();
        data.add(rootByCode.info);
        int count = 0;
        Node p;
        while (count < countNode(rootByCode)) {
            p = search(data.get(count++).getCode());
            if (p.left != null) {
                data.add(p.left.info);
            }
            if (p.right != null) {
                data.add(p.right.info);
            }
        }
        return data;
    }

    int countNode(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + countNode(p.left) + countNode(p.right);
    }

    @Override
    public Book searchBookbyName(String name) {
        setNewTreeByName();
        if (name == null || searchByName(name) == null) {
            return null;
        }
        return searchByName(name).info;
    }

    void setNewTreeByName() {
        rootByName = null;
        ArrayList<Book> arr = toArrayList();
        while (!arr.isEmpty()) {
            insertCompareName(arr.get(0));
            arr.remove(0);
        }
    }

    boolean insertCompareName(Book b) {
        if (isEmpty(rootByName)) {
            rootByName = new Node(b);
            return true;
        }
        Node f, p;
        p = rootByName;
        f = null;
        while (p != null) {
            if (p.info.getName().equals(b.getName())) {
                return false;
            }
            f = p;
            if (b.getName().compareTo(p.info.getName()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (b.getName().compareTo(f.info.getName()) < 0) {
            f.left = new Node(b);
        } else {
            f.right = new Node(b);
        }
        return true;
    }

    Node searchByName(String name) {
        if (isEmpty(rootByName)) {
            System.out.println("Empty library!");
            return null;
        }
        Node cur = rootByName;
        while (cur != null) {
            if (cur.info.getName().equals(name)) {
                break;
            }
            if (name.compareTo(cur.info.getName()) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return cur;
    }
}

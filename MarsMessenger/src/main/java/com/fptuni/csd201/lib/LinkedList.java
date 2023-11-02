/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

/**
 *
 * @author Thai Thanh Nguyen
 */
public class LinkedList {

    private Node head, tail;

    public LinkedList() {
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

    //    1. `void addToHead(int x)` - add a node with value x  at the head of  a list.
    /**
     * Add the node with value x to the head of linked list
     *
     * @param x : integer
     */
    public void addToHead(int x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
    }

    //2. `void addToTail(int x)` - add a node with  value x  at the tail of  a list.
    /**
     * Add the node with value x to the tail of linked list
     *
     * @param x : integer
     */
    public void addToTail(int x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            tail = head = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    //3. `void addAfter(Node p, int x)` - add a node with value x  after the node p.
    /**
     * Add the node with value x to the linked list after the available node
     *
     * @param p: Node
     * @param x: integer
     */
    public void addAfter(Node p, int x) {
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

    /**
     * Add the node with value x to the linked list at index
     *
     * @param index: integer
     * @param x: integer
     */
    public void addAtIndex(int index, int x) {
        Node cur = head;
        int count = 0;
        while (cur != null && count < index - 1) {
            cur = cur.getNext();
            count++;
        }

        if (index == 0) {
            addToHead(x);
        } else if (cur == null || index < 0) {
            System.out.println("Invalid index!");
        } else if (cur == tail) {
            addToTail(x);
        } else {
            addAfter(cur, x);
        }
    }

    //4. `void traverse()` - traverse from head to tail and dislay info of all nodes in the list.
    /**
     * Traverse the linked list and print out its info
     */
    public void traverse() {
        if (isEmpty()) {
            System.out.println("The list empty!");
        } else {
            Node cur = head;
            while (cur != null) {
                System.out.print(cur.getInfo() + "  ");
                cur = cur.getNext();
            }
        }
    }

    //5. `int deleteFromHead()` - delete the head and return its info.
    /**
     * Delete the head of the linked list. Call this method when the list not
     * empty
     *
     * @return head's info
     */
    public int deleteFromHead() {
        Node cur = head, nodeDel = head;
        if (head == tail) {
            head = tail = null;
        } else {
            head = cur.getNext();
        }
        return nodeDel.getInfo();
    }

    public void deleteHead() {
        if (isEmpty()) {
            System.out.println("The list empty!");
        } else {
            System.out.println(deleteFromHead());
        }
    }

    //6. `int deleteFromTail()` - delete the tail and return its info.
    /**
     * Delete the tail of the linked list. Call this method when the list not
     * empty
     *
     * @return tail's info
     */
    public int deleteFromTail() {
        Node cur = head, nodeDel = tail;
        if (head == tail) {
            head = tail = null;
        } else {
            while (cur.getNext() != tail) {
                cur = cur.getNext();
            }
            tail = cur;
            cur.setNext(null);
        }
        return nodeDel.getInfo();
    }

    public void deleteTail() {
        if (isEmpty()) {
            System.out.println("The list empty!");
        } else {
            System.out.println(deleteFromTail());
        }
    }

    //7. `int deleteAter(Node p)` - delete the node after the node  p  and return its info.
    /**
     * Delete the node after node p
     *
     * @param p: Node
     * @return the delete node's info
     */
    @SuppressWarnings("null")
    public int deleteAfter(Node p) {
        Node delNode = null;
        if (search(p) == null) {
            System.out.println("Not Found!");
        } else {
            delNode = p.getNext();
            p.setNext(p.getNext().getNext());
        }
        return delNode.getInfo();
    }

    /**
     * Delete the node at index
     *
     * @param index: integer
     */
    public void deleteAtIndex(int index) {
        Node cur = head;
        int count = 0;
        while (cur != null && count < index) {
            cur = cur.getNext();
            count++;
        }
        if (cur == null) {
            System.out.println("Invalid index!");
        } else {
            remove(cur);
        }
    }

    //8. `void dele(int x)` - delete the first node whose info is equal to  x.
    /**
     * Delete the first node whose info is equal to x.
     *
     * @param x: integer
     */
    public void deleByValue(int x) {
        if (search(x) == null) {
            System.out.println("Not Found!");
        } else {
            remove(search(x));
        }
    }

    //9. `Node search(int x)` - search and return the reference to the first node having value x
    /**
     * search and return the reference to the first node having value x
     *
     * @param x: integer
     * @return the reference of the node having value x
     */
    public Node search(int x) {
        if (isEmpty()) {
            return null;
        }
        Node cur = head;
        while (cur != null && cur.getInfo() != x) {
            cur = cur.getNext();
        }
        return cur;
    }

    /**
     * search and return the reference the node p
     *
     * @param p: Node
     * @return the reference of the node p
     */
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

    //10. `int count()` - count and return number of nodes in the list.
    /**
     * count the number of node in list
     *
     * @return number of node in list
     */
    public int count() {
        Node cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.getNext();
            count++;
        }
        return count;
    }

    //11. `void dele(int i)` - delete an i-th node on the list. Besure that such a node exists.
    /**
     * delete an i-th node on the list
     *
     * @param i: integer
     */
    public void deleInOrder(int i) {
        deleteAtIndex(i - 1);
    }

    //12. `void sort()` - sort the list by ascending order of info.
    /**
     * sort the list by ascending order of info
     */
    public void sort() {
        Node lastSorted = null;
        while (lastSorted != head) {
            Node cur = head;
            while (cur.getNext() != lastSorted) {
                Node curNext = cur.getNext();
                if (cur.getInfo() > curNext.getInfo()) {
                    int temp = cur.getInfo();
                    cur.setInfo(curNext.getInfo());
                    curNext.setInfo(temp);
                }
                cur = curNext;
            }
            lastSorted = cur;
        }
    }

    //13. `void remove(Node p)` - delete node p if it exists in the list.
    /**
     * delete node p if it exists in the list
     *
     * @param p: Node
     */
    public void remove(Node p) {
        if (p == head) {
            deleteFromHead();
        } else if (p == tail) {
            deleteFromTail();
        } else {
            Node cur = head;
            while (cur.getNext() != p && cur.getNext() != null) {
                cur = cur.getNext();
            }
            if (cur.getNext() == p) {
                deleteAfter(cur);
            } else {
                System.out.println("Not Found!");
            }
        }
    }

    //14. `int [] toArray()` - create and return array containing info of all nodes in the list.
    /**
     * create and return array containing info of all nodes in the list
     *
     * @return array containing all node
     */
    public int[] toArray() {
        int[] arr = null;
        if (isEmpty()) {
            System.out.println("The list empty!");
        } else {
            arr = new int[count()];
            Node cur = head;
            int index = 0;
            while (cur != null) {
                arr[index++] = cur.getInfo();
                cur = cur.getNext();
            }
        }
        return arr;
    }

    //15. Merge two ordered singly linked lists of integers into one ordered list.
    /**
     * Merge two ordered singly linked lists of integers into one ordered list
     *
     * @param l1: LinkedList
     * @param l2: LinkedList
     * @return new list from merging
     */
    public static LinkedList merge(LinkedList l1, LinkedList l2) {
        LinkedList list = new LinkedList();
        l1.sort();
        l2.sort();
        if (l1.isEmpty() && l2.isEmpty()) {
            list.head = list.tail = null;
        } else if (l1.isEmpty()) {
            list.head = l2.head;
            list.tail = l2.tail;
        } else if (l2.isEmpty()) {
            list.head = l1.head;
            list.tail = l1.tail;
        } else {
            list.head = new Node(Math.min(l1.head.getInfo(), l2.head.getInfo()));
            list.tail = list.head;
            Node cur1 = (l1.head.getInfo() > l2.head.getInfo()) ? l1.head : l2.head;
            Node cur2 = (l1.head.getInfo() <= l2.head.getInfo()) ? l1.head.getNext() : l2.head.getNext();

            while (cur1 != null && cur2 != null) {
                if (cur1.getInfo() < cur2.getInfo()) {
                    list.addToTail(cur1.getInfo());
                    cur1 = cur1.getNext();
                } else {
                    list.addToTail(cur2.getInfo());
                    cur2 = cur2.getNext();
                }
            }

            Node cur = (cur1 == null) ? cur2 : cur1;
            list.tail.setNext(cur);
            while (cur.getNext() != null) {
                cur = cur.getNext();
            }
            list.tail = cur;

        }
        return list;
    }

    //16. `void addBefore(Node p, int x)` - add a node with value x  before the node p.
    /**
     * Add the node with value x to the linked list before the available node
     *
     * @param p: Node
     * @param x: integer
     */
    public void addBefore(Node p, int x) {
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

    //17. Attach a singly linked list to the end of another singly linked list.
    /**
     * Attach a singly linked list to the end of another singly linked list
     *
     * @param l: LinkedList
     * @return new linkedList from attaching
     */
    public LinkedList attachToTheTail(LinkedList l) {
        if (l.isEmpty()) {
            l.head = head;
            l.tail = tail;
        }
        l.tail.setNext(head);
        l.tail = tail;
        return l;
    }

    //18. `int max()` - find and return the maximum value in the list. 
    /**
     * find the maximum value in the list
     *
     * @return max value in list, if the list empty return 0
     */
    public int max() {
        if (isEmpty()) {
            return 0;
        }
        int max = head.getInfo();
        Node cur = head.getNext();
        while (cur != null) {
            if (cur.getInfo() > max) {
                max = cur.getInfo();
            }
            cur = cur.getNext();
        }
        return max;
    }

    //19. `int min()` - find and return the minimum value in the list. 
    /**
     * find the minimum value in the list
     *
     * @return min value in list, if the list empty return 0
     */
    public int min() {
        if (isEmpty()) {
            return 0;
        }
        int min = head.getInfo();
        Node cur = head.getNext();
        while (cur != null) {
            if (cur.getInfo() < min) {
                min = cur.getInfo();
            }
            cur = cur.getNext();
        }
        return min;
    }

    //20. `int sum()` - return the sum of all values in the list. 
    /**
     * find the sum of all node value
     *
     * @return sum of all node, if the list empty return 0
     */
    public int sum() {
        if (isEmpty()) {
            return 0;
        }
        int sum = 0;
        Node cur = head;
        while (cur != null) {
            sum += cur.getInfo();
            cur = cur.getNext();
        }
        return sum;
    }

    //21. `int avg()` - return the average of all values in the list.
    /**
     * find the average of all node value
     *
     * @return average, if the list empty return 0
     */
    public double avg() {
        return isEmpty() ? 0 : (double) sum() / count();
    }

    //22. `boolean sorted()` - check and return true if the list is sorted, return false if the list is not sorted.
    /**
     * check the list is sorted or not
     *
     * @return true if sorted, false in opposite, if the list empty return false
     */
    public boolean sorted() {
        if (isEmpty()) {
            System.out.println("The list empty!");
            return false;
        }
        Node cur = head;
        while (cur.getNext() != null) {
            if (cur.getInfo() > cur.getNext().getInfo()) {
                return false;
            }
            cur = cur.getNext();
        }
        return true;
    }

    //23. `void insert(int x)` - insert node with value x into sorted list so that the new list is sorted.
    /**
     * insert node with value x into sorted list so that the new list is sorted
     *
     * @param x: integer
     */
    public void insert(int x) {
        if (isEmpty()) {
            head = tail = new Node(x);
        } else {
            sort();
            Node cur = head;
            while (cur != null) {
                if (x > tail.getInfo()) {
                    addToTail(x);
                    break;
                } else if (x < cur.getInfo()) {
                    addBefore(cur, x);
                    break;
                }
                cur = cur.getNext();
            }
        }
    }

    //24. Reverse a singly linked list using only one pass through the list.
    /**
     * Reverse a singly linked list using only one pass through the list.
     */
    public void reverse() {
        Node nodeBefore = head;
        Node cur = nodeBefore.getNext();
        Node oldHead = head;
        while (cur != null) {
            if (cur == tail) {
                head = cur;
            }
            Node nodeAfter = cur.getNext();
            cur.setNext(nodeBefore);
            nodeBefore = cur;
            cur = nodeAfter;
        }
        tail = oldHead;
        tail.setNext(null);
    }

    //25. Check whether two singly linked list have the same contents.   
    /**
     * Check whether two singly linked list have the same contents
     *
     * @param l2: LinkedList
     * @return true if same content, false in opposite
     */
    public boolean checkSameContent(LinkedList l2) {
        if (l2.count() != count()) {
            return false;
        }
        Node cur1 = head, cur2 = l2.head;
        while (cur1 != null) {
            if (cur1.getInfo() != cur2.getInfo()) {
                return false;
            }
            cur1 = cur1.getNext();
            cur2 = cur2.getNext();
        }
        return true;
    }

}

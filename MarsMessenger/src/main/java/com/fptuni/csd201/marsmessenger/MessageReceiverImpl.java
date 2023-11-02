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
public class MessageReceiverImpl implements MessageReceiver {

    private MessageLinkedList mList = new MessageLinkedList();

    @Override
    public void receive(MessagePackage pck) {

        System.out.println("Index: " + pck.getIndex() + " - " + pck.getContent());
        // STUDENT DEVELOPS CODE HERE
        //check whether index exist or not. Search by index
        Node node = mList.search(pck);
        //not exist => insert to linkedlist
        if (node == null) {
            mList.insert(pck);
            return;
        }
        //already exist: FOR TASK 5
        //check whether the content length equal size or not
        if (pck.getContent().length() == pck.getSize()) { //the content correctly => insert
            node.setInfo(pck);
        } else if (node.getInfo().getContent().length() != pck.getSize()) { //the content is broken
            String correctContent = getCorrectContent(node.getInfo().getContent(), pck.getContent()); //get the correct content
            node.getInfo().setContent(correctContent);
        }
    }

    @Override
    public String getMessage() {
        // STUDENT DEVELOPS CODE HERE
        String message = "";
        Node cur = mList.getHead();
        while (cur != null) {
            message += cur.getInfo().getContent();
            cur = cur.getNext();
        }
        if (message.isEmpty()) {
            System.out.println("No Message!");
        } else {
            System.out.println("Message:");
        }
        return message;
    }

    @Override
    public int[] getMissingIndex() {
        // STUDENT DEVELOPS CODE HERE
        String missIndexStr = "";
        int[] miss = null;
        int count = 0, index = 0;
        Node cur = mList.getHead();
        while (cur != null) {
            while (cur.getInfo().getIndex() != count) {
                missIndexStr = missIndexStr + count + ",";
                count++;
            }
            count++;
            cur = cur.getNext();
        }
        if (!missIndexStr.trim().isEmpty()) {
            System.out.println("Missing index: ");
            String[] missIndexArr = missIndexStr.split(",");
            miss = new int[missIndexArr.length];
            for (int i = 0; i < missIndexArr.length; i++) {
                miss[index++] = Integer.parseInt(missIndexArr[i]);
            }
        } else {
            System.out.println("No Missing Index!");
            miss = new int[0];
        }
        return miss;
    }

    //Thuật toán: n kí tự cuối của chuỗi thứ nhất là đầu của chuỗi thì 2 thì ghép lại
    //Để tăng độ chính xác thì lấy n >=3
    //Tạo substring chứa đuôi của chuỗi s1 và kiểm tra với đầu chuỗi s2 và ngược lại
    //x là biến số tăng dần để tạo substring bắt đầu từ index 1
    // n>=3 nên x < (độ dài chuỗi - 3)
    public String getCorrectContent(String s1, String s2) {
        int x = 1;
        while (x != s1.length() - 3 && !s2.startsWith(s1.substring(x))) {
            x++;
        }
        if (x != s1.length() - 3) {
            return s1 + s2.substring(s1.length() - x);
        }
        x = 1;
        while (x != s2.length() - 3 && !s1.startsWith(s2.substring(x))) {
            x++;
        }
        if (x != s2.length() - 3) {
            return s2 + s1.substring(s2.length() - x);
        }
        return null;
    }

}

package com.lanterntown.learnning.leetcode;


class Soution {
    /**
     * 螺旋矩阵
     *
     * @param n n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] a = new int[n][n];
        for (int i = 0; i < n; ++i) {

        }

        return a;

    }

    public static void main(String[] args) {
        String s = "2";
        String[] strings = s.split("\t");
        for (int i=0; i<strings.length; ++i){
            s= strings[i];
            System.out.println(strings[i]);
        }
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
package com.手撕算法.牛客网;

import java.util.Scanner;
/**
 * 问题描述：数独（Sudoku）是一款大众喜爱的数字逻辑游戏。
 * 玩家需要根据9X9盘面上的已知数字，推算出所有剩余空格的数字，并且满足每一行、每一列、每一个3X3粗线宫内的数字均含1-9，并且不重复。
 * 例如：
 * 输入
 *
 * 输出
 *
 *
 * 数据范围：输入一个 9*9 的矩阵
 */
public class 数独 {

    /**
     * Sudoku#回溯法解数独
     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] board = new int[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                board[i][j] = in.nextInt();
            }
        }
        solveSudoku(board);
        //输出二维矩阵
        for(int i=0;i<9;i++){
            for(int j=0;j<8;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println(board[i][8]);//换行，每一行的最后一个数字
        }
    }
    public static boolean solveSudoku(int[][] board){
        //「一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
        // 一行一***定下来之后，递归遍历这个位置放9个数字的可能性！」
        for (int i = 0; i < 9; i++){ // 遍历行
            for (int j = 0; j < 9; j++){ // 遍历列
                if (board[i][j] != 0){ // 跳过原始数字
                    continue;
                }
                for (int k = 1; k <= 9; k++){ // (i, j) 这个位置放k是否合适
                    if (isValidSudoku(i, j, k, board)){
                        board[i][j] = k;//将k放在（i，j）
                        if (solveSudoku(board)){ // 如果找到合适一组立刻返回
                            return true;
                        }
                        board[i][j] = 0;//回溯
                    }
                }
                // 9个数都试完了，都不行，那么就返回false
                return false;
                // 因为如果一行一***定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
                // 那么会直接返回， 「这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！」
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    /**
     * 判断棋盘是否合法有如下三个维度:
     *     同行是否重复
     *     同列是否重复
     *     9宫格里是否重复
     */
    public static boolean isValidSudoku(int row, int col, int val, int[][] board){
        // 同行是否重复
        for (int i = 0; i < 9; i++){
            if (board[row][i] == val){
                return false;
            }
        }
        // 同列是否重复
        for (int j = 0; j < 9; j++){
            if (board[j][col] == val){
                return false;
            }
        }
        // 9宫格里是否重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++){
            for (int j = startCol; j < startCol + 3; j++){
                if (board[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }
}

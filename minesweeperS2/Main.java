package minesweeperS2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        Scanner scanner = new Scanner(System.in);
        int mine = scanner.nextInt();
        char[][] board = initializare(9,9,mine);
        afisare(board);
    }

    public static char[][] initializare(int rows, int cols, int totalMines){
        char[][] board = new char[rows][cols];
        int mines = 0;

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                board[i][j] = '.';
            }
        }

        while(mines< totalMines){
            int rowsR = (int) Math.floor(Math.random()*rows);
            int colR = (int) Math.floor(Math.random()*cols);
            if(board[rowsR][colR]!='X'){
                board[rowsR][colR] = 'X';
                mines++;
            }
        }
        return board;
    }

    public static void afisare(char[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}

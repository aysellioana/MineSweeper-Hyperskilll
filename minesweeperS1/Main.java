package minesweeperS1;

public class Main {
    public static void main(String[] args) {
        char[][] board = initializare(9,9,10);
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

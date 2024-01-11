package minesweeperS3;

public class MineSweeperGame {
    private int rows;
    private int cols;
    private int numMines;
    private char[][] field;

    public MineSweeperGame(int rows, int cols, int numMines){
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.field = new char[rows][cols];

        initializeField(rows, cols);
    }

    private void initializeField(int rows, int cols){
        field = new char[rows][cols];
        int mines = 0;

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                field[i][j] = '.';
            }
        }

        while(mines< numMines){
            int rowsR = (int) Math.floor(Math.random()*rows);
            int colR = (int) Math.floor(Math.random()*cols);
            if(field[rowsR][colR]!='X'){
                field[rowsR][colR] = 'X';
                mines++;
            }
        }
    }

    public void printField(){
        for(int i=0;i<field.length;i++){
            for(int j=0;j<field[i].length;j++){
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    private int countMines(int row, int col){
        int num = 0;

        for (int x = Math.max(0, row - 1); x <= Math.min(field.length - 1, row + 1); x++) {
            for (int y = Math.max(0, col - 1); y <= Math.min(field[0].length - 1, col + 1); y++) {
                if (field[x][y] == 'X') {
                    num++;
                }
            }
        }

        return num;
    }

    public void updateField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == 'X') {
                    // Dacă celula conține o mină, păstrăm 'X'
                } else {
                    // Dacă celula nu conține o mină, aplicăm regulile și actualizăm valoarea
                    int numMines = countMines(i, j);
                    if (numMines > 0 && numMines <= 8) {
                        field[i][j] = (char) ('0' + numMines);
                    }
                }
            }
        }
    }


}

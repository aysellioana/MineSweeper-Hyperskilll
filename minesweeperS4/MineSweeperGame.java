package minesweeperS4;

import java.util.Scanner;

class MinesweeperGame {
    private int rows;
    private int cols;
    private int numMines;
    private char[][] field;
    private char[][] mines;

    public MinesweeperGame(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.field = new char[rows][cols];
        this.mines = new char[rows][cols];

        initializeField(rows, cols);
        initializeMines();
        updateField();
    }

    private void initializeField(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                field[i][j] = '.';
            }
        }
    }

    private void initializeMines() {
        int minesCount = 0;
        while (minesCount < numMines) {
            int randomRow = (int) (Math.random() * rows);
            int randomCol = (int) (Math.random() * cols);

            if (mines[randomRow][randomCol] != 'X') {
                mines[randomRow][randomCol] = 'X';
                minesCount++;
            }
        }
    }

    public void printField() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < cols; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    private int countMines(int row, int col) {
        int num = 0;

        for (int x = Math.max(0, row - 1); x <= Math.min(rows - 1, row + 1); x++) {
            for (int y = Math.max(0, col - 1); y <= Math.min(cols - 1, col + 1); y++) {
                if (mines[x][y] == 'X') {
                    num++;
                }
            }
        }

        return num;
    }

    public void updateField() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j] == '*' || mines[i][j] == 'X') {
                    field[i][j] = 'X';
                } else {
                    int numMines = countMines(i, j);
                    if (numMines > 0 && numMines <= 8) {
                        field[i][j] = (char) ('0' + numMines);
                    } else if (numMines == 0) {
                        field[i][j] = '/';
                    }
                }
            }
        }
    }


    public void findMines() {
        int numMinesMarked = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            printField();
            System.out.println("Set/delete mine marks (x and y coordinates):");
            int inputY = scanner.nextInt() - 1;
            int inputX = scanner.nextInt() - 1;


            if (field[inputX][inputY] == '*') {
                field[inputX][inputY] = '.';
                numMinesMarked--;
            } else if (field[inputX][inputY] == '.' || mines[inputX][inputY] == 'X') {
                field[inputX][inputY] = '*';
                numMinesMarked++;
            } else {
                System.out.println("There is a number here!");
                continue;
            }

            // Check if all mines are marked correctly
            if (numMinesMarked == countMinesMarkedCorrectly()) {
                System.out.println("Congratulations! You found all the mines!");
                return; // Exit the loop
            }

        } while (true);
    }

    private int countMinesMarkedCorrectly() {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j] == '*' && mines[i][j] == 'X') {
                    count++;
                }
            }
        }
        return count;
    }
}

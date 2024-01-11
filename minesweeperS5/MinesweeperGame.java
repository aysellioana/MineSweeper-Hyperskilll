package minesweeperS5;
import java.util.Scanner;

class MinesweeperGame {
    private int rows;
    private int cols;
    private int numMines;
    private char[][] field;
    private char[][] mines;
    private char[][] explore;

    public MinesweeperGame(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.field = new char[rows][cols];
        this.mines = new char[rows][cols];
        this.explore = new char[rows][cols];

        initializeField(rows, cols);
        initializeMines();
        updateField();
    }

    private void initializeField(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                field[i][j] = '.';
                explore[i][j] = '.';
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
                updateNumbers(randomRow, randomCol);
            }
        }
    }

    private void updateNumbers(int row, int col) {
        for (int x = Math.max(0, row - 1); x <= Math.min(rows - 1, row + 1); x++) {
            for (int y = Math.max(0, col - 1); y <= Math.min(cols - 1, col + 1); y++) {
                if (mines[x][y] != 'X') {
                    mines[x][y] = (char) ('0' + countMines(x, y));
                }
            }
        }
    }

    public void printField() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < cols; j++) {
                System.out.print(explore[i][j]);
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

    public void exploreCell(int row, int col) {
        if (explore[row][col] == '/' || Character.isDigit(explore[row][col])) {
            return;
        }

        if (field[row][col] == 'X' || mines[row][col] == 'X') {
            if (explore[row][col] != '/') {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (mines[i][j] == 'X') {
                            explore[i][j] = 'X';
                        }
                    }
                }
            }
            printField();
            System.out.println("You stepped on a mine and failed!");
            System.exit(0);
        }

        int numMines = countMines(row, col);
        if (numMines == 0) {
            explore[row][col] = '/';
        } else {
            explore[row][col] = (char) ('0' + numMines);
        }

        if (numMines == 0) {
            exploreCellIfValid(row - 1, col);
            exploreCellIfValid(row + 1, col);
            exploreCellIfValid(row, col - 1);
            exploreCellIfValid(row, col + 1);
        }
        updateField();
    }

    private void exploreCellIfValid(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols && explore[row][col] != '/') {
            exploreCell(row, col);
        }
    }

    private void generateMinesAvoidingFirstExplore(int firstExploreRow, int firstExploreCol) {
        int minesCount = 0;
        while (minesCount < numMines) {
            int randomRow = (int) (Math.random() * rows);
            int randomCol = (int) (Math.random() * cols);

            if (randomRow != firstExploreRow || randomCol != firstExploreCol) {
                if (mines[randomRow][randomCol] != 'X') {
                    mines[randomRow][randomCol] = 'X';
                    minesCount++;
                }
            }
        }
    }

    public void playGameWithCommands() {
        Scanner scanner = new Scanner(System.in);
        printField();

        while (true) {
            System.out.print("Set/unset mine marks or claim a cell as free: ");
            int col = scanner.nextInt() - 1;
            int row = scanner.nextInt() - 1;
            String command = scanner.next();

            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                System.out.println("Invalid coordinates. Try again.");
                continue;
            }

            if (command.equalsIgnoreCase("mine")) {
                toggleMineMark(row, col);
            } else if (command.equalsIgnoreCase("free")) {
                exploreCell(row, col);
            } else {
                System.out.println("Invalid command. Please enter 'mine' or 'free'.");
                continue;
            }

            printField();

            if (checkWin()) {
                System.out.println("Congratulations! You won!");
                return;
            }
        }
    }

    private void toggleMineMark(int row, int col) {
        if (explore[row][col] == '.') {
            explore[row][col] = '*';
        } else if (explore[row][col] == '*') {
            explore[row][col] = '.';
        }
        updateField();
    }

    private boolean checkWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mines[i][j] != 'X' && explore[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }
}
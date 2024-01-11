package minesweeperS3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        Scanner scanner = new Scanner(System.in);
        int mines = scanner.nextInt();
        MineSweeperGame mine = new MineSweeperGame(9,9,mines);
        mine.updateField();
        mine.printField();

    }

}

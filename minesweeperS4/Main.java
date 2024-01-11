package minesweeperS4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many mines do you want on the field? ");
        int numMines = scanner.nextInt();

        MinesweeperGame game = new MinesweeperGame(9, 9, numMines);
        game.findMines();
    }

}

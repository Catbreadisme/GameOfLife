package main.java;
/**
 * Conway's Game of Life
 * by Luka Scott started on 27/04/2023
 * <a href="https://www.github.com/SkySourced/GameOfLife">...</a>
 *
 * Simulates Conway's Game of Life in console
 * This class starts the program
 */

public class Main {
    public static void main(String[] args) {
        Game game = new Game(); // Create a new game
        Game.kb = new java.util.Scanner(System.in); // Initialise the keyboard input
        game.gridInit(); // Initialise the grid
    }
}

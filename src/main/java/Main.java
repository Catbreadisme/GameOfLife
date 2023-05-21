/**
 * @title Conway's Game of Life
 * @author Luka Scott
 * @started 27/04/2023
 * @repository <a href="https://www.github.com/SkySourced/GameOfLife">Github repo</a>
 *
 * Simulates Conway's Game of Life in console
 * This class starts the program
 */

package main.java;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(); // Create a new game
        Game.kb = new java.util.Scanner(System.in); // Initialise the keyboard input
        game.gridInit(); // Initialise the grid
    }
}

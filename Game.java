/*
 * Conway's Game of Life
 * by Luka Scott started on 27/04/2023
 * https://www.github.com/SkySourced/GameOfLife
 */

import java.util.Timer;
import java.util.Scanner;

public class Game {
    // Constants
    public static final int WIDTH = 50; // Width of the grid
    public static final int HEIGHT = 50; // Height of the grid
    // Public variables
    public Cell[][] cells; // 2D array of cells
    // Private variables
    private int rate; // Rate of updates per second
    // Library implementations
    private static Keyboard kb; // Keyboard input

    // Main
    public static void main(String[] args) {
        kb = new Scanner(System.in);
        gridInit();
    }

    // Initialises the grid
    private static void gridInit() {
        this.cells = new Cell[WIDTH][HEIGHT]; // Initialise the 2D array
        for (int i = 0; i < WIDTH; i++) { // For each row
            for (int j = 0; j < HEIGHT; j++) { // For each column
                cells[i][j] = new Cell(i, j, this); // Create a new cell
            }
        }
    }
    // Update every cell
    public static void update() {
        for (Cell[] row : cells) { // For each row
            for (Cell cell : row) { // For each column
                cell.update(); // Update the cell
            }
        }
    }
    // Render the grid
    public static void render() {
        for (Cell[] row : cells) { // For each row
            for (Cell cell : row) { // For each column
                System.out.print(cell.render()); // Print the rendered cell
            }
            System.out.println(); // Print a new line after each row is complete
        }
    }
}
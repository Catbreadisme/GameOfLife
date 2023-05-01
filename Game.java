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
        render(); // Render the grid
        newTurn();
    }
    private static void newTurn() {
        System.out.println("Enter the x coordinate of a cell you would like to toggle, or enter 'next <no. of turns>' to evaluate turns.");
        String input = kb.nextLine(); // Get the user's input
        int x;
        try { // Try to parse the input as an integer
            Integer.parseInt(input);
        } catch { // If it fails, check if the input is "next"
            word = input.split(" ")[0]; // Split the input into words
            if (word.toLowerCase() == "next") {
                try {
                    int turns = Integer.parseInt(input.split(" ")[1]); // Get the number of turns
                } catch { // No specified turn number, run one turn
                    runTurns(1);
                }
                runTurns(turns); // Run the turns
            } else { // If it's not "next", then the input is invalid
                System.out.println("Invalid input. Please enter a number or 'next'.");
                newTurn(); // Start a new turn
            }
        }
        System.out.println("Now enter the y coordinate of the cell to toggle.");
        int y = kb.nextInt(); // Get the user's input
        cells[x][y].state = !cells[x][y].state; // Toggle the cell's state
        newTurn(); // Start a new turn
    }
    public static void runTurns(int turns){
        for(int i = 0; i < turns; i++){
            update();
            render();
        }
        newTurn();
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
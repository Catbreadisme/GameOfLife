/**
 * @title Conway's Game of Life
 * @author Luka Scott
 * @version 1.0
 * @started 27/04/2023
 * @repository https://www.github.com/SkySourced/GameOfLife
 *
 * This class handles all game events and displays the full grid.
 */

package main.java;

import java.util.Scanner;

public class Game {
    // Constants
    public static final int GRID_SIZE = 10; // Size of the grid
    private static final int rate = 1; // Rate of updates per second
    // Variables
    public static Cell[][] cells; // 2D array of cells
    // Library implementations
    public static Scanner kb; // Keyboard input

    // Initialises the grid
    public void gridInit() {
        cells = new Cell[GRID_SIZE][GRID_SIZE]; // Initialise the 2D array
        for (int i = 0; i < GRID_SIZE; i++) { // For each row
            for (int j = 0; j < GRID_SIZE; j++) { // For each column
                cells[j][i] = new Cell(i, j, this); // Create a new cell
            }
        }
        newTurn(); // Start first turn
    }
    private static void newTurn() { // Effectively the main loop
        render(); // Render the grid
        System.out.println("Enter the x coordinate of a cell you would like to toggle, or enter 'next <no. of turns>' to evaluate turns.");
        System.out.println("You can also enter 'auto <number of repeating states>' to run the simulation automatically, ending when the grid repeats within the specified number of turns. Enter 0 here to run indefinitely.");
        String[] input = kb.nextLine().split(" "); // Get the user's input
        int x = 0; // X coordinate
        int y = 0; // Y coordinate
        int turns; // Number of turns to run

        if (input[0].equalsIgnoreCase("next")) { // If the user enters 'next'
            try {
                turns = Integer.parseInt(input[1]); // Get the number of turns
            } catch (NumberFormatException notInt) { // If not an int, ask again
                System.out.println("Invalid input. Please enter a number of turns to run.");
                turns = 0;
                newTurn(); // Start a new turn
            } catch (ArrayIndexOutOfBoundsException oob) { // No specified turn number, run one turn
                turns = 1;
            }
            runTurns(turns); // Run the turns
        } else if (input[0].equalsIgnoreCase("auto")) {
            try {
                int repeats = Integer.parseInt(input[1]); // Get the number of turns
                if(repeats < 0){
                    System.out.println("Invalid input. Please enter a positive number.");
                } else {
                    runAuto(repeats);
                }
            } catch (NumberFormatException notInt) {
                System.out.println("Invalid input. Please enter a number of repeating states.");
                newTurn(); // Start a new turn
            } catch (ArrayIndexOutOfBoundsException oob) { // No specified turn number, run one turn
                System.out.println("Invalid input. Please enter a number of repeating states.");
                newTurn(); // Start a new turn
            }
        } else {
            x = parseInt(input, x);
            System.out.println("Now enter the y coordinate of the cell to toggle.");
            input = kb.nextLine().split(" "); // Get the user's input
            y = parseInt(input, y);
            x--; // Convert the coordinates to array indices
            y--;
            cells[y][x].state = !cells[y][x].state; // Toggle the cell's state
            newTurn(); // Start a new turn
        }
    }

    private static int parseInt(String[] input, int coord) {
        try { // Try to parse the input as an integer
            coord = Integer.parseInt(input[0]);
            if (coord > GRID_SIZE || coord < 1) { // Check if the input is within the grid
                System.out.println("Coordinate must be within 1-" + GRID_SIZE);
                newTurn(); // Start a new turn
            }
        } catch (NumberFormatException notInt) { // If it fails, check if the input is "next"
            System.out.println("Invalid input. Please enter a number or 'next (optional number of turns)'.");
            newTurn(); // Start a new turn
        }
        return coord;
    }

    public static void runTurns(int turns){
        for(int i = 0; i < turns; i++){
            update();
            try{
                Thread.sleep(1000 / rate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i+1 < turns) {
                render();
                System.out.println("----------"); // buffer between multiple turns
            }
        }
        newTurn();
    }
    // Automatic mode
    public static void runAuto(int states){
        if(states == 0){
            while(true){
                update();
                render();
                System.out.println("----------");
            }
        } else {
            Cell[][][] gridStates = new Cell[states][GRID_SIZE][GRID_SIZE];
            for (int i = states - 1; i > 0; i--) {
                gridStates[i] = gridStates[i - 1];
            }
            gridStates[0] = cells;
            update();
            try {
                Thread.sleep(1000 / rate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            render();
            System.out.println("----------");
            for (int i = 0; i < states; i++) {
                if (cells == gridStates[i]) {
                    System.out.println("Grid state repeats after " + states + " turns.");
                    break;
                }
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
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.state = cell.newState; // Update the cell's state
            }
        }
    }
    // Render the grid
    public static void render() {
        for (Cell[] row : cells) { // For each row
            for (Cell cell : row) { // For each column
                System.out.print(cell.render() + " "); // Print the rendered cell
            }
            System.out.println(); // Print a new line after each row is complete
        }
    }
}
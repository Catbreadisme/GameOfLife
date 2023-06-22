/**
 * @title Conway's Game of Life
 * @author Luka Scott
 * @version 1.0
 * @started 27/04/2023
 * @repository <a href="https://www.github.com/SkySourced/GameOfLife">Github repo</a>
 *
 * This class represents an individual cell.
 */

package main.java;

import static main.java.Game.*;

public class Cell {
    public final int X; // X position
    public final int Y; // Y position
    public boolean state; // State of the cell (true = alive, false = dead)
    public boolean newState; // State of the cell in the next turn
    public Game game; // Reference to the game object

    public Cell(int x, int y, Game game) { // Constructor
        this.X = x;
        this.Y = y;
        this.state = false; // Default state is dead
        this.game = game;
    }
    public void update() { // Update the cell
        /*
         * 1) Any live cell with fewer than two live neighbours dies, as if by underpopulation.
         * 2) Any live cell with two or three live neighbours lives on to the next generation.
         * 3) Any live cell with more than three live neighbours dies, as if by overpopulation.
         * 4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
         */
        int count = 0; // Number of live neighbors

        // count the number of live neighbors
        if(Y > 0 && X > 0) { // Top left
            if(cells[Y - 1][X - 1].state) {
                count++;
            }
        }
        if(X > 0) { // Top
            if(cells[Y][X - 1].state) {
                count++;
            }
        }
        if(Y < GRID_SIZE - 2 && X > 0) { // Top right
            if(cells[Y + 1][X - 1].state) {
                count++;
            }
        }
        if(Y > 0) { // Left
            if(cells[Y - 1][X].state) {
                count++;
            }
        }
        if(Y < GRID_SIZE - 2) { // Right
            if(cells[Y + 1][X].state) {
                count++;
            }
        }
        if(Y > 0 && X < GRID_SIZE - 2) { // Bottom left
            if(cells[Y - 1][X + 1].state) {
                count++;
            }
        }
        if(X < GRID_SIZE - 2) { // Bottom
            if(cells[Y][X + 1].state) {
                count++;
            }
        }
        if(Y < GRID_SIZE - 2 && X < GRID_SIZE - 2) { // Bottom right
            if(cells[Y + 1][X + 1].state) {
                count++;
            }
        }
        // update newState based on game of life rules
        if(state) { // Cell is alive
            // Underpopulation or overpopulation
            newState = count >= 2 && count <= 3;
        } else { // Cell is dead
            // Reproduction
            newState = count == 3;
        }
    }
    public char render(){ // Render the cell
        if(state) { // Cell is alive
            // Character to represent a live cell
            return '■';
        } else { // Cell is dead
            // Character to represent a dead cell
            return '□';
        }
    }
}
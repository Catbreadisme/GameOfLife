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
    private final int X; // X position
    private final int Y; // Y position
    public boolean state; // State of the cell (true = alive, false = dead)
    public boolean newState; // State of the cell in the next turn
    public Game game; // Reference to the game object
    private String debug; // Debug string

    public Cell(int x, int y, Game game) { // Constructor
        this.X = x;
        this.Y = y;
        this.state = false; // Default state is dead
        this.game = game;
        debug = x + "," + y;
    }
    public void update() { // Update the cell
        /*
         * 1) Any live cell with fewer than two live neighbours dies, as if by underpopulation.
         * 2) Any live cell with two or three live neighbours lives on to the next generation.
         * 3) Any live cell with more than three live neighbours dies, as if by overpopulation.
         * 4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
         */
        int count = 0; // Number of live neighbors

        if(cells[Y][X].state) System.out.println(Y + " " + X);

        if(state) { // Cell is alive
            if (count < 2 || count > 3) { // Cell death (case 1 and 3)
                newState = false;
                debug += "dies";
            }   // If cell survives, no need to change state (case 2)
        } else { // Cell is dead
            if(count == 3) newState = true; // Cell reproduction (case 4)
        }
        //System.out.println(debug);
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
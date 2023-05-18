package main.java;
/*
  Conway's Game of Life
  by Luka Scott started on 27/04/2023
  https://www.github.com/SkySourced/GameOfLife

  This class represents an individual cell.
 */

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

        if(X > 0 && Y > 0 && cells[X -1][Y -1].state){
            count++;
            debug += " tl "; // top left
        }
        if(X > 0 && cells[X -1][Y].state) {
            count++;
            debug += " l "; // left
        }
        if(X > 0 && Y < Game.HEIGHT-1 && cells[X -1][Y +1].state) {
            count++;
            debug += " bl "; // bottom left
        } // bottom left
        if(Y > 0 && cells[X][Y -1].state) {
            count++;
            debug += " t "; // top
        }// top
        if(Y < Game.HEIGHT-1 && cells[X][Y +1].state) {
            count++;
            debug += " b "; // bottom
        } // bottom
        if(X < Game.WIDTH-1 && Y > 0 && cells[X +1][Y -1].state) {
            count++;
            debug += " tr "; // top right
        } // top right
        if(X < Game.WIDTH-1 && cells[X +1][Y].state) {
            count++;
            debug += " r "; // right
        } // right
        if(X < Game.WIDTH-1 && Y < Game.HEIGHT-1 && cells[X +1][Y +1].state) {
            count++;
            debug += " br "; // bottom right
        } // bottom right

        if(state) { // Cell is alive
            if (count < 2 || count > 3) { // Cell death (case 1 and 3)
                newState = false;
                debug += " dies";
            }   // If cell survives, no need to change state (case 2)
        } else { // Cell is dead
            if(count == 3) newState = true; // Cell reproduction (case 4)
        }
        System.out.println(debug);
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
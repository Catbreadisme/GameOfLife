package main.java;

import static main.java.Game.cells;

public class Cell {
    private final int x; // X position
    private final int y; // Y position
    public boolean state; // State of the cell (true = alive, false = dead)
    public Game game; // Reference to the game object
    public Cell(int x, int y, Game game) { // Constructor
        this.x = x;
        this.y = y;
        this.state = false; // Default state is dead
        this.game = game;
    }
    public void update() { // Update the cell
        /*
         * 1. Any live cell with fewer than two live neighbours dies, as if caused by under-population.
         * 2. Any live cell with two or three live neighbours lives on to the next generation.
         * 3. Any live cell with more than three live neighbours dies, as if by overcrowding.
         * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
         */
        int count = 0; // Number of live neighbors

        if(x > 0 && y > 0 && cells[x-1][y-1].state) count++; // top left
        if(x > 0 && cells[x-1][y].state) count++; // left
        if(x > 0 && y < Game.HEIGHT-1 && cells[x-1][y+1].state) count++; // bottom left
        if(y > 0 && cells[x][y-1].state) count++; // top
        if(y < Game.HEIGHT-1 && cells[x][y+1].state) count++; // bottom
        if(x < Game.WIDTH-1 && y > 0 && cells[x+1][y-1].state) count++; // top right
        if(x < Game.WIDTH-1 && cells[x+1][y].state) count++; // right
        if(x < Game.WIDTH-1 && y < Game.HEIGHT-1 && cells[x+1][y+1].state) count++; // bottom right

        if(state) { // Cell is alive
            if (count < 2 || count > 3) { // Cell death (case 1 and 3)
                state = false;
            }   // If cell survives, no need to change state (case 2)
        } else { // Cell is dead
            if(count == 3) state = true; // Cell reproduction (case 4)
        }
    }
    public char render(){ // Render the cell
        if(state) { // Cell is alive
            return '■';
        } else { // Cell is dead
            return '□';
        }
    }
}
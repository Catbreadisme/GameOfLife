public class Cell {
    private int x;
    private int y;
    public boolean state;
    public Game game;
    public Cell(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.state = false;
        this.game = game;
    }
    public void update() {
        /*
         * 1. Any live cell with fewer than two live neighbours dies, as if caused by under-population.
         * 2. Any live cell with two or three live neighbours lives on to the next generation.
         * 3. Any live cell with more than three live neighbours dies, as if by overcrowding.
         * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
         */
        int count = 0; // Number of live neighbors

        if(x > 0 && y > 0 && game.cells[x-1][y-1].state) count++; // top left
        if(x > 0 && game.cells[x-1][y].state) count++; // left
        if(x > 0 && y < Game.HEIGHT-1 && game.cells[x-1][y+1].state) count++; // bottom left
        if(y > 0 && game.cells[x][y-1].state) count++; // top
        if(y < Game.HEIGHT-1 && game.cells[x][y+1].state) count++; // bottom
        if(x < Game.WIDTH-1 && y > 0 && game.cells[x+1][y-1].state) count++; // top right
        if(x < Game.WIDTH-1 && game.cells[x+1][y].state) count++; // right
        if(x < Game.WIDTH-1 && y < Game.HEIGHT-1 && game.cells[x+1][y+1].state) count++; // bottom right

        if(state) { // Cell is alive
            if (count < 2 || count > 3) { // Cell death (case 1 and 3)
                state = false;
            } else {
                state = true; // Cell lives on (case 2)
            }
        } else { // Cell is dead
            if(count == 3) state = true; // Cell reproduction (case 4)
        }
    }
    public char render(){
        if(state) {
            return "■";
        } else {
            return "□";
        }
    }
}

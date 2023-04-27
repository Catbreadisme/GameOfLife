import java.util.Timer;
import java.util.Scanner;

    public class Game {
    public static final int WIDTH = 50; // Width of the grid
    public static final int HEIGHT = 50; // Height of the grid
    public Cell[][] cells; // 2D array of cells
    private int rate; // Rate of updates per second
    private static Keyboard kb; // Keyboard input
    public static void main(String[] args) {
        kb = new Scanner(System.in);
        gridInit();
    }

    private static void gridInit() {
        this.cells = new Cell[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j] = new Cell(i, j, this);
            }
        }
    }

    public void update() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.update();
            }
        }
    }

    public void render() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                System.out.print(cell.render());
            }
            System.out.println();
        }
    }
}
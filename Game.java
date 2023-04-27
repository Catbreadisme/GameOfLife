public class Game {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    private Cell[][] cells;
    public static void main(String[] args) {
        System.out.println("Hello World!");
        gridInit();
    }

    private static void gridInit(){
        this.cells = new Cell[WIDTH][HEIGHT];
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                cells[i][j] = new Cell();
            }
        }
    }
}
package main.java;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(); // Create a new game
        Game.kb = new java.util.Scanner(System.in); // Initialise the keyboard input
        game.gridInit(); // Initialise the grid
    }
}

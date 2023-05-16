package Puzzle_AI_CS;

import java.util.*;

// Main class for running the the eight-puzzle 
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the initial state of the puzzle in row-major order with 0 for blank tile:");
        //Taking the user input for the initail state
        List<Integer> initialState = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            initialState.add(scanner.nextInt());
        }
        scanner.close();

        EightPuzzle eightPuzzle = new EightPuzzle(initialState);

        long startTime, endTime; //using the times to check the time taken for the execution
        

        System.out.println("Using Uniform Cost Search:");
        startTime = System.currentTimeMillis();
        eightPuzzle.solve(new UniformCostSearch());
        endTime = System.currentTimeMillis();
        System.out.println("*************"+"     "+"Time taken: " + (endTime - startTime) + "ms"+"     "+"*************");
        System.out.println();
        System.out.println();
        System.out.println("Using A* with Misplaced Tile heuristic:");
        startTime = System.currentTimeMillis();
        eightPuzzle.solve(new MisplacedTiles());
        endTime = System.currentTimeMillis();
        System.out.println("*************"+"     "+"Time taken: " + (endTime - startTime) + "ms"+"     "+"*************");
        System.out.println();
        System.out.println();
        System.out.println("Using A* with Manhattan Distance heuristic:");
        startTime = System.currentTimeMillis();
        eightPuzzle.solve(new ManhattanDistance());
        endTime = System.currentTimeMillis();
        System.out.println("*************"+"     "+"Time taken: " + (endTime - startTime) + "ms"+"     "+"*************");


    }
}

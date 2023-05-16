package Puzzle_AI_CS;

import java.util.List;

// Heuristic interface for calculating heuristic cost
public interface Heuristic {
    int calculate(List<Integer> state);
}

// Uniform Cost Search heuristic (always returns 0)
class UniformCostSearch implements Heuristic {
    @Override
    public int calculate(List<Integer> state) {
        return 0; //Hard coded to 0 making it equal to Breadth First Search
    }
}

// Misplaced Tiles heuristic which uses the number of misplaced tiles in the initial problem state 
class MisplacedTiles implements Heuristic {
    @Override
    public int calculate(List<Integer> state) {
        int misplacedTiles = 0;
        for (int i = 0; i < 9; i++) {
            if (state.get(i) != 0 && state.get(i) != i + 1) {
                misplacedTiles++;
            }
        }
        return misplacedTiles;
    }
}

// Manhattan Distance heuristic which uses the distance between the misplaced tile and the actual tile
class ManhattanDistance implements Heuristic {
    @Override
    public int calculate(List<Integer> state) {
        int distance = 0;
        for (int i = 0; i < 9; i++) {
            if (state.get(i) != 0) {
                int value = state.get(i) - 1; // Adjusted value for indexing purposes
                int actRow = value / 3; // Calculate the actual row
                int actCol = value % 3; // Calculate the actual column
                int currRow = i / 3; // Calculate the current row
                int currCol = i % 3; // Calculate the current column
                distance += Math.abs(currRow - actRow) + Math.abs(currCol - actCol);
            }
        }
        return distance;
    }
}


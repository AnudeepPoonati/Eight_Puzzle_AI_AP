package Puzzle_AI_CS;

import java.util.*;

public class EightPuzzle {
    private List<Integer> initialState;
    private List<Integer> goalState = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0);  //Solution or Goal State 
    private List<String> actions = Arrays.asList("UP", "DOWN", "LEFT", "RIGHT"); // Actions that can be performed by the tiles

    public EightPuzzle(List<Integer> initialState) {
        this.initialState = initialState;
    }

    public void solve(Heuristic heuristic) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(node -> node.pathCost + heuristic.calculate(node.state)));
        Set<List<Integer>> explored = new HashSet<>();
        int nodesExpanded = 0;
        int maxQueueDepth = 0;

        frontier.add(new Node(initialState, null, "", 0, 0));

        while (!frontier.isEmpty()) { //checks if the frontier is empty or not 
            maxQueueDepth = Math.max(maxQueueDepth, frontier.size());

            Node node = frontier.poll(); // if empty returns null , if not returns head 
            if (goalTest(node.state)) {
                printSolution(node);
                System.out.println("*************"+"     "+"Nodes Expanded: " + nodesExpanded+"     "+"*************");
                System.out.println("*************"+"     "+"Max Queue Depth: " + maxQueueDepth+"     "+"*************");
                return;
            }

            if (!explored.contains(node.state)) {
                explored.add(node.state); //To check for the repeated expanded nodes for removing the duplicates
                nodesExpanded++;

                for (Node child : MoveTile(node)) {
                    if (!explored.contains(child.state) && !frontier.contains(child)) {
                        frontier.add(child);
                    }
                }
            }
        }

        System.out.println("No solution found.");
        System.out.println("*************" + "Nodes Expanded: " + nodesExpanded+"*************");
        System.out.println("*************" + "Max Queue Depth: " + maxQueueDepth+"*************");
    }


// Function to move the tile to achive goal state 
    private List<Node> MoveTile(Node node) {
        List<Node> newlistS = new ArrayList<>();
        int index = node.state.indexOf(0);

        for (String action : actions) {
            List<Integer> newState = new ArrayList<>(node.state);
            switch (action) {
                case "UP":
                    if (index > 2) {
                        Collections.swap(newState, index, index - 3);
                        newlistS.add(new Node(newState, node, action, node.pathCost + 1, node.depth + 1));
                    }
                    break;
                case "DOWN":
                    if (index < 6) {
                        Collections.swap(newState, index, index + 3);
                        newlistS.add(new Node(newState, node, action, node.pathCost + 1, node.depth + 1));
                    }
                    break;
                case "LEFT":
                    if (index % 3 != 0) {
                        Collections.swap(newState, index, index - 1);
                        newlistS.add(new Node(newState, node, action, node.pathCost + 1, node.depth + 1));
                    }
                    break;
                case "RIGHT":
                    if (index % 3 != 2) {
                        Collections.swap(newState, index, index + 1);
                        newlistS.add(new Node(newState, node, action, node.pathCost + 1, node.depth + 1));
                    }
                    break;
            }
        }

        return newlistS;
    }
    //To check whether we have reached goal state or not.
    private boolean goalTest(List<Integer> state) {
        return state.equals(goalState);
    }

    private void printSolution(Node node) {
        List<Node> path = new ArrayList<>();
        int moves = -1;
        while (node != null) {
            path.add(node);
            node = node.parent;
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            Node currentNode = path.get(i);
            moves++;
            System.out.println("Depth: " + currentNode.depth + " Action: " + currentNode.action +"State : "+currentNode.state);
            printPuzzleState(currentNode.state);
        }
        System.out.println("*************"+"     "+"Total Moves : "+moves+"     "+"*************");
    }

    private void printPuzzleState(List<Integer> state) {
        System.out.println("+---+---+---+");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = state.get(i * 3 + j);
                if (value == 0) {
                    System.out.print("| - ");
                } else {
                    System.out.print("| " + value + " ");
                }
            }
            System.out.println("|");
            System.out.println("+---+---+---+");
        }
        System.out.println();
    }
}
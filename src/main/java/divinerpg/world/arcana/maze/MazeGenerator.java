package divinerpg.world.arcana.maze;

import java.util.*;

/**
 * This program generates mazes and solves them using Breadth-first Search and
 * Depth-first Search algorithms
 * <p>
 * https://github.com/nattwasm/maze
 *
 * @author Nhat Nguyen
 * @author Jasmine Mai
 */
public class MazeGenerator {
    /**
     * Creates an empty non-generated maze
     *
     * @param size The size of the maze
     * @return maze2D The empty non-generated maze
     */
    public static String[][] maze2D(int size) {
        String[][] maze2D = new String[2 * size + 1][2 * size + 1];
        // 2D Array
        for (int columnIndex = 0; columnIndex < (2 * size + 1); columnIndex++) {
            for (int rowIndex = 0; rowIndex < (2 * size + 1); rowIndex++) {
                // Start of maze
                if (rowIndex == 1 && columnIndex == 0) {
                    maze2D[columnIndex][rowIndex] = "S";
                    // End of maze
                } else if (rowIndex == 2 * size - 1 && columnIndex == 2 * size) {
                    maze2D[columnIndex][rowIndex] = "E";
                    // Row is even
                } else if (rowIndex % 2 == 0) {
                    // Column is even
                    if (columnIndex % 2 == 0) {
                        maze2D[columnIndex][rowIndex] = "+";
                        // Column is odd
                    } else {
                        maze2D[columnIndex][rowIndex] = "|";
                    }
                    // Row is odd
                } else {
                    // Column is even
                    if (columnIndex % 2 == 0) {
                        maze2D[columnIndex][rowIndex] = "-";
                        // Column is odd
                    } else {
                        maze2D[columnIndex][rowIndex] = "0";
                    }
                }
            }
        }

        return maze2D;
    }

    public static Cell generateMap(Random r, int size) {
        String[][] maze = MazeGenerator.maze2D(size);
        maze = generator(maze, r);

        System.out.println(convert2D(maze));

        Cell result = new Cell(0, 0, maze);
        Cell current = result;

        for (int x = 0; x < maze.length / 2; x++) {
            for (int y = 0; y < maze[x].length / 2; y++) {
                if (x == 0 && y == 0)
                    continue;

                Cell cell = new Cell(x, y, maze);

                current.setNext(cell);
                current = cell;
            }
        }

        return result;
    }

    /**
     * Creates a valid generated maze that has a path from the begining to end
     *
     * @param maze2D The empty non-generated maze
     * @return maze2D The empty generated maze
     */
    private static String[][] generator(String[][] maze2D, Random r) {
        Stack<Cell> location = new Stack<Cell>();
        int size = (maze2D.length - 1) / 2;
        int totalCells = size * size;
        int visitedCells = 1;
        Cell current = new Cell(0, 0);

        while (visitedCells < totalCells) {

            // Generates a unique direction
            ArrayList<String> direction = new ArrayList<>();
            Collections.addAll(direction, "NORTH", "EAST", "SOUTH", "WEST");
            Collections.shuffle(direction, r);

            String random = validSpot(maze2D, current, direction);

            if (random == "BACKTRACK") {
                // // DEBUGGING: Prints BACKTRACKING
                // System.out.println("\t PROCESSS: " + random);

                current = location.pop();
                continue;
            }

            current = move(maze2D, current, random);
            visitedCells = visitedCells + 1;
            location.push(current);
        }

        return maze2D;
    }

    /**
     * The valid spot returns all the valid spot given a cell location
     *
     * @param maze2D    The maze from maze2D method
     * @param current   The current located cell
     * @param direction The list of directions
     * @return random The valid random direction
     */
    private static String validSpot(String[][] maze2D, Cell current, ArrayList<String> direction) {
        int size = (maze2D.length - 1) / 2;

        int x = 2 * current.getx() + 1;
        int y = 2 * current.gety() + 1;

        // When the size of the list is 0, return -1
        if (direction.size() == 0) {
            return "BACKTRACK";
        }

        String random = direction.remove(0);

        // // DEBUGGING: Prints current direction
        // System.out.println("DIRECTION: " + random);

        if (random == "NORTH") {
            if (current.gety() - 1 < 0) {
                // System.out.println("Do not go NORTH because outside of range of the 2D
                // array");
                return validSpot(maze2D, current, direction);
            }
            if ((maze2D[y - 3][x] == "#" || maze2D[y - 1][x] == "#")
                    || (maze2D[y - 2][x - 1] == "#" || maze2D[y - 2][x + 1] == "#")) {
                // System.out.println("Do not go NORTH because that cell is not enclosed by
                // walls");
                return validSpot(maze2D, current, direction);
            }
        } else if (random == "EAST") {
            if (current.getx() + 1 >= size) {
                // System.out.println("Do not go EAST because outside of range of the 2D
                // array");
                return validSpot(maze2D, current, direction);
            }
            if (((maze2D[y + 1][x + 2] == "#" || maze2D[y - 1][x + 2] == "#")
                    || (maze2D[y][x + 1] == "#" || maze2D[y][x + 3] == "#"))) {
                // System.out.println("Do not go EAST because that cell is not enclosed by
                // walls");
                return validSpot(maze2D, current, direction);
            }
        } else if (random == "SOUTH") {
            if (current.gety() + 1 >= size) {
                // System.out.println("Do not go SOUTH because outside of range of the 2D
                // array");
                return validSpot(maze2D, current, direction);
            }
            if (((maze2D[y + 1][x] == "#" || maze2D[y + 3][x] == "#")
                    || (maze2D[y + 2][x - 1] == "#" || maze2D[y + 2][x + 1] == "#"))) {
                // System.out.println("Do not go SOUTH because that cell is not enclosed by
                // walls");
                return validSpot(maze2D, current, direction);
            }
        } else if (random == "WEST") {
            if (current.getx() - 1 < 0) {
                // System.out.println("Do not go WEST because outside of range of the 2D
                // array");
                return validSpot(maze2D, current, direction);
            }
            if (((maze2D[y - 1][x - 2] == "#" || maze2D[y + 1][x - 2] == "#")
                    || (maze2D[y][x - 3] == "#" || maze2D[y][x - 1] == "#"))) {
                // System.out.println("Do not go WEST because that cell is not enclosed by
                // walls");
                return validSpot(maze2D, current, direction);
            }
        }
        return random;
    }

    /**
     * Move the next cell and break the wall in between
     *
     * @param maze2D  The maze from the maze2D
     * @param current The current located cell
     * @param random  The valid random direction
     * @return current The new current cell
     */
    private static Cell move(String[][] maze2D, Cell current, String random) {

        // // Prints out the coordinates of the current cell object
        // System.out.println(" X-coordinate: " + current.getx() + ", Y-coordinate: " +
        // current.gety());

        maze2D[1][1] = "#";

        if (random == "NORTH") {
            // NORTH and delete wall from bottom from next cell
            current.setNext(new Cell(current.getx(), current.gety() - 1));
            current = current.getNext();
            // Breaks the bottom wall from next cell
            maze2D[2 * current.gety() + 2][2 * current.getx() + 1] = "#";

            // DEBUGGING: Visualizing
            maze2D[2 * current.gety() + 1][2 * current.getx() + 1] = "#";
        } else if (random == "EAST") {
            // EAST and delete wall from left from next cell
            current.setNext(new Cell(current.getx() + 1, current.gety()));
            current = current.getNext();
            // Breaks the left wall from next cell
            maze2D[2 * current.gety() + 1][2 * current.getx()] = "#";

            // DEBUGGING: Visualizing
            maze2D[2 * current.gety() + 1][2 * current.getx() + 1] = "#";
        } else if (random == "SOUTH") {
            // SOUTH and delete wall from top from next cell
            current.setNext(new Cell(current.getx(), current.gety() + 1));
            current = current.getNext();
            // Breaks the top wall from next cell
            maze2D[2 * current.gety()][2 * current.getx() + 1] = "#";

            // DEBUGGING: Visualizing
            maze2D[2 * current.gety() + 1][2 * current.getx() + 1] = "#";
        } else if (random == "WEST") {
            // WEST and delete wall from right from next cell
            current.setNext(new Cell(current.getx() - 1, current.gety()));
            current = current.getNext();
            // Breaks the right wall from next cell
            maze2D[2 * current.gety() + 1][2 * current.getx() + 2] = "#";

            // DEBUGGING: Visualizing
            maze2D[2 * current.gety() + 1][2 * current.getx() + 1] = "#";
        }

        // // DEBUGGING: Printing maze at each step
        // System.out.println("NEW X-coordinate: " + current.getx() + ", NEW
        // Y-coordinate: " + current.gety());
        // for (String[] row : maze2D) {
        // System.out.println(Arrays.toString(row));
        // }
        // System.out.println();

        return current;
    }

    /**
     * Converts 2D array maze to the string representation
     *
     * @param maze2D The maze that will be convert to string representation
     * @return maze The string representation of the maze
     */
    private static String convert2D(String[][] maze2D) {
        String maze = "";
        int size = maze2D.length;
        for (int columnIndex = 0; columnIndex < size; columnIndex++) {
            for (int rowIndex = 0; rowIndex < size; rowIndex++) {
                if (maze2D[columnIndex][rowIndex] == "+") {
                    maze = maze + "+";
                } else if (maze2D[columnIndex][rowIndex] == "-") {
                    maze = maze + "---";
                } else if (maze2D[columnIndex][rowIndex] == "|") {
                    maze = maze + "|";
                } else if (maze2D[columnIndex][rowIndex] == "#" && columnIndex % 2 == 1) {
                    // Hash symbol and column is odd
                    if (rowIndex % 2 == 0) {
                        maze = maze + " ";
                    } else if (rowIndex % 2 == 1) {
                        maze = maze + "   ";
                    }
                } else if (maze2D[columnIndex][rowIndex] == "#" && columnIndex % 2 == 0) {
                    // Hash symbol and column is even
                    maze = maze + "   ";
                } else if (maze2D[columnIndex][rowIndex] == "S" || maze2D[columnIndex][rowIndex] == "E") {
                    maze = maze + "   ";
                } else if (maze2D[columnIndex][rowIndex] == " " && columnIndex % 2 == 1 && rowIndex % 2 == 0) {
                    // Spacing for the wall
                    maze = maze + " ";
                } else if (maze2D[columnIndex][rowIndex] == " ") {
                    // Spacing for the cell
                    maze = maze + "   ";
                } else {
                    maze = maze + " " + maze2D[columnIndex][rowIndex] + " ";
                }

                // When rowIndex is at end AND columnIndex is not at end, add a new line
                if (rowIndex == (size - 1) && columnIndex != (size - 1)) {
                    maze = maze + System.lineSeparator();
                }
            }
        }
        return maze;
    }

    /**
     * Main method for running the program and displaying to the console
     */
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------------------");

        Scanner scan = new Scanner(System.in);
        int size = 0;

        do {
            System.out.print("Input the size for the maze: ");
            while (!scan.hasNextInt()) {
                // Repeat message when bad input
                System.out.println("Needs a valid integer for maze size (3 or Higher)");
                System.out.print("Input the size for the maze: ");
                scan.next();
            }
            size = scan.nextInt();
        } while (size <= 2);

        Cell cell = generateMap(new Random(), 8);
    }
}

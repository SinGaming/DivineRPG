package divinerpg.world.arcana.maze;

import net.minecraft.util.Direction;
import net.minecraft.util.math.ChunkPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cell {
    private final List<String> pathValues = Arrays.asList("#", "S", "E");
    private final List<Direction> entries = new ArrayList<>();

    private Cell node;
    private int x;
    private int y;

    /**
     * Constructor has the location of each Cell
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor has the location of each Cell
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public Cell(int x, int y, String[][] maze) {
        this(x, y);

        initEntries(maze);
    }

    private void initEntries(String[][] maze) {
        int xPos = x * 2 + 1;
        int yPos = y * 2 + 1;

        if (getPathAsBool(maze[xPos - 1][yPos])) {
            entries.add(Direction.NORTH);
        }

        if (getPathAsBool(maze[xPos + 1][yPos])) {
            entries.add(Direction.SOUTH);
        }

        if (getPathAsBool(maze[xPos][yPos + 1])) {
            entries.add(Direction.WEST);
        }

        if (getPathAsBool(maze[xPos][yPos - 1])) {
            entries.add(Direction.EAST);
        }
    }

    /**
     * Gets the x-coordinate
     *
     * @return x The x-coordinate
     */
    public int getx() {
        return x;
    }

    /**
     * Gets the y-coordinate
     *
     * @return y The y-coordinate
     */
    public int gety() {
        return y;
    }

    /**
     * Gets the next node
     *
     * @return node The next node
     */
    public Cell getNext() {
        return node;
    }

    /**
     * Sets the next node
     *
     * @param node The next node being set
     */
    public void setNext(Cell node) {
        this.node = node;
    }

    public Cell move(int x, int y) {
        this.x += x;
        this.y += y;

        if (node != null) {
            node.move(x, y);
        }

        return this;
    }

    /**
     * Gets needed entries sides
     *
     * @return
     */
    public List<Direction> getEntries() {
        return entries;
    }

    private boolean getPathAsBool(String raw) {
        return getPathAsInt(raw) == 1;
    }

    private int getPathAsInt(String raw) {
        return pathValues.contains(raw)
                ? 1
                : 0;
    }

    /**
     * String Representation of the Cell class
     */
    public String toString() {
        return "[" + x + ":" + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            Cell cell = (Cell) obj;

            return getx() == cell.getx() && gety() == cell.gety();
        }

        return false;
    }

    public ChunkPos getPos() {
        return new ChunkPos(getx(), gety());
    }
}

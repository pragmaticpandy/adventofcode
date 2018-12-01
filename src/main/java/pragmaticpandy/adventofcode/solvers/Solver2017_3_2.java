package pragmaticpandy.adventofcode.solvers;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * TODO: This uses a grid to hold the spiral, which is pretty bad for space. It shoud be done with a
 * more graph-like approach since you only need to keep the last couple rings in memory.
 */
public class Solver2017_3_2 implements Solver {

    public String solve(@NonNull final String input) {
        final int target = Integer.parseInt(input);

        // Square root of target should be more than big enough.
        final int gridHeight = (int)Math.sqrt(target);
        final int[][] grid = new int[gridHeight][gridHeight];

        /*
         * Because the problem defines the spiral as a counter-clockwise one, where the first move
         * is to the right, we make our starting state's direction down, because a counter-clockwise
         * spiral that is currently moving down will attempt to go right if it can.
         */
        State state = new State(gridHeight / 2, gridHeight / 2, Direction.DOWN, grid);
        grid[state.getX()][state.getY()] = 1;
        
        int sum = 1;
        while (sum < target) {
            state = state.nextState();
            final int x = state.getX();
            final int y = state.getY();
            sum = grid[x-1][y+1]
                + grid[x][y+1]
                + grid[x+1][y+1]
                + grid[x+1][y]
                + grid[x+1][y-1]
                + grid[x][y-1]
                + grid[x-1][y-1]
                + grid[x-1][y];

            grid[x][y] = sum;
        }

        return String.format("%d", sum);
    }

    /**
     * The state for our spiral comprises the current position, and the last direction traveled.
     */
    @RequiredArgsConstructor
    @ToString
    private static class State {
        @Getter private final int x;
        @Getter private final int y;
        private final Direction direction;
        private final int[][] grid;

        /**
         * Based on my state and the empty spots on the grid, determine the next state to continue
         * the spiral.
         */
        public State nextState() {
            if (direction.equals(Direction.DOWN)) {
                if (grid[x+1][y] == 0) {
                    return new State(x+1, y, Direction.RIGHT, grid);
                } else {
                    return new State(x, y-1, direction, grid);
                }
            } else if (direction.equals(Direction.RIGHT)) {
                if (grid[x][y+1] == 0) {
                    return new State(x, y+1, Direction.UP, grid);
                } else {
                    return new State(x+1, y, direction, grid);
                }
            } else if (direction.equals(Direction.UP)) {
                if (grid[x-1][y] == 0) {
                    return new State(x-1, y, Direction.LEFT, grid);
                } else {
                    return new State(x, y+1, direction, grid);
                }
            }

            // left
            if (grid[x][y-1] == 0) {
                return new State(x, y-1, Direction.DOWN, grid);
            } else {
                return new State(x-1, y, direction, grid);
            }
        }
    }

    private static enum Direction {
        DOWN,
        RIGHT,
        UP,
        LEFT;
    }
}

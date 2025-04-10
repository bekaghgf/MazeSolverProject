import java.util.*;

public class MazeSolverProject {
    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final char VISITED = '.';

    private static char[][] maze;
    private static boolean[][] visited;
    private static int rows, cols;
    private static int[] start, exit;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of rows (min. 10): ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns (min. 10): ");
        int cols = scanner.nextInt();
        System.out.println("Sizes: " + rows + " x " + cols);

    }
    private static void generateMaze() {
        for (int i = 0; i < rows; i++)
            Arrays.fill(maze[i], WALL);

        maze[start[0]][start[1]] = PATH;
        Random rand = new Random();
        Stack<int[]> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int[] current = stack.peek();
            List<int[]> neighbors = getUnvisitedNeighbors(current);

            if (!neighbors.isEmpty()) {
                int[] next = neighbors.get(rand.nextInt(neighbors.size()));
                int wallX = (current[0] + next[0]) / 2;
                int wallY = (current[1] + next[1]) / 2;
                maze[wallX][wallY] = PATH;
                maze[next[0]][next[1]] = PATH;
                stack.push(next);
            } else {
                stack.pop();
            }
        }
    }

    private static List<int[]> getUnvisitedNeighbors(int[] cell) {
        List<int[]> neighbors = new ArrayList<>();
        int[][] dirs = {{2, 0}, {-2, 0}, {0, 2}, {0, -2}};

        for (int[] d : dirs) {
            int x = cell[0] + d[0];
            int y = cell[1] + d[1];
            if (isInBounds(x, y) && maze[x][y] == WALL) {
                neighbors.add(new int[]{x, y});
            }
        }
        return neighbors;
    }
    private static boolean isInBounds(int x, int y) {
        return x > 0 && y > 0 && x < rows - 1 && y < cols - 1;
    }

    private static boolean findPath(int x, int y) { return false; }
    private static void printMaze(boolean[][] visitedMap) {}

}

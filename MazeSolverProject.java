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
    private static void generateMaze() {}
    private static boolean findPath(int x, int y) { return false; }
    private static void printMaze(boolean[][] visitedMap) {}

}

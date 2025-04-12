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
        rows = scanner.nextInt();
        System.out.print("Enter number of columns (min. 10): ");
        cols = scanner.nextInt();

        if (rows < 10 || cols < 10) {
            System.out.println("The size of the maze must be at least 10x10.");
            return;
        }

        if (rows % 2 == 0) rows++;
        if (cols % 2 == 0) cols++;

        start = new int[]{1, 1};
        exit = new int[]{rows - 2, cols - 2};

        maze = new char[rows][cols];
        visited = new boolean[rows][cols];


    }



    private static void generateMaze() {
        //The method of generating a maze using the DFS algorithm
        for (int i = 0; i < rows; i++)
            Arrays.fill(maze[i], WALL); //We fill the whole maze with walls

        maze[start[0]][start[1]] = PATH;//The initial cell becomes a passage

        Random rand = new Random();
        Stack<int[]> stack = new Stack<>();
        stack.push(start);
        //We create a random generator and a stack for the generation algorithm

        while (!stack.isEmpty()) //As long as there is room to move, we continue to generate
            {
            int[] current = stack.peek();
            List<int[]> neighbors = getUnvisitedNeighbors(current);
            //We take the current cell and find the neighbors into which we can "dig" (through 2 cells)

            if (!neighbors.isEmpty()) {
                int[] next = neighbors.get(rand.nextInt(neighbors.size())); //If there are options, choose a random neighbor

                int wallX = (current[0] + next[0]) / 2;
                int wallY = (current[1] + next[1]) / 2;
                maze[wallX][wallY] = PATH;
                maze[next[0]][next[1]] = PATH;
                stack.push(next); //Remove the wall between the current and the next cell and move forward

            } else { //If there's nowhere to go, we roll back
                stack.pop();
            }

        }
        maze[exit[0]][exit[1]] = PATH; //EXIT

    }

    private static List<int[]> getUnvisitedNeighbors(int[] cell) {
        //Returns a list of neighbors that can be reached (moving through one wall)

        List<int[]> neighbors = new ArrayList<>();
        int[][] dirs = {{2, 0}, {-2, 0}, {0, 2}, {0, -2}};  // Possible directions of movement (up, down, right, left – through 2 cells)
        for (int[] d : dirs) {
            int x = cell[0] + d[0];
            int y = cell[1] + d[1];
            if (isInBounds(x, y) && maze[x][y] == WALL) {
                neighbors.add(new int[]{x, y});
            }
        }
        return neighbors;  //We check whether the neighbors are within the limits and have not yet been visited – we add them to the list
    }

    private static boolean findPath(int x, int y) {
        //Recursive search for a path from point x,y to the exit
        if (!isInBounds(x, y) || maze[x][y] != PATH || visited[x][y]) return false;
        visited[x][y] = true;

        if (x == exit[0] && y == exit[1]) {
            maze[x][y] = VISITED;
            return true; //If you have reached the exit, the path is found
        }

        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] d : dirs) {  //We're trying to move in every direction
            if (findPath(x + d[0], y + d[1])) {
                maze[x][y] = VISITED;
                return true;
            }
        }

        return false;
    }


    private static void printMaze(boolean[][] visitedMap) { //Maze printing method
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visitedMap != null && maze[i][j] == VISITED) {
                    System.out.print(VISITED);
                } else {
                    System.out.print(maze[i][j]);
                }
            }
            System.out.println(); //We go through each cell and print either a wall, a path, or a point, if it has been visited
        }
    }

    private static boolean isInBounds(int x, int y) { //Checking whether the cell is within the bounds, not counting the boundaries
        return x > 0 && y > 0 && x < rows - 1 && y < cols - 1;
    }
}

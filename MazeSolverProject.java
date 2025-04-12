import java.util.*;

public class MazeSolverProject {
    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final char VISITED = '.';

    private static char[][] maze;
    private static boolean[][] visited;
    private static int rows, columns;
    private static int[] startPosition, exitPosition;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of rows (min. 10): ");
        rows = scanner.nextInt();
        System.out.print("Enter number of columns (min. 10): ");
        columns = scanner.nextInt();

        if (rows < 10 || columns < 10) {
            System.out.println("The size of the maze must be at least 10x10.");
            return;
        }

        if (rows % 2 == 0) rows++;
        if (columns % 2 == 0) columns++;

        startPosition = new int[]{1, 1};
        exitPosition = new int[]{rows - 2, columns - 2};

        maze = new char[rows][columns];
        visited = new boolean[rows][columns];


    }



    private static void generateMaze() {
        //The method of generating a maze using the DFS algorithm
        for (int i = 0; i < rows; i++)
            Arrays.fill(maze[i], WALL); //We fill the whole maze with walls

        maze[startPosition[0]][startPosition[1]] = PATH;//The initial cell becomes a passage

        Random rand = new Random();
        Stack<int[]> cellStack = new Stack<>();
        cellStack.push(startPosition);
        //We create a random generator and a stack for the generation algorithm

        while (!cellStack.isEmpty()) //As long as there is room to move, we continue to generate
            {
            int[] currentCell = cellStack.peek();
            List<int[]> unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
            //We take the current cell and find the neighbors into which we can "dig" (through 2 cells)

            if (!unvisitedNeighbors.isEmpty()) {
                int[] nextCell = unvisitedNeighbors.get(rand.nextInt(unvisitedNeighbors.size())); //If there are options, choose a random neighbor

                int wallRow = (currentCell[0] + nextCell[0]) / 2;
                int wallCol = (currentCell[1] + nextCell[1]) / 2;
                maze[wallRow][wallCol] = PATH;
                maze[nextCell[0]][nextCell[1]] = PATH;
                cellStack.push(nextCell); //Remove the wall between the current and the next cell and move forward

            } else { //If there's nowhere to go, we roll back
                cellStack.pop();
            }

        }
        maze[exitPosition[0]][exitPosition[1]] = PATH; //EXIT

    }

    private static List<int[]> getUnvisitedNeighbors(int[] cell) {
        //Returns a list of neighbors that can be reached (moving through one wall)

        List<int[]> neighbors = new ArrayList<>();
        int[][] directions = {{2, 0}, {-2, 0}, {0, 2}, {0, -2}};  // Possible directions of movement (up, down, right, left – through 2 cells)
        for (int[] direction : directions) {
            int neighborRow = cell[0] + direction[0];
            int neighborCol = cell[1] + direction[1];
            if (isInBounds(neighborRow, neighborCol) && maze[neighborRow][neighborCol] == WALL) {
                neighbors.add(new int[]{neighborRow, neighborCol});
            }
        }
        return neighbors;  //We check whether the neighbors are within the limits and have not yet been visited – we add them to the list
    }

    private static boolean findPath(int row, int col) {
        //Recursive search for a path from point x,y to the exit
        if (!isInBounds(row, col) || maze[row][col] != PATH || visited[row][col]) return false;
        visited[row][col] = true;

        if (row == exitPosition[0] && col == exitPosition[1]) {
            maze[row][col] = VISITED;
            return true; //If you have reached the exit, the path is found
        }

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] direction : directions) {//We're trying to move in every direction
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if (findPath(nextRow, nextCol)) {
                maze[row][col] = VISITED;
                return true;
            }
        }

        return false;
    }


    private static void printMaze(boolean[][] visitedMap) { //Maze printing method
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
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
        return x > 0 && y > 0 && x < rows - 1 && y < columns - 1;
    }
}

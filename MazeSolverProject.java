import java.util.*;

public class MazeSolverProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of rows (min. 10): ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns (min. 10): ");
        int cols = scanner.nextInt();
        System.out.println("Sizes: " + rows + " x " + cols);
    }
}

import java.util.*;

public class DiagonalDifference {
    public static void main(String[] args) {
        var scan = new Scanner(System.in);

        // The first integer we receive from stdin is
        // the size of the square matrix. Since a square
        // matrix has the same amount of rows and columns
        // we can describe its size with a single integer n
        int n = scan.nextInt();

        int[][] arr = new int[n][n];

        // We use a nested for loop to fill
        // the entire 2D array with the values
        // from stdin
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = scan.nextInt();

        int diagonalSum1 = 0;
        int diagonalSum2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    diagonalSum1 += arr[i][j];
                if (i == n - j - 1)
                    diagonalSum2 += arr[i][j];
            }
        }

        System.out.println(Math.abs(diagonalSum1 - diagonalSum2));
    }
}

import java.util.Scanner;

public class Sample2 {

    public static long[][] powerMatrix(long[][] matrix, int power) {
        long[][] result = matrix;
        for (int i = 0; i < power; i++) {
            result = multiplyMatrix(result, result);
        }
        return result;
    }

    public static long[][] multiplyMatrix(long[][] matrix1, long[][] matrix2) {
        int m = matrix1.length;
        int n = matrix2[0].length;
        long[][] result = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long sum = 0;
                for (int k = 0; k < matrix1[0].length; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        long[][] matrix = {{2, 4}, {6, 8}};
        Scanner scanner = new Scanner(System.in);
        int power = scanner.nextInt();
        long[][] result = powerMatrix(matrix, power);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}

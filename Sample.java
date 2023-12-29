import java.lang.Math;

public class Sample {
    public static long[][] powerMatrix(long[][] matrix) {
        long[][] result = matrix;
        for (int i = 0; i < Math.pow(2, 30) - 1; i++) {
            result = multiplyMatrix(result, matrix);
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
        long[][] result = powerMatrix(matrix);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}

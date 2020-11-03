public class MatrixService {

    public static boolean magicSquare(int[][] mat) {
        int N = mat.length;

        // calculate the sum of
        // the prime diagonal
        int sum = 0;
        for (int i = 0; i < N; i++)
            sum = sum + mat[i][i];

        // For sums of Rows
        for (int i = 0; i < N; i++) {

            int rowSum = 0;
            for (int j = 0; j < N; j++)
                rowSum += mat[i][j];

            // check if every row sum is
            // equal to prime diagonal sum
            if (rowSum != sum)
                return false;
        }

        // For sums of Columns
        for (int i = 0; i < N; i++) {

            int colSum = 0;
            for (int j = 0; j < N; j++)
                colSum += mat[j][i];

            // check if every column sum is
            // equal to prime diagonal sum
            if (sum != colSum)
                return false;
        }

        return true;
    }
}
package src.PPClean.Similarity;

import java.util.Arrays;

/**
 * Levenshtein String similarity
 */
public class Levenshtein implements StringSimilarity {
    public Levenshtein() {
    }

    /**
     * Calculates Levenshtein String similarity for x and y
     * @param x
     * @param y
     * @return Similarity score in range [0,1]
     */
    @Override
    public double compare(String x, String y) {
        double res = 0;
        int m = x.length();
        int n = y.length();
        // BEGIN SOLUTION

        // 1. Schritt
        int[][] arr = new int[m+1][n+1];

        // 2. Schritt
        for (int i = 0; i < m+1; i++) {
            arr[i][0] = i;
        }
        for (int j = 0; j < n+1; j++) {
            arr[0][j] = j;
        }

        // 3. Schritt // ohne Rekursion
        if (m == 0 && n == 0) {
            return 0;
        } else if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (x.charAt(i-1) == y.charAt(j-1)) {
                    arr[i][j] = arr[i-1][j-1];
                } else {
                    int min = Math.min(arr[i-1][j], arr[i][j-1]);
                    min = Math.min(min, arr[i-1][j-1]);
                    arr[i][j] = 1 + min;
                }
            }
        }

        // 4. Schritt
        res = arr[m][n];

        // Überführung
        res = 1 - (res/Math.max(m,n));

        // END SOLUTION
        return res;
    }
}
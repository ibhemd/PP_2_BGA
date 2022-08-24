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
        for (int i = 0; i < m; i++) {
            arr[i][0] = i;
        }
        for (int j = 0; j < n; j++) {
            arr[0][j] = j;
        }

        // 3. Schritt
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (m == 0 && n == 0) {
                    return  0;
                } else if (n == 0) {
                    return m;
                } else if (m == 0) {
                    return n;
                } else if (x.charAt(i) == y.charAt(j)) {
                    arr[i][j] = (int) compare(x.substring(0, m-1), y.substring(0, n-1));
                } else {
                    double z1 = compare(x.substring(0,m-1),y.substring(0,n-1));
                    double z2 = compare(x.substring(0,m-1),y);
                    double z3 = compare(x,y.substring(0,n-1));
                    double z = Math.min(z1, z2);
                    return (int) (1+Math.min(z, z3));
                }
            }
        }

        // 4. Schritt
        res = (double) arr[m-1][n-1];

        // END SOLUTION
        return res;
    }
}

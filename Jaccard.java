package src.PPClean.Similarity;

import java.util.HashSet;
import java.util.Set;

/**
 * Jaccard String similarity
 */
public class Jaccard implements StringSimilarity {

    int n;

    /**
     * @param n Length of substrings
     */
    public Jaccard(int n) {
        this.n = n;
    }

    /**
     * Calculates Jaccard String similarity for x and y, using ngrams of length {@link #n}
     * @param x
     * @param y
     * @return Similarity score in range [0,1]
     */
    @Override
    public double compare(String x, String y) {
        double res = 0;
        Set<String> ngramsX = new HashSet<>();
        Set<String> ngramsY = new HashSet<>();
        // BEGIN SOLUTION
        int i = 0;
        while (i < x.length()-n+1) {
            ngramsX.add(x.substring(i,i+n));
            i += 1;
        }
        int j = 0;
        while (j < y.length()-n+1) {
            ngramsY.add(y.substring(j,j+n));
            j += 1;
        }
        int sizex = ngramsX.size();
        int sizey = ngramsY.size();
        ngramsX.retainAll(ngramsY);
        int sizexy = ngramsX.size();
        res = (double) sizexy / (double) (sizey+sizex-sizexy);
        // END SOLUTION
        return res;
    }
}

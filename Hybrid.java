package src.PPClean.Similarity;

import src.PPClean.Data.Record;

import java.util.List;

/**
 * Record similarity for comparing two Records attribute by attribute either with {@link Levenshtein} or {@link Jaccard}
 */
public class Hybrid implements RecordSimilarity {

    private List<String> policies;
    private final int JACCARD_N = 3;
    private StringSimilarity jaccard;
    private StringSimilarity levenshtein;

    /**
     * @param policies List of comparison policies, write "L" for {@link Levenshtein},
     *                 "J" {@link Jaccard}, and null to skip an attribute.
     *                 The policies are applied in order of attributes (e.g., first policy to first attribute)
     */
    public Hybrid(List<String> policies) {
        this.policies = policies;
        this.jaccard = new Jaccard(JACCARD_N);
        this.levenshtein = new Levenshtein();
    }

    /**
     * Compares two Records attribute by attribute according to {@link #policies}.
     * For Jaccard similarity, a default window size of {@link #JACCARD_N} is used
     * @param r1
     * @param r2
     * @return Similarity score in range [0,1] (1=same, 0=very different)
     */
    @Override
    public double compare(Record r1, Record r2) {
        double res = 0;
        // BEGIN SOLUTION
        double c = 0;
        for (int i = 0; i < policies.size(); i++) {
            String s = policies.get(i);
            if (s != null) {
                String s1 = r1.getContent().get(i);
                String s2 = r2.getContent().get(i);
                if (s.equals("J")) {
                    res += this.jaccard.compare(s1, s2);
                }
                if (s.equals("L")) {
                    res += this.levenshtein.compare(s1, s2);
                }
                c += 1;
            }
        }

        if (c != 0) {
            res = res/c;
        }
        // END SOLUTION
        return res;
    }
}


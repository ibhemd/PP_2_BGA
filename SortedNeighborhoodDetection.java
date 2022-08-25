package src.PPClean.DuplicateDetection;

import src.PPClean.Data.Duplicate;
import src.PPClean.Data.Record;
import src.PPClean.Data.Table;
import src.PPClean.Similarity.RecordSimilarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Duplicate detection algorithm which first sorts the Table (according to a key)
 * and then only compares neighboring Records
 */
public class SortedNeighborhoodDetection implements DuplicateDetection {

    double threshold;
    int windowSize;
    int[] keyComponents;

    /**
     * @param threshold Threshold in range [0,1], Records at least this similar are considered Duplicates
     * @param windowSize Each record is compared with 2*(windowSize-1) neighboring Records
     * @param keyComponents Each component indicates how many characters of the content
     *                      value at the same list position are integrated in the key
     *                      Enter 0 to omit a Record value
     */
    public SortedNeighborhoodDetection(double threshold, int windowSize, int[] keyComponents) {
        this.threshold = threshold;
        this.windowSize = windowSize;
        this.keyComponents = keyComponents;
    }

    /**
     * @param table Table to check for duplicates
     * @param recSim Similarity measure to use for comparing two records
     * @return Set of detected duplicates
     */
    @Override
    public Set<Duplicate> detect(Table table, RecordSimilarity recSim) {
        Set<Duplicate> duplicates = new HashSet<>();
        int numComparisons = 0;
        // BEGIN SOLUTION

        // 1. Schritt
        table.generateKeys(this.keyComponents);

        // 2. Schritt
        table.sortByKey();

        // 3. Schritt
        int s = table.getData().size();
        for (int i = 0; i < s-windowSize; i++) {
            for (int j = 0; j < windowSize; j++) {
                Record r1 = table.getData().get(i+j);
                Record r2 = table.getData().get(i+j+1);
                if (recSim.compare(r1, r2) >= threshold) {
                    duplicates.add(new Duplicate(r1, r2));
                }
                numComparisons += 1;
            }
        }

        // END SOLUTION
        System.out.printf("Sorted Neighborhood Detection found %d duplicates after %d comparisons%n", duplicates.size(), numComparisons);
        return duplicates;
    }
}

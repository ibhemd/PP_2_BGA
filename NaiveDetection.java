package src.PPClean.DuplicateDetection;

import src.PPClean.Data.Duplicate;
import src.PPClean.Data.Record;
import src.PPClean.Data.Table;
import src.PPClean.Similarity.RecordSimilarity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Duplicate detection algorithm which compares every record with every other
 */
public class NaiveDetection implements DuplicateDetection {

    double threshold;

    /**
     * @param threshold Threshold in range [0,1], Records at least this similar are considered Duplicates
     */
    public NaiveDetection(double threshold) {
        this.threshold = threshold;
    }

    /**
     * @param table Table to check for duplicates
     * @param recSim Similarity measure to use for comparing two records
     * @return Set of detected duplicates
     */
    public Set<Duplicate> detect(Table table, RecordSimilarity recSim) {
        List<Record> records = table.getData();
        Set<Duplicate> duplicates = new HashSet<>();
        int numComparisons = 0;
        // BEGIN SOLUTION
        for (int i = 0; i < records.size() - 1; i++) {
            for (int j = i+1; j < records.size(); j++) {
                Record r1 = records.get(i);
                Record r2 = records.get(j);
                numComparisons += 1;
                if (recSim.compare(r1,r2) >= threshold) {
                    duplicates.add(new Duplicate(r1, r2));
                }
            }
        }
        // END SOLUTION
        System.out.printf("Naive Detection found %d duplicates after %d comparisons%n", duplicates.size(), numComparisons);
        return duplicates;
    }
}

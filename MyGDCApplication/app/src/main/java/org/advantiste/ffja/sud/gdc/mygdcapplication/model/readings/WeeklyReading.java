package org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jean- on 19/11/2017.
 */

public class WeeklyReading extends AbstractReading{

    /**
     *
     */
    private int weekNumber;

    /**
     *
     */
    private long id;


    /**
     * The association between books and chapter count.
     */
    private Map<BibleBook,List<Integer>> readingDetails;

    /**
     * Creates a new reading
     * @param beginDate The begining date
     * @param endDate The end date
     * @param weekNumber The week number
     * @param readingDetails : Pour chaque chapitre à lire, on donne le chapitre de début et de fin
     */
    public WeeklyReading(long beginDate, long endDate, String comment,int weekNumber, Map<BibleBook,List<Integer>> readingDetails) {
        super(beginDate, endDate, comment);
        this.readingDetails = readingDetails;
        this.weekNumber = weekNumber;
    }

    /**
     * Gets the id
     * @return The id of the reading
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get week number
     * @return the week number
     */
    public int getWeekNumber() {
        return weekNumber;
    }

    /**
     * Gets the reading detail book / chapter association
     * @return The reading detail book / chapter association
     */
    public Map<BibleBook, List<Integer>> getReadingDetails() {
        return readingDetails;
    }
}

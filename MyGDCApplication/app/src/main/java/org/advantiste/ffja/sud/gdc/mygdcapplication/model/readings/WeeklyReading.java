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
     *
     */
    private Map<BibleBook,List<Integer>> readingDetails;

    /**
     *
     * @param beginDate
     * @param endDate
     * @param weekNumber
     * @param readingDetails : Pour chaque chapitre à lire, on donne le chapitre de début et de fin
     */
    public WeeklyReading(long beginDate, long endDate, String comment,int weekNumber, Map<BibleBook,List<Integer>> readingDetails) {
        super(beginDate, endDate, comment);
        this.readingDetails = readingDetails;
        this.weekNumber = weekNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public Map<BibleBook, List<Integer>> getReadingDetails() {
        return readingDetails;
    }
}

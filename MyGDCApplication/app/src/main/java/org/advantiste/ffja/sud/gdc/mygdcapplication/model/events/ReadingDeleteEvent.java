package org.advantiste.ffja.sud.gdc.mygdcapplication.model.events;

import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;

/**
 * The {@link ReadingDeleteEvent} shall be  used to transfer a Weekly reading deletion information.
 */
public class ReadingDeleteEvent {

    /**
     * The deleted weekly reading
     */
    private WeeklyReading weeklyReading;

    public ReadingDeleteEvent(WeeklyReading weeklyReading) {
        this.weeklyReading = weeklyReading;
    }

    public WeeklyReading getWeeklyReading() {
        return weeklyReading;
    }
}

package org.advantiste.ffja.sud.gdc.mygdcapplication.model.events;

import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.Prayer;

/**
 * The {@link PrayerDeleteEvent} shall be  used to transfer a Weekly reading deletion information.
 */
public class PrayerDeleteEvent {

    /**
     * The deleted weekly reading
     */
    private Prayer paryerId;

    public PrayerDeleteEvent(long prayerId) {
        this.paryerId = paryerId;
    }

    public Prayer getParyerId() {
        return paryerId;
    }
}

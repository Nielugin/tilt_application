package org.advantiste.ffja.sud.gdc.mygdcapplication.model;

import java.util.Date;


/**
 * The GDCModelElement is the base model class of the GDC model.
 */
public abstract  class GDCModelElement
{

    /**
     * The creation date of the object
     */
    private long creationDate;
    /**
     * The beginning date of the applicable period
     */
    private long beginDate;
    /**
     * The end date of the applicable period.
     */
    private long endDate;

    /**
     * Constructor of the base GDCModel element.
     * All elements have a creation date, and an applicable periode.
     * This period is defined from the start date and the end date.
     * @param beginDate The beginning date of the applicable periode
     * @param endDate The end date of the applicable periode.
     */
    public GDCModelElement( long beginDate, long endDate) {
        this.creationDate = System.currentTimeMillis();
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    /**
     * Returns whereas the element has begun or not;
     * @return True if has begun false other wise
     */
    public boolean hasBegun(){
        long currentTime=System.currentTimeMillis();
        return beginDate<currentTime;
    }

    /**
     * Returns whether the reading is terminated
     * @return True is the reading shall be terminated, false otherwise
     */
    public boolean isTerminated(){
        long currentTime = System.currentTimeMillis();
        return endDate<currentTime;
    }


    /**
     * Returns the creation date
     * @return The creation date.
     */
    public long getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date
     * @param creationDate The creation date as a long
     */
    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the begin date
     * @return The reading beginning date
     */
    public long getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the reading begin date
     * @param beginDate The begin date
     */
    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Gets the end date
     * @return The end date of the reading
     */
    public long getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the reading
     * @param endDate The end date of the reading.
     */
    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }
}


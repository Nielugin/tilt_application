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
     * @return
     */
    public boolean hasBegun(){
        long currentTime=System.currentTimeMillis();
        return beginDate<currentTime;
    }

    public boolean isTerminated(){
        long currentTime = System.currentTimeMillis();
        return endDate<currentTime;
    }


    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }
}


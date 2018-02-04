package org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings;

/**
 * Created by jean- on 07/11/2017.
 */

import org.advantiste.ffja.sud.gdc.mygdcapplication.model.GDCModelElement;

/**
 * The reading class represents a reading session.
 */
public class AbstractReading extends GDCModelElement {

    private String comment;


    public AbstractReading(long beginDate, long endDate, String comment) {
        super(beginDate, endDate);
        this.comment = comment;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}

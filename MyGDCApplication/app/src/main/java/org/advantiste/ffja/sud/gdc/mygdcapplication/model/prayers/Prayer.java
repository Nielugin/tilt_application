package org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers;

import org.advantiste.ffja.sud.gdc.mygdcapplication.model.GDCModelElement;

/**
 * Created by jean- on 07/11/2017.
 */

public class Prayer extends GDCModelElement{

    private long id;
    private String topic;
    private String comments;

    public Prayer(long beginDate, long endDate, String topic, String comments) {
        super(beginDate, endDate);
        this.topic = topic;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }




}

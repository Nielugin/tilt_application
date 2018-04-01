package org.advantiste.ffja.sud.gdc.mygdcapplication.model.sharings;

import org.advantiste.ffja.sud.gdc.mygdcapplication.model.GDCModelElement;

/**
 * Created by jean- on 01/04/2018.
 */

public class SharingQuestion {

    private long id;
    private String question;

    public SharingQuestion (long id, String question ) {
        this.id = id;
        this.question = question;
    }

    public long getId () {
        return id;
    }

    public void setId ( long id ) {
        this.id = id;
    }

    public String getQuestion () {
        return question;
    }

    public void setQuestion ( String question ) {
        this.question = question;
    }
}

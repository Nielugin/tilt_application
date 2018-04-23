package org.advantiste.ffja.sud.gdc.mygdcapplication.model.events;

import org.advantiste.ffja.sud.gdc.mygdcapplication.model.sharings.SharingQuestion;

/**
 * The {@link QuestionDeleteEvent} shall be  used to transfer a Weekly reading deletion information.
 */
public class QuestionDeleteEvent {

    /**
     * The deleted weekly reading
     */
    private SharingQuestion question;

    public QuestionDeleteEvent(SharingQuestion question) {
        this.question = question;
    }

    public SharingQuestion getQuestion() {
        return question;
    }
}

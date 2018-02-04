package org.adventiste.ffja.sud.masssmssender.model;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jean- on 02/12/2017.
 */

public class Campain {


    private long creationDate;
    private long sendDate;
    private Message messageToSend;
    private List<ContactsContract.Contacts> contactsList = new ArrayList<>();


    public Campain(final Message messageToSend, final List<ContactsContract.Contacts> contactsList) {
        this.creationDate = System.currentTimeMillis();
        this.messageToSend = messageToSend;
        this.contactsList = contactsList;
    }

    public long getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(final long creationDate) {
        this.creationDate = creationDate;
    }

    public long getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(final long sendDate) {
        this.sendDate = sendDate;
    }

    public Message getMessageToSend() {
        return this.messageToSend;
    }

    public void setMessageToSend(final Message messageToSend) {
        this.messageToSend = messageToSend;
    }

    public List<ContactsContract.Contacts> getContactsList() {
        return this.contactsList;
    }

    public void setContactsList(final List<ContactsContract.Contacts> contactsList) {
        this.contactsList = contactsList;
    }
}

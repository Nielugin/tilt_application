package org.adventiste.ffja.sud.masssmssender.model;

/**
 * The message class represents the message to send.
 * Created by jean- on 02/12/2017.
 */

public class Message {
    /**
     * The message title
     */
    private String title;

    /**
     * The message text.
     */
    private String message;

    /**
     * Constructo with both message and title
     * @param title The title of the message
     * @param message The message it self.
     */
    public Message(String title, String message) {
        this.title = title;
        this.message = message;
    }

    /**
     * Gets the title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title
     * @param title The title to set onto the message.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the message text.
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message text.
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

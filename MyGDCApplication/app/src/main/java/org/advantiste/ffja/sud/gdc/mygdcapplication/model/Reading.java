package org.advantiste.ffja.sud.gdc.mygdcapplication.model;

/**
 * Created by jean- on 07/11/2017.
 */

/**
 * The reading class represents a reading session.
 */
public class Reading {

    /**
     * The name of the book to read
     */
    private String book;

    /**
     * The number of chapter where to start
     */
    private int chapterFrom;

    /**
     * The number of the chapter where to stop.
     */
    private int chapterTo;

    public int getChapterTo() {
        return chapterTo;
    }

    public void setChapterTo(int chapterTo) {
        this.chapterTo = chapterTo;
    }

    public int getChapterFrom() {
        return chapterFrom;
    }

    public void setChapterFrom(int chapterFrom) {
        this.chapterFrom = chapterFrom;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    /**
     * Constructor
     * @param book The name of the book to read
     * @param chapterFrom The chapter where to start reading
     * @param chapterTo The chapter where to end reading
     */
    public Reading(String book, int chapterFrom, int chapterTo){
        this.chapterFrom =  chapterFrom;
        this.chapterTo = chapterTo;
        this.book = book;
    }
}

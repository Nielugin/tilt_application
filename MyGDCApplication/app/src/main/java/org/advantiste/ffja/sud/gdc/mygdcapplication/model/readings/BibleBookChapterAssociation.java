package org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings;

import java.util.HashMap;

/**
 * Created by jean- on 19/11/2017.
 */

public class BibleBookChapterAssociation {

    private HashMap<BibleBook,Integer> bibleIntegerEnumMap = new HashMap<>();

    public BibleBookChapterAssociation() {
        bibleIntegerEnumMap.put(BibleBook.GENESIS,50);
        bibleIntegerEnumMap.put(BibleBook.EXODUS, 40);
        bibleIntegerEnumMap.put(BibleBook.LEVITICUS,27);
        bibleIntegerEnumMap.put(BibleBook.NUMBERS, 36);
        bibleIntegerEnumMap.put(BibleBook.DEUTERONOMY, 34);
        bibleIntegerEnumMap.put(BibleBook.JOSHUA,24);
        bibleIntegerEnumMap.put(BibleBook.JUDGES,21);
        bibleIntegerEnumMap.put(BibleBook.RUTH,4);
        bibleIntegerEnumMap.put(BibleBook.SAMUEL1,31);
        bibleIntegerEnumMap.put(BibleBook.SAMUEL2,24);
        bibleIntegerEnumMap.put(BibleBook.KINGS1,22);
        bibleIntegerEnumMap.put(BibleBook.KINGS2,25);
        bibleIntegerEnumMap.put(BibleBook.CHRONICLES1,29);
        bibleIntegerEnumMap.put(BibleBook.CHRONICLES2,36);
        bibleIntegerEnumMap.put(BibleBook.EZRA,10);
        bibleIntegerEnumMap.put(BibleBook.NEHEMIAH, 13);
        bibleIntegerEnumMap.put(BibleBook.ESTHER,10);
        bibleIntegerEnumMap.put(BibleBook.JOB,42);
        bibleIntegerEnumMap.put(BibleBook.PSALM,150);
        bibleIntegerEnumMap.put(BibleBook.PROVERBS,31);
        bibleIntegerEnumMap.put(BibleBook.ECCLESIASTES,12);
        bibleIntegerEnumMap.put(BibleBook.SONGOFSOLOMON,8);
        bibleIntegerEnumMap.put(BibleBook.ISAIAH,66);
        bibleIntegerEnumMap.put(BibleBook.JEREMIAH,52);
        bibleIntegerEnumMap.put(BibleBook.LAMENTATIONS,5);
        bibleIntegerEnumMap.put(BibleBook.EZEKIEL,48);
        bibleIntegerEnumMap.put(BibleBook.DANIEL,12);
        bibleIntegerEnumMap.put(BibleBook.HOSEA,14);
        bibleIntegerEnumMap.put(BibleBook.JOEL,3);
        bibleIntegerEnumMap.put(BibleBook.AMOS,9);
        bibleIntegerEnumMap.put(BibleBook.OBADIAH,1);
        bibleIntegerEnumMap.put(BibleBook.JONAH,4);
        bibleIntegerEnumMap.put(BibleBook.MICAH,7);
        bibleIntegerEnumMap.put(BibleBook.NAHUM,3);
        bibleIntegerEnumMap.put(BibleBook.HABAKKUK,3);
        bibleIntegerEnumMap.put(BibleBook.ZEPHANIAH,3);
        bibleIntegerEnumMap.put(BibleBook.HAGGAI,2);
        bibleIntegerEnumMap.put(BibleBook.ZECHARIAH,14);
        bibleIntegerEnumMap.put(BibleBook.MALACHI,4);
        bibleIntegerEnumMap.put(BibleBook.MATTHEW,28);
        bibleIntegerEnumMap.put(BibleBook.MARK,16);
        bibleIntegerEnumMap.put(BibleBook.LUKE,24);
        bibleIntegerEnumMap.put(BibleBook.JOHN,21);
        bibleIntegerEnumMap.put(BibleBook.ACTS,28);
        bibleIntegerEnumMap.put(BibleBook.ROMANS,16);
        bibleIntegerEnumMap.put(BibleBook.CORINTHIANS1,16);
        bibleIntegerEnumMap.put(BibleBook.CORINTHIANS2,13);
        bibleIntegerEnumMap.put(BibleBook.GALATIANS,6);
        bibleIntegerEnumMap.put(BibleBook.EPHESIANS,6);
        bibleIntegerEnumMap.put(BibleBook.PHILIPPIANS,4);
        bibleIntegerEnumMap.put(BibleBook.COLOSSIANS,4);
        bibleIntegerEnumMap.put(BibleBook.THESSALONIANS1,5);
        bibleIntegerEnumMap.put(BibleBook.THESSALONIANS2,3);
        bibleIntegerEnumMap.put(BibleBook.TIMOTHY1,6);
        bibleIntegerEnumMap.put(BibleBook.TIMOTHY2,4);
        bibleIntegerEnumMap.put(BibleBook.TITUS,3);
        bibleIntegerEnumMap.put(BibleBook.PHILEMON,1);
        bibleIntegerEnumMap.put(BibleBook.HEBREWS,13);
        bibleIntegerEnumMap.put(BibleBook.JAMES,5);
        bibleIntegerEnumMap.put(BibleBook.PETER1,5);
        bibleIntegerEnumMap.put(BibleBook.PETER2,3);
        bibleIntegerEnumMap.put(BibleBook.JOHN1,5);
        bibleIntegerEnumMap.put(BibleBook.JOHN2,1);
        bibleIntegerEnumMap.put(BibleBook.JOHN3,1);
        bibleIntegerEnumMap.put(BibleBook.JUDE,1);
        bibleIntegerEnumMap.put(BibleBook.REVELATION,22);

    }


    public HashMap<BibleBook, Integer> getBibleIntegerEnumMap() {
        return bibleIntegerEnumMap;
    }
}

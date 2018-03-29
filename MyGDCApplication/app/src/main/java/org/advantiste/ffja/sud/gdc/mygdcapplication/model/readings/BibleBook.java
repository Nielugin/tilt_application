package org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings;

import java.util.EnumMap;

/**
 * Created by jean- on 19/11/2017.
 */

public enum BibleBook {

    GENESIS("Gen"),
    EXODUS("Ex"),
    LEVITICUS("Le"),
    NUMBERS("No"),
    DEUTERONOMY("De"),
    JOSHUA("Jos"),
    JUDGES("Jg"),
    RUTH("Ru"),
    SAMUEL1("1 S"),
    SAMUEL2("2 S"),
    KINGS1("1 R"),
    KINGS2("2 R"),
    CHRONICLES1("1 Ch"),
    CHRONICLES2("2 Ch"),
    EZRA("Esd"),
    NEHEMIAH("Né"),
    ESTHER("Est"),
    JOB("Job"),
    PSALM("Ps"),
    PROVERBS("Pr"),
    ECCLESIASTES("Ec"),
    SONGOFSOLOMON("Ca"),
    ISAIAH("Es"),
    JEREMIAH("Jé"),
    LAMENTATIONS("La"),
    EZEKIEL("Ez"),
    DANIEL("Da"),
    HOSEA("Os"),
    JOEL("Joë"),
    AMOS("Am"),
    OBADIAH("Ab"),
    JONAH("Jon"),
    MICAH("Mi"),
    NAHUM("Na"),
    HABAKKUK("Ha"),
    ZEPHANIAH("So"),
    HAGGAI("Ag"),
    ZECHARIAH("Za"),
    MALACHI("Mal"),
    MATTHEW("Mt"),
    MARK("Mc"),
    LUKE("Lu"),
    JOHN("Jn"),
    ACTS("Ac"),
    ROMANS("Ro"),
    CORINTHIANS1("1 Co"),
    CORINTHIANS2("2 Co"),
    GALATIANS("Ga"),
    EPHESIANS("Ep"),
    PHILIPPIANS("Ph"),
    COLOSSIANS("Col"),
    THESSALONIANS1("1 Th"),
    THESSALONIANS2("2 Th"),
    TIMOTHY1("1 Ti"),
    TIMOTHY2("2 Ti"),
    TITUS("Tit"),
    PHILEMON("Phm"),
    HEBREWS("Hé"),
    JAMES("Ja"),
    PETER1("1 Pi"),
    PETER2("2 Pi"),
    JOHN1("1 Jn"),
    JOHN2("2 Jn"),
    JOHN3("3 Jn"),
    JUDE("Jud"),
    REVELATION("Ap");


    private String abrev;
     BibleBook(String abrev){
this.abrev = abrev;
    }

    @Override
    public String toString () {
        return abrev;
    }


public static BibleBook fromString(String name){
BibleBook bibleBook = null;
    for (BibleBook bb: BibleBook.values ()  ) {
        if(bb.toString ().equals ( name )){
            bibleBook = bb;
        }
    }
    return bibleBook;
}

}

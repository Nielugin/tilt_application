package org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings;

import java.util.EnumMap;

/**
 * Created by jean- on 19/11/2017.
 */

public enum BibleBook {

    GENESIS("Gen","Génèse"),
    EXODUS("Ex","Exode"),
    LEVITICUS("Le","Lévétique"),
    NUMBERS("No","Nombres"),
    DEUTERONOMY("De","Deutéronome"),
    JOSHUA("Jos","Josué"),
    JUDGES("Jg","Juges"),
    RUTH("Ru","Ruth"),
    SAMUEL1("1 S","1 Samuel"),
    SAMUEL2("2 S","2 Samuel"),
    KINGS1("1 R","1 Rois"),
    KINGS2("2 R","2 Rois"),
    CHRONICLES1("1 Ch","1 Chroniques"),
    CHRONICLES2("2 Ch","2 Chroniques"),
    EZRA("Esd","Ezra"),
    NEHEMIAH("Né","Néhémie"),
    ESTHER("Est","Esther"),
    JOB("Job","Job"),
    PSALM("Ps","Psaumes"),
    PROVERBS("Pr","Proverbes" ),
    ECCLESIASTES("Ec","Ecclésiaste"),
    SONGOFSOLOMON("Ca","Salomon"),
    ISAIAH("Es","Esaïe"),
    JEREMIAH("Jé","Jérémie"),
    LAMENTATIONS("La","Lamentations"),
    EZEKIEL("Ez","Ezekiel"),
    DANIEL("Da","Daniel"),
    HOSEA("Os","Osée"),
    JOEL("Joë","Joel"),
    AMOS("Am","Amos"),
    OBADIAH("Ab","Obédias"),
    JONAH("Jon","Jonas"),
    MICAH("Mi","Michée"),
    NAHUM("Na","Nahoum"),
    HABAKKUK("Ha","Habakuk"),
    ZEPHANIAH("So","Sophonie"),
    HAGGAI("Ag","Aggée"),
    ZECHARIAH("Za","Zakarie"),
    MALACHI("Mal","Malachie"),
    MATTHEW("Mt","Matthieu"),
    MARK("Mc","Marc"),
    LUKE("Lu","Luc"),
    JOHN("Jn","Jean"),
    ACTS("Ac","Actes"),
    ROMANS("Ro","Romains"),
    CORINTHIANS1("1 Co","1 Corinthiens"),
    CORINTHIANS2("2 Co","2 Corinthiens"),
    GALATIANS("Ga","Galates"),
    EPHESIANS("Ep","Ephesiens"),
    PHILIPPIANS("Ph","Philippiens"),
    COLOSSIANS("Col","Colossiens"),
    THESSALONIANS1("1 Th","1 Thessaloniciens"),
    THESSALONIANS2("2 Th","2 Thessaloniciens"),
    TIMOTHY1("1 Ti", "1 Timothée"),
    TIMOTHY2("2 Ti", "2 Timothée"),
    TITUS("Tit","Tites"),
    PHILEMON("Phm", "Philemon"),
    HEBREWS("Hé","Hebreux"),
    JAMES("Ja","Jacques"),
    PETER1("1 Pi","1 Pierre"),
    PETER2("2 Pi","2 Pierre"),
    JOHN1("1 Jn","1 Jean"),
    JOHN2("2 Jn","2 Jean"),
    JOHN3("3 Jn","3 Jean"),
    JUDE("Jud","Jude"),
    REVELATION("Ap","Apocalypse");


    private String abrev;
    private  String longName;
    BibleBook(String abrev, String longName){
        this.abrev = abrev;
        this.longName = longName;
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


    public static BibleBook toLongName(String name){
        BibleBook bibleBook = null;
        for (BibleBook bb: BibleBook.values ()  ) {
            if(bb.toString ().equals ( name )){
                bibleBook = bb;
            }
        }
        return bibleBook;
    }


    public String getLongName () {
        return longName;
    }
}

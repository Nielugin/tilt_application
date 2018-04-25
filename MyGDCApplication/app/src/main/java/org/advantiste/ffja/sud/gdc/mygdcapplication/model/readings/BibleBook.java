package org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings;


/**
 * Created by jean- on 19/11/2017.
 */

public enum BibleBook {

    GENESIS("Gen","Génèse","GEN"),
    EXODUS("Ex","Exode","EXO"),
    LEVITICUS("Le","Lévétique","LEV"),
    NUMBERS("No","Nombres","NUM"),
    DEUTERONOMY("De","Deutéronome","DEU"),
    JOSHUA("Jos","Josué","JOS"),
    JUDGES("Jg","Juges","JDG"),
    RUTH("Ru","Ruth","RUT"),
    SAMUEL1("1 S","1 Samuel","1SA"),
    SAMUEL2("2 S","2 Samuel","2SA"),
    KINGS1("1 R","1 Rois","1KI"),
    KINGS2("2 R","2 Rois","2KI"),
    CHRONICLES1("1 Ch","1 Chroniques","1CH"),
    CHRONICLES2("2 Ch","2 Chroniques","2CH"),
    EZRA("Esd","Ezra","EZR"),
    NEHEMIAH("Né","Néhémie","NEH"),
    ESTHER("Est","Esther","ESG"),
    JOB("Job","Job","JOB"),
    PSALM("Ps","Psaumes","PSA"),
    PROVERBS("Pr","Proverbes","PRO" ),
    ECCLESIASTES("Ec","Ecclésiaste","ECC"),
    SONGOFSOLOMON("Ca","Salomon","SNG"),
    ISAIAH("Es","Esaïe","ISA"),
    JEREMIAH("Jé","Jérémie","JER"),
    LAMENTATIONS("La","Lamentations","LAM"),
    EZEKIEL("Ez","Ezekiel","EZK"),
    DANIEL("Da","Daniel","DAN"),
    HOSEA("Os","Osée","HOS"),
    JOEL("Joë","Joel","JOL"),
    AMOS("Am","Amos","AMO"),
    OBADIAH("Ab","Obédias","OBA"),
    JONAH("Jon","Jonas","JON"),
    MICAH("Mi","Michée","MIC"),
    NAHUM("Na","Nahoum","NAM"),
    HABAKKUK("Ha","Habakuk","HAB"),
    ZEPHANIAH("So","Sophonie","ZEP"),
    HAGGAI("Ag","Aggée","HAG"),
    ZECHARIAH("Za","Zakarie","ZEC"),
    MALACHI("Mal","Malachie","MAL"),
    MATTHEW("Mt","Matthieu","MAT"),
    MARK("Mc","Marc","MRK"),
    LUKE("Lu","Luc","LUK"),
    JOHN("Jn","Jean","JHN"),
    ACTS("Ac","Actes","ACT"),
    ROMANS("Ro","Romains","ROM"),
    CORINTHIANS1("1 Co","1 Corinthiens","1CO"),
    CORINTHIANS2("2 Co","2 Corinthiens","2CO"),
    GALATIANS("Ga","Galates","GAL"),
    EPHESIANS("Ep","Ephesiens","EPH"),
    PHILIPPIANS("Ph","Philippiens","PHP"),
    COLOSSIANS("Col","Colossiens","COL"),
    THESSALONIANS1("1 Th","1 Thessaloniciens","1TH"),
    THESSALONIANS2("2 Th","2 Thessaloniciens","2TH"),
    TIMOTHY1("1 Ti", "1 Timothée","1TI"),
    TIMOTHY2("2 Ti", "2 Timothée","2TI"),
    TITUS("Tit","Tites","TIT"),
    PHILEMON("Phm", "Philemon","PHM"),
    HEBREWS("Hé","Hebreux","HEB"),
    JAMES("Ja","Jacques","JAS"),
    PETER1("1 Pi","1 Pierre","1PE"),
    PETER2("2 Pi","2 Pierre","2PE"),
    JOHN1("1 Jn","1 Jean","1JN"),
    JOHN2("2 Jn","2 Jean","2JN"),
    JOHN3("3 Jn","3 Jean","3JN"),
    JUDE("Jud","Jude","JUD"),
    REVELATION("Ap","Apocalypse","REV");


    private String abrev;
    private  String longName;
    private String bibleAppId;
    BibleBook(String abrev, String longName,String bibleAppId){
        this.abrev = abrev;
        this.longName = longName;
        this.bibleAppId = bibleAppId;
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

    public String getBibleAppId() {
        return bibleAppId;
    }
}

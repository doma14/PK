package Aufgabe_6;

/**
 * Diese Klasse stellt einen erweiterbaren Ring zur Verf&uuml;gung.
 */
public class Ring {
    /**
     * int-Array, das die Ringelemente enth&auml;lt.
     */
    private int[] ring;

    /**
     * Erstes Ringelement
     */
    private int head;
    /**
     * Letztes Ringelement
     */
    private int tail;
    /**
     * Anzahl der Ringelemente
     */
    private int content;
    /**
     * Maximale Anzahl an Ringelementen
     */
    private int maximum;

    /**
     * Standardgr&ouml;&szlig;e, falls kein Wert angegeben wurde
     */
    private static final int INIT = 10;

    /**
     * Standardkonstruktor mit Standartwert
     */
    public Ring() {
        ring = new int[INIT];
        maximum = INIT;
    }

    /**
     * Konstruktor zum Initialisieren des Rings mit einer bestimmten Gr&ouml;&szlig;e,
	 * das hei&szlig;t, es wird festgelegt, wie viele Elemente zu Beginn im Ring gespeichert werden
	 * k&ouml;nnen.
     * Falls der Wert ung&uuml;ltig ist, wird der Standartwert verwendet.
     *
     * @param init Wert zur Initialisierung der Ringgr&ouml;&szlig;e.
     */
    public Ring(int init) {
        if (init > 0) {
            maximum = init;
        } else {
            maximum = INIT;
        }
        ring = new int[maximum];
    }

    /**
     * F&uuml;gt dem Ring einen Wert hinzu. Falls der Ring voll ist, wird er expandiert.
     *
     * @param value einzuf&uuml;gender Wert
     */
    public void push(int value) {
        ring[head] = value;
        head = (head + 1) % maximum;
        //head wird um 1 erhoeht und die restklasse modulo maximum gebildet, um feststellen zu koennen,
        //ob der ring voll ist. das ist der fall, wenn head == tail ist. dann wird der ring erweitert
        content++;
        if (head == tail) {
            expand();
        }
    }

    /**
     * Das letzte Element wird "entfernt", sofern das nicht das erste Element ist, d.h. der "Zeiger" auf das letzte
     * Element wird auf das vorletzte Element gesetzt.
     *
     * @return Wert des letzten Elements oder null, falls kein Element vorhanden ist
     */
    public Integer pop() {
        if (head != tail) {
            int oldTail = tail;
            tail = (tail + 1) % maximum;
            //tail wird um 1 erhoeht
            content--;
            return ring[oldTail];
        } else {
            return null;
        }
    }

    /**
     * Gibt die Anzahl der Ringelemente zur&uuml;ck.
     *
     * @return int Anzahl der Ringelemente
     */
    public int size() {
        return content;
    }

    /**
     * Die Ringgr&ouml;&szlig;e wird verdoppelt.
     * Dabei wird der Inhalt des alten Rings in einen neuen Ring der doppelten Gr&ouml;&szlig;e kopiert.
     */
    private void expand() {
        int size = maximum * 2;
        int[] newValues = new int[size];

        //kopiert den alten Ring in einen neuen Ring von doppelter Groesse
        for (int i = tail, j = 0; j < maximum; i = (i + 1) % maximum, j++) {
            newValues[j] = ring[i];
        }

        tail = 0;
        head = content;
        maximum = size;
        ring = newValues;
        newValues = null;
    }
}
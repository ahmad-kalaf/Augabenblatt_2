package de.hawhh.informatik.sml.mediathek.services.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.kunden.Kunde;
import de.hawhh.informatik.sml.mediathek.kunden.Kundennummer;
import de.hawhh.informatik.sml.mediathek.medien.CD;
import de.hawhh.informatik.sml.mediathek.medien.DVD;
import de.hawhh.informatik.sml.mediathek.services.Verleihkarte;
import de.hawhh.informatik.sml.mediathek.services.VerleihkartenComparator;
import de.hawhh.informatik.sml.mediathek.wertklassen.Datum;

/**
 * Testklasse für den VerleihkartenComparator
 * 
 * @author SE2-Team, PM2-Team
 */
public class VerleihkartenComparatorTest
{
    private VerleihkartenComparator _comparator;
    private Verleihkarte _verleihkarte1;
    private Verleihkarte _verleihkarte2;
    private Verleihkarte _verleihkarte3;

    public VerleihkartenComparatorTest() throws Exception
    {
        Kunde kunde1 = new Kunde(Kundennummer.get(222322), "Renate", "Becker");
        Kunde kunde2 = new Kunde(Kundennummer.get(334354), "Lena", "Schmidt");
        CD cd = new CD("Titel", "Kommentar", "Interpret", 200);
        DVD dvd = new DVD("Titel", "Kommentar", "Regisseur", 199);
        _verleihkarte1 = new Verleihkarte(kunde1, cd, Datum.heute());
        _verleihkarte2 = new Verleihkarte(kunde1, dvd, Datum.heute());
        _verleihkarte3 = new Verleihkarte(kunde2, cd, Datum.heute());
        _comparator = new VerleihkartenComparator();
    }

    /**
     * Testet folgendes: Wenn die Kunden gleich sind, soll nach dem Medium
     * sortiert werden.
     */
    @Test
    public void testCompareGleicherKunde()
    {
        // Gleicher Kunde, unterschiedliche Medien:
        assertTrue(_comparator.compare(_verleihkarte1, _verleihkarte2) < 0);

        // Gleicher Kunde, unterschiedliche Medien:
        assertTrue(_comparator.compare(_verleihkarte2, _verleihkarte1) > 0);

        // Gleicher Kunde, gleiche Medien:
        assertEquals(0, _comparator.compare(_verleihkarte1, _verleihkarte1));
    }

    /**
     * Testet folgendes: Wenn die Kunden unterschiedlich sind, soll nach den
     * Kunden sortiert werden.
     */
    @Test
    public void testVerschiedeneKunden()
    {

        // Unterschiedliche Kunden, gleiches Medium:
        assertTrue(_comparator.compare(_verleihkarte1, _verleihkarte3) < 0);

        // Unterschiedliche Kunden, gleiches Medium, andere Reihenfolge:
        assertTrue(_comparator.compare(_verleihkarte3, _verleihkarte1) > 0);

        // Unterschiedliche Kunden, unterschiedliche Medien. Würde nach den
        // Medien sortiert, so würde sich genau die andere Reihenfolge ergeben.
        assertTrue(_comparator.compare(_verleihkarte2, _verleihkarte3) < 0);
    }

}

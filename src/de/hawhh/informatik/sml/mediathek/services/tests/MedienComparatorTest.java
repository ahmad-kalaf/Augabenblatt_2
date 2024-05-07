package de.hawhh.informatik.sml.mediathek.services.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.medien.AbstractVideospiel;
import de.hawhh.informatik.sml.mediathek.medien.CD;
import de.hawhh.informatik.sml.mediathek.medien.DVD;
import de.hawhh.informatik.sml.mediathek.medien.KonsolenVideospiel;
import de.hawhh.informatik.sml.mediathek.services.MedienComparator;

/**
 * Testklasse für den MedienComparator
 * 
 * @author SE2-Team, PM2-Team
 * 
 */
public class MedienComparatorTest
{

    private CD _cd1;
    private CD _cd2;
    private CD _cd3;
    private DVD _dvd;
    private AbstractVideospiel _medium1;
    private MedienComparator _medienComparator;

    public MedienComparatorTest()
    {
        _cd1 = new CD("Titel a", "Kommentar", "Interpret", 100);
        _cd2 = new CD("Titel b", "Kommentar", "Interpret", 100);
        _cd3 = new CD("Titel b", "Kommentar", "Interpret", 100);

        _dvd = new DVD("Titel a", "Kommentar", "Regisseur", 100);
        _medium1 = new KonsolenVideospiel("Titel a", "Kommentar", "Wii");

        _medienComparator = new MedienComparator();
    }

    @Test
    public void testCompareEinMedium()
    {
        assertTrue(_medienComparator.compare(_cd3, _cd1) > 0);
        assertTrue(_medienComparator.compare(_cd1, _cd2) < 0);
        assertEquals(0, _medienComparator.compare(_cd3, _cd2));
    }

    @Test
    public void testCompareVerschiedeneMedien()
    {
        assertTrue(_medienComparator.compare(_cd2, _dvd) < 0);
        assertTrue(_medienComparator.compare(_dvd, _medium1) < 0);
    }
}

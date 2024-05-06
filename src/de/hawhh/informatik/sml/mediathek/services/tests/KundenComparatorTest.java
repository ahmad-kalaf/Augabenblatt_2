package de.hawhh.informatik.sml.mediathek.services.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.kunden.Kunde;
import de.hawhh.informatik.sml.mediathek.kunden.Kundennummer;
import de.hawhh.informatik.sml.mediathek.services.KundenComparator;

/**
 * Testklasse für den KundenComparator
 * 
 * @author SE2-Team, PM2-Team
 * 
 */
public class KundenComparatorTest
{
    private KundenComparator _comparator;
    private Kunde _kunde1;
    private Kunde _kunde2;
    private Kunde _kunde3;

    public KundenComparatorTest()
    {
        _kunde1 = new Kunde(Kundennummer.get(111111), "Hans", "Meier");
        _kunde2 = new Kunde(Kundennummer.get(543453), "Heidi", "Klum");
        _kunde3 = new Kunde(Kundennummer.get(432343), "Lara", "Klum");
        _comparator = new KundenComparator();
    }

    @Test
    public void testCompare()
    {
        assertTrue(_comparator.compare(_kunde1, _kunde2) > 0);
        assertTrue(_comparator.compare(_kunde2, _kunde1) < 0);
        assertEquals(0, _comparator.compare(_kunde2, _kunde3));
    }

}

package de.hawhh.informatik.sml.mediathek.wertklassen.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.wertklassen.Geldbetrag;

/**
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 * 
 */
public class GeldbetragTest
{

    @Test
    public final void testGeldbetrag()
    {
        Geldbetrag betrag = Geldbetrag.get(100);
        assertEquals(1, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("1,00", betrag.getFormatiertenString());

        betrag = Geldbetrag.get(0);
        assertEquals(0, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("0,00", betrag.getFormatiertenString());

        betrag = Geldbetrag.get(99);
        assertEquals(0, betrag.getEuroAnteil());
        assertEquals(99, betrag.getCentAnteil());
        assertEquals("0,99", betrag.getFormatiertenString());

        betrag = Geldbetrag.get(101);
        assertEquals(1, betrag.getEuroAnteil());
        assertEquals(1, betrag.getCentAnteil());
        assertEquals("1,01", betrag.getFormatiertenString());
    }

    @Test
    public final void testEqualsHashcode()
    {
        Geldbetrag betrag1 = Geldbetrag.get(100);
        Geldbetrag betrag2 = Geldbetrag.get(100);
        assertTrue(betrag1.equals(betrag2));
        assertTrue(betrag1.hashCode() == betrag2.hashCode());

        Geldbetrag betrag3 = Geldbetrag.get(101);
        assertFalse(betrag1.equals(betrag3));
        assertFalse(betrag1.hashCode() == betrag3.hashCode());

        Geldbetrag betrag4 = Geldbetrag.get(1000);
        assertFalse(betrag1.equals(betrag4));
        assertFalse(betrag1.hashCode() == betrag4.hashCode());
    }
}

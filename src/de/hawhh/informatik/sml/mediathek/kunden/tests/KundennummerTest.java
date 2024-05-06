package de.hawhh.informatik.sml.mediathek.kunden.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.kunden.Kundennummer;

/**
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 * 
 */
public class KundennummerTest
{

    @Test
    public void testEqualsUndHashcode()
    {
        Kundennummer kundennummer1 = Kundennummer.get(123456);
        assertEquals("123456", kundennummer1.toString());

        Kundennummer kundennummer2 = Kundennummer.get(123456);
        assertTrue(kundennummer1.equals(kundennummer2));
        assertTrue(kundennummer1.hashCode() == kundennummer2.hashCode());

        Kundennummer kundennummer3 = Kundennummer.get(654321);
        assertFalse(kundennummer1.equals(kundennummer3));
        assertFalse(kundennummer1.hashCode() == kundennummer3.hashCode());
    }

    @Test
    public void testIstGueltig()
    {
        assertTrue(Kundennummer.istGueltig(111111));
        assertFalse(Kundennummer.istGueltig(0));
        assertFalse(Kundennummer.istGueltig(1111111));
    }
}

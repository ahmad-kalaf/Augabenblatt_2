package de.hawhh.informatik.sml.mediathek.wertklassen.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.wertklassen.PLZ;

public class PLZTest
{

    @Test
    public void constructorTest()
    {
        PLZ plz1 = PLZ.get("22761");
        PLZ plz2 = PLZ.get("D-22760");
        assertEquals("22761", plz1.toString());
        assertEquals("D-22760", plz2.toString());
        assertNotSame(plz1, plz2);
    }

    @Test
    public void equalsUndHashCodeTest()
    {
        PLZ plz1 = PLZ.get("22761");
        PLZ plz2 = PLZ.get("D-22761");

        assertEquals(plz1, PLZ.get("22761"));
        assertTrue(plz1.equals(plz2));
        assertTrue(plz1.hashCode() == plz2.hashCode());

        PLZ plz3 = PLZ.get("22760");
        assertFalse(plz1.equals(plz3));
        assertFalse(plz1.hashCode() == plz3.hashCode());
    }
}

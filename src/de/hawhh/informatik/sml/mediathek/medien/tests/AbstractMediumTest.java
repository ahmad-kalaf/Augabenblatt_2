package de.hawhh.informatik.sml.mediathek.medien.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.medien.AbstractMedium;
import de.hawhh.informatik.sml.mediathek.medien.Medium;
import de.hawhh.informatik.sml.mediathek.wertklassen.Geldbetrag;

public abstract class AbstractMediumTest
{
	protected static final String KOMMENTAR = "Kommentar";
	protected static final String TITEL = "Titel";
	protected static final int LAENGE = 100;
	protected AbstractMedium _medium1;
	protected AbstractMedium _medium2;
	
	/**
	 * Initializiere neue Median
	 * @param medium
	 */
	public AbstractMediumTest()
	{
		_medium1 = erzeugeMedium();
		_medium2 = erzeugeMedium();
	}

	
	public abstract AbstractMedium erzeugeMedium();
	
	/**
     * Test die Mietgebür für eine angegebene Mietdauer
     * 
     * @param mietTage
     */
    @Test
    public void testBerechneMietgebuehr()
    {    			
    	assertEquals(Geldbetrag.get(900), _medium1.berechneMietgebuehr(3));
    	assertEquals(Geldbetrag.get(300), _medium1.berechneMietgebuehr(1));
    	assertEquals(Geldbetrag.get(1200), _medium1.berechneMietgebuehr(4));
    }

    @Test
    public void testKonstruktor()
    {
        assertEquals(TITEL, _medium1.getTitel());
        assertEquals(KOMMENTAR, _medium1.getKommentar());
    }

    @Test
    public void testSetter()
    {
    	_medium1.setKommentar("Kommentar2");
        assertEquals(_medium1.getKommentar(), "Kommentar2");
        _medium1.setTitel("Titel2");
        assertEquals(_medium1.getTitel(), "Titel2");
    }

    @Test
    /*
     * Von ein und der selben DVD kann es mehrere Exemplare geben, die von
     * unterschiedlichen Personen ausgeliehen werden können.
     */
    public void testEquals()
    {
        assertFalse("Mehrere Exemplare der gleichen DVD sind gleich",
                _medium1.equals(_medium2));
        assertTrue("Mehrere Exemplare der gleichen DVD sind ungleich",
        		!_medium1.equals(_medium2));
    }
    
    @Test
    public final void testGetFormatiertenString()
    {
        assertNotNull(erzeugeMedium().getFormatiertenString());
    }
}

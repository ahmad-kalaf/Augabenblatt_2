package de.hawhh.informatik.sml.mediathek.medien.tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.hawhh.informatik.sml.mediathek.medien.AbstractVideospiel;
import de.hawhh.informatik.sml.mediathek.medien.KonsolenVideospiel;
import de.hawhh.informatik.sml.mediathek.wertklassen.Geldbetrag;

public class KonsolenVideospielTest extends AbstractVideospielTest
{
	@Override
    public AbstractVideospiel erzeugeVideospiel() {
    	return new KonsolenVideospiel(TITEL, KOMMENTAR, SYSTEM);
    }
    
    @Test @Override
    public void testBerechneMietgebuehr()
    {    			
    	assertEquals(Geldbetrag.get(3000), _medium1.berechneMietgebuehr(10));
    	assertEquals(Geldbetrag.get(5100), _medium1.berechneMietgebuehr(20));
    	assertEquals(Geldbetrag.get(7200), _medium1.berechneMietgebuehr(30));
    }

	
	@Test
	public void testBerechneMietgebuehrAeqKlasseEins()
	{
		assertEquals(Geldbetrag.get(900), _medium1.berechneMietgebuehr(1));
		assertEquals(Geldbetrag.get(900), _medium1.berechneMietgebuehr(2));
		assertEquals(Geldbetrag.get(900), _medium1.berechneMietgebuehr(3));
	}

	@Test
	public void testBerechneMietgebuehrAeqKlasseZwei()
	{
		assertEquals(Geldbetrag.get(7200), _medium1.berechneMietgebuehr(28));
		assertEquals(Geldbetrag.get(7200), _medium1.berechneMietgebuehr(29));
		assertEquals(Geldbetrag.get(7200), _medium1.berechneMietgebuehr(30));
	}

	@Test
	public void testBerechneMietgebuehrGrenzwerteEins()
	{
		assertEquals(Geldbetrag.get(900), _medium1.berechneMietgebuehr(3));
		assertEquals(Geldbetrag.get(1600), _medium1.berechneMietgebuehr(4));
	}
	
	@Test
	public void testBerechneMietgebuehrGrenzwerteZwei()
	{
		assertEquals(Geldbetrag.get(7200), _medium1.berechneMietgebuehr(30));
		assertEquals(Geldbetrag.get(7900), _medium1.berechneMietgebuehr(31));
	}
}